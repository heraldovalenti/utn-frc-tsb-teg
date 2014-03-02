/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servidor;

import com.Accionable;
import servidor.ServerManager;

/**
 *
 * @author heril
 */
public class EnviarChat implements Accionable {

    public EnviarChat(String chat) {
        this.chat = chat;
    }
    
    private String chat;
    
    @Override
    public void accionar() {
        ServerManager.getInstance().procesarChat(chat);
    }
    
}
