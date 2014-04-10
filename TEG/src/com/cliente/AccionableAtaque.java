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
import logger.LogItem;
import servidor.ServerManager;
import servidor.control.ControlVictoria;

/**
 *
 * @author Daniel
 */
public class AccionableAtaque implements Accionable {

    private final Pais origenCliente;
    private final Pais destinoCliente;
    private ControlAtaque control;

    public AccionableAtaque(Pais origenCliente, Pais destinoCliente) {
        this.origenCliente = origenCliente;
        this.destinoCliente = destinoCliente;
    }

    public AccionableAtaque(Pais origenCliente, Pais destinoCliente, ControlAtaque control) {
        this.origenCliente = origenCliente;
        this.destinoCliente = destinoCliente;
        this.control = control;
    }

    @Override
    public void accionar() {
        Pais origenServidor = GestorPaises.getPais(origenCliente.getNroPais());
        Pais destinoServidor = GestorPaises.getPais(destinoCliente.getNroPais());
        ServerManager.getInstance().getLogger().addLogItem(
                new LogItem("Ataque desde " + origenCliente.getNombre() + " a " + destinoServidor.getNombre()));
        if (control == null) {
            control = new ControlAtaque(origenServidor, destinoServidor);
        }
        if (control.ataqueValido()) {
            boolean conquistado = false;
            boolean mostrarTarjetaContinente = false;
            int ejercitosAtacantes = control.ataquePermitido();
            int ejercitosDefensores = control.defensaPermitida();
            if (!control.ataqueRealizado()) {
                control.atacar(ejercitosAtacantes, ejercitosDefensores);
            }
            AccionableMensajeGlobal mensaje = new AccionableMensajeGlobal(origenServidor.getNombre() + " ataca a " + destinoServidor.getNombre());
            ServerManager.getInstance().registrarSalida(mensaje);
            AccionableMostrarDadosAtaque dados = new AccionableMostrarDadosAtaque(origenServidor.getJugador().getNombre(), destinoServidor.getJugador().getNombre(), control.dadosAtacante(), control.dadosDefensor());
            ServerManager.getInstance().registrarSalida(dados);
            destinoServidor.restarEjercitos(control.perdidasDefensor());
            origenServidor.restarEjercitos(control.perdidasAtacante());
            if (destinoServidor.getCantidadEjercitos() < 1) {
                origenServidor.restarEjercitos(1);
                destinoServidor.ocuparPais(origenServidor.getJugador());
                conquistado = true;
            }
            ActualizadorPais actualizador = new ActualizadorPais(origenServidor);
            ServerManager.getInstance().registrarSalida(actualizador);
            actualizador = new ActualizadorPais(destinoServidor);
            ServerManager.getInstance().registrarSalida(actualizador);
            if (conquistado) {
                if (ControlVictoria.comprobarVictoria()) {
                    return;
                }
                resolverTarjetasContinente(origenCliente, destinoCliente, origenCliente.getJugador(), origenServidor.getJugador());
                int maximoEjercitos = Math.min(origenServidor.getCantidadEjercitos() - 1, 2);
                AccionableSolicitarMovimientoPaisGanado movimiento = new AccionableSolicitarMovimientoPaisGanado(origenServidor.getJugador(), origenServidor, destinoServidor, maximoEjercitos);
                ServerManager.getInstance().registrarSalida(movimiento);
            }
        }
    }

    private void resolverTarjetasContinente(Pais origen, Pais destino, Jugador atacante, Jugador defensor) {
        if (!origen.getContinente().equals(destino.getContinente())) {
            return;
        }
        Continente continente = origen.getContinente();
        TarjetaContinente tarjeta = GestorTarjetas.obtenerPorContinente(continente);
        int cantidadPaisesAtacante = atacante.calcularCantidadDePaisesDeContinente(continente);
        if (cantidadPaisesAtacante == GestorContinentes.obtenerCantidadPaises(continente)) {
            GestorTarjetas.entregarTarjetaContinente(atacante, tarjeta);
            AccionableMostrarTarjeta mostrar = new AccionableMostrarTarjeta(atacante, tarjeta);
            ServerManager.getInstance().registrarSalida(mostrar);
        }
        if (tarjeta.getJugadorActual().equals(defensor)) {
            defensor.devolverTarjetaContinente(tarjeta);
        }
    }
}
