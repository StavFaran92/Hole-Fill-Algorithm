package ImageProcessLibraryPackage.Algorithms;

import ImageProcessLibraryPackage.Algorithms.Interfaces.IFillHoleAlgorithm;
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

import static org.junit.Assert.*;
import static org.opencv.core.CvType.CV_32S;

public class FillHoleAlgorithm_implTest {

    @Before
    public void setUp() throws Exception {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Test
    public void invoke() {
        Mat mat = new Mat(3, 3, CV_32S);
        mat.setTo(new Scalar(0));
        mat.put(0,1, 100);
        mat.put(1,0, 255);
        mat.put(2,1, 100);
        mat.put(1,2, 255);
        mat.put(1,1, HoleHelperUtil.HOLE);
        System.out.println("mat old: " +mat.dump());
        ImageProcessingLibrary imageProcessingLibrary = new ImageProcessingLibrary();
        Mat result = imageProcessingLibrary.FillHoleAlgorithm(mat, 0.00001, 0, ImageProcessingLibrary.ConnectivityOption.EIGHT_WAY_CONNECTED);
        System.out.println("mat new: " +result.dump());
    }

    @Test
    public void invoke1() {
    }
}