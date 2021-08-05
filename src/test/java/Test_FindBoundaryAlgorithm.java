import ImageProcessLibraryPackage.Algorithms.FindBoundaryAlgorithm_impl;
import ImageProcessLibraryPackage.Algorithms.Interfaces.IFindBoundAlgorithm;
import ImageProcessLibraryPackage.ImageProcessingLibrary;
import org.opencv.core.Mat;

public class Test_FindBoundaryAlgorithm {

  public static void main(String ...args)
  {
    Test_1();
  }

  private static void Test_1() {
    Mat image = new Mat(3, 3, 0);
    IFindBoundAlgorithm findBoundAlgorithm = new FindBoundaryAlgorithm_impl();
    findBoundAlgorithm.invoke(image, ImageProcessingLibrary.ConnectivityOption.FOUR_WAY_CONNECTED);
  }
}
