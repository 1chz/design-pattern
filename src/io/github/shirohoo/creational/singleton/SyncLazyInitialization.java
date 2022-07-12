package io.github.shirohoo.creational.singleton;

import java.util.Objects;

/**
 * This pattern is thread-safe. This approach is not recommended as it has the fatal drawback of being very slow due to the synchronized syntax.
 */
public class SyncLazyInitialization {
    private static SyncLazyInitialization instance;

    private SyncLazyInitialization() {
    }

    public static synchronized SyncLazyInitialization getInstance() {
        if (Objects.isNull(instance)) {
            instance = new SyncLazyInitialization();
        }
        return instance;
    }
}
