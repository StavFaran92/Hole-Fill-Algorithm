package ImageProcessLibraryPackage.Algorithms;

import ImageProcessLibraryPackage.Algorithms.Interfaces.*;
import ImageProcessLibraryPackage.Functions.DefaultWeightFunction;
import ImageProcessLibraryPackage.Functions.Interfaces.IWeightFunction;

import ImageProcessLibraryPackage.ImageProcessingLibrary;
import java.awt.Point;
import java.util.List;
import org.opencv.core.Mat;

public class FillHoleAlgorithm_impl implements IFillHoleAlgorithm {

  private IFindBoundAlgorithm findBoundAlgorithm;
  private IFindHoleAlgorithm findHolesAlgorithm;

  @Override
  public Mat invoke(Mat image, Mat dest, double epsilon, double exponent, ImageProcessingLibrary.ConnectivityOption connectivityOption) {
    IWeightFunction weightFunction = new DefaultWeightFunction(exponent, epsilon);
    return invoke(image, dest, weightFunction, connectivityOption);
  }

  @Override
  public Mat invoke(Mat source, Mat dest, IWeightFunction weightFunction, ImageProcessingLibrary.ConnectivityOption connectivityOption) {

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

  private Mat FillHoles(Mat source, Mat dest, List<Point> holes, List<Point> boundaries, IWeightFunction weightFunction) {
    for (Point p: holes) {
      double intensity = GetIntensity(source, p, boundaries, weightFunction);
      dest.put(p.x, p.y, intensity);
    }
    return dest;

  }

  private double GetIntensity(Mat image, Point p, List<Point> boundaries, IWeightFunction weightFunction) {
    double nominator = 0;
    double denominator = 0;
    for (Point b: boundaries) {
      double weight = weightFunction.invoke(p, b);
      nominator += weight * image.get(b.x, b.y)[0];
      denominator += weight;
    }
    return nominator / denominator;
  }
}
