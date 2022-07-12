package io.github.shirohoo.behavioral.state;

public class PowerOn implements PowerState {
    @Override
    public void powerOperate() {
        System.out.println("Power off.");
    }
}
