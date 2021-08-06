package ImageProcessLibraryPackage.Algorithms;

import ImageProcessLibraryPackage.Algorithms.Interfaces.IFindBoundAlgorithm;
import ImageProcessLibraryPackage.Algorithms.Interfaces.IFindHoleAlgorithm;
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

import static org.junit.Assert.*;

public class FindHoleAlgorithm_implTest {

    @Before
    public void setUp() throws Exception {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Test
    public void findHole_1() {
        Mat mat = Mat.eye(3, 3, CvType.CV_32S);
        mat.setTo(new Scalar(0));
        mat.put(1,1, HoleHelperUtil.HOLE);
        IFindHoleAlgorithm findHoleAlgorithm = new FindHoleAlgorithm_impl();
        List<Point> holes = null;
        try {
            holes = findHoleAlgorithm.invoke(mat);
            List<Point> expected = Arrays.asList(new Point(1,1) );
            assertTrue(expected.size() == holes.size() && expected.containsAll(holes) && holes.containsAll(expected));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void findHole_2() {
        Mat mat = Mat.eye(5, 5, CvType.CV_32S);
        mat.setTo(new Scalar(0));
        mat.put(1,1, HoleHelperUtil.HOLE);
        mat.put(2,1, HoleHelperUtil.HOLE);
        mat.put(2,2, HoleHelperUtil.HOLE);
        IFindHoleAlgorithm findHoleAlgorithm = new FindHoleAlgorithm_impl();
        List<Point> holes = null;
        try {
            holes = findHoleAlgorithm.invoke(mat);
            List<Point> expected = Arrays.asList(new Point(1,1), new Point(2,1) , new Point(2,2)  );
            assertTrue(expected.size() == holes.size() && expected.containsAll(holes) && holes.containsAll(expected));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}