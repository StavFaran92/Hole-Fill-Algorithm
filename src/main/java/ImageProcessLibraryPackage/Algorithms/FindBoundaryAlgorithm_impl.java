package ImageProcessLibraryPackage.Algorithms;

import ImageProcessLibraryPackage.Algorithms.Interfaces.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import ImageProcessLibraryPackage.Utils.NeighborsHelperUtil;
import org.opencv.core.Mat;

import static ImageProcessLibraryPackage.Utils.HoleHelperUtil.isHole;

public class FindBoundaryAlgorithm_impl implements IFindBoundAlgorithm {
  @Override
  public List<Point> invoke(Mat image, int connectivityOption) throws Exception {

    if(image == null)
      throw new Exception("image cannot be null.");

    List<Point> boundaries = new ArrayList<>();
    for (int i = 0; i < image.rows(); i++) {
      for (int j = 0; j < image.cols(); j++) {
        if(!isHole(image, i, j)  && NeighborsHelperUtil.checkNeighborsForHoles(image, i, j, connectivityOption))
          boundaries.add(new Point(i,j));
      }
    }
    return boundaries;
  }
}
