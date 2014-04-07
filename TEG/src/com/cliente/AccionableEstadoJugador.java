/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import com.Accionable;
import servidor.ServerManager;
import servidor.control.ControlEstadoJugadores;

/**
 *
 * @author heril
 */
public class AccionableEstadoJugador implements Accionable {

    private int idJugador;
    private boolean estaListo;

    public AccionableEstadoJugador(int idJugador, boolean estaListo) {
        this.idJugador = idJugador;
        this.estaListo = estaListo;
    }
    
    @Override
    public void accionar() {
        ControlEstadoJugadores.jugadorListo(idJugador, estaListo);
    }
    
}
