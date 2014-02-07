/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.ataque;

import juego.util.GeneradorTiradas;



/**
 *
 * @author heril
 */
public class TiradaAtaque {
    
    private int[] tiradas;

    public TiradaAtaque(int cantidadAtaques) {
        this.tiradas = new int[cantidadAtaques];
        for (int i = 0; i < this.tiradas.length; i++) {
            this.tiradas[i] = GeneradorTiradas.generarTirada();
        }
        ordenarTirada();
    }
    
    private void ordenarTirada() {
        int n = this.tiradas.length;
        for (int j = 1; j < n; j++) {
            int k, y = this.tiradas[j];
            for (k = j - 1; k >= 0 && y > this.tiradas[k]; k--) {
                this.tiradas[k + 1] = this.tiradas[k];
            }
            this.tiradas[k + 1] = y;
        }
    }
    
    public int[] getTiradas() {
        return this.tiradas;
    }    
}
