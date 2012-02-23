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

import java.util.HashMap;

public class Parameters {
    private static Parameters inst = new Parameters();
    
    private HashMap<String, Double> params;
    
    private Parameters() {
        params = new HashMap<String, Double>();
        params.put("ModifiedSecantStep", 0.01);
        params.put("MaxIterations", 100.0);
        params.put("StoppingThreshold", 0.00001);
    }
    
    public double getParam(String id) {
        return params.get(id);
    }
    
    public boolean hasParam(String id) {
        return params.containsKey(id);
    }
    
    public void setParam(String id, double value) {
        params.put(id, value);
    }
    
    public static Parameters getParams() {
        return inst;
    }
    
}
