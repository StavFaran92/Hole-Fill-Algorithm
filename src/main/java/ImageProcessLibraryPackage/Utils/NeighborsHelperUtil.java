package ImageProcessLibraryPackage.Utils;

import ImageProcessLibraryPackage.ImageProcessingLibrary;
import org.opencv.core.Mat;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static ImageProcessLibraryPackage.Utils.HoleHelperUtil.isHole;

public class NeighborsHelperUtil {

    public static java.util.List<Point> GetNeighbors(Point p, ImageProcessingLibrary.ConnectivityOption connectivityOption)
    {
        java.util.List<Point> neighbors = new ArrayList<>();
        neighbors.add(new Point(p.x - 1, p.y));
        neighbors.add(new Point(p.x, p.y - 1));
        neighbors.add(new Point(p.x, p.y + 1));
        neighbors.add(new Point(p.x + 1, p.y));

        if(connectivityOption == ImageProcessingLibrary.ConnectivityOption.EIGHT_WAY_CONNECTED){

            neighbors.add(new Point(p.x - 1, p.y -1));
            neighbors.add(new Point(p.x + 1, p.y - 1));
            neighbors.add(new Point(p.x + 1, p.y + 1));
            neighbors.add(new Point(p.x - 1, p.y + 1));
        }
        return neighbors;
    }

    public static boolean checkNeighborsForHoles(Mat image, int i, int j, ImageProcessingLibrary.ConnectivityOption connectivityOption) {
        List<Point> neighbors = GetNeighbors(new Point(i, j), connectivityOption);
        for (Point neighbor: neighbors) {
            if(neighbor.x < 0 || neighbor.y < 0 || neighbor.x >= image.cols() || neighbor.y >= image.rows())
                continue;
            if(isHole(image, neighbor.x, neighbor.y))
                return true;
        }
        return false;
    }
}
