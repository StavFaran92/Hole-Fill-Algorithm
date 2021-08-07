package ImageProcessLibraryPackage;

import ImageProcessLibraryPackage.Exceptions.CommandLineException;
import ImageProcessLibraryPackage.Utils.HoleHelperUtil;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

import static org.opencv.core.CvType.CV_32S;

@Command(name = "commandLine", mixinStandardHelpOptions = true, version = "commandLine 1.0",
    description = "ImageProcessLibraryPackage.CommandLine used to execute the Image Process Lib.")
public
class CommandLine implements Callable<Integer> {

  @Parameters(index = "0", description = "First Input RGB Image path")
  private String source;

  @Parameters(index = "1", description = "Second Input RGB Image path")
  private String mask;

  @Parameters(index = "2", description = "exponent")
  private double exponent;

  @Parameters(index = "3", description = "epsilon")
  private double epsilon;

  @Option(names = { "-th", "--threshold" }, description = "The mask's threshold option\n, this not required options is used to control the treshold of the mask e.g. if I(v) < threshold; I(v) = Hole_Color", required = false)
  private int threshold = 100;

  @Option(names = { "-co", "--connectivityOption" }, description = "connectivity options: \n{ 0: 4 way connection,\n 1: 8 way connection }", required = false)
  private int connectivityOption = ImageProcessingLibrary.C4W;

  @Option(names = { "-a", "--algorithm" }, description = "specify the algorithm you wish to FindOuterBoundary, options:{ FillHoleAlgorithm,\n FillHoleAlgorithm,\n FillHoleAlgorithm,\n FillHoleAlgorithm }", required = true)
  private String algorithm;

  @Override
  public Integer call() throws Exception {
    try {

      Mat source = Imgcodecs.imread(System.getProperty("user.dir") + this.source, Imgcodecs.IMREAD_GRAYSCALE);
      Mat mask = Imgcodecs.imread(System.getProperty("user.dir") + this.mask, Imgcodecs.IMREAD_GRAYSCALE);

      source.convertTo(source, CV_32S);
      mask.convertTo(mask, CV_32S);

      mask = HoleHelperUtil.generateHoleInImageByThreshold(mask, threshold);

      source = HoleHelperUtil.maskImage(source, mask);

      if ("FillHoleAlgorithm".equals(algorithm)) {
        Mat result = ImageProcessingLibrary.FillHoleAlgorithm(source, epsilon, exponent, connectivityOption, ImageProcessingLibrary.FHA_Default);

        Imgcodecs.imwrite(System.getProperty("user.dir") + "/result.png", result);
      }

      return 0;
    }
     catch(Exception e)
    {
      e.printStackTrace();
      throw e;
    }
  }

  // this example implements Callable, so parsing, error handling and handling user
  // requests for usage help or version help can be done with one line of code.
  public static void main(String... args) {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    int exitCode = new picocli.CommandLine(new CommandLine()).execute(args);
    System.exit(exitCode);
  }
}