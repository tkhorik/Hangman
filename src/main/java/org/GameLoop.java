package org;

import java.util.*;
import java.util.stream.Collectors;

public class GameLoop {
    private final String word;
    private final int maxAttempts;

    public GameLoop(String word, int maxAttempts) {
        this.word = word;
        this.maxAttempts = maxAttempts;
    }

    public void start() {
        int attempts = maxAttempts;
        StringBuilder display = new StringBuilder("-".repeat(word.length()));
        Set<Character> guessedLetters = new HashSet<>();
        Scanner scanner = new Scanner(System.in);

        while (attempts > 0 && display.indexOf("-") != -1) {
            System.out.println("Current word: " + display.toString().replace("", " ").trim());
            System.out.println("Attempts left: " + attempts);
            if (!guessedLetters.isEmpty()) {
                String guessedStr = guessedLetters.stream()
                        .map(String::valueOf)
                        .sorted()
                        .collect(Collectors.joining(", "));
                System.out.println("Letters guessed: " + guessedStr);
            }
            System.out.print("Guess a letter: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Please enter a single letter.");
                continue;
            }
            char guess = input.charAt(0);
            if (guessedLetters.contains(guess)) {
                System.out.println("You already guessed that letter.");
                continue;
            }
            guessedLetters.add(guess);

            boolean correctGuess = false;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess) {
                    display.setCharAt(i, guess);
                    correctGuess = true;
                }
            }
            if (!correctGuess) {
                attempts--;
            }
        }

        if (display.indexOf("-") == -1) {
            System.out.println("Congratulations! You guessed the word: " + word);
        } else {
            System.out.println("Sorry, you lost. The word was: " + word);
        }
    }
}