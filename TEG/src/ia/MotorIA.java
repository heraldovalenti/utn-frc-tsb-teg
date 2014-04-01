/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

import java.util.HashMap;
import java.util.Map;
import juego.estructura.GestorPaises;
import juego.estructura.Jugador;
import juego.estructura.Pais;

/**
 *
 * @author Daniel
 */
public class MotorIA {

    public static int calcularAmenaza(Pais pais) {
        Jugador dueño = pais.getJugador();
        int amenaza = 0;
        for (Pais limitrofe : GestorPaises.obtenerLimitrofes(pais)) {
            if (!limitrofe.getJugador().equals(dueño) && limitrofe.getCantidadEjercitos() > amenaza) {
                amenaza = limitrofe.getCantidadEjercitos();
            }
        }
        return amenaza;
    }

    public static Map<Pais, Integer> calcularAmenazasTotales(Jugador jugador) {
        Map<Pais, Integer> mapaAmenazas = new HashMap<>((int) (jugador.getCantidadPaises() / 0.75));
        for (Pais pais : jugador.getConjuntoPaises()) {
            mapaAmenazas.put(pais, calcularAmenaza(pais));
        }
        return mapaAmenazas;
    }
}
