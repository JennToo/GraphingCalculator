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
import gui.LineSegment;
import gui.PointD;

public class Secant {
    /**
     * Implements the standard Secant method for root finding
     */
    @SuppressWarnings("deprecation")
    public static RootFindResults zeroSecant(Function f, double p2, double p1,
            double stop, int max, Graph g) {
        if (g != null) {
            g.clearAll();
            g.addPlot(f, Color.BLACK);
        }
        
        int oMax = max;
        double[] arg = new double[1];
        double ea = stop * 1.1;
        arg[0] = p1;
        double fp1 = f.evaluate(new FunctionArguments(arg));
        arg[0] = p2;
        double fp2 = f.evaluate(new FunctionArguments(arg));
        double xr = 0.0;
        while(ea > stop && max > 0) {
            xr = p1 - fp1 * (p1 - p2) / (fp1 - fp2);
            ea = Math.abs((xr - p1) / p1);
            
            arg[0] = xr;
            double fxr = f.evaluate(new FunctionArguments(arg));
            
            if(g != null) {
                g.clearPoints();
                g.clearLines();
                
                g.setNotification("X1: " + (float) p1 + "\nf(X1): "
                        + (float) fp1 + "\nX2: " + (float) p2
                        + "\nf(X2): " + (float) fp2 + "\nXr: " + (float) xr
                        + "\nEa: " + (float) ea);
                
                PointD pd1 = new PointD(p1, fp1);
                PointD pd2 = new PointD(p2, fp2);
                PointD pdxr = new PointD(xr, fxr);
                PointD pxr = new PointD(xr, 0);
                
                g.addPoint(new LabeledPoint(pd1,"(p1, f(p1))", Color.BLUE));
                g.addPoint(new LabeledPoint(pd2, "(p2, f(p2))", Color.BLUE));
                g.addPoint(new LabeledPoint(pdxr, "(xr, f(xr))", Color.ORANGE));
                
                g.addLine(new Line(pd1, pd2, Color.BLUE));
                g.addLine(new LineSegment(pdxr, pxr, Color.ORANGE));
                g.scheduleRepaint();
                Thread.currentThread().suspend();
            }
            
            p2 = p1;
            fp2 = fp1;
            
            p1 = xr;
            arg[0] = xr;
            fp1 = fxr;
            
            max--;
        }
        
        return new RootFindResults(xr, oMax-max, ea, fp1, true);
    }
}
