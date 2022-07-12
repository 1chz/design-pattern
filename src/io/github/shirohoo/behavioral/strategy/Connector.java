package io.github.shirohoo.behavioral.strategy;

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
