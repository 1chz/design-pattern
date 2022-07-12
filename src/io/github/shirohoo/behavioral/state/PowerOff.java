package io.github.shirohoo.behavioral.state;

public class PowerOff implements PowerState {
    @Override
    public void powerOperate() {
        System.out.println("Power on.");
    }
}
