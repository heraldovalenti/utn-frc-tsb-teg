/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.movimiento;

import modelo.juego.Ocupacion;

/**
 *
 * @author heril
 */
public class Movimiento {
    
    private Ocupacion origen;
    private Ocupacion destino;
    private int cantidadEjercitos;
    private int cantidadMisiles;

    public Movimiento(Ocupacion origen, Ocupacion destino, int cantidadEjercitos, int cantidadMisiles) {
        this.origen = origen;
        this.destino = destino;
        this.cantidadEjercitos = cantidadEjercitos;
        this.cantidadMisiles = cantidadMisiles;
    }
    
    public void ejecutarMovimiento() {
        origen.setEjercitos(origen.getEjercitos() - cantidadEjercitos);
        destino.setEjercitos(destino.getEjercitos() + cantidadEjercitos);
        
        origen.setMisiles(origen.getMisiles() - cantidadMisiles);
        destino.setMisiles(destino.getMisiles() + cantidadMisiles);
    }
    
    public void deshacerMovimiento() {
        origen.setEjercitos(origen.getEjercitos() + cantidadEjercitos);
        destino.setEjercitos(destino.getEjercitos() - cantidadEjercitos);
        
        origen.setMisiles(origen.getMisiles() + cantidadMisiles);
        destino.setMisiles(destino.getMisiles() - cantidadMisiles);
    }
}
