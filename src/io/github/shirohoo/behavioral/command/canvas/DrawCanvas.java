package io.github.shirohoo.behavioral.command.canvas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class DrawCanvas extends Canvas implements Drawable {
    private static final int RADIUS = 6;

    private final MacroCommand history;

    public DrawCanvas(int width, int height, MacroCommand history) {
        setSize(width, height);
        setBackground(Color.white);
        this.history = history;
    }

    public void paint(Graphics graphics) {
        history.execute();
    }

    public void draw(int x, int y) {
        Graphics graphics = getGraphics();
        graphics.setColor(Color.RED);
        graphics.fillOval(x - RADIUS, y - RADIUS, RADIUS * 2, RADIUS * 2);
    }
}
