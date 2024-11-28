import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LinearAlgebra {
  private static int countScrewUps = 0;
  protected static ArrayList<int[][]> matricies =new ArrayList<int[][]>();

  private static int[][] generateMatrix(int[][] matrix, int x, int y, int absRange) {
    for (int i = 0; i < x; i++) {
      for (int j = 0; j < y; j++) {
        matrix[i][j] = (int) ((Math.random() * 2 * absRange) - absRange);
      }
    }
    return matrix;
  }

  private static int[][] inputMatrix(int[][] matrix, int x, int y) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter in the following entries...");
    for (int i = 0; i < x; i++) {
      for (int j = 0; j < y; j++) {
        System.out.println("(" + i + "," + j + ") = ?");
        while (true) {
          try {
            matrix[i][j] = scanner.nextInt();
            break;
          } catch (InputMismatchException e) {
            System.out.println("Please enter a proper value for (" + i + ", " + j + ")...");
            scanner.next(); // clear the invalid input
          }
        }
      }
    }
    return matrix;
  }

  private static void createRandomMatrix() {
    int range = (int)(Math.random() * 3000) + 1; //limit is 3000.
    int sizeX = (int)(Math.random() * 10) + 1;
    int sizeY = (int)(Math.random() * 10) + 1;

    int[][] matrix = new int[sizeX][sizeY];

    System.out.println("Matrix Generated:\nGenerated Range: (-" + range + ", " + range + ")");
    System.out.println("Size: ["+ sizeX + " x " + sizeY + "]\n");
    for (int i = 0; i < sizeX; i++) {
      for (int j = 0; j < sizeY; j++) {
        matrix[i][j] = (int) (Math.random() * 2 * range) - range;
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();

    matricies.add(matrix);
    matrixOptions();
  }

  protected static int[][] createBoundedMatrix(int x, int y, char selection) {
    int[][] boundedMatrix = new int[x][y];

    switch (selection) {
      case 'Y':
        int range = (int)(Math.random() * 3000) + 1;
        for (int i = 0; i < x; i++) {
          for (int j = 0; j < y; j++) {
            boundedMatrix[i][j] = (int) (Math.random() * 2 * range) - range;
          }
        }
        break;
      case 'N':
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter in the following entries...");
        for (int i = 0; i < x; i++) {
          for (int j = 0; j < y; j++) {
            while (true) {
              try {
                String temp = scanner.nextLine().trim();
                boundedMatrix[i][j] = Integer.parseInt(temp);
                break;
              } catch (NumberFormatException e) {
                System.out.println("Please enter a proper value for (" + i + ", " + j + ")...");
              }
            }
          }
          break;
        }
    }
    return boundedMatrix;
  }

  private static void createGivenMatrix() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Let's create a matrix! What are the dimensions? (X, Y)");

    int sizeX = -1;
    while (true) {
      try {
        int temp = scanner.nextInt();
        if (temp < 1) throw new IllegalArgumentException();
        sizeX = temp;
        break;
      } catch (InputMismatchException | IllegalArgumentException e) {
        System.out.println("Please enter a proper value for the X dimension...");
        scanner.next(); // clear the invalid input
      }
    }

    System.out.println("\n" + sizeX + " x [...]\n");
    int sizeY = -1;
    while (true) {
      try {
        int temp = scanner.nextInt();
        if (temp < 1) throw new IllegalArgumentException();
        sizeY = temp;
        break;
      } catch (InputMismatchException | IllegalArgumentException e) {
        System.out.println("Please enter a proper value for the Y dimension...");
        scanner.next(); // clear the invalid input
      }
    }

    System.out.println("\nSo this matrix should be: " + sizeX + "x" + sizeY + "\n");

    int[][] matrix = new int[sizeX][sizeY];

    String[] matrixOptions = {"1) Generate a random matrix.", "2) Create my own matrix.", "3) Create a pre-made matrix."};
    System.out.println("Would you like to create your own matrix or generate one?");
    for (String option: matrixOptions) {
      System.out.println(option);
    }

    int selection = -1;
    int countScrewUps = 0;
    while (true) {
      try {
        int temp = scanner.nextInt();
        if (temp < 1 || temp > matrixOptions.length) throw new IllegalArgumentException();
        selection = temp;
        break;
      } catch (InputMismatchException e) {
        System.out.println("Please enter a proper selection...");
        countScrewUps++;
        if (countScrewUps > 5) {
          System.out.println("Fine, be that way...");
          selection = 2;
          break;
        }
        scanner.next();
      } catch (IllegalArgumentException e) {
        System.out.println("Please enter a proper selection...");
        countScrewUps++;
        if (countScrewUps > 4) {
          System.out.println("Fine, be that way...");
          selection = 2;
          break;
        }

      }
    }

    switch (selection) {
      case 1:
        System.out.println("You've chosen to generate a matrix.\nWhat will be the absolute range of integers?");
        int absRange = 0;
        while (true) {
          try {
            absRange = scanner.nextInt();
            break;
          } catch (InputMismatchException e) {
            System.out.println("Enter a proper range value...");
            scanner.next(); // clear the invalid input
          }
        }
        matrix = generateMatrix(matrix, sizeX, sizeY, absRange);
        break;
      case 2:
        matrix = inputMatrix(matrix, sizeX, sizeY);
        break;
      case 3:
        System.out.println("Oops, nothing here yet.");
        break;
    }

    System.out.println("The matrix is constructed as follows: ");
    for (int i = 0; i < sizeX; i++) {
      for (int j = 0; j < sizeY; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }

    matricies.add(matrix);
    matrixOptions();
  }

  private static void printAMatrixRow(int index, int rowIndex) {
    System.out.print("[ ");
    for (int i = 0; i < matricies.get(index)[rowIndex].length; i++) {
      System.out.print(matricies.get(index)[rowIndex][i] + ", ");
    }
    System.out.println("]");
  }

  private static void printAMatrix(int index) {
    System.out.println("Matrix No." + index + ", Size: [" + matricies.get(index).length + " x " + matricies.get(index)[0].length + "]:\n");
    for (int i = 0; i < matricies.get(index).length; i++) {
      for (int j = 0; j < matricies.get(index)[i].length; j++) {
        System.out.print(matricies.get(index)[i][j] + " ");
      }
      System.out.println();
    }
  }

  protected static void printAMatrix(int[][] matrix) {
    System.out.println("Matrix details: Size: [" + matrix.length + " x " + matrix[0].length + "]:\n");
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j <matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }

  protected static void printMatrices(ArrayList<int[][]> matrices) {
    if (matrices.size() == 0) {
      System.out.println("No matrices were made.");
      return;
    }

    for (int i = 0; i < matrices.size(); i++) {
      String separationMark = "~~~~";
      System.out.println("\nMatrix No." + (i+1) + ", SIZE: [" + matricies.get(i).length + " x "+ matricies.get(i)[0].length + "]:");
      for (int j = 0; j < matrices.get(i).length; j++) {
        separationMark += "~~";
        for (int k = 0; k < matrices.get(i)[j].length; k++) {
          System.out.print(matrices.get(i)[j][k] + " ");
          if (matrices.get(i)[j][k] < 0) separationMark += "~";
        }
        System.out.println();
      }
      System.out.println(separationMark);
    }
  }

  private static void matrixOptions() {
    int selection = -1;
    Scanner scanner = new Scanner(System.in);
    String[] options = {
        "Create a Matrix","Generate a COMPLETELY RANDOM Matrix", "Check list of Created Matrices", "Edit a Matrix",
        "Linear Algebra Calculations", "EXIT"
    };


    System.out.println("What would you like to do now?");

    for (int i = 0; i < options.length; i++) {
      System.out.println(i+1 + ") " + options[i]);
    }

    while (selection == -1) {
      try {
        int temp = scanner.nextInt();
        if (temp < 1 || temp > options.length) throw new IllegalArgumentException();
        selection = temp-1;
      } catch (InputMismatchException | IllegalArgumentException e) {
        System.out.println("Please enter a proper selection...");
      }
    }
    System.out.println("You have chosen: " + options[selection] + "...");

    switch (selection) {
      case 0:
        createGivenMatrix();
        break;
      case 1:
        createRandomMatrix();
      case 2:
        printMatrices(matricies);
        matrixOptions();
        break;
      case 3:
        try {
          editAMatrix();
        } catch (IllegalStateException e) {
          System.out.println("Whoops, you have no matrices to edit. REDIRECTING.");
          matrixOptions();
        }
        break;
      case 4:
        try {
          LinearAlgebraCalculations.calculationOptions();
        } catch (IllegalStateException e) {
          System.out.println("Whoops, you have no matrices to work with. REDIRECTING.");
          matrixOptions();
        }
        break;
      case 5:
        endPrompt();
        break;
    }
  }


  public static void editAMatrix() {
    if (matricies.size() == 0) {throw new IllegalStateException();}
    char selection = ' ';
    int selector = -1;
    System.out.println("Let's edit a matrix.\nNeed to see your options again? (Y/N)");
    Scanner scanner = new Scanner(System.in);

    while (selection == ' ') {
      try {
        char temp = scanner.nextLine().charAt(0);
        if (temp != 'Y' && temp != 'N') {throw new IllegalArgumentException();}
        selection = temp;
      } catch (IllegalArgumentException e) {
        System.out.println("Please enter Y or N...");
      }
    }

    switch (selection) {
      case 'Y':
        printMatrices(matricies);
        editAMatrix();
        break;
      case 'N':
          break;
    }

    selection = ' ';

    System.out.println("Indicate which matrix you'd like to edit...");
    if (matricies.size() > 1) System.out.println("SELECTION: (1 - " + (matricies.size()) + ")");
    else System.out.println("SELECTION: (1)");


    while (selector == -1) {
      try {
        int temp = scanner.nextInt()-1;
        if (temp < 0 || temp >= matricies.size()) throw new IllegalArgumentException();
        selector = temp;
      } catch (InputMismatchException | IllegalArgumentException e) {
        System.out.println("Please enter a proper selection...");
      }
    }

    printAMatrix(selector);
    int selector2 = -1;

    System.out.println("Which row would you like to edit? (1 - " + matricies.get(selector).length + ")");
    while (selector2 == -1) {
      try {
        int temp = scanner.nextInt()-1;
        if (temp < 0 || temp >= matricies.size()) throw new IllegalArgumentException();
        selector2 = temp;
      } catch (InputMismatchException | IllegalArgumentException e) {
        System.out.println("Please enter a proper selection duhhhhhh...");
      }
    }

    editAMatrixRow(selector, selector2);

    printAMatrix(selector);

    matrixOptions();
  }

  private static void editAMatrixRow(int mIndex, int rowIndex) {
    System.out.println("\n\n\nAbove is the old row. Enter the values the row should have below.");
    printAMatrixRow(mIndex, rowIndex);

    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine().toUpperCase();
    input = input.trim();
    if (input.charAt(input.length()-1) != ' ') input += " ";
    input = input.replaceAll("[,/]", " ");

    System.out.println(input);

    int count = 0;
    while(input.indexOf(" ") != -1) {
      try {
        matricies.get(mIndex)[rowIndex][count] = Integer.parseInt(input.substring(0, input.indexOf(" ")));
      } catch (NumberFormatException e) {
        System.out.println("Please enter a proper selection...");
        editAMatrixRow(mIndex, rowIndex);
      }
      input = input.substring(input.indexOf(" ") + 1);
      count++;
    }

    System.out.println("Sick! Let's see what it looks like!");
  }

  public static void linearAlgebraFormalities() {
    System.out.println("Welcome to Linear Algebra.\n\n");
    matrixOptions();
    endPrompt();
  }

  private static void endPrompt() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Would you like to leave?");
    System.out.println("1) Yes");
    System.out.println("2) No");

    int selection = 0;
    while(selection == 0) {
      try {
        int temp = scanner.nextInt();
        if (temp < 1 || temp > 2) {throw new IllegalArgumentException();}
        selection = temp;
        break;
      } catch (IllegalArgumentException | InputMismatchException e) {
        scanner.next();
        if (countScrewUps > 4) System.out.println("Really? This again?");
        else System.out.println("Either Yes or No.");
        countScrewUps++;
      }
    }

    switch (selection) {
      case 2:
        linearAlgebraFormalities();
        break;
      case 1:
        System.out.println("Thank you for using Linear Algebra.\nWe cannot wait to see you again!");
        FileReader.start();
        break;
    }
  }
}
