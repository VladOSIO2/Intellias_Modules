package module11;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Task3 {
    /**
     * Extracts numbers from Strings in array, and returns them
     * in a single string with ", " delimiter, sorted ascending
     * @param numbers array of strings that contains numbers
     * @return string with all gotten numbers in a single String, ascending order
     */
    public static String getSortedNumbersAsString(String[] numbers) {
        if (numbers == null) {
            return "";
        }

        return Arrays.stream(numbers)
                .flatMap(s -> Arrays.stream(s.split("\\D+")))
                .mapToInt(Integer::parseInt)
                .sorted()
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(", "));
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"1, 2, 0", "4, 5"};

        System.out.println(getSortedNumbersAsString(arr));
    }
}
