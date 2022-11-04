package module10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class Task1 {
    //matches both "(xxx) xxx-xxxx" and "xxx-xxx-xxxx"
    //pattern: ("(xxx) " or "xxx-") + "xxx-xxxx"
    private static final String PHONE_NUMBER_REGEX
            = "(?:(\\(\\d{3}\\) )|(\\d{3}-))\\d{3}-\\d{4}";
    private static final Pattern PHONE_NUMBER_PATTERN
            = Pattern.compile(PHONE_NUMBER_REGEX);

    public static void main(String[] args) throws IOException {
        String path = "./src/main/resources/module10/task1/file.txt";
        printPhoneNumbersFrom(path);
    }

    private static void printPhoneNumbersFrom(String path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while (reader.ready()) {
                String line = reader.readLine();
                if (isPhoneNumber(line)) {
                    System.out.println(line);
                }
            }
        }
    }

    private static boolean isPhoneNumber(String s) {
        return PHONE_NUMBER_PATTERN.matcher(s).matches();
    }
}
