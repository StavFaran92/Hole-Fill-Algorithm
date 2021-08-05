package ImageProcessLibraryPackage.Algorithms;

import ImageProcessLibraryPackage.Algorithms.Interfaces.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Mat;

public class FindHoleAlgorithm_impl implements IFindHoleAlgorithm {
  @Override
  public List<Point> invoke(Mat image) {
    List<Point> holes = new ArrayList<>();
    for (int i = 0; i < image.rows(); i++) {
      for (int j = 0; j < image.cols(); j++) {
        if(image.get(i,j)[0] == -1)
          holes.add(new Point(i,j));

      }
    }
    return holes;
  }
}
