/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import com.Accionable;
import java.util.LinkedList;

/**
 *
 * @author heril
 */
public class ColaAcciones {
    
    private LinkedList<Accionable> colaEntrada;
    private LinkedList<Accionable> colaSalida;
    
    public ColaAcciones() {
        colaEntrada = new LinkedList<>();
        colaSalida = new LinkedList<>();
    }
    
    public synchronized boolean hayEntradas() {
        return !colaEntrada.isEmpty();
    }
    
    public synchronized boolean haySalidas() {
        return !colaSalida.isEmpty();
    }
    
    public synchronized void pushEntrada(Accionable entrada) {
        colaEntrada.addLast(entrada);
    }
    
    public synchronized void pushSalida(Accionable salida) {
        colaSalida.addLast(salida);
    }
    
    public synchronized Accionable pullEntrada() {
        return colaEntrada.removeFirst();
    }
    
    public synchronized Accionable pullSalida() {
        return colaSalida.removeFirst();
    }
    
}
