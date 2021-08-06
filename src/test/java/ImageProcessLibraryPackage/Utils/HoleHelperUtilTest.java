package ImageProcessLibraryPackage.Utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import static org.junit.Assert.*;
import static org.opencv.core.CvType.CV_32S;

public class HoleHelperUtilTest {

    @Before
    public void setUp() throws Exception {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Test
    public void isHole() {
        Mat mat = new Mat(3, 3, CV_32S);
        mat.setTo(new Scalar(0));
        mat.put(1,1, HoleHelperUtil.HOLE);

        try {
            Assert.assertTrue(HoleHelperUtil.isHole(mat, 1, 1));
            Assert.assertFalse(HoleHelperUtil.isHole(mat, 0, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void generateHoleInImageByThreshold() {
        Mat mat = new Mat(3, 3, CV_32S);
        mat.setTo(new Scalar(255));

        //We place a pixels with intensity 10 which is < threshold (50) to convert into hole
        mat.put(1,1, 10);
        Assert.assertFalse(mat.get(1,1)[0] == HoleHelperUtil.HOLE);
        try {
            HoleHelperUtil.generateHoleInImageByThreshold(mat, 50);
            Assert.assertTrue(mat.get(1,1)[0] == HoleHelperUtil.HOLE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void maskImage()  {
        Mat image = new Mat(3, 3, CV_32S);
        image.setTo(new Scalar(255));

        Mat mask = new Mat(5, 5, CV_32S);
        mask.setTo(new Scalar(255));

        mask.put(1, 1, HoleHelperUtil.HOLE);
        Assert.assertFalse(image.get(1,1)[0] == HoleHelperUtil.HOLE);
        try {
            HoleHelperUtil.maskImage(image, mask);
            Assert.assertTrue(image.get(1,1)[0] == HoleHelperUtil.HOLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}