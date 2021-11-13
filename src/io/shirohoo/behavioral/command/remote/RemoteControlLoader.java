package io.shirohoo.behavioral.command.remote;

public class RemoteControlLoader {

    public static RemoteControl load() {
        LightOnCommand command = new LightOnCommand(new LightOn());
        return new RemoteControl(command);
    }

}
