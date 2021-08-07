package ImageProcessLibraryPackage.Algorithms;


import ImageProcessLibraryPackage.Functions.Interfaces.IWeightFunction;

import java.awt.Point;
import java.util.List;

import ImageProcessLibraryPackage.Utils.HoleHelperUtil;
import ImageProcessLibraryPackage.Utils.ImageHelperUtil;
import org.opencv.core.Mat;

public class FillHoleAlgorithm_impl extends FillHoleAlgorithmBase_impl {

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
