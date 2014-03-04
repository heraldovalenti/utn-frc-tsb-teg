/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teg;

import cliente.ClienteManager;
import cliente.control.ControlConexion;

/**
 *
 * @author heril
 */
public class TestCliente {
    
    public static void main(String args[]) {
        ThemeLoader.loadTheme();
        ClienteManager.getInstance().init();
        ControlConexion.conectarServidor("localhost");
    }
    
}
