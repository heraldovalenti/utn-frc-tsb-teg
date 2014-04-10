/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servidor;

import cliente.control.ControlRondaInicialCliente;
import com.Accionable;

/**
 *
 * @author heril
 */
public class AccionableRondaInicial implements Accionable {

    private int idJugador;
    private int cantidadEjercitos;

    public AccionableRondaInicial(int idJugador, int cantidadEjercitos) {
        this.idJugador = idJugador;
        this.cantidadEjercitos = cantidadEjercitos;
    }
    
    @Override
    public void accionar() {
        ControlRondaInicialCliente.getInstance().ejecutarRondaInicial(idJugador, cantidadEjercitos);
    }
    
}
