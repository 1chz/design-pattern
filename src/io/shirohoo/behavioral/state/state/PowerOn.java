package io.shirohoo.behavioral.state.state;

public class PowerOn implements PowerState {

    @Override
    public void powerOperate() {
        System.out.println("Power off.");
    }

}
