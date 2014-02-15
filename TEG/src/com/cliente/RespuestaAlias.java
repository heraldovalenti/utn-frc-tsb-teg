/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import cliente.ClienteManager;
import cliente.control.ControlAlias;
import com.Accionable;

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
        int idConexion = ClienteManager.getInstance().getConexionServidor().getConexionId();
        ControlAlias controlAlias = ClienteManager.getInstance().getControlAlias();
        if (id != idConexion) {
            return;
        }
        controlAlias.respuestaServidor(aceptado);
    }
}
