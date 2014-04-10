/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.control;

import cliente.ClienteManager;
import juego.estructura.GestorJugadores;
import juego.estructura.Jugador;
import juego.estructura.ObjetivoSecreto;

/**
 *
 * @author heril
 */
public class FachadaControl {
    
    public static void enviarChat(String chat) {
        
    }
    
    /**
     * Informa el objetivo secreto del jugador local.
     * @return objetivo secreto.
     */
    public static ObjetivoSecreto getObjetivoSecreto() {
        Jugador jugadorLocal = GestorJugadores.obtenerPorNumero(ClienteManager.getInstance().getIdCliente());
        if (jugadorLocal == null) {
            System.out.println("FachadaControl: JUGADOR LOCAL NO ENCONTRADO");
            return null;
        }
        ObjetivoSecreto objetivoSecreto = jugadorLocal.getObjetivoSecreto();
        if (objetivoSecreto == null) {
            System.out.println("FachadaControl: OBJETIVO SECRETO NO ENCONTRADO");
            return null;
        }
        return objetivoSecreto;
    }
    
}
