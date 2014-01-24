/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import com.Accionable;

/**
 *
 * @author heril
 */
public class RecibirNuevoCliente implements Accionable {

    private int id;
    
    public RecibirNuevoCliente(int id) {
        this.id = id;
    }
    
    @Override
    public void accionar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
}
