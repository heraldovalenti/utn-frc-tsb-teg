/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.ataque;

/**
 *
 * @author heril
 */
public class Ataque {
    
    private TiradaAtaque tiradaAtacaque;
    private TiradaAtaque tiradaDefensa;
    private ResolucionAtaque resolucion;

    public Ataque(int cantidadAtaque, int cantidadDefensa) {
        this.tiradaAtacaque = new TiradaAtaque(cantidadAtaque);
        this.tiradaDefensa = new TiradaAtaque(cantidadDefensa);
        this.resolucion = new ResolucionAtaque(tiradaAtacaque, tiradaDefensa);
    }

    public TiradaAtaque getTiradaAtacaque() {
        return tiradaAtacaque;
    }

    public TiradaAtaque getTiradaDefensa() {
        return tiradaDefensa;
    }

    public ResolucionAtaque getResolucion() {
        return resolucion;                
    }
    
    private static void test() {
        Ataque ataque = new Ataque(5,5);
        int ataques[] = ataque.getTiradaAtacaque().getTiradas();
        int defensas[] = ataque.getTiradaDefensa().getTiradas();
        String resAtaques = "ATAQUES: ";
        String resDefensas = "DEFENSAS: ";
        for (int a : ataques) {
            resAtaques += a + " - ";
        }
        for (int d : defensas) {
            resDefensas += d + " - ";
        }
        System.out.println(resAtaques);
        System.out.println(resDefensas);
    }
    
}
