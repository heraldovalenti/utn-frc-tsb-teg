/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Daniel
 */
public class ObjetivoSecreto {

    //Map<Continente, Boolean> continentesAOcupar;
    //Map<Pais, Boolean> paisesAOcupar;
    private int nroObjetivo;
    private String descripcion;
    private Map<Continente, Integer> paisesPorContinenteAOcupar;
    private Jugador jugadorADestruir;
    private int paisesAOcupar;
    private Map<Continente, Integer> islasPorContinenteAOcupar;

    public ObjetivoSecreto() {
    }

    public ObjetivoSecreto(int nroObjetivo, String descripcion, Map<Continente, Integer> paisesPorContinenteAOcupar, Jugador jugadorADestruir, int paisesAOcupar, Map<Continente, Integer> islasPorContinenteAOcupar) {
        this.nroObjetivo = nroObjetivo;
        this.descripcion = descripcion;
        this.paisesPorContinenteAOcupar = paisesPorContinenteAOcupar;
        this.jugadorADestruir = jugadorADestruir;
        this.paisesAOcupar = paisesAOcupar;
        this.islasPorContinenteAOcupar = islasPorContinenteAOcupar;
    }

    public int getNroObjetivo() {
        return nroObjetivo;
    }

    public void setNroObjetivo(int nroObjetivo) {
        this.nroObjetivo = nroObjetivo;
    }

    public Map<Continente, Integer> getPaisesPorContinenteAOcupar() {
        return paisesPorContinenteAOcupar;
    }

    public void setPaisesPorContinenteAOcupar(Map<Continente, Integer> paisesPorContinenteAOcupar) {
        this.paisesPorContinenteAOcupar = paisesPorContinenteAOcupar;
    }

    public Jugador getJugadorADestruir() {
        return jugadorADestruir;
    }

    public void setJugadorADestruir(Jugador jugadorADestruir) {
        this.jugadorADestruir = jugadorADestruir;
    }

    public int getPaisesAOcupar() {
        return paisesAOcupar;
    }

    public void setPaisesAOcupar(int paisesAOcupar) {
        this.paisesAOcupar = paisesAOcupar;
    }

    public Map<Continente, Integer> getIslasPorContinenteAOcupar() {
        return islasPorContinenteAOcupar;
    }

    public void setIslasPorContinenteAOcupar(Map<Continente, Integer> islasPorContinenteAOcupar) {
        this.islasPorContinenteAOcupar = islasPorContinenteAOcupar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.nroObjetivo;
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
        final ObjetivoSecreto other = (ObjetivoSecreto) obj;
        if (this.nroObjetivo != other.nroObjetivo) {
            return false;
        }
        return true;
    }

    public boolean comprobarVictoria(Jugador jugador) {
        Set<Pais> conjuntoPaises;
        Set<Continente> conjuntoContinentes;
        if (paisesPorContinenteAOcupar != null) {
            conjuntoPaises = jugador.obtenerPaisesOcupados();
            conjuntoContinentes = jugador.obtenerContinentesOcupados();
            Map<Continente, Integer> paisesPorContinenteOcupados = new HashMap<>(conjuntoContinentes.size());
            for (Pais pais : conjuntoPaises) {
                Continente continente = pais.getContinente();
                int cantidadAnterior = 0;
                if (paisesPorContinenteOcupados.containsKey(continente)) {
                    cantidadAnterior = paisesPorContinenteOcupados.get(continente);
                }
                paisesPorContinenteOcupados.put(continente, cantidadAnterior + 1);
            }
            for (Continente continente : paisesPorContinenteAOcupar.keySet()) {
                int cantidadObjetivo = paisesPorContinenteAOcupar.get(continente);
                int cantidadOcupada = 0;
                if (paisesPorContinenteOcupados.containsKey(continente)) {
                    cantidadOcupada = paisesPorContinenteOcupados.get(continente);
                }
                if (cantidadOcupada < cantidadObjetivo) {
                    return false;
                }
            }
        }
        if (jugadorADestruir != null) {
            int cantidadPaises = jugadorADestruir.obtenerPaisesOcupados().size();
            if (cantidadPaises > 0) {
                return false;
            }
        }
        if (islasPorContinenteAOcupar != null) {
            conjuntoPaises = jugador.obtenerIslasOcupadas();
            conjuntoContinentes = jugador.obtenerContinentesOcupadosConIslas();
            Map<Continente, Integer> islasPorContinenteOcupadas = new HashMap<>(conjuntoContinentes.size());
            if (conjuntoContinentes.size() < 3) {
                return false;
            }
            for (Pais pais : conjuntoPaises) {
                Continente continente = pais.getContinente();
                int cantidadAnterior = 0;
                if (islasPorContinenteOcupadas.containsKey(continente)) {
                    cantidadAnterior = islasPorContinenteOcupadas.get(continente);
                }
                islasPorContinenteOcupadas.put(continente, cantidadAnterior + 1);
            }
            for (Continente continente : paisesPorContinenteAOcupar.keySet()) {
                int cantidadObjetivo = paisesPorContinenteAOcupar.get(continente);
                int cantidadOcupada = 0;
                if (islasPorContinenteOcupadas.containsKey(continente)) {
                    cantidadOcupada = islasPorContinenteOcupadas.get(continente);
                }
                if (cantidadOcupada < cantidadObjetivo) {
                    return false;
                }
            }
        }
        if (paisesAOcupar > 0) {
            conjuntoPaises = jugador.obtenerPaisesOcupados();
            if (conjuntoPaises.size() < paisesAOcupar) {
                return false;
            }
        }
        return true;
    }
}
