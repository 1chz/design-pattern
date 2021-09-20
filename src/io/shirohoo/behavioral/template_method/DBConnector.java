package io.shirohoo.behavioral.template_method;

public abstract class DBConnector {

    protected DatabaseConnection connection;

    // 데이터 베이스를 입력받습니다.
    public abstract void setDatabase(final DatabaseDriver databaseDriver);

    // 입력받은 데이터베이스에 접속합니다.
    public void connect() {
        System.out.println(String.format("Connected to %s", connection));
    }

    // 필요 할 경우 재정의할 훅 메서드
    public void hook() {
    }

}
