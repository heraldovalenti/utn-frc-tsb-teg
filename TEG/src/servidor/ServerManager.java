/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import com.cliente.RecibirChat;
import com.servidor.RecibirNuevoClienteEnServidor;
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
    }
    private AdministracionPartida administracionPartida;
    private GestorClientes gestorClientes;
    private ColaAcciones colaAcciones;
    private Servidor servidor;
    private DespachadorAcciones despachadorAcciones;
    private Logger logger;

    public void informarNuevoCliente(int identificadorCliente) {
        despachadorAcciones.ingresarEntrada(new RecibirNuevoClienteEnServidor(identificadorCliente, gestorClientes));
    }

    public void procesarChat(String chat) {
        RecibirChat aEnviarseAlCliente = new RecibirChat(chat);
        despachadorAcciones.ingresarSalida(aEnviarseAlCliente);
    }

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
    
    
}
