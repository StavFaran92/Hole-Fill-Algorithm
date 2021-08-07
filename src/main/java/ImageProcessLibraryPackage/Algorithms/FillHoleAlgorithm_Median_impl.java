package ImageProcessLibraryPackage.Algorithms;

import ImageProcessLibraryPackage.Algorithms.Interfaces.IFillHoleAlgorithm;
import ImageProcessLibraryPackage.Algorithms.Interfaces.IFindBoundAlgorithm;
import ImageProcessLibraryPackage.Functions.Interfaces.IWeightFunction;
import ImageProcessLibraryPackage.ImageProcessingLibrary;
import org.opencv.core.Mat;

import java.awt.*;
import java.util.Collection;
import java.util.Set;

/**
 * This is a naive algorithm implementation, it performs in o(n) with terrible results
 * calculating the median boundary color and assigning it to all the pixels.
 */
public class FillHoleAlgorithm_Median_impl extends FillHoleAlgorithm_impl {

  @Override
  public Mat invoke(Mat source, Mat dest, IWeightFunction weightFunction, int connectivityOption) throws Exception {

      Set outerBoundaries = (Set<Point>)findBoundAlgorithm.FindOuterBoundary(source, connectivityOption, IFindBoundAlgorithm.SET);
      Set innerBoundaries = findBoundAlgorithm.FindInnerBoundary(source, connectivityOption, outerBoundaries);


      while(!innerBoundaries.isEmpty()) {

          FillHoles(source, dest, innerBoundaries, outerBoundaries, weightFunction);

          outerBoundaries = (Set<Point>) findBoundAlgorithm.FindOuterBoundary(source, connectivityOption, IFindBoundAlgorithm.SET);
          innerBoundaries = findBoundAlgorithm.FindInnerBoundary(source, connectivityOption, outerBoundaries);
      }

    return dest;

  }
}
