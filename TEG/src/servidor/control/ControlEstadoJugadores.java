/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.control;

import com.servidor.AccionableEstadoJugadores;
import java.util.HashMap;
import java.util.Map;
import logger.LogItem;
import servidor.ServerManager;

/**
 *
 * @author heril
 */
public class ControlEstadoJugadores {
    
    private static Map<Integer,Boolean> jugadoresListos = new HashMap<>();
    
    public static void jugadorListo(int idJugador, boolean listo) {
        jugadoresListos.put(idJugador, listo);
        if (listo) {
            String aliasJugador = ControladorAlias.getAliasJugador(idJugador);
            ServerManager.getInstance().getLogger().addLogItem(new LogItem("El jugador <" + aliasJugador + "> está listo."));            
        }
        AccionableEstadoJugadores.notificarActualizacionJugadores();
    }
    
    public static Boolean getEstadoJugador(int idJugador) {
        return jugadoresListos.get(idJugador);
    }
    
    public static void purgar() {
        jugadoresListos = new HashMap<>();
    }
    
}
