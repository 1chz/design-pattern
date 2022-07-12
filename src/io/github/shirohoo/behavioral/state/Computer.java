package io.github.shirohoo.behavioral.state;

public class Computer {
    private PowerState powerState;

    private Computer(PowerState powerState) {
        this.powerState = powerState;
    }

    public static Computer from(PowerState powerState) {
        return new Computer(powerState);
    }

    public void changePowerState(PowerState powerState) {
        this.powerState = powerState;
    }

    public void powerOperate() {
        powerState.powerOperate();
    }
}
