/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.control;

import cliente.ClienteManager;
import cliente.ConexionServidor;
import cliente.SalaEspera;
import com.cliente.AccionableCerrarConexion;
import java.io.IOException;
import javax.swing.JOptionPane;
import logger.LogItem;
import servidor.DespachadorAcciones;

/**
 * Clase encargada de iniciar la conexion al servidor. Cuando se inicia una
 * conexion al servidor, se deben iniciar otros procedimientos al mismo tiempo:
 * se debe iniciar el monitor de la conexion (que en este caso resulta la misma
 * clase ConexionServidor) y el despachador de acciones que procesara las
 * entradas/salidas resultantes de la comunicación entre el cliente y el
 * servidor, y obviamente cuando la conexion se finaliza, se deben finalizar
 * estos procedimientos tambien. Esta clase, encapsula ese comportamiento.
 *
 * @author heril
 */
public class ControlConexion {
    
    /**
     * Informa mediante una cadena el identificador de la conexion con el
     * servidor.
     * @return el identificador de la conexion.
     */
    public static String stringIdentificadorConexion() {
        String res = "No definido";
        if (conectado()) {
            res = ClienteManager.getInstance().getConexionServidor().getConexionId() + "";
        }
        return res;
    }
    
    /**
     * Informa mediante una cadena el estado de la conexión.
     * @return el estado de la conexión.
     */
    public static String stringEstadoConexion() {
        return ClienteManager.getInstance().getConexionServidor().getEstado();
    }
    
    /**
     * Informa si se esta conectado al servidor o no.
     * @return true si esta conectado al servidor, false en otro caso.
     */
    public static boolean conectado() {
        return ClienteManager.getInstance().getConexionServidor().conexionEstablecida();
    }
    
    /**
     * Inicia la conexión con el servidor y el monitor de la conexión y el
     * despachador de acciones.
     *
     * @param direccionServidor la direccion del servidor.
     */
    public static void conectarServidor(String direccionServidor) {
        ConexionServidor conexionServidor = ClienteManager.getInstance().getConexionServidor();
        DespachadorAcciones despachadorAcciones = ClienteManager.getInstance().getDespachadorAcciones();
        SalaEspera salaEspera = ClienteManager.getInstance().getSalaEspera();
        //verificar que la direccion del servidor sea valida...
        /*if (!direccionValida(direccionServidor)) {
            JOptionPane.showMessageDialog(salaEspera, "La dirección IP ingresada es inválida.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }*/
        //verificar que la conexion no este iniciada ya...
        if (conexionServidor.conexionEstablecida()) {
            ClienteManager.getInstance().getLogger().addLogItem(new LogItem("Advertencia: Se ha intentado realizar una conexión y la conexión ya se encontraba establecida."));
            return;
        }
        try {
            conexionServidor.conectar(direccionServidor);
            
            Thread t1 = new Thread(conexionServidor,"Hilo - ConexionServidor");
            conexionServidor.setHilo(t1);
            Thread t2 = new Thread(despachadorAcciones,"Hilo - DespachadorAcciones(Cliente)");
            despachadorAcciones.setHilo(t2);
            t1.start();
            t2.start();
            //new Thread(conexionServidor).start();
            //new Thread(despachadorAcciones).start();
        } catch (IOException ex) {
            ClienteManager.getInstance().getLogger().addLogItem(new LogItem("Error estableciendo conexión con el servidor.", ex));
            JOptionPane.showMessageDialog(salaEspera, "Error estableciendo conexión con el servidor:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Verifica que la direccion pasada como parametro sea una direccion valida.
     * Estos es, que sea la cadena <localhost> o una IP valida.
     *
     * @param direccion la direccion a verificarse
     * @return true si la direccion es valida, false en otro caso.
     */
    private static boolean direccionValida(String direccion) {
        if (direccion.equalsIgnoreCase("localhost")) {
            return true;
        }
        String[] octetosString = direccion.split(".");
        if (octetosString.length != 4) {
            return false;
        }
        int oct1, oct2, oct3, oct4;
        try {
            oct1 = Integer.parseInt(octetosString[0]);
            oct2 = Integer.parseInt(octetosString[1]);
            oct3 = Integer.parseInt(octetosString[2]);
            oct4 = Integer.parseInt(octetosString[3]);
        } catch (Exception ex) {
            return false;
        }
        if (oct1 < 0 || oct1 > 255 || 
                oct2 < 0 || oct2 > 255 || 
                oct3 < 0 || oct3 > 255 || 
                oct4 < 0 || oct4 > 255) {
            return false;
        }
        return true;
    }

    /**
     * Finaliza la conexión con el servidor y detiene los procedimientos 
     * asociados a la conexión con el servidor y el despachador de acciones.
     */
    public static void desconectarServidor() {
        ConexionServidor conexionServidor = ClienteManager.getInstance().getConexionServidor();
        DespachadorAcciones despachadorAcciones = ClienteManager.getInstance().getDespachadorAcciones();
        SalaEspera salaEspera = ClienteManager.getInstance().getSalaEspera();
        //verificar que hay una conexion iniciada...
        if (!conexionServidor.conexionEstablecida()) {
            ClienteManager.getInstance().getLogger().addLogItem(new LogItem("Advertencia: Se ha intentado finalizar una conexión y la conexión ya se encontraba finalizada."));
            return;
        }
        try {
            conexionServidor.desconectar();
            despachadorAcciones.parar();
            
            ControlAlias ca = new ControlAlias();
            ControlColor cc = new ControlColor();
            cc.invalidar();
            ca.invalidar();
            salaEspera.actualizarEstadoConexion();
            salaEspera.actualizarAlias();
            salaEspera.actualizarAsignacionColor();
            ClienteManager.getInstance().getLogger().addLogItem(new LogItem("Conexión finalizada."));
        } catch (IOException ex) {
            ClienteManager.getInstance().getLogger().addLogItem(new LogItem("Error finalizando conexión con el servidor.", ex));
            JOptionPane.showMessageDialog(salaEspera, "Error finalizando conexión con el servidor:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void solicitarCierreConexion() {
        AccionableCerrarConexion cc = new AccionableCerrarConexion(ClienteManager.getInstance().getConexionServidor().getConexionId(),AccionableCerrarConexion.DESCONEXION_MANUAL);
        ClienteManager.getInstance().registrarSalida(cc);
        ClienteManager.getInstance().getLogger().addLogItem(new LogItem("Cierre de conexión solicitado..."));
    }
    
    public static void cerrarConexion(int idConexion, String razon) {
        int idConexionServidor = ClienteManager.getInstance().getConexionServidor().getConexionId();
        if (idConexionServidor == idConexion || idConexion == -1) {
            desconectarServidor();
            ClienteManager.getInstance().getSalaEspera().informarCierreConexion(razon);
        }
    }
}
