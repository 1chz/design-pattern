package io.shirohoo.behavioral.command.canvas;

import java.awt.Point;

public class DrawCommand implements Command{

    protected Drawable drawable;
    private Point point;

    public DrawCommand(final Drawable drawable, final Point point) {
        this.drawable = drawable;
        this.point = point;
    }

    public void execute(){
        drawable.draw(point.x, point.y);
    }

}
