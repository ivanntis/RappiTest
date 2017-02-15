/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.testrappi.matrix.bussines;

import com.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Usuario
 */
public class ProccessInput {

    private CSVReader reader;
    private List<String[]> csvList;
    private Map<Integer, List<String[]>> mapCases;
    FileReader file;

    public ProccessInput(String path) throws FileNotFoundException, IOException {
        //"C:/Users/Usuario/Documents/inputTest.csv"
        this.mapCases = new HashMap<>();
        this.file = new FileReader(path);
        this.reader = new CSVReader(this.file, ' ');
        this.csvList = getRowsInput();

    }

    public List<String[]> getRowsInput() throws IOException {
        this.reader.toString();
        return this.reader.readAll();
    }

    public void ProcessCases() throws Throwable {
        int t = Integer.parseInt(this.csvList.get(0)[0]);

        if (t <= 50) {

        } else {
            throw new Throwable("La condicion indica que no puede tener mas de 50 casos");

        }

    }

    public void getBuildCases() throws IOException, Throwable {
        //   this.mapCases;
        String[] nline;
        int caseln = 0;
        int n = 0; //indica tamaño de las dimensiones del array
        int m = 0; //indica numero de operaciones de un caso
        int t = 0; //indica cantidad de casos
        while ((nline = reader.readNext()) != null) {
            if (nline.length == 1 && caseln == 0) { //indica cuantos casos existen
                t = Integer.parseInt(nline[0]);
            } else if (nline.length == 2) { //indica es un nuevo caso
                caseln++;
                n = Integer.parseInt(nline[0]);
                m = Integer.parseInt(nline[1]);
                if(n<=100 && m <=1000){
                    RappiMatrix rappi = new RappiMatrix(n,m);
                    
                }else{
                    throw new Throwable("La cantidad de operaciones de un caso no puede pasar los 1000, ni la cantidad de dimensiones 100");
                }                
            } else if (nline.length > 2) { //indica la operacion.
                if(nline[0].equals("UPDATE")){
                    
                }else if(nline[0].equals("QUERY")){
                    
                }else {
                    throw new Throwable("La operacion "+nline[0]+" no es permitida ");
                }
            } else {
                throw new Throwable("El formato del archivo no cumple con lo requerimientos");
            }            
                 

        }

    }
    
    private boolean isValidUpdate(String[] lineVals){
        if(lineVals.length>5 ){
            return false;
        }else{
            for(String val : lineVals){
                int position = Integer.parseInt(val);
            }
        }
        
    }

    public void close() throws IOException {
        this.file.close();
    }

}
