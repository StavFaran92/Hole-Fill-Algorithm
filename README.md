# lightricks_exercise

A **Java** image processing library,

## Usage
in order to run the jar file run the following line
```java
java -cp "picocli-4.6.1.jar;lightricks_exercise.jar" ImageProcessLibraryPackage.CommandLine <image> <mask> <exponent> <epsilon> -co <connection type> -a <algorithm> -fhat <algorithm implementation>
```

for example, to run the basic question 1 algorithm use the following line:
```java
java -cp "picocli-4.6.1.jar;lightricks_exercise.jar" ImageProcessLibraryPackage.CommandLine ./lenna.png ./Mask.png 5 0.0001 -co 1 -a FillHoleAlgorithm -fhat 0
```

## Design
I used java and openCV, using openCV only for loading and converting the image format,
the project uses Mat type to store and manipulate the data.
I used Picocli repo for command line parsing and JUnit 4 for testing.

## Theoretical Questions
1) The basic algorithm goes through all the pixels in the given hole (n) and for each one calculates the new intensity while iterating
all the pixels in the boundary (m) therefore the overall run time is o(n*m), since the boundary is determined by the hole
for large n we will get m < n therefore we can conclude that the algorithm runtime is o(n*2)

2) I used a filter algorithm to generate the result in o(n), we iterate over the pixels in the hole and for each pixel we
call the basic FillHoleAlgorithm using it's neighbors as the boundary excluding hole marked pixels,
since every action on each pixel is o(1) we got a result of o(n) runtime.
In order to run the algorithm use the following command:
```java
java -cp "picocli-4.6.1.jar;lightricks_exercise.jar" ImageProcessLibraryPackage.CommandLine ./lenna.png ./Mask.png 5 0.0001 -co 1 -a FillHoleAlgorithm -fhat 2
```


3) (Bonus question) I was not able to find the **exact** solution, I attempted to solve it using KNN algorithm,
the idea was to take the nearest neighbors to each pixel which are the ones that have the highest weight in the original algorithm,
since KNN setup is o(nlogn) and the query is o(logn) iterating over all the holes yields a runtime of o(nlogn),
however the results were not quite what I had in mind..
In order to run the algorithm use the following command:
```java
java -cp "picocli-4.6.1.jar;lightricks_exercise.jar" ImageProcessLibraryPackage.CommandLine ./lenna.png ./Mask.png 5 0.0001 -co 1 -a FillHoleAlgorithm -fhat 3
```