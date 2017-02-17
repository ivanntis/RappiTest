
package org.testrappi.matrix;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.testrappi.matrix.business.ProccessInput;

/**
 *
 * @author Ivan Ricardo Peña
 */
public class mainMatrix {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProccessInput pi = null;
        try {            
            
            pi = new ProccessInput("./TEST1.csv");
            pi.getBuildCases();
           
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (Throwable ex) {
            System.err.println(ex.getMessage());
        } finally {
            try {
                pi.close();
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
        
    }
    
}
