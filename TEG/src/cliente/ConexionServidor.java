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
import java.util.Date;

/**
 *
 * @author heril
 */
public class ConexionServidor {
    
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ConexionServidor() {
        try {
            socket = new Socket(
                    Configuracion.getInstancia().direccionServidor(), 
                    Configuracion.getInstancia().puertoServidor()
                    );
            out = new ObjectOutputStream(this.socket.getOutputStream());
            in = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public synchronized void recibir() {
        try {
            in.read();
            Accionable a = (Accionable) in.readObject();
            a.accionar();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public synchronized int disponible() {
        int res = -1;
        try {
            Date d = new Date();
            res = in.available();
            System.out.println(d.getTime() + ": " + res);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return res;
    }
    
    public synchronized void enviar(Accionable a) {
        try {
            out.write(1);
            out.writeObject(a);
            out.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
