/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.servidor;

import java.util.Iterator;
import java.util.LinkedList;
import modelo.comunicacion.Accionable;

/**
 *
 * @author heril
 */
public class CoordinadorClientes {
    
    private LinkedList<ConexionCliente> conexionesCliente;
    
    public CoordinadorClientes() {
        conexionesCliente = new LinkedList();
    }
    
    public void agregarCliente(ConexionCliente ac) {
        conexionesCliente.add(ac);
    }
    
    public void enviarMensaje(String msg) {
//        Iterator<ConexionCliente> i = conexionesCliente.iterator();
//        while (i.hasNext()) {
//            ConexionCliente ac = i.next();
//            MostrarMensaje e = new MostrarMensaje(msg);
//            ac.enviar(e);
//        }
    }
    
    public void enviarNumero() {
//        Iterator<ConexionCliente> i = conexionesCliente.iterator();
//        while (i.hasNext()) {
//            ConexionCliente ac = i.next();
//            MostrarNumero e = new MostrarNumero();
//            ac.enviar(e);
//        }
    }
    
    public void cambioSeleccion() {
//        Iterator<ConexionCliente> i = conexionesCliente.iterator();
//        while (i.hasNext()) {
//            ConexionCliente ac = i.next();
//            CambiarSeleccion e = new CambiarSeleccion();
//            ac.enviar(e);
//        }
    }
    
    public void desconectarTodos() {
//        Iterator<ConexionCliente> i = conexionesCliente.iterator();
//        while (i.hasNext()) {
//            ConexionCliente ac = i.next();
//            Salir e = new Salir();
//            ac.enviar(e);
//        }
    }
    
    public int disponible() {
        int res = -1;
        Iterator<ConexionCliente> i = conexionesCliente.iterator();
        while (i.hasNext()) {
            ConexionCliente cc = i.next();
            res = cc.disponible();
            if (res != -1) break;
        }
        return res;
    }
    
    public Accionable recibir() {
        Accionable res = null;
//        int disp = -1;
//        Iterator<ConexionCliente> i = conexionesCliente.iterator();
//        while (i.hasNext()) {
//            ConexionCliente ac = i.next();
//            disp = ac.disponible();
//            if (disp != 0) {
//                res = ac.recibir();
//                break;
//            }
//        }
        return res;
    }
    
}
