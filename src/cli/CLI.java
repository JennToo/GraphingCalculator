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
package cli;

import java.util.ArrayList;

import roots.Bisection;
import roots.FalsePosition;
import roots.ModifiedSecant;
import roots.RootFindResults;
import roots.Secant;

import functions.Function;
import functions.FunctionStore;
import functions.TokenizedFunctionFactory;

//Unused. Superseded by CalculatorWindow
/** A test class for roots */
public class CLI {
    public static void main(String[] args) {
        String input = "";
        do {
            input = Keyboard.getKeyboard().readString(":> ");
            String[] parts = input.split(" ");
            if (parts[0].equals("eval") || parts[0].equals("evaluate")) {
                evaluateString(parts);
            } else if (parts[0].equals("defun")
                    || parts[0].equals("deffunction")) {
                defineFunction(parts);
            } else if (parts[0].equals("zero")) {
                evaluateZero(parts);
            } else {
                System.out.println("Unknown command");
            }
        } while (!input.equals("quit"));
    }

    private static void evaluateZero(String[] parts) {
        try {
            RootFindResults rf = null;
            int iterations = (int) Parameters.getParams().getParam(
                    "MaxIterations");
            double es = Parameters.getParams().getParam("StoppingThreshold");

            if (parts[1].equals("falseposition")
                    || parts[1].equals("bisection")
                    || parts[1].equals("secant")) {
                double low = Double.parseDouble(parts[2]);
                double high = Double.parseDouble(parts[3]);
                ArrayList<String> vars = new ArrayList<String>();
                vars.add(parts[4]);
                StringBuilder buildUp = new StringBuilder();
                for (int i = 5; i < parts.length; i++) {
                    buildUp.append(parts[i]);
                }
                Function f = TokenizedFunctionFactory.createFunction(
                        buildUp.toString(), vars);

                if (parts[1].equals("bisection")) {
                    rf = Bisection.zeroBisection(f, high, low, es, iterations,
                            null);
                } else if (parts[1].equals("falseposition")) {
                    rf = FalsePosition.zeroFalsePosition(f, high, low, es,
                            iterations, null);
                } else if (parts[1].equals("secant")) {
                    rf = Secant.zeroSecant(f, high, low, es, iterations, null);
                }
            } else if (parts[1].equals("modsecant")) {
                double guess = Double.parseDouble(parts[2]);
                ArrayList<String> vars = new ArrayList<String>();
                vars.add(parts[3]);
                StringBuilder buildUp = new StringBuilder();
                for (int i = 4; i < parts.length; i++) {
                    buildUp.append(parts[i]);
                }
                Function f = TokenizedFunctionFactory.createFunction(
                        buildUp.toString(), vars);
                double step = Parameters.getParams().getParam(
                        "ModifiedSecantStep");
                rf = ModifiedSecant.zeroModifiedSecant(f, guess, step, es,
                        iterations, null);
            }
            if (rf.foundRoot()) {
                System.out.println(rf);
            }
        } catch (RuntimeException e) {
            System.out.println("Malformed command");
        }
    }

    private static void defineFunction(String[] parts) {
        String funName = "";
        ArrayList<String> varList = null;
        try {
            int parenSplit = parts[1].indexOf('(');
            funName = parts[1].substring(0, parenSplit);
            String vars[] = parts[1].substring(parenSplit + 1,
                    parts[1].length() - 1).split(",");
            varList = new ArrayList<String>();
            for (int i = 0; i < vars.length; i++) {
                varList.add(vars[i]);
            }
        } catch (RuntimeException e) {
            System.out.println("Malformed command");
            return;
        }

        if (FunctionStore.getStore().hasFunction(funName)) {
            System.out.println("Function " + funName + " already exists");
            return;
        }

        try {
            StringBuilder buildUp = new StringBuilder();
            for (int i = 2; i < parts.length; i++) {
                buildUp.append(parts[i]);
            }
            Function f = TokenizedFunctionFactory.createFunction(
                    buildUp.toString(), varList);
            FunctionStore.getStore().storeFunction(funName, f);
        } catch (RuntimeException e) {
            System.out.println("Error creating function");
        }
    }

    private static void evaluateString(String[] parts) {
        try {
            StringBuilder buildUp = new StringBuilder();
            for (int i = 1; i < parts.length; i++) {
                buildUp.append(parts[i]);
            }
            Function f = TokenizedFunctionFactory.createFunction(
                    buildUp.toString(), null);
            System.out.println(f.evaluate(null));
        } catch (RuntimeException e) {
            System.out.println("Could not evaluate expression: "
                    + e.getMessage());
        }
    }
}
