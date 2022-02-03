package by.epam.task4.service;

import by.epam.task4.entity.TextComponent;
import by.epam.task4.entity.TextComposite;
import by.epam.task4.exception.TextException;
import by.epam.task4.parser.TextParser;
import by.epam.task4.parser.impl.ParagraphParser;
import by.epam.task4.reader.TextReader;
import by.epam.task4.reader.impl.TextReaderImpl;
import by.epam.task4.service.impl.TextServiceImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class TextServiceTest {
    private TextService textOperation;
    private TextComposite textComposite;

    @BeforeTest
    public void init() throws TextException {
        textOperation = new TextServiceImpl();
        TextReader textReader = new TextReaderImpl();
        TextParser textParser = new ParagraphParser();

        String text = textReader.read("data/text.txt");
        textComposite = textParser.parse(text);
    }

    @Test
    public void sortParagraphsBySentenceAmount() {
        int expected = 1;
        List<TextComponent> sortedSentences = textOperation.sortParagraphs(textComposite);
        int actual = sortedSentences.get(0).getComponents().size();
        Assert.assertEquals(actual, expected, "sorting paragraphs by sentences amount is invalid");
    }

    @Test
    public void findLongestWordSentences() {
        int expected = 1;
        List<TextComponent> sentences = textOperation.findLongestWordSentences(textComposite);
        int actual = sentences.size();
        Assert.assertEquals(actual, expected, "amount of sentences with the longest word is invalid");
    }

    @Test
    public void countSimilarWords() {
        int expected = 25;
        Map<String, Long> similarWords = (Map<String, Long>) textOperation.countRepeatWords(textComposite);
        int actual = similarWords.size();
        Assert.assertEquals(actual, expected, "amount of repeating words is invalid");
    }

    @Test(dataProvider = "sentences")
    public void countConsonants(TextComponent sentenceComponent) {
        long expected = 57;
        long actual = textOperation.countConsonants(sentenceComponent);
        Assert.assertEquals(actual, expected, "amount of consonants is invalid");
    }

    @Test(dataProvider = "sentences")
    public void countVowels(TextComponent sentenceComponent) {
        long expected = 42;
        long actual = textOperation.countVowels(sentenceComponent);
        Assert.assertEquals(actual, expected, "amount of vowels is invalid");
    }

}
