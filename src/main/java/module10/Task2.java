package module10;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class Task2 {
    public static void main(String[] args) throws IOException {
        String inputPath = "./src/main/resources/module10/task2/file.txt";
        List<User> users = getUsersFromFile(inputPath);

        String outputPath = "./src/main/resources/module10/task2/user.json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(outputPath)) {
            gson.toJson(users, writer);
        }
    }

    private static List<User> getUsersFromFile(String path) throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.readLine(); //skipping the first line
            while (reader.ready()) {
                String[] properties = reader.readLine().split("\\s+");
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




