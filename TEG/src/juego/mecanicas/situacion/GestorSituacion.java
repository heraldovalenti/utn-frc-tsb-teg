/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.mecanicas.situacion;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import juego.estructura.GestorJugadores;
import juego.estructura.Jugador;

/**
 *
 * @author Daniel
 */
public class GestorSituacion {

    private static GestorSituacion instance;
    private List<Situacion> listaSituaciones;
    private int tarjetaActual;
    private Situacion situacionActual;

    public GestorSituacion() {
        tarjetaActual = 0;
        crearSituaciones();
    }

    public static GestorSituacion getInstance() {
        if (instance == null) {
            instance = new GestorSituacion();
        }
        return instance;
    }

    private void crearSituaciones() {
        listaSituaciones = new ArrayList<>(50);
        for (int i = 0; i < 20; i++) {
            listaSituaciones.add(new CombateNormal());
        }
        for (int i = 0; i < 4; i++) {
            listaSituaciones.add(new Nieve());
        }
        for (int i = 0; i < 4; i++) {
            listaSituaciones.add(new VientoFavor());
        }
        for (int i = 0; i < 4; i++) {
            listaSituaciones.add(new Crisis());
        }
        for (int i = 0; i < 4; i++) {
            listaSituaciones.add(new RefuerzosExtra());
        }
        for (int i = 0; i < 4; i++) {
            listaSituaciones.add(new FronterasAbiertas());
        }
        for (int i = 0; i < 4; i++) {
            listaSituaciones.add(new FronterasCerradas());
        }
        listaSituaciones.add(new Descanso(Color.BLACK));
        listaSituaciones.add(new Descanso(Color.WHITE));
        listaSituaciones.add(new Descanso(Color.BLUE));
        listaSituaciones.add(new Descanso(Color.GREEN));
        listaSituaciones.add(new Descanso(Color.YELLOW));
        listaSituaciones.add(new Descanso(Color.RED));
    }

    private void renovarSituaciones() {
        Collections.shuffle(listaSituaciones);
        tarjetaActual = 0;
    }

    public Situacion getProximaSituacion() {
        boolean noValido = true;
        while (noValido) {
            situacionActual = listaSituaciones.get(tarjetaActual);
            tarjetaActual++;
            if (tarjetaActual == 50) {
                renovarSituaciones();
            }
            if (situacionActual instanceof Descanso) {
                noValido = !validarDescanso();
            } else {
                noValido = false;
            }
        }
        return situacionActual;
    }

    public Situacion getSituacionActual() {
        return situacionActual;
    }

    private boolean validarDescanso() {
        Color color = ((Descanso) situacionActual).getColor();
        for (Jugador jugador : GestorJugadores.getJugadores()) {
            if (jugador.getColor().equals(color)) {
                return true;
            }
        }
        return false;
    }
}
