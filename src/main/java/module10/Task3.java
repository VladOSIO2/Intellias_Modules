package module10;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Task3 {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "./src/main/resources/module10/task3/words.txt";
        Map<String, Integer> wordCount = countWordsFromFile(new File(path));
        Map<String, Integer> wordCountSorted = sortMapByValues(wordCount, Comparator.reverseOrder());
        printMapEntries(wordCountSorted);
    }

    private static Map<String, Integer> countWordsFromFile(File file) throws FileNotFoundException {
        Map<String, Integer> wordsCount = new HashMap<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String[] words = scanner.nextLine().split("\\s+");
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
