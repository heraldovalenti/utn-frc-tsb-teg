/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

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
    
    public int getNroContinente() {
        return nroContinente;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.nroContinente;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Continente other = (Continente) obj;
        if (this.nroContinente != other.nroContinente) {
            return false;
        }
        return true;
    }
    
    
}
