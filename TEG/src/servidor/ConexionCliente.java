/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import com.Accionable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import logger.LogItem;
import logger.Logger;

/**
 *
 * @author heril
 */
public class ConexionCliente {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public ConexionCliente(Socket socket) {
        this.socket = socket;
        try {
            out = new ObjectOutputStream(this.socket.getOutputStream());
            in = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException ex) {
            Logger.getInstance().addLogItem(new LogItem("Error estableciendo conexión con cliente",ex));
        }
    }

    public void enviar(Accionable a) {
        try {
            out.write(1);
            out.writeObject(a);
            out.flush();
        } catch (IOException ex) {
            Logger.getInstance().addLogItem(
                    new LogItem("Error enviando datos a cliente",ex
                    ));
        }
    }
    
    public int disponible() {
        try {
            int disponible = in.available();
            return disponible;
        } catch (IOException ex) {
            Logger.getInstance().addLogItem(
                    new LogItem("Error leyendo datos de cliente",ex
                    ));
        }
        return -1;
    }
    
    public Accionable recibir() {
        try {
            in.read();
            Accionable a = (Accionable) in.readObject();
            return a;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getInstance().addLogItem(
                    new LogItem("Error leyendo datos de cliente",ex
                    ));
        }
        return null;
    }
}
