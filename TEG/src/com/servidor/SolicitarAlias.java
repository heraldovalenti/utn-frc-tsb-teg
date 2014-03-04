/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servidor;

import com.Accionable;
import logger.LogItem;
import servidor.ServerManager;
import servidor.control.ReguladorAlias;

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
        ReguladorAlias reguladorAlias = new ReguladorAlias(id, alias);
        reguladorAlias.procesarSolicitudAlias();
    }
}
