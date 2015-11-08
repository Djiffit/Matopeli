/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matopeli;

import java.io.IOException;
import matopeli.Käyttis.Pääikkuna;

/**
 *
 * @author Konsta
 */
public class Matopeli {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Pääikkuna ikkuna = new Pääikkuna();
        ikkuna.run();
    }
    
}
