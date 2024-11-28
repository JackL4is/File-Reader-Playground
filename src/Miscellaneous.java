import java.io.IOException;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.sound.sampled.*;

public class Miscellaneous {
  public static void startUp() {
    String[] options = {"Play music!", "Play a game!"};
    Scanner scanner = new Scanner(System.in);

    System.out.println("Welcome to Miscellaneous!\nWhat would you like to do today?");
    for (int i = 0; i < options.length; i++) {
      System.out.println(i + 1 + ") " + options[i]);
    }

    int choice = -1;
    while (choice == -1) {
      try {
        choice = scanner.nextInt() - 1;  // Adjust choice to zero-indexed
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Try again.");
        scanner.nextLine();  // Clear the invalid input
      }
    }

    matchOptions(choice);
  }

  private static void matchOptions(int choice) {
    switch (choice) {
      case 0:
        PlayMusic.startUp();
        break;
      case 1:
        System.out.println("Starting a game...");
        break;
      default:
        System.out.println("Invalid option.");
    }
  }

  public static class PlayMusic {
    public static void startUp() {
      System.out.println("What playlist would you like to play?");
      String[] playLists = {"Work", "Lock-in", "Fun", "MK-ASSOC", "Complete Shuffle"};
      Scanner scanner = new Scanner(System.in);

      // Print the playlist options
      for (int i = 0; i < playLists.length; i++) {
        System.out.print(playLists[i]);
        if (i < playLists.length - 1)
          System.out.print(", ");
      }
      System.out.println();  // Newline after listing playlists

      int choice = -1;
      while (choice == -1) {
        try {
          choice = scanner.nextInt();
          if (choice < 1 || choice > playLists.length) {
            System.out.println("Invalid option. Try again.");
            choice = -1;
          }
        } catch (InputMismatchException e) {
          System.out.println("Invalid input. Try again.");
          scanner.nextLine();  // Clear the invalid input
        }
      }
      try {
        playAudio("Magical_trials.wav");
      } catch (Exception e) {
      }
    }


    public static void playAudio(String fileName)
        throws LineUnavailableException, IOException, UnsupportedAudioFileException,
        LineUnavailableException {
      if (fileName == null) {
        return;
      }
      Scanner scanner = new Scanner(System.in);

      // Locate the file in the `src` folder
      fileName = "music/work/" + fileName; // Adjusted relative path
      URL resource = Miscellaneous.class.getResource("/" + fileName);



      if (resource == null) {
        System.out.println("File not found: " + fileName);
        return;
      }

      try {
        // Load the audio file
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(resource);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        String response = "";

        while (!response.equals("Q")) {
          System.out.println("P = Play (Loop), S = Stop, R = Reset, O = Reset Application, Q = Quit");
          System.out.print("Enter your choice: ");

          response = scanner.next();
          response = response.toUpperCase();

          switch (response) {
            case "P":
              clip.loop(Clip.LOOP_CONTINUOUSLY); // Play and loop continuously
              System.out.println("Playing audio on loop...");
              break;
            case "S":
              clip.stop(); // Stop the audio
              System.out.println("Audio stopped.");
              break;
            case "R":
              clip.setMicrosecondPosition(0); // Reset playback position
              System.out.println("Audio reset to the beginning.");
              break;
            case "O":
              FileReader.start();
            case "Q":
              clip.close(); // Close the audio clip and exit
              System.out.println("Quitting program. Bye!");
              break;
            default:
              System.out.println("Not a valid response.");
          }
        }
      } catch (UnsupportedAudioFileException e) {
        System.out.println("The audio file format is unsupported: " + e.getMessage());
      } catch (IOException e) {
        System.out.println("An I/O error occurred: " + e.getMessage());
      } catch (LineUnavailableException e) {
        System.out.println("Audio line unavailable: " + e.getMessage());
      }
    }
  }
}
