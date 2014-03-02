/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package juego.estructura;

import java.util.List;

/**
 *
 * @author Daniel
 */
public class GestorJugadores {
    private List<Jugador> listaJugadores;
    
    public List<Jugador> getJugadores() {
        return this.listaJugadores;
    }
    
    public void setJugadores(List<Jugador> jugadores) {
        this.listaJugadores = jugadores;
    }
}
