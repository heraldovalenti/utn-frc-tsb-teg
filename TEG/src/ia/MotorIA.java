/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

import com.cliente.AccionableAtaque;
import com.cliente.AccionableFinTurno;
import com.cliente.AccionableMovimiento;
import com.cliente.AccionableSolicitarTarjetaPais;
import com.servidor.ActualizadorPais;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
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
import juego.mecanicas.ataque.ControlAtaque;
import juego.mecanicas.turno.GestorTurno;
import logger.LogItem;
import servidor.ServerManager;
import servidor.control.ControlVictoria;

/**
 *
 * @author Daniel
 */
public class MotorIA {

    public static void turnoIA(Jugador jugador, int cantidadEjercitos, Map<Continente, Integer> ejercitosPorContinente) {
        if (ControlVictoria.juegoTerminado()) {
            return;
        }
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
        AccionableSolicitarTarjetaPais solicitar = new AccionableSolicitarTarjetaPais(jugador);
        ServerManager.getInstance().registrarEntrada(solicitar);
        ServerManager.getInstance().getLogger().addLogItem(new LogItem("Turno de " + jugador.getNombre() + ": solicitando tarjeta..."));
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
        ServerManager.getInstance().getLogger().addLogItem(new LogItem("Turno de " + jugador.getNombre() + ": inicio de etapa de ataque, realizando ataques..."));
        boolean ataqueRealizado = false;
        boolean exploracionCompleta = false;
        while (!ataqueRealizado || !exploracionCompleta) {
            try {
                for (Pais pais : jugador.getConjuntoPaises()) {
                    boolean repetir = true;
                    while (repetir) {
                        if (pais.getCantidadEjercitos() > calcularAmenaza(pais)) {
                            Pais blanco = determinarBlanco(pais);
                            if (blanco != null) {
                                ControlAtaque control = new ControlAtaque(pais, blanco);
                                if (control.ataqueValido()) {
                                    int ejercitosAntesDeAtaque = pais.getCantidadEjercitos();
                                    control.atacar(control.ataquePermitido(), control.defensaPermitida());
                                    AccionableAtaque ataque = new AccionableAtaque(pais, blanco, control);
                                    ServerManager.getInstance().registrarEntrada(ataque);
                                    ataqueRealizado = true;
                                    int ejercitosLuegoDeAtaque = ejercitosAntesDeAtaque - control.perdidasAtacante();
                                    repetir = ejercitosLuegoDeAtaque > 1 && !control.paisConquistado();
                                }
                            } else {
                                repetir = false;
                            }
                        } else {
                            repetir = false;
                        }
                    }
                }
            } catch (ConcurrentModificationException ex) {
            }
            exploracionCompleta = true;
        }
        ServerManager.getInstance().getLogger().addLogItem(new LogItem("Turno de " + jugador.getNombre() + ": fin de etapa de ataques."));
    }

    public static void reforzar(Jugador jugador, int ejercitos, Map<Continente, Integer> ejercitosPorContinente) {
        ServerManager.getInstance().getLogger().addLogItem(new LogItem("Turno de " + jugador.getNombre() + ": inicio de etapa de refuerzo, realizando refuerzos..."));
        //ControlRefuerzo control = new ControlRefuerzo(ejercitos, ejercitosPorContinente, false);
        ArrayList<Pais> paisesJugador = new ArrayList(jugador.getConjuntoPaises());
        if (ejercitosPorContinente != null) {
            for (Continente continente : ejercitosPorContinente.keySet()) {
                int margen = 0;
                while (quedanRefuerzos(ejercitosPorContinente, continente)) {
                    Set<Pais> paises = jugador.obtenerPaisesDeContinente(continente);
                    for (Pais pais : paises) {
                        int necesidad = calcularNecesidadRefuerzos(pais, margen);
                        while (necesidad > 0 && ejercitos > 0) {
                            //control.agregarEjercito(pais);
                            paisesJugador.get(paisesJugador.indexOf(pais)).añadirEjercitos(1);
                            necesidad--;
                            ejercitos--;
                        }
                    }
                    margen++;
                }
            }
        }
        int margen = 0;
        while (ejercitos > 0) {
            Set<Pais> paises = jugador.getConjuntoPaises();
            for (Pais pais : paises) {
                int necesidad = calcularNecesidadRefuerzos(pais, margen);
                while (necesidad > 0 && ejercitos > 0) {
                    //control.agregarEjercito(pais);
                    paisesJugador.get(paisesJugador.indexOf(pais)).añadirEjercitos(1);
                    necesidad--;
                    ejercitos--;
                }
            }
            margen++;
        }
        //control.aplicarRefuerzo();
        for (Pais p : paisesJugador) {
            ActualizadorPais actualizador = new ActualizadorPais(p);
            ServerManager.getInstance().registrarSalida(actualizador);
        }
        AccionableFinTurno accionable = new AccionableFinTurno();
        ServerManager.getInstance().registrarEntrada(accionable);
        ServerManager.getInstance().getLogger().addLogItem(new LogItem("Turno de " + jugador.getNombre() + ": fin de etapa de refuerzos."));
    }

    public static void reforzarRondaInicial(Jugador jugador, int ejercitos) {
        ServerManager.getInstance().getLogger().addLogItem(new LogItem("Turno de " + jugador.getNombre() + ": refuerzo de ronda inicial, realizando refuerzos..."));
        ArrayList<Pais> paisesJugador = new ArrayList(jugador.getConjuntoPaises());
        while (ejercitos > 0) {
            int indiceRandom = (int) (Math.random() * paisesJugador.size());
            Pais pais = paisesJugador.get(indiceRandom);
            paisesJugador.get(paisesJugador.indexOf(pais)).añadirEjercitos(1);
            ejercitos--;
            ServerManager.getInstance().getLogger().addLogItem(new LogItem("Turno de " + jugador.getNombre() + ": pais " + pais.getNombre() + " reforzado..."));
        }
        for (Pais p : paisesJugador) {
            ActualizadorPais actualizador = new ActualizadorPais(p);
            ServerManager.getInstance().registrarSalida(actualizador);
        }
        AccionableFinTurno accionable = new AccionableFinTurno();
        ServerManager.getInstance().registrarEntrada(accionable);
        ServerManager.getInstance().getLogger().addLogItem(new LogItem("Turno de " + jugador.getNombre() + ": refuerzo de ronda inicial realizado. Fin de turno de IA."));
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
        ServerManager.getInstance().getLogger().addLogItem(new LogItem("Turno de " + jugador.getNombre() + ": inicio de etapa de reagrupe, reagrupando..."));
        Map<Pais, Integer> mapaAmenazas = calcularAmenazasTotales(jugador);
        for (Pais pais : mapaAmenazas.keySet()) {
            if (mapaAmenazas.get(pais) <= 0 && pais.getCantidadEjercitos() > 4) {
                Pais destino = determinarDestinoRefuerzo(pais);
                int ejercitos = pais.getCantidadEjercitos() - 2;
                GestorTurno.getInstance().reagruparEjercitos(pais, destino, ejercitos, 0);
                //TODO: enviarAccionable
                if (GestorTurno.getInstance().accionPermitida(GestorTurno.ACCION_REAGRUPAR)) {
                    AccionableMovimiento movimiento = new AccionableMovimiento(pais, destino, ejercitos, 0);
                    ServerManager.getInstance().registrarEntrada(movimiento);
                }
            }
        }
        ServerManager.getInstance().getLogger().addLogItem(new LogItem("Turno de " + jugador.getNombre() + ": fin de reagrupaciones."));
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
        List<Canjeable> paraCanjear = new ArrayList<>();
        paraCanjear.add(null);
        paraCanjear.add(null);
        paraCanjear.add(null);
        paraCanjear.add(null);
        paraCanjear.add(null);
        if (listaTarjetas.size() > 0) {
            for (int i = 0; i < listaTarjetas.size(); i++) {
                if (!canjeValido) {
                    paraCanjear.set(0, listaTarjetas.get(i));
                    canjeValido = GestorTarjetas.canjeValido(jugador, paraCanjear);
                    if (canjeValido) {
                        GestorTurno.getInstance().canjearTarjetas(jugador, listaTarjetas);
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
                            GestorTurno.getInstance().canjearTarjetas(jugador, listaTarjetas);
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
                                GestorTurno.getInstance().canjearTarjetas(jugador, listaTarjetas);
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
