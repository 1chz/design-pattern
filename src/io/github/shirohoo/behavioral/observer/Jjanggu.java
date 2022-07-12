package io.github.shirohoo.behavioral.observer;

public class Jjanggu implements LittleRascals {
    @Override
    public void notify(boolean teacherIsComing) {
        if (teacherIsComing) {
            System.out.println("짱구가 자리에 앉습니다 !");
        }
    }
}
