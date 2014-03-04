/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.control;

import com.servidor.AccionableNotificacionInicioJuego;
import java.util.Set;
import servidor.ServerManager;

/**
 *
 * @author heril
 */
public class ControlInicioJuego {

    public static boolean jugadoresListos() {
        Set<Integer> idClientes = ServerManager.getInstance().getGestorClientes().getIdConexionesEstablecidas();
        for (Integer id : idClientes) {
            Boolean aux = ControlEstadoJugadores.getEstadoJugador(id);
            if (aux == null || !aux) {
                return false;
            }
        }
        return true;
    }
    
    public static void iniciarJuego() {
        if (!jugadoresListos()) {
            ServerManager.getInstance().getAdministracionPartida().informarJugadoresNoListos();
            return;
        }
        enviarNotificacionInicioJuego();
    }
    
    private static void enviarNotificacionInicioJuego() {
        ServerManager.getInstance().registrarSalida(new AccionableNotificacionInicioJuego());
    }
    
    private static void inicializarParametrosJuego() {
        
    }
    
    private static void enviarOrdenComienzoJuego() {
        
    }
}
