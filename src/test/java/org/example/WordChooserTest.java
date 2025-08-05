package org.example;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;

public class WordChooserTest {
    @Test
    public void chooseWordReturnsValidWord() throws Exception {
        Path temp = Files.createTempFile("words", ".txt");
        Files.write(temp, Arrays.asList("ab", "cat"));
        WordChooser chooser = new WordChooser(temp.toString());
        Optional<String> word = chooser.chooseWord();
        assertTrue(word.isPresent());
        assertEquals("cat", word.get());
        Files.deleteIfExists(temp);
    }
}
