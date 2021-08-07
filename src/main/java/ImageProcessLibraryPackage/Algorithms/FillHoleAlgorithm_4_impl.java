package ImageProcessLibraryPackage.Algorithms;


import ImageProcessLibraryPackage.Functions.Interfaces.IWeightFunction;
import org.opencv.core.Mat;

import java.awt.*;
import java.util.List;

public class FillHoleAlgorithm_4_impl extends FillHoleAlgorithmBase_impl {

  protected double evaluateIntensity(Mat image, Point p, List<Point> boundaries, IWeightFunction weightFunction) throws Exception {

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
