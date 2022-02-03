package by.epam.task4.service.impl;

import by.epam.task4.entity.TextComponent;
import by.epam.task4.entity.TextComponentType;
import by.epam.task4.entity.TextComposite;
import by.epam.task4.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextServiceImpl implements TextService {

    static Logger logger = LogManager.getLogger();

    private static final String VOWELS = "(?iu:[aouieyаоэуыеяёюи])";
    private static final String CONSONANTS = "(?iu:[b-zб-ь&&[^еёиоуыeiouy]])";

    @Override
    public List<TextComponent> sortParagraphs(TextComposite textComposite) {
        List<TextComponent> sortedParagraphs = textComposite.getComponents();
        sortedParagraphs.sort(new Comparator<TextComponent>() {
            public int compare(TextComponent o1, TextComponent o2) {
                return o1.size() - o2.size();
            }
        });
        return sortedParagraphs;
    }

    @Override
    public List<TextComponent> findLongestWordSentences(TextComposite textComposite) {
        int maxLength = 0;
        List<TextComponent> sentenceWithWord = new ArrayList<>();
        List<TextComponent> paragraphs = textComposite.getComponents();
        for (TextComponent paragraph : paragraphs) {
            List<TextComponent> sentences = paragraph.getComponents();
            for (TextComponent sentence : sentences) {
                List<TextComponent> lexemes = sentence.getComponents();
                for (TextComponent lexeme : lexemes) {
                    List<TextComponent> wordsAndSymbols = lexeme.getComponents();
                    for (TextComponent word : wordsAndSymbols) {
                        if (word.size() > maxLength) {
                            maxLength = word.size();
                            sentenceWithWord.clear();
                            sentenceWithWord.add(sentence);
                        }
                    }
                }
            }
        }
        return sentenceWithWord;
    }

    @Override
    public void deleteSentencesWithLessWords(TextComposite textComposite, int wordsAmount) {
        List<TextComponent> sentencesAfterRemoving = new ArrayList<>();
        List<TextComponent> sentences;
        int count = 0;
        List<TextComponent> paragraphs = textComposite.getComponents();
        for (TextComponent paragraph : paragraphs) {
            sentences = paragraph.getComponents();
            sentencesAfterRemoving.addAll(sentences);
            for (TextComponent sentence : sentences) {
                List<TextComponent> lexemes = sentence.getComponents();
                for (TextComponent lexeme : lexemes) {
                    if (lexeme.getType().equals(TextComponentType.WORD)) {
                        ++count;
                    }
                }
                if (count < wordsAmount) {
                    sentencesAfterRemoving.remove(sentence);
                }
                count = 0;
            }
            if (sentencesAfterRemoving.isEmpty()) {
                paragraphs.remove(paragraph);
            } else {
                paragraph.setComponents(sentencesAfterRemoving);
            }
            sentencesAfterRemoving.removeAll(sentences);
        }
    }

    @Override
    public List<String> countRepeatWords(TextComposite textComposite) {
        Map<String, Integer> textWords = new HashMap<>();
        List<TextComponent> paragraphs = textComposite.getComponents();
        for (TextComponent paragraph : paragraphs) {
            List<TextComponent> sentences = paragraph.getComponents();
            for (TextComponent sentence : sentences) {
                List<TextComponent> lexemes = sentence.getComponents();
                for (TextComponent lexeme : lexemes) {
                    if (lexeme.getType().equals(TextComponentType.WORD)) {
                        List<TextComponent> words = lexeme.getComponents();
                        for (TextComponent word : words) {
                            int counter = 1;
                            String wordWithoutCase = word.toString().toLowerCase();
                            if (textWords.containsKey(wordWithoutCase)) {
                                counter = textWords.get(wordWithoutCase) + 1;
                            }
                            textWords.put(wordWithoutCase, counter);
                        }
                    }
                }
            }
        }
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> word : textWords.entrySet()) {
            if (word.getValue() > 1) {
                result.add(word.getKey());
            }
        }
        return result;
    }

    @Override
    public long countConsonants(TextComponent sentenceComponent) {
        Pattern pattern = Pattern.compile(CONSONANTS);
        Matcher matcher;
        long counter = 0;
        List<TextComponent> lexemes = sentenceComponent.getComponents();
        for (TextComponent lexeme : lexemes) {
            if (lexeme.getType().equals(TextComponentType.WORD)) {
                List<TextComponent> words = lexeme.getComponents();
                for (TextComponent word : words) {
                    List<TextComponent> symbols = word.getComponents();
                    for (TextComponent symbol : symbols) {
                        matcher = pattern.matcher(symbol.toString().toLowerCase());
                        if (matcher.matches()) {
                            counter++;
                        }
                    }
                }
            }
        }
        return counter;
    }

    @Override
    public long countVowels(TextComponent sentenceComponent) {
        Pattern pattern = Pattern.compile(VOWELS);
        Matcher matcher;
        long counter = 0;
        List<TextComponent> lexemes = sentenceComponent.getComponents();
        for (TextComponent lexeme : lexemes) {
            if (lexeme.getType().equals(TextComponentType.WORD)) {
                List<TextComponent> words = lexeme.getComponents();
                for (TextComponent word : words) {
                    List<TextComponent> symbols = word.getComponents();
                    for (TextComponent symbol : symbols) {
                        matcher = pattern.matcher(symbol.toString().toLowerCase());
                        if (matcher.matches()) {
                            counter++;
                        }
                    }
                }
            }
        }
        return counter;
    }
}
