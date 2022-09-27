package module11;

import java.util.stream.Stream;

public class Task4 {
    /**
     * Creates and returns a linear congruential generator (LCG) infinite Stream
     * the formula for each next number is x[n + 1] = (a * x[n] + c) % m
     * @param seed, the seed (first number) in linear congruential sequence
     * @param a, the multiplier
     * @param c, the increment
     * @param m, the modulus
     * @return infinite Stream of longs, that can generate longs with the given formula
     */
    public static Stream<Long> getLCGStream(long seed, long a, long c, long m) {
        return Stream.iterate(seed, x -> (a * x + c) % m);
    }


    public static void main(String[] args) {
        long seed = 0;
        long a = 25214903917L;
        long c = 11L;
        long m = 2^48;

        getLCGStream(seed, a, c, m)
                .limit(10)
                .forEach(System.out::println);
    }
}
