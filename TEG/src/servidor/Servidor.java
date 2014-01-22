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
    
    public  Servidor() {
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(Configuracion.getInstancia().puertoServidor());

        } catch (IOException ex) {
            logger.Logger.getInstance().addLogItem(
                    new LogItem("Error iniciando servidor", ex));
        }
        while (true) {
            //esta accion se puede trasladar al manager...
            try {
                Socket s = server.accept();
                GestorClientes.getInstance().agregarCliente(new ConexionCliente(s));
            } catch (IOException ex) {
                logger.Logger.getInstance().addLogItem(
                        new LogItem("Error estableciendo conexion con cliente", ex));
            }
        }
    }
}
