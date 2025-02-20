package org;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WordLoader {
    private static final Logger LOGGER = Logger.getLogger(WordLoader.class.getName());

    public static List<String> loadWordsFromFile() {
        List<String> words;
        try {
            ClassLoader classLoader = WordLoader.class.getClassLoader();
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