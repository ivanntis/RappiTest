/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.testrappi.matrix.bussines;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testrappi.matrix.dto.Coordinate;

/**
 *
 * @author Usuario
 */
public class RappiMatrix {

    private int[][][] matrix;
    private List<Integer> resultList;
    private int maxInstructions;
    private int dimension;
    private final String UPD = "UPDATE";
    private final String QRY = "QUERY";

    public RappiMatrix(int dimension, int maxInstructions) throws Throwable {
        setDimension(dimension);
        setMaxInstructions(maxInstructions);
        this.matrix = new int[dimension][dimension][dimension];
        //fillMatrix( this.matrix,dimension-1,dimension-1,dimension,2); //esta linea no es necesaria en java

        this.resultList = new ArrayList<>();

    }

    public void instruction(String[] fullLine) throws Throwable {
        List<Integer> coordinates = new ArrayList<>();
        if (isValidCoordinates(fullLine, coordinates)) {
            if (fullLine[0].equals(UPD)) {
                int value = Integer.parseInt(fullLine[4]);
                Coordinate cordinate = new Coordinate(coordinates.get(0), coordinates.get(1), coordinates.get(2));
                update(cordinate, value);

            } else if (fullLine[0].equals(QRY)) {
                Coordinate coordinateIni = new Coordinate(coordinates.get(0), coordinates.get(1), coordinates.get(2));
                Coordinate coordinateFin = new Coordinate(coordinates.get(3), coordinates.get(4), coordinates.get(5));
                query(coordinateIni,coordinateFin,coordinateFin);

            } else {
                throw new Throwable("La operacion " + fullLine[0] + " no es permitida ");
            }
        } else {
            throw new Throwable("La operacion " + fullLine[0] + " no es permitida ");
        }

    }

    //private void update(int x, int y, int z, int value) {
    private void update(Coordinate c, int value) {
        this.matrix[c.getX()][c.getY()][c.getZ()] = value;
        resultList.add(value);
        //System.out.println(value);
    }

    //private int query(int x1, int y1, int z1, int x2, int y2, int z2) {
    private int query(Coordinate cIni,Coordinate cFin,Coordinate cAct) {
        //resultList.add(this.matrix[x1][y1][z1] + this.matrix[x2][y2][z2]);
        //System.out.println(this.matrix[x1][y1][z1] + this.matrix[x2][y2][z2]);
        if (cIni.getZ()> cAct.getZ()) {
           return   this.matrix[cFin.getX()][cFin.getY()][cFin.getZ()]+query(cIni,cFin,cAct) ;
        } /*else if (y1 > y2) {
            return   this.matrix[x2][y2][z2]+query(x1,y1,z1,x1,y1,z1-1) ; 
        }else if (x1 > x2) {

        }*/

        return 0;
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

    private boolean isValidCoordinates(String[] lineVals, List<Integer> coordinates) {
        int sizeLine = lineVals.length;

        if (sizeLine == 5) {
            sizeLine = lineVals.length - 1;
        } else if (sizeLine != 7) {
            return false;
        }
        for (int i = 1; i < sizeLine; i++) {
            int arrayPosition = i - 1;
            coordinates.add(Integer.parseInt(lineVals[i]));
            if (coordinates.get(arrayPosition) < 0 || coordinates.get(arrayPosition) > getDimension()) {
                return false;
            }
        }
        return true;
    }

    public int getMaxInstructions() {
        return maxInstructions;
    }

    public void setMaxInstructions(int maxInstructions) throws Throwable {
        if (maxInstructions >= 1 && maxInstructions <= 1000) {
            this.maxInstructions = maxInstructions;
        } else {
            throw new Throwable("La dimencion 'N' de la matrix debe ser 1<=M<=100");
        }
        this.maxInstructions = maxInstructions;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) throws Throwable {
        if (dimension >= 1 && dimension <= 100) {
            this.dimension = dimension;
        } else {
            throw new Throwable("La dimencion 'N' de la matrix debe ser 1<=N<=100");
        }
    }

}
