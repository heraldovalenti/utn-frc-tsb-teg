/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import com.Accionable;
import com.servidor.AccionableMensajeGlobal;
import java.util.ArrayList;
import java.util.List;
import juego.estructura.Canjeable;
import juego.estructura.GestorJugadores;
import juego.estructura.GestorTarjetas;
import juego.estructura.Jugador;
import com.servidor.ActualizadorJugadores;
import servidor.ServerManager;

/**
 *
 * @author Daniel
 */
public class AccionableCanjeTarjetas implements Accionable {

    private final Jugador jugadorCliente;
    private final List<Canjeable> listaTarjetas;

    public AccionableCanjeTarjetas(Jugador jugadorCliente, List<Canjeable> listaTarjetas) {
        this.jugadorCliente = jugadorCliente;
        this.listaTarjetas = listaTarjetas;
    }

    @Override
    public void accionar() {
        if (GestorTarjetas.canjeValido(listaTarjetas)) {
            Jugador jugadorServidor = GestorJugadores.obtenerPorNumero(jugadorCliente.getNroJugador());
            jugadorServidor.canjearTarjetas(listaTarjetas);
            AccionableMensajeGlobal mensaje = new AccionableMensajeGlobal(jugadorServidor + " ha canjeado las tarjetas de " + stringTarjetas() + " por ejércitos");
            ServerManager.getInstance().registrarSalida(mensaje);
            List<Jugador> listaJugadores = new ArrayList<>(1);
            listaJugadores.add(jugadorServidor);
            ActualizadorJugadores actualizador = new ActualizadorJugadores(listaJugadores);
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
