package ImageProcessLibraryPackage.Algorithms.Interfaces;

import java.awt.Point;
import java.util.List;
import org.opencv.core.Mat;


public interface IFindHoleAlgorithm {
  List<Point> FindHoles(Mat image) throws Exception;
}
