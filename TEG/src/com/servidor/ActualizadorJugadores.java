/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servidor;

import com.Accionable;
import java.util.List;
import juego.estructura.GestorJugadores;
import juego.estructura.Jugador;

/**
 *
 * @author Daniel
 */
public class ActualizadorJugadores implements Accionable {
    
    private final List<Jugador> listaJugadores;
    
    public ActualizadorJugadores(List<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }
    
    @Override
    public void accionar() {
        for (Jugador jugadorServidor : listaJugadores) {
            Jugador jugadorCliente = GestorJugadores.obtenerPorNumero(jugadorServidor.getNroJugador());
            jugadorCliente.setCantidadCanjes(jugadorServidor.getCantidadCanjes());
            jugadorCliente.setListaTarjetasPais(jugadorServidor.getListaTarjetasPais());
            jugadorCliente.setListaTarjetaContinentes(jugadorServidor.getListaTarjetaContinentes());
        }
    }
    
}
