import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FileInterpreter {
  public static void readingFiles() {
    System.out.println("Welcome to Reading Files.");
    System.out.println("What would you like to do?");

    String[] options = {"Check for Plagiarism", "Convert files to .txt files", "Take Excerpts from a Text File."};

    for (int i = 0; i < options.length; i++) {
      System.out.println(i+1 + ") " + options[i]);
    }

    Scanner scanner = new Scanner(System.in);

    int selection = -1;
    while (selection == -1) {
      try {
        int temp = scanner.nextInt() - 1;
        if (temp < 0 || temp >= options.length) {throw new IllegalArgumentException();}
        selection = temp;
      } catch (InputMismatchException | IllegalArgumentException e) {
        System.out.println("Oops, you input something incorrectly. Try again!");
      }
    }

    System.out.println("You've selected: " + options[selection] + ".");

    switch (selection) {
      case 0:
        plagiarismChecker();
        break;
      case 1:
      case 2:
    }
  }

  //~~~~~~~~~~~~~~~~~||Plagiarism Checker||~~~~~~~~~~~~~~~~~//

  private static void plagiarismChecker() {
    //converts text file into a bunch of text segments based on input, feeds it into wordAnagram
    //and then compares against files in "Reading_Check"

  }

  private static void wordAnagramGenerator() {

  }

  //~~~~~~~~~~~~~~~~~~~||End of Program||~~~~~~~~~~~~~~~~~~~//



  //~~~~~~~~~~~~~~~~~~~||File Converter||~~~~~~~~~~~~~~~~~~~//
  //~~~~~~~~~~~~~~~~~~~||End of Program||~~~~~~~~~~~~~~~~~~~//




  //~~~~~~~~~~~~~~~~~~~||Excerpt Taker||~~~~~~~~~~~~~~~~~~~//
  //~~~~~~~~~~~~~~~~~~~||End of Program||~~~~~~~~~~~~~~~~~~~//

  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  public static void writingFiles() {
    System.out.println("Welcome to Writing Files.");
  }

}
