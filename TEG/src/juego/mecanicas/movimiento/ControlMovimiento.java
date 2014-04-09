/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.mecanicas.movimiento;

import juego.Juego;
import juego.estructura.Pais;
import juego.estructura.GestorPaises;
import juego.mecanicas.situacion.Situacion;

/**
 *
 * @author heril
 */
public class ControlMovimiento {

    private Pais origen;
    private Pais destino;
    private int cantidadEjercitos;
    private int cantidadMisiles;
    private Situacion situacion;
    private ControlMovimientosJugador movimientosRealizados;

    public ControlMovimiento(Pais origen, Pais destino, int cantidadEjercitos, int cantidadMisiles, Situacion situacion, ControlMovimientosJugador movimientosRealizados) {
        this.origen = origen;
        this.destino = destino;
        this.cantidadEjercitos = cantidadEjercitos;
        this.cantidadMisiles = cantidadMisiles;
        this.situacion = situacion;
        this.movimientosRealizados = movimientosRealizados;
    }

//    public int maximoMovimiento() {
//        return origen.getCantidadEjercitos() - 1;
//    }
//
//    public boolean movimientoValido() {
//        if (!origen.getJugador().equals(destino.getJugador())) {
//            return false;
//        }
//        if (cantidadEjercitos > this.maximoMovimiento()) {
//            return false;
//        }
//       if(!GestorPaises.sonLimitrofes(origen, destino)) {
//            return false;
//        }
//        return true;
//    }
    private int maximoMovimientoEjercitos() {
        int ejercitosNuevos = movimientosRealizados.getEjercitosNuevos(origen);
        return origen.getCantidadEjercitos() - ejercitosNuevos - 1;
    }

    private int maximoMovimientoMisiles() {
        int misilesNuevos = movimientosRealizados.getMisilesNuevos(origen);
        return origen.getCantidadEjercitos() - misilesNuevos;
    }

    public boolean movimientoValido() {
        if (!GestorPaises.sonLimitrofes(origen, destino)) {
            return false;
        }
        if (!origen.getJugador().equals(destino.getJugador())) {
            return false;
        }
        if (!Juego.getInstancia().getSituacion().puedeReagrupar(origen.getJugador())) {
            return false;
        }
        if (cantidadEjercitos > maximoMovimientoEjercitos()) {
            return false;
        }
        if (cantidadMisiles > maximoMovimientoMisiles()) {
            return false;
        }
        if (maximoMovimientoEjercitos() == 0 && maximoMovimientoMisiles() == 0) {
            return false;
        }
        if (!situacion.puedeReagrupar(origen.getJugador())) {
            return false;
        }
        return true;
    }
}
