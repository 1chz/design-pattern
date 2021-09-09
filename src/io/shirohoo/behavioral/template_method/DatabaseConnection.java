package io.shirohoo.behavioral.template_method;

public class DatabaseConnection {
    private String connection;

    private DatabaseConnection(final String driver) {
        this.connection = driver;
    }

    public static DatabaseConnection from(final String driver) {
        return new DatabaseConnection(driver);
    }

    @Override
    public String toString() {
        return connection;
    }
}
