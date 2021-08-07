package ImageProcessLibraryPackage.Functions;

import ImageProcessLibraryPackage.Functions.Interfaces.IWeightFunction;

import java.awt.Point;

public class DefaultWeightFunction  implements IWeightFunction {

  private double exponent;
  private double epsilon;

  public DefaultWeightFunction(double exponent, double epsilon) {
    this.exponent = exponent;
    this.epsilon = epsilon;
  }


  @Override
  public double invoke(Point u, Point v) throws Exception {
    if(u == null)
      throw new NullPointerException("first specified point is null.");

    if(v == null)
      throw new NullPointerException("second specified point is null.");

    return 1 / (Math.pow(u.distance(v), exponent) + epsilon);

  }
}
