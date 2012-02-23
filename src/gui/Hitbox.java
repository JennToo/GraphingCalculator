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

import java.awt.geom.Rectangle2D;

/**
 * Represents a clickable area on a panel
 */
public class Hitbox {
    /** Stores the geometry of the box */
    private Rectangle2D box;

    /** The name of the box. Passed to the listener on click */
    private String name;

    public Hitbox(Rectangle2D box, String name) {
        this.box = box;
        this.name = name;
    }

    /**
     * Tests if the point is within the hitbox area
     */
    public boolean isHit(double x, double y) {
        return box.contains(x, y);
    }

    public String getName() {
        return name;
    }
}
