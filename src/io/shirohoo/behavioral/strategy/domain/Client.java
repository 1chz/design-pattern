package io.shirohoo.behavioral.strategy.domain;

import io.shirohoo.behavioral.strategy.connector.DBConnector;
import java.util.Objects;

public class Client {

    private DBConnector dbConnector;

    private Client(final DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public static Client from(final DBConnector dbConnector) {
        return new Client(dbConnector);
    }

    public void connect() {
        dbConnector.connect();
    }

    public void changeDBConnector(final DBConnector dbConnector) {
        Objects.requireNonNull(dbConnector, "DBConnector must not ne null !");
        this.dbConnector = dbConnector;
    }

}
