package io.shirohoo.behavioral.observer;

public class ConsoleRunner {

    public static void main(String[] args) {
        final Watcher publisher = new Watcher(
            event -> System.out.println(String.format("망보는 친구: 복도에 %s 선생님 출현 !", event))
        );

        publisher.publish("홍길동");
        publisher.publish("이순신");
        publisher.publish("유관순");

        System.out.println("친구들은 판을 정리하고 자리에 앉았다 !");
    }

}
