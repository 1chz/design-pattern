package io.shirohoo.creational.factory_method;

import io.shirohoo.creational.factory_method.connector.DBConnector;
import io.shirohoo.creational.factory_method.connector.MySQLConnector;
import io.shirohoo.creational.factory_method.connector.OracleConnector;

public final class DBConnectors {

    public static DBConnector createMySQLConnector() {
        return new MySQLConnector();
    }

    public static DBConnector createOracleConnector() {
        return new OracleConnector();
    }

}
