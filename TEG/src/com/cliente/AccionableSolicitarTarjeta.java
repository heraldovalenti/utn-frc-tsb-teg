/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import com.Accionable;
import com.servidor.ActualizadorJugadores;
import java.util.ArrayList;
import java.util.List;
import juego.estructura.GestorTarjetas;
import juego.estructura.Jugador;
import juego.estructura.TarjetaPais;
import servidor.ServerManager;

/**
 *
 * @author Daniel
 */
public class AccionableSolicitarTarjeta implements Accionable {

    private final Jugador jugador;

    public AccionableSolicitarTarjeta(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public void accionar() {
        TarjetaPais tarjeta = GestorTarjetas.solicitarTarjetaPais();
        jugador.añadirTarjetaPais(tarjeta);
        List<Jugador> listaJugadores = new ArrayList<>(1);
        listaJugadores.add(jugador);
        ActualizadorJugadores actualizador = new ActualizadorJugadores(listaJugadores);
        ServerManager.getInstance().registrarSalida(actualizador);
    }

}
