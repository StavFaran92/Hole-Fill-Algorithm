package ImageProcessLibraryPackage.Algorithms;

import ImageProcessLibraryPackage.Algorithms.Interfaces.IFillHoleAlgorithm;
import ImageProcessLibraryPackage.Algorithms.Interfaces.IFindBoundAlgorithm;
import ImageProcessLibraryPackage.Algorithms.Interfaces.IFindHoleAlgorithm;
import ImageProcessLibraryPackage.Functions.DefaultWeightFunction;
import ImageProcessLibraryPackage.Functions.Interfaces.IWeightFunction;
import org.opencv.core.Mat;

import java.awt.*;
import java.util.List;

public abstract class FillHoleAlgorithmBase_impl implements IFillHoleAlgorithm {

  private IFindBoundAlgorithm findBoundAlgorithm;
  private IFindHoleAlgorithm findHolesAlgorithm;

  @Override
  public Mat invoke(Mat image, Mat dest, double epsilon, double exponent, int connectivityOption) throws Exception {
    IWeightFunction weightFunction = new DefaultWeightFunction(exponent, epsilon);
    return invoke(image, dest, weightFunction, connectivityOption);
  }

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

    List<Point> boundaries = findBoundAlgorithm.invoke(source, connectivityOption);

    List<Point> holes = findHolesAlgorithm.invoke(source);

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

  protected void FillHoles(Mat source, Mat dest, List<Point> holes, List<Point> boundaries, IWeightFunction weightFunction) throws Exception {

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

  protected abstract double evaluateIntensity(Mat image, Point p, List<Point> boundaries, IWeightFunction weightFunction)throws Exception;
}
