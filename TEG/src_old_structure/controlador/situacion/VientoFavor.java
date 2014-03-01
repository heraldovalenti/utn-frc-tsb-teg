/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.situacion;

import modelo.juego.Jugador;
import modelo.juego.Pais;

/**
 *
 * @author heril
 */
public class VientoFavor implements Situacion {
    
    public int maximoAtaque() {
        return 4;
    }
    
    public int maximoDefensa() {
        return 3;
    }
    
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
        return true;
    }
    
    
    
}
