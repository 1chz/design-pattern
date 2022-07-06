package io.shirohoo.behavioral.strategy;

public final class OracleConnector implements DBConnector {
    @Override
    public void connect() {
        System.out.println("connected to Oracle");
    }
}
