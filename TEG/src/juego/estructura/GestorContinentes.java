/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.estructura;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class GestorContinentes {

    public static final int AMERICA_NORTE = 0;
    public static final int AMERICA_CENTRAL = 1;
    public static final int AMERICA_SUR = 2;
    public static final int EUROPA = 3;
    public static final int ASIA = 4;
    public static final int AFRICA = 5;
    public static final int OCEANIA = 6;
    private static int[][] limitrofes = null;
    private static List<Continente> listaContinentes;

    public static void crearContinentes() {
        listaContinentes = new ArrayList<>(7);
        Continente continente;
        continente = new Continente(AMERICA_NORTE, "America del Norte");
        listaContinentes.add(continente);
        continente = new Continente(AMERICA_CENTRAL, "America Central");
        listaContinentes.add(continente);
        continente = new Continente(AMERICA_SUR, "America del Sur");
        listaContinentes.add(continente);
        continente = new Continente(EUROPA, "Europa");
        listaContinentes.add(continente);
        continente = new Continente(ASIA, "Asia");
        listaContinentes.add(continente);
        continente = new Continente(AFRICA, "Africa");
        listaContinentes.add(continente);
        continente = new Continente(OCEANIA, "Oceania");
        listaContinentes.add(continente);
    }

    public static Continente getContinente(int nroContinente) {
        return listaContinentes.get(nroContinente);
    }

    public static boolean sonLimitrofes(Continente origen, Continente destino) {
        if (limitrofes == null) {
            armarEstructura();
        }
        return limitrofes[origen.getNroContinente()][destino.getNroContinente()] == 1;
    }

    private static void armarEstructura() {
        limitrofes = new int[7][7];

        limitrofes[AMERICA_NORTE][AMERICA_CENTRAL] = 1;
        limitrofes[AMERICA_NORTE][EUROPA] = 1;
        limitrofes[AMERICA_NORTE][ASIA] = 1;
        limitrofes[AMERICA_NORTE][OCEANIA] = 1;

        limitrofes[AMERICA_CENTRAL][AMERICA_NORTE] = 1;
        limitrofes[AMERICA_CENTRAL][AMERICA_SUR] = 1;

        limitrofes[AMERICA_SUR][AMERICA_CENTRAL] = 1;
        limitrofes[AMERICA_SUR][AFRICA] = 1;
        limitrofes[AMERICA_SUR][OCEANIA] = 1;

        limitrofes[EUROPA][ASIA] = 1;
        limitrofes[EUROPA][AMERICA_NORTE] = 1;
        limitrofes[EUROPA][AFRICA] = 1;

        limitrofes[ASIA][AMERICA_NORTE] = 1;
        limitrofes[ASIA][EUROPA] = 1;
        limitrofes[ASIA][AFRICA] = 1;
        limitrofes[ASIA][OCEANIA] = 1;

        limitrofes[AFRICA][EUROPA] = 1;
        limitrofes[AFRICA][ASIA] = 1;
        limitrofes[AFRICA][AMERICA_SUR] = 1;

        limitrofes[ASIA][AMERICA_NORTE] = 1;
        limitrofes[ASIA][AMERICA_SUR] = 1;
        limitrofes[ASIA][ASIA] = 1;
    }
}
