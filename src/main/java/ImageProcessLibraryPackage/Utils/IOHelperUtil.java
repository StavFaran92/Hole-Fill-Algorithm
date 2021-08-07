package ImageProcessLibraryPackage.Utils;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import static org.opencv.core.CvType.CV_32S;

public class IOHelperUtil {
    public static Mat LoadImageAs_Grasycale_32S(String sourcePath){
        Mat source = Imgcodecs.imread(System.getProperty("user.dir") + sourcePath, Imgcodecs.IMREAD_GRAYSCALE);
        source.convertTo(source, CV_32S);
        return source;
    }

    public static void SaveImage(Mat result){
        Imgcodecs.imwrite(System.getProperty("user.dir") + "/result.png", result);
    }
}
