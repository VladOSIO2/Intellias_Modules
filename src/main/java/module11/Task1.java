package module11;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task1 {

    /**
     * Constructs a string from odd index list entries
     * In a format "i. {list.get(i)}"
     * @param list list with names
     * @return string with all odd entries from the list
     */
    public static String getOddEntriesAsString(List<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        return IntStream.range(1, list.size())
                .filter(i -> i % 2 != 0)
                .mapToObj(i -> i + ". " + list.get(i))
                .collect(Collectors.joining(", "));
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (char i = 0; i < 19; i++) {
            list.add(String.valueOf((char) ('a' + i)));
        }
        System.out.println(Task1.getOddEntriesAsString(list));
    }
}
