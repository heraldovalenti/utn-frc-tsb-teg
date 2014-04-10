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
public class HiloSonido extends Thread{
   public boolean esMusicaFondo;
   private String ruta;
   public HiloSonido(){
       esMusicaFondo = true;
   }
   public HiloSonido(String ruta){
       this.esMusicaFondo = false;
       this.ruta = ruta;
   }
   public void run()
   {
       if(esMusicaFondo){
             while(true){
                ReproducirSonido.reproducir(obtenerRuta());
            }
       }
       else{
           int a= 0;
           while(a<2){
                ReproducirSonido.reproducir(ruta);
                a++;
           }
          
       }
     
       
   }  
   private String obtenerRuta(){
       String ruta = "src/Sonidos/musicaJuego";
       int num = (int)(Math.random()*3)+1;
       return ruta+num+".mp3";
   }
}

