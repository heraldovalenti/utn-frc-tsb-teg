/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import com.Accionable;
import com.servidor.AccionableMensajeGlobal;
import com.servidor.ActualizadorPais;
import java.util.ArrayList;
import java.util.List;
import juego.estructura.GestorPaises;
import juego.estructura.Pais;
import logger.LogItem;
import servidor.ServerManager;

/**
 *
 * @author Daniel
 */
public class AccionableCanjePorEjercitos implements Accionable {

    private final Pais paisCliente;
    private final int cantidadMisiles;

    public AccionableCanjePorEjercitos(Pais paisCliente, int cantidadMisiles) {
        this.paisCliente = paisCliente;
        this.cantidadMisiles = cantidadMisiles;
    }

    @Override
    public void accionar() {
        Pais paisServidor = GestorPaises.getPais(paisCliente.getNroPais());
        ServerManager.getInstance().getLogger().addLogItem(
                new LogItem("Canje de misiles por ejércitos en " + paisServidor.getNombre()));
        paisServidor.añadirEjercitos(cantidadMisiles * 6);
        paisServidor.restarMisiles(cantidadMisiles);
        ActualizadorPais actualizador = new ActualizadorPais(paisServidor);
        ServerManager.getInstance().registrarSalida(actualizador);
    }
}
