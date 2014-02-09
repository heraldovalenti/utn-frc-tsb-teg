/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servidor;

import com.Accionable;

/**
 *
 * @author heril
 */
public class RecibirNuevoClienteEnServidor implements Accionable {

    private int id;
    
    public RecibirNuevoClienteEnServidor(int id) {
        this.id = id;
    }
    
    @Override
    public void accionar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
