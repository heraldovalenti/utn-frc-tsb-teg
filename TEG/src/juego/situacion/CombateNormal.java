/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.situacion;

import juego.Jugador;
import juego.Pais;



/**
 *
 * @author heril
 */
public class CombateNormal implements Situacion {
    
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
