package io.shirohoo.behavioral.observer;

public class ConsoleRunner {
    public static void main(String[] args) {
        LookOut lookOut = new LookOut();
        lookOut.registerObserver(new Jjanggu());
        lookOut.registerObserver(new Chulsoo());
        lookOut.registerObserver(new Younghee());

        System.out.println("망보는 친구: 복도에 선생님 출현하지 않음 !");
        lookOut.notifyObservers(false);

        System.out.println("망보는 친구: 복도에 선생님 출현 !");
        lookOut.notifyObservers(true);
    }
}
