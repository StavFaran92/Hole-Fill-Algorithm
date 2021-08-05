package ImageProcessLibraryPackage.Algorithms.Interfaces;

import ImageProcessLibraryPackage.ImageProcessingLibrary;
import java.awt.Point;
import java.util.List;
import org.opencv.core.Mat;

public interface IFindBoundAlgorithm {
  List<Point> invoke(Mat image, ImageProcessingLibrary.ConnectivityOption connectivityOption);
}
