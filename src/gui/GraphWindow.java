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
 * Stores information about the Graph's viewport
 */
public class GraphWindow {
    public double xScale;
    public double yScale;
    public double xLow;
    public double xHigh;
    public double yLow;
    public double yHigh;
    public int pixHeight;
    public int pixWidth;

    public GraphWindow(double xLow, double xHigh, double yLow, double yHigh,
            int pixWidth, int pixHeight) {
        this.xLow = xLow;
        this.xHigh = xHigh;
        this.yLow = yLow;
        this.yHigh = yHigh;
        this.xScale = (xHigh - xLow) / ((double) pixWidth);
        this.yScale = (yHigh - yLow) / ((double) pixHeight);
        this.pixWidth = pixWidth;
        this.pixHeight = pixHeight;
    }

    public void rescale() {
        this.xScale = (xHigh - xLow) / ((double) pixWidth);
        this.yScale = (yHigh - yLow) / ((double) pixHeight);
    }

    /**
     * Converts x from window coordinates to absolute pixel coordinates
     */
    public int windowToPixelX(double x) {
        return (int) ((x - xLow) / xScale);
    }

    /**
     * Converts y from window coordinates to absolute pixel coordinates
     */
    public int windowToPixelY(double y) {
        return pixHeight - (int) ((y - yLow) / yScale);
    }
}
