/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.mecanicas.situacion;

import java.io.Serializable;
import java.util.List;
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

    public boolean refuerzosExtra();

    public boolean puedeAtacar(Jugador jugador);

    public boolean puedeReagrupar(Jugador jugador);

    public String getNombre();

    public String getDescripcion();

}
