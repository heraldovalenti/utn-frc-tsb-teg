/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.estructura;

import modelo.juego.Continente;

/**
 *
 * @author heril
 */
public class Continentes {

    public static final int AMERICA_NORTE = 0;
    public static final int AMERICA_CENTRAL = 1;
    public static final int AMERICA_SUR = 2;
    public static final int EUROPA = 3;
    public static final int ASIA = 4;
    public static final int AFRICA = 5;
    public static final int OCEANIA = 6;
    private static int[][] limitrofes = null;

    public static Continente getContinente(int nroContinente) {
        Continente res = null;
        if (nroContinente == AMERICA_NORTE) {
            res = new Continente(AMERICA_NORTE, "America del Norte");
        }
        if (nroContinente == AMERICA_CENTRAL) {
            res = new Continente(AMERICA_CENTRAL, "America Central");
        }
        if (nroContinente == AMERICA_SUR) {
            res = new Continente(AMERICA_SUR, "America del Sur");
        }
        if (nroContinente == EUROPA) {
            res = new Continente(EUROPA, "Europa");
        }
        if (nroContinente == ASIA) {
            res = new Continente(ASIA, "Asia");
        }
        if (nroContinente == AFRICA) {
            res = new Continente(AFRICA, "Africa");
        }
        if (nroContinente == OCEANIA) {
            res = new Continente(OCEANIA, "Oceania");
        }
        return res;
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
