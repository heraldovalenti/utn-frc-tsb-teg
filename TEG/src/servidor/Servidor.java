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

    private int numeroDeJuego;
    private ServerSocket server;
    private GestorClientes gestorClientes;
    private boolean banderaEjecucion;
    private static String ESTADO_EN_EJECUCION = "Servidor en ejecución";
    private static String ESTADO_NO_INICIADO = "Servidor no iniciado";

    public Servidor(GestorClientes gestorClientes) {
        this.gestorClientes = gestorClientes;
        try {
            server = new ServerSocket(Configuracion.getInstancia().puertoServidor());
        } catch (IOException ex) {
            ServerManager.getInstance().getLogger().addLogItem(
                    new LogItem("Error inicializando servidor", ex));
        }
        numeroDeJuego = 0;
    }
    
    /**
     * Indica que el servidor debe interrumpir su ejecucion.
     */
    public void parar() {
        try {
            server.close();
            this.interrupt();
        } catch (IOException ex) {
            ServerManager.getInstance().getLogger().addLogItem(
                        new LogItem("Error intentando detener el servidor", ex));
        }
        numeroDeJuego = 0;
        banderaEjecucion = false;
    }
    
    /**
     * Informa si el servidor esta en ejecución o no.
     * @return true si el servidor esta en ejecución, false en otro caso.
     */
    public boolean enEjecucion() {
        return this.banderaEjecucion;
    }
    
    /**
     * Informa mediante un mensaje el estado de ejecucion del servidor.
     * @return un mensaje iformando el estado del servidor.
     */
    public String getEstado() {
        return (banderaEjecucion) ? ESTADO_EN_EJECUCION : ESTADO_NO_INICIADO;
    }
    
    /**
     * Genera aleatoriamente un numero de juego para identificar al mismo.
     */
    private void generarNumeroDeJuego() {
        double rnd = Math.random();
        rnd = rnd * 1000000000;
        numeroDeJuego = (int) rnd;
    }
    
    /**
     * Informa el numero de juego.
     * @return el numero de juego si ya fue generado, 0 en otro caso.
     */
    public int getNumeroDeJuego() {
        return numeroDeJuego;
    }

    @Override
    public void run() {
        generarNumeroDeJuego();
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
