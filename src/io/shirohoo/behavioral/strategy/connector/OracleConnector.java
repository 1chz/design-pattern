package io.shirohoo.behavioral.strategy.connector;

public final class OracleConnector implements DBConnector {

    @Override
    public void connect() {
        System.out.println("connect Oracle");
    }

}
