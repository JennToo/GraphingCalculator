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

public class Bisection {
    /**
     * Implements the standard bisection algorithm for a function of a single
     * variable.
     */
    @SuppressWarnings("deprecation")
    public static RootFindResults zeroBisection(Function f, double high,
            double low, double stop, int max, Graph g) {

        if (g != null) {
            g.clearAll();
            g.addPlot(f, Color.BLACK);
        }

        int oMax = max;
        double[] arg = new double[1];

        arg[0] = high;
        double fh = f.evaluate(new FunctionArguments(arg));
        arg[0] = low;
        double fl = f.evaluate(new FunctionArguments(arg));
        if ((fh > 0 && fl > 0) || (fh < 0 && fl < 0)) {
            return new RootFindResults(0, 0, 0, 0, false);
        }

        double prev = high;
        double es = stop * 1.1;
        double xr = Double.NaN;
        double fxr = 0.0;
        while (max > 0 && es > stop) {
            xr = (high + low) / 2;
            arg[0] = xr;
            fxr = f.evaluate(new FunctionArguments(arg));

            if (g != null) {
                g.clearPoints();
                g.clearLines();

                g.setNotification("Xl: " + (float) low + "\nf(Xl): "
                        + (float) fl + "\nXh: " + (float) high + "\nf(Xh): "
                        + (float) fh + "\nXr: " + (float) xr + "\nf(Xr): "
                        + (float) fxr + "\nEa: " + (float)es);

                PointD pLow = new PointD(low, fl);
                PointD pHigh = new PointD(high, fh);
                PointD pXr = new PointD(xr, fxr);

                g.addPoint(new LabeledPoint(pLow, "(Xl, f(Xl))", Color.BLUE));
                g.addPoint(new LabeledPoint(pHigh, "(Xh, f(Xh))", Color.BLUE));
                g.addPoint(new LabeledPoint(pXr, "(Xr, f(Xr))", Color.ORANGE));

                g.addLine(new Line(pLow, pLow, Color.BLUE));
                g.addLine(new Line(pHigh, pHigh, Color.BLUE));
                g.addLine(new Line(pXr, pXr, Color.ORANGE));
                g.scheduleRepaint();
                Thread.currentThread().suspend();
            }

            if (fxr > 0) {
                if (fh > 0) {
                    high = xr;
                    fh = fxr;
                } else {
                    low = xr;
                    fl = fxr;
                }
            } else {
                if (fh < 0) {
                    high = xr;
                    fh = fxr;
                } else {
                    low = xr;
                    fl = fxr;
                }
            }
            es = Math.abs((prev - xr) / (xr));
            //es = Math.abs((high - low) / (high + low));
            prev = xr;
            max--;

        }

        return new RootFindResults(xr, oMax - max, es, fxr, true);
    }
}
