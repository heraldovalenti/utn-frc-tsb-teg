/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.IOException;
import logger.LogItem;
import servidor.ColaAcciones;

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
    
    /**
     * Obtiene un accionable desde la cola de acciones y lo ejecuta.
     */
    private void despacharEntradas() {
        if (colaAcciones.hayEntradas()) {
            colaAcciones.pullEntrada().accionar();
        }
    }
    
    /**
     * Obtiene un acionable desde la cola de acciones y lo envia al servidor.
     */
    private void despacharSalidas() {
        if (colaAcciones.haySalidas()) {
            try {
                conexionServidor.enviar(colaAcciones.pullSalida());
            } catch (IOException ex) {
                ClienteManager.getInstance().getLogger().addLogItem(new LogItem("Error enviando datos al servidor.", ex));
            }
        }
    }
    
}
