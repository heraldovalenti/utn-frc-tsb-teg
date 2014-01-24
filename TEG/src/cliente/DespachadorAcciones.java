/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import servidor.*;

/**
 *
 * @author heril
 */
public class DespachadorAcciones {
    
    private ColaAcciones colaAcciones;
    private ConexionServidor conexionServidor;
    
    public DespachadorAcciones(ColaAcciones colaAcciones, ConexionServidor conexionServidor) {
        this.colaAcciones = colaAcciones;
        this.conexionServidor = conexionServidor;
    }
    
    public void despacharAcciones() {
        despacharEntradas();
        despacharSalidas();
    }
    
    private void despacharEntradas() {
        if (colaAcciones.hayEntradas()) {
            colaAcciones.pullEntrada().accionar();
        }
    }
    
    private void despacharSalidas() {
        if (colaAcciones.haySalidas()) {
            conexionServidor.enviar(colaAcciones.pullSalida());
        }
    }
    
}
