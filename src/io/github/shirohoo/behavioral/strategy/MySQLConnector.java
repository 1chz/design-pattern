package io.github.shirohoo.behavioral.strategy;

public final class MySQLConnector implements DBConnector {
    @Override
    public void connect() {
        System.out.println("connected to MySQL");
    }
}
