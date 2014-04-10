/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servidor;

import Interfaces.FachadaInterfacePrincipal;
import cliente.ClienteManager;
import com.Accionable;
import juego.estructura.Canjeable;
import juego.estructura.GestorTarjetas;
import juego.estructura.Jugador;

/**
 *
 * @author Daniel
 */
public class AccionableMostrarTarjeta implements Accionable {

    private final int nroTarjeta;
    private final int nroJugador;
    private final boolean esDePais;

    public AccionableMostrarTarjeta(Jugador jugador, Canjeable tarjeta) {
        this.nroTarjeta = tarjeta.getNumero();
        this.esDePais = tarjeta.esDePais();
        this.nroJugador = jugador.getNroJugador();
    }

    @Override
    public void accionar() {
        if (ClienteManager.getInstance().esJugadorLocal(nroJugador)) {
            Canjeable tarjeta;
            if (esDePais) {
                tarjeta = GestorTarjetas.getTarjetaPais(nroTarjeta);
            } else {
                tarjeta = GestorTarjetas.getTarjetaContinente(nroTarjeta);
            }
            FachadaInterfacePrincipal.mostrarTarjeta(tarjeta);
        }
    }

}
