/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import modelo.comunicacion.Accionable;

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
            System.out.println(ex.getMessage());
        }
    }

    public void enviar(Accionable a) {
        try {
            out.write(1);
            out.writeObject(a);
            out.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public int disponible() {
        try {
            int disponible = in.available();
            return disponible;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }
    
    public Accionable recibir() {
        try {
            in.read();
            Accionable a = (Accionable) in.readObject();
            return a;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex ) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
