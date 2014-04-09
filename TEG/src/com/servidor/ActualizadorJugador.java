/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servidor;

import Interfaces.FachadaInterfacePrincipal;
import cliente.ClienteManager;
import com.Accionable;
import java.util.ArrayList;
import java.util.List;
import juego.estructura.GestorJugadores;
import juego.estructura.GestorTarjetas;
import juego.estructura.Jugador;
import logger.LogItem;
import servidor.ServerManager;

/**
 *
 * @author Daniel
 */
public class ActualizadorJugador implements Accionable {

    private final int nroJugador;
    private final int[] tarjetasPais;
    private final int[] tarjetasContinentes;
    private final int cantidadCanjes;

    public ActualizadorJugador(Jugador jugador) {
        this.nroJugador = jugador.getNroJugador();
        this.tarjetasPais = new int[jugador.getCantidadTarjetasPais()];
        this.tarjetasContinentes = new int[jugador.getCantidadTarjetasContinente()];
        this.cantidadCanjes = jugador.getCantidadCanjes();
        for (int i = 0; i < tarjetasPais.length; i++) {
            tarjetasPais[i] = jugador.getListaTarjetasPais().get(i).getNroTarjeta();
        }
        for (int i = 0; i < tarjetasContinentes.length; i++) {
            tarjetasContinentes[i] = jugador.getListaTarjetaContinentes().get(i).getNroTarjeta();
        }
        ServerManager.getInstance().getLogger().addLogItem(
                new LogItem("Actualizado jugador. Nombre: " + jugador.getNombre() + " Tarjetas pais: " + jugador.getListaTarjetasPais()));
    }

    @Override
    public void accionar() {
        Jugador jugador = GestorJugadores.obtenerPorNumero(nroJugador);
        jugador.setCantidadCanjes(cantidadCanjes);
        List listaTarjetasPais = new ArrayList(5);
        for (int i = 0; i < tarjetasPais.length; i++) {
            listaTarjetasPais.add(GestorTarjetas.getTarjetaPais(tarjetasPais[i]));
        }
        jugador.setListaTarjetasPais(listaTarjetasPais);
        List listaTarjetasContinente = new ArrayList(7);
        for (int i = 0; i < tarjetasContinentes.length; i++) {
            listaTarjetasPais.add(GestorTarjetas.getTarjetaContinente(tarjetasContinentes[i]));
        }
        jugador.setListaTarjetaContinentes(listaTarjetasContinente);
        ClienteManager.getInstance().getLogger().addLogItem(
                new LogItem("Actualizado jugador. Nombre: " + jugador.getNombre() + " Tarjetas pais: " + jugador.getListaTarjetasPais()));

    }

}
