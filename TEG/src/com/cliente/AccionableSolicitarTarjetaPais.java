/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import com.Accionable;
import com.servidor.ActualizadorJugador;
import juego.estructura.GestorJugadores;
import juego.estructura.GestorTarjetas;
import juego.estructura.Jugador;
import juego.estructura.TarjetaPais;
import servidor.ServerManager;

/**
 *
 * @author Daniel
 */
public class AccionableSolicitarTarjetaPais implements Accionable {

    private final int nroJugador;

    public AccionableSolicitarTarjetaPais(Jugador jugador) {
        this.nroJugador = jugador.getNroJugador();
    }

    @Override
    public void accionar() {
        Jugador jugador = GestorJugadores.obtenerPorNumero(nroJugador);
        if (jugador.getCantidadTarjetasPais() < 5) {
            TarjetaPais tarjeta = GestorTarjetas.solicitarTarjetaPais();
            jugador.añadirTarjetaPais(tarjeta);
            ActualizadorJugador actualizador = new ActualizadorJugador(jugador);
            ServerManager.getInstance().registrarSalida(actualizador);
        }
    }
}
