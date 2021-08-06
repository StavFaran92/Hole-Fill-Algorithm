package ImageProcessLibraryPackage;

import ImageProcessLibraryPackage.Algorithms.*;
import ImageProcessLibraryPackage.Algorithms.Interfaces.*;

import org.opencv.core.Mat;

/*
* This class is the library API gateway,
* It is responsible of granting access to the libraries algorithms,
* it uses IOC to prevent hard dependency and allow flexibility in algorithm execution.
* */
public class ImageProcessingLibrary {

  private static ImageProcessingLibrary instance = new ImageProcessingLibrary();

  public static final int
    C4W = 0,
    C8W = 1;

  // Fields
  private static IFillHoleAlgorithm fillHoleAlgorithm;
  private static IFindBoundAlgorithm findBoundAlgorithm;
  private static IFindHoleAlgorithm findHoleAlgorithm;

  // Default Constructor
  private ImageProcessingLibrary(){
    fillHoleAlgorithm = new FillHoleAlgorithm_impl();
    findBoundAlgorithm = new FindBoundaryAlgorithm_impl();
    findHoleAlgorithm = new FindHoleAlgorithm_impl();

    Init();
  }

  // Constructor
  private ImageProcessingLibrary(IFillHoleAlgorithm fillHoleAlgorithm, IFindBoundAlgorithm findBoundAlgorithm, IFindHoleAlgorithm findHoleAlgorithm){
    this.fillHoleAlgorithm = fillHoleAlgorithm;
    this.findBoundAlgorithm = findBoundAlgorithm;
    this.findHoleAlgorithm = findHoleAlgorithm;

    Init();
  }

  private void Init() {
    fillHoleAlgorithm.setFindBoundaryAlgorithm(findBoundAlgorithm);
    fillHoleAlgorithm.setFindHolesAlgorithm(findHoleAlgorithm);
  }



  // Methods
  public static Mat FillHoleAlgorithm(Mat source, double epsilon, double exponent, int connectivityOption) throws Exception {

    if(source == null)
      throw new Exception("Source cannot be null");

    if(epsilon <= 0)
      throw new Exception("Epsilon needs to be larger than 0");

    if(connectivityOption < 0)
      throw new Exception("connectivity option cannot be negative: " + connectivityOption);

    Mat dest = source.clone();
    try {
      fillHoleAlgorithm.invoke(source, dest, epsilon, exponent, connectivityOption);
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
    return dest;
  }





}
