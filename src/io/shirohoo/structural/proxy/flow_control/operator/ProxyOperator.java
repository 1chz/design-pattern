package io.shirohoo.structural.proxy.flow_control.operator;

public class ProxyOperator implements Operator {
    private final Operator operator = new RealOperator();

    @Override
    public void operation() {
        System.out.println("it's proxy operator !");
        operator.operation();
    }
}
