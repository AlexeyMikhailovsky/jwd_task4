package by.epam.task4.parser.impl;

import by.epam.task4.entity.SymbolLeaf;
import by.epam.task4.entity.TextComponent;
import by.epam.task4.entity.TextComponentType;
import by.epam.task4.entity.TextComposite;
import by.epam.task4.parser.TextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser implements TextParser {

    private static final String LEXEME_REGEX = "\\S+";
    private static final String WORD_REGEX = "[А-я\\w]+";
    private final TextParser wordParser = new WordParser();
    private final TextParser symbolParser = new SymbolParser();

    @Override
    public TextComposite parse(String sentenceValue) {
        TextComposite sentenceComposite = new TextComposite(TextComponentType.SENTENCE);
        Pattern lexemePattern = Pattern.compile(LEXEME_REGEX);
        Matcher lexemes = lexemePattern.matcher(sentenceValue);

        while (lexemes.find()) {
            TextComposite lexemeComponent = new TextComposite(TextComponentType.LEXEME);
            String lexeme = lexemes.group();
            if (lexeme.matches(WORD_REGEX)) {
                TextComponent wordComponent = wordParser.parse(lexeme);
                lexemeComponent.add(wordComponent);
            } else {
                String possibleWord = lexeme.substring(0, lexeme.length() - 1);
                if (possibleWord.matches(WORD_REGEX)) {
                    TextComponent wordComponent = wordParser.parse(possibleWord);
                    lexemeComponent.add(wordComponent);
                    lexemeComponent.add(new SymbolLeaf(TextComponentType.SYMBOL, lexeme.charAt(possibleWord.length())));
                } else {
                    TextComponent expressionComponent = symbolParser.parse(lexeme);
                    lexemeComponent.add(expressionComponent);
                }
            }
            sentenceComposite.add(lexemeComponent);
        }
        return sentenceComposite;
    }
}
