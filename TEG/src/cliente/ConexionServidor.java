/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import com.Accionable;
import conf.Configuracion;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import logger.LogItem;
import servidor.ColaAcciones;

/**
 *
 * @author heril
 */
public class ConexionServidor extends Thread {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private boolean banderaEjecucion;
    private ColaAcciones colaAcciones;
    private int id;
    private boolean conectado;
    private static String ESTADO_SIN_CONEXION = "Sin conexión";
    private static String ESTADO_CONEXION_ESTABLECIDA = "Conexión establecida";

    public ConexionServidor(ColaAcciones colaAcciones) {
        this.colaAcciones = colaAcciones;
        conectado =  false;
    }

    public void conectar(String direccionServidor) throws IOException {
        try {
            socket = new Socket(
                    direccionServidor,
                    Configuracion.getInstancia().puertoServidor());
            out = new ObjectOutputStream(this.socket.getOutputStream());
            in = new ObjectInputStream(this.socket.getInputStream());
            conectado =  true;
        } catch (IOException ex) {
            socket = null;
            out = null;
            in = null;
            conectado =  false;
            throw ex;
        }
    }

    public void desconectar() throws IOException {
        try {
            in.close();
            out.close();
            socket.close();
            this.interrupt();
            conectado =  false;
        } catch (IOException ex) {
            socket = null;
            out = null;
            in = null;
            conectado =  false;
            throw ex;
        }
    }

    /**
     * Lee y retorna un accionable desde la entrada.
     *
     * @return el accionable leido.
     */
    private Accionable recibir() {
        try {
            in.read();
            Accionable a = (Accionable) in.readObject();
            return a;
        } catch (IOException | ClassNotFoundException ex) {
            ClienteManager.getInstance().getLogger().addLogItem(
                    new LogItem("Error leyendo datos del servidor.", ex));
        }
        return null;
    }

    /**
     * Lee y retorna la cantidad de bytes disponibles para lectura en la
     * entrada.
     *
     * @return la cantidad de bytes disponibles para lectura.
     */
    private int disponible() {
        try {
            int disponible = in.available();
            return disponible;
        } catch (IOException ex) {
            ClienteManager.getInstance().getLogger().addLogItem(
                    new LogItem("Error leyendo datos del servidor.", ex));
        }
        return 0;
    }

    /**
     * Envia un accionable al servidor.
     *
     * @param a el accionable a enviarse.
     * @throws IOException si surge algun error durante el envío.
     */
    public void enviar(Accionable a) throws IOException {
        try {
            out.write(1);
            out.writeObject(a);
            out.flush();
        } catch (IOException ex) {
            throw ex;
        }
    }

    /**
     * Verifica si hay datos disponibles para lectura o no.
     *
     * @return true si hay datos disponibles para leerse, false en otro caso.
     */
    private boolean datosDisponibles() {
        return disponible() != 0;
    }

    /**
     * Lee un accionable desde la entrada y lo ingresa en la cola de acciones.
     */
    private void leerAccionable() {
        Accionable accion = recibir();
        colaAcciones.solicitarAcceso();
        colaAcciones.pushEntrada(accion);
        colaAcciones.informarSalida();
    }

    /**
     * Verifica si hay salidas en la cola de acciones esperando a ser enviadas.
     *
     * @return true si hay salidas esperando su envio, false en otro caso.
     */
    private boolean salidasEnEspera() {
        colaAcciones.solicitarAcceso();
        boolean res = colaAcciones.haySalidas();
        colaAcciones.informarSalida();
        return res;
    }

    /**
     * Obtiene un accionable desde la cola de acciones y lo envia al servidor.
     */
    private void enviarAccionable() {
        colaAcciones.solicitarAcceso();
        Accionable accion = colaAcciones.pullSalida();
        colaAcciones.informarSalida();
        try {
            enviar(accion);
        } catch (IOException ex) {
            ClienteManager.getInstance().getLogger().addLogItem(new LogItem("Error enviando datos al servidor.", ex));
        }
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
                sleep(conf.Configuracion.getInstancia().tiempoEspera());
            } catch (InterruptedException ex) {
                System.err.println("error tratando de dormir: " + ex.getMessage());
            }
        }
    }

    public void setConexionId(int id) {
        this.id = id;
    }

    public int getConexionId() {
        return id;
    }

    /**
     * Metodo para verificar si esta conextado al servidor o no.
     *
     * @return true si esta conectado, false en otro caso.
     */
    public boolean conexionEstablecida() {
        return conectado;
    }

    /**
     * Informa el estado de la conexión.
     * @return un mensaje indicando el estado de la conexión.
     */
    public String getEstado() {
        String res = (conectado) ? ESTADO_CONEXION_ESTABLECIDA : ESTADO_SIN_CONEXION;
        return res;
    }
}
