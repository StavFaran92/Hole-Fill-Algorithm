package ImageProcessLibraryPackage.Algorithms;

import ImageProcessLibraryPackage.ImageProcessingLibrary;
import ImageProcessLibraryPackage.Utils.HoleHelperUtil;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import static org.junit.Assert.*;
import static org.opencv.core.CvType.CV_32S;

public class FillHoleAlgorithm_KDTree_implTest {

    @Before
    public void setUp() throws Exception {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Test
    public void fillHoles() {
        Mat mat = new Mat(3, 3, CV_32S);
        mat.setTo(new Scalar(0));
        mat.put(0,1, 100);
        mat.put(1,0, 255);
        mat.put(2,1, 100);
        mat.put(1,2, 255);
        mat.put(1,1, HoleHelperUtil.HOLE);
        System.out.println("mat old: " +mat.dump());
        Mat result = mat.clone();
        try {
            FillHoleAlgorithm_KDTree_impl algorithm = new FillHoleAlgorithm_KDTree_impl();
            algorithm.setFindBoundaryAlgorithm(new FindBoundaryAlgorithm_impl());
            algorithm.setFindHolesAlgorithm(new FindHoleAlgorithm_impl());
            algorithm.invoke(mat, result, 0.00001, 5, ImageProcessingLibrary.C8W);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("mat new: " +result.dump());
    }

    @Test
    public void evaluateIntensity() {
    }
}