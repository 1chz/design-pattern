package io.shirohoo.behavioral.strategy;

import java.util.function.Supplier;

public enum Databases {
    MYSQL(MySQLConnector::new),
    ORACLE(OracleConnector::new);

    private final Supplier<DBConnector> supplier;

    Databases(Supplier<DBConnector> supplier) {
        this.supplier = supplier;
    }

    public DBConnector createConnector() {
        return supplier.get();
    }
}
