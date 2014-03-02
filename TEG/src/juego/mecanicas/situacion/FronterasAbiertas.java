/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.mecanicas.situacion;

import juego.estructura.Jugador;
import juego.estructura.Pais;
import juego.estructura.GestorContinentes;

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
    public double refuerzosExtra() {
        return 0;
    }

    @Override
    public boolean puedeAtacar(Jugador jugador) {
        return true;
    }

}
