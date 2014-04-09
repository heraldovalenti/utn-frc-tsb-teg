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

    private final List<Jugador> jugadoresEnCrisis;

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
    public boolean puedeReagrupar(Jugador jugador) {
        return true;
    }

    @Override
    public String getNombre() {
        return "Crisis";
    }

    @Override
    public String getDescripcion() {
        return armarDescripcion();
    }

    private String armarDescripcion() {
        int cantidadJugadores = jugadoresEnCrisis.size();
        StringBuilder builder = new StringBuilder();
        if (cantidadJugadores == 1) {
            builder.append("El jugador ");
            builder.append(jugadoresEnCrisis.get(0));
            builder.append(" ");
        } else {
            builder.append("Los jugadores ");
            for (int i = 0; i < cantidadJugadores; i++) {
                builder.append(jugadoresEnCrisis.get(i));
                if (i + 1 < cantidadJugadores) {
                    builder.append(", ");
                } else {
                    builder.append(" y ");
                }
            }
        }
        builder.append(" no puede solicitar tarjetas de país");
        return builder.toString();

    }

}
