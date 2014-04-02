/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

import Interfaces.FachadaInterface;
import cliente.control.ControlRefuerzo;
import com.cliente.AccionableAtaque;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import juego.estructura.Continente;
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
        Map<Pais, Integer> mapaAmenazas = new HashMap<>();
        for (Pais pais : jugador.getConjuntoPaises()) {
            mapaAmenazas.put(pais, calcularAmenaza(pais));
        }
        return mapaAmenazas;
    }

    public static Pais determinarBlanco(Pais origen) {
        Pais destino = null;
        int resultado = 0;
        for (Pais pais : GestorPaises.obtenerLimitrofes(origen)) {
            int aux = origen.getCantidadEjercitos() - pais.getCantidadEjercitos();
            if (aux > resultado) {
                resultado = aux;
                destino = pais;
            }
        }
        return destino;
    }

    public static void atacar(Jugador jugador) {
        boolean ataqueRealizado = false;
        while (ataqueRealizado) {
            ataqueRealizado = false;
            for (Pais pais : jugador.getConjuntoPaises()) {
                if (pais.getCantidadEjercitos() > calcularAmenaza(pais)) {
                    Pais blanco = determinarBlanco(pais);
                    if (blanco != null) {
                        AccionableAtaque ataque = new AccionableAtaque(pais, blanco);
                        //TODO: Enviar el accionable
                        ataqueRealizado = true;
                    }
                }
            }
        }
    }

    public static void reforzar(Jugador jugador, int ejercitos, Map<Continente, Integer> ejercitosPorContinente) {
        ControlRefuerzo control = new ControlRefuerzo(ejercitos, ejercitosPorContinente);
        if (ejercitosPorContinente != null) {
            for (Continente continente : ejercitosPorContinente.keySet()) {
                int margen = 0;
                while (quedanRefuerzos(ejercitosPorContinente, continente)) {
                    Set<Pais> paises = jugador.obtenerPaisesDeContinente(continente);
                    for (Pais pais : paises) {
                        int necesidad = calcularNecesidadRefuerzos(pais, margen);
                        if (necesidad > 0) {
                            
                        }
                    }
                    margen++;
                }
            }
        }
    }

    public static int calcularNecesidadRefuerzos(Pais pais, int margen) {
        int amenaza = calcularAmenaza(pais);
        int necesidad = amenaza + margen - pais.getCantidadEjercitos();
        return necesidad;
    }

    public static boolean quedanRefuerzos(Map<Continente, Integer> ejercitosPorContinente, Continente continente) {
        return ejercitosPorContinente.get(continente) > 0;
    }

//    public static Map<Pais, Integer> calcularNecesidadRefuerzos(Set<Pais> conjuntoPaises) {
//        Map<Pais, Integer> necesidadRefuerzos = new HashMap<>();
//        for (Pais pais : conjuntoPaises) {
//            int necesidad = calcularAmenaza(pais) - pais.getCantidadEjercitos();
//            necesidadRefuerzos.put(pais, necesidad);
//        }
//        return necesidadRefuerzos;
//    }
}
