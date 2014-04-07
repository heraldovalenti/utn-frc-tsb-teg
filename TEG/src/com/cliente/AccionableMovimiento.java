/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import com.Accionable;
import java.util.ArrayList;
import java.util.List;
import juego.estructura.GestorPaises;
import juego.estructura.Pais;
import juego.mecanicas.movimiento.ControlMovimiento;
import juego.mecanicas.movimiento.Movimiento;
import com.servidor.ActualizadorPaises;
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
        ControlMovimiento control = new ControlMovimiento(origenServidor, destinoServidor, cantidadEjercitos, cantidadMisiles);
        if (control.movimientoValido()) {
            Movimiento movimiento = new Movimiento(origenServidor, destinoServidor, cantidadEjercitos, cantidadMisiles);
            movimiento.ejecutarMovimiento();
            List<Pais> listaPaises = new ArrayList<>(2);
            listaPaises.add(origenServidor);
            listaPaises.add(destinoServidor);
            ActualizadorPaises actualizador = new ActualizadorPaises(listaPaises);
            ServerManager.getInstance().registrarSalida(actualizador);
        }
    }

}
