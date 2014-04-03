/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import cliente.ClienteManager;
import com.Accionable;
import java.util.Date;
import logger.LogChat;
import logger.LogItem;
import servidor.ServerManager;

/**
 *
 * @author heril
 */
public class AccionableChat implements Accionable {

    private boolean procesadoEnServidor;
    private int idCliente;
    private String aliasJugador;
    private String mensaje;
    private Date timeStamp;

    public AccionableChat(int idCliente, String aliasJugador, String mensaje) {
        this.idCliente = idCliente;
        this.aliasJugador = aliasJugador;
        this.mensaje = mensaje;
    }
    
    @Override
    public void accionar() {
        if (procesadoEnServidor) {
            mostrarChat();
        } else {
            procesarChat();
        }
        
    }
    
    private void procesarChat() {
        timeStamp = new Date();
        procesadoEnServidor = true;
        ServerManager.getInstance().getLogger().addLogItem(new LogChat(mensaje, aliasJugador, timeStamp));
        ServerManager.getInstance().registrarSalida(this);
    }
    
    private void mostrarChat() {
        ClienteManager.getInstance().getLogger().addLogItem(new LogChat(mensaje, aliasJugador, timeStamp));
    }
        
}
