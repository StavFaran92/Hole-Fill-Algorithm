package ImageProcessLibraryPackage.Algorithms;

import ImageProcessLibraryPackage.Algorithms.Interfaces.IFindBoundAlgorithm;
import ImageProcessLibraryPackage.ImageProcessingLibrary;
import ImageProcessLibraryPackage.Utils.HoleHelperUtil;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class FindBoundaryAlgorithm_implTest {

    @Before
    public void setUp() throws Exception {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Test
    public void TestEightWayConnectionBoundaries() {

        Mat mat = Mat.eye(3, 3, CvType.CV_32S);
        mat.setTo(new Scalar(0));
        mat.put(1,1, HoleHelperUtil.HOLE);
        IFindBoundAlgorithm findBoundAlgorithm = new FindBoundaryAlgorithm_impl();
        List<Point> boundaries = null;
        try {
            boundaries = (List<Point>)findBoundAlgorithm.FindOuterBoundary(mat, ImageProcessingLibrary.C8W, IFindBoundAlgorithm.LIST);
            List<Point> expected = Arrays.asList(new Point(0,0),new Point(0,1), new Point(0,2), new Point(1,0), new Point(1,2), new Point(2,0), new Point(2,1), new Point(2,2) );
            assertTrue(expected.size() == boundaries.size() && expected.containsAll(boundaries) && boundaries.containsAll(expected));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void TestFourWayConnectionBoundaries() {
        Mat mat = Mat.eye(3, 3, CvType.CV_32S);
        mat.setTo(new Scalar(0));
        mat.put(1,1, HoleHelperUtil.HOLE);
        IFindBoundAlgorithm findBoundAlgorithm = new FindBoundaryAlgorithm_impl();
        List<Point> boundaries = null;
        try {
            boundaries = (List<Point>)findBoundAlgorithm.FindOuterBoundary(mat, ImageProcessingLibrary.C4W, IFindBoundAlgorithm.LIST);
            List<Point> expected = Arrays.asList(new Point(1,0),new Point(0,1), new Point(2, 1), new Point(1, 2) );
            assertTrue(expected.size() == boundaries.size() && expected.containsAll(boundaries) && boundaries.containsAll(expected));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void TestFindInnerBoundary() {
        Mat mat = Mat.eye(5, 5, CvType.CV_32S);
        mat.setTo(new Scalar(0));
        mat.put(1,1, HoleHelperUtil.HOLE);
        mat.put(1,2, HoleHelperUtil.HOLE);
        mat.put(1,3, HoleHelperUtil.HOLE);
        mat.put(2,1, HoleHelperUtil.HOLE);
        mat.put(2,2, HoleHelperUtil.HOLE);
        mat.put(2,3, HoleHelperUtil.HOLE);
        mat.put(3,1, HoleHelperUtil.HOLE);
        mat.put(3,2, HoleHelperUtil.HOLE);
        mat.put(3,3, HoleHelperUtil.HOLE);
        IFindBoundAlgorithm findBoundAlgorithm = new FindBoundaryAlgorithm_impl();
        Set<Point> outerBoundaries = null;
        try {
            outerBoundaries = (Set<Point>)findBoundAlgorithm.FindOuterBoundary(mat, ImageProcessingLibrary.C8W, IFindBoundAlgorithm.SET);
            Set innerBoundaries = findBoundAlgorithm.FindInnerBoundary(mat, ImageProcessingLibrary.C8W, outerBoundaries);
            List<Point> expected = Arrays.asList(new Point(1,1),new Point(1,2), new Point(1, 3), new Point(2, 1), new Point(2, 3), new Point(3, 1), new Point(3, 2), new Point(3, 3)   );
            assertTrue(expected.size() == innerBoundaries.size() && expected.containsAll(innerBoundaries) && innerBoundaries.containsAll(expected));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}