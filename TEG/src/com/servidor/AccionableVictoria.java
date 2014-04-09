/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servidor;

import com.Accionable;
import juego.estructura.Jugador;

/**
 *
 * @author Daniel
 */
public class AccionableVictoria implements Accionable {

    private final Jugador jugador;
    private final String mensaje;

    public AccionableVictoria(Jugador jugador, String mensaje) {
        this.jugador = jugador;
        this.mensaje = mensaje;
    }
    
    @Override
    public void accionar() {
        //TODO: Mostrar ventana de victoria y finalizar el juego
    }

}
