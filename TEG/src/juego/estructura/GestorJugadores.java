/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.estructura;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Daniel
 */
public class GestorJugadores {

    private static Set<Jugador> conjuntoJugadores = new HashSet<>(8);

    public static void añadirJugador(Jugador jugador) {
        conjuntoJugadores.add(jugador);
    }

    public static int getCantidadJugadores() {
        return conjuntoJugadores.size();
    }

    public static Jugador obtenerPorColor(Color color) {
        for (Jugador jugador : conjuntoJugadores) {
            if (jugador.getColor().equals(color)) {
                return jugador;
            }
        }
        return null;
    }

    public static Jugador obtenerPorNombre(String nombre) {
        for (Jugador jugador : conjuntoJugadores) {
            if (jugador.getNombre().equals(nombre)) {
                return jugador;
            }
        }
        return null;
    }

    public static Jugador obtenerPorNumero(int numero) {
        for (Jugador jugador : conjuntoJugadores) {
            if (jugador.getNroJugador() == numero) {
                return jugador;
            }
        }
        return null;
    }
}
