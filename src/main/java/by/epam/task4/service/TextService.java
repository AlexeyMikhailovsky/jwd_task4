package by.epam.task4.service;

import by.epam.task4.entity.TextComponent;
import by.epam.task4.entity.TextComposite;

import java.util.List;
import java.util.Map;

public interface TextService {
    List<TextComponent> sortParagraphs(TextComposite textComposite);
    List<TextComponent> findLongestWordSentences(TextComposite textComposite);
    void deleteSentencesWithLessWords(TextComposite textComposite, int wordsAmount);
    List<String> countRepeatWords(TextComposite textComposite);
    long countConsonants(TextComponent sentenceComponent);
    long countVowels(TextComponent sentenceComponent);
}
