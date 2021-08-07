package ImageProcessLibraryPackage.Algorithms;

import ImageProcessLibraryPackage.Algorithms.Interfaces.IFillHoleAlgorithm;
import ImageProcessLibraryPackage.Algorithms.Interfaces.IFindBoundAlgorithm;
import ImageProcessLibraryPackage.Functions.DefaultWeightFunction;
import ImageProcessLibraryPackage.ImageProcessingLibrary;
import ImageProcessLibraryPackage.Utils.HoleHelperUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.commons.util.CollectionUtils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import java.awt.*;
import java.util.*;
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
        Mat result = mat.clone();
        try {
            IFillHoleAlgorithm algorithm = new FillHoleAlgorithm_impl();
            algorithm.setFindBoundaryAlgorithm(new FindBoundaryAlgorithm_impl());
            algorithm.setFindHolesAlgorithm(new FindHoleAlgorithm_impl());
            algorithm.invoke(mat, result, 0.00001, 0, ImageProcessingLibrary.C8W);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("mat new: " +result.dump());
    }

    @Test
    public void invoke1() {
        Mat mat = Mat.eye(7, 7, CvType.CV_32S);
        mat.setTo(new Scalar(0));
        mat.put(0,0, 100);
        mat.submat( 1,6,1,6).setTo(new Scalar(HoleHelperUtil.HOLE));
        System.out.println("mat old: " +mat.dump());
        Mat result = mat.clone();
        try {
            FillHoleAlgorithm_impl algorithm = new FillHoleAlgorithm_impl();
            FindBoundaryAlgorithm_impl findBoundAlgorithm = new FindBoundaryAlgorithm_impl();
            algorithm.setFindBoundaryAlgorithm(findBoundAlgorithm);
            FindHoleAlgorithm_impl findHolesAlgorithm = new FindHoleAlgorithm_impl();
            algorithm.setFindHolesAlgorithm(findHolesAlgorithm);

            Set outerBoundaries = (Set<Point>)findBoundAlgorithm.FindOuterBoundary(mat, ImageProcessingLibrary.C8W, IFindBoundAlgorithm.SET);
            Set innerBoundaries = findBoundAlgorithm.FindInnerBoundary(mat, ImageProcessingLibrary.C8W, outerBoundaries);


            while(!innerBoundaries.isEmpty()) {

                List<Point> innerBoundariesAsList = new ArrayList<>(innerBoundaries);
                List<Point> outerBoundariesAsList = new ArrayList<>(outerBoundaries);

                algorithm.FillHoles(mat, mat, innerBoundariesAsList, outerBoundariesAsList, new DefaultWeightFunction(5, 0.0001));

                outerBoundaries = (Set<Point>) findBoundAlgorithm.FindOuterBoundary(mat, ImageProcessingLibrary.C8W, IFindBoundAlgorithm.SET);
                innerBoundaries = findBoundAlgorithm.FindInnerBoundary(mat, ImageProcessingLibrary.C8W, outerBoundaries);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("mat new: " +mat.dump());
    }
}