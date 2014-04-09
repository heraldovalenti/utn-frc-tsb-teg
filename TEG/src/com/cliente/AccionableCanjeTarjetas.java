/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import com.Accionable;
import com.servidor.AccionableMensajeGlobal;
import java.util.List;
import juego.estructura.Canjeable;
import juego.estructura.GestorJugadores;
import juego.estructura.GestorTarjetas;
import juego.estructura.Jugador;
import com.servidor.ActualizadorJugador;
import servidor.ServerManager;

/**
 *
 * @author Daniel
 */
public class AccionableCanjeTarjetas implements Accionable {

    private final int nroJugador;
    private final List<Canjeable> listaTarjetas;

    public AccionableCanjeTarjetas(Jugador jugadorCliente, List<Canjeable> listaTarjetas) {
        this.nroJugador = jugadorCliente.getNroJugador();
        this.listaTarjetas = listaTarjetas;
    }

    @Override
    public void accionar() {
        Jugador jugadorServidor = GestorJugadores.obtenerPorNumero(nroJugador);
        if (GestorTarjetas.canjeValido(jugadorServidor, listaTarjetas)) {
            jugadorServidor.usarTarjetas(listaTarjetas);
            AccionableMensajeGlobal mensaje = new AccionableMensajeGlobal(jugadorServidor + " ha canjeado las tarjetas de " + stringTarjetas() + " por ejércitos");
            ServerManager.getInstance().registrarSalida(mensaje);
            ActualizadorJugador actualizador = new ActualizadorJugador(jugadorServidor);
            ServerManager.getInstance().registrarSalida(actualizador);
        }
    }

    private String stringTarjetas() {
        StringBuilder builder = new StringBuilder(listaTarjetas.get(0).toString());
        int cantidadTarjetas = listaTarjetas.size();
        if (cantidadTarjetas > 1) {
            for (int i = 1; i < cantidadTarjetas - 1; i++) {
                builder.append(", ");
                builder.append(listaTarjetas.get(i).toString());
            }
            builder.append(" y ");
            builder.append(listaTarjetas.get(cantidadTarjetas - 1).toString());
        }
        return builder.toString();
    }
}
