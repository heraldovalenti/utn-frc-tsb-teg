/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import com.Accionable;
import juego.estructura.GestorPaises;
import juego.estructura.Pais;
import com.servidor.ActualizadorPais;
import logger.LogItem;
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
        if (origenServidor.getCantidadMisiles() > destinoServidor.getCantidadMisiles() && GestorPaises.calcularDistancia(origenServidor, destinoServidor) <= 3 && GestorPaises.calcularDistancia(origenServidor, destinoServidor) != 0) {
            ServerManager.getInstance().getLogger().addLogItem(
                    new LogItem("Misil lanzado desde " + origenServidor.getNombre() + " hacia " + destinoServidor.getNombre()));
            origenServidor.restarMisiles(1);
            destinoServidor.restarEjercitos(4 - GestorPaises.calcularDistancia(origenServidor, destinoServidor));
            if(destinoServidor.getCantidadEjercitos()<1){
                destinoServidor.setCantidadEjercitos(1);
            }
            ActualizadorPais actualizador = new ActualizadorPais(origenServidor);
            ServerManager.getInstance().registrarSalida(actualizador);
            actualizador = new ActualizadorPais(destinoServidor);
            ServerManager.getInstance().registrarSalida(actualizador);
        }
    }

}
