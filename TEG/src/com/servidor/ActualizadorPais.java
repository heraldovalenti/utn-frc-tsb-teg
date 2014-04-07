/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servidor;

import Interfaces.FachadaInterfacePrincipal;
import cliente.ClienteManager;
import com.Accionable;
import juego.estructura.GestorJugadores;
import juego.estructura.GestorPaises;
import juego.estructura.Jugador;
import juego.estructura.Pais;
import logger.LogItem;

/**
 *
 * @author Daniel
 */
public class ActualizadorPais implements Accionable {

    private final int nroPais;
    private final int nroJugador;
    private final int cantidadEjercitos;
    private final int cantidadMisiles;

    public ActualizadorPais(Pais pais) {
        this.nroPais = pais.getNroPais();
        this.nroJugador = pais.getJugador().getNroJugador();
        this.cantidadEjercitos = pais.getCantidadEjercitos();
        this.cantidadMisiles = pais.getCantidadMisiles();
    }

    @Override
    public void accionar() {
        ClienteManager.getInstance().getLogger().addLogItem(
                new LogItem("Actualizador recibido: Pais:" + GestorPaises.getPais(nroPais) + " ejercitos: " + cantidadEjercitos + " jugador: " + nroJugador));
        Pais paisCliente = GestorPaises.getPais(nroPais);
        ClienteManager.getInstance().getLogger().addLogItem(
                new LogItem("Original: " + paisCliente));
        Jugador jugadorCliente = GestorJugadores.obtenerPorNumero(nroJugador);
        jugadorCliente.añadirPais(paisCliente);
        paisCliente.setJugador(jugadorCliente);
        paisCliente.setCantidadEjercitos(cantidadEjercitos);
        paisCliente.setCantidadMisiles(cantidadMisiles);
        ClienteManager.getInstance().getLogger().addLogItem(
                new LogItem("Actualizado: " + paisCliente));
        FachadaInterfacePrincipal.actualizarMapa();
    }

}
