package io.shirohoo.behavioral.template_method;

public class ConsoleRunner {

    public static void main(String[] args) {
        DBConnector dbConnector = new MysQLConnector();

        dbConnector.setDatabase(DatabaseDriver.from("MySQL"));
        dbConnector.connect();
    }

}
