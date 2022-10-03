package module12;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Task1 {
    public static void main(String[] args) {
        final long startMs = System.currentTimeMillis();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        scheduler.scheduleAtFixedRate(
                () -> System.out.println("Пройшло 5 секунд"),
                5, 5, TimeUnit.SECONDS);

        scheduler.scheduleAtFixedRate(
                () -> System.out.println("Main is working for: " + (System.currentTimeMillis() - startMs) + "ms"),
                1, 1, TimeUnit.SECONDS);
    }
}
