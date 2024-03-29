/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import com.Accionable;
import com.servidor.AccionableMostrarTarjeta;
import com.servidor.ActualizadorJugador;
import juego.estructura.GestorJugadores;
import juego.estructura.GestorTarjetas;
import juego.estructura.Jugador;
import juego.estructura.Pais;
import juego.estructura.TarjetaPais;
import logger.LogItem;
import servidor.ServerManager;

/**
 *
 * @author Daniel
 */
public class AccionableSolicitarTarjetaPais implements Accionable {

    private final int nroJugador;

    public AccionableSolicitarTarjetaPais(Jugador jugador) {
        this.nroJugador = jugador.getNroJugador();
    }

    @Override
    public void accionar() {
        ServerManager.getInstance().getLogger().addLogItem(
                new LogItem("Recibida petición de tarjeta país"));
        Jugador jugador = GestorJugadores.obtenerPorNumero(nroJugador);
        //Se cambia el if por un for para debuguear mas rapido
        if (jugador.getCantidadTarjetasPais() < 5) {
            // for (int i = 0; i < 5; i++) {
            TarjetaPais tarjeta = GestorTarjetas.solicitarTarjetaPais();
            jugador.añadirTarjetaPais(tarjeta);
            ServerManager.getInstance().getLogger().addLogItem(
                    new LogItem("Tarjeta: " + tarjeta.getNombre()));
            ActualizadorJugador actualizador = new ActualizadorJugador(jugador);
            ServerManager.getInstance().registrarSalida(actualizador);
            Pais pais = tarjeta.getPais();
            if (jugador.getConjuntoPaises().contains(pais)) {
                pais.añadirEjercitos(3);
                ActualizadorJugador actualizadorPais = new ActualizadorJugador(jugador);
                ServerManager.getInstance().registrarSalida(actualizadorPais);
            }
            AccionableMostrarTarjeta accionable = new AccionableMostrarTarjeta(jugador, tarjeta);
            ServerManager.getInstance().registrarSalida(accionable);
        }
    }
}
