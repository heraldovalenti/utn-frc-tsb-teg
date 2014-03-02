/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conf;

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
    
    public long tiempoEsperaLectura() {
        return tiempoEsperaLectura;
    }
    
    public String direccionServidor() {
        return direccionServidor;
    }
    
    public String direccionCliente() {
        return direccionCliente;
    }
    
    public int puertoServidor() {
        return puertoServidor;
    }
    
    public int puertoCliente() {
        return puertoCliente;
    }
    
    public long tiempoEsperaLecturaCliente() {
        return tiempoEsperaLecturaCliente;
    }
    
    public long tiempoEspera() {
        return tiempoEsperaLecturaCliente;
    }
    
    private static final long tiempoEsperaLectura = 500;
    private static final long tiempoEsperaLecturaCliente = 100;
    private static final String direccionServidor = "localhost";
    private static final String direccionCliente = "localhost";
    private static final int puertoServidor = 9988;
    private static final int puertoCliente = 9988;
    
}
