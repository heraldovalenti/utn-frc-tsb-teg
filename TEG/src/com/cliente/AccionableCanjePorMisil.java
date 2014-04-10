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
import com.servidor.ActualizadorPais;
import logger.LogItem;
import servidor.ServerManager;

/**
 *
 * @author Daniel
 */
public class AccionableCanjePorMisil implements Accionable {

    private final Pais paisCliente;
    private final int cantidadMisiles;

    public AccionableCanjePorMisil(Pais paisCliente, int cantidadMisiles) {
        this.paisCliente = paisCliente;
        this.cantidadMisiles = cantidadMisiles;
    }

    @Override
    public void accionar() {
        Pais paisServidor = GestorPaises.getPais(paisCliente.getNroPais());
        ServerManager.getInstance().getLogger().addLogItem(
                new LogItem("Canje de ejércitos por misiles en " + paisServidor));
        paisServidor.restarEjercitos(cantidadMisiles * 6);
        paisServidor.añadirMisiles(cantidadMisiles);
        ActualizadorPais actualizador = new ActualizadorPais(paisServidor);
        ServerManager.getInstance().registrarSalida(actualizador);
    }

}
