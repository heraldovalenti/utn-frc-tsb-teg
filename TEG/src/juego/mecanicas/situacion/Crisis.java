/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.mecanicas.situacion;

import java.util.ArrayList;
import java.util.List;
import juego.estructura.Jugador;
import juego.estructura.Pais;
import juego.GeneradorTiradas;
import juego.estructura.GestorJugadores;

/**
 *
 * @author heril
 */
public class Crisis implements Situacion {

    private List<Jugador> jugadoresEnCrisis;

    public Crisis() {
        jugadoresEnCrisis = new ArrayList<>();
        List<Jugador> jugadoresJuego = new ArrayList(GestorJugadores.getJugadores());
        int[] tiradas = new int[jugadoresJuego.size()];
        int tiradaMasBaja = 10;
        for (int i = 0; i < tiradas.length; i++) {
            tiradas[i] = GeneradorTiradas.generarTirada();
            if (tiradas[i] <= tiradaMasBaja) {
                tiradaMasBaja = tiradas[i];
            }
        }
        for (int i = 0; i < tiradas.length; i++) {
            if (tiradas[i] == tiradaMasBaja) {
                jugadoresEnCrisis.add(jugadoresJuego.get(i));
            }
        }
    }

    public int maximoAtaque() {
        return 3;
    }

    public int maximoDefensa() {
        return 3;
    }

    public boolean ataquePermitido(Pais atacante, Pais defensor) {
        return true;
    }

    @Override
    public boolean puedeObtenerTarjetaPais(Jugador jugador) {
        return !jugadoresEnCrisis.contains(jugador);
    }

    @Override
    public boolean puedeAtacar(Jugador jugador) {
        return true;
    }

    @Override
    public boolean refuerzosExtra() {
        return false;
    }

    @Override
    public List<Jugador> jugadoresEnCrisis() {
        return jugadoresEnCrisis;
    }

}
