/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import conf.Configuracion;

/**
 *
 * @author heril
 */
public class Cliente extends Thread {

    private static Cliente instancia = null;
    public static Cliente getInstancia() {
        if (instancia == null) instancia = new Cliente();
        return instancia;
    }
    
    private Cliente() {
    }
    
    private ConexionServidor conexion;

    public void run() {
        /*conexion = new ConexionServidor();
        while (true) {
            try {
                int disp = conexion.disponible();
                if (disp == 0) {
                    this.sleep(Configuracion.getInstancia().tiempoEsperaLectura());
                } else {
                    conexion.recibir();
                }
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
        */
    }
}
