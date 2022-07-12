package io.github.shirohoo.behavioral.state;

public class ConsoleRunner {
    public static void main(String[] args) {
        // 컴퓨터가 꺼진 상태로 초기화
        Computer computer = Computer.from(new PowerOff());

        computer.powerOperate(); // Power on. 출력
        computer.changePowerState(new PowerOn()); // 컴퓨터가 켜진 상태로 변경

        computer.powerOperate(); // Power off. 출력
        computer.changePowerState(new PowerOff()); // 컴퓨터가 꺼진 상태로 변경
    }
}
