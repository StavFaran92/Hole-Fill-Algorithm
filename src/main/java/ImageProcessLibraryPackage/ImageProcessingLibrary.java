package ImageProcessLibraryPackage;

import ImageProcessLibraryPackage.Algorithms.*;
import ImageProcessLibraryPackage.Algorithms.Interfaces.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Mat;

/*
* This class is the library API gateway,
* It is responsible of granting access to the libraries algorithms,
* it uses IOC to prevent hard dependency and allow flexibility in algorithm execution.
* */
public class ImageProcessingLibrary {

  public enum ConnectivityOption
  {
    FOUR_WAY_CONNECTED,
    EIGHT_WAY_CONNECTED
  }

  // Fields
  private static IFillHoleAlgorithm fillHoleAlgorithm;
  private static IFindBoundAlgorithm findBoundAlgorithm;
  private static IFindHoleAlgorithm findHoleAlgorithm;

  // Default Constructor
  public ImageProcessingLibrary(){
    fillHoleAlgorithm = new FillHoleAlgorithm_impl();
    findBoundAlgorithm = new FindBoundaryAlgorithm_impl();
    findHoleAlgorithm = new FindHoleAlgorithm_impl();

    Init();
  }

  // Constructor
  public ImageProcessingLibrary(IFillHoleAlgorithm fillHoleAlgorithm, IFindBoundAlgorithm findBoundAlgorithm, IFindHoleAlgorithm findHoleAlgorithm){
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
  public Mat FillHoleAlgorithm(Mat source, double epsilon, double exponent, ConnectivityOption connectivityOption){
    Mat dest = source.clone();
    fillHoleAlgorithm.invoke(source, dest, epsilon, exponent, connectivityOption);
    return dest;
  }





}
