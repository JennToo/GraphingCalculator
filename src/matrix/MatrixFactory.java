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

public class MatrixFactory {
    public static Matrix createMatrix(int rows, int cols) {
        return new ArrayMatrix(rows, cols);
    }

    public static Matrix createIdentity(int size) {
        Matrix ret = createMatrix(size, size);
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                if (i == j) {
                    ret.setElement(i, i, 1.0);
                } else {
                    ret.setElement(i, j, 0.0);
                }
            }
        }
        return ret;
    }
}
