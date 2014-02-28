/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author heril
 */
public class Jugador {

    private int nroJugador;
    private String nombre;
    private String color;
    private List<Ocupacion> listaOcupaciones;

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

    public List<Ocupacion> getListaOcupaciones() {
        return listaOcupaciones;
    }

    public void setListaOcupaciones(List<Ocupacion> listaOcupaciones) {
        this.listaOcupaciones = listaOcupaciones;
    }

    public Set<Pais> obtenerPaisesOcupados() {
        Set<Pais> conjuntoPaises = new HashSet<>();
        for (Ocupacion ocupacion : listaOcupaciones) {
            conjuntoPaises.add(ocupacion.getPais());
        }
        return conjuntoPaises;
    }

    public Set<Continente> obtenerContinentesOcupados() {
        Set<Continente> conjuntoContinentes = new HashSet<>();
        for (Ocupacion ocupacion : listaOcupaciones) {
            conjuntoContinentes.add(ocupacion.getPais().getContinente());
        }
        return conjuntoContinentes;
    }

    public Set<Pais> obtenerIslasOcupadas() {
        Set<Pais> conjuntoIsla = new HashSet<>();
        for (Ocupacion ocupacion : listaOcupaciones) {
            Pais pais = ocupacion.getPais();
            if (pais.isIsla()) {
                conjuntoIsla.add(pais);
            }
        }
        return conjuntoIsla;
    }

    public Set<Continente> obtenerContinentesOcupadosConIslas() {
        Set<Continente> conjuntoContinentes = new HashSet<>();
        for (Ocupacion ocupacion : listaOcupaciones) {
            Pais pais = ocupacion.getPais();
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
