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

public abstract class Matrix {
    private int rows;
    private int cols;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public abstract double getElement(int row, int col);

    public abstract void setElement(int row, int col, double value);
    
    public abstract Matrix clone();

    /**
     * Multiplies this matrix by other (in that order, this * other) Returns the
     * resulting matrix, or throws exception if the dimensions are incorrect
     */
    public Matrix multiply(Matrix other) {
        if (this.getCols() != other.getRows()) {
            throw new MatrixException("Bad matrix dimensions");
        }
        Matrix toReturn = MatrixFactory.createMatrix(this.getRows(),
                other.getCols());
        for (int i = 1; i <= toReturn.getRows(); i++) {
            for (int j = 1; j <= toReturn.getCols(); j++) {
                double cell = 0.0;
                for (int k = 1; k <= this.getCols(); k++) {
                    cell += this.getElement(i, k) * other.getElement(k, j);
                }
                toReturn.setElement(i, j, cell);
            }
        }
        return toReturn;
    }

    public Matrix transpose() {
        Matrix toReturn = MatrixFactory.createMatrix(getCols(), getRows());
        for(int i = 1; i <= getRows(); i++) {
            for(int j = 1; j <= getCols(); j++) {
                toReturn.setElement(j, i, getElement(i,j));
            }
        }
        return toReturn;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= getRows(); i++) {
            for (int j = 1; j <= getCols(); j++) {
                sb.append(getElement(i, j) + "   ");
            }
            sb.append('\n');
        }
        return sb.toString();
    }
    
    public Matrix augment(Matrix rhs) {
        if(rhs.getRows() != getRows()) {
            throw new MatrixException("Bad dimensions for augment");
        }

        Matrix toReturn = MatrixFactory.createMatrix(getRows(), getCols() + rhs.getCols());
        for(int i = 1; i <= getRows(); i++) {
            for(int j = 1; j <= getCols(); j++) {
                toReturn.setElement(i,j, getElement(i,j));
            }
            
            for(int j = 1; j <= rhs.getCols(); j++) {
                toReturn.setElement(i, j+getCols(), rhs.getElement(i, j));
            }
        }
        return toReturn;
    }
    
    /**
     * Puts the matrix in Gauss-Jordan form
     * Note that this does modify the original matrix.
     */
    public void gaussJordan() {
        //Forward elimination
        for(int row = 1; row <= getRows(); row++) {
            int pivot = findBestPivotRow(row);
            swapRows(row, pivot);
            normalizeRow(row, row);
            
            for(int i = row+1; i <= getRows(); i++) {
                applyRowTransform(row, -getElement(i, row), i);
            }
        }
        
        //Back elimination
        for(int row = getRows(); row > 0; row--) {
            for(int i = row-1; i > 0; i--) {
                applyRowTransform(row, -getElement(i, row), i);
            }
        }
    }
    
    // Helpers for rref:
    private void swapRows(int r1, int r2) {
        for(int i = 1; i <= getCols(); i++) {
            double temp = getElement(r1, i);
            setElement(r1, i, getElement(r2, i));
            setElement(r2, i, temp);
        }
    }
    
    private void normalizeRow(int row, int col) {
        double factor = getElement(row, col);
        for(int i = col; i <= getCols(); i++) {
            setElement(row, i, getElement(row, i) / factor);
        }
    }
    
    private int findBestPivotRow(int depth) {
        double max = Math.abs(getElement(depth, depth));
        int best = depth;
        for(int i = depth+1; i <= getRows(); i++) {
            double test = Math.abs(getElement(i, depth));
            if(test > max) {
                max = test;
                best = i;
            }
        }
        return best;
    }
    
    private void applyRowTransform(int source, double factor, int dest) {
        for(int i = 1; i <= getCols(); i++) {
            double src = getElement(source, i);
            double dst = getElement(dest, i);
            setElement(dest, i, src * factor + dst);
        }
    }
    
}
