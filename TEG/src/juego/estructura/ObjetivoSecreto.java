/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.estructura;

import java.awt.Color;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import juego.mecanicas.turno.SecuenciaTurnos;

/**
 *
 * @author Daniel
 */
public class ObjetivoSecreto implements Serializable {

    //Map<Continente, Boolean> continentesAOcupar;
    //Map<Pais, Boolean> paisesAOcupar;
    private int nroObjetivo;
    private String descripcion;
    private Map<Continente, Integer> paisesPorContinenteAOcupar;
    private Color colorADestruir;
    private Jugador jugadorADestruir;
    private int paisesAOcupar;
    private int islasAOcupar;
    private boolean destruirIzquierda;
    //private Map<Continente, Integer> islasPorContinenteAOcupar;

    public ObjetivoSecreto() {
    }

    public ObjetivoSecreto(int nroObjetivo, String descripcion, Map<Continente, Integer> paisesPorContinenteAOcupar, Color colorADestruir, int paisesAOcupar, int islasAOcupar, boolean destruirIzquierda) {
        this.nroObjetivo = nroObjetivo;
        this.descripcion = descripcion;
        this.paisesPorContinenteAOcupar = paisesPorContinenteAOcupar;
        this.paisesAOcupar = paisesAOcupar;
        this.colorADestruir = colorADestruir;
        this.destruirIzquierda = destruirIzquierda;
        // this.islasPorContinenteAOcupar = islasPorContinenteAOcupar;
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
        SecuenciaTurnos secuencia = SecuenciaTurnos.getInstancia();
        Set<Pais> conjuntoPaises;
        Set<Continente> conjuntoContinentes;
        if (paisesPorContinenteAOcupar != null) {
            conjuntoPaises = jugador.getConjuntoPaises();
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
        if (colorADestruir != null && jugadorADestruir == null) {
            if (jugador.getColor().equals(colorADestruir)) {
                jugadorADestruir = secuencia.getJugadorAnterior(jugador);
            } else {
                jugadorADestruir = GestorJugadores.obtenerPorColor(colorADestruir);
            }
        }
        if (destruirIzquierda && jugadorADestruir == null) {
            jugadorADestruir = secuencia.getJugadorSiguiente(jugador);
        }
        if (jugadorADestruir != null) {
            if (jugadorADestruir.getCantidadPaises() > 0) {
                return false;
            }
        }
        if (jugadorADestruir.getCantidadPaises() > 0) {
            return false;
        }
        if (islasAOcupar > 0) {
            conjuntoPaises = jugador.obtenerIslasOcupadas();
            conjuntoContinentes = jugador.obtenerContinentesOcupadosConIslas();
            if (conjuntoPaises.size() < islasAOcupar) {
                return false;
            }
            if (conjuntoContinentes.size() < 3) {
                return false;
            }
        }
        if (paisesAOcupar > 0) {
            conjuntoPaises = jugador.getConjuntoPaises();
            if (conjuntoPaises.size() < paisesAOcupar) {
                return false;
            }
        }
        return true;
    }
}
