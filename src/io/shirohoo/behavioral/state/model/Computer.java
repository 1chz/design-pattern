package io.shirohoo.behavioral.state.model;

import io.shirohoo.behavioral.state.state.PowerState;

public class Computer {

    private PowerState powerState;

    private Computer(final PowerState powerState) {
        this.powerState = powerState;
    }


    public static Computer from(final PowerState powerState) {
        return new Computer(powerState);
    }

    public void changePowerState(final PowerState powerState) {
        this.powerState = powerState;
    }

    public void powerOperate() {
        powerState.powerOperate();
    }

}
