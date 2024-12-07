package Lesson_13;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class WordsCounter {
    public static void main(String[] args) {
        String[] wordsArray = {
                "Luffy", "Zorro", "Sanji", "Zorro", "Nami",
                "Chopper", "Luffy", "Zorro", "Usopp", "Robin",
                "Franky", "Brook", "Luffy", "Jinbe", "Luffy",
                "Vivi", "Nami", "Robin", "Luffy", "Vivi"
        };

        List<String> wordsList = new ArrayList<>();
        for (String word : wordsArray) {
            wordsList.add(word);
        }

        Map<String, Integer> wordCountMap = new HashMap<>();
        for (String word : wordsList) {
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }

        System.out.println("Уникальные слова и их количество:");
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

