package io.shirohoo.structural.adpater.v220;

public class AirConditioner implements Electronic220V {

    @Override
    public void on() {
        System.out.println("220V - 에어컨");
    }

}
