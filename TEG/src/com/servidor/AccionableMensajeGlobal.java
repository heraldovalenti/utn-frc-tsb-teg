/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servidor;

import Interfaces.FachadaInterfacePrincipal;
import com.Accionable;

/**
 *
 * @author Daniel
 */
public class AccionableMensajeGlobal implements Accionable {

    private final String mensaje;

    public AccionableMensajeGlobal(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public void accionar() {
        FachadaInterfacePrincipal.mostrarMensaje(mensaje);
    }

}
