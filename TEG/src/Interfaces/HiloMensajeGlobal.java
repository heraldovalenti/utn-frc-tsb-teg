/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaces;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author Emanuel
 */
public class HiloMensajeGlobal extends Thread{  
   private JTextArea txtArea;
   private String mensaje;
   public HiloMensajeGlobal(){
       
   }
   public HiloMensajeGlobal(JTextArea txtArea, String msj){
      this.txtArea = txtArea;
      this.mensaje = msj;
   }
   public void run()
   {
       try {
           txtArea.setVisible(true);
           txtArea.setText(mensaje);
           Thread.sleep(3000);
           txtArea.setText("");
           txtArea.setVisible(false);
       } catch (InterruptedException ex) {
          ex.printStackTrace();
       }
   }  
   
}

