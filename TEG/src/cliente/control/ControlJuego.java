/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.control;

import Interfaces.Presentacion;
import cliente.ClienteManager;

/**
 * Clase responsable de llevar a cabo las acciones de inicio de juego.
 * Estas son basicamente incializar las interfaces graficas de acuerdo al inicio
 * del juego, controlar los parametros de inicializacion que vienen desde el
 * servidor y actualizar localmente el modelo para que este disponible para
 * comenzar el juego.
 * @author heril
 */
public class ControlJuego {

    /**
     * Informa mediante una cadena el identificador del juego.
     *
     * @return el identificador de la conexion.
     */
    public static String stringIdentificadorJuego() {
        String res = "No definido";
        if (ControlConexion.conectado()) {
            res = ClienteManager.getInstance().getJuego().getIdJuego() + "";
        }
        return res;
    }

    public static void notificacionInicioJuego() {
        ClienteManager.getInstance().getSalaEspera().setVisible(false);
        Presentacion.main(null);
    }
}
