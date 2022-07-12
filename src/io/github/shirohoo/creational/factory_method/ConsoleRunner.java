package io.github.shirohoo.creational.factory_method;

import io.github.shirohoo.creational.factory_method.connector.DBConnector;

public class ConsoleRunner {
    public static void main(String[] args) {
        System.out.println("======== MySQL ========");
        DBConnector mySQLConnector = DBConnectors.createMySQLConnector();
        mySQLConnector.connect();

        System.out.println("======== Oracle ========");
        DBConnector oracleConnector = DBConnectors.createOracleConnector();
        oracleConnector.connect();
    }
}
