/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import cliente.ClienteManager;
import com.Accionable;
import logger.LogItem;

/**
 *
 * @author heril
 */
public class RespuestaAlias implements Accionable {

    private boolean aceptado;
    private int id;

    public RespuestaAlias(int id, boolean aceptado) {
        this.id = id;
        this.aceptado = aceptado;
    }


    @Override
    public void accionar() {
        if (ClienteManager.getInstance().getIdentificadorConexion() != this.id) {
            return;
        }
        StringBuilder msg = new StringBuilder("Solicitud de alias ");
        if (aceptado) {
            msg.append("aceptada");
        } else {
            msg.append("rechazada");
        }
        ClienteManager.getInstance().getLogger().addLogItem(new LogItem(msg.toString()));
        if (!aceptado) {
            ClienteManager.getInstance().getControlAlias().ejecutarControlAlias();
        }
    }
    
    
    
}
