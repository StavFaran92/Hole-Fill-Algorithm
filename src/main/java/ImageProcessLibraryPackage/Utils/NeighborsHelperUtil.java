package ImageProcessLibraryPackage.Utils;

import ImageProcessLibraryPackage.ImageProcessingLibrary;
import org.opencv.core.Mat;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static ImageProcessLibraryPackage.Utils.HoleHelperUtil.isHole;

public class NeighborsHelperUtil {

    public static java.util.List<Point> GetNeighbors(Point p, int connectivityOption) throws Exception {

        if (p == null)
            throw new NullPointerException("Point specified is null.");

        if (connectivityOption < 0)
            throw new Exception("connectivity option cannot be negative: " + connectivityOption);

        java.util.List<Point> neighbors = new ArrayList<>();
        neighbors.add(new Point(p.x - 1, p.y));
        neighbors.add(new Point(p.x, p.y - 1));
        neighbors.add(new Point(p.x, p.y + 1));
        neighbors.add(new Point(p.x + 1, p.y));

        if (connectivityOption == ImageProcessingLibrary.C8W) {

            neighbors.add(new Point(p.x - 1, p.y - 1));
            neighbors.add(new Point(p.x + 1, p.y - 1));
            neighbors.add(new Point(p.x + 1, p.y + 1));
            neighbors.add(new Point(p.x - 1, p.y + 1));
        }
        return neighbors;
    }

    public static boolean checkNeighborsForHoles(Mat image, int i, int j, int connectivityOption) throws Exception {

        if (image == null)
            throw new NullPointerException("image cannot be null.");

        if (i < 0 || i > image.rows() || j < 0 || j > image.cols())
            throw new IndexOutOfBoundsException("indeces outside of image, row: " + i + " col: " + j);

        if (connectivityOption < 0)
            throw new Exception("connectivity option cannot be negative: " + connectivityOption);

        List<Point> neighbors = GetNeighbors(new Point(i, j), connectivityOption);
        for (Point neighbor : neighbors) {
            if (neighbor.x < 0 || neighbor.y < 0 || neighbor.x >= image.cols() || neighbor.y >= image.rows())
                continue;
            if (isHole(image, neighbor.x, neighbor.y))
                return true;
        }
        return false;
    }

    public static boolean checkNeighborsForBoundaries(Mat image, int i, int j, int connectivityOption, Set<Point> boundary) throws Exception {

        if (image == null)
            throw new NullPointerException("image cannot be null.");

        if (i < 0 || i > image.rows() || j < 0 || j > image.cols())
            throw new IndexOutOfBoundsException("indeces outside of image, row: " + i + " col: " + j);

        if (connectivityOption < 0)
            throw new Exception("connectivity option cannot be negative: " + connectivityOption);

        List<Point> neighbors = GetNeighbors(new Point(i, j), connectivityOption);
        for (Point neighbor : neighbors) {
            if (neighbor.x < 0 || neighbor.y < 0 || neighbor.x >= image.cols() || neighbor.y >= image.rows())
                continue;
            if (boundary.contains(new Point(neighbor.x, neighbor.y)))
                return true;
        }
        return false;
    }
}
