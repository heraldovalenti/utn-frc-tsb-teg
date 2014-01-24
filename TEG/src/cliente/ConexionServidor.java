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

/**
 *
 * @author heril
 */
public class ConexionServidor extends Thread {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ConexionServidor() {
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
        return -1;
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

    @Override
    public void run() {
        long tiempoEspera = Configuracion.getInstancia().tiempoEsperaLecturaCliente();
        while (true) {
            //MonitorCliente.getInstance().startWork();
            System.out.println(System.nanoTime() + ": Conexion servidor checkeada...");
            ClienteManager.getInstance().getLogger().addLogItem(new LogItem("Conexion servidor checkeada..."));
            //MonitorCliente.getInstance().jobFinished();
            try {
                sleep(tiempoEspera);
            } catch (InterruptedException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }
    
    
}
