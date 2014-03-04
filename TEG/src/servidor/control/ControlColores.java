/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.control;

import com.servidor.AccionableColoresDisponibles;
import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import servidor.ServerManager;

/**
 * Clase encargada de mantener las asociaciones entre los jugadores y los
 * colores. Provee metodos para asignacion de colores y cambio de colores de un
 * jugador
 *
 * @author heril
 */
public class ControlColores {

    private ControlColores() {
    }
    //Variable para almacenar las asignacion de color existentes.
    //La clave corresponde al id del jugador, que es lo mismo que el
    //identificador de la conexion con el cliente.
    private static HashMap<Integer, Color> asignacionesColor = new HashMap<>();
    //Variable para mantener los colores disponibles para asignacion.
    private static Color[] colores = {
        Color.BLACK, Color.WHITE, Color.RED,
        Color.BLUE, Color.YELLOW, Color.GREEN
    };

    /**
     * Informa el color asignado a un jugador.
     *
     * @param idJugador el id de conexion con el cliente para indicar el
     * jugador.
     * @return el color asignado al jugador si la asignacion existe, null en
     * otro caso.
     */
    public static Color getColorJugador(Integer idJugador) {
        return asignacionesColor.get(idJugador);
    }

    /**
     * Informa los colores que todavia no han sido asignados a ningun jugador.
     *
     * @return un conjunto con los colores sin asignarse a jugadores.
     */
    public static Set<Color> getColoresDisponibles() {
        actualizarAsignaciones();
        Set<Color> disponibles = new HashSet<>();
        Collection<Color> asignaciones = asignacionesColor.values();
        for (Color c : colores) {
            if (!asignaciones.contains(c)) {
                disponibles.add(c);
            }
        }
        return disponibles;
    }

    /**
     * Actualiza las asignaciones de colores a jugadores. La actualizacion se
     * realiza para quitar a los jugadores que se han desconectado de las
     * asignaciones.
     */
    private static void actualizarAsignaciones() {
        Set<Integer> idJugadores = ServerManager.getInstance().getGestorClientes().getIdConexionesEstablecidas();
        Set<Integer> idAsignaciones = asignacionesColor.keySet();
        for (Integer i : idAsignaciones) {
            //Si el id no esta en los id de jugadores conectados, removerlo de 
            //las asociaciones.
            if (!idJugadores.contains(i)) {
                asignacionesColor.remove(i);
            }
        }
    }

    /**
     * Informa si un color esta disponible para asignarse a un jugador.
     *
     * @param color el color a consultarse si esta disponible.
     * @return true si el color esta disponible para asignarse, false en otro
     * caso.
     */
    public static boolean colorDisponible(Color color) {
        return getColoresDisponibles().contains(color);
    }

    /**
     * Asigna un color a un jugador.
     *
     * @param idJugador el identificador del jugador a asignarse el color.
     * @param color el color a asignarse al jugador.
     */
    public static void asignarColor(Integer idJugador, Color color) {
        asignacionesColor.put(idJugador, color);
        informarColoresDisponibles();
    }

    /**
     * Asigna un color automaticamente a un jugador, es decir, sin especificar
     * que color le asigna el primero que haya disponible. Si no hay colores
     * disponibles, no realiza nada.
     *
     * @param idJugador el identificador del jugador a asignarse el color.
     */
    public static void asignarColor(Integer idJugador) {
        Iterator<Color> iter = getColoresDisponibles().iterator();
        if (iter.hasNext()) {
            asignacionesColor.put(idJugador, iter.next());
            informarColoresDisponibles();
        }
    }

    /**
     * Informa de las asignaciones realizadas.
     *
     * @return un set con las asignaciones de colores a los jugadores.
     */
    public static Set<Entry<Integer, Color>> getAsignacionesActivas() {
        actualizarAsignaciones();
        return asignacionesColor.entrySet();
    }
    
    /**
     * Informa a los clientes conectados al servidor de los colores disponibles
     * para seleccionar.
     */
    private static void informarColoresDisponibles() {
        ServerManager.getInstance().registrarSalida(new AccionableColoresDisponibles(getColoresDisponibles()));
    }
}
