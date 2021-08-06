package ImageProcessLibraryPackage.Algorithms;

import ImageProcessLibraryPackage.Algorithms.Interfaces.*;
import ImageProcessLibraryPackage.Functions.DefaultWeightFunction;
import ImageProcessLibraryPackage.Functions.Interfaces.IWeightFunction;

import java.awt.Point;
import java.util.List;
import org.opencv.core.Mat;

public class FillHoleAlgorithm_impl implements IFillHoleAlgorithm {

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

  private Mat FillHoles(Mat source, Mat dest, List<Point> holes, List<Point> boundaries, IWeightFunction weightFunction) throws Exception {

    if(holes.isEmpty())
    {
      System.out.println("Image does not contain any holes.");
      return source;
    }

    for (Point p: holes) {
      double intensity = evaluateIntensity(source, p, boundaries, weightFunction);
      dest.put(p.x, p.y, intensity);
    }
    return dest;

  }

  private double evaluateIntensity(Mat image, Point p, List<Point> boundaries, IWeightFunction weightFunction) throws Exception {

    if(image == null)
      throw new Exception("image cannot be null.");

    if(p == null)
      throw new Exception("Point specified is null.");

    if(boundaries.isEmpty())
      throw new Exception("boundaries list is empty.");

    double nominator = 0;
    double denominator = 0;
    for (Point b: boundaries) {
      double weight = weightFunction.invoke(p, b);
      nominator += weight * image.get(b.x, b.y)[0];
      denominator += weight;
    }

    if(denominator == 0)
      throw new Exception("Denominator cannot be zero.");

    return nominator / denominator;
  }
}
