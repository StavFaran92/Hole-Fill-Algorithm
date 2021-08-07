package ImageProcessLibraryPackage;

import ImageProcessLibraryPackage.Utils.HoleHelperUtil;
import ImageProcessLibraryPackage.Utils.IOHelperUtil;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "commandLine", mixinStandardHelpOptions = true, version = "commandLine 1.0",
    description = "ImageProcessLibraryPackage.CommandLine used to execute the Image Process Lib.")
public
class CommandLine implements Callable<Integer> {

  @Parameters(index = "0", description = "First Input RGB Image path")
  private String image_path;

  @Parameters(index = "1", description = "Second Input RGB Image path")
  private String mask_path;

  @Parameters(index = "2", description = "exponent")
  private double exponent;

  @Parameters(index = "3", description = "epsilon")
  private double epsilon;

  @Option(names = { "-th", "--threshold" }, description = "The mask_path's threshold option\n, this not required options is used to control the treshold of the mask_path e.g. if I(v) < threshold; I(v) = Hole_Color", required = false)
  private int threshold = 100;

  @Option(names = { "-co", "--connectivityOption" }, description = "connectivity options: \n{ 0: 4 way connection,\n 1: 8 way connection }", required = false)
  private int connectivityOption = ImageProcessingLibrary.C4W;

  @Option(names = { "-a", "--algorithm" }, description = "specify the algorithm you wish to FindOuterBoundary, options:{ FillHoleAlgorithm }", required = true)
  private String algorithm;

    @Option(names = { "-fhat", "--fillHoleAlgorithmType" }, description = "specify the Fill Hole algorithm type, options:{ 0: default,\n 1: random,\n 2: median,\n 3: kdtree  }", required = false)
    private int type = ImageProcessingLibrary.FHA_Default;

  @Override
  public Integer call() throws Exception {
    try {
        Mat source = IOHelperUtil.LoadImageAs_Grasycale_32S(this.image_path);
        Mat mask = IOHelperUtil.LoadImageAs_Grasycale_32S(this.mask_path);

        mask = HoleHelperUtil.generateHoleInImageByThreshold(mask, threshold);
        source = HoleHelperUtil.maskImage(source, mask);

        if ("FillHoleAlgorithm".equals(algorithm)) {
            Mat result = ImageProcessingLibrary.FillHoleAlgorithm(source, epsilon, exponent, connectivityOption, type);

            IOHelperUtil.SaveImage(result);
        }

        return 0;
    }
    catch(Exception e)
    {
      e.printStackTrace();
      throw e;
    }
  }

  public static int call(String... args){
      System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
      return new picocli.CommandLine(new CommandLine()).execute(args);

  }

  public static void main(String... args) {
      System.exit(call(args));
  }
}