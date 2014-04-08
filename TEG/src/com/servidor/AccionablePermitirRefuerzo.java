/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servidor;

import cliente.control.ControlRefuerzo;
import com.Accionable;
import java.util.Map;
import juego.estructura.Continente;
import juego.estructura.GestorJugadores;
import juego.estructura.Jugador;
import juego.mecanicas.turno.GestorTurno;

/**
 *
 * @author Daniel
 */
public class AccionablePermitirRefuerzo implements Accionable {

    private final Jugador jugadorServidor;
    private final int cantidadEjercitos;
    private final Map<Continente, Integer> ejercitosPorContinente;
    private final boolean permitirMisiles;

    public AccionablePermitirRefuerzo(Jugador jugador, int cantidadEjercitos, Map<Continente, Integer> ejercitosPorContinente, boolean permitirMisiles) {
        this.jugadorServidor = jugador;
        this.cantidadEjercitos = cantidadEjercitos;
        this.ejercitosPorContinente = ejercitosPorContinente;
        this.permitirMisiles = permitirMisiles;
    }

    @Override
    public void accionar() {
        Jugador jugadorCliente = GestorJugadores.obtenerPorNumero(jugadorServidor.getNroJugador());
        if (jugadorCliente.equals(GestorJugadores.getJugadorLocal())) {
            ControlRefuerzo control = new ControlRefuerzo(cantidadEjercitos, ejercitosPorContinente, permitirMisiles);
            GestorTurno.setRefuerzoActual(control);
            if (permitirMisiles) {
                GestorTurno.setEtapaActual(GestorTurno.ETAPA_INCORPORAR_EJERCITOS);
            } else {
                GestorTurno.setEtapaActual(GestorTurno.ETAPA_SOLO_REFUERZOS);
            }
        }
    }
}
