package ImageProcessLibraryPackage.Utils;

import org.opencv.core.Mat;

public class HoleHelperUtil {
    public static int HOLE = -1;

    public static boolean isHole(Mat source, int row, int col) throws Exception {

        if(source == null)
            throw new NullPointerException("image cannot be null.");

        if(row < 0 || row > source.rows() || col < 0 || col > source.cols())
            throw new Exception("indeces outside of image, row: " + row + " col: " + col);

        return (ImageHelperUtil.get(source, row, col) == HOLE);
    }

    public static Mat generateHoleInImageByThreshold(Mat image, int threshold) throws Exception {

        if(image == null)
            throw new Exception("image cannot be null.");

        if(threshold < 0 || threshold > 255)
            throw new Exception("Illegal threshold, should be between 0 - 255.");

        for (int i = 0; i < image.rows(); i++) {
            for (int j = 0; j < image.cols(); j++) {
                if (ImageHelperUtil.get(image, i, j) < threshold)
                    image.put(i, j, HOLE);
                else
                    image.put(i, j, 0);
            }
        }
        return image;
    }

    public static Mat maskImage(Mat image , Mat mask) throws Exception {

        if(image == null)
            throw new Exception("image cannot be null.");

        if(mask == null)
            throw new Exception("mask cannot be null.");

        for (int i = 0; i < mask.rows(); i++) {
            for (int j = 0; j < mask.cols(); j++) {
                if (isHole(mask, i, j))
                    image.put(i, j, HOLE);
            }
        }
        return image;
    }
}
