/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teg;

import servidor.ServerManager;
import servidor.control.ControlEjecucionServidor;

/**
 *
 * @author heril
 */
public class TestServer {
    
    public static void main (String args[]) {
        ThemeLoader.loadTheme();
        //iniciar servidor...
        ControlEjecucionServidor.mostrarVentanaAdministracionPartida();
        ControlEjecucionServidor.iniciarServidor();
        ServerManager.getInstance().getAdministracionPartida().actualizarEstadoServidor();
    }
    
}
