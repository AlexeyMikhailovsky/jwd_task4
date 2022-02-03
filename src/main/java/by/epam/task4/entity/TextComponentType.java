package by.epam.task4.entity;

public enum TextComponentType {
    TEXT("\n\t"),
    PARAGRAPH("\n"),
    SENTENCE(""),
    LEXEME(" "),
    WORD(""),
    SYMBOL("");

    private final String delimiter;

    TextComponentType(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
