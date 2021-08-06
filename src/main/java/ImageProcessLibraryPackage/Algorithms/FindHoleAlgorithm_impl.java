package ImageProcessLibraryPackage.Algorithms;

import ImageProcessLibraryPackage.Algorithms.Interfaces.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;

import static ImageProcessLibraryPackage.Utils.HoleHelperUtil.isHole;

public class FindHoleAlgorithm_impl implements IFindHoleAlgorithm {
  @Override
  public List<Point> invoke(Mat image) throws Exception {

    if(image == null)
      throw new Exception("image cannot be null.");
    
    List<Point> holes = new ArrayList<>();
    for (int i = 0; i < image.rows(); i++) {
      for (int j = 0; j < image.cols(); j++) {
        if(isHole(image, i, j))
          holes.add(new Point(i,j));
      }
    }
    return holes;
  }
}
