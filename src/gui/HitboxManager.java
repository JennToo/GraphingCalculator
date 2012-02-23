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
import java.util.ArrayList;

/**
 * Stores, updates, and notifies Hitboxes and HitboxListener
 */
public class HitboxManager {
    private ArrayList<Hitbox> boxes;
    /** The listener to call on hit */
    private HitboxListener listen;

    public HitboxManager() {
        boxes = new ArrayList<Hitbox>();
    }

    /** Adds a box to the collection */
    public void createBox(Rectangle2D rect, String name) {
        boxes.add(new Hitbox(rect, name));
    }

    public void clearBoxes() {
        boxes.clear();
    }

    public void setListener(HitboxListener listen) {
        this.listen = listen;
    }

    /**
     * Check all boxes for the point, and notify listen if needed
     */
    public void checkBoxes(double x, double y) {
        if (listen == null)
            return;

        for (int i = 0; i < boxes.size(); i++) {
            Hitbox cur = boxes.get(i);
            if (cur.isHit(x, y)) {
                listen.boxClick(cur.getName());
            }
        }
    }
}
