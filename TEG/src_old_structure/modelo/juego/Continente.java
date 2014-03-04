/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.juego;

/**
 *
 * @author heril
 */
public class Continente {

    private int nroContinente;
    private String nombre;

    public Continente(int nroContinente, String nombre) {
        this.nroContinente = nroContinente;
        this.nombre = nombre;
    }
    
    public boolean equals(Object o) {
        if (o instanceof Continente) {
            Continente otro = (Continente) o;
            return otro.nroContinente == this.nroContinente;
        } else {
            return false;
        }
    }

    public int getNroContinente() {
        return nroContinente;
    }

    public String getNombre() {
        return nombre;
    }
}
