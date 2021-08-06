package ImageProcessLibraryPackage.Utils;

import ImageProcessLibraryPackage.ImageProcessingLibrary;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;


import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.opencv.core.CvType.CV_32S;

public class NeighborsHelperUtilTest {

    @Before
    public void setUp() throws Exception {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Test
    public void getNeighbors() {

        Point p = new Point(5,4);
        List<Point> points = null;
        try {
            points = NeighborsHelperUtil.GetNeighbors(p, ImageProcessingLibrary.C4W);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Point> expected = Arrays.asList(new Point(6,4), new Point(4,4) , new Point(5,3), new Point(5, 5)  );
        assertTrue(expected.size() == points.size() && expected.containsAll(points) && points.containsAll(expected));
    }

    @Test
    public void checkNeighborsForHoles() {
        Mat mat = Mat.eye(3, 3, CvType.CV_32S);
        mat.setTo(new Scalar(0));
        mat.put(1,1, HoleHelperUtil.HOLE);

        try {
            Assert.assertTrue(NeighborsHelperUtil.checkNeighborsForHoles(mat, 0, 1, ImageProcessingLibrary.C4W));
            Assert.assertFalse(NeighborsHelperUtil.checkNeighborsForHoles(mat, 0, 0, ImageProcessingLibrary.C4W));
            Assert.assertTrue(NeighborsHelperUtil.checkNeighborsForHoles(mat, 0, 0, ImageProcessingLibrary.C8W));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}