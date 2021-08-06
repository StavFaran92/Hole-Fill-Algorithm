package ImageProcessLibraryPackage.Utils;

import org.opencv.core.Mat;

public class HoleHelperUtil {
    public static int HOLE = -1;

    public static boolean isHole(Mat source, int row, int col)
    {
        return (source.get(row, col)[0] == HOLE);
    }

    public static Mat generateHoleInImage(Mat image, int threshold){
        for (int i = 0; i < image.rows(); i++) {
            for (int j = 0; j < image.cols(); j++) {
                if (image.get(i, j)[0] < threshold)
                    image.put(i, j, HOLE);
                else
                    image.put(i, j, 0);
            }
        }
        return image;
    }

    public static Mat maskImage(Mat image , Mat mask){
        for (int i = 0; i < mask.rows(); i++) {
            for (int j = 0; j < mask.cols(); j++) {
                if (isHole(mask, i, j))
                    image.put(i, j, HOLE);
            }
        }
        return image;
    }
}
