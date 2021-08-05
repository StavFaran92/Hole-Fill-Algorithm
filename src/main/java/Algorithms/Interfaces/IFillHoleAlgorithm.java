package Algorithms.Interfaces;

import Functions.Interfaces.IWeightFunction;
import org.opencv.core.Mat;

public interface IFillHoleAlgorithm {

  Mat invoke(Mat image, Mat dest, double epsilon, double exponent);
  Mat invoke(Mat image, Mat dest, IWeightFunction weightFunction);

  void setFindBoundaryAlgorithm(IFindBoundAlgorithm findBoundAlgorithm);

  void setFindHolesAlgorithm(IFindHoleAlgorithm findBoundAlgorithm);
}
