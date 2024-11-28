import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class
FileReader { // Renamed to avoid conflict with java.io.FileReader
  private static String[] options = new String[]{"Reading Files", "Writing Files", "Algebra Equations", "Linear Algebra", "Miscellaneous"};

  private static void tutorial() {
    try {
      BufferedWriter bw = new BufferedWriter(new FileWriter("external_files/output.txt"));
      bw.write("welcome to the internet");
      bw.write("\nHELP ME REEEEE");
    } catch (IOException e) {
      System.err.println("Failed to write to file: " + e.getMessage());
    }

    try (Scanner reader = new Scanner(new FileInputStream("external_files/output.txt"))) {
      while (reader.hasNextLine()) {
        System.out.println(reader.nextLine());
      }
    } catch (IOException e) {
      System.err.println("Failed to read from file: " + e.getMessage());
    }
  }



  public static void start() {
    Scanner input = new Scanner(System.in);

    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
        "Remember,\nIf you want to leave, just print 'EXIT' in input below!\n\n" +
        "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    System.out.println("Input anything to get started...");


    if (input.nextLine().equals("EXIT")) System.exit(0);


    System.out.println("Welcome to Scanning and Reading!\nWhat would you like to do today?\n");
    for (int i = 0; i < options.length; i++) {
      System.out.println((i + 1) + ") " + options[i]);
    }
    int selection = -1;
    while (selection == -1) {
      try {
        int inputMade = input.nextInt() - 1;
        if (inputMade < 0 || inputMade >= options.length) {
          throw new IllegalArgumentException();
        }
        selection = inputMade;
      } catch (InputMismatchException | IllegalArgumentException e) {
        System.out.println("Oops, a typo occurred. Please enter a proper index.");
        input.next();
      }
    }
    System.out.println("Selection was: " + options[selection]);
    switch (selection) {
      case 0:
        FileInterpreter.readingFiles();
        break;
      case 1:
        FileInterpreter.writingFiles();
        break;
      case 2:
        Algebra.algebraEquations();
        break;
      case 3:
        LinearAlgebra.linearAlgebraFormalities();
        break;
      case 4:
        Miscellaneous.startUp();
        break;
    }


    input.close();
  }

  public static void main(String[] args) {
    start();
  }
}
