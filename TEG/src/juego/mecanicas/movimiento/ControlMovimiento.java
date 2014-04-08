/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.mecanicas.movimiento;

import juego.estructura.Pais;
import juego.estructura.GestorPaises;


/**
 *
 * @author heril
 */
public class ControlMovimiento {

    private Pais origen;
    private Pais destino;
    private int cantidadEjercitos;
    private int cantidadMisiles;
    private Movimiento movimiento;

    public ControlMovimiento(Pais origen, Pais destino, int cantidadEjercitos, int cantidadMisiles) {
        this.origen = origen;
        this.destino = destino;
        this.cantidadEjercitos = cantidadEjercitos;
        this.cantidadMisiles = cantidadMisiles;
    }

    public int maximoMovimiento() {
        return origen.getCantidadEjercitos() - 1;
    }

    public boolean movimientoValido() {
        if (!origen.getJugador().equals(destino.getJugador())) {
            return false;
        }
        if (cantidadEjercitos > this.maximoMovimiento()) {
            return false;
        }
       if(!GestorPaises.sonLimitrofes(origen, destino)) {
            return false;
        }
        return true;
    }
}
