package io.github.shirohoo.creational.singleton;

import java.util.Random;

public class RandomValueGenerator {
    private final Random random;

    private RandomValueGenerator(Random random) {
        this.random = random;
    }

    public static RandomValueGenerator getInstance() {
        return RandomValueGeneratorHolder.instance;
    }

    public int nextInt(int bound) {
        return random.nextInt(bound);
    }

    public boolean nextBoolean() {
        return random.nextBoolean();
    }

    private static class RandomValueGeneratorHolder {
        private static final RandomValueGenerator instance = new RandomValueGenerator(new Random());
    }
}
