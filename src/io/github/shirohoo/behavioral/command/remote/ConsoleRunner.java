package io.github.shirohoo.behavioral.command.remote;

public class ConsoleRunner {
    public static void main(String[] args) {
        RemoteControl remoteControl = RemoteControlLoader.load();
        remoteControl.pressedButton();
    }
}
