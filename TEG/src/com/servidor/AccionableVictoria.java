/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servidor;

import Interfaces.FachadaInterfacePrincipal;
import cliente.ClienteManager;
import com.Accionable;
import juego.estructura.Jugador;
import juego.mecanicas.turno.GestorTurno;

/**
 *
 * @author Daniel
 */
public class AccionableVictoria implements Accionable {

    private final int nroJugador;
    private final String mensaje;

    public AccionableVictoria(Jugador jugador, String mensaje) {
        this.nroJugador = jugador.getNroJugador();
        this.mensaje = mensaje;
    }

    @Override
    public void accionar() {
        if (ClienteManager.getInstance().esJugadorLocal(nroJugador)) {
            FachadaInterfacePrincipal.victoria(mensaje);
        } else {
            FachadaInterfacePrincipal.derrota(mensaje);
        }
        GestorTurno.getInstance().setEtapaActual(GestorTurno.FUERA_TURNO);
        FachadaInterfacePrincipal.actualizarEstadoBotones();
        //TODO: Finalizar el juego
    }

}
