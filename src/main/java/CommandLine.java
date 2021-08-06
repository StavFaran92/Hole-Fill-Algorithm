import ImageProcessLibraryPackage.ImageProcessingLibrary;
import ImageProcessLibraryPackage.Utils.HoleHelperUtil;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import org.opencv.imgproc.Imgproc;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

import static org.opencv.core.CvType.CV_32S;

@Command(name = "commandLine", mixinStandardHelpOptions = true, version = "commandLine 1.0",
    description = "CommandLine used to execute the Image Process Lib.")
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

//  @Option(index = "4", description = "connectivity option", required = true)
//  private ConnectivityOption connectivityOption = ConnectivityOption.FOUR_WAY_CONNECTED;

//  @Option(names = {"-a", "--algorithm"}, description = "MD5, SHA-1, SHA-256, ...")
//  private String algorithm = "MD5";

  @Override
  public Integer call() throws Exception {
    try {
      System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
      Mat source = Imgcodecs.imread(System.getProperty("user.dir") + this.source, Imgcodecs.IMREAD_GRAYSCALE);
      Mat mask = Imgcodecs.imread(System.getProperty("user.dir") + this.mask, Imgcodecs.IMREAD_GRAYSCALE);

      source.convertTo(source, CV_32S);
      mask.convertTo(mask, CV_32S);

      mask = HoleHelperUtil.generateHoleInImage(mask, 100);

      source = HoleHelperUtil.maskImage(source, mask);

      Mat result = ImageProcessingLibrary.FillHoleAlgorithm(source, epsilon, exponent, ImageProcessingLibrary.ConnectivityOption.EIGHT_WAY_CONNECTED);

      Imgcodecs.imwrite(System.getProperty("user.dir") + "/result.png", result);

      return 0;

    }catch(picocli.CommandLine.ExecutionException e)
    {
      e.printStackTrace();
    }
    return -1;
  }

  // this example implements Callable, so parsing, error handling and handling user
  // requests for usage help or version help can be done with one line of code.
  public static void main(String... args) {
    int exitCode = new picocli.CommandLine(new CommandLine()).execute(args);
    System.exit(exitCode);
  }
}