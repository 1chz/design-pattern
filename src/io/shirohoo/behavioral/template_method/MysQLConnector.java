package io.shirohoo.behavioral.template_method;

public class MysQLConnector extends DBConnector {
    @Override
    public void setDatabase(final DatabaseDriver databaseDriver) {
        super.connection = databaseDriver.getConnection();
    }
}
