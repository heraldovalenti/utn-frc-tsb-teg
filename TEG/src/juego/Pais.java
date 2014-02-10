/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author heril
 */
public class Pais {

    private int nroPais;
    private String nombre;
    private Continente continente;
    private Ocupacion ocupacion;
    private boolean isla;

    public Pais(int nroPais, String nombre, Continente continente, boolean isla) {
        this.nroPais = nroPais;
        this.nombre = nombre;
        this.continente = continente;
        ocupacion = new Ocupacion();
        this.isla = isla;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNroPais() {
        return nroPais;
    }

    public Ocupacion getOcupacion() {
        return ocupacion;
    }

    public Continente getContinente() {
        return continente;
    }

    public boolean mismoContinente(Pais otro) {
        return otro.continente.equals(this.continente);
    }

    public boolean isIsla() {
        return isla;
    }

    public void setIsla(boolean isla) {
        this.isla = isla;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.nroPais;
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
        final Pais other = (Pais) obj;
        if (this.nroPais != other.nroPais) {
            return false;
        }
        return true;
    }

}
