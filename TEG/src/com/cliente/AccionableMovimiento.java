/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import com.Accionable;
import juego.estructura.GestorPaises;
import juego.estructura.Pais;
import juego.mecanicas.movimiento.ControlMovimiento;
import juego.mecanicas.movimiento.Movimiento;
import com.servidor.ActualizadorPais;
import juego.mecanicas.movimiento.ControlMovimientosJugador;
import juego.mecanicas.situacion.GestorSituacion;
import juego.mecanicas.situacion.Situacion;
import logger.LogItem;
import servidor.ServerManager;

/**
 *
 * @author Daniel
 */
public class AccionableMovimiento implements Accionable {

    private final Pais origenCliente;
    private final Pais destinoCliente;
    private final int cantidadEjercitos;
    private final int cantidadMisiles;

    public AccionableMovimiento(Pais origenCliente, Pais destinoCliente, int cantidadEjercitos, int cantidadMisiles) {
        this.origenCliente = origenCliente;
        this.destinoCliente = destinoCliente;
        this.cantidadEjercitos = cantidadEjercitos;
        this.cantidadMisiles = cantidadMisiles;
    }

    @Override
    public void accionar() {
        Pais origenServidor = GestorPaises.getPais(origenCliente.getNroPais());
        Pais destinoServidor = GestorPaises.getPais(destinoCliente.getNroPais());
        ServerManager.getInstance().getLogger().addLogItem(
                new LogItem("Movimiento desde " + origenServidor.getNombre() + " hacia " + destinoServidor.getNombre() + ". Ejércitos: " + cantidadEjercitos + ", misiles: " + cantidadMisiles));
        Situacion situacion = GestorSituacion.getInstance().getSituacionActual();
        ControlMovimiento control = new ControlMovimiento(origenServidor, destinoServidor, cantidadEjercitos, cantidadMisiles, situacion, new ControlMovimientosJugador());
        if (control.movimientoValido()) {
            Movimiento movimiento = new Movimiento(origenServidor, destinoServidor, cantidadEjercitos, cantidadMisiles);
            movimiento.ejecutarMovimiento();
            ActualizadorPais actualizador = new ActualizadorPais(origenServidor);
            ServerManager.getInstance().registrarSalida(actualizador);
            actualizador = new ActualizadorPais(destinoServidor);
            ServerManager.getInstance().registrarSalida(actualizador);
        }
    }

}
