package io.shirohoo.creational.singleton;

import java.util.Random;

public class RandomGenerator {
    private final Random random;

    private RandomGenerator(final Random random) {
        this.random = random;
    }

    public static RandomGenerator getInstance() {
        return RandomGeneratorHolder.instance;
    }

    public int nextInt(final int bound) {
        return random.nextInt(bound);
    }

    public boolean nextBoolean() {
        return random.nextBoolean();
    }

    private static class RandomGeneratorHolder {
        private static final RandomGenerator instance = new RandomGenerator(new Random());
    }
}
