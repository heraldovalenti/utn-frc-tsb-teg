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
public class CerrarConexion implements Accionable {

    private String razon;
    public static String SERVIDOR_INTERRUMPIDO = "El servidor ha sido detenido.";
    public static String DESCONEXION_SERVIDOR = "Has sido desconectado por el servidor.";
    public static String DESCONEXION_MANUAL = "Has cerrado la conexión con el servidor.";
    
    public CerrarConexion(String razon) {
        this.razon = razon;
    }

    @Override
    public void accionar() {
        throw new UnsupportedOperationException(razon + " - Not supported yet.");
    }
    
    
    
}
