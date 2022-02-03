package by.epam.task4.parser.impl;

import by.epam.task4.entity.TextComponent;
import by.epam.task4.entity.TextComponentType;
import by.epam.task4.entity.TextComposite;
import by.epam.task4.parser.TextParser;

public class ParagraphParser implements TextParser {

    private static final String PARAGRAPH_REGEX = "\\r\\n";
    private final TextParser sentenceParser = new SentenceParser();

    @Override
    public TextComposite parse(String text) {
        TextComposite textComposite = new TextComposite(TextComponentType.TEXT);
        String[] paragraphs = text.split(PARAGRAPH_REGEX);
        for (String paragraph : paragraphs) {
            TextComponent paragraphComponent = sentenceParser.parse(paragraph);
            textComposite.add(paragraphComponent);
        }
        return textComposite;
    }
}
