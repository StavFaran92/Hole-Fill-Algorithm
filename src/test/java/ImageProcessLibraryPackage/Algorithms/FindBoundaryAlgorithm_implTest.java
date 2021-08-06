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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<Point> boundaries = findBoundAlgorithm.invoke(mat, ImageProcessingLibrary.ConnectivityOption.EIGHT_WAY_CONNECTED);
        List<Point> expected = Arrays.asList(new Point(0,0),new Point(0,1), new Point(0,2), new Point(1,0), new Point(1,2), new Point(2,0), new Point(2,1), new Point(2,2) );
        assertTrue(expected.size() == boundaries.size() && expected.containsAll(boundaries) && boundaries.containsAll(expected));
    }

    @Test
    public void TestFourWayConnectionBoundaries() {
        Mat mat = Mat.eye(3, 3, CvType.CV_32S);
        mat.setTo(new Scalar(0));
        mat.put(1,1, HoleHelperUtil.HOLE);
        IFindBoundAlgorithm findBoundAlgorithm = new FindBoundaryAlgorithm_impl();
        List<Point> boundaries = findBoundAlgorithm.invoke(mat, ImageProcessingLibrary.ConnectivityOption.FOUR_WAY_CONNECTED);
        List<Point> expected = Arrays.asList(new Point(1,0),new Point(0,1), new Point(2, 1), new Point(1, 2) );
        assertTrue(expected.size() == boundaries.size() && expected.containsAll(boundaries) && boundaries.containsAll(expected));
    }
}