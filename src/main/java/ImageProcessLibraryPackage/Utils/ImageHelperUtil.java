package ImageProcessLibraryPackage.Utils;

import org.opencv.core.Mat;

public class ImageHelperUtil {

    public static double get(Mat image, int x, int y)
    {
        if(image == null)
            throw new NullPointerException("image cannot be null.");

        if (x < 0 || x > image.rows() || y < 0 || y > image.cols())
            throw new IndexOutOfBoundsException("indeces outside of image, row: " +x + " col: " + y);

        return image.get(x,y)[0];
    }
}
