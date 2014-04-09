/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.mecanicas.situacion;

import java.util.List;
import juego.estructura.GestorContinentes;
import juego.estructura.Jugador;
import juego.estructura.Pais;

/**
 *
 * @author heril
 */
public class FronterasAbiertas implements Situacion {

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
        if (atacante.mismoContinente(defensor)) {
            return false;
        }
        return GestorContinentes.sonLimitrofes(atacante.getContinente(), defensor.getContinente());
    }

    @Override
    public boolean puedeObtenerTarjetaPais(Jugador jugador) {
        return true;
    }

    @Override
    public boolean refuerzosExtra() {
        return false;
    }

    @Override
    public boolean puedeAtacar(Jugador jugador) {
        return true;
    }

    @Override
    public List<Jugador> jugadoresEnCrisis() {
        return null;
    }

}
