package ImageProcessLibraryPackage.Utils;

import org.opencv.core.Mat;

public class ImageHelperUtil {

    public static double get(Mat image, int x, int y)
    {
        return image.get(x,y)[0];
    }
}
