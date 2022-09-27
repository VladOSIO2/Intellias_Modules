package module11;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Task5 {

    /**
     * zips two streams
     * adds an element from each stream to resulting stream
     * until all elements from the smallest of two streams zipped
     * @param first first stream
     * @param second second stream
     * @return zipped stream
     */
    @SuppressWarnings("unchecked")
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        if (first == null || second == null) {
            return null;
        }

        T[] firstObjects = (T[]) first.toArray();
        T[] secondObjects = (T[]) second.toArray();

        int minLength = Math.min(firstObjects.length, secondObjects.length);

        //giving an arraylist capacity, so it won't spend time on resizing
        List<T> list = new ArrayList<>(minLength * 2);

        for (int i = 0; i < minLength; i++) {
            list.add(firstObjects[i]);
            list.add(secondObjects[i]);
        }
        return list.stream();
    }


    public static void main(String[] args) {
        Stream<Integer> first = IntStream.range(0, 15).boxed();
        Stream<Integer> second = IntStream.range(15, 20).boxed();

        System.out.println(zip(first, second).toList());
        //  [0, 15, 1, 16, 2, 17, 3, 18, 4, 19]
    }
}
