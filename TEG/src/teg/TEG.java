/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teg;

import cliente.ClienteManager;
import cliente.SalaEspera;

/**
 *
 * @author heril
 */
public class TEG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SalaEspera sala = new cliente.SalaEspera();
        ClienteManager.getInstance().setSalaEspera(sala);
        sala.setLocationRelativeTo(null);
        sala.setVisible(true);
    }
}
