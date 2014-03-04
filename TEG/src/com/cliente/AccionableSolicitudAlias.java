/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import cliente.control.ControlAlias;
import com.Accionable;
import servidor.ServerManager;
import servidor.control.ControladorAlias;

/**
 *
 * @author heril
 */
public class AccionableSolicitudAlias implements Accionable {

    private int id;
    private String aliasSolicitado;
    private String aliasAsignado;

    public AccionableSolicitudAlias(int id, String alias) {
        this.id = id;
        this.aliasSolicitado = alias;
        aliasAsignado = null;
    }

    @Override
    public void accionar() {
        if (aliasAsignado != null) {
            procesarRespuesta();
        } else {
            procesarSolicitud();
        }
    }
    
    private void procesarSolicitud() {
        boolean disponible = ControladorAlias.aliasDisponible(aliasSolicitado);
        if (disponible && aliasSolicitado != null) {
            ControladorAlias.asignarAlias(id, aliasSolicitado);
            aliasAsignado = aliasSolicitado;
        } else {
            String aliasAleatorio;
            do {
                int rnd = (int)(Math.random() * 1000);
                aliasAleatorio = "Jugador " + rnd;
            } while (!ControladorAlias.aliasDisponible(aliasAleatorio));
            ControladorAlias.asignarAlias(id, aliasAleatorio);
            aliasAsignado = aliasAleatorio;
        }
        ServerManager.getInstance().registrarSalida(this);
    }
    
    private void procesarRespuesta() {
        ControlAlias ca = new ControlAlias();
        ca.asignarAlias(id, aliasAsignado, aliasSolicitado);
    }
}
