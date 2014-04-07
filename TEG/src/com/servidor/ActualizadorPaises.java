/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servidor;

import Interfaces.FachadaInterfacePrincipal;
import cliente.ClienteManager;
import com.Accionable;
import java.util.List;
import juego.estructura.GestorJugadores;
import juego.estructura.GestorPaises;
import juego.estructura.Jugador;
import juego.estructura.Pais;
import logger.LogItem;

/**
 *
 * @author Daniel
 */
public class ActualizadorPaises implements Accionable {

    private final List<Pais> listaPaises;

    public ActualizadorPaises(List<Pais> listaPaises) {
        this.listaPaises = listaPaises;
    }

    @Override
    public void accionar() {
        ClienteManager.getInstance().getLogger().addLogItem(
                new LogItem("Recibido actualizador con " + listaPaises.toString()));
        for (Pais paisServidor : listaPaises) {
            Pais paisCliente = GestorPaises.getPais(paisServidor.getNroPais());
            paisCliente.setCantidadEjercitos(paisServidor.getCantidadEjercitos());
            paisCliente.setCantidadMisiles(paisServidor.getCantidadMisiles());
            Jugador jugador = GestorJugadores.obtenerPorNumero(paisServidor.getJugador().getNroJugador());
            jugador.quitarPais(paisCliente);
            jugador.añadirPais(paisServidor);
            paisCliente.setJugador(jugador);
            ClienteManager.getInstance().getLogger().addLogItem(
                    new LogItem("Jugador cambiado: " + jugador.getNombre()));
            ClienteManager.getInstance().getLogger().addLogItem(
                    new LogItem("Pais cambiado " + paisCliente.getNombre() + ", " + paisCliente.getCantidadEjercitos()));
        }
                FachadaInterfacePrincipal.actualizarMapa();
    }

}
