package io.github.shirohoo.creational.singleton;

/**
 * This is the most recommended singleton pattern, leveraging dynamic binding to ensure thread safety and deliver superior performance.
 */
public class LazyHolderInitialization {
    private LazyHolderInitialization() {
    }

    public static LazyHolderInitialization getInstance() {
        return LazyHolder.instance;
    }

    private static class LazyHolder {
        private static final LazyHolderInitialization instance = new LazyHolderInitialization();
    }
}

