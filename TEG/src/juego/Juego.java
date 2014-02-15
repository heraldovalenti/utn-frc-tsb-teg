/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import java.util.List;
import juego.situacion.Situacion;

/**
 *
 * @author heril
 */
public class Juego {
    
    private static Juego instancia = null;
    public static Juego getInstancia() {
        if (instancia == null) instancia = new Juego();
        return instancia;
    }
    
    private Juego() {
    }
    private int idJuego;
    private List<Jugador> jugadores;
    private List<Pais> paises;
    private List<TarjetaContinente> tarjetasContinente;
    private List<TarjetaPais> tarjetasPais;
    private Estado estado;
    private Situacion situacion;

    public Situacion getSituacion() {
        return situacion;
    }

    public void setSituacion(Situacion situacion) {
        this.situacion = situacion;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public int getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }    
}
