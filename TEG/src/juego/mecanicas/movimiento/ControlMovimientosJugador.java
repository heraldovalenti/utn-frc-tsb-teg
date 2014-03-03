/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.mecanicas.movimiento;

import java.util.ArrayList;
import java.util.List;
import juego.estructura.GestorPaises;
import juego.estructura.Jugador;
import juego.estructura.Pais;
import juego.mecanicas.turno.SecuenciaTurnos;

/**
 *
 * @author heril
 */
public class ControlMovimientosJugador {

    private Jugador jugador;
    private List<Movimiento> movimientos;

    public ControlMovimientosJugador() {
        this.jugador = SecuenciaTurnos.getInstancia().getActual();
        this.movimientos = new ArrayList<>();
    }

    public int maximoMovimiento(Pais origen) {
        return origen.getCantidadEjercitos() - 1;
    }

    public boolean movimientoValido(Pais origen, Pais destino, int cantidadEjercitos) {
        if (!origen.getJugador().equals(destino.getJugador())) {
            return false;
        }
        if (cantidadEjercitos > this.maximoMovimiento(origen)) {
            return false;
        }
        if (GestorPaises.sonLimitrofes(origen, destino)) {
            return false;
        }
        return true;
    }

}
