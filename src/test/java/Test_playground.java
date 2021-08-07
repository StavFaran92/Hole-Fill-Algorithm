import com.harium.storage.kdtree.KDTree;
import org.junit.Test;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test_playground {




    @Test
    public void Test(){


        Point a = new Point(1,2);
        Point b = new Point(2,2);

        Set points = new HashSet();
        points.add(a);
        points.add(b);

        Point c = new Point(1,3);
        System.out.println(points.contains(c));




    }
}
