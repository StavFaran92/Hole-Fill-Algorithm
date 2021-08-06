package ImageProcessLibraryPackage.Utils;

import org.opencv.core.Mat;

public class HoleHelperUtil {
    public static int HOLE = -1;

    public static boolean isHole(Mat source, int row, int col)
    {
        return (source.get(row, col)[0] == HOLE);
    }
}
