/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import com.cliente.RecibirNuevoCliente;
import com.servidor.RecibirNuevoClienteEnServidor;
import logger.LogItem;
import logger.Logger;

/**
 *
 * @author heril
 */
public class ServerManager {

    private static ServerManager instance = null;

    public static ServerManager getInstance() {
        if (instance == null) {
            instance = new ServerManager();
        }
        return instance;
    }

    private ServerManager() {
        colaAcciones = new ColaAcciones();
        gestorClientes = new GestorClientes(colaAcciones);
        despachadorAcciones = new DespachadorAcciones(colaAcciones);
        servidor = new Servidor(gestorClientes);
        logger = new Logger();
    }
    private GestorClientes gestorClientes;
    private ColaAcciones colaAcciones;
    private Servidor servidor;
    private DespachadorAcciones despachadorAcciones;
    private Logger logger;
    private String estado;
    private static String ESTADO_EN_EJECUCION = "Servidor en ejecución";
    private static String ESTADO_NO_INICIADO = "Servidor no iniciado";

    /**
     * Inicia el servidor para permitir conexiones, el gestor de clientes y el
     * despachador de acciones.
     */
    public void iniciarServidor() {
        servidor.start();
        gestorClientes.start();
        despachadorAcciones.start();
        estado = ESTADO_EN_EJECUCION;
        ServerManager.getInstance().getLogger().addLogItem(
                new LogItem("Servidor iniciado"));
    }

    /**
     * Interrumpe el servidor de conexiones a clientes, el gestor de clientes y
     * el despachador de acciones.
     */
    public void interrumpirServidor() {
        servidor.parar();
        gestorClientes.parar();
        despachadorAcciones.parar();
        estado = ESTADO_NO_INICIADO;
        ServerManager.getInstance().getLogger().addLogItem(
                new LogItem("Servidor iniciado"));
    }

    /**
     * Informa de la conexion de un nuevo cliente localmente al servidor y a los
     * clientes ya conectados al servidor.
     *
     * @param identificadorCliente el identificador del cliente nuevo.
     */
    public void informarNuevoCliente(int identificadorCliente) {
        despachadorAcciones.ingresarEntrada(new RecibirNuevoClienteEnServidor(identificadorCliente));
        despachadorAcciones.ingresarSalida(new RecibirNuevoCliente(identificadorCliente));
    }

    //METODOS SETTERS AND GETTERS - SIEMPRE AL FINAL DE LA CLASE
    public Logger getLogger() {
        return this.logger;
    }

    public String getEstado() {
        return this.estado;
    }
}
