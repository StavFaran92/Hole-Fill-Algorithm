import com.harium.storage.kdtree.KDTree;
import org.junit.Test;

import java.util.List;

public class Test_NNLib {




    @Test
    public void Test(){
        double[] A = {2, 5};
        double[] B = {1, 1};
        double[] C = {3, 9};

        KDTree<double[]> tree = new KDTree<double[]>(2);
        tree.insert(A, A);
        tree.insert(B, B);
        tree.insert(C, C);


        List<double[]> nearest = tree.nearest(new double[]{1, 0}, 2);
        for (double[] neighbor : nearest) {
            System.out.println(neighbor[0] + ", " + neighbor[1]);


        }




    }
}
