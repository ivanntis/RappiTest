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

    public RappiMatrix(int dimension,int maxInstructions) {
        this.maxInstructions = maxInstructions;
        this.matrix = new int[dimension][dimension][dimension];        
        //fillMatrix( this.matrix,dimension-1,dimension-1,dimension,2); //esta linea no es necesaria en java
        System.out.println(Arrays.deepToString(this.matrix)); //Borrar        
        this.resultList = new ArrayList<>();
    }

    public void instruction(String fullLine) {
        if ("".equals("UPDATE")) {
            update(1, 1, 1, 4);
        } else {
            query(1, 1, 1, 2, 2, 2);
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
            fillMatrix(matrixInit, x, y - 1, matrix[0][0].length,valueFill);
        } else if (x >0) {
            fillMatrix(matrixInit, x - 1, matrix[0].length-1, matrix[0][0].length,valueFill);
        }
    }

}
