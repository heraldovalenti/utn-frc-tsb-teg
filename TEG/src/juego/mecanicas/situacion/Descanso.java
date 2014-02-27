/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.mecanicas.situacion;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import juego.Juego;
import juego.estructura.Jugador;
import juego.estructura.Pais;


/**
 *
 * @author heril
 */
public class Descanso implements Situacion {

    private static int[] descansos = null;
    private static Color[] colores = null;
    private static int maxDescansos = 0;
    private static int jugadorEnDescanso = -1;

    public Descanso() {
        if (descansos == null) {
            List<Jugador> jugadores = Juego.getInstancia().getJugadores();
            descansos = new int[jugadores.size()];
            colores = new Color[jugadores.size()];
            maxDescansos = 0;
            for (int i = 0; i < jugadores.size(); i++) {
                Jugador j = jugadores.get(i);
                colores[i] = j.getColor();
            }
        }
        elegirJugador();
    }

    private void elegirJugador() {
        boolean b = true;
        for (int d : descansos) {
            b = b && (d == maxDescansos);
        }
        if (b) {
            maxDescansos++;
        }
        do {
            double rnd = Math.random();
            double dRes = 0 + (rnd * colores.length);
            int iRes = (int) dRes;
            jugadorEnDescanso = iRes;
        } while (descansos[jugadorEnDescanso] == maxDescansos);
        descansos[jugadorEnDescanso]++;
    }

    @Override
    public int maximoAtaque() {
        return 3;
    }

    @Override
    public int maximoDefensa() {
        return 3;
    }

    @Override
    public boolean ataquePermitido(Pais atacante, Pais defensor) {
        return true;
    }

    @Override
    public boolean puedeObtenerTarjetaPais(Jugador jugador) {
        return true;
    }

    @Override
    public double refuerzosExtra() {
        return 0;
    }

    @Override
    public boolean puedeAtacar(Jugador jugador) {
        int indiceJugador = -1;
        for (int i = 0; i < colores.length; i++) {
            if (jugador.getColor().equals(colores[i])) {
                indiceJugador = i;
                break;
            }
        }
        return indiceJugador != jugadorEnDescanso;
    }

    /**
     * Prueba la funcionalidad de la clase.
     */
    private static void test() {
        Juego j = Juego.getInstancia();
        List<Jugador> jugadores = new LinkedList();
        Jugador j1, j2, j3;
        j1 = new Jugador();
        j1.setColor(Color.BLUE);
        j2 = new Jugador();
        j2.setColor(Color.RED);
        j3 = new Jugador();
        j3.setColor(Color.GREEN);
        jugadores.add(j1);
        jugadores.add(j2);
        jugadores.add(j3);
        j.setJugadores(jugadores);
        Color colorPenalizado = null;
        System.out.println("j1="+j1.getColor()+";j2="+j2.getColor()+";j3="+j3.getColor());

        for (int i = 0; i < 10; i++) {
            j.setSituacion(new Descanso());
            if (!j.getSituacion().puedeAtacar(j1)) {
                colorPenalizado = j1.getColor();
            }
            if (!j.getSituacion().puedeAtacar(j2)) {
                colorPenalizado = j2.getColor();
            }
            if (!j.getSituacion().puedeAtacar(j3)) {
                colorPenalizado = j3.getColor();
            }
            System.out.println("j1=" + j.getSituacion().puedeAtacar(j1)
                    + ";j2=" + j.getSituacion().puedeAtacar(j2)
                    + ";j3=" + j.getSituacion().puedeAtacar(j3)
                    + ";colorPenalizado=" + colorPenalizado);
            System.out.println("descansos=[" + descansos[0] + "," + descansos[1] + ","+descansos[2]+"]");
        }
        System.out.println("END");
    }
}
