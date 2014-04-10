/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import com.Accionable;
import com.servidor.ActualizadorPais;
import java.util.ArrayList;
import java.util.List;
import juego.estructura.GestorPaises;
import juego.estructura.Pais;
import servidor.ServerManager;
import servidor.control.ControlRondaInicial;

/**
 *
 * @author heril
 */
public class AccionableRefuerzoRondaInicial implements Accionable {

    private final List<Pais> listaPaises;

    public AccionableRefuerzoRondaInicial(List<Pais> listaPaises) {
        this.listaPaises = listaPaises;
    }

    @Override
    public void accionar() {
        List<Pais> listaActualizacionesPais = new ArrayList<>();
        for (Pais paisCliente : listaPaises) {
            Pais paisServidor = GestorPaises.getPais(paisCliente.getNroPais());
            paisServidor.setCantidadEjercitos(paisCliente.getCantidadEjercitos());
            paisServidor.setCantidadMisiles(paisCliente.getCantidadMisiles());
            listaActualizacionesPais.add(paisServidor);
            ActualizadorPais actualizador = new ActualizadorPais(paisServidor);
            ServerManager.getInstance().registrarSalida(actualizador);
        }

        ControlRondaInicial.getInstance().finTurnoJugador();
    }
}
