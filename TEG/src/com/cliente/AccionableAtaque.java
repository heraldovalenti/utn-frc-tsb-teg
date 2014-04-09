/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import com.Accionable;
import com.servidor.AccionableMensajeGlobal;
import juego.estructura.GestorPaises;
import juego.estructura.Pais;
import juego.mecanicas.ataque.ControlAtaque;
import com.servidor.AccionableMostrarDadosAtaque;
import com.servidor.AccionableMostrarTarjeta;
import com.servidor.AccionableSolicitarMovimientoPaisGanado;
import com.servidor.ActualizadorPais;
import juego.estructura.Continente;
import juego.estructura.GestorContinentes;
import juego.estructura.GestorTarjetas;
import juego.estructura.Jugador;
import juego.estructura.TarjetaContinente;
import servidor.ServerManager;
import servidor.control.ControlVictoria;

/**
 *
 * @author Daniel
 */
public class AccionableAtaque implements Accionable {

    private final Pais origenCliente;
    private final Pais destinoCliente;

    public AccionableAtaque(Pais origenCliente, Pais destinoCliente) {
        this.origenCliente = origenCliente;
        this.destinoCliente = destinoCliente;
    }

    @Override
    public void accionar() {
        Pais origenServidor = GestorPaises.getPais(origenCliente.getNroPais());
        Pais destinoServidor = GestorPaises.getPais(destinoCliente.getNroPais());
        ControlAtaque control = new ControlAtaque(origenServidor, destinoServidor);
        if (control.ataqueValido()) {
            boolean conquistado = false;
            boolean mostrarTarjetaContinente = false;
            int ejercitosAtacantes = control.ataquePermitido();
            int ejercitosDefensores = control.defensaPermitida();
            control.atacar(ejercitosAtacantes, ejercitosDefensores);
            AccionableMensajeGlobal mensaje = new AccionableMensajeGlobal(origenServidor.getNombre() + " ataca a " + destinoServidor.getNombre());
            ServerManager.getInstance().registrarSalida(mensaje);
            AccionableMostrarDadosAtaque dados = new AccionableMostrarDadosAtaque(origenServidor.getJugador().getNombre(), destinoServidor.getJugador().getNombre(), control.dadosAtacante(), control.dadosDefensor());
            ServerManager.getInstance().registrarSalida(dados);
            destinoServidor.restarEjercitos(control.perdidasDefensor());
            origenServidor.restarEjercitos(control.perdidasAtacante());
            if (destinoServidor.getCantidadEjercitos() < 1) {
                mostrarTarjetaContinente = mostrarNuevaTarjetaDeContinente(origenServidor, destinoServidor);
                origenServidor.restarEjercitos(1);
                destinoServidor.ocuparPais(origenServidor.getJugador());
                conquistado = true;
            }
            ActualizadorPais actualizador = new ActualizadorPais(origenServidor);
            ServerManager.getInstance().registrarSalida(actualizador);
            actualizador = new ActualizadorPais(destinoServidor);
            ServerManager.getInstance().registrarSalida(actualizador);
            if (conquistado) {
                ControlVictoria.comprobarVictoria();
                if (mostrarTarjetaContinente) {
                    TarjetaContinente tarjeta = GestorTarjetas.obtenerPorContinente(origenServidor.getContinente());
                    AccionableMostrarTarjeta mostrar = new AccionableMostrarTarjeta(origenServidor.getJugador(), tarjeta);
                    ServerManager.getInstance().registrarSalida(mostrar);
                }
                int maximoEjercitos = Math.min(origenServidor.getCantidadEjercitos() - 1, 2);
                AccionableSolicitarMovimientoPaisGanado movimiento = new AccionableSolicitarMovimientoPaisGanado(origenServidor.getJugador(), origenServidor, destinoServidor, maximoEjercitos);
                ServerManager.getInstance().registrarSalida(movimiento);
            }
        }
    }

    private boolean mostrarNuevaTarjetaDeContinente(Pais origen, Pais destino) {
        Jugador jugador = origen.getJugador();
        Continente continente = origen.getContinente();
        if (destino.getContinente().equals(continente)) {
            int cantidad = origen.getJugador().calcularCantidadDePaisesDeContinente(continente);
            if (cantidad == GestorContinentes.obtenerCantidadPaises(continente) - 1) {
                TarjetaContinente tarjeta = GestorTarjetas.obtenerPorContinente(continente);
                if (tarjeta.fueUsada(jugador)) {
                    return false;
                } else {
                    return jugador.añadirTarjetaContinente(tarjeta);
                }
            }
        }
        return false;
    }
}
