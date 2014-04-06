/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import com.Accionable;
import juego.mecanicas.turno.SecuenciaTurnos;

/**
 *
 * @author Daniel
 */
public class AccionableFinTurno implements Accionable {

    public AccionableFinTurno() {
    }

    @Override
    public void accionar() {
        SecuenciaTurnos.getInstancia().siguienteTurno();
    }

}
