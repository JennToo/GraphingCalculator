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
package calculus;

import java.awt.Color;
import java.util.ArrayList;

import matrix.Matrix;
import matrix.MatrixFactory;

import functions.Function;
import functions.FunctionArguments;
import gui.Graph;
import gui.LabeledPoint;
import gui.PointD;
import gui.QuadShape;
import gui.RectangleShape;

public class Integration {
    /**
     * Calculates the numerical integral of f over the range from a to b using
     * Midpoint-Rectangle approximation
     * 
     * @param f
     *            The function to integrate
     * @param a
     *            The lower limit of the interval
     * @param b
     *            The upper limit of the interval
     * @param n
     *            The number of rectangles to use
     * @param g
     *            The Graph object to draw on. Send null if no graphing is
     *            needed
     */
    public static double midpointRectangle(Function f, double a, double b,
            int n, Graph g) {
        if (g != null) {
            g.clearAll();
            g.addPlot(f, Color.BLACK);
        }

        double sum = 0.0;
        double h = (b - a) / n;
        double xn = a;
        double arg[] = new double[1];
        while (xn <= b) {
            arg[0] = xn + h / 2;
            double eval = f.evaluate(new FunctionArguments(arg));

            if (g != null) {
                g.addPoint(new LabeledPoint(new PointD(xn + h / 2, eval), "",
                        Color.BLUE));
                g.addShape(new RectangleShape(new PointD(xn, 0), new PointD(xn
                        + h, eval), Color.BLUE));
            }

            sum += eval;
            xn += h;
        }

        return sum * h;
    }

    /**
     * Calculates the numerical integral of f over the range from a to b using
     * the Trapezoid Rule
     * 
     * @param f
     *            The function to integrate
     * @param a
     *            The lower limit of the interval
     * @param b
     *            The upper limit of the interval
     * @param n
     *            The number of trapezoids to use
     * @param g
     *            The Graph object to draw on. Send null if no graphing is
     *            needed
     */
    public static double trapezoidRule(Function f, double a, double b,
            double n, Graph g) {
        if (g != null) {
            g.clearAll();
            g.addPlot(f, Color.BLACK);
        }

        double sum = 0.0;
        double h = (b - a) / n;
        double arg[] = new double[1];
        double xn = a;
        ArrayList<Double> xs = new ArrayList<Double>();
        ArrayList<Double> ys = new ArrayList<Double>();
        while (xn <= b) {
            xs.add(xn);
            arg[0] = xn;
            ys.add(f.evaluate(new FunctionArguments(arg)));
            xn += h;
        }

        if (g != null) {
            for (int i = 0; i < xs.size() - 1; i++) {
                PointD p1 = new PointD(xs.get(i), ys.get(i));
                PointD p2 = new PointD(xs.get(i + 1), ys.get(i + 1));
                PointD p3 = new PointD(xs.get(i + 1), 0);
                PointD p4 = new PointD(xs.get(i), 0);
                g.addShape(new QuadShape(Color.BLUE, p1, p2, p3, p4));
            }
        }

        for (int i = 1; i < xs.size(); i++) {
            sum += (xs.get(i) - xs.get(i - 1)) * (ys.get(i) + ys.get(i - 1));
        }

        return 0.5 * sum;
    }

    /**
     * Calculates the numerical integral of f over the range from a to b using
     * Simpson's rule
     * 
     * @param f
     *            The function to integrate
     * @param a
     *            The lower limit of the interval
     * @param b
     *            The upper limit of the interval
     * @param n
     *            The number of sub-divisions to make. Must be even
     */
    public static double simpsonsRule(Function f, double a, double b, int n) {
        if (n % 2 != 0) {
            throw new RuntimeException("n must be an even number");
        }
        double h = (b - a) / n;
        double[] arg = new double[1];
        arg[0] = a;
        double sum = f.evaluate(new FunctionArguments(arg));
        boolean four = true;
        int iter = 1;
        while (iter < n) {
            arg[0] = a + iter * h;
            sum += (four ? 4 : 2) * f.evaluate(new FunctionArguments(arg));
            iter++;
            four = !four;
        }

        arg[0] = b;
        sum += f.evaluate(new FunctionArguments(arg));

        return h * sum / 3;
    }

    /**
     * Calculates the numerical integral of f over the range from a to b using
     * Midpoint-Rectangle approximation
     * 
     * @param f
     *            The function to integrate
     * @param a
     *            The lower limit of the interval
     * @param b
     *            The upper limit of the interval
     * @param n
     *            The number of iterations to perform
     */
    public static double rombergsRule(Function f, double a, double b, int n) {
        double[][] r = new double[n][n];
        for (int k = 0; k < n; k++) {
            r[k][0] = trapezoidRule(f, a, b, Math.pow(2, k), null);
        }

        for (int i = 1; i < n; i++) {
            double fi = Math.pow((double) 4, i);
            for (int k = i; k < n; k++) {
                r[k][i] = (fi * r[k][i - 1] - r[k - 1][i - 1]) / (fi - 1);
            }
        }
        return r[n - 1][n - 1];
    }
}
