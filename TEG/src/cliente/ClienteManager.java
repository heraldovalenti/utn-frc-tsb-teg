/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import Interfaces.InterfacePrincipal;
import cliente.control.ControlInicio;
import com.Accionable;
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
        juego = Juego.getInstancia();
        
        logger.agregarLoggeable(salaEspera);
    }
    private SalaEspera salaEspera;
    private ConexionServidor conexionServidor;
    private ColaAcciones colaAcciones;
    private servidor.DespachadorAcciones despachadorAcciones;
    private Logger logger;
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

    //METODOS SETTERS AND GETTERS - SIEMPRE AL FINAL DE LA CLASE
    public Logger getLogger() {
        return this.logger;
    }

    public SalaEspera getSalaEspera() {
        return this.salaEspera;
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
     * Informa el identificador de la conexion asignado por el servidor. Este
     * mismo valor es luego utilizado como identificador de los jugadores.
     *
     * @return el identificador de la conexion.
     */
    public int getIdCliente() {
        return this.conexionServidor.getConexionId();
    }

    public InterfacePrincipal getInterfacePrincipal() {
        return this.interfacePrincipal;
    }

    public Jugador getJugador() {
        return jugador;
    }

    /**
     * Metodo para consultar si el jugador indicado es el jugador local.
     * @param idJugador el id del jugador a consultarse.
     * @return true si el id indicado es el del jugador local, false en otro
     * caso.
     */
    public boolean esJugadorLocal(int idJugador) {
        return conexionServidor.getConexionId() == idJugador;
    }
}
