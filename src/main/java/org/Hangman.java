package org;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class Hangman {
    private static final Logger LOGGER = Logger.getLogger(Hangman.class.getName());

    public static void main(String[] args) {
        List<String> words = loadWordsFromFile();
        if (words == null) {
            return;
        }
        // Task 2: Select a random word
        Random random = new Random();
        String word = words.get(random.nextInt(words.size()));

        // Task 3: Initialize the game state
        int attempts = 10;
        StringBuilder display = new StringBuilder("----------");
        Set<Character> guessedLetters = new HashSet<>();
        Scanner scanner = new Scanner(System.in);

        // Task 4: Game loop
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

        // Task 5: Win or lose condition
        if (display.indexOf("-") == -1) {
            System.out.println("Congratulations! You guessed the word: " + word);
        } else {
            System.out.println("Sorry, you lost. The word was: " + word);
        }
    }

    private static List<String> loadWordsFromFile() {
        List<String> words;
        try {
            ClassLoader classLoader = Hangman.class.getClassLoader();
            words = Files.readAllLines(Paths.get(Objects.requireNonNull(classLoader.getResource("words.txt")).toURI()));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading words file", e);
            System.err.println("An error occurred while reading the words file. Please try again later.");
            return null;
        } catch (URISyntaxException e) {
            LOGGER.log(Level.SEVERE, "Invalid URI syntax for words file", e);
            System.err.println("An error occurred while accessing the words file. Please try again later.");
            return null;
        } catch (NullPointerException e) {
            LOGGER.log(Level.SEVERE, "Words file not found", e);
            System.err.println("Words file not found. Please ensure the file is in the correct location.");
            return null;
        }
        return words;
    }
}