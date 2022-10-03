package module12.task2;

import java.util.concurrent.SynchronousQueue;
import java.util.function.IntPredicate;

public class ConcurrentFizzBuzz {
    private final int n;
    private int counter;
    private final SynchronousQueue<String> numberQueue = new SynchronousQueue<>();

    //used for threads A, B, C synchronization
    private final Object mutex = new Object();
    public ConcurrentFizzBuzz(int n) {
        this.n = n;
        this.counter = 1;
    }

    public void printNumbers() {
        Thread threadA = newNumberCheckerThread(ConcurrentFizzBuzz::fizz, "fizz");
        Thread threadB = newNumberCheckerThread(ConcurrentFizzBuzz::buzz, "buzz");
        Thread threadC = newNumberCheckerThread(ConcurrentFizzBuzz::fizzbuzz, "fizzbuzz");

        Thread threadD = new Thread(() -> {
            threadA.start();
            threadB.start();
            threadC.start();
            while (counter <= n) {
                synchronized (numberQueue) {
                    try {
                        numberQueue.wait(50);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new IllegalThreadStateException(e.getMessage());
                    }
                    number();
                    counter++;
                }
            }
        });

        threadD.start();
    }


    //helper method for creating similar A, B, C threads
    //for avoiding code duplication
    private Thread newNumberCheckerThread(
            IntPredicate predicate, String resultIfTrue
    ) {
        return new Thread(() -> {
            synchronized (mutex) {
                for (int i = 1; i <= n + 1; i++) {
                    try {
                        if (predicate.test(counter)) {
                            numberQueue.put(resultIfTrue);
                        }
                        while (counter < i) {
                            mutex.wait(1);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new IllegalThreadStateException(e.getMessage());
                    }
                    mutex.notifyAll();
                }
            }
        });
    }

    private static boolean fizz(int n) {
        return n % 3 == 0 && n % 5 != 0;
    }

    private static boolean buzz(int n) {
        return n % 5 == 0 && n % 3 != 0;
    }

    private static boolean fizzbuzz(int n) {
        return n % 5 == 0 && n % 3 == 0;
    }

    private void number() {
        String number = numberQueue.poll();
        System.out.print((number == null ? counter : number) + ", ");
    }
}
