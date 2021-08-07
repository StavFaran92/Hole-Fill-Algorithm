package ImageProcessLibraryPackage.Algorithms.Interfaces;

import java.awt.Point;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.opencv.core.Mat;

public interface IFindBoundAlgorithm {
  public final static int
          LIST = 0,
          SET = 1;

  Collection<Point> FindOuterBoundary(Mat image, int connectivityOption, int returnType) throws Exception;
  Set<Point> FindInnerBoundary(Mat image, int connectivityOption, Set<Point> boundary) throws Exception;
}
