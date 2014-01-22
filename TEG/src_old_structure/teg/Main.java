/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teg;

import vista.servidor.AdministracionPartida;

/**
 *
 * @author heril
 */
public class Main {
    
    public static void main (String args[]) {
        AdministracionPartida salaEspera = AdministracionPartida.getInstance();
        salaEspera.setVisible(true);
    }
    
}
