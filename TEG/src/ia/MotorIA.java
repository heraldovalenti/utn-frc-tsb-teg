/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

import cliente.control.ControlRefuerzo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import juego.estructura.Canjeable;
import juego.estructura.Continente;
import juego.estructura.GestorPaises;
import juego.estructura.GestorTarjetas;
import juego.estructura.Jugador;
import juego.estructura.Pais;
import juego.mecanicas.turno.GestorTurno;

/**
 *
 * @author Daniel
 */
public class MotorIA {

    public static void turnoIA(Jugador jugador, int cantidadEjercitos, Map<Continente, Integer> ejercitosPorContinente) {
        faseRefuerzo(jugador, cantidadEjercitos, ejercitosPorContinente);
        faseAtaque(jugador);
        faseReagrupamiento(jugador);
        faseSolicitarTarjeta(jugador);
    }

    public static void faseRefuerzo(Jugador jugador, int cantidadEjercitos, Map<Continente, Integer> ejercitosPorContinente) {
        canjearTarjetas(jugador);
        reforzar(jugador, cantidadEjercitos, ejercitosPorContinente);
    }

    public static void faseAtaque(Jugador jugador) {
        atacar(jugador);
    }

    public static void faseReagrupamiento(Jugador jugador) {
        reagrupar(jugador);
    }

    public static void faseSolicitarTarjeta(Jugador jugador) {
        GestorTurno.solicitarTarjeta(jugador);
    }

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
            if (!origen.getJugador().equals(pais.getJugador())) {
                int aux = origen.getCantidadEjercitos() - pais.getCantidadEjercitos();
                if (aux > resultado) {
                    resultado = aux;
                    destino = pais;
                }
            }
        }
        return destino;
    }

    public static void atacar(Jugador jugador) {
        boolean ataqueRealizado = false;
        while (ataqueRealizado) {
            ataqueRealizado = false;
            for (Pais pais : jugador.getConjuntoPaises()) {
                boolean repetir = true;
                while (repetir) {
                    repetir = false;
                    if (pais.getCantidadEjercitos() > calcularAmenaza(pais)) {
                        Pais blanco = determinarBlanco(pais);
                        if (blanco != null) {
                            GestorTurno.atacar(pais, blanco);
                            //TODO: probablemente haya que cambiar el accionable para que use la cola de entrada
                        }
                    }
                }
            }
        }
    }

    public static void reforzar(Jugador jugador, int ejercitos, Map<Continente, Integer> ejercitosPorContinente) {
        ControlRefuerzo control = new ControlRefuerzo(ejercitos, ejercitosPorContinente, false);
        if (ejercitosPorContinente != null) {
            for (Continente continente : ejercitosPorContinente.keySet()) {
                int margen = 0;
                while (quedanRefuerzos(ejercitosPorContinente, continente)) {
                    Set<Pais> paises = jugador.obtenerPaisesDeContinente(continente);
                    for (Pais pais : paises) {
                        int necesidad = calcularNecesidadRefuerzos(pais, margen);
                        while (necesidad > 0) {
                            control.agregarEjercito(pais);
                            necesidad--;
                        }
                    }
                    margen++;
                }
            }
        }
        while (ejercitos > 0) {
            int margen = 0;
            Set<Pais> paises = jugador.getConjuntoPaises();
            for (Pais pais : paises) {
                int necesidad = calcularNecesidadRefuerzos(pais, margen);
                while (necesidad > 0) {
                    control.agregarEjercito(pais);
                    necesidad--;
                }
            }
            margen++;
        }
        control.aplicarRefuerzo();
    }

    public static int calcularNecesidadRefuerzos(Pais pais, int margen) {
        int amenaza = calcularAmenaza(pais);
        int necesidad = amenaza + margen - pais.getCantidadEjercitos();
        return necesidad;
    }

    public static boolean quedanRefuerzos(Map<Continente, Integer> ejercitosPorContinente, Continente continente) {
        return ejercitosPorContinente.get(continente) > 0;
    }

    public static void reagrupar(Jugador jugador) {
        Map<Pais, Integer> mapaAmenazas = calcularAmenazasTotales(jugador);
        for (Pais pais : mapaAmenazas.keySet()) {
            if (mapaAmenazas.get(pais) <= 0 && pais.getCantidadEjercitos() > 4) {
                Pais destino = determinarDestinoRefuerzo(pais);
                int ejercitos = pais.getCantidadEjercitos() - 2;
                GestorTurno.reagruparEjercitos(pais, destino, ejercitos, 0);
                //TODO: enviarAccionable
            }
        }
    }

    public static Pais determinarDestinoRefuerzo(Pais origen) {
        Pais destino = null;
        int resultado = 0;
        for (Pais pais : GestorPaises.obtenerLimitrofes(origen)) {
            if (origen.getJugador().equals(pais.getJugador())) {
                int aux = origen.getCantidadEjercitos() - pais.getCantidadEjercitos();
                if (aux > resultado) {
                    resultado = aux;
                    destino = pais;
                }
            }
        }
        return destino;
    }

    public static void canjearTarjetas(Jugador jugador) {
        boolean canjeValido = false;
        List<Canjeable> listaTarjetas = jugador.obtenerTarjetas();
        List<Canjeable> paraCanjear = new ArrayList<>(5);
        if (listaTarjetas.size() > 0) {
            for (int i = 0; i < listaTarjetas.size(); i++) {
                if (!canjeValido) {
                    paraCanjear.set(0, listaTarjetas.get(i));
                    canjeValido = GestorTarjetas.canjeValido(jugador, paraCanjear);
                    if (canjeValido) {
                        GestorTurno.canjearTarjetas(jugador, listaTarjetas);
                    }
                }
            }
        }
        if (!canjeValido && listaTarjetas.size() > 1) {
            for (int i = 0; i < listaTarjetas.size() - 1; i++) {
                for (int j = i; j < listaTarjetas.size(); j++) {
                    if (!canjeValido) {
                        paraCanjear.set(0, listaTarjetas.get(i));
                        paraCanjear.set(1, listaTarjetas.get(j));
                        canjeValido = GestorTarjetas.canjeValido(jugador, paraCanjear);
                        if (canjeValido) {
                            GestorTurno.canjearTarjetas(jugador, listaTarjetas);
                        }
                    }
                }
            }
        }
        if (!canjeValido && listaTarjetas.size() > 2) {
            for (int i = 0; i < listaTarjetas.size() - 2; i++) {
                for (int j = i + 1; j < listaTarjetas.size() - 1; j++) {
                    for (int h = j + 1; h < listaTarjetas.size(); h++) {
                        if (!canjeValido) {
                            paraCanjear.set(0, listaTarjetas.get(i));
                            paraCanjear.set(1, listaTarjetas.get(j));
                            paraCanjear.set(2, listaTarjetas.get(h));
                            canjeValido = GestorTarjetas.canjeValido(jugador, paraCanjear);
                            if (canjeValido) {
                                GestorTurno.canjearTarjetas(jugador, listaTarjetas);
                            }
                        }
                    }
                }
            }
        }
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
