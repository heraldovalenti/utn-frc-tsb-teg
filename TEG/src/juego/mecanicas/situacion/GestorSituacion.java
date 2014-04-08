/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.mecanicas.situacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class GestorSituacion {

    private static GestorSituacion instance;
    private List<Situacion> listaSituaciones;
    private int tarjetaActual;

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
        for (int i = 0; i < 6; i++) {
            listaSituaciones.add(new Descanso());
        }
    }

    private void renovarSituaciones() {
        Collections.shuffle(listaSituaciones);
        tarjetaActual = 0;
    }

    public Situacion getProximaSituacion() {
        Situacion situacion = listaSituaciones.get(tarjetaActual);
        tarjetaActual++;
        if (tarjetaActual == 50) {
            renovarSituaciones();
        }
        return situacion;
    }

    public Situacion getSituacionActual() {
        return listaSituaciones.get(tarjetaActual);
    }
}
