package by.epam.task4.parser.impl;

import by.epam.task4.entity.SymbolLeaf;
import by.epam.task4.entity.TextComponent;
import by.epam.task4.entity.TextComponentType;
import by.epam.task4.entity.TextComposite;
import by.epam.task4.parser.TextParser;

public class SymbolParser implements TextParser {

    private static final String SYMBOL_DELIMITER= "";

    @Override
    public TextComposite parse(String text) {
        TextComposite symbolComposite = new TextComposite(TextComponentType.SYMBOL);
        String[] symbols = text.split(SYMBOL_DELIMITER);
        for (String symbol : symbols) {
            TextComponent leaf =
                    new SymbolLeaf(TextComponentType.SYMBOL, symbol.charAt(0));
            symbolComposite.add(leaf);
        }
        return symbolComposite;
    }
}
