import javax.swing.JOptionPane;

public class PlayGame {
    public static void main(String[] args) {
        ArrayGame gamer = new ArrayGame();

        JOptionPane.showMessageDialog(null, "Think of a number between 1000 and 9999.\nClick OK when you are ready...",
            "Let's play a game", JOptionPane.INFORMATION_MESSAGE);

        while (!gamer.isOver()) {
            int guess = gamer.getGuess();
            String userInput = JOptionPane.showInputDialog("I guess your number is " + guess + ". How many digits are matched?");

            if (userInput == null) { // User closed the dialog or clicked cancel
                JOptionPane.showMessageDialog(null, "Game terminated by user.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }

            int nmatches;
            try {
                nmatches = Integer.parseInt(userInput.trim());
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number between 0 and 4.", "Warning", JOptionPane.WARNING_MESSAGE);
                continue;
            }

            if (nmatches < 0 || nmatches > 4) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number between 0 and 4.", "Warning", JOptionPane.WARNING_MESSAGE);
                continue;
            }

            if (!gamer.updateGuess(nmatches)) {
                JOptionPane.showMessageDialog(null, "Something is wrong. I don't think your number exists...", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }

        int finalGuess = gamer.getGuess();
        System.out.println("I got it. Your number was " + finalGuess + ".");
        System.out.println("I did it in " + gamer.numGuesses() + " rounds. Here is the list of my guesses:");
        for (int g : gamer.priorGuesses()) {
            System.out.print(g + " ");
        }
        System.out.println();
    }
}
