package module9.tests;

import module9.myCollections.MyHashMap;

public class MyHashMapTest {
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        int fillCount = 15;

        System.out.println("filling MyHashMap with values");
        for (int i = 0; i < fillCount; i++) {
            map.put(Integer.toString(i), i);
        }
        System.out.println("Map filled with " + fillCount + " values");
        System.out.println("Map size: " + map.size());

        String existingKey = Integer.toString(fillCount / 2);
        System.out.println("Getting value for existing key: " + existingKey);
        System.out.println("Value: " + map.get(existingKey));


        String nonExistingKey = "KEY";
        System.out.println("Getting value for non-existing key: " + nonExistingKey);
        System.out.println("Value: " + map.get(nonExistingKey));

        String removingKey = Integer.toString(fillCount - 2);
        System.out.println("Removing the entry with key: " + removingKey);
        System.out.println("Removed, value contained with that key: " + map.remove(removingKey));
        System.out.println("New size: " + map.size());

        System.out.println("Clearing the map");
        map.clear();
        System.out.println("New size: " + map.size());
    }
}
