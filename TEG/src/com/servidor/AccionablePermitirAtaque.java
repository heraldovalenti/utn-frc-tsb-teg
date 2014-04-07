/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.servidor;

import com.Accionable;
import juego.estructura.GestorJugadores;
import juego.estructura.Jugador;
import juego.mecanicas.turno.GestorTurno;

/**
 *
 * @author Daniel
 */
public class AccionablePermitirAtaque implements Accionable{
    private final Jugador jugadorServidor;

    public AccionablePermitirAtaque(Jugador jugadorServidor) {
        this.jugadorServidor = jugadorServidor;
    }

    @Override
    public void accionar() {
        Jugador jugadorCliente = GestorJugadores.obtenerPorNumero(jugadorServidor.getNroJugador());
        if (jugadorCliente.equals(GestorJugadores.getJugadorLocal())) {
            GestorTurno.permitirAtaque();
        }
    }
    
    
}
