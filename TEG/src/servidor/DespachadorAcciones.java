/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import com.Accionable;

/**
 *
 * @author heril
 */
public class DespachadorAcciones extends Thread {

    private ColaAcciones colaAcciones;
    private boolean banderaEjecucion;

    public DespachadorAcciones(ColaAcciones colaAcciones) {
        this.colaAcciones = colaAcciones;
        banderaEjecucion = false;
    }

    /**
     * Verifica la existencia de entradas en la cola de acciones.
     *
     * @return true si hay entradas pendientes de procesar, false en otro caso.
     */
    private boolean entradasPendientes() {
        colaAcciones.solicitarAcceso();
        boolean res = colaAcciones.hayEntradas();
        colaAcciones.informarSalida();
        return res;
    }

    /**
     * Procesa las entradas pendientes presentes en la cola de acciones.
     */
    private void despacharEntradas() {
        colaAcciones.solicitarAcceso();
        Accionable accion = colaAcciones.pullEntrada();
        colaAcciones.informarSalida();
        accion.accionar();
    }

    /**
     * Ingresa una entrada en la cola de acciones.
     *
     * @param accion la accion a ingresarse en la cola de entrada.
     */
    public void ingresarEntrada(Accionable accion) {
        colaAcciones.solicitarAcceso();
        colaAcciones.pushEntrada(accion);
        colaAcciones.informarSalida();
    }
    
    /**
     * Ingresa una salida en la cola de acciones.
     *
     * @param accion la accion a ingresarse en la cola de salida.
     */
    public void ingresarSalida(Accionable accion) {
        colaAcciones.solicitarAcceso();
        colaAcciones.pushSalida(accion);
        colaAcciones.informarSalida();
    }

    /**
     * Indica que el despachador debe parar su ejecucion.
     */
    public void parar() {
        banderaEjecucion = false;
    }

    @Override
    public void run() {
        banderaEjecucion = true;
        while (banderaEjecucion) {
            if (entradasPendientes()) {
                despacharEntradas();
            }
            try {
                sleep(conf.Configuracion.getInstancia().tiempoEspera());
            } catch (InterruptedException ex) {
                System.err.println("error tratando de dormir: " + ex.getMessage());
            }
        }
    }
}
