/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.movimiento;

import controlador.estructura.Paises;
import controlador.turno.SecuenciaTurnos;
import java.util.LinkedList;
import modelo.juego.Juego;
import modelo.juego.Jugador;
import modelo.juego.Pais;

/**
 *
 * @author heril
 */
public class ControlMovimientosJugador {
    
    private Jugador jugador;
    private LinkedList<Movimiento> movimientos;
    
    public ControlMovimientosJugador() {
        this.jugador = SecuenciaTurnos.getInstancia().getActual();
        this.movimientos = new LinkedList<Movimiento>();
    }
    
    public int maximoMovimiento(Pais origen) {
        return origen.getOcupacion().getEjercitos() - 1;
    }
    
    public boolean movimientoValido(Pais origen, Pais destino, int cantidadEjercitos) {
        if (!origen.getOcupacion().getJugador().equals(destino.getOcupacion().getJugador())) {
            return false;
        }
        if (cantidadEjercitos > this.maximoMovimiento(origen)) {
            return false;
        }
        if (!Paises.sonLimitrofes(origen, destino)) {
            return false;
        }
        return true;
    }
    
}
