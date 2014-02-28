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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private String alias;

    public ConexionServidor(ColaAcciones colaAcciones) {
        this.colaAcciones = colaAcciones;
    }
    
    public void conectar(String direccionServidor) {
        try {
            socket = new Socket(
                    direccionServidor,
                    Configuracion.getInstancia().puertoServidor());
            out = new ObjectOutputStream(this.socket.getOutputStream());
            in = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException ex) {
            ClienteManager.getInstance().getLogger().addLogItem(new LogItem("Error estableciendo conexión con el servidor.", ex));
        }
    }
    
    public void desconectar() {
        try {
            in.close();
            out.close();
            socket.close();
            this.interrupt();
        } catch (IOException | SecurityException ex) {
            ClienteManager.getInstance().getLogger().addLogItem(new LogItem("Error cerrado conexión con el servidor.", ex));
        }
        
    }

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

    public void enviar(Accionable a) {
        try {
            out.write(1);
            out.writeObject(a);
            out.flush();
        } catch (IOException ex) {
            ClienteManager.getInstance().getLogger().addLogItem(
                    new LogItem("Error enviando datos al servidor", ex));
        }
    }
    
    private boolean datosDisponibles() {
        return disponible() != 0;
    }
        
    private void leerAccionable() {
        Accionable accion = recibir();
        colaAcciones.solicitarAcceso();
        colaAcciones.pushEntrada(accion);
        colaAcciones.informarSalida();
    }
    
    private boolean salidasEnEspera() {
        colaAcciones.solicitarAcceso();
        boolean res = colaAcciones.haySalidas();
        colaAcciones.informarSalida();
        return res;
    }
    
   private void enviarAccionable() {
        colaAcciones.solicitarAcceso();
        Accionable accion = colaAcciones.pullSalida();
        colaAcciones.informarSalida();
        enviar(accion);
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

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getConexionId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }    
}
