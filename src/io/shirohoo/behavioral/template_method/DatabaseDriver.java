package io.shirohoo.behavioral.template_method;

public class DatabaseDriver {

    private final String driver;

    private DatabaseDriver(final String driver) {
        this.driver = driver;
    }

    public static DatabaseDriver from(final String driver) {
        return new DatabaseDriver(driver);
    }


    public DatabaseConnection getConnection() {
        return DatabaseConnection.from(driver);
    }

}
