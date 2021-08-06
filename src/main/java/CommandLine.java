import ImageProcessLibraryPackage.ImageProcessingLibrary;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
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
      Mat source = Imgcodecs.imread(System.getProperty("user.dir") + "/src/resources/" + this.source);
      Mat mask = Imgcodecs.imread(System.getProperty("user.dir") + "/src/resources/" + this.mask);


      Mat source_gray = new Mat();
      Mat mask_gray = new Mat();
      Imgproc.cvtColor(source, source_gray, Imgproc.COLOR_RGB2GRAY);
      Imgproc.cvtColor(mask, mask_gray, Imgproc.COLOR_RGB2GRAY);

      source_gray.convertTo(source_gray, CV_32S);
      mask_gray.convertTo(mask_gray, CV_32S);

      int threshold = 100;
      for (int i = 0; i < mask_gray.rows(); i++) {
        for (int j = 0; j < mask_gray.cols(); j++) {
          if (mask_gray.get(i, j)[0] < threshold)
            mask_gray.put(i, j, -1);
          else
            mask_gray.put(i, j, 0);
        }
      }

      for (int i = 0; i < mask_gray.rows(); i++) {
        for (int j = 0; j < mask_gray.cols(); j++) {
          if (mask_gray.get(i, j)[0] == -1)
            source_gray.put(i, j, -1);
        }
      }

      ImageProcessingLibrary imageProcessingLibrary = new ImageProcessingLibrary();
      Mat result = imageProcessingLibrary.FillHoleAlgorithm(source_gray, epsilon, exponent, ImageProcessingLibrary.ConnectivityOption.EIGHT_WAY_CONNECTED);

      Imgcodecs.imwrite(System.getProperty("user.dir") + "/src/resources/" + this.source + "_gray.png", source_gray);
      Imgcodecs.imwrite(System.getProperty("user.dir") + "/src/resources/" + this.mask + "_gray.png", mask_gray);
      Imgcodecs.imwrite(System.getProperty("user.dir") + "/src/resources/result.png", result);


      return 0;

    }catch(picocli.CommandLine.ExecutionException e)
    {
      e.printStackTrace();
    }
    return -1;

//    byte[] digest = MessageDigest.getInstance(algorithm).digest(fileContents);
//    System.out.printf("%0" + (digest.length*2) + "x%n", new BigInteger(1, digest));
//    return 0;
  }

  // this example implements Callable, so parsing, error handling and handling user
  // requests for usage help or version help can be done with one line of code.
  public static void main(String... args) {
    int exitCode = new picocli.CommandLine(new CommandLine()).execute(args);
    System.exit(exitCode);
  }
}