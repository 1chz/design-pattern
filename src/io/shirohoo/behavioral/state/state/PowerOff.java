package io.shirohoo.behavioral.state.state;

public class PowerOff implements PowerState {

    @Override
    public void powerOperate() {
        System.out.println("Power on.");
    }

}
