package io.shirohoo.behavioral.command.remote;

// Invoker
public class RemoteControl {

    private final Command lightOn;

    public RemoteControl(final Command lightOn) {
        this.lightOn = lightOn;
    }

    public void pressedButton() {
        lightOn.execute();
    }

}
