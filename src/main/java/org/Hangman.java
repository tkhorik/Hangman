package org;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class Hangman {
    public static void main(String[] args) {
        // Task 1: Set up the word list
//        String[] words = {"university", "creativity", "innovation", "technology", "experience",
//                "expression", "imagination", "adrenaline", "dedication", "motivation"};
        //todo: fix this remove after correct implementation and debugging it makes Error reading words file: ./resourses/words.txt
        List<String> words = new ArrayList<>();
        try {
            words = Files.readAllLines(Paths.get("./resourses/words.txt"));
        } catch (IOException e) {
            System.err.println("Error reading words file: " + e.getMessage());
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
}