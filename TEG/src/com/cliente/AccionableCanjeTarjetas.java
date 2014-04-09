/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import com.Accionable;
import com.servidor.AccionableMensajeGlobal;
import com.servidor.AccionablePermitirRefuerzo;
import java.util.List;
import juego.estructura.Canjeable;
import juego.estructura.GestorJugadores;
import juego.estructura.GestorTarjetas;
import juego.estructura.Jugador;
import com.servidor.ActualizadorJugador;
import java.util.HashMap;
import juego.estructura.Continente;
import logger.LogItem;
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
        ServerManager.getInstance().getLogger().addLogItem(
                new LogItem("Canje recibido del jugador " + jugadorServidor.getNombre()));
        if (GestorTarjetas.canjeValido(jugadorServidor, listaTarjetas)) {
            jugadorServidor.usarTarjetas(listaTarjetas);
            AccionableMensajeGlobal mensaje = new AccionableMensajeGlobal(jugadorServidor.getNombre() + " ha canjeado las tarjetas " + stringTarjetas());
            ServerManager.getInstance().registrarSalida(mensaje);
            ActualizadorJugador actualizador = new ActualizadorJugador(jugadorServidor);
            ServerManager.getInstance().registrarSalida(actualizador);
            AccionablePermitirRefuerzo refuerzo = new AccionablePermitirRefuerzo(jugadorServidor, GestorTarjetas.calcularEjercitosAdicionales(jugadorServidor.getCantidadCanjes()), new HashMap<Continente, Integer>(), true);
            ServerManager.getInstance().registrarSalida(refuerzo);
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
