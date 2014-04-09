/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.control;

import com.servidor.AccionableEstadoJugadores;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import servidor.ServerManager;

/**
 *
 * @author heril
 */
public class ControladorAlias {
    
    private static HashMap<Integer,String> asignacionesAlias = new HashMap<>();
    
    public static boolean aliasDisponible(String aliasSolicitado) {
        actualizarAsignaciones();
        Iterator<String> iter = asignacionesAlias.values().iterator();
        while (iter.hasNext()) {
            String aliasAsignado = iter.next();
            if (aliasAsignado.equals(aliasSolicitado)) {
                return false;
            }
        }
        return true;
    }
    
    public static void asignarAlias(Integer idJugador, String alias) {
        asignacionesAlias.put(idJugador, alias);
        AccionableEstadoJugadores.notificarActualizacionJugadores();
    }
    
    private static void actualizarAsignaciones() {
        Set<Integer> idJugadores = ServerManager.getInstance().getGestorClientes().getIdConexionesEstablecidas();
        Set<Integer> idAsignaciones = new HashSet(asignacionesAlias.keySet());
        for (Integer i : idAsignaciones) {
            //Si el id no esta en los id de jugadores conectados, removerlo de 
            //las asociaciones.
            if (!idJugadores.contains(i)) {
                asignacionesAlias.remove(i);
            }
        }
    }
    
    public static String getAliasJugador(Integer idJugador) {
        return asignacionesAlias.get(idJugador);
    }
    
    public static void purgar() {
        asignacionesAlias = new HashMap<>();
    }
    
    public static void asignarAliasIA(Integer idJugador) {
        int randomNumer = (int)(Math.random() * 1000);
        String aliasIA = "IA-" + randomNumer;
        while (!aliasDisponible(aliasIA)) {
            randomNumer++;
            aliasIA = "IA-" + randomNumer;
        }
        asignacionesAlias.put(idJugador, aliasIA);
    }
}
