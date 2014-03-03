/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servidor;

import cliente.control.ControlColor;
import com.Accionable;
import java.awt.Color;
import servidor.ServerManager;
import servidor.control.ControlColores;

/**
 *
 * @author heril
 */
public class SolicitudColor implements Accionable {
    
    private int idJugador;
    private Color colorSolicitado;
    private boolean solicitudProcesada;

    public SolicitudColor(int idJugador, Color colorSolicitado) {
        this.idJugador = idJugador;
        this.colorSolicitado = colorSolicitado;
        this.solicitudProcesada = false;
    }
    
    @Override
    public void accionar() {
        if (solicitudProcesada) {
            procesarRespuesta();
        } else {
            procesarSolicitud();
        }
    }
    
    /**
     * Metodo a ejecutarse en el servidor.
     */
    private void procesarSolicitud() {
        if (colorSolicitado != null) {
            if (ControlColores.colorDisponible(colorSolicitado)) {
                ControlColores.asignarColor(idJugador, colorSolicitado);
            } else {
                colorSolicitado = null;
            }
        } else {
            ControlColores.asignarColor(idJugador);
            colorSolicitado = ControlColores.getColorJugador(idJugador);
        }
        ServerManager.getInstance().registrarSalida(this);
        solicitudProcesada = true;
    }
    
    /**
     * Metodo a ejecutarse en el cliente.
     */
    private void procesarRespuesta() {
        ControlColor cc = new ControlColor();
        if (colorSolicitado != null) {    
            cc.asignarColor(idJugador, colorSolicitado);
        } else {
            //ERROR: no se asigno ningun color en el servidor.
            cc.errorEnAsignacion();
        }
    }
}
