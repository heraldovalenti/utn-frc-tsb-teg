/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import com.Accionable;
import com.servidor.AccionableEstadoJugadores;

/**
 *
 * @author heril
 */
public class AccionableSolicitudEstadoJugadores implements Accionable {

    @Override
    public void accionar() {
        AccionableEstadoJugadores.notificarActualizacionJugadores();
    }    
}
