/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import com.Accionable;
import com.servidor.ActualizadorPais;
import java.util.List;
import juego.estructura.GestorPaises;
import juego.estructura.Pais;
import java.util.ArrayList;
import logger.LogItem;
import servidor.ServerManager;

/**
 *
 * @author Daniel
 */
public class AccionableRefuerzo implements Accionable {

    private final int nroPais;
    private final int cantidadEjercitos;
    private final int cantidadMisiles;

    public AccionableRefuerzo(Pais pais) {
        this.nroPais = pais.getNroPais();
        this.cantidadEjercitos = pais.getCantidadEjercitos();
        this.cantidadMisiles = pais.getCantidadMisiles();
    }

    @Override
    public void accionar() {
        ServerManager.getInstance().getLogger().addLogItem(
                new LogItem("Recibido refuerzo con " + GestorPaises.getPais(nroPais) + " ejercitos: " + cantidadEjercitos + " misiles: " + cantidadMisiles));
        Pais paisServidor = GestorPaises.getPais(nroPais);
        ServerManager.getInstance().getLogger().addLogItem(
                new LogItem("Original servidor: " + paisServidor));
        paisServidor.setCantidadEjercitos(cantidadEjercitos);
        paisServidor.setCantidadMisiles(cantidadMisiles);
        ServerManager.getInstance().getLogger().addLogItem(
                new LogItem("Actualizado servidor: " + paisServidor));
        ActualizadorPais actualizador = new ActualizadorPais(paisServidor);
        ServerManager.getInstance().registrarSalida(actualizador);
    }
}
