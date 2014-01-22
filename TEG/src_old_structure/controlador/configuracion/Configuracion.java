/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.configuracion;

/**
 *
 * @author heril
 */
public class Configuracion {
    
    private static Configuracion instancia = null;
    public static Configuracion getInstancia() {
        if (instancia == null) instancia = new Configuracion();
        return instancia;
    }
    private Configuracion() {
    }
    
    public static final long tiempoEsperaLectura = 500;
    public static final String direccionServidor = "localhost";
    public static final String direccionCliente = "localhost";
    public static final int puertoServidor = 9988;
    public static final int puertoCliente = 9988;
    
}
