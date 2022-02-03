package by.epam.task4.reader;

import by.epam.task4.exception.TextException;
import by.epam.task4.reader.impl.TextReaderImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TextReaderTest {
    private TextReaderImpl textReader;

    @BeforeTest
    public void init() {
        textReader = new TextReaderImpl();
    }

    @Test
    public void readText() throws TextException {
        String text = textReader.read("text.txt");
        Assert.assertFalse(text.isEmpty());
    }
}
