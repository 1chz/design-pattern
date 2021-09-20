package io.shirohoo.behavioral.strategy;

import static io.shirohoo.behavioral.strategy.type.DBType.MYSQL;
import static io.shirohoo.behavioral.strategy.type.DBType.ORACLE;
import io.shirohoo.behavioral.strategy.domain.Client;

public class ConsoleRunner {

    public static void main(String[] args) {
        Client client = Client.from(ORACLE.createConnector());
        client.connect();

        // 전략패턴으로 정책 변경
        client.changeDBConnector(MYSQL.createConnector());
        client.connect();
    }

}
