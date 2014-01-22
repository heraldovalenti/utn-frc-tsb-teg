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
public interface Situacion {

    public int maximoAtaque();

    public int maximoDefensa();

    public boolean ataquePermitido(Pais atacante, Pais defensor);
    
    public boolean puedeObtenerTarjetaPais(Jugador jugador);
    
    public double refuerzosExtra();
    
    public boolean puedeAtacar(Jugador jugador);
    
}
