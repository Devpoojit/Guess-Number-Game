import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * An Array-based implementation of the Guess-A-Number game.
 */
public class ArrayGame {

    private int guess; // The number guessed by the computer
    private int currIndex; // The current index of the guess
    private boolean end; // Whether the game is over
    private Set<Integer> priorGuessesSet; // The set of prior guesses
    private int[] eliminatedNum; // The array of eliminated numbers
    private boolean[] candidateFlags; // The array of candidate flags

    // Constructor
    public ArrayGame() {
        reset();
    }

    // Reset the game data
    public void reset() {
        guess = 1000;
        currIndex = 0;
        end = false;
        priorGuessesSet = new HashSet<>();
        eliminatedNum = new int[9000];
        candidateFlags = new boolean[9000];
        Arrays.fill(candidateFlags, true);
    }

    // Return the current guess
    public boolean isPriorGuess(int n) {
        return priorGuessesSet.contains(n);
    }

    // Return the number of guesses so far
    public int numGuesses() {
        return currIndex;
    }

    // Return the guess number and update the guess number
    public static int numMatches(int a, int b) {
        int matches = 0;
        for (int i = 0; i < 4; i++) {
            if (a % 10 == b % 10) {
                matches++;
            }
            a /= 10;
            b /= 10;
        }
        return matches;
    }

    // Return whether the game is over
    public boolean isOver() {
        return end;
    }

    // Return the guess number and update the guess number
    public int getGuess() {
        if (currIndex > 0) {
            eliminatedNum[currIndex - 1] = guess;
        }
        priorGuessesSet.add(guess);
        return guess;
    }

    // Update the guess number based on the number of matches
    public boolean updateGuess(int nmatches) {
        if (nmatches == 4) {
            end = true;
            return true;
        }

        for (int i = 0; i < candidateFlags.length; i++) {
            if (candidateFlags[i] && numMatches(i + 1000, guess) != nmatches) {
                candidateFlags[i] = false;
            }
        }

        for (int i = 0; i < candidateFlags.length; i++) {
            if (candidateFlags[i]) {
                guess = i + 1000;
                currIndex++;
                return true;
            }
        }

        end = true;
        return false; // All candidates eliminated
    }

    // Return the prior guesses
    public int[] priorGuesses() {
        if (currIndex == 0) {
            return null;
        }
        return Arrays.copyOf(eliminatedNum, currIndex);
    }
}
