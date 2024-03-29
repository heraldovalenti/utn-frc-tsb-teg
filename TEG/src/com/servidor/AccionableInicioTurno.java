/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servidor;

import Interfaces.FachadaInterfacePrincipal;
import com.Accionable;
import juego.estructura.GestorJugadores;
import juego.estructura.Jugador;
import juego.mecanicas.turno.GestorTurno;

/**
 *
 * @author Daniel
 */
public class AccionableInicioTurno implements Accionable {
    
    private final int nroJugador;
    
    public AccionableInicioTurno(Jugador jugador) {
        this.nroJugador = jugador.getNroJugador();
    }
    
    @Override
    public void accionar() {
        Jugador jugadorCliente = GestorJugadores.obtenerPorNumero(nroJugador);
        GestorTurno.getInstance().setJugadorActual(jugadorCliente);
        FachadaInterfacePrincipal.informarInicioTurno(jugadorCliente);
        FachadaInterfacePrincipal.mostrarMensaje("Turno del jugador: " + jugadorCliente.getNombre());
    }
    
}
