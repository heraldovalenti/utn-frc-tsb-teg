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

/**
 *
 * @author heril
 */
public class ConexionCliente {

    private int id;
    private String alias;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    public ConexionCliente(int id) {
        this.id = id;
    }

    public ConexionCliente(Socket socket) {
        this.socket = socket;
        try {
            out = new ObjectOutputStream(this.socket.getOutputStream());
            in = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException ex) {
            ServerManager.getInstance().getLogger().addLogItem(new LogItem("Error estableciendo conexión con cliente.", ex));
        }
    }

    /**
     * Envia una accion al cliente conectado.
     *
     * @param a la accion a enviarse al cliente.
     */
    public void enviar(Accionable a) {
        try {
            out.write(1);
            out.writeObject(a);
            out.flush();
        } catch (IOException ex) {
            ServerManager.getInstance().getLogger().addLogItem(
                    new LogItem("Error enviando datos a cliente.", ex));
        }
    }

    /**
     * Metodo para verificar si el cliente conectado ha enviado datos y estan
     * pendientes de procesamiento.
     *
     * @return int 0 si no hay datos disponibles, cualquier otro valor en caso
     * contrario.
     */
    public int disponible() {
        try {
            int disponible = in.available();
            return disponible;
        } catch (IOException ex) {
            ServerManager.getInstance().getLogger().addLogItem(
                    new LogItem("Error leyendo datos de cliente.", ex));
        }
        return 0;
    }

    /**
     * Recibe una accion enviada por el cliente.
     *
     * @return Accionable la accion enviada por el cliente.
     */
    public Accionable recibir() {
        try {
            in.read();
            Accionable a = (Accionable) in.readObject();
            return a;
        } catch (IOException | ClassNotFoundException ex) {
            ServerManager.getInstance().getLogger().addLogItem(
                    new LogItem("Error leyendo datos de cliente.", ex));
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ConexionCliente) {
            ConexionCliente other = (ConexionCliente) obj;
            return other.id == this.id;
        }
        return false;
    }

    //METODOS GETTERS AND SETTER
    public int getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
