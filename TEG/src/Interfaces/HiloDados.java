/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emanuel
 */
public class HiloDados extends Thread{
    private int[] ataque;
    private int[] defensa;
    private Dados dados;
    public HiloDados(int[] ataque, int[] defensa, Dados dados){
        this.ataque=ataque;
        this.defensa = defensa;
        this.dados = dados;
    }
   public void run()
   {
      int i = 0;
      dados.deshabilitar();
      while(i<15){
          try {
              dados.simularTirada(ataque.length, defensa.length);
              Thread.sleep(70);
              i++;
          } catch (InterruptedException ex) {
              Logger.getLogger(HiloDados.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      dados.cargarDados(ataque, defensa);
      
      
   } 
}
