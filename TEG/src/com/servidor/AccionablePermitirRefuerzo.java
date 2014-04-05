/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servidor;

import cliente.control.ControlRefuerzo;
import com.Accionable;
import java.util.HashMap;
import java.util.Map;
import juego.estructura.Continente;
import juego.estructura.GestorContinentes;
import juego.estructura.Jugador;

/**
 *
 * @author Daniel
 */
public class AccionablePermitirRefuerzo implements Accionable {

    private final Jugador jugador;
    private final int cantidadEjercitos;

    public AccionablePermitirRefuerzo(Jugador jugador, int cantidadEjercitos) {
        this.jugador = jugador;
        this.cantidadEjercitos = cantidadEjercitos;
    }

    @Override
    public void accionar() {
        Map<Continente, Integer> mapaContinentes = jugador.calcularPaisesPorContinente();
        Map<Continente, Integer> paisesPorContinente = new HashMap<>();
        for (Continente continente : mapaContinentes.keySet()) {
            if (mapaContinentes.get(continente) == GestorContinentes.obtenerCantidadPaises(continente.getNroContinente())) {
                paisesPorContinente.put(continente, GestorContinentes.obtenerRefuerzosPorContinente(continente.getNroContinente()));
            }
        }
        ControlRefuerzo control = new ControlRefuerzo(cantidadEjercitos, paisesPorContinente);
    }

}
