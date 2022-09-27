package module11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Task2 {

    /**
     * @param list a list with strings
     * @return new unmodifiable list with uppercase strings sorted in reverse order
     */
    public static List<String> getUppercaseReverseSortedList(List<String> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        return list.stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (char i = 0; i < 19; i++) {
            list.add(String.valueOf((char) ('a' + i)));
        }
        System.out.println(getUppercaseReverseSortedList(list));
    }
}
