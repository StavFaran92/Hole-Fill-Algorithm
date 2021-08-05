package ImageProcessLibraryPackage.Algorithms.Interfaces;

import ImageProcessLibraryPackage.Functions.Interfaces.IWeightFunction;
import ImageProcessLibraryPackage.ImageProcessingLibrary;
import org.opencv.core.Mat;

public interface IFillHoleAlgorithm {

  Mat invoke(Mat image, Mat dest, double epsilon, double exponent, ImageProcessingLibrary.ConnectivityOption connectivityOption);
  Mat invoke(Mat image, Mat dest, IWeightFunction weightFunction, ImageProcessingLibrary.ConnectivityOption connectivityOption);

  void setFindBoundaryAlgorithm(IFindBoundAlgorithm findBoundAlgorithm);

  void setFindHolesAlgorithm(IFindHoleAlgorithm findBoundAlgorithm);
}
