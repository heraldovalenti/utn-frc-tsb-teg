/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import cliente.ClienteManager;
import com.Accionable;

/**
 *
 * @author heril
 */
public class RecibirChat implements Accionable {

    public RecibirChat(String chat) {
        this.chat = chat;
    }
    
    private String chat;
    
    @Override
    public void accionar() {
        ClienteManager.getInstance().recibirChat(chat);
    }
        
}
