/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import cliente.ClienteManager;

/**
 *
 * @author heril
 */
public class FachadaInterfacePrincipal {
    
    public static void mostrarChat(String chat) {
        ClienteManager.getInstance().getInterfacePrincipal();
    }
    
}
