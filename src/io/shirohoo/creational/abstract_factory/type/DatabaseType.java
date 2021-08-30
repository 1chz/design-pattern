package io.shirohoo.creational.abstract_factory.type;

public enum DatabaseType {
    MYSQL("mysql"),
    ORACLE("oracle");

    private final String database;

    DatabaseType(final String database) {
        this.database = database;
    }

    public String getDatabase() {
        return database;
    }
}
