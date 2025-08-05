package org.example;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HangmanTest {
    private Hangman hangman;

    @Before
    public void setUp() {
        hangman = new Hangman("hangman", 10);
    }

    @Test
    public void guessLetterCorrectlyUpdatesHiddenWord() {
        hangman.guess('h');
        assertEquals("h______", hangman.getHiddenWord());
    }

    @Test
    public void guessLetterIncorrectlyKeepsHiddenWordSame() {
        hangman.guess('z');
        assertEquals("_______", hangman.getHiddenWord());
    }

    @Test
    public void guessLetterCorrectlyReturnsTrue() {
        assertTrue(hangman.guess('h'));
    }

    @Test
    public void guessLetterIncorrectlyReturnsFalse() {
        assertFalse(hangman.guess('z'));
    }

    @Test
    public void isGameOverReturnsFalseInitially() {
        assertFalse(hangman.isGameOver());
    }

    @Test
    public void isGameOverReturnsTrueWhenWordGuessed() {
        for (char c : "hangman".toCharArray()) {
            hangman.guess(c);
        }
        assertTrue(hangman.isGameOver());
    }
}