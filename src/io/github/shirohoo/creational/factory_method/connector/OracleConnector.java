package io.github.shirohoo.creational.factory_method.connector;

public class OracleConnector implements DBConnector {
    @Override
    public void connect() {
        System.out.println("connect Oracle");
    }
}
