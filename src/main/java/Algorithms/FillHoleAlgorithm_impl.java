package Algorithms;

import Algorithms.Interfaces.IFillHoleAlgorithm;
import Algorithms.Interfaces.IFindBoundAlgorithm;
import Algorithms.Interfaces.IFindHoleAlgorithm;
import Functions.Interfaces.DefaultWeightFunction;
import Functions.Interfaces.IWeightFunction;


import java.awt.Point;
import java.util.List;
import org.opencv.core.Mat;

public class FillHoleAlgorithm_impl implements IFillHoleAlgorithm {

  private IFindBoundAlgorithm findBoundAlgorithm;
  private IFindHoleAlgorithm findHolesAlgorithm;

  @Override
  public Mat invoke(Mat image, Mat dest, double exponent, double epsilon) {
    IWeightFunction weightFunction = new DefaultWeightFunction(exponent, epsilon);
    return invoke(image, dest, weightFunction);
  }

  @Override
  public Mat invoke(Mat source, Mat dest, IWeightFunction weightFunction) {

    List<Point> boundaries = findBoundAlgorithm.invoke(source);

    List<Point> holes = findHolesAlgorithm.invoke(source);

    FillHoles(source, dest, holes, boundaries, weightFunction);

    return dest;
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


  @Override
  public void setFindBoundaryAlgorithm(IFindBoundAlgorithm findBoundAlgorithm) {
    this.findBoundAlgorithm = findBoundAlgorithm;
  }

  @Override
  public void setFindHolesAlgorithm(IFindHoleAlgorithm findHolesAlgorithm) {
    this.findHolesAlgorithm = findHolesAlgorithm;
  }
}
