/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.testrappi.matrix.bussines;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class RappiMatrix {

    private int[][][] matrix;
    private List<Integer> resultList;
    private int maxInstructions;
    private int dimension;

    public RappiMatrix(int dimension, int maxInstructions) throws Throwable {
        setDimension(dimension);
        setMaxInstructions(maxInstructions);
        this.maxInstructions = maxInstructions;
        this.matrix = new int[dimension][dimension][dimension];
        //fillMatrix( this.matrix,dimension-1,dimension-1,dimension,2); //esta linea no es necesaria en java
        System.out.println(Arrays.deepToString(this.matrix)); //Borrar        
        this.resultList = new ArrayList<>();
    }

    public void instruction(String[] fullLine) throws Throwable {
        if (fullLine[0].equals("UPDATE") && isValidUpdate(fullLine)) {
            update(1, 1, 1, 4);

        } else if (fullLine[0].equals("QUERY")) {
            query(1, 1, 1, 2, 2, 2);

        } else {
            throw new Throwable("La operacion " + fullLine[0] + " no es permitida ");
        }

    }

    private void update(int x, int y, int z, int value) {
        this.matrix[x][y][z] = value;
        resultList.add(value);
    }

    private void query(int x1, int y1, int z1, int x2, int y2, int z2) {
        resultList.add(this.matrix[x1][y1][z1] + this.matrix[x2][y2][z2]);
    }

    @Override
    public String toString() {

        return resultList.toString();
    }

    private void fillMatrix(int[][][] matrixInit, int x, int y, int z, int valueFill) {
        if (y >= 0) {
            int[] arrayTemp = new int[z];
            Arrays.fill(arrayTemp, valueFill);
            matrixInit[x][y] = arrayTemp;
            fillMatrix(matrixInit, x, y - 1, matrix[0][0].length, valueFill);
        } else if (x > 0) {
            fillMatrix(matrixInit, x - 1, matrix[0].length - 1, matrix[0][0].length, valueFill);
        }
    }
    

    private boolean isValidUpdate(String[] lineVals) {
        if (lineVals.length == 5) {
            for (int i = 1; i < lineVals.length; i++) {
                int arrayPosition= Integer.parseInt(lineVals[i]);
                if(arrayPosition <0 || arrayPosition>getDimension()){
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public int getMaxInstructions() {
        return maxInstructions;
    }

    public void setMaxInstructions(int maxInstructions) throws Throwable {
        if (maxInstructions >=1 && maxInstructions <= 1000)
            this.maxInstructions = maxInstructions;
        else {
            throw new Throwable("La dimencion 'N' de la matrix debe ser 1<=M<=100");
        }
        this.maxInstructions = maxInstructions;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) throws Throwable {
        if (dimension >=1 && dimension <= 100)
            this.dimension = dimension;
        else {
            throw new Throwable("La dimencion 'N' de la matrix debe ser 1<=N<=100");
        }
    }
    
    

}
