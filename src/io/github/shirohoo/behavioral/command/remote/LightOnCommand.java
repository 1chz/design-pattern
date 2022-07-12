package io.github.shirohoo.behavioral.command.remote;

// Command implementation
public class LightOnCommand implements Command {
    private final LightOn lightOn;

    public LightOnCommand(LightOn lightOn) {
        this.lightOn = lightOn;
    }

    @Override
    public void execute() {
        lightOn.on();
    }
}
