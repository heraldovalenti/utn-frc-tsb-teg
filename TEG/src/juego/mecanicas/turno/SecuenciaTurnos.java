/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.mecanicas.turno;

import java.awt.Color;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import juego.Juego;
import juego.estructura.GestorJugadores;
import juego.estructura.Jugador;

/**
 *
 * @author heril
 */
public class SecuenciaTurnos {

    private static SecuenciaTurnos instancia = null;
    private List<Jugador> secuencia;
    private int actual;
    private int contadorRondas;

    public static SecuenciaTurnos getInstancia() {
        if (instancia == null) {
            instancia = new SecuenciaTurnos();
        }
        return instancia;
    }

    private SecuenciaTurnos() {
        //  secuencia = new ArrayList<>();
        actual = 0;
        contadorRondas = 1;
        secuencia = new LinkedList<>(GestorJugadores.getJugadores());
        Collections.shuffle(secuencia);
//        while (!listaJugadores.isEmpty()) {
//            double rnd = Math.random();
//            double dRes = rnd * listaJugadores.size();
//            int iRes = (int) dRes;
//            secuencia.add(listaJugadores.remove(iRes));
//        }
    }

    /**
     * Metodo para controlar si la ronda continua o ha llegado a su fin.
     *
     * @return si la ronda ha finalizado o no.
     */
    public boolean esFinRonda() {
        return actual >= (secuencia.size() - 1);
    }

    /**
     * Metodo que indica el comienzo de un nuevo turno.
     */
    public void siguienteTurno() {
        actual++;
        if (esFinRonda()) {
            nuevaRonda();
        }
    }

    /**
     * Metodo que genera una nueva ronda, pasando el primero jugador de la ronda
     * a ultimo lugar ahora.
     */
    public void nuevaRonda() {
        contadorRondas++;
        actual = 0;
        Jugador aux = secuencia.remove(0);
        secuencia.add(aux);
    }

    /**
     * Metodo para obtener el jugador que tiene el turno actual.
     *
     * @return el Jugador del turno.
     */
    public Jugador getActual() {
        return secuencia.get(actual);
    }

    public Jugador getJugadorAnterior(Jugador jugador) {
        for (int i = 0; i < secuencia.size(); i++) {
            Jugador res = secuencia.get(i);
            if (jugador.equals(res)) {
                if (i == 0) {
                    return secuencia.get(secuencia.size() - 1);
                } else {
                    return secuencia.get(i - 1);
                }
            }
        }
        return null;
    }

    public Jugador getJugadorSiguiente(Jugador jugador) {
        for (int i = 0; i < secuencia.size(); i++) {
            Jugador res = secuencia.get(i);
            if (jugador.equals(res)) {
                if (i == secuencia.size() - 1) {
                    return secuencia.get(0);
                } else {
                    return secuencia.get(i + 1);
                }
            }
        }
        return null;
    }

    /**
     * Prueba la funcionalidad de la clase.
     */
    public static void test() {
        Juego j = Juego.getInstancia();
        Set<Jugador> jugadores = new HashSet<>();
        jugadores.add(new Jugador(1, "Heraldo", Color.ORANGE));
        jugadores.add(new Jugador(2, "Tero", Color.BLACK));
        jugadores.add(new Jugador(3, "Nariz", Color.BLUE));
        jugadores.add(new Jugador(4, "Bren", Color.GREEN));
        jugadores.add(new Jugador(5, "Lulu", Color.WHITE));
        jugadores.add(new Jugador(6, "Gato", Color.RED));
        GestorJugadores.setJugadores(jugadores);
        SecuenciaTurnos st = SecuenciaTurnos.getInstancia();

        System.out.println("secuencia: ");
        for (Jugador i : st.secuencia) {
            System.out.println("\t>>" + i.toString());
        }

        st.nuevaRonda();
        System.out.println("secuencia: ");
        for (Jugador i : st.secuencia) {
            System.out.println("\t>>" + i.toString());
        }

        st.nuevaRonda();
        System.out.println("secuencia: ");
        for (Jugador i : st.secuencia) {
            System.out.println("\t>>" + i.toString());
        }

        st.nuevaRonda();
        System.out.println("secuencia: ");
        for (Jugador i : st.secuencia) {
            System.out.println("\t>>" + i.toString());
        }
    }
//    public static void main (String args[]) {
//        test();
//    }
}
