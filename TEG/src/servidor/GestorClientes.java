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
public class GestorClientes {

    private static GestorClientes instance = null;

    public static GestorClientes getInstance() {
        if (instance == null) {
            instance = new GestorClientes();
        }
        return instance;
    }

    private GestorClientes() {
        conexionesCliente = new LinkedList();
        indiceCliente = -1;
    }
    private LinkedList<ConexionCliente> conexionesCliente;
    private int indiceCliente;

    public synchronized void agregarCliente(ConexionCliente ac) {
        conexionesCliente.add(ac);
    }

    /**
     * Metodo publico para consultar si hay datos disponibles enviados por algun
     * cliente.
     *
     * @return true si hay datos para leer, false en caso contrario.
     */
    public boolean datosDisponibles() {
        indiceCliente = checkDatosDisponibles();
        return indiceCliente != -1;
    }

    /**
     * Metodo publico para obtener los accionables enviados por los clientes.
     *
     * @return Accionable enviando por el cliente.
     */
    public Accionable leerAccionable() {
        if (indiceCliente == -1) {
            return null;
        }
        Accionable res = recibir(indiceCliente);
        indiceCliente = -1;
        return res;
    }
    
    /**
     * Enviar un accionable a todos los clientes conectados.
     * @param accion el accionable a enviarse.
     */
    public void enviarAccionable(Accionable accion) {
        for (ConexionCliente aux : conexionesCliente) {
            aux.enviar(accion);
        }
    }

    /**
     * Chequea si hay datos para recibir de los clientes.
     *
     * @return el indice del cliente que envio datos, -1 si no existen datos.
     */
    private int checkDatosDisponibles() {
        for (int i = 0; i < conexionesCliente.size(); i++) {
            ConexionCliente cc = conexionesCliente.get(i);
            if (cc.disponible() != -1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Devuelve lee un accionable enviado por el cliente.
     *
     * @param i int el indice del cliente
     * @return Accionable la accion enviada por el cliente.
     */
    private Accionable recibir(int i) {
        return conexionesCliente.get(i).recibir();
    }
}
