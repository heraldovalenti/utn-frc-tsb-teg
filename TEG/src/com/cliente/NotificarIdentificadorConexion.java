/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import cliente.ClienteManager;
import com.Accionable;

/**
 *
 * @author heril
 */
public class NotificarIdentificadorConexion implements Accionable {

    private int id;

    public NotificarIdentificadorConexion(int id) {
        this.id = id;
    }
    
    @Override
    public void accionar() {
        ClienteManager.getInstance().establecerIdentificadorConexion(id);
    }
    
}
