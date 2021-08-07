package ImageProcessLibraryPackage.Algorithms;

import ImageProcessLibraryPackage.Functions.Interfaces.IWeightFunction;
import ImageProcessLibraryPackage.ImageProcessingLibrary;
import ImageProcessLibraryPackage.Utils.NeighborsHelperUtil;
import org.opencv.core.Mat;

import java.awt.*;
import java.util.Collection;
import java.util.List;

/**
 * This is a naive algorithm implementation, it performs in o(n) with OK results.
 * it iterates the holes and for each hole assigns it the median color of its neighbors that a re not holes.
 */
public class FillHoleAlgorithm_Median_impl extends FillHoleAlgorithm_Default_impl {

    @Override
    public Mat invoke(Mat source, Mat dest, IWeightFunction weightFunction, int connectivityOption) throws Exception {
        if(source == null)
            throw new NullPointerException("image cannot be null.");

        if(dest == null)
            throw new NullPointerException("dest cannot be null.");

        if(weightFunction == null)
            throw new NullPointerException("weight function is null.");

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
