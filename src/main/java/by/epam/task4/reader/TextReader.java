package by.epam.task4.reader;

import by.epam.task4.exception.TextException;

public interface TextReader {

    String read(String pathToFile) throws TextException;
}
