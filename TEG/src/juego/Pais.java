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
    private Jugador jugador;
    private int cantidadEjercitos;
    private int cantidadMisiles;
    private boolean isla;

    public Pais(int nroPais, String nombre, Continente continente, boolean isla) {
        this.nroPais = nroPais;
        this.nombre = nombre;
        this.continente = continente;
        this.isla = isla;
    }

    public int getNroPais() {
        return nroPais;
    }

    public void setNroPais(int nroPais) {
        this.nroPais = nroPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Continente getContinente() {
        return continente;
    }

    public void setContinente(Continente continente) {
        this.continente = continente;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public boolean isIsla() {
        return isla;
    }

    public void setIsla(boolean isla) {
        this.isla = isla;
    }

    public int getCantidadEjercitos() {
        return cantidadEjercitos;
    }

    public void setCantidadEjercitos(int cantidadEjercitos) {
        this.cantidadEjercitos = cantidadEjercitos;
    }

    public int getCantidadMisiles() {
        return cantidadMisiles;
    }

    public void setCantidadMisiles(int cantidadMisiles) {
        this.cantidadMisiles = cantidadMisiles;
    }

    public boolean mismoContinente(Pais otro) {
        return otro.continente.equals(this.continente);
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
