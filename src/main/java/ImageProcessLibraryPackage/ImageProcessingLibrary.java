package ImageProcessLibraryPackage;

import ImageProcessLibraryPackage.Algorithms.*;
import ImageProcessLibraryPackage.Algorithms.Interfaces.*;

import ImageProcessLibraryPackage.Exceptions.CommandLineException;
import ImageProcessLibraryPackage.Exceptions.ImageProcessException;
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

  public static final int
          FHA_Default = 0,
          FHA_Random = 1,
          FHA_Median = 2,
          FHA_KDTree = 3;

  // Fields
  private static IFillHoleAlgorithm fillHoleAlgorithm;
  private static IFindBoundAlgorithm findBoundAlgorithm;
  private static IFindHoleAlgorithm findHoleAlgorithm;

  // Default Constructor
  private ImageProcessingLibrary(){
    fillHoleAlgorithm = new FillHoleAlgorithm_Median_impl();
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

  private static void Init() {
    fillHoleAlgorithm.setFindBoundaryAlgorithm(findBoundAlgorithm);
    fillHoleAlgorithm.setFindHolesAlgorithm(findHoleAlgorithm);
  }

  public static Mat FillHoleAlgorithm(Mat source, double epsilon, double exponent, int connectivityOption, int FHA_type) throws Exception {
    switch(FHA_type)
    {
      case FHA_Default:
        fillHoleAlgorithm = new FillHoleAlgorithm_Default_impl();
        break;
      case FHA_Random:
        fillHoleAlgorithm = new FillHoleAlgorithm_Random_impl();
        break;
      case FHA_Median:
        fillHoleAlgorithm = new FillHoleAlgorithm_Median_impl();
        break;
      case FHA_KDTree:
        fillHoleAlgorithm = new FillHoleAlgorithm_KDTree_impl();
        break;
    }

    Init();

    return FillHoleAlgorithm(source, epsilon, exponent, connectivityOption);
  }

  // Methods
  private static Mat FillHoleAlgorithm(Mat source, double epsilon, double exponent, int connectivityOption) throws Exception {

    if(source == null)
      throw new NullPointerException("Source cannot be null");

    if(source.channels() != 1)
      throw new ImageProcessException("Not supported source image type");

    if(epsilon <= 0)
      throw new CommandLineException("Epsilon needs to be larger than 0");

    if(connectivityOption < 0)
      throw new CommandLineException("connectivity option cannot be negative: " + connectivityOption);

    Mat dest = source.clone();
    try {
      dest = fillHoleAlgorithm.invoke(source, dest, epsilon, exponent, connectivityOption);
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
    return dest;
  }





}
