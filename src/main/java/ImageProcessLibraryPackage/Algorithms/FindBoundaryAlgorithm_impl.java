package ImageProcessLibraryPackage.Algorithms;

import ImageProcessLibraryPackage.Algorithms.Interfaces.*;
import java.awt.Point;
import java.util.*;

import ImageProcessLibraryPackage.Utils.NeighborsHelperUtil;
import org.opencv.core.Mat;

import static ImageProcessLibraryPackage.Utils.HoleHelperUtil.isHole;

public class FindBoundaryAlgorithm_impl implements IFindBoundAlgorithm {

  @Override
  public Collection<Point> FindOuterBoundary(Mat image, int connectivityOption, int returnType) throws Exception {
    if(returnType == LIST)
      return FindOuterBoundaryAsList(image, connectivityOption);
    else if(returnType == SET)
      return FindOuterBoundaryAsSet(image, connectivityOption);
    return null;
  }

  private List<Point> FindOuterBoundaryAsList(Mat image, int connectivityOption) throws Exception {


    if(image == null)
      throw new NullPointerException("image cannot be null.");

    List<Point> boundaries = new ArrayList<>();
    for (int i = 0; i < image.rows(); i++) {
      for (int j = 0; j < image.cols(); j++) {
        if(!isHole(image, i, j)  && NeighborsHelperUtil.checkNeighborsForHoles(image, i, j, connectivityOption))
          boundaries.add(new Point(i,j));
      }
    }
    return boundaries;
  }

  private Set<Point> FindOuterBoundaryAsSet(Mat image, int connectivityOption) throws Exception {

    List<Point> points = FindOuterBoundaryAsList(image, connectivityOption);

    return new HashSet<>(points);
  }



  public Set<Point> FindInnerBoundary(Mat image, int connectivityOption, Set<Point> boundary) throws Exception {
    if(image == null)
      throw new NullPointerException("image cannot be null.");

    Set<Point> boundaries = new HashSet<>();
    for (int i = 0; i < image.rows(); i++) {
      for (int j = 0; j < image.cols(); j++) {
        if(isHole(image, i, j)  && NeighborsHelperUtil.checkNeighborsForBoundaries(image, i, j,connectivityOption, boundary))
          boundaries.add(new Point(i,j));
      }
    }
    return boundaries;
  }
}
