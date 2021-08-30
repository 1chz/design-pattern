package io.shirohoo.creational.singleton;

import java.util.Random;

public class RandomValueGenerator {
    private final Random random;

    private RandomValueGenerator(final Random random) {
        this.random = random;
    }

    public static RandomValueGenerator getInstance() {
        return RandomValueGeneratorHolder.instance;
    }

    public int nextInt(final int bound) {
        return random.nextInt(bound);
    }

    public boolean nextBoolean() {
        return random.nextBoolean();
    }

    private static class RandomValueGeneratorHolder {
        private static final RandomValueGenerator instance = new RandomValueGenerator(new Random());
    }
}
