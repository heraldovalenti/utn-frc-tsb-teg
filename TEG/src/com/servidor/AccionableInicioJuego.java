/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servidor;

import cliente.ClienteManager;
import com.Accionable;
import java.util.List;
import java.util.Set;
import juego.Juego;
import juego.estructura.GestorJugadores;
import juego.estructura.GestorPaises;
import juego.estructura.Jugador;
import juego.estructura.Pais;
import juego.mecanicas.turno.SecuenciaTurnos;
import logger.LogItem;

/**
 *
 * @author heril
 */
public class AccionableInicioJuego implements Accionable {

    private Juego juego;
    private Set<Jugador> jugadores;
    private List<Jugador> secuenciaTurnos;
    private List<Pais> paises;

    public AccionableInicioJuego(Juego juego, Set<Jugador> jugadores, List<Jugador> secuenciaTurnos, List<Pais> paises) {
        this.juego = juego;
        this.jugadores = jugadores;
        this.secuenciaTurnos = secuenciaTurnos;
        this.paises = paises;
    }

    @Override
    public void accionar() {
        ClienteManager.getInstance().getJuego().setIdJuego(juego.getIdJuego());
        GestorJugadores.setJugadores(jugadores);
        GestorPaises.setPaises(paises);
        SecuenciaTurnos.getInstancia().setSecuencia(secuenciaTurnos);
        ClienteManager.getInstance().getLogger().addLogItem(new LogItem("Juego inicializado."));
    }
}
