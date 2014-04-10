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
public class RefuerzosExtra implements Situacion {

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
    public boolean refuerzosExtra() {
        return true;
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
        return "Refuerzos extra";
    }

    @Override
    public String getDescripcion() {
        return "Se realiza una ronda extra de incorporación de ejércitos";
    }

}
