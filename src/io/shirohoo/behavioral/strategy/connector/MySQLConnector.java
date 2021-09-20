package io.shirohoo.behavioral.strategy.connector;

public final class MySQLConnector implements DBConnector {

    @Override
    public void connect() {
        System.out.println("connect MySQL");
    }

}
