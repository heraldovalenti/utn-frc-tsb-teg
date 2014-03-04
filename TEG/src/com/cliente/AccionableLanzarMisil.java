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
import com.servidor.ActualizadorPaises;
import servidor.ServerManager;

/**
 *
 * @author Daniel
 */
public class AccionableLanzarMisil implements Accionable {

    private final Pais origenCliente;
    private final Pais destinoCliente;

    public AccionableLanzarMisil(Pais origenCliente, Pais destinoCliente) {
        this.origenCliente = origenCliente;
        this.destinoCliente = destinoCliente;
    }

    @Override
    public void accionar() {
        Pais origenServidor = GestorPaises.getPais(origenCliente.getNroPais());
        Pais destinoServidor = GestorPaises.getPais(destinoCliente.getNroPais());
        origenServidor.restarMisiles(1);
        destinoServidor.restarEjercitos(4 - GestorPaises.calcularDistancia(origenServidor, destinoServidor));
        List<Pais> listaPaises = new ArrayList<>(2);
        listaPaises.add(origenServidor);
        listaPaises.add(destinoServidor);
        ActualizadorPaises actualizador = new ActualizadorPaises(listaPaises);
        ServerManager.getInstance().registrarSalida(actualizador);
    }

}
