package io.github.shirohoo.behavioral.template_method;

public class MysQLConnector extends DBConnector {
    @Override
    public void setDatabase(DatabaseDriver databaseDriver) {
        super.connection = databaseDriver.getConnection();
    }
}
