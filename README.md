# lightricks_exercise

in order to run the jar file run the following line
java -cp "picocli-4.6.1.jar;lightricks_exercise.jar" ImageProcessLibraryPackage.CommandLine <image> <mask> <exponent> <epsilon> -co 1 -a FillHoleAlgorithm

for example:
java -cp "picocli-4.6.1.jar;lightricks_exercise.jar" ImageProcessLibraryPackage.CommandLine ./lenna.png ./Mask.png 5 0.0001 -co 1 -a FillHoleAlgorithm