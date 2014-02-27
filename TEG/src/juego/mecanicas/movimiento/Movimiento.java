/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.mecanicas.movimiento;

import juego.estructura.Pais;

/**
 *
 * @author heril
 */
public class Movimiento {

    private Pais origen;
    private Pais destino;
    private int cantidadEjercitos;
    private int cantidadMisiles;

    public Movimiento(Pais origen, Pais destino, int cantidadEjercitos, int cantidadMisiles) {
        this.origen = origen;
        this.destino = destino;
        this.cantidadEjercitos = cantidadEjercitos;
        this.cantidadMisiles = cantidadMisiles;
    }

    public void ejecutarMovimiento() {
        origen.setCantidadEjercitos(origen.getCantidadEjercitos() - cantidadEjercitos);
        destino.setCantidadEjercitos(destino.getCantidadEjercitos() + cantidadEjercitos);

        origen.setCantidadMisiles(origen.getCantidadMisiles() - cantidadMisiles);
        destino.setCantidadMisiles(destino.getCantidadMisiles() + cantidadMisiles);
    }

    public void deshacerMovimiento() {
        origen.setCantidadEjercitos(origen.getCantidadEjercitos() + cantidadEjercitos);
        destino.setCantidadEjercitos(destino.getCantidadEjercitos() - cantidadEjercitos);

        origen.setCantidadMisiles(origen.getCantidadMisiles() + cantidadMisiles);
        destino.setCantidadMisiles(destino.getCantidadMisiles() - cantidadMisiles);
    }
}
