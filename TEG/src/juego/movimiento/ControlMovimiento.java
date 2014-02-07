/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.movimiento;

import juego.Pais;
import juego.estructura.Paises;


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
        return origen.getOcupacion().getEjercitos() - 1;
    }

    public boolean movimientoValido() {
        if (!origen.getOcupacion().getJugador().equals(destino.getOcupacion().getJugador())) {
            return false;
        }
        if (cantidadEjercitos > this.maximoMovimiento()) {
            return false;
        }
        if (!Paises.sonLimitrofes(origen, destino)) {
            return false;
        }
        return true;
    }
}
