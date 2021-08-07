package ImageProcessLibraryPackage.Algorithms.Interfaces;

import ImageProcessLibraryPackage.Functions.Interfaces.IWeightFunction;
import org.opencv.core.Mat;

import java.awt.*;
import java.util.Collection;

public interface IFillHoleAlgorithm {

  Mat invoke(Mat image, Mat dest, double epsilon, double exponent, int connectivityOption) throws Exception;
  Mat invoke(Mat image, Mat dest, IWeightFunction weightFunction, int connectivityOption) throws Exception;

  void setFindBoundaryAlgorithm(IFindBoundAlgorithm findBoundAlgorithm);

  void setFindHolesAlgorithm(IFindHoleAlgorithm findBoundAlgorithm);

  void FillHoles(Mat source, Mat dest, Collection<Point> holes, Collection<Point> boundaries, IWeightFunction weightFunction) throws Exception;
  double evaluateIntensity(Mat image, Point p, Collection<Point> boundaries, IWeightFunction weightFunction) throws Exception;
}
