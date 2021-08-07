package ImageProcessLibraryPackage;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandLineTest {

    @Test
    public void call() {
            Assert.assertTrue(CommandLine.call("/resources/lenna.png /resources/Mask.png 5 0.0001 -co 1 -a FillHoleAlgorithm -fhat 3".split(" ")) == 0);
            Assert.assertTrue(CommandLine.call("/resources/lenna.png /resources/Mask.png 5 0.0001 -co 1 -a FillHoleAlgorithm".split(" ")) == 0);
            Assert.assertTrue(CommandLine.call("/resources/lenna.png /resources/Mask.png 5 0.0001 -a FillHoleAlgorithm".split(" ")) == 0);

            Assert.assertFalse(CommandLine.call("/resources/lenna.png /resources/Mask.png 5 0.0001 -co 1".split(" ")) == 0);
            Assert.assertFalse(CommandLine.call("/resources/lenna.png /resources/Mask.png 5 0.0001 -co -1 -a FillHoleAlgorithm".split(" ")) == 0);
            Assert.assertFalse(CommandLine.call("/resources/lenna.png /resources/Mask.png 5 0 -co 1 -a FillHoleAlgorithm".split(" ")) == 0);
            Assert.assertFalse(CommandLine.call("/resources/lenna.png /resources/Mas 5 0 -co 1 -a FillHoleAlgorithm".split(" ")) == 0);
            Assert.assertFalse(CommandLine.call("/resources/le /resources/Mask.png 5 0 -co 1 -a FillHoleAlgorithm".split(" ")) == 0);
    }
}