/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import com.Accionable;
import com.servidor.EnviarChat;
import logger.Logger;
import servidor.ColaAcciones;

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
    }
    private SalaEspera salaEspera;
    private ConexionServidor conexionServidor;
    private ColaAcciones colaAcciones;
    private servidor.DespachadorAcciones despachadorAcciones;
    private Logger logger;
    private String estado;
    private ControlAlias controlAlias;
    private static String ESTADO_SIN_CONEXION = "Servidor en ejecución";
    private static String ESTADO_CONEXION_ESTABLECIDA = "Servidor no iniciado";
    
    public void registrarSalida(Accionable salida) {
        colaAcciones.pushSalida(salida);
    }
    
    public void registrarEntrada(Accionable entrada) {
        colaAcciones.pushEntrada(entrada);
    }

    public void conectarServidor(String direccionServidor) {
        conexionServidor.conectar(direccionServidor);
        conexionServidor.start();
        despachadorAcciones.start();
        estado = ESTADO_CONEXION_ESTABLECIDA;
    }

    public void desconectarServidor() {
        conexionServidor.desconectar();
        despachadorAcciones.parar();
        estado = ESTADO_SIN_CONEXION;
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

    public void setSalaEspera(SalaEspera salaEspera) {
        this.salaEspera = salaEspera;
    }
    
    public SalaEspera getSalaEspera() {
        return this.salaEspera;
    }
    
    public int getIdentificadorConexion() {
        return this.conexionServidor.getConexionId();
    }
    public ControlAlias getControlAlias() {
        return this.controlAlias;
    }
}
