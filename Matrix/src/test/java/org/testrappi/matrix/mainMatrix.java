/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.testrappi.matrix;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.testrappi.matrix.bussines.ProccessInput;
import org.testrappi.matrix.bussines.RappiMatrix;

/**
 *
 * @author Usuario
 */
public class mainMatrix {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProccessInput pi = null;
        try {            
            pi = new ProccessInput("/Users/ivanps/Documents/RappiTest/Matrix/TEST1.csv");
            pi.getBuildCases();
           // pi.getRowsInput();
           // RappiMatrix rm = new RappiMatrix(2,2);
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            Logger.getLogger(mainMatrix.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Throwable ex) {
            Logger.getLogger(mainMatrix.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pi.close();
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
        
    }
    
}
