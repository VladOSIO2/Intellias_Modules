package module9.tests;

import module9.myCollections.MyQueue;

public class MyQueueTest {
    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>();
        for (int i = 0; i < 7; i++) {
            queue.add(i);
        }
        print(queue);

        System.out.println("peek: " + queue.peek());
        print(queue);

        System.out.println("poll: " + queue.poll());
        print(queue);

        System.out.println("Adding 8 to queue");
        queue.add(8);
        print(queue);

        System.out.println("poll: " + queue.poll());
        print(queue);

        System.out.println("removing at index 2: " + queue.remove(2));
        print(queue);

        System.out.println("removing at last index: " + queue.remove(queue.size() - 1));
        print(queue);

        System.out.println("Clearing queue");
        queue.clear();
        print(queue);

    }

    private static <E> void print(MyQueue<E> queue) {
        System.out.println("Queue: [" + queue + "], size: " + queue.size());
    }
}
