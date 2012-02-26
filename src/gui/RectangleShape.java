package gui;

import java.awt.Color;
import java.awt.Graphics;

public class RectangleShape implements Shape {
    private PointD p1;
    private PointD p2;
    private Color color;

    public RectangleShape(PointD p1, PointD p2, Color color) {
        this.p1 = p1;
        this.p2 = p2;
        this.color = color;
    }

    public void draw(Graphics g, GraphWindow window) {
        g.setColor(color);
        int x1 = window.windowToPixelX(p1.x);
        int y1 = window.windowToPixelY(p1.y);
        int x2 = window.windowToPixelX(p2.x);
        int y2 = window.windowToPixelY(p2.y);
        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);

        g.drawRect(x, y, width, height);
    }

}
