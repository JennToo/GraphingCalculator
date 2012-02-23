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

/**
 * Performs actions on the graph based on hitbox names
 */
public class GraphToolbox implements HitboxListener {
    private Graph parent;
    
    public GraphToolbox(Graph par) {
        parent = par;
    }
    
    public void boxClick(String name) {
        if(name.equals("zoomOut")) {
            parent.zoom(1.1);
        } else if(name.equals("zoomIn")) {
            parent.zoom(0.9);
        } else if(name.equals("left")) {
            parent.translate(-7.0, 0.0);
        } else if(name.equals("right")) {
            parent.translate(7.0, 0.0);
        } else if(name.equals("up")) {
            parent.translate(0.0, 7.0);
        } else if(name.equals("down")) {
            parent.translate(0.0, -7.0);
        } else if(name.equals("zoomBox")) {
            parent.setZoomBoxMode();
        }
    }

}
