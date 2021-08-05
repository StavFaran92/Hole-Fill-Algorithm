package ImageProcessLibraryPackage;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "commandLine", mixinStandardHelpOptions = true, version = "commandLine 1.0",
    description = "ImageProcessLibraryPackage.CommandLine used to execute the Image Process Lib.")
public
class CommandLine implements Callable<Integer> {

  @Parameters(index = "0", description = "First Input RGB Image path")
  private String image_a;

  @Parameters(index = "1", description = "Second Input RGB Image path")
  private String image_b;

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
      Mat source = Imgcodecs.imread(System.getProperty("user.dir") + "/src/test/java/" + image_a);

    }catch(picocli.CommandLine.ExecutionException e)
    {
      e.printStackTrace();
    }
    return 0;
//    byte[] digest = MessageDigest.getInstance(algorithm).digest(fileContents);
//    System.out.printf("%0" + (digest.length*2) + "x%n", new BigInteger(1, digest));
//    return 0;
  }

  // this example implements Callable, so parsing, error handling and handling user
  // requests for usage help or version help can be done with one line of code.
  public static void main(String... args) {

//    // To load  OpenCV core library
//    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//    String input = "C:/opencv/GeeksforGeeks.jpg";
//
//    // To Read the image
//    Mat source = Imgcodecs.imread(input);
//
//    // Creating the empty destination matrix
//    Mat destination = new Mat();
//
//    // Converting the image to gray scale and
//    // saving it in the dst matrix
//    Imgproc.cvtColor(source, destination, Imgproc.COLOR_RGB2GRAY);
//
//    // Writing the image
//    Imgcodecs.imwrite("C:/opencv/GeeksforGeeks.jpg", destination);
//    System.out.println("The image is successfully to Grayscale");

    int exitCode = new picocli.CommandLine(new CommandLine()).execute(args);
    System.exit(exitCode);
  }
}