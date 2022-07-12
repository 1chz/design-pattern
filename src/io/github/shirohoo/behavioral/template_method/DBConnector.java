package io.github.shirohoo.behavioral.template_method;

public abstract class DBConnector {
    protected DatabaseConnection connection;

    public abstract void setDatabase(DatabaseDriver databaseDriver);

    public void connect() {
        System.out.printf("Connected to %s%n", connection);
    }

    public void hook() {
    }
}
