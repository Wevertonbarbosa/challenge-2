package br.com.fiap.email.util;

import br.com.fiap.email.model.BlackListSpamWords;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringEmailUtil {


    public static String[] getArrayStringsEmail(final String email) {
        return email.split("\\s+");
    }


    public static Set<String> spamWordsToSet(List<BlackListSpamWords> blackListSpamWords) {
        return blackListSpamWords.stream()
                .map(spamWord -> spamWord.getWord().toLowerCase())
                .collect(Collectors.toSet());
    }


    public static Map<String, Integer> mapTimesAppearanceWord(String[] subjectWords, String[] bodyWords, Set<String> spamWordSet) {
        Map<String, Integer> wordAppearance = new HashMap<>();

        Stream.of(subjectWords, bodyWords)
                .flatMap(Arrays::stream)
                .map(String::toLowerCase)
                .forEach(word -> {
                    if (spamWordSet.contains(word)) {
                        wordAppearance.merge(word, 1, Integer::sum);
                    }
                });
        return wordAppearance;
    }
}
