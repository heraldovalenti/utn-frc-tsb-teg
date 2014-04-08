/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servidor;

import com.Accionable;
import juego.Juego;
import juego.mecanicas.situacion.Situacion;

/**
 *
 * @author Daniel
 */
public class AccionableSituacion implements Accionable {

    private Situacion situacion;

    public AccionableSituacion(Situacion situacion) {
        this.situacion = situacion;
    }

    @Override
    public void accionar() {
        Juego.getInstancia().setSituacion(situacion);
    }
}
