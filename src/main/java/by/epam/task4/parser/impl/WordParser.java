package by.epam.task4.parser.impl;

import by.epam.task4.entity.TextComponent;
import by.epam.task4.entity.TextComponentType;
import by.epam.task4.entity.TextComposite;
import by.epam.task4.parser.TextParser;

public class WordParser implements TextParser {

    private final TextParser symbolParser = new SymbolParser();

    @Override
    public TextComposite parse(String data) {
        TextComposite wordComposite = new TextComposite(TextComponentType.WORD);
        TextComponent wordComponent  = symbolParser.parse(data);
        wordComposite.add(wordComponent);
        return wordComposite;
    }
}
