/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.control;

import javax.swing.JOptionPane;
import logger.LogItem;
import servidor.AdministracionPartida;
import servidor.DespachadorAcciones;
import servidor.GestorClientes;
import servidor.ServerManager;
import servidor.Servidor;

/**
 * Clase que encapsula la funcionalidad ligada a la ejecicion sel servidor.
 * Cuando el servidor se inicia, se deben iniciar junto con el el monitor de las
 * nuevas conexiones, el despachador de acciones y el gestor de clientes. Esta
 * clase se encarga de gestionar dichas clases y su logica funcional.
 * @author heril
 */
public class ControlEjecucionServidor {
    
    /**
     * Muestra la ventana de administración de partida.
     */
    public static void mostrarVentanaAdministracionPartida() {
        ServerManager.getInstance().getAdministracionPartida().setVisible(true);
        ServerManager.getInstance().getAdministracionPartida().actualizarEstadoServidor();
        ServerManager.getInstance().getLogger().addLogItem(new LogItem("Ventana servidor visible."));
    }
    
    /**
     * Oculta la ventana de administración de partida.
     */
    public static void ocultarVentanaAdministracionPartida() {
        ServerManager.getInstance().getAdministracionPartida().setVisible(false);
        ServerManager.getInstance().getLogger().addLogItem(new LogItem("Ventana servidor ocultada."));
    }
    
    /**
     * Inicia la ejecución del servidor.
     * Junto con el servidor, inicia además el despachador de acciones, el
     * gestor de clientes y el escucha de nuevas conexiones.
     */
    public static void iniciarServidor() {
        AdministracionPartida administracionPartida = ServerManager.getInstance().getAdministracionPartida();
        Servidor servidor = ServerManager.getInstance().getServidor();
        GestorClientes gestorClientes = ServerManager.getInstance().getGestorClientes();
        DespachadorAcciones despachadorAcciones = ServerManager.getInstance().getDespachadorAcciones();
        
        if (servidor.enEjecucion()) {
            ServerManager.getInstance().getLogger().addLogItem(new LogItem("Advertencia: se ha intentado iniciar el servidor y ya se encontraba iniciado."));
            return;
        }
        
        new Thread(servidor).start();
        new Thread(gestorClientes).start();
        new Thread(despachadorAcciones).start();
        
        ServerManager.getInstance().getLogger().addLogItem(
                new LogItem("Servidor iniciado."));
        administracionPartida.actualizarEstadoServidor();
    }
    
    /**
     * Detiene la ejecución del servidor.
     * Junto con el servidor, detiene además el despachador de acciones, el
     * gestor de clientes y el escucha de nuevas conexiones.
     * Verifica ademas si hay conexiones establecidas con los clientes y si es
     * el caso, informa la situación y solicita confirmación.
     */
    public static void detenerServidor() {
        AdministracionPartida administracionPartida = ServerManager.getInstance().getAdministracionPartida();
        Servidor servidor = ServerManager.getInstance().getServidor();
        GestorClientes gestorClientes = ServerManager.getInstance().getGestorClientes();
        DespachadorAcciones despachadorAcciones = ServerManager.getInstance().getDespachadorAcciones();
        
        if (!servidor.enEjecucion()) {
            ServerManager.getInstance().getLogger().addLogItem(new LogItem("Advertencia: se ha intentado detener el servidor y ya se encontraba detenido."));
            return;
        }
        
        if (gestorClientes.conexionesEstablecidas()) {
            int res = JOptionPane.showConfirmDialog(administracionPartida, 
                    "Existen conexiones abiertas. Se perderán dichas conexiones"
                    + "y cualquier avance no guardado del juego."
                    + "¿Está seguro de continuar?", 
                    "Confirmación requerida", JOptionPane.YES_NO_OPTION);
            if (res != JOptionPane.YES_OPTION) {
                return;
            }
        }
        
        servidor.parar();
        gestorClientes.parar();
        despachadorAcciones.parar();
        
        ServerManager.getInstance().getLogger().addLogItem(
                new LogItem("Servidor detenido."));
        administracionPartida.actualizarEstadoServidor();
    }
    
    /**
     * Informa si el servidor está en ejecución o no.
     * @return true si el servidor está en ejecución, false en otro caso.
     */
    public static boolean enEjecucion() {
        Servidor servidor = ServerManager.getInstance().getServidor();
        return servidor.enEjecucion();
    }
    
    /**
     * Informa el estado de ejecución del servidor.
     * @return un mensaje informando el estado de ejecución del servidor.
     */
    public static String estadoServidor() {
        return ServerManager.getInstance().getServidor().getEstado();
    }
    
    /**
     * Devuelve el numero de juego.
     * @return el numero de juego o null si no fue generado todavia.
     */
    public static Integer numeroDeJuego() {
        Servidor servidor = ServerManager.getInstance().getServidor();
        Integer res = servidor.getNumeroDeJuego();
        if (res.intValue() == 0) {
            res = null;
        }
        return res;
    }
    
}
