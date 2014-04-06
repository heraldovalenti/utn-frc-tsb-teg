/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.control;

import com.cliente.AccionableCerrarConexion;
import com.servidor.AccionableEstadoJugadores;
import com.servidor.AcionableIniciarConexion;
import servidor.ConexionCliente;
import servidor.ServerManager;

/**
 * Clase responsable de llevar al cabo el procedimiento de conexión de un nuevo
 * cliente al servidor. Basicamente, cuando un nuevo cliente se conecta, se le
 * debe asignar un identificador unico para distinguirlo de otras conexiones con
 * otros clientes e informarle de su identificador. El identificador es generado
 * automaticamente cuando se agrega la conexion con el cliente en la clase
 * GestorClientes, luego esta clase se encarga de crear el objeto con dicho
 * identificador y enviarla al cliente involucrado para informarselo. Tambien
 * iforma el numero de partida generado para identificar el juego.
 *
 * @author heril
 */
public class ControlInicioConexion {

    private ConexionCliente conexionCliente;
    private int idCliente;
    private int idPartida;

    public ControlInicioConexion(ConexionCliente conexionCliente) {
        this.conexionCliente = conexionCliente;
        idCliente = conexionCliente.getId();
        idPartida = ServerManager.getInstance().getServidor().getNumeroDeJuego();
    }

    /**
     * Crea un objeto con los datos de inicializacion de la conexion y se la
     * envia al cliente involucrado.
     *
     */
    public void ejecutar() {
        AcionableIniciarConexion inicioConexion = new AcionableIniciarConexion(idCliente, idPartida);
        conexionCliente.enviar(inicioConexion);
    }
    
    /**
     * Finaliza la conexion con el cliente recien conectado.
     * Metodo a ejecutarse en caso de que la sala este llena.
     */
    public void finalizarConexion() {
        AcionableIniciarConexion inicioConexion = new AcionableIniciarConexion(idCliente, -1);
        AccionableCerrarConexion cierreConexion = new AccionableCerrarConexion(idCliente, AccionableCerrarConexion.DESCONEXION_SERVIDOR);
        cierreConexion.setProcesado(true);
        conexionCliente.enviar(inicioConexion);
        conexionCliente.enviar(cierreConexion);
    }
}
