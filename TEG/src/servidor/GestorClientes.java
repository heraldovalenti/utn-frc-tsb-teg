/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import com.Accionable;
import com.cliente.AccionableCerrarConexion;
import com.servidor.AccionableEstadoJugadores;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import servidor.control.ControlColores;
import servidor.control.ControlEstadoJugadores;
import servidor.control.ControlInicioConexion;
import servidor.control.ControladorAlias;
import servidor.ia.ClienteIA;

/**
 *
 * @author heril
 */
public class GestorClientes implements Runnable {

    private Thread hilo;
    private LinkedList<ConexionCliente> conexionesCliente;
    private ConexionCliente conexionAAtender;
    private ColaAcciones colaAcciones;
    private boolean banderaEjecucion;
    private static final int maximoClientes = 6;

    public GestorClientes(ColaAcciones colaAcciones) {
        conexionesCliente = new LinkedList();
        conexionAAtender = null;
        this.colaAcciones = colaAcciones;
        banderaEjecucion = false;
    }

    /**
     * Agrega una conexion con un cliente al gestor. Ejecuta el procedimiento de
     * inicio de conexion con el cliente. Si la sala esta llena, no se almacena
     * la conexion y envia la orden de cerrar la conexion al cliente, informando
     * ademas la situacion.
     *
     * @param cc ConexionCliente a agregar
     */
    public void agregarCliente(ConexionCliente cc) {
        cc.setId(generarIdentificadorUnico());
        if (conexionesCliente.size() < maximoClientes) {
            conexionesCliente.add(cc);
            new ControlInicioConexion(cc).ejecutar();
        } else {
            new ControlInicioConexion(cc).finalizarConexion();
        }
    }

    /**
     * Agrega un cliente de IA al gestor. Si la capacidad del gestor ya esta en
     * su maximo nivel, no se podria agregar el cliente IA y por lo tengo no se
     * realiza ninguna accion. Si se agrega el jugador IA, se inicializan sus
     * parametros de juego (color, alias y estado listo) y se emite una
     * actualizacion de estado de jugadores.
     */
    public void agregarClienteIA() {
        if (conexionesCliente.size() < maximoClientes) {
            ClienteIA clienteIA = new ClienteIA();
            clienteIA.setId(generarIdentificadorUnico());
            conexionesCliente.add(clienteIA);

            ControladorAlias.asignarAliasIA(clienteIA.getId());
            ControlEstadoJugadores.jugadorIAListo(clienteIA.getId());
            ControlColores.asignarColorIA(clienteIA.getId());

            AccionableEstadoJugadores.notificarActualizacionJugadores();
        }
    }

    /**
     * Elimina un cliente de IA del gestor si existe alguno, de otra manera, no
     * realiza ninguna accion. Si se quito algun jugador IA, se emite una
     * actualizacion de estado de jugadores.
     */
    public void quitarClienteIA() {
        for (ConexionCliente cc : conexionesCliente) {
            if (cc instanceof ClienteIA) {
                quitarCliente(cc.getId());
                AccionableEstadoJugadores.notificarActualizacionJugadores();
                return;
            }
        }
    }

    /**
     * Informa si un jugador es un cliente de IA o no.
     * @param idJugador el jugador a determinarse si es IA.
     * @return true si es un jugador de IA, false en otro caso.
     */
    public boolean esIA(int idJugador) {
        ConexionCliente buscado = null;
        for (ConexionCliente cc : conexionesCliente) {
            if (cc.getId() == idJugador) {
                buscado = cc;
                break;
            }
        }
        if (buscado == null) {
            return false;
        } else {
            if (buscado instanceof ClienteIA) {
                return true;
            } else {
                return false;
            }
        }

    }

    /**
     * Quita una conexion de la coleccion de conexiones a clientes.
     *
     * @param idCliente el id de la conexion a quitarse.
     * @return la conexion identificada, null si no se ha encontrado ninguna
     * conexion.
     */
    public ConexionCliente quitarCliente(int idCliente) {
        ConexionCliente res = null;
        for (int i = 0; i < conexionesCliente.size(); i++) {
            ConexionCliente cc = conexionesCliente.get(i);
            if (cc.getId() == idCliente) {
                res = conexionesCliente.remove(i);
                break;
            }
        }
        return res;
    }

    /**
     * Genera un identificador unico para los nuevos clientes que se conectan al
     * servidor. Para realizarlo, genera un numero aleatorio y verifica que los
     * clientes ya conectados no contengan el mismo identificador.
     *
     * @return int el identificador generado.
     */
    private int generarIdentificadorUnico() {
        boolean identificadorValido;
        int identificador = -1;
        do {
            double rnd = Math.random();
            identificador = (int) (rnd * 1000000000);
            boolean forInterrumpido = false;
            for (ConexionCliente cc : conexionesCliente) {
                if (cc.getId() == identificador || identificador == 0) {
                    forInterrumpido = true;
                    break;
                }
            }
            if (forInterrumpido) {
                identificadorValido = false;
            } else {
                identificadorValido = true;
            }
        } while (!identificadorValido);
        return identificador;
    }

    /**
     * Metodo para consultar si hay datos disponibles enviados por algun
     * cliente. Establece la conexion a atender del cliente que ha enviado datos
     * y estan listos para recibirse.
     *
     * @return true si hay datos para leer, false en caso contrario.
     */
    private boolean datosDisponibles() {
        conexionAAtender = checkDatosDisponibles();
        rotarColaConexiones();
        return conexionAAtender != null;
    }

    /**
     * Rota la cola de conexiones establecidas con clientes. La conexion que
     * estaba en primer lugar de la cola, pasa a estar en ultimo lugar ahora,
     * con el objetivo de que si un cliente envia datos constantemente, los
     * demas clientes tambien tengan oportunidad de ser atendidos por el
     * servidor.
     */
    private void rotarColaConexiones() {
        ConexionCliente aux = conexionesCliente.pollFirst();
        if (aux != null) {
            conexionesCliente.addLast(aux);
        }
    }

    /**
     * Metodo para obtener los accionables enviados por los clientes. Lee un
     * accionable del cliente indicado por el la conexion a servir y lo agrega a
     * la cola de acciones. Si la conexion es null no hace nada.
     *
     * @return Accionable enviando por el cliente.
     */
    private void leerAccionable() {
        if (conexionAAtender == null) {
            return;
        }
        Accionable accion = recibir();
        colaAcciones.solicitarAcceso();
        colaAcciones.pushEntrada(accion);
        colaAcciones.informarSalida();
        conexionAAtender = null;
    }

    /**
     * Envia un accionable a todos los clientes conectados. Obtiene el
     * accionable a enviarse desde la cola de salida de acciones.
     *
     * @param accion el accionable a enviarse.
     */
    private void enviarAccionable() {
        colaAcciones.solicitarAcceso();
        Accionable accion = colaAcciones.pullSalida();
        colaAcciones.informarSalida();
        for (ConexionCliente aux : conexionesCliente) {
            aux.enviar(accion);
        }
    }

    /**
     * Envia un accionable a todos los clientes conectados.
     *
     * @param accion el accionable a enviarse.
     */
    public void enviarAccionable(Accionable accion) {
        for (ConexionCliente aux : conexionesCliente) {
            aux.enviar(accion);
        }
    }

    /**
     * Envia un accionable a la conexion cliente especificada. Sobrecarga para
     * enviar mensajes a un cliente en particular.
     *
     * @param accion el accionable a enviarse.
     * @param cc la conexion a enviarse el accionable.
     */
    public void enviarAccionable(Accionable accion, ConexionCliente cc) {
        int indexOf = conexionesCliente.indexOf(cc);
        conexionesCliente.get(indexOf).enviar(accion);
    }

    /**
     * Chequea si hay datos para recibir de los clientes.
     *
     * @return la conexion de cliente que envio datos, null si no existen datos.
     */
    private ConexionCliente checkDatosDisponibles() {
        for (ConexionCliente cc : conexionesCliente) {
            if (cc.disponible() != 0) {
                return cc;
            }
        }
        return null;
    }

    /**
     * Devuelve lee un accionable enviado por el cliente.
     *
     * @return Accionable la accion enviada por el cliente.
     */
    private Accionable recibir() {
        return conexionAAtender.recibir();
    }

    /**
     * Verifica la existencia de salidas esperando para ser enviadas.
     *
     * @return true si hay salidas pendientes de envio, false en otro caso.
     */
    private boolean salidasEnEspera() {
        colaAcciones.solicitarAcceso();
        boolean res = colaAcciones.haySalidas();
        colaAcciones.informarSalida();
        return res;
    }

    /**
     * Indica que el gestor debe parar su ejecucion y cierra las conexiones con
     * clientes activas.
     */
    public void parar() {
        banderaEjecucion = false;
        enviarAccionable(new AccionableCerrarConexion());
        conexionesCliente.clear();
    }

    /**
     * Informa si hay conexiones establecidas con clientes.
     *
     * @return true si hay alguna conexion establecida, false en otro caso.
     */
    public boolean conexionesEstablecidas() {
        return conexionesCliente.size() != 0;
    }

    /**
     * Informa la cantidad de conexiones establecidas con clientes.
     *
     * @return la cantidad de conexiones establecidas.
     */
    public int cantidadConexionesEstablecidas() {
        return conexionesCliente.size();
    }

    /**
     * Informa el identificador de las conexiones establecidas con clientes.
     *
     * @return un conjunto con los id de las conexiones establecidas.
     */
    public Set<Integer> getIdConexionesEstablecidas() {
        Set<Integer> res = new HashSet<>();
        for (ConexionCliente cc : conexionesCliente) {
            res.add(cc.getId());
        }
        return res;
    }

    @Override
    public void run() {
        banderaEjecucion = true;
        while (banderaEjecucion) {
            if (datosDisponibles()) {
                leerAccionable();
            }
            if (salidasEnEspera()) {
                enviarAccionable();
            }
            try {
                hilo.sleep(conf.Configuracion.getInstancia().tiempoEspera());
            } catch (InterruptedException ex) {
                System.err.println("Error tratando de dormir: " + ex.getMessage());
            }
        }
    }

    public void setHilo(Thread hilo) {
        this.hilo = hilo;
    }
}
