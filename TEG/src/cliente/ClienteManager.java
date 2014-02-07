/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import com.Accionable;
import logger.Logger;
import servidor.ColaAcciones;

/**
 *
 * @author heril
 */
public class ClienteManager {
    
    private static ClienteManager instance = null;
    
    public static ClienteManager getInstance() {
        if (instance == null) {
            instance = new ClienteManager();
        }
        return instance;
    }
    
    private ClienteManager() {
        colaAcciones = new ColaAcciones();
        logger = new Logger();
        conexionServidor = new ConexionServidor();
        despachadorAcciones = new DespachadorAcciones(colaAcciones,conexionServidor);
    }
    
    private ConexionServidor conexionServidor;
    private ColaAcciones colaAcciones;
    private DespachadorAcciones despachadorAcciones;
    private Logger logger;
    
    public Logger getLogger() {
        return this.logger;
    }
    
}
