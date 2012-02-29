/*
 * Copyright 2012 Justin Wilcox
 * 
 * This file is part of GraphingCalculator.
 *
 * GraphingCalculator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GraphingCalculator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with GraphingCalculator.  If not, see <http://www.gnu.org/licenses/>.
 */
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
