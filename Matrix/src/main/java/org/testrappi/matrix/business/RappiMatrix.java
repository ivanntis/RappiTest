
package org.testrappi.matrix.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testrappi.matrix.dto.Coordinate;

/**
 *
 * @author Ivan Ricardo Peña
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
            switch (fullLine[0]) {
                case UPD:
                    int value = Integer.parseInt(fullLine[4]);
                    Coordinate cordinate = new Coordinate(coordinates.get(0), coordinates.get(1), coordinates.get(2));
                    update(cordinate, value);
                    break;
                case QRY:
                    Coordinate coordinateIni = new Coordinate(coordinates.get(0), coordinates.get(1), coordinates.get(2));
                    Coordinate coordinateFIn = new Coordinate(coordinates.get(3), coordinates.get(4), coordinates.get(5));
                    Coordinate coordinateAct = new Coordinate(coordinates.get(3), coordinates.get(4), coordinates.get(5));
                    int res = query(coordinateIni, coordinateFIn, coordinateAct);
                    System.out.println(res);
                    break;
                default:
                    throw new Throwable("La operacion " + fullLine[0] + " no es permitida ");
            }
        } else {
            throw new Throwable("La operacion " + fullLine[0] + " no es permitida ");
        }

    }

    private void update(Coordinate c, int value) {
        this.matrix[c.getX()][c.getY()][c.getZ()] = value;
        resultList.add(value);
    }

    private int query(Coordinate cIni, Coordinate cFin, Coordinate cAct) {
        int total = 0;
        if (cIni.getZ() <= cAct.getZ()) {
            int posZ = cAct.getZ();
            cAct.setZ(cAct.getZ() - 1);
            total = this.matrix[cAct.getX()][cAct.getY()][posZ] + query(cIni, cFin, cAct);
        } else if (cIni.getY() < cAct.getY()) {
            cAct.setZ(cFin.getZ());
            cAct.setY(cAct.getY() - 1);
            total = query(cIni, cFin, cAct);
        } else if (cIni.getX() < cAct.getX()) {
            cAct.setZ(cFin.getZ());
            cAct.setY(cFin.getY());
            cAct.setX(cAct.getX() - 1);
            total = query(cIni, cFin, cAct);

        }
        return total;

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
            coordinates.add(Integer.parseInt(lineVals[i]) - 1);
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
            throw new Throwable("La dimencion 'M' de la matrix debe ser 1<=M<=1000");
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
