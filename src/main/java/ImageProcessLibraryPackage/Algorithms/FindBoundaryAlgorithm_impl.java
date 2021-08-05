package ImageProcessLibraryPackage.Algorithms;

import ImageProcessLibraryPackage.Algorithms.Interfaces.*;
import ImageProcessLibraryPackage.ImageProcessingLibrary;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Mat;

public class FindBoundaryAlgorithm_impl implements IFindBoundAlgorithm {
  @Override
  public List<Point> invoke(Mat image, ImageProcessingLibrary.ConnectivityOption connectivityOption) {
    List<Point> boundaries = new ArrayList<>();
    for (int i = 0; i < image.rows(); i++) {
      for (int j = 0; j < image.cols(); j++) {
        if(image.get(i,j)[0] != -1  && ImageProcessingLibrary.NeighborsContainHole(image, i, j, connectivityOption))
          boundaries.add(new Point(i,j));

      }
    }
    return boundaries;
  }
}
