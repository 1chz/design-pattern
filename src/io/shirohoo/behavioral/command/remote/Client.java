package io.shirohoo.behavioral.command.remote;

public class Client {

    public static void main(String[] args) {
        RemoteControl remoteControl = RemoteControlLoader.load();
        remoteControl.pressedButton();
    }

}
