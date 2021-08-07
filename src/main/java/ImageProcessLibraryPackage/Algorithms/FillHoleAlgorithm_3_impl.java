package ImageProcessLibraryPackage.Algorithms;

import ImageProcessLibraryPackage.Functions.Interfaces.IWeightFunction;
import org.opencv.core.Mat;

import java.awt.*;
import java.util.List;

/**
 * This is a naive algorithm implementation, it performs in o(n) with terrible results
 * calculating the median boundary color and assigning it to all the pixels.
 */
public class FillHoleAlgorithm_3_impl extends FillHoleAlgorithmBase_impl {

  @Override
  protected void FillHoles(Mat source, Mat dest, List<Point> holes, List<Point> boundaries, IWeightFunction weightFunction) throws Exception {

    if(holes.isEmpty())
    {
      System.out.println("Image does not contain any holes.");
      return;
    }

    double intensity = evaluateIntensity(source, null, boundaries, null);
    for (Point p: holes) {
      dest.put(p.x, p.y, intensity);
    }

  }

  protected double evaluateIntensity(Mat image, Point p, List<Point> boundaries, IWeightFunction weightFunction) throws Exception {

    if(boundaries.isEmpty())
      throw new Exception("boundaries list is empty.");

    double nominator = 0;
    double denominator = boundaries.size();
    for (Point b: boundaries) {
      nominator += image.get(b.x, b.y)[0];
    }

    return nominator / denominator;
  }
}
