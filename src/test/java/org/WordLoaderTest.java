package org;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class WordLoaderTest {
    @Test
    public void loadWordsFromFileReturnsList() {
        List<String> words = WordLoader.loadWordsFromFile();
        assertNotNull("Words list should not be null", words);
        assertFalse("Words list should not be empty", words.isEmpty());
        assertTrue("Should contain common word", words.contains("time"));
    }
}
