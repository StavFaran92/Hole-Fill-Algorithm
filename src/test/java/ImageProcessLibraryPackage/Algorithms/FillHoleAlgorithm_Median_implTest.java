package ImageProcessLibraryPackage.Algorithms;

import ImageProcessLibraryPackage.Algorithms.Interfaces.IFillHoleAlgorithm;
import ImageProcessLibraryPackage.Algorithms.Interfaces.IFindBoundAlgorithm;
import ImageProcessLibraryPackage.Functions.DefaultWeightFunction;
import ImageProcessLibraryPackage.ImageProcessingLibrary;
import ImageProcessLibraryPackage.Utils.HoleHelperUtil;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import java.awt.*;
import java.util.Set;

import static org.junit.Assert.*;

public class FillHoleAlgorithm_Median_implTest {

    @Before
    public void setUp() throws Exception {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Test
    public void invoke() {
        Mat mat = Mat.eye(7, 7, CvType.CV_32S);
        mat.setTo(new Scalar(0));
        mat.put(0,0, 100);
        mat.submat( 1,6,1,6).setTo(new Scalar(HoleHelperUtil.HOLE));
        System.out.println("mat old: " +mat.dump());
        try {
            IFillHoleAlgorithm algorithm = new FillHoleAlgorithm_Median_impl();
            algorithm.setFindBoundaryAlgorithm(new FindBoundaryAlgorithm_impl());
            algorithm.setFindHolesAlgorithm(new FindHoleAlgorithm_impl());
            algorithm.invoke(mat, mat, 0.00001, 3, ImageProcessingLibrary.C8W);



        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("mat new: " +mat.dump());
    }
}