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

    public void registrarMovimientoEjercito(Pais destino) {
        int cantidadAnterior = 0;
        if (ejercitosMovidos.containsKey(destino)) {
            cantidadAnterior = ejercitosMovidos.get(destino);
        }
        ejercitosMovidos.put(destino, cantidadAnterior);
    }

    public void registrarMovimientoMisil(Pais destino) {
        int cantidadAnterior = 0;
        if (misiliesMovidos.containsKey(destino)) {
            cantidadAnterior = misiliesMovidos.get(destino);
        }
        misiliesMovidos.put(destino, cantidadAnterior);
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
