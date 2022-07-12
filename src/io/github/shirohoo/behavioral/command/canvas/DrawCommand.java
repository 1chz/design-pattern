package io.github.shirohoo.behavioral.command.canvas;

import java.awt.Point;

public class DrawCommand implements Command {
    protected Drawable drawable;

    private final Point point;

    public DrawCommand(Drawable drawable, Point point) {
        this.drawable = drawable;
        this.point = point;
    }

    public void execute() {
        drawable.draw(point.x, point.y);
    }
}
