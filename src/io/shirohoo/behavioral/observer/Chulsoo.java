package io.shirohoo.behavioral.observer;

public class Chulsoo implements LittleRascals {
    @Override
    public void notify(boolean teacherIsComing) {
        if (teacherIsComing) {
            System.out.println("철수가 자리에 앉습니다 !");
        }
    }
}
