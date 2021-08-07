package ImageProcessLibraryPackage.Algorithms;


import ImageProcessLibraryPackage.Functions.Interfaces.IWeightFunction;
import ImageProcessLibraryPackage.Utils.ImageHelperUtil;
import com.harium.storage.kdtree.KDTree;
import org.opencv.core.Mat;


import java.awt.*;
import java.util.Collection;

/**
 * This algorithm uses the KDTree struct to perform the fill hole algorithm,
 * it performs in o(nlogn) complexity,
 * it uses K nearest neighbors to determine the color intensity to fill.
 */
public class FillHoleAlgorithm_KDTree_impl extends FillHoleAlgorithm_Default_impl {

  private KDTree<Point> tree = new KDTree<>(2);

  @Override
  public void FillHoles(Mat source, Mat dest, Collection<Point> holes, Collection<Point> boundaries, IWeightFunction weightFunction) throws Exception {
    if(holes.isEmpty())
    {
      System.out.println("Image does not contain any holes.");
      return;
    }

    tree.clear();

    for (Point p: boundaries) {
      tree.insert(new double[]{p.x,p.y}, p);
    }

    for (Point p: holes) {
      double intensity = evaluateIntensity(source, p, null, weightFunction);
      dest.put(p.x, p.y, intensity);
    }
  }

  @Override
  public double evaluateIntensity(Mat image, Point p, Collection<Point> boundaries, IWeightFunction weightFunction) throws Exception {

    if(image == null)
      throw new NullPointerException("image cannot be null.");

    if(p == null)
      throw new NullPointerException("Point specified is null.");

    double nominator = 0;
    double denominator = 0;
    for (Point b: tree.nearest(new double[]{p.x, p.y}, 4)) {
      double weight = weightFunction.invoke(p, b);
      nominator += weight *  ImageHelperUtil.get(image, b.x, b.y);
      denominator += weight;
    }

    if(denominator == 0)
      throw new Exception("Denominator cannot be zero.");

    return nominator / denominator;
  }
}
