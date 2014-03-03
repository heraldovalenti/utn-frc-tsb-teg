/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import Interfaces.InterfacePrincipal;
import cliente.control.ControlAlias;
import cliente.control.ControlInicio;
import com.Accionable;
import com.servidor.EnviarChat;
import juego.Juego;
import juego.estructura.Jugador;
import logger.Logger;
import servidor.ColaAcciones;
import servidor.DespachadorAcciones;

/**
 *
 * @author heril
 */
public class ClienteManager {

    private static ClienteManager instance = null;

    public static ClienteManager getInstance() {
        if (instance == null) {
            instance = new ClienteManager();
        }
        return instance;
    }

    private ClienteManager() {
        colaAcciones = new ColaAcciones();
        logger = new Logger();
        conexionServidor = new ConexionServidor(colaAcciones);
        despachadorAcciones = new servidor.DespachadorAcciones(colaAcciones);
        salaEspera = new SalaEspera();
        controlAlias = new ControlAlias();
        juego = Juego.getInstancia();
    }
    private SalaEspera salaEspera;
    private ConexionServidor conexionServidor;
    private ColaAcciones colaAcciones;
    private servidor.DespachadorAcciones despachadorAcciones;
    private Logger logger;
    private String estado;
    private ControlAlias controlAlias;
    private Juego juego;
    private InterfacePrincipal interfacePrincipal;
    private Jugador jugador;
    
    /**
     * Metodo para iniciar la aplicacion.
     */
    public void init() {
        new ControlInicio().iniciar();
    }
    
    /**
     * Registra una salida en la cola de acciones.
     * @param salida la salida a registrarse en la cola.
     */
    public void registrarSalida(Accionable salida) {
        colaAcciones.pushSalida(salida);
    }
    
    /**
     * Registra una entrada en la cola de acciones.
     * @param entrada la entrada a registrarse en la cola.
     */
    public void registrarEntrada(Accionable entrada) {
        colaAcciones.pushEntrada(entrada);
    }

    public void enviarChat(String chat) {
        EnviarChat enviarChat = new EnviarChat(chat);
        despachadorAcciones.ingresarSalida(enviarChat);
    }

    public void recibirChat(String chat) {
        salaEspera.mostrarChat(chat);
    }
    
    public void establecerIdentificadorConexion(int id) {
        conexionServidor.setConexionId(id);
    }

    //METODOS SETTERS AND GETTERS - SIEMPRE AL FINAL DE LA CLASE
    public Logger getLogger() {
        return this.logger;
    }

    public String getEstado() {
        return this.estado;
    }
    
    public SalaEspera getSalaEspera() {
        return this.salaEspera;
    }
    public ControlAlias getControlAlias() {
        return this.controlAlias;
    }

    public ConexionServidor getConexionServidor() {
        return conexionServidor;
    }

    public DespachadorAcciones getDespachadorAcciones() {
        return despachadorAcciones;
    }

    public Juego getJuego() {
        return juego;
    }
    
    /**
     * Informa el identificador de la conexion asignado por el servidor.
     * Este mismo valor es luego utilizado como identificador de los jugadores.
     * @return el identificador de la conexion.
     */
    public int getIdCliente() {
        return this.conexionServidor.getConexionId();
    }

//    public Jugador getJugador() {
//        return jugador;
//    }
    
    public InterfacePrincipal getInterfacePrincipal() {
        return this.interfacePrincipal;
    }
}
