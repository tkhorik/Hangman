package org;

import java.util.*;

public class Hangman {

    public static void main(String[] args) {
        List<String> words = WordLoader.loadWordsFromFile();
        if (words == null) {
            return;
        }

        Random random = new Random();
        String word = words.get(random.nextInt(words.size()));

        GameLoop gameLoop = new GameLoop(word, 10);
        gameLoop.start();
    }
}