package Functions.Interfaces;

import java.awt.Point;

public class DefaultWeightFunction  implements IWeightFunction{

  private double exponent;
  private double epsilon;

  public DefaultWeightFunction(double exponent, double epsilon) {
    this.exponent = exponent;
    this.epsilon = epsilon;
  }


  @Override
  public double invoke(Point u, Point v) {
    return 1 / (Math.pow(u.distance(v), exponent) + epsilon);

  }
}
