/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.control;

import cliente.ClienteManager;
import com.servidor.SolicitudColor;
import java.awt.Color;
import java.util.Set;
import logger.LogItem;

/**
 *
 * @author heril
 */
public class ControlColor {

    private static Set<Color> coloresDisponibles = null;
    private static Color colorAsignado = null;

    public void invalidar() {
        colorAsignado = null;
        coloresDisponibles = null;
    }
    
    /**
     * Solicita un color automaticamente.
     */
    public void solicitarColor() {
        SolicitudColor solicitud = new SolicitudColor(ClienteManager.getInstance().getIdCliente(), null);
        ClienteManager.getInstance().registrarSalida(solicitud);
        ClienteManager.getInstance().getLogger().addLogItem(new LogItem("Solicitando color..."));
    }

    /**
     * Solicita el color indicado.
     *
     * @param color el color solicitado.
     */
    public void solicitarColor(Color color) {
        SolicitudColor solicitud = new SolicitudColor(ClienteManager.getInstance().getIdCliente(), color);
        ClienteManager.getInstance().registrarSalida(solicitud);
        ClienteManager.getInstance().getLogger().addLogItem(new LogItem("Solicitando color..."));
    }

    /**
     * Asigna el color indicado si el jugador de la asignacion es el mismo del
     * cliente, en caso contrario no realiza nada.
     *
     * @param idJugador el identificador del jugador de la asignacion.
     * @param color el color a asignarse al jugador.
     */
    public void asignarColor(int idJugador, Color color) {
        int idCliente = ClienteManager.getInstance().getIdCliente();
        if (idJugador == idCliente) {
            colorAsignado = color;
            ClienteManager.getInstance().getSalaEspera().actualizarAsignacionColor();
            ClienteManager.getInstance().getLogger().addLogItem(new LogItem("Color asignado: " + this.getNombreColor(color)));
        } else {
            ClienteManager.getInstance().getLogger().addLogItem(new LogItem("Color descartado (otra conexion)"));
        }
    }

    /**
     * Metodo para ejecutar en caso de que la asignacion del color sea erronea.
     * El color que se solicito ya se asigno a otro jugador, caso en el que se
     * informa de la situacion y se solicita la asignacion de un color
     * automaticamente.
     */
    public void errorEnAsignacion() {
        ClienteManager.getInstance().getSalaEspera().informarAsignacionFallida();
        solicitarColor();
    }

    /**
     * Actualiza localmente los colores disponibles para seleccionarse.
     *
     * @param actualizacionColoresDisponibles los colores disponibles para
     * seleccionarse.
     */
    public void actualizarColoresDisponibles(Set<Color> actualizacionColoresDisponibles) {
        coloresDisponibles = actualizacionColoresDisponibles;
    }

    /**
     * Informa el nombre del color en español.
     *
     * @param color el color del cual se quiere el nombre
     * @return el nombre del color.
     */
    public String getNombreColor(Color color) {
        String res = null;
        if (color.equals(Color.BLACK)) {
            res = "Negro";
        }
        if (color.equals(Color.WHITE)) {
            res = "Blanco";
        }
        if (color.equals(Color.BLUE)) {
            res = "Azul";
        }
        if (color.equals(Color.GREEN)) {
            res = "Verde";
        }
        if (color.equals(Color.YELLOW)) {
            res = "Amarillo";
        }
        if (color.equals(Color.RED)) {
            res = "Rojo";
        }
        return res;
    }

    /**
     * Informa los colores disponibles para seleccionarse.
     *
     * @return los colores disponibles para seleccionarse.
     */
    public Set<Color> getColoresDisponibles() {
        return coloresDisponibles;
    }

    public String getColorAsignado() {
        if (colorAsignado == null) {
            return "No definido";
        } else {
            return getNombreColor(colorAsignado);
        }
    }
}
