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

/**
 * Stores various information about a *single variable* root finding algorithm
 * run
 */
public class RootFindResults {
    private int iterationCount;
    private double finalEa;
    private double evaluation;
    private double root;
    private boolean found;

    public RootFindResults(double root, int iterationCount, double finalEa,
            double eval, boolean found) {
        this.iterationCount = iterationCount;
        this.finalEa = finalEa;
        this.evaluation = eval;
        this.root = root;
        this.found = found;
    }

    /** The number of times the loop was run */
    public int getIterations() {
        return iterationCount;
    }

    /** The approximate error */
    public double getApproxError() {
        return finalEa;
    }

    /**
     * The evaluation of the function at the returned root. Should be ideally
     * close to zero
     */
    public double getEval() {
        return evaluation;
    }

    /** The actual root value */
    public double getRoot() {
        return root;
    }

    /**
     * Returns true if the root could be looked-for. Is false if, for instance,
     * the zero wasn't bracketed when it should have been
     */
    public boolean foundRoot() {
        return found;
    }

    public String toString() {
        if (!found) {
            return "The root could not be determined\n";
        }
        return "Root: " + root + "\nEval: " + evaluation + "\nIterations: "
                + iterationCount + "\n";
    }
}
