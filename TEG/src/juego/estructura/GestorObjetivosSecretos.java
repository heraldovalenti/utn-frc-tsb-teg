/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.estructura;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import juego.mecanicas.turno.SecuenciaTurnos;

/**
 *
 * @author Daniel
 */
public class GestorObjetivosSecretos {

    private static List<ObjetivoSecreto> listaObjetivos = null;

    private static void crearObjetivos() {
        listaObjetivos = new ArrayList<>(19);
        Map<Continente, Integer> paisesPorContinenteAOcupar;
        paisesPorContinenteAOcupar = new HashMap<>(3);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AMERICA_SUR), 8);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.EUROPA), 16);
        listaObjetivos.add(new ObjetivoSecreto(1, "Ocupar Europa y América del Sur", paisesPorContinenteAOcupar, null, 0, 0, false));
        paisesPorContinenteAOcupar = new HashMap<>(4);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AMERICA_NORTE), 12);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.OCEANIA), 6);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AFRICA), 5);
        listaObjetivos.add(new ObjetivoSecreto(2, "Ocupar América del Norte, Oceanía y 5 países de África", paisesPorContinenteAOcupar, null, 0, 0, false));
        paisesPorContinenteAOcupar = new HashMap<>(3);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.ASIA), 16);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AMERICA_CENTRAL), 6);
        listaObjetivos.add(new ObjetivoSecreto(3, "Ocupar Asia y América Central", paisesPorContinenteAOcupar, null, 0, 0, false));
        paisesPorContinenteAOcupar = new HashMap<>(4);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AMERICA_NORTE), 12);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.ASIA), 8);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.EUROPA), 4);
        listaObjetivos.add(new ObjetivoSecreto(4, "Ocupar América del Norte, 8 países de Asia y 4 de Europa", paisesPorContinenteAOcupar, null, 0, 0, false));
        paisesPorContinenteAOcupar = new HashMap<>(10);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AMERICA_NORTE), 4);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.EUROPA), 4);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.ASIA), 4);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AMERICA_SUR), 3);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AMERICA_CENTRAL), 3);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AFRICA), 3);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.OCEANIA), 3);
        listaObjetivos.add(new ObjetivoSecreto(5, "Ocupar 4 países de América del Norte, 4 de Europa, 4 de Asia, 3 de América del Sur, 3 de América Central 3 de Africa y 3 de Oceanía", paisesPorContinenteAOcupar, null, 0, 0, false));
        paisesPorContinenteAOcupar = new HashMap<>(10);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.OCEANIA), 6);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.ASIA), 6);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AFRICA), 6);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AMERICA_NORTE), 6);
        listaObjetivos.add(new ObjetivoSecreto(6, "Ocupar Oceanía, 6 paises de Asia, 6 de Africa y 6 de América del Norte", paisesPorContinenteAOcupar, null, 0, 0, false));
        paisesPorContinenteAOcupar = new HashMap<>(10);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AMERICA_CENTRAL), 6);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AMERICA_SUR), 6);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.EUROPA), 6);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.ASIA), 6);
        listaObjetivos.add(new ObjetivoSecreto(7, "Ocupar América Central, 6 países de América del Sur, 6 de Europa y 6 de Asia", paisesPorContinenteAOcupar, null, 0, 0, false));
        paisesPorContinenteAOcupar = new HashMap<>(4);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AMERICA_SUR), 8);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AFRICA), 8);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.ASIA), 8);
        listaObjetivos.add(new ObjetivoSecreto(8, "Ocupar América del Sur, Africa y 8 países de Asia", paisesPorContinenteAOcupar, null, 0, 0, false));
        paisesPorContinenteAOcupar = new HashMap<>(10);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.OCEANIA), 6);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AFRICA), 8);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AMERICA_CENTRAL), 4);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.ASIA), 4);
        listaObjetivos.add(new ObjetivoSecreto(9, "Ocupar Oceanía, Africa, 4 países de América Central y 4 de Asia", paisesPorContinenteAOcupar, null, 0, 0, false));
        paisesPorContinenteAOcupar = new HashMap<>(4);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.EUROPA), 16);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.ASIA), 4);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AMERICA_SUR), 4);
        listaObjetivos.add(new ObjetivoSecreto(10, "Ocupar Europa, 4 países de Asia y 4 países de América del Sur", paisesPorContinenteAOcupar, null, 0, 0, false));
        paisesPorContinenteAOcupar = new HashMap<>(4);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AFRICA), 8);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.EUROPA), 4);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.ASIA), 4);
        listaObjetivos.add(new ObjetivoSecreto(11, "Ocupar Africa, 4 países de Europa, 4 países de Asia y 6 islas, repartidas en por lo menos tres continentes", paisesPorContinenteAOcupar, null, 0, 6, false));
        listaObjetivos.add(new ObjetivoSecreto(12, "Ocupar 35 países en cualquier lugar del mapa", null, null, 35, 0, false));
        listaObjetivos.add(new ObjetivoSecreto(13, "Destruir al ejército Blanco; de ser imposible, al jugador de la derecha", null, Color.WHITE, 0, 0, false));
        listaObjetivos.add(new ObjetivoSecreto(14, "Destruir al ejército Negro; de ser imposible, al jugador de la derecha", null, Color.BLACK, 0, 0, false));
        listaObjetivos.add(new ObjetivoSecreto(15, "Destruir al ejército Rojo; de ser imposible, al jugador de la derecha", null, Color.RED, 0, 0, false));
        listaObjetivos.add(new ObjetivoSecreto(16, "Destruir al ejército Azul; de ser imposible, al jugador de la derecha", null, Color.BLUE, 0, 0, false));
        listaObjetivos.add(new ObjetivoSecreto(17, "Destruir al ejército Amarillo; de ser imposible, al jugador de la derecha", null, Color.YELLOW, 0, 0, false));
        listaObjetivos.add(new ObjetivoSecreto(18, "Destruir al ejército Verde; de ser imposible, al jugador de la derecha", null, Color.GREEN, 0, 0, false));
        listaObjetivos.add(new ObjetivoSecreto(19, "Destruir al jugador de la izquierda", null, null, 0, 0, true));
    }

    public static List<ObjetivoSecreto> getListaObjetivos(int cantidadJugadores) {
        if (listaObjetivos == null) {
            if (cantidadJugadores <= 3) {
                crearObjetivosReducido();
            } else {
                crearObjetivos();
            }
        }
        return listaObjetivos;
    }

    private static void crearObjetivosReducido() {
        listaObjetivos = new ArrayList<>(19);
        Map<Continente, Integer> paisesPorContinenteAOcupar;
        paisesPorContinenteAOcupar = new HashMap<>(3);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AMERICA_SUR), 8);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.EUROPA), 16);
        listaObjetivos.add(new ObjetivoSecreto(1, "Ocupar Europa y América del Sur", paisesPorContinenteAOcupar, null, 0, 0, false));
        paisesPorContinenteAOcupar = new HashMap<>(4);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AMERICA_NORTE), 12);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.OCEANIA), 6);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AFRICA), 5);
        listaObjetivos.add(new ObjetivoSecreto(2, "Ocupar América del Norte, Oceanía y 5 países de África", paisesPorContinenteAOcupar, null, 0, 0, false));
        paisesPorContinenteAOcupar = new HashMap<>(3);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.ASIA), 16);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AMERICA_CENTRAL), 6);
        listaObjetivos.add(new ObjetivoSecreto(3, "Ocupar Asia y América Central", paisesPorContinenteAOcupar, null, 0, 0, false));
        paisesPorContinenteAOcupar = new HashMap<>(4);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AMERICA_NORTE), 12);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.ASIA), 8);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.EUROPA), 4);
        listaObjetivos.add(new ObjetivoSecreto(4, "Ocupar América del Norte, 8 países de Asia y 4 de Europa", paisesPorContinenteAOcupar, null, 0, 0, false));
        paisesPorContinenteAOcupar = new HashMap<>(10);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AMERICA_NORTE), 4);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.EUROPA), 4);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.ASIA), 4);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AMERICA_SUR), 3);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AMERICA_CENTRAL), 3);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AFRICA), 3);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.OCEANIA), 3);
        listaObjetivos.add(new ObjetivoSecreto(5, "Ocupar 4 países de América del Norte, 4 de Europa, 4 de Asia, 3 de América del Sur, 3 de América Central 3 de Africa y 3 de Oceanía", paisesPorContinenteAOcupar, null, 0, 0, false));
        paisesPorContinenteAOcupar = new HashMap<>(10);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.OCEANIA), 6);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.ASIA), 6);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AFRICA), 6);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AMERICA_NORTE), 6);
        listaObjetivos.add(new ObjetivoSecreto(6, "Ocupar Oceanía, 6 paises de Asia, 6 de Africa y 6 de América del Norte", paisesPorContinenteAOcupar, null, 0, 0, false));
        paisesPorContinenteAOcupar = new HashMap<>(10);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AMERICA_CENTRAL), 6);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AMERICA_SUR), 6);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.EUROPA), 6);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.ASIA), 6);
        listaObjetivos.add(new ObjetivoSecreto(7, "Ocupar América Central, 6 países de América del Sur, 6 de Europa y 6 de Asia", paisesPorContinenteAOcupar, null, 0, 0, false));
        paisesPorContinenteAOcupar = new HashMap<>(4);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AMERICA_SUR), 8);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AFRICA), 8);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.ASIA), 8);
        listaObjetivos.add(new ObjetivoSecreto(8, "Ocupar América del Sur, Africa y 8 países de Asia", paisesPorContinenteAOcupar, null, 0, 0, false));
        paisesPorContinenteAOcupar = new HashMap<>(10);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.OCEANIA), 6);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AFRICA), 8);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AMERICA_CENTRAL), 4);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.ASIA), 4);
        listaObjetivos.add(new ObjetivoSecreto(9, "Ocupar Oceanía, Africa, 4 países de América Central y 4 de Asia", paisesPorContinenteAOcupar, null, 0, 0, false));
        paisesPorContinenteAOcupar = new HashMap<>(4);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.EUROPA), 16);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.ASIA), 4);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AMERICA_SUR), 4);
        listaObjetivos.add(new ObjetivoSecreto(10, "Ocupar Europa, 4 países de Asia y 4 países de América del Sur", paisesPorContinenteAOcupar, null, 0, 0, false));
        paisesPorContinenteAOcupar = new HashMap<>(4);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.AFRICA), 8);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.EUROPA), 4);
        paisesPorContinenteAOcupar.put(GestorContinentes.getContinente(GestorContinentes.ASIA), 4);
        listaObjetivos.add(new ObjetivoSecreto(11, "Ocupar Africa, 4 países de Europa, 4 países de Asia y 6 islas, repartidas en por lo menos tres continentes", paisesPorContinenteAOcupar, null, 0, 6, false));
    }

//    public static void main(String args[]) {
//        crearObjetivos();
//        Jugador jugador1 = new Jugador(1, "EL_VERDE", Color.green);
//        Jugador jugador2 = new Jugador(2, "EL_AZUL", Color.blue);
//        Jugador jugador3 = new Jugador(3, "EL_BLANCO", Color.white);
////        Jugador jugador4 = new Jugador(4, "EL_ROJO", Color.red);
////        Jugador jugador5 = new Jugador(5, "EL_NEGRO", Color.black);
////        Jugador jugador6 = new Jugador(6, "EL_AMARILLO", Color.yellow);
//        
//        ArrayList<Jugador> listaSecuencia = new ArrayList<>();
//        listaSecuencia.add(jugador1);
//        listaSecuencia.add(jugador2);
//        listaSecuencia.add(jugador3);
////        listaSecuencia.add(jugador4);
////        listaSecuencia.add(jugador5);
////        listaSecuencia.add(jugador6);
//        SecuenciaTurnos.getInstancia().setSecuencia(listaSecuencia);
//        
//        GestorJugadores.añadirJugador(jugador1);
//        GestorJugadores.añadirJugador(jugador2);
//        GestorJugadores.añadirJugador(jugador3);
////        GestorJugadores.añadirJugador(jugador4);
////        GestorJugadores.añadirJugador(jugador5);
////        GestorJugadores.añadirJugador(jugador6);
//        
//        List<Pais> paises = new ArrayList<>(GestorPaises.getListaPaises());
//        LinkedList<Jugador> jugadores = new LinkedList(GestorJugadores.getJugadores());
//        Collections.shuffle(paises);
//        Collections.shuffle(jugadores);
//        for (Pais p : paises) {
//            Jugador j = jugadores.removeFirst();
//            jugadores.add(j);
//            j.añadirPais(p);
//            p.setCantidadMisiles(0);
//            p.setCantidadEjercitos(1);
//        }
//        
//        ObjetivoSecreto destruirVerde = listaObjetivos.get(16);
//        System.out.println("esperado->false_real->"+destruirVerde.comprobarVictoria(jugador2));
//        ArrayList<Pais> paisesToRemove = new ArrayList<>(jugador3.getConjuntoPaises());
//        for (int i = 0; i < paisesToRemove.size(); i++) {
//            paisesToRemove.get(i).ocuparPais(jugador2);
//        }
//        System.out.println("esperado->false_real->"+destruirVerde.comprobarVictoria(jugador2));
//        
//        paisesToRemove = new ArrayList<>(jugador1.getConjuntoPaises());
//        for (int i = 0; i < paisesToRemove.size(); i++) {
//            paisesToRemove.get(i).ocuparPais(jugador2);
//        }
//        System.out.println("esperado->true_real->"+destruirVerde.comprobarVictoria(jugador2));
//    }
}
