/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servidor;

import cliente.control.ControlColor;
import com.Accionable;
import java.awt.Color;
import java.util.Set;

/**
 *
 * @author heril
 */
public class AccionableColoresDisponibles implements Accionable {

    private Set<Color> coloresDisponibles;

    /**
     * Constructor para el servidor. Sirve para informar a los clientes de los
     * colores disponibles para seleccionar los jugadores frente a eventuales
     * cambios en los colores disponibles, por ej, cuando un jugador cambia su
     * color asignado, o cuando se conecta un nuevo jugador y un color de la
     * lista es asignado a este nuevo jugador.
     *
     * @param coloresDisponibles los colores disponibles para seleccionar.
     */
    public AccionableColoresDisponibles(Set<Color> coloresDisponibles) {
        this.coloresDisponibles = coloresDisponibles;
    }

    @Override
    public void accionar() {
        new ControlColor().actualizarColoresDisponibles(coloresDisponibles);
    }
}
