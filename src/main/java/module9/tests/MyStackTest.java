package module9.tests;

import module9.myCollections.MyStack;

public class MyStackTest {
    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        for (int i = 0; i < 7; i++) {
            stack.push(i);
        }
        print(stack);

        System.out.println("peek: " + stack.peek());
        print(stack);

        System.out.println("pop: " + stack.pop());
        print(stack);

        System.out.println("Adding 8 to stack");
        stack.push(8);
        print(stack);

        System.out.println("pop: " + stack.pop());
        print(stack);

        System.out.println("removing at index 2: " + stack.remove(2));
        print(stack);

        System.out.println("removing at last index: " + stack.remove(stack.size() - 1));
        print(stack);

        System.out.println("Clearing stack");
        stack.clear();
        print(stack);

    }

    private static <E> void print(MyStack<E> stack) {
        System.out.println("Stack: [" + stack + "], size: " + stack.size());
    }
}
