package io.github.shirohoo.structural.adpater.v110;

public class HairDryer implements Electronic110V {
    @Override
    public void on() {
        System.out.println("110V - 헤어 드라이기");
    }
}
