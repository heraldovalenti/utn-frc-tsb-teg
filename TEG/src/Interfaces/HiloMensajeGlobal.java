/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaces;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Emanuel
 */
public class HiloMensajeGlobal extends Thread{  
   private JTextArea txtArea;
   private String mensaje;
   private JScrollPane scroll;
   public HiloMensajeGlobal(){
       
   }
   public HiloMensajeGlobal(JTextArea txtArea,JScrollPane scroll, String msj){
      this.txtArea = txtArea;
      this.mensaje = msj;
      this.scroll = scroll;
   }
   public void run()
   {
       try {
           txtArea.setVisible(true);
           txtArea.setText(mensaje);
           scroll.setVisible(true);
           Thread.sleep(3000);
           txtArea.setText("");
           txtArea.setVisible(false);
           scroll.setVisible(false);
       } catch (InterruptedException ex) {
          ex.printStackTrace();
       }
   }  
   
}

