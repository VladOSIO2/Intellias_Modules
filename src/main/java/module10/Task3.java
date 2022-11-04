package module10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Task3 {
    public static void main(String[] args) throws IOException {
        String path = "./src/main/resources/module10/task3/words.txt";
        Map<String, Integer> wordCount = countWordsFromFile(path);
        Map<String, Integer> wordCountSorted = sortMapByValues(wordCount, Comparator.reverseOrder());
        printMapEntries(wordCountSorted);
    }

    private static Map<String, Integer> countWordsFromFile(String path) throws IOException {
        Map<String, Integer> wordsCount = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while (reader.ready()) {
                String[] words = reader.readLine().split("\\s+");
                for(String word : words) {
                    wordsCount.merge(word, 1, Integer::sum);
                }
            }
        }
        return wordsCount;
    }

    private static <K, V> Map<K, V> sortMapByValues(Map<K, V> map, Comparator<V> comparator) {
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(comparator))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        //we're sorting an already existing map, so no key collisions should be met
                        (x, y) -> {throw new IllegalArgumentException("Key collision on map sorting happened");},
                        LinkedHashMap::new));
    }

    private static <K, V> void printMapEntries(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
