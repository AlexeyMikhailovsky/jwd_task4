package by.epam.task4.reader.impl;

import by.epam.task4.exception.TextException;
import by.epam.task4.reader.TextReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextReaderImpl implements TextReader {

    static Logger logger = LogManager.getLogger();

    @Override
    public String read(String pathToFile) throws TextException {
        String text;
        try {
            text = Files.readString(Paths.get(pathToFile));
        }catch (FileNotFoundException e){
            logger.error( "File on path: " + pathToFile + " was not found", e);
            throw new TextException("File on path: " + pathToFile + " was not found", e);
        }
        catch (IOException e) {
            logger.error("IO Exception: " + pathToFile, e);
            throw new TextException("IO Exception: " + pathToFile, e);
        }
        return text;
    }
}
