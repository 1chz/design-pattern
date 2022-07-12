package io.github.shirohoo.behavioral.command.canvas;

import java.util.Stack;

public class MacroCommand implements Command {
    private final Stack<Command> commands = new Stack<>();

    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }

    public void append(Command command) {
        if (command != this) {
            commands.push(command);
        }
    }

    public void undo() {
        if (!commands.empty()) {
            commands.pop();
        }
    }

    public void clear() {
        commands.clear();
    }
}
