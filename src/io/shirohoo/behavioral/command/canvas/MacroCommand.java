package io.shirohoo.behavioral.command.canvas;

import java.util.Iterator;
import java.util.Stack;

public class MacroCommand implements Command {

    private Stack<Command> commands = new Stack<>();

    public void execute() {
        Iterator<Command> iterator = commands.iterator();
        while (iterator.hasNext()) {
            iterator.next().execute();
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
