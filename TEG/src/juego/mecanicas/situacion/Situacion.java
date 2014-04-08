/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.mecanicas.situacion;

import java.io.Serializable;
import juego.estructura.Jugador;
import juego.estructura.Pais;



/**
 *
 * @author heril
 */
public interface Situacion extends Serializable {

    public int maximoAtaque();

    public int maximoDefensa();

    public boolean ataquePermitido(Pais atacante, Pais defensor);
    
    public boolean puedeObtenerTarjetaPais(Jugador jugador);
    
    public double refuerzosExtra();
    
    public boolean puedeAtacar(Jugador jugador);
    
}
