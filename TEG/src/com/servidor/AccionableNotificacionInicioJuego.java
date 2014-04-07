/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servidor;

import cliente.control.ControlJuego;
import com.Accionable;

/**
 *
 * @author heril
 */
public class AccionableNotificacionInicioJuego implements Accionable {

    @Override
    public void accionar() {
        ControlJuego.notificacionInicioJuego();
    }
}
