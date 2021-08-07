package ImageProcessLibraryPackage;

import ImageProcessLibraryPackage.Utils.HoleHelperUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import static org.junit.Assert.*;

public class ImageProcessingLibraryTest {

    @Before
    public void setUp() throws Exception {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Test
    public void Test() {

        Mat mat;
        Mat dest = null;
        try {
            mat = Mat.eye(3, 3, CvType.CV_32S);
            mat.put(1,1, HoleHelperUtil.HOLE);
            Assert.assertTrue(HoleHelperUtil.imageContainsHoles(mat));
            dest = ImageProcessingLibrary.FillHoleAlgorithm(mat, 0.00001, 1, ImageProcessingLibrary.C8W, ImageProcessingLibrary.FHA_Default);
            Assert.assertFalse(HoleHelperUtil.imageContainsHoles(dest));
            mat = Mat.eye(3, 3, CvType.CV_32S);
            mat.put(1,1, HoleHelperUtil.HOLE);
            Assert.assertTrue(HoleHelperUtil.imageContainsHoles(mat));
            dest = ImageProcessingLibrary.FillHoleAlgorithm(mat, 0.00001, 1, ImageProcessingLibrary.C8W, ImageProcessingLibrary.FHA_Median);
            Assert.assertFalse(HoleHelperUtil.imageContainsHoles(dest));
            mat = Mat.eye(3, 3, CvType.CV_32S);
            mat.put(1,1, HoleHelperUtil.HOLE);
            Assert.assertTrue(HoleHelperUtil.imageContainsHoles(mat));
            dest = ImageProcessingLibrary.FillHoleAlgorithm(mat, 0.00001, 1, ImageProcessingLibrary.C8W, ImageProcessingLibrary.FHA_KDTree);
            Assert.assertFalse(HoleHelperUtil.imageContainsHoles(dest));
            mat = Mat.eye(3, 3, CvType.CV_32S);
            mat.put(1,1, HoleHelperUtil.HOLE);
            Assert.assertTrue(HoleHelperUtil.imageContainsHoles(mat));
            dest = ImageProcessingLibrary.FillHoleAlgorithm(mat, 0.00001, 1, ImageProcessingLibrary.C8W, ImageProcessingLibrary.FHA_Random);
            Assert.assertFalse(HoleHelperUtil.imageContainsHoles(dest));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}