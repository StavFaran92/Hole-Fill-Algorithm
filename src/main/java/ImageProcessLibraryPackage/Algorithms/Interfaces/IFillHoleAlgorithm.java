package ImageProcessLibraryPackage.Algorithms.Interfaces;

import ImageProcessLibraryPackage.Functions.Interfaces.IWeightFunction;
import org.opencv.core.Mat;

public interface IFillHoleAlgorithm {

  Mat invoke(Mat image, Mat dest, double epsilon, double exponent, int connectivityOption) throws Exception;
  Mat invoke(Mat image, Mat dest, IWeightFunction weightFunction, int connectivityOption) throws Exception;

  void setFindBoundaryAlgorithm(IFindBoundAlgorithm findBoundAlgorithm);

  void setFindHolesAlgorithm(IFindHoleAlgorithm findBoundAlgorithm);
}
