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

public class MatrixTestDriver {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Matrix m = MatrixFactory.createMatrix(2, 3);
        m.setElement(1, 1, 1.0);
        m.setElement(1, 2, 2.0);
        m.setElement(1, 3, 3.0);
        m.setElement(2, 1, 4.0);
        m.setElement(2, 2, 5.0);
        m.setElement(2, 3, 6.0);
        
        Matrix t = m.transpose();
        /*Matrix theSquare = t.multiply(m);
        theSquare.rref();*/
        
        System.out.println("Matrix:");
        System.out.println(m);
        System.out.println("Transpose:");
        System.out.println(t);
        System.out.println("MT*M");
        //System.out.println(theSquare);
        m.gaussJordan();
        System.out.println(m.augment(m));
    }

}
