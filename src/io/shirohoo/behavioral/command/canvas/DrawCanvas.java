package io.shirohoo.behavioral.command.canvas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class DrawCanvas extends Canvas implements Drawable {

    private Color color = Color.RED;
    private int radius = 6;
    private MacroCommand history;

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
        graphics.setColor(color);
        graphics.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

}
