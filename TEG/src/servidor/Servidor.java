/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import conf.Configuracion;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import logger.LogItem;

/**
 *
 * @author heril
 */
public class Servidor extends Thread {

    private ServerSocket server;
    private GestorClientes gestorClientes;
    private boolean banderaEjecucion;

    public Servidor(GestorClientes gestorClientes) {
        this.gestorClientes = gestorClientes;
        try {
            server = new ServerSocket(Configuracion.getInstancia().puertoServidor());
        } catch (IOException ex) {
            ServerManager.getInstance().getLogger().addLogItem(
                    new LogItem("Error iniciando servidor", ex));
        }
    }
    
    /**
     * Indica que el servidor debe interrumpir su ejecucion.
     */
    public void parar() {
        try {
            server.close();
        } catch (IOException ex) {
            ServerManager.getInstance().getLogger().addLogItem(
                        new LogItem("Error intentando parar el servidor", ex));
        }
        banderaEjecucion = false;
    }

    @Override
    public void run() {
        banderaEjecucion = true;
        while (banderaEjecucion) {
            try {
                Socket s = server.accept();
                gestorClientes.agregarCliente(new ConexionCliente(s));
            } catch (IOException ex) {
                ServerManager.getInstance().getLogger().addLogItem(
                        new LogItem("Error estableciendo conexion con cliente", ex));
            }
        }
    }
}
