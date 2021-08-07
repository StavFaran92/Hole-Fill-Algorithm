package ImageProcessLibraryPackage.Algorithms;

import ImageProcessLibraryPackage.Algorithms.Interfaces.IFillHoleAlgorithm;
import ImageProcessLibraryPackage.Algorithms.Interfaces.IFindBoundAlgorithm;
import ImageProcessLibraryPackage.Algorithms.Interfaces.IFindHoleAlgorithm;
import ImageProcessLibraryPackage.Functions.DefaultWeightFunction;
import ImageProcessLibraryPackage.Functions.Interfaces.IWeightFunction;
import ImageProcessLibraryPackage.Utils.HoleHelperUtil;
import ImageProcessLibraryPackage.Utils.ImageHelperUtil;
import org.opencv.core.Mat;

import java.awt.*;
import java.util.Collection;
import java.util.List;

public class FillHoleAlgorithm_impl implements IFillHoleAlgorithm {

  protected IFindBoundAlgorithm findBoundAlgorithm;
  protected IFindHoleAlgorithm findHolesAlgorithm;

  @Override
  public Mat invoke(Mat image, Mat dest, double epsilon, double exponent, int connectivityOption) throws Exception {
    IWeightFunction weightFunction = new DefaultWeightFunction(exponent, epsilon);
    return invoke(image, dest, weightFunction, connectivityOption);
  }

  /**
   * This is a wrapper function for FillHoles.
   * @param source
   * @param dest
   * @param weightFunction
   * @param connectivityOption
   * @return
   * @throws Exception
   */
  @Override
  public Mat invoke(Mat source, Mat dest, IWeightFunction weightFunction, int connectivityOption) throws Exception {

    if(source == null)
      throw new Exception("image cannot be null.");

    if(dest == null)
      throw new Exception("dest cannot be null.");

    if(weightFunction == null)
      throw new Exception("weight function is null.");

    if(connectivityOption < 0)
      throw new Exception("connectivity option cannot be negative: " + connectivityOption);

    Collection<Point> boundaries  = findBoundAlgorithm.FindOuterBoundary(source, connectivityOption, IFindBoundAlgorithm.LIST);

    List<Point> holes = findHolesAlgorithm.FindHoles(source);

    FillHoles(source, dest, holes, boundaries, weightFunction);

    return dest;
  }

  @Override
  public void setFindBoundaryAlgorithm(IFindBoundAlgorithm findBoundAlgorithm) {
    this.findBoundAlgorithm = findBoundAlgorithm;
  }

  @Override
  public void setFindHolesAlgorithm(IFindHoleAlgorithm findHolesAlgorithm) {
    this.findHolesAlgorithm = findHolesAlgorithm;
  }

  @Override
  public void FillHoles(Mat source, Mat dest, Collection<Point> holes, Collection<Point> boundaries, IWeightFunction weightFunction) throws Exception {

    if(holes.isEmpty())
    {
      System.out.println("Image does not contain any holes.");
      return;
    }

    for (Point p: holes) {
      double intensity = evaluateIntensity(source, p, boundaries, weightFunction);
      dest.put(p.x, p.y, intensity);
    }

  }

  @Override
  public double evaluateIntensity(Mat image, Point p, Collection<Point> boundaries, IWeightFunction weightFunction) throws Exception {

    if(image == null)
      throw new Exception("image cannot be null.");

    if(p == null)
      throw new Exception("Point specified is null.");

    if(boundaries.isEmpty())
      throw new Exception("boundaries list is empty.");

    double nominator = 0;
    double denominator = 0;
    for (Point b: boundaries) {
      if(HoleHelperUtil.isHole(image, b.x, b.y))
        continue;
      double weight = weightFunction.invoke(p, b);
      nominator += weight * ImageHelperUtil.get(image, b.x, b.y);
      denominator += weight;
    }

    if(denominator == 0)
      throw new Exception("Denominator cannot be zero.");

    return nominator / denominator;
  }
}
