package io.github.shirohoo.behavioral.template_method;

public class DatabaseConnection {
    private final String connection;

    private DatabaseConnection(String driver) {
        this.connection = driver;
    }

    public static DatabaseConnection from(String driver) {
        return new DatabaseConnection(driver);
    }

    @Override
    public String toString() {
        return connection;
    }
}
