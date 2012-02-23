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

import java.util.ArrayList;

import functions.Function;
import functions.TokenizedFunctionFactory;

public class RootsTestDriver {

    public static void main(String[] args) {

        //Function f = new TestFunction();
        ArrayList<String> vars = new ArrayList<String>();
        vars.add("x");
        Function f = TokenizedFunctionFactory.createFunction("x^2-1", vars);
        
        System.out.println("Function: " + f);
        
        System.out.println("Bisection:");
        RootFindResults r = Bisection.zeroBisection(f, 3.0, 0.0, 0.000001, 100, null);
        System.out.println("Zero: " + r.getRoot() + "\nIterations: "
                + r.getIterations() + "\nEval: " + r.getEval());
        
        System.out.println("\nFalse Position:");
        r = FalsePosition.zeroFalsePosition(f, 3.0, 0.0, 0.000001, 100, null);
        System.out.println("Zero: " + r.getRoot() + "\nIterations: "
                + r.getIterations() + "\nEval: " + r.getEval());
        
        System.out.println("\nSecant:");
        r = Secant.zeroSecant(f, -3.0, 0.01, 0.000001, 100, null);
        System.out.println("Zero: " + r.getRoot() + "\nIterations: "
                + r.getIterations() + "\nEval: " + r.getEval());
        
        System.out.println("\nModified Secant:");
        r = ModifiedSecant.zeroModifiedSecant(f, -3.0, 0.01, 0.000001, 100, null);
        System.out.println("Zero: " + r.getRoot() + "\nIterations: "
                + r.getIterations() + "\nEval: " + r.getEval());
    }

}
