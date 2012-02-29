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
import java.util.ArrayList;

public class PolygonShape implements Shape {
    private ArrayList<PointD> verticies;
    private Color color;

    public PolygonShape(Color color) {
        verticies = new ArrayList<PointD>();
        this.color = color;
    }
    
    public void clearVertex() {
        verticies.clear();
    }
    
    public void addVertex(PointD point) {
        verticies.add(point);
    }

    public void draw(Graphics g, GraphWindow window) {
        g.setColor(color);
        if (verticies.size() < 3) {
            return;
        }

        PointD p1 = null;
        PointD p2 = verticies.get(0);
        for (int i = 1; i < verticies.size(); i++) {
            p1 = p2;
            p2 = verticies.get(i);
            drawSegment(g, window, p1, p2);
        }
        drawSegment(g, window, p2, verticies.get(0));
    }

    private void drawSegment(Graphics g, GraphWindow window, PointD p1,
            PointD p2) {
        g.drawLine(window.windowToPixelX(p1.x), window.windowToPixelY(p1.y),
                window.windowToPixelX(p2.x), window.windowToPixelY(p2.y));
    }

}
