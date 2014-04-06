/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 *
 * @author heril
 */
public class Servidor extends Thread {
    
    private static Servidor instancia = null;
    public static Servidor getInstancia() {
        if (instancia == null) instancia = new Servidor();
        return instancia;
    }
    private Servidor() {
        this.coordinador = new CoordinadorClientes();
    }
    
    private ServerSocket server;
    private CoordinadorClientes coordinador;    
    public void run() {
        try {
            server = new ServerSocket(9988);
            while (true) {
                Socket s = server.accept();
                coordinador.agregarCliente(new ConexionCliente(s));
            }
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
