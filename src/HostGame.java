import java.util.Random;
import java.util.Scanner;

public class HostGame {
    /**
     * Computer as the host of the game.
     */
  public static void main(String[] args) {
    Random rnd = new Random();
    Scanner conIn = new Scanner(System.in);

    int groundtruth = rnd.nextInt(9000) + 1000;

    System.out.println("Welcome to the Number Guessing Game!");
    System.out.println("I have thought of a 4-digit number for you to guess.");
    System.out.println("Try to guess it with the fewest attempts.");

    int nguesses = 0;
    while (true) {
      // Take guess
      System.out.print("Enter your guess (a 4-digit integer): ");
      int guess;
      try {
          guess = conIn.nextInt();
      } catch (Exception e) {
          conIn.nextLine(); // Clear input buffer
          System.out.println("Invalid input. Please enter a 4-digit integer.");
          continue;
      }

      if (guess < 1000 || guess > 9999) {
          System.out.println("Your guess is out of range (1000 - 9999).");
          continue;
      }

      nguesses++;
      int nmatches = ArrayGame.numMatches(guess, groundtruth);
      if (nmatches == 4) {
          System.out.println("Congratulations! You have won!");
          break;
      } else {
          System.out.println("Number of matches with the target: " + nmatches);
          System.out.println("Try again.");
      }
    }

    System.out.println("The number I had was " + groundtruth);
    System.out.println("You guessed it in " + nguesses + " rounds.");
    
    conIn.close(); // Close the input scanner
  }
}
