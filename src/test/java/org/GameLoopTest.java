package org;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;

public class GameLoopTest {
    @Test
    public void testDisplayMatchesWordLengthAfterFirstGuess() {
        String word = "java";
        ByteArrayInputStream in = new ByteArrayInputStream("x\n".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        try {
            System.setIn(in);
            System.setOut(new PrintStream(out));
            GameLoop loop = new GameLoop(word, 1);
            loop.start();
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
        String output = out.toString();
        String currentWordLine = null;
        for (String line : output.split(System.lineSeparator())) {
            if (line.startsWith("Current word:")) {
                currentWordLine = line;
                break;
            }
        }
        assertNotNull("Should print current word line", currentWordLine);
        long dashCount = currentWordLine.chars().filter(ch -> ch == '-').count();
        assertEquals(word.length(), dashCount);
    }
}
