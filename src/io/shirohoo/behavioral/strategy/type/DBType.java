package io.shirohoo.behavioral.strategy.type;

import io.shirohoo.behavioral.strategy.connector.DBConnector;
import io.shirohoo.behavioral.strategy.connector.MySQLConnector;
import io.shirohoo.behavioral.strategy.connector.OracleConnector;
import java.util.function.Supplier;

public enum DBType {
    MYSQL(() -> new MySQLConnector()),
    ORACLE(() -> new OracleConnector());

    private final Supplier<DBConnector> supplier;

    DBType(final Supplier<DBConnector> supplier) {
        this.supplier = supplier;
    }

    public DBConnector createConnector() {
        return supplier.get();
    }
}
