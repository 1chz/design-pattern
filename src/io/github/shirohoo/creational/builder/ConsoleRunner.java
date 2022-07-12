package io.github.shirohoo.creational.builder;

public class ConsoleRunner {
    public static void main(String[] args) {
        Member member = Member.builder()
                .firstName("f")
                .middleName("m")
                .lastName("l")
                .age(1)
                .country("ko")
                .build();
    }
}
