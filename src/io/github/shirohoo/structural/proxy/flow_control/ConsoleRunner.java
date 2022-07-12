package io.github.shirohoo.structural.proxy.flow_control;

import io.github.shirohoo.structural.proxy.flow_control.client.Client;
import io.github.shirohoo.structural.proxy.flow_control.operator.ProxyOperator;

public class ConsoleRunner {
    public static void main(String[] args) {
        Client client = new Client(new ProxyOperator());
        client.callOperation();
    }
}
