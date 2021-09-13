package io.shirohoo.creational.factory_method.connector;

public class MySQLConnector implements DBConnector{
    @Override
    public void connect() {
        System.out.println("connect MySQL");
    }
}
