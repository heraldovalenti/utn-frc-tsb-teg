/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.juego;

/**
 *
 * @author heril
 */
public class Ocupacion {
    
    private Jugador jugador;
    private int ejercitos;
    private int misiles;

    public Jugador getJugador() {
        return jugador;
    }

    public int getEjercitos() {
        return ejercitos;
    }

    public int getMisiles() {
        return misiles;
    }

    public void setEjercitos(int ejercitos) {
        this.ejercitos = ejercitos;
    }

    public void setMisiles(int misiles) {
        this.misiles = misiles;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }    
}
