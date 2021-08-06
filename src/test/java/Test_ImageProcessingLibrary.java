import ImageProcessLibraryPackage.Algorithms.FindBoundaryAlgorithm_impl;
import ImageProcessLibraryPackage.Algorithms.Interfaces.IFindBoundAlgorithm;
import ImageProcessLibraryPackage.ImageProcessingLibrary;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class Test_ImageProcessingLibrary {

  public static void main(String ...args)
  {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    Test_1();
  }

  private static void Test_1() {
    Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
    mat.put(1,1, 5);
    System.out.println("mat old = " + mat.dump());

    Mat dest = ImageProcessingLibrary.FillHoleAlgorithm(mat, 0.00001, 1, ImageProcessingLibrary.ConnectivityOption.EIGHT_WAY_CONNECTED);
    System.out.println("mat new = " + dest.dump());
  }
}
