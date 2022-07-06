package io.shirohoo.behavioral.strategy;

public class Runner {
    public static void main(String[] args) {
        Connector mysql = Connector.from(Databases.MYSQL);
        mysql.connect();

        Connector oracle = Connector.from(Databases.ORACLE);
        oracle.connect();
    }
}
