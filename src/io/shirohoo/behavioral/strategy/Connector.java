package io.shirohoo.behavioral.strategy;

import java.util.Objects;

public final class Connector {
    private final DBConnector dbConnector;

    private Connector(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public static Connector from(Databases databases) {
        return new Connector(databases.createConnector());
    }

    public void connect() {
        dbConnector.connect();
    }
}
