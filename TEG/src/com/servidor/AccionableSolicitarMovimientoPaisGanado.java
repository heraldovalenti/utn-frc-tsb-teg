/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servidor;

import Interfaces.FachadaInterfacePrincipal;
import com.Accionable;
import juego.estructura.GestorJugadores;
import juego.estructura.GestorPaises;
import juego.estructura.Jugador;
import juego.estructura.Pais;

/**
 *
 * @author Daniel
 */
public class AccionableSolicitarMovimientoPaisGanado implements Accionable {

    private final Jugador jugador;
    private final Pais origen;
    private final Pais destino;
    private final int cantidadEjercitos;

    public AccionableSolicitarMovimientoPaisGanado(Jugador jugador, Pais origen, Pais destino, int cantidadEjercitos) {
        this.jugador = GestorJugadores.obtenerPorNumero(jugador.getNroJugador());
        this.origen = GestorPaises.getPais(origen.getNroPais());
        this.destino = GestorPaises.getPais(destino.getNroPais());
        this.cantidadEjercitos = cantidadEjercitos;
    }

    @Override
    public void accionar() {
        if (GestorJugadores.getJugadorLocal().equals(jugador)) {
            FachadaInterfacePrincipal.refuerzoPaisGanado(origen, destino, cantidadEjercitos);
        }
    }

}
