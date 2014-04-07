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
            ServerManager.getInstance().getLogger().addLogItem(
                    new LogItem("Pais original cliente " + paisCliente.getNombre() + ", " + paisCliente.getCantidadEjercitos()));
            Pais paisServidor = GestorPaises.getPais(paisCliente.getNroPais());
            ServerManager.getInstance().getLogger().addLogItem(
                    new LogItem("Pais original servidor " + paisServidor.getNombre() + ", " + paisServidor.getCantidadEjercitos()));
            paisServidor.setCantidadEjercitos(paisCliente.getCantidadEjercitos());
            paisServidor.setCantidadMisiles(paisCliente.getCantidadMisiles());
            ServerManager.getInstance().getLogger().addLogItem(
                    new LogItem("Pais actualizado servidor " + paisServidor.getNroPais() + ", " + paisServidor.getCantidadEjercitos()));
            //listaActualizacionesPais.add(paisServidor);
            ActualizadorPais actualizador = new ActualizadorPais(paisServidor);
            ServerManager.getInstance().registrarSalida(actualizador);
        }
        //ServerManager.getInstance().getLogger().addLogItem(
        //       new LogItem("Enviado actualizador con " + listaActualizacionesPais.toString()));
        //ActualizadorPaises actualizador = new ActualizadorPaises(listaActualizacionesPais);
        //Pais paisServidor = listaActualizacionesPais.get(0);
        // ServerManager.getInstance().getLogger().addLogItem(
        //        new LogItem("Pais actualizado servidor " + paisServidor.getNombre() + ", " + paisServidor.getCantidadEjercitos()));
        //ServerManager.getInstance().registrarSalida(actualizador);
    }
}
