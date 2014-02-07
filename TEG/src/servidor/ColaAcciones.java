/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import com.Accionable;
import java.util.LinkedList;
import logger.LogItem;

/**
 *
 * @author heril
 */
public class ColaAcciones {
    
    private LinkedList<Accionable> colaEntrada;
    private LinkedList<Accionable> colaSalida;
    private boolean accesoPermitido;
    
    public ColaAcciones() {
        colaEntrada = new LinkedList<>();
        colaSalida = new LinkedList<>();
        accesoPermitido = true;
    }
    
    public boolean hayEntradas() {
        return !colaEntrada.isEmpty();
    }
    
    public boolean haySalidas() {
        return !colaSalida.isEmpty();
    }
    
    public void pushEntrada(Accionable entrada) {
        colaEntrada.addLast(entrada);
    }
    
    public void pushSalida(Accionable salida) {
        colaSalida.addLast(salida);
    }
    
    public Accionable pullEntrada() {
        return colaEntrada.removeFirst();
    }
    
    public Accionable pullSalida() {
        return colaSalida.removeFirst();
    }
    
    public synchronized void solicitarAcceso() {
        while (!accesoPermitido) {
            try {
                wait();
            } catch (InterruptedException ex) {
                ServerManager.getInstance().getLogger().addLogItem(
                        new LogItem("Error en solicitud de acceso a cola de acciones. ", ex)                        
                        );
            }
        }
        accesoPermitido = false;
    }
    
    public synchronized void informarSalida() {
        accesoPermitido = true;
        notifyAll();
    }
    
}
