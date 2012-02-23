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
import java.awt.Font;
import java.awt.Graphics;

/**
 * Area for information to appear on the Graph
 */
public class NotificationArea {
    private Font font;
    private Color color;
    private String message;
    private PointD location;
    
    public NotificationArea(PointD location, Font font, Color color) {
        this.font = font;
        this.color = color;
        this.location = location;
        message = "";
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String newMsg) {
        message = newMsg;
    }
    
    public void draw(Graphics g) {
        int x = (int) location.x;
        int y = (int) location.y + g.getFontMetrics(font).getHeight();
        String[] lines = message.split("\n");
        g.setColor(color);
        g.setFont(font);
        for(int i = 0; i < lines.length; i++) {
            g.drawString(lines[i], x, y);
            y += g.getFontMetrics(font).getHeight();
        }
    }
}
