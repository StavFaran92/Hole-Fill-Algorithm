package ImageProcessLibraryPackage.Algorithms;


import ImageProcessLibraryPackage.Functions.Interfaces.IWeightFunction;
import com.harium.storage.kdtree.KDTree;
import org.opencv.core.Mat;


import java.awt.*;
import java.util.List;

public class FillHoleAlgorithm_KDTree_impl extends FillHoleAlgorithmBase_impl {

  private KDTree<Point> tree = new KDTree<>(2);

  @Override
  protected void FillHoles(Mat source, Mat dest, List<Point> holes, List<Point> boundaries, IWeightFunction weightFunction) throws Exception {
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

  protected double evaluateIntensity(Mat image, Point p, List<Point> boundaries, IWeightFunction weightFunction) throws Exception {

    if(image == null)
      throw new Exception("image cannot be null.");

    if(p == null)
      throw new Exception("Point specified is null.");

    double nominator = 0;
    double denominator = 0;
    for (Point b: tree.nearest(new double[]{p.x, p.y}, 4)) {
      double weight = weightFunction.invoke(p, b);
      nominator += weight * image.get(b.x, b.y)[0];
      denominator += weight;
    }

    if(denominator == 0)
      throw new Exception("Denominator cannot be zero.");

    return nominator / denominator;
  }
}
