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

import functions.Function;
import functions.FunctionArguments;

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
     */
    public static double midpointRectangle(Function f, double a, double b, int n) {
        double sum = 0.0;
        double h = (b - a) / n;
        double xn = a;
        double arg[] = new double[1];
        while (xn <= b) {
            arg[0] = xn + h / 2;
            sum += f.evaluate(new FunctionArguments(arg));
            xn += h;
        }

        return sum * h;
    }
}
