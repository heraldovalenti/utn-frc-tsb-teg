/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.estructura;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author heril
 */
public class Jugador {

    private int nroJugador;
    private String nombre;
    private Color color;
    private Set<Pais> conjuntoPaises;

    public Jugador() {
    }

    public Jugador(int nroJugador) {
        this.nroJugador = nroJugador;
    }

    public Jugador(int nroJugador, String nombre, Color color) {
        this.nroJugador = nroJugador;
        this.nombre = nombre;
        this.color = color;
    }

    public int getNroJugador() {
        return nroJugador;
    }

    public void setNroJugador(int nroJugador) {
        this.nroJugador = nroJugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Set<Pais> getConjuntoPaises() {
        return conjuntoPaises;
    }

    public void setConjuntoPaises(Set<Pais> conjuntoPaises) {
        this.conjuntoPaises = conjuntoPaises;
    }

    public Set<Continente> obtenerContinentesOcupados() {
        Set<Continente> conjuntoContinentes = new HashSet<>();
        for (Pais pais : conjuntoPaises) {
            conjuntoContinentes.add(pais.getContinente());
        }
        return conjuntoContinentes;
    }

    public Set<Pais> obtenerIslasOcupadas() {
        Set<Pais> conjuntoIslas = new HashSet<>();
        for (Pais pais : conjuntoPaises) {
            if (pais.isIsla()) {
                conjuntoIslas.add(pais);
            }
        }
        return conjuntoIslas;
    }

    public Set<Continente> obtenerContinentesOcupadosConIslas() {
        Set<Continente> conjuntoContinentes = new HashSet<>();
        for (Pais pais : conjuntoPaises) {
            if (pais.isIsla()) {
                conjuntoContinentes.add(pais.getContinente());
            }
        }
        return conjuntoContinentes;
    }

    @Override
    public String toString() {
        return "Jugador{" + "nroJugador=" + nroJugador + ", nombre=" + nombre + ", color=" + color + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.nroJugador;
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
        final Jugador other = (Jugador) obj;
        if (this.nroJugador != other.nroJugador) {
            return false;
        }
        return true;
    }
}
