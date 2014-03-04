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
import com.servidor.ActualizadorPaises;
import servidor.ServerManager;

/**
 *
 * @author Daniel
 */
public class AccionableCanjePorEjercitos implements Accionable {

    private final Pais paisCliente;
    private final int cantidadMisiles;

    public AccionableCanjePorEjercitos(Pais paisCliente, int cantidadMisiles) {
        this.paisCliente = paisCliente;
        this.cantidadMisiles = cantidadMisiles;
    }

    @Override
    public void accionar() {
        Pais paisServidor = GestorPaises.getPais(paisCliente.getNroPais());
        paisServidor.añadirEjercitos(cantidadMisiles * 6);
        paisServidor.restarMisiles(cantidadMisiles);
        AccionableMensajeGlobal mensaje = new AccionableMensajeGlobal("Se han canjeado " + cantidadMisiles + " misiles por " + cantidadMisiles * 6 + " ejércitos en " + paisServidor.getNombre());
        ServerManager.getInstance().registrarSalida(mensaje);
        List listaPaises = new ArrayList(1);
        listaPaises.add(paisServidor);
        ActualizadorPaises actualizador = new ActualizadorPaises(listaPaises);
        ServerManager.getInstance().registrarSalida(actualizador);
    }
}
