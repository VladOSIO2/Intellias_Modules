package module10;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "./src/main/resources/module10/task2/file.txt";
        File file = new File(path);
        List<User> users = getUsersFromFile(file);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(users));
    }

    private static List<User> getUsersFromFile(File file) throws FileNotFoundException {
        List<User> users = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            scanner.nextLine(); //skipping the first line
            while (scanner.hasNext()) {
                String[] properties = scanner.nextLine().split("\\s+");
                users.add(new User(properties[0], Integer.parseInt(properties[1])));
            }
        }
        return users;
    }

    @Getter
    @AllArgsConstructor
    private static class User {
        private String name;
        private int age;
    }
}




