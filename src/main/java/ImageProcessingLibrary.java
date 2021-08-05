import Algorithms.FillHoleAlgorithm_impl;
import Algorithms.FindBoundaryAlgorithm_impl;
import Algorithms.FindHoleAlgorithm_impl;
import Algorithms.Interfaces.IFillHoleAlgorithm;
import Algorithms.Interfaces.IFindBoundAlgorithm;
import Algorithms.Interfaces.IFindHoleAlgorithm;

public class ImageProcessingLibrary {

  // Fields
  private static IFillHoleAlgorithm fillHoleAlgorithm;
  private static IFindBoundAlgorithm findBoundAlgorithm;
  private static IFindHoleAlgorithm findHoleAlgorithm;

  // Default Constructor
  public ImageProcessingLibrary(){
    fillHoleAlgorithm = new FillHoleAlgorithm_impl();
    findBoundAlgorithm = new FindBoundaryAlgorithm_impl();
    findHoleAlgorithm = new FindHoleAlgorithm_impl();
  }

  // Constructor
  public ImageProcessingLibrary(IFillHoleAlgorithm fillHoleAlgorithm, IFindBoundAlgorithm findBoundAlgorithm, IFindHoleAlgorithm findHoleAlgorithm){
    this.fillHoleAlgorithm = fillHoleAlgorithm;
    this.findBoundAlgorithm = findBoundAlgorithm;
    this.findHoleAlgorithm = findHoleAlgorithm;
  }

  // Methods
  public static void FillHoleAlgorithm(){
    //fillHoleAlgorithm.invoke();
  }

}
