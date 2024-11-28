import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LinearAlgebraCalculations extends LinearAlgebra {
  protected static ArrayList<Holder> matrixHolders = new ArrayList<>();

  protected static void calculationOptions() {
    if (matricies.size() == 0) {
      throw new IllegalStateException();
    }
    String[] options = {
        "Addition", "Scalar Multiplication", "Matrix Multiplication", "REF", "RREF", "Determinant", "Eigenvalues & Eigenvectors"
    };

    System.out.println("What would you like to do?");

    int selection = -1;
    Scanner scanner = new Scanner(System.in);
    for (int i = 0; i < options.length; i++) {
      System.out.println((i + 1) + ") " + options[i]);
    }

    while (selection == -1) {
      try {
        int temp = scanner.nextInt() - 1;
        if (temp < 0 || temp >= options.length) {
          throw new IllegalArgumentException();
        }
        selection = temp;
      } catch (InputMismatchException | IllegalArgumentException e) {
        System.out.println("Proper selection please...");
        scanner.next(); // Clear invalid input
      }
    }

    switch (selection) {
      case 0:
        matrixAddition();
        break;
      case 1:
        matrixScalarMultiplication();
        break;
      case 2:
        matrixMatrixMultiplication();
        break;
      case 3:
        matrixRef();
        break;
      case 4:
        matrixRref();
        break;
      case 5:
        matrixDeterminant();
        break;
      case 6:
        matrixEigenvaluesEigenvalues();
        break;
    }
  }

  protected static void matrixAddition() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Matrix Addition:\nSelect the first matrix.");
    printMatrices(matricies);

    int index1 = selectMatrix(scanner, "Enter the index of the first matrix: ");
    int[][] matrix1 = matricies.get(index1);

    System.out.println("Select the second matrix.");
    ArrayList<int[][]> compatible = getCompatibleMatrices("Addition", matrix1);
    printMatrices(compatible);

    int index2 = selectMatrix(scanner, "Enter the index of the second matrix: ");
    int[][] matrix2 = compatible.get(index2);

    int[][] result = addMatrices(matrix1, matrix2);
    System.out.println("The resulting matrix is:");
    printAMatrix(result);
    saveResult(scanner, result);
  }

  protected static void matrixScalarMultiplication() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Scalar Multiplication:\nSelect a matrix.");
    printMatrices(matricies);

    int index = selectMatrix(scanner, "Enter the index of the matrix: ");
    int[][] matrix = matricies.get(index);

    System.out.println("Enter the scalar value:");
    int scalar = scanner.nextInt();

    int[][] result = scalarMultiplyMatrix(matrix, scalar);
    System.out.println("The resulting matrix is:");
    printAMatrix(result);
    saveResult(scanner, result);
  }

  /*
  protected static void matrixMatrixMultiplication() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Matrix Multiplication:\nSelect the first matrix.");
    printMatrices(matricies);

    int index1 = selectMatrix(scanner, "Enter the index of the first matrix: ");
    int[][] matrix1 = matricies.get(index1);

    System.out.println("Select the second matrix.");
    ArrayList<int[][]> compatible = getCompatibleMatrices("Matrix Multiplication", matrix1);
    printMatrices(compatible);

    int index2 = selectMatrix(scanner, "Enter the index of the second matrix: ");
    int[][] matrix2 = compatible.get(index2);

    int[][] result = multiplyMatrices(matrix1, matrix2);
    System.out.println("The resulting matrix is:");
    printAMatrix(result);
    saveResult(scanner, result);
  }

   */

  protected static void matrixRef() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("REF (Row Echelon Form):\nSelect a matrix.");
    printMatrices(matricies);

    int index = selectMatrix(scanner, "Enter the index of the matrix: ");
    int[][] matrix = matricies.get(index);

    int[][] result = rowEchelonForm(matrix);
    System.out.println("The resulting REF is:");
    printAMatrix(result);
    saveResult(scanner, result);
  }

  protected static void matrixRref() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("RREF (Reduced Row Echelon Form):\nSelect a matrix.");
    printMatrices(matricies);

    int index = selectMatrix(scanner, "Enter the index of the matrix: ");
    int[][] matrix = matricies.get(index);

    int[][] result = reducedRowEchelonForm(matrix);
    System.out.println("The resulting RREF is:");
    printAMatrix(result);
    saveResult(scanner, result);
  }

  protected static void matrixDeterminant() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Determinant Calculation:\nSelect a square matrix.");
    printMatrices(matricies);

    int index = selectMatrix(scanner, "Enter the index of the matrix: ");
    int[][] matrix = matricies.get(index);

    if (matrix.length != matrix[0].length) {
      System.out.println("The matrix must be square to calculate the determinant.");
      return;
    }

    int determinant = -1; // determinant(matrix); NOT IMPLEMENTED YET
    System.out.println("The determinant of the matrix is: " + determinant);
  }

  protected static void matrixEigenvaluesEigenvalues() {
    System.out.println("Eigenvalue and Eigenvector computation is not implemented yet.");
  }

  // Utility Methods
  private static int selectMatrix(Scanner scanner, String prompt) {
    int selection = -1;
    while (selection == -1) {
      System.out.print(prompt);
      try {
        int temp = scanner.nextInt() - 1;
        if (temp < 0 || temp >= matricies.size()) {
          throw new IllegalArgumentException("Invalid input.");
        }
        selection = temp;
      } catch (InputMismatchException | IllegalArgumentException e) {
        System.out.println("Invalid selection. Please try again.");
        scanner.next(); // Clear invalid input
      }
    }
    return selection;
  }

  private static void saveResult(Scanner scanner, int[][] result) {
    System.out.println("Would you like to save the resulting matrix? (Y/N)");
    String response = scanner.next().toUpperCase();
    if (response.equals("Y")) {
      matricies.add(result);
      System.out.println("Matrix saved.");
    }
  }

  // Add implementation of REF and RREF below
  private static int[][] rowEchelonForm(int[][] matrix) {
    // Implementation of REF
    return matrix; // Placeholder
  }

  private static int[][] reducedRowEchelonForm(int[][] matrix) {
    // Implementation of RREF
    return matrix; // Placeholder
  }
}
