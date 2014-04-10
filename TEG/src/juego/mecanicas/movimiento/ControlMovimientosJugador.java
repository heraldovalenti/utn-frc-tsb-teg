/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.mecanicas.movimiento;

import java.util.HashMap;
import java.util.Map;
import juego.estructura.Pais;

/**
 *
 * @author heril
 */
public class ControlMovimientosJugador {

    private final Map<Pais, Integer> ejercitosMovidos = new HashMap<>();
    private final Map<Pais, Integer> misiliesMovidos = new HashMap<>();

    public ControlMovimientosJugador() {
    }

    public void registrarMovimiento(Pais destino, int cantidadEjercitos, int cantidadMisiles){
        if(cantidadEjercitos>0){
            registrarMovimientoEjercito(destino, cantidadEjercitos);
        }
        if(cantidadMisiles>0){
            registrarMovimientoMisil(destino, cantidadMisiles);
        }
    }
    
    private void registrarMovimientoEjercito(Pais destino, int cantidad) {
        int cantidadAnterior = 0;
        if (ejercitosMovidos.containsKey(destino)) {
            cantidadAnterior = ejercitosMovidos.get(destino);
        }
        ejercitosMovidos.put(destino, cantidadAnterior + cantidad);
    }

    private void registrarMovimientoMisil(Pais destino, int cantidad) {
        int cantidadAnterior = 0;
        if (misiliesMovidos.containsKey(destino)) {
            cantidadAnterior = misiliesMovidos.get(destino);
        }
        misiliesMovidos.put(destino, cantidadAnterior + cantidad);
    }

    public int getEjercitosNuevos(Pais origen) {
        int cantidad = 0;
        if (ejercitosMovidos.containsKey(origen)) {
            cantidad = ejercitosMovidos.get(origen);
        }
        return cantidad;
    }

    public int getMisilesNuevos(Pais origen) {
        int cantidad = 0;
        if (misiliesMovidos.containsKey(origen)) {
            cantidad = misiliesMovidos.get(origen);
        }
        return cantidad;
    }
}
