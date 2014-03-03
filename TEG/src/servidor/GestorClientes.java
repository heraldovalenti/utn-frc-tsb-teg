/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import com.Accionable;
import com.cliente.CerrarConexion;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import servidor.control.ControlInicioConexion;

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

    public GestorClientes(ColaAcciones colaAcciones) {
        conexionesCliente = new LinkedList();
        conexionAAtender = null;
        this.colaAcciones = colaAcciones;
        banderaEjecucion = false;
    }

    /**
     * Agrega una conexion con un cliente al gestor.
     * Ejecuta el procedimiento de inicio de conexion con el cliente.
     *
     * @param cc ConexionCliente a agregar
     */
    public void agregarCliente(ConexionCliente cc) {
        cc.setId(generarIdentificadorUnico());
        conexionesCliente.add(cc);
        new ControlInicioConexion(cc).ejecutar();
    }
    
    /**
     * Genera un identificador unico para los nuevos clientes que se conectan
     * al servidor. Para realizarlo, genera un numero aleatorio y verifica que
     * los clientes ya conectados no contengan el mismo identificador.
     * @return int el identificador generado.
     */
    private int generarIdentificadorUnico() {
        boolean identificadorValido;
        int identificador = -1;
        do {
            double rnd = Math.random();
            identificador = (int)(rnd * 1000000000);
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
        } while(!identificadorValido);
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
     * Envia un accionable a la conexion cliente especificada.
     * Sobrecarga para enviar mensajes a un cliente en particular.
     * @param accion el accionable a enviarse.
     * @param cc la conexion a enviarse el accionable.
     */
    public void enviarAccionable(Accionable accion, ConexionCliente cc) {
        int indexOf = conexionesCliente.indexOf(cc);
        conexionesCliente.get(indexOf).enviar(accion);
    }
    
    public boolean aliasDisponible(String alias) {
        for (ConexionCliente cc : conexionesCliente) {
            if (cc.getAlias() != null && cc.getAlias().equals(alias)) {
                return false;
            }
        }
        return true;
    }
    
    public void establecerAlias(int id, String alias) {
        int indexOf = conexionesCliente.indexOf(new ConexionCliente(id));
        conexionesCliente.get(indexOf).setAlias(alias);
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
        enviarAccionable(new CerrarConexion(CerrarConexion.SERVIDOR_INTERRUMPIDO));
        banderaEjecucion = false;
        conexionesCliente.clear();
    }
    
    /**
     * Informa si hay conexiones establecidas con clientes.
     * @return true si hay alguna conexion establecida, false en otro caso.
     */
    public boolean conexionesEstablecidas() {
        return conexionesCliente.size() != 0;
    }
    
    /**
     * Informa el identificador de las conexiones establecidas con clientes.
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
