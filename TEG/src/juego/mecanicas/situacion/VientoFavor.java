/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.mecanicas.situacion;

import juego.estructura.Jugador;
import juego.estructura.Pais;

/**
 *
 * @author heril
 */
public class VientoFavor implements Situacion {

    @Override
    public int maximoAtaque() {
        return 4;
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
    public boolean refuerzosExtra() {
        return false;
    }

    @Override
    public boolean puedeAtacar(Jugador jugador) {
        return true;
    }

    @Override
    public boolean puedeReagrupar(Jugador jugador) {
        return true;
    }

    @Override
    public String getNombre() {
        return "Viento a favor";
    }

    @Override
    public String getDescripcion() {
        return "Los jugadores atacantes utilizan un dado extra para atacar";
    }

}
