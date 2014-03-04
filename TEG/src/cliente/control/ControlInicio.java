/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.control;

import cliente.ClienteManager;
import cliente.SalaEspera;

/**
 * Clase encargada de ejecutar la inicializacion del sistema. Se encarga de
 * mostrar la ventana de la sala de espera y de ejecutar los procedimientos de
 * cargar el alias del jugador y asegurarse de que sea valido.
 *
 * @author heril
 */
public class ControlInicio {

    public ControlInicio() {
    }

    /**
     * El metodo ejecuta todas las acciones que se deben ejecutar al inicio
     * de la aplicacion cliente.
     * Eventualmente, si se quieren agregar acciones, aqui se deberian agregar.
     */
    public void iniciar() {
        mostrarSalaEspera();
        solicitarAlias();
        actualizarEstadoConexion();
        actualizarAsignacionColor();
    }

    /**
     * Muestra la ventana de la sala de espera.
     */
    private void mostrarSalaEspera() {
        SalaEspera salaEspera = ClienteManager.getInstance().getSalaEspera();
        salaEspera.setLocationRelativeTo(null);
        salaEspera.setVisible(true);
    }

    /**
     * Solicita un nuevo alias.
     */
    private void solicitarAlias() {
        ClienteManager.getInstance().getControlAlias().solicitarAlias();
    }
    
    /**
     * Actualiza el estado de la conexión de la ventana de la sala de espera.
     */
    private void actualizarEstadoConexion() {
        ClienteManager.getInstance().getSalaEspera().actualizarEstadoConexion();
    }
    
    private void actualizarAsignacionColor() {
        ClienteManager.getInstance().getSalaEspera().actualizarAsignacionColor();
    }
}
