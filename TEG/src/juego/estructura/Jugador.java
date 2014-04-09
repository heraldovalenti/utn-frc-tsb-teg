/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.estructura;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author heril
 */
public class Jugador implements Serializable {

    private int nroJugador;
    private String nombre;
    private Color color;
    private Set<Pais> conjuntoPaises = new HashSet<>(96);
    private ObjetivoSecreto objetivoSecreto;
    private int cantidadCanjes = 0;
    private List<TarjetaPais> listaTarjetasPais = new ArrayList<>(5);
    private Set<TarjetaContinente> conjuntoTarjetaContinentes = new HashSet<>(10);
    private boolean ia;

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

    public ObjetivoSecreto getObjetivoSecreto() {
        return objetivoSecreto;
    }

    public void setObjetivoSecreto(ObjetivoSecreto objetivoSecreto) {
        this.objetivoSecreto = objetivoSecreto;
    }

    public int getCantidadCanjes() {
        return cantidadCanjes;
    }

    public void setCantidadCanjes(int cantidadCanjes) {
        this.cantidadCanjes = cantidadCanjes;
    }

    public List<TarjetaPais> getListaTarjetasPais() {
        return listaTarjetasPais;
    }

    public void setListaTarjetasPais(List<TarjetaPais> listaTarjetasPais) {
        this.listaTarjetasPais = listaTarjetasPais;
    }

    public Set<TarjetaContinente> getConjuntoTarjetaContinentes() {
        return conjuntoTarjetaContinentes;
    }

    public void setConjuntoTarjetaContinentes(Set<TarjetaContinente> conjuntoTarjetaContinentes) {
        this.conjuntoTarjetaContinentes = conjuntoTarjetaContinentes;
    }

    public boolean isIa() {
        return ia;
    }

    public void setIa(boolean ia) {
        this.ia = ia;
    }

    public List<Canjeable> obtenerTarjetas() {
        List<Canjeable> listaTarjetas = new ArrayList<>();
        listaTarjetas.addAll(listaTarjetasPais);
        listaTarjetas.addAll(conjuntoTarjetaContinentes);
        return listaTarjetas;
    }

    /**
     * Agrega el pais al conjunto de paises del jugador. Ademas, actualiza la
     * referencia del pais al jugador referenciado (this).
     *
     * @param pais el pais a agregarse a la coleccion de paises del jugador.
     */
    public void añadirPais(Pais pais) {
        if (pais == null) {
            return;
        }
        this.conjuntoPaises.add(pais);
        pais.setJugador(this);
    }

    public void quitarPais(Pais pais) {
        conjuntoPaises.remove(pais);
    }

    public int getCantidadPaises() {
        return conjuntoPaises.size();
    }

    public int getCantidadTarjetasPais() {
        return listaTarjetasPais.size();
    }

    public void añadirTarjetaPais(TarjetaPais tarjeta) {
        listaTarjetasPais.add(tarjeta);
    }

    public boolean añadirTarjetaContinente(TarjetaContinente tarjeta) {
        return conjuntoTarjetaContinentes.add(tarjeta);
    }

    public int getCantidadTarjetasContinente() {
        return conjuntoTarjetaContinentes.size();
    }

    public Set<Pais> obtenerPaisesDeContinente(Continente continente) {
        Set<Pais> paises = new HashSet();
        for (Pais pais : conjuntoPaises) {
            if (pais.getContinente().equals(continente)) {
                paises.add(pais);
            }
        }
        return paises;
    }

    public int calcularCantidadDePaisesDeContinente(Continente continente) {
        int acu = 0;
        for (Pais pais : conjuntoPaises) {
            if (pais.getContinente().equals(continente)) {
                acu++;
            }
        }
        return acu;
    }

    public int calcularRefuerzosPermitidos() {
        if (getCantidadPaises() < 6) {
            return 4;
        } else {
            return (int) (getCantidadPaises() / 2);
        }
    }

    public Map<Continente, Integer> calcularPaisesPorContinente() {
        Map<Continente, Integer> mapaContinentes = new HashMap<>();
        for (Pais pais : conjuntoPaises) {
            Continente continente = pais.getContinente();
            int cantidadAnterior = 0;
            if (mapaContinentes.containsKey(continente)) {
                cantidadAnterior = mapaContinentes.get(continente);
            }
            mapaContinentes.put(continente, cantidadAnterior + 1);
        }
        return mapaContinentes;
    }

    public Set<Continente> obtenerContinentesOcupados() {
        Set<Continente> conjuntoContinentes = new HashSet<>();
        for (Pais pais : conjuntoPaises) {
            conjuntoContinentes.add(pais.getContinente());
        }
        return conjuntoContinentes;
    }

    public Set<Continente> obtenerContinentesOcupadosCompletos() {
        Map<Continente, Integer> mapaContinentes = new HashMap<>();
        Set<Continente> conjuntoContinentes = new HashSet<>();
        for (Pais pais : conjuntoPaises) {
            int cantidadAnterior = 0;
            Continente continente = pais.getContinente();
            if (mapaContinentes.containsKey(continente)) {
                cantidadAnterior = mapaContinentes.get(continente);
            }
            mapaContinentes.put(continente, cantidadAnterior + 1);
        }
        for (Continente continente : mapaContinentes.keySet()) {
            int cantidadPaises = mapaContinentes.get(continente);
            if (cantidadPaises == GestorContinentes.obtenerCantidadPaises(continente)) {
                conjuntoContinentes.add(continente);
            }
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

    public void usarTarjetas(List<Canjeable> listaTarjetas) {
        for (Canjeable tarjeta : listaTarjetas) {
            if (tarjeta instanceof TarjetaPais) {
                GestorTarjetas.devolverTarjeta(
                        (TarjetaPais) tarjeta);
            } else {
                TarjetaContinente tarjetaCont = (TarjetaContinente) tarjeta;
                tarjetaCont.registrarUso(this);
            }
        }
        cantidadCanjes++;
    }

    public boolean comprobarObjetivoSecreto() {
        return objetivoSecreto.comprobarVictoria(this);
    }

    @Override
    public String toString() {
        return "Jugador{" + "nroJugador=" + nroJugador + ", nombre=" + nombre + ", color=" + color + ", cantidadCanjes=" + cantidadCanjes + ", listaTarjetasPais=" + listaTarjetasPais + ", listaTarjetaContinentes=" + conjuntoTarjetaContinentes + '}';
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
