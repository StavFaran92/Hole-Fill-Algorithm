package ImageProcessLibraryPackage.Algorithms;

import ImageProcessLibraryPackage.Exceptions.ImageProcessException;
import ImageProcessLibraryPackage.Functions.Interfaces.IWeightFunction;
import ImageProcessLibraryPackage.Utils.ImageHelperUtil;
import org.opencv.core.Mat;

import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * This is a naive algorithm implementation, it performs in o(n) with terrible results
 * choosing random boundary pixel for each hole.
 */
public class FillHoleAlgorithm_Random_impl extends FillHoleAlgorithm_Default_impl {

  private Random rand = new Random();

  @Override
  public double evaluateIntensity(Mat image, Point p, Collection<Point> boundaries, IWeightFunction weightFunction) throws Exception {
    if (image == null)
      throw new NullPointerException("image cannot be null.");

    if (boundaries.isEmpty())
      throw new ImageProcessException("boundaries list is empty.");

    if(!(boundaries instanceof List))
        throw new ImageProcessException("boundaries needs to accept a list");

    Point randomPoint = ((List<Point>)boundaries).get(rand.nextInt(boundaries.size()));
    return ImageHelperUtil.get(image, randomPoint.x, randomPoint.y);
  }
}
