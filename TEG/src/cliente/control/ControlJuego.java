/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.control;

import cliente.ClienteManager;

/**
 *
 * @author heril
 */
public class ControlJuego {
    
    /**
     * Informa mediante una cadena el identificador del juego.
     * @return el identificador de la conexion.
     */
    public static String stringIdentificadorJuego() {
        String res = "No definido";
        if (ControlConexion.conectado()) {
            res = ClienteManager.getInstance().getJuego().getIdJuego() + "";
        }
        return res;
    }
    
}
