/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import com.Accionable;
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
        administracionPartida = new AdministracionPartida();
        colaAcciones = new ColaAcciones();
        gestorClientes = new GestorClientes(colaAcciones);
        despachadorAcciones = new DespachadorAcciones(colaAcciones);
        servidor = new Servidor(gestorClientes);
        logger = new Logger();
        logger.agregarLoggeable(administracionPartida);
    }
    private AdministracionPartida administracionPartida;
    private GestorClientes gestorClientes;
    private ColaAcciones colaAcciones;
    private Servidor servidor;
    private DespachadorAcciones despachadorAcciones;
    private Logger logger;

    //METODOS SETTERS AND GETTERS - SIEMPRE AL FINAL DE LA CLASE
    public Logger getLogger() {
        return this.logger;
    }

    public AdministracionPartida getAdministracionPartida() {
        return this.administracionPartida;
    }

    public GestorClientes getGestorClientes() {
        return gestorClientes;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public DespachadorAcciones getDespachadorAcciones() {
        return despachadorAcciones;
    }

    /**
     * Registra una salida en la cola de acciones.
     *
     * @param salida la salida a registrarse en la cola.
     */
    public void registrarSalida(Accionable salida) {
        colaAcciones.pushSalida(salida);
    }

    /**
     * Registra una entrada en la cola de acciones.
     *
     * @param entrada la entrada a registrarse en la cola.
     */
    public void registrarEntrada(Accionable entrada) {
        colaAcciones.pushEntrada(entrada);
    }

}
