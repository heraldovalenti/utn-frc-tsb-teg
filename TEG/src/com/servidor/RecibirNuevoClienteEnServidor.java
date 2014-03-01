/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servidor;

import com.Accionable;
import com.cliente.NotificarIdentificadorConexion;
import logger.LogItem;
import servidor.ConexionCliente;
import servidor.GestorClientes;
import servidor.ServerManager;

/**
 *
 * @author heril
 */
public class RecibirNuevoClienteEnServidor implements Accionable {

    private int id;
    private GestorClientes gestorClientes;
    
    public RecibirNuevoClienteEnServidor(int id, GestorClientes gestorClientes) {
        this.id = id;
        this.gestorClientes = gestorClientes;
    }
    
    @Override
    public void accionar() {
        NotificarIdentificadorConexion notificacion = new NotificarIdentificadorConexion(id);
        gestorClientes.enviarAccionable(notificacion, new ConexionCliente(id));
        ServerManager.getInstance().getLogger().addLogItem(new LogItem("Notificacion de identificador enviada (" + id + ")"));
    }
}
