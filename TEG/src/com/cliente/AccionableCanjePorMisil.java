/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import com.Accionable;
import com.servidor.AccionableMensajeGlobal;
import java.util.ArrayList;
import java.util.List;
import juego.estructura.GestorPaises;
import juego.estructura.Pais;
import com.servidor.ActualizadorPais;
import servidor.ServerManager;

/**
 *
 * @author Daniel
 */
public class AccionableCanjePorMisil implements Accionable {

    private final Pais paisCliente;
    private final int cantidadMisiles;

    public AccionableCanjePorMisil(Pais paisCliente, int cantidadMisiles) {
        this.paisCliente = paisCliente;
        this.cantidadMisiles = cantidadMisiles;
    }

    @Override
    public void accionar() {
        Pais paisServidor = GestorPaises.getPais(paisCliente.getNroPais());
        paisServidor.restarEjercitos(cantidadMisiles * 6);
        paisServidor.añadirMisiles(cantidadMisiles);
        AccionableMensajeGlobal mensaje = new AccionableMensajeGlobal("Se han canjeado " + cantidadMisiles * 6 + " ejércitos por " + cantidadMisiles + " misiles en " + paisServidor.getNombre());
        ServerManager.getInstance().registrarSalida(mensaje);
        ActualizadorPais actualizador = new ActualizadorPais(paisServidor);
        ServerManager.getInstance().registrarSalida(actualizador);
    }

}
