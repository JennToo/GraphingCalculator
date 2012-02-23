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

public class LineSegment extends Line {

    public LineSegment(PointD p1, PointD p2, Color color) {
        super(p1, p2, color);
    }

    public void drawLine(Graphics g, GraphWindow window) {
        int x1 = window.windowToPixelX(getP1().x);
        int y1 = window.windowToPixelY(getP1().y);
        int x2 = window.windowToPixelX(getP2().x);
        int y2 = window.windowToPixelY(getP2().y);
        g.setColor(getColor());
        g.drawLine(x1, y1, x2, y2);
    }
}
