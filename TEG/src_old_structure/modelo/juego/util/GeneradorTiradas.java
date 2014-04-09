/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.juego.util;

/**
 *
 * @author heril
 */
public class GeneradorTiradas {
    
    /**
     * 
     * @return Numero entero aleatorio entre 1 y 6, con distribucion uniforme.
     */
    public static int generarTirada() {
        double rnd = Math.random();
        double dRes = 1 + (rnd * 6);
        int iRes = (int) dRes;
        //System.out.println(iRes);
        return iRes;
    }
    
//    public static void main (String args[]) {
//        test();
//    }
    
    /**
     * Prueba la funcionalidad de la clase.
     */
    private static void test() {
        int[] tiradas = new int[6];
        int vueltas = 10000000;
        for (int i = 0; i < vueltas; i++) {
            int res = generarTirada(); 
            tiradas[res-1]++;
        }
        
        System.out.println("1 - " + tiradas[0] + " - " + (double)tiradas[0]/vueltas);
        System.out.println("2 - " + tiradas[1] + " - " + (double)tiradas[1]/vueltas);
        System.out.println("3 - " + tiradas[2] + " - " + (double)tiradas[2]/vueltas);
        System.out.println("4 - " + tiradas[3] + " - " + (double)tiradas[3]/vueltas);
        System.out.println("5 - " + tiradas[4] + " - " + (double)tiradas[4]/vueltas);
        System.out.println("6 - " + tiradas[5] + " - " + (double)tiradas[5]/vueltas);
        
        
        double res = (double)tiradas[0]/vueltas + (double)tiradas[1]/vueltas + (double)tiradas[2]/vueltas +
        (double)tiradas[3]/vueltas + (double)tiradas[4]/vueltas + (double)tiradas[5]/vueltas;
        int total = tiradas[0] + tiradas[1] + tiradas[2] + tiradas[3] + tiradas[4] + tiradas[5];
        System.out.println("Total: " + total + " , " + res);
    }
    
}
