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
package threads;

import roots.RootFindResults;
import roots.Secant;
import functions.Function;
import gui.Graph;

public class SecantThread extends CalculationThread {
    private Graph g;
    private Function f;
    private double high;
    private double low;
    private int max;
    private double stop;
    private RootFindResults r;    
    
    public SecantThread(Function f, double high, double low, double stop, int max, Graph g) {
        this.f = f;
        this.high = high;
        this.low = low;
        this.max = max;
        this.stop = stop;
        this.g = g;                
    }
    
    public void run() {
        r = Secant.zeroSecant(f, high, low, stop, max, g);
    }
    
    public RootFindResults getResults() {
        return r;
    }
    
    public boolean hasResults() {
        return (r != null);
    }

    public String getResultsString() {
        return r.toString();
    }
}
