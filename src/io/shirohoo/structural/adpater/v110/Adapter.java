package io.shirohoo.structural.adpater.v110;

import io.shirohoo.structural.adpater.v220.Electronic220V;

public class Adapter implements Electronic110V {

    private final Electronic220V electronic220V;

    private Adapter(final Electronic220V electronic220V) {
        this.electronic220V = electronic220V;
    }

    public static Adapter from(final Electronic220V electronic220V) {
        return new Adapter(electronic220V);
    }

    @Override
    public void on() {
        System.out.println("220V -> 110V 컨버트 !");
        electronic220V.on();
    }

}
