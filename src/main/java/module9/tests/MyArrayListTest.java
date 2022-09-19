package module9.tests;

import module9.myCollections.MyArrayList;

public class MyArrayListTest {
    public static void main(String[] args) {
        MyArrayList<Integer> l = new MyArrayList<>(2);
        for (int i = 0; i < 5; i++) {
            l.add(i);
            System.out.println("Added " + l.get(i) +
                    " to MyArrayList instance, size: " + l.size());
        }


        int index = l.size() / 2;
        System.out.println("Removing item on index: " + index);
        System.out.println("Removed: " + l.remove(index));

        System.out.println("List size before clearing: " + l.size());
        l.clear();
        System.out.println("List cleared. Size: " + l.size());
    }
}
