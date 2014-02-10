/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import juego.estructura.Continentes;

/**
 *
 * @author Daniel
 */
public class GestorObjetivoSecreto {

    private static Set<ObjetivoSecreto> conjuntoObjetivos;

    public static void crearObjetivos() {
        conjuntoObjetivos = new HashSet<>(19);
        Map<Continente, Integer> paisesPorContinenteAOcupar;
        Jugador jugadorADestruir;
        Map<Continente, Integer> islasPorContinenteAOcupar;
        paisesPorContinenteAOcupar = new HashMap<>();
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.AMERICA_SUR), 8);
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.EUROPA), 16);
        conjuntoObjetivos.add(new ObjetivoSecreto(1, "Ocupar Europa y América del Sur", paisesPorContinenteAOcupar, null, 0, null));
        paisesPorContinenteAOcupar = new HashMap<>();
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.AMERICA_NORTE), 12);
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.OCEANIA), 6);
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.AFRICA), 5);
        conjuntoObjetivos.add(new ObjetivoSecreto(2, "Ocupar América del Norte, Oceanía y 5 países de África", paisesPorContinenteAOcupar, null, 0, null));
        paisesPorContinenteAOcupar = new HashMap<>();
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.ASIA), 16);
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.AMERICA_CENTRAL), 6);
        conjuntoObjetivos.add(new ObjetivoSecreto(3, "Ocupar Asia y América Central", paisesPorContinenteAOcupar, null, 0, null));
        paisesPorContinenteAOcupar = new HashMap<>();
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.AMERICA_NORTE), 12);
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.ASIA), 8);
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.EUROPA), 4);
        conjuntoObjetivos.add(new ObjetivoSecreto(4, "Ocupar América del Norte, 8 países de Asia y 4 de Europa", paisesPorContinenteAOcupar, null, 0, null));
        paisesPorContinenteAOcupar = new HashMap<>();
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.AMERICA_NORTE), 4);
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.EUROPA), 4);
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.ASIA), 4);
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.AMERICA_SUR), 3);
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.AMERICA_CENTRAL), 3);
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.AFRICA), 3);
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.OCEANIA), 3);
        conjuntoObjetivos.add(new ObjetivoSecreto(5, "Ocupar 4 países de América del Norte, 4 de Europa, 4 de Asia, 3 de América del Sur, 3 de América Central 3 de Africa y 3 de Oceanía", paisesPorContinenteAOcupar, null, 0, null));
        paisesPorContinenteAOcupar = new HashMap<>();
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.OCEANIA), 6);
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.ASIA), 6);
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.AFRICA), 6);
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.AMERICA_NORTE), 6);
        conjuntoObjetivos.add(new ObjetivoSecreto(6, "Ocupar Oceanía, 6 paises de Asia, 6 de Africa y 6 de América del Norte", paisesPorContinenteAOcupar, null, 0, null));
        paisesPorContinenteAOcupar = new HashMap<>();
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.AMERICA_CENTRAL), 6);
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.AMERICA_SUR), 6);
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.EUROPA), 6);
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.ASIA), 6);
        conjuntoObjetivos.add(new ObjetivoSecreto(7, "Ocupar América Central, 6 países de América del Sur, 6 de Europa y 6 de Asia", paisesPorContinenteAOcupar, null, 0, null));
        paisesPorContinenteAOcupar = new HashMap<>();
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.AMERICA_SUR), 8);
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.AFRICA), 8);
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.ASIA), 8);
        conjuntoObjetivos.add(new ObjetivoSecreto(8, "Ocupar América del Sur, Africa y 8 países de Asia", paisesPorContinenteAOcupar, null, 0, null));
        paisesPorContinenteAOcupar = new HashMap<>();
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.OCEANIA), 6);
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.AFRICA), 8);
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.AMERICA_CENTRAL), 4);
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.ASIA), 4);
        conjuntoObjetivos.add(new ObjetivoSecreto(9, "Ocupar Oceanía, Africa, 4 países de América Central y 4 de Asia", paisesPorContinenteAOcupar, null, 0, null));
        paisesPorContinenteAOcupar = new HashMap<>();
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.EUROPA), 16);
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.ASIA), 4);
        paisesPorContinenteAOcupar.put(Continentes.getContinente(Continentes.AMERICA_SUR), 4);
        conjuntoObjetivos.add(new ObjetivoSecreto(9, "Ocupar Europa, 4 países de Asia y 4 países de América del Sur", paisesPorContinenteAOcupar, null, 0, null));

    }

}
