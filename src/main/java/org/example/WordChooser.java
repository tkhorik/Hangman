package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

public class WordChooser {
    private List<String> words;

    public WordChooser(String filePath) {
        words = new ArrayList<>();
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                if (word.length() >= 3 && word.length() <= 10) {
                    words.add(word);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while trying to read the file: " + e.getMessage());
        }
    }

    /**
     * Randomly selects a word from the list of words.
     *
     * @return an {@link Optional} containing the randomly selected word, or
     *         {@code Optional.empty()} if no valid words are available
     */
    public Optional<String> chooseWord() {
        if (words.isEmpty()) {
            return Optional.empty();
        }
        Random random = new Random();
        return Optional.of(words.get(random.nextInt(words.size())));
    }
}