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
import java.awt.Image;
import java.awt.image.BufferedImage;

import functions.Function;
import functions.FunctionArguments;

public class PlotFunction {
    /**
     * Creates a new Image of a function plot
     * 
     * @param f
     *            The function to plot
     * @param window
     *            The constraints of the window (same size as the new image)
     * @param pixelStep
     *            The granularity of the drawing. Higher numbers look better,
     *            but are more expensive (require more function evaluations)
     * @param color
     *            The color of the function plot
     * @return An image with the function plotted
     */
    public static Image plotFunction(Function f, GraphWindow window,
            int pixelStep, Color color) {
        Image plot = new BufferedImage(window.pixWidth, window.pixHeight,
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = plot.getGraphics();

        double[] arg = new double[1];

        PointD prev = null;
        PointD cur = null;
        g.setColor(color);
        for (int i = 0; i < window.pixWidth; i += pixelStep) {
            double trueX = window.xLow + i * window.xScale;
            arg[0] = trueX;
            double trueY = 0.0;
            try {
                trueY = f.evaluate(new FunctionArguments(arg));
            } catch (RuntimeException e) {
                continue;
            }
            if (Double.isNaN(trueY)) {
                continue;
            }

            cur = new PointD(trueX, trueY);
            drawLine(prev, cur, g, window);
            prev = cur;
        }
        
        arg[0] = window.xLow + window.pixWidth * window.xScale;
        drawLine(cur, new PointD(arg[0], f.evaluate(new FunctionArguments(arg))), g, window);

        return plot;
    }

    private static void drawLine(PointD prev, PointD cur, Graphics g,
            GraphWindow window) {
        if (prev == null)
            return;
        int x1 = (int) ((prev.x - window.xLow) / window.xScale);
        int y1 = window.pixHeight
                - (int) ((prev.y - window.yLow) / window.yScale);
        int x2 = (int) ((cur.x - window.xLow) / window.xScale);
        int y2 = window.pixHeight
                - (int) ((cur.y - window.yLow) / window.yScale);
        g.drawLine(x1, y1, x2, y2);
    }
}
