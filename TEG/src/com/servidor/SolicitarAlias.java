/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servidor;

import com.Accionable;
import logger.LogItem;
import servidor.ServerManager;

/**
 *
 * @author heril
 */
public class SolicitarAlias implements Accionable {

    private int id;
    private String alias;

    public SolicitarAlias(int id, String alias) {
        this.id = id;
        this.alias = alias;
    }

    @Override
    public void accionar() {
        boolean res = ServerManager.getInstance().procesarSolicitudAlias(id, alias);
        StringBuilder logItem = new StringBuilder("Solicitud alias procesada: [" + alias + "] ");
        if (res) {
            logItem.append("aceptado");
        } else {
            logItem.append("rechazado");
        }
        ServerManager.getInstance().getLogger().addLogItem(new LogItem(logItem.toString()));
    }
}
