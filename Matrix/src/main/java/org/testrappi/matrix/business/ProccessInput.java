
package org.testrappi.matrix.business;

import com.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ivan Ricardo Peña
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
        //this.csvList = getRowsInput();

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
        RappiMatrix rappi = null;
        while ((nline = reader.readNext()) != null) {
            if (nline.length == 1 && caseln == 0) { //indica cuantos casos existen
                t = Integer.parseInt(nline[0]);
            } else if (nline.length == 2) { //indica es un nuevo caso
                caseln++;
                n = Integer.parseInt(nline[0]);
                m = Integer.parseInt(nline[1]);
                rappi = new RappiMatrix(n,m);
            } else if (nline.length > 2) { //indica la operacion.
                rappi.instruction(nline);
            } else {
                throw new Throwable("El formato del archivo no cumple con lo requerimientos");
            }            
            
            if(caseln>t){
                throw new Throwable("Se omiten los demas casos por que exeden las "+t+" Solicitadas");
            }

        }

    }
    

    public void close() throws IOException {
        this.file.close();
    }

}
