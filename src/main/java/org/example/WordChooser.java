package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
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
     * @return the randomly selected word
     */
    public String chooseWord() {
        if (words.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }
}