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
package roots;

import java.awt.Color;

import functions.Function;
import functions.FunctionArguments;
import gui.Graph;
import gui.LabeledPoint;
import gui.Line;
import gui.PointD;

public class ModifiedSecant {
    /**
     * Implements the Secant algorithm, but using a fixed step size to emulate
     * Newton-Rapson with a differential approximation
     */
    @SuppressWarnings("deprecation")
    public static RootFindResults zeroModifiedSecant(Function f, double point,
            double step, double stop, int max, Graph g) {
        // Plot the function
        if (g != null) {
            g.clearAll();
            g.setDefaultWindow();
            g.addPlot(f, Color.BLACK);
        }

        //The max iterations
        int oMax = max;
        
        double[] arg = new double[1];
        
        double prev = point;
        double xr = point;
        
        double ea = stop * 1.1;
        double fxr = 0.0;
        while (ea > stop && max > 0) {
            arg[0] = xr;
            fxr = f.evaluate(new FunctionArguments(arg));
            double slope = calculus.Derivatives.forwardDifference(f, xr, step);
            xr = prev - fxr / slope;

            if (g != null) {
                g.clearPoints();
                g.clearLines();

                PointD old1 = new PointD(prev, fxr);
                PointD old2 = new PointD(0, slope * (0 - prev) + fxr);

                PointD new1 = new PointD(xr, 0);

                g.addLine(new Line(old1, old2, Color.BLUE));
                g.addPoint(new LabeledPoint(new1, "Xr", Color.ORANGE));
                g.addPoint(new LabeledPoint(old1, "", Color.BLUE));
                g.scheduleRepaint();
                Thread.currentThread().suspend();
            }

            if (prev != Double.NaN) {
                ea = Math.abs((xr - prev) / xr);
            }
            prev = xr;
            max--;
        }

        return new RootFindResults(xr, oMax - max, ea, fxr, true);
    }
}
