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

/**
 * Implements 3 forms of (single) partial differentiation appoximations
 */
public class PartialDerivatives {
    public static double forwardDifference(Function f, double[] point, int var,
            double step) {
        if (point.length != f.getArgCount())
            return Double.NaN;

        double args[] = point.clone();
        double fx = f.evaluate(new FunctionArguments(args));
        args[var] += step;
        double fxh = f.evaluate(new FunctionArguments(args));
        return (fxh - fx) / step;
    }

    public static double backwardDifference(Function f, double[] point,
            int var, double step) {
        if (point.length != f.getArgCount())
            return Double.NaN;

        double args[] = point.clone();
        double fx = f.evaluate(new FunctionArguments(args));
        args[var] -= step;
        double fxh = f.evaluate(new FunctionArguments(args));
        return (fx - fxh) / step;
    }

    public static double centeredDifference(Function f, double[] point,
            int var, double step) {
        if (point.length != f.getArgCount())
            return Double.NaN;

        double args[] = point.clone();

        args[var] += step;
        double fxh = f.evaluate(new FunctionArguments(args));

        args[var] = point[var] - step;
        double fxl = f.evaluate(new FunctionArguments(args));
        return (fxh - fxl) / (2 * step);
    }
}