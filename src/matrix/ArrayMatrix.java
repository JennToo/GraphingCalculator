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
package matrix;

public class ArrayMatrix extends Matrix {
    private double[] array;
    
    public ArrayMatrix(int rows, int cols) {
        super(rows,cols);
        array = new double[rows*cols];
    }

    public double getElement(int row, int col) {
        //Borrowed from old test
        return array[(row - 1) * getCols() + col - 1];
    }

    public void setElement(int row, int col, double value) {
        array[(row - 1) * getCols() + col - 1] = value;
    }

    public Matrix clone() {
        ArrayMatrix n = new ArrayMatrix(getRows(), getCols());
        n.array = array.clone();
        return n;
    }


}
