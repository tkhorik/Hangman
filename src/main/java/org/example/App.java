package org.example;

import java.util.Scanner;

/**
 * This is the main class for the Hangman game.
 * It initializes a Hangman game and handles user input for guessing letters.
 */
public class App {
    /**
     * The main method that starts the Hangman game.
     * It creates a new Hangman game with the word "hangman" and allows up to 10 incorrect guesses.
     * It then enters a game loop where it asks the user for a letter guess, processes the guess,
     * and displays the current state of the word being guessed.
     * The game continues until the word is fully guessed or the maximum number of incorrect guesses is reached.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Initialize a new Hangman game with the word "hangman" and 10 maximum incorrect guesses
        Hangman hangman = new Hangman("hangman", 10);

        // Create a Scanner object for reading user input
        Scanner scanner = new Scanner(System.in);
//        System.out.println("Argument count: " + args.length);

        // Game loop
        while (!hangman.isGameOver()) {
            // Prompt the user to guess a letter

            //todo: fix this remove after correct implementation and debugging
            WordChooser wordChooser = new WordChooser("src/main/java/org/resourses/words.txt");
            java.util.Optional<String> chosenWord = wordChooser.chooseWord();
            chosenWord.ifPresent(System.out::println);
            String userAgregatedAnswer = null;
            if (chosenWord.isPresent() && chosenWord.get().equals(userAgregatedAnswer)) {
                System.out.println("Word is chosen correctly");
                GameStatus gameOver = GameStatus.GAME_OVER;
            } else {
                System.out.println("Word is not chosen correctly");
            }
            System.out.println("Guess a letter: ");

            //implement a cycle to read the user's guess
            //and process it using the guessLetter method of the Hangman class
            // Read the user's guess
            char guess = scanner.nextLine().charAt(0);

            // Process the user's guess
            hangman.guessLetter(guess);

            // Display the current state of the word being guessed
            System.out.println(hangman.getVisibleWord());
        }

        // Check if the game is over and display the appropriate message
        if (hangman.getVisibleWord().contains("_")) {
            System.out.println("You lost! The word was: " + hangman.getVisibleWord());
        } else {
            System.out.println("You won! The word was: " + hangman.getVisibleWord());
        }
    }
}
