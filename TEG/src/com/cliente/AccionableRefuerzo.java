/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import cliente.ClienteManager;
import com.Accionable;
import java.util.List;
import juego.estructura.GestorPaises;
import juego.estructura.Pais;
import com.servidor.ActualizadorPaises;
import java.util.ArrayList;
import logger.LogItem;
import servidor.ServerManager;

/**
 *
 * @author Daniel
 */
public class AccionableRefuerzo implements Accionable {

    private final List<Pais> listaPaises;

    public AccionableRefuerzo(List<Pais> listaPaises) {
        this.listaPaises = listaPaises;
    }

    @Override
    public void accionar() {
        ServerManager.getInstance().getLogger().addLogItem(
                new LogItem("Recibido refuerzo con " + listaPaises.toString()));
        List<Pais> listaActualizacionesPais = new ArrayList<>();
        for (Pais paisCliente : listaPaises) {
            Pais paisServidor = GestorPaises.getPais(paisCliente.getNroPais());
            paisServidor.setCantidadEjercitos(paisCliente.getCantidadEjercitos());
            paisServidor.setCantidadMisiles(paisCliente.getCantidadMisiles());
            listaActualizacionesPais.add(paisServidor);
        }
        ServerManager.getInstance().getLogger().addLogItem(
                new LogItem("Enviado actualizador con " + listaActualizacionesPais.toString()));
        ActualizadorPaises actualizador = new ActualizadorPaises(listaActualizacionesPais);
        ServerManager.getInstance().registrarSalida(actualizador);
    }
}
