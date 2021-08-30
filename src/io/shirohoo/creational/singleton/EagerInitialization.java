package io.shirohoo.creational.singleton;

/**
 * This pattern is thread-safe.
 */
public class EagerInitialization {
    private static final EagerInitialization instance = new EagerInitialization();

    private EagerInitialization() {}

    public static EagerInitialization getInstance() {
        return instance;
    }
}
