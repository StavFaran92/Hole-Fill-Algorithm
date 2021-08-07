package ImageProcessLibraryPackage.Algorithms;

import ImageProcessLibraryPackage.Functions.Interfaces.IWeightFunction;
import org.opencv.core.Mat;

import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * This is a naive algorithm implementation, it performs in o(n) with terrible results
 * choosing random boundary pixel for each hole.
 */
public class FillHoleAlgorithm_2_impl extends FillHoleAlgorithmBase_impl {

  private Random rand = new Random();

  @Override
  protected double evaluateIntensity(Mat image, Point p, List<Point> boundaries, IWeightFunction weightFunction) throws Exception {
    if (image == null)
      throw new Exception("image cannot be null.");

    if (boundaries.isEmpty())
      throw new Exception("boundaries list is empty.");

    Point randomPoint = boundaries.get(rand.nextInt(boundaries.size()));
    return image.get(randomPoint.x, randomPoint.y)[0];
  }
}
