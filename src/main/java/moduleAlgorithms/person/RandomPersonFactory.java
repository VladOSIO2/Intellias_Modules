package moduleAlgorithms.person;

public class RandomPersonFactory {
    private static final int MIN_HEIGHT = 140;
    private static final int MAX_HEIGHT = 195;

    private static final int MIN_WEIGHT = 40;
    private static final int MAX_WEIGHT = 120;

    private static final int MIN_AGE = 20;
    private static final int MAX_AGE = 80;

    public static Person nextPerson() {
        return new Person(getRandomHeight(), getRandomWeight(), getRandomAge());
    }

    private static int getRandomHeight() {
        return getRandIntInRange(MIN_HEIGHT, MAX_HEIGHT);
    }

    private static int getRandomWeight() {
        return getRandIntInRange(MIN_WEIGHT, MAX_WEIGHT);
    }

    private static int getRandomAge() {
        return getRandIntInRange(MIN_AGE, MAX_AGE);
    }

    private static int getRandIntInRange(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }
}
