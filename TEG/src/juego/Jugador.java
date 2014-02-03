/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

/**
 *
 * @author heril
 */
public class Jugador {

    private int nroJugador;
    private String nombre;
    private String color;

    public Jugador() {
    }
    
    public Jugador(int nroJugador) {
        this.nroJugador = nroJugador;
    }

    public Jugador(int nroJugador, String nombre, String color) {
        this.nroJugador = nroJugador;
        this.nombre = nombre;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Jugador{" + "nroJugador=" + nroJugador + ", nombre=" + nombre + ", color=" + color + '}';
    }
    
    

    public boolean equals(Object o) {
        if (o instanceof Jugador) {
            Jugador otro = (Jugador) o;
            return otro.nroJugador == this.nroJugador;
        } else {
            return false;
        }
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
