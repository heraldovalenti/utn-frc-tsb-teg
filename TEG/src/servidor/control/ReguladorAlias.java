/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.control;

import com.cliente.RespuestaAlias;
import logger.LogItem;
import servidor.DespachadorAcciones;
import servidor.GestorClientes;
import servidor.ServerManager;

/**
 *
 * @author heril
 */
public class ReguladorAlias {
    
    private int idCliente;
    private String alias;

    public ReguladorAlias(int idCliente, String alias) {
        this.idCliente = idCliente;
        this.alias = alias;
    }
    
    public void procesarSolicitudAlias() {
        GestorClientes gestorClientes = ServerManager.getInstance().getGestorClientes();
        DespachadorAcciones despachadorAcciones = ServerManager.getInstance().getDespachadorAcciones();
        boolean aliasDisponible = gestorClientes.aliasDisponible(alias);
        RespuestaAlias respuestaAlias = new RespuestaAlias(idCliente, aliasDisponible);
        if (aliasDisponible) {
            gestorClientes.establecerAlias(idCliente, alias);
            ServerManager.getInstance().getLogger().addLogItem(new LogItem("Alias <" + alias + "> aceptado. Informando al cliente."));
        } else {
            ServerManager.getInstance().getLogger().addLogItem(new LogItem("Alias <" + alias + "> duplicado. Solicitando nuevo alias al cliente."));
        }
        despachadorAcciones.ingresarSalida(respuestaAlias);
    }
    
}
