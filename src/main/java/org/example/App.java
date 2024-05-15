package org.example;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Hangman hangman = new Hangman("hangman", 10);
        Scanner scanner = new Scanner(System.in);

        while (!hangman.isGameOver()) {
            System.out.println("Guess a letter: ");
            char guess = scanner.nextLine().charAt(0);
            hangman.guessLetter(guess);
            System.out.println(hangman.getVisibleWord());
        }

        if (hangman.getVisibleWord().contains("_")) {
            System.out.println("You lost! The word was: " + hangman.getVisibleWord());
        } else {
            System.out.println("You won! The word was: " + hangman.getVisibleWord());
        }
    }
}