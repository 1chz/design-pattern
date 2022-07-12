package io.github.shirohoo.behavioral.observer;

public class Younghee implements LittleRascals {
    @Override
    public void notify(boolean teacherIsComing) {
        if (teacherIsComing) {
            System.out.println("영희가 자리에 앉습니다 !");
        }
    }
}
