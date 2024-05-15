package org.example;

import java.util.Collection;

public class Hangman {
    //wordToGuess: the word that needs to be guessed.
    //hiddenWord: the word that is displayed to the player.
    private String wordToGuess;
    private String hiddenWord;

    public Hangman(String wordToGuess, int i) {
        this.wordToGuess = wordToGuess;
        this.hiddenWord = wordToGuess.replaceAll("[a-zA-Z]", "_");
    }

    public String getWordToGuess() {
        return this.wordToGuess;
    }

    public String getHiddenWord() {
        return this.hiddenWord;
    }

    //guess: the letter guessed by the player.
    //returns: true if the letter is in the word, false otherwise.
    public boolean guess(char guess) {
        boolean found = false;
        StringBuilder newHiddenWord = new StringBuilder();
        for (int i = 0; i < this.wordToGuess.length(); i++) {
            if (this.wordToGuess.charAt(i) == guess) {
                newHiddenWord.append(guess);
                found = true;
            } else {
                newHiddenWord.append(this.hiddenWord.charAt(i));
            }
        }
        this.hiddenWord = newHiddenWord.toString();
        return found;
    }

    public boolean isGameOver() {
    return false;
    }

    public void guessLetter(char guess) {
        return;
    }

    public Collection<Object> getVisibleWord() {
    return null;
    }
}
