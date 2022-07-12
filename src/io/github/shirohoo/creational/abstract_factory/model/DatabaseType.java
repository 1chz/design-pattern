package io.github.shirohoo.creational.abstract_factory.model;

public enum DatabaseType {
    MYSQL("mysql"),
    ORACLE("oracle"),
    ;

    private final String database;

    DatabaseType(String database) {
        this.database = database;
    }

    public String getDatabase() {
        return database;
    }
}
