package ImageProcessLibraryPackage.Algorithms;

import ImageProcessLibraryPackage.Algorithms.Interfaces.IFillHoleAlgorithm;
import ImageProcessLibraryPackage.Algorithms.Interfaces.IFindBoundAlgorithm;
import ImageProcessLibraryPackage.Functions.Interfaces.IWeightFunction;
import ImageProcessLibraryPackage.ImageProcessingLibrary;
import ImageProcessLibraryPackage.Utils.HoleHelperUtil;
import ImageProcessLibraryPackage.Utils.NeighborsHelperUtil;
import org.opencv.core.Mat;

import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * This is a naive algorithm implementation, it performs in o(n) with terrible results
 * calculating the median boundary color and assigning it to all the pixels.
 */
public class FillHoleAlgorithm_Median_impl extends FillHoleAlgorithm_impl {

    @Override
    public Mat invoke(Mat source, Mat dest, IWeightFunction weightFunction, int connectivityOption) throws Exception {
        
      List<Point> holes = findHolesAlgorithm.FindHoles(source);

      FillHoles(source, source,holes , null, weightFunction);

      return source;

  }

    @Override
    public void FillHoles(Mat source, Mat dest, Collection<Point> holes, Collection<Point> boundaries, IWeightFunction weightFunction) throws Exception {

        if(holes.isEmpty())
        {
            System.out.println("Image does not contain any holes.");
            return;
        }

        for (Point p: holes) {
            double intensity = evaluateIntensity(source, p, NeighborsHelperUtil.GetNeighbors(p, ImageProcessingLibrary.C8W), weightFunction);
            dest.put(p.x, p.y, intensity);
        }

    }
}
