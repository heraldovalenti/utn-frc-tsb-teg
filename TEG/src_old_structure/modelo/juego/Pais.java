/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.juego;

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

    public Pais(int nroPais, String nombre, Continente continente) {
        this.nroPais = nroPais;
        this.nombre = nombre;
        this.continente = continente;
        ocupacion = new Ocupacion();
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

    public boolean equals(Object o) {
        if (o instanceof Pais) {
            Pais otro = (Pais) o;
            return otro.nroPais == this.nroPais;
        } else {
            return false;
        }
    }
}
