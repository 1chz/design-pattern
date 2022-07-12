package io.github.shirohoo.structural.proxy.flow_control.operator;

public class ProxyOperator implements Operator {
    private final Operator operator = new RealOperator();

    @Override
    public void operate() {
        System.out.println("it's proxy operator !");
        operator.operate();
    }
}
