/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import com.servidor.SolicitarAlias;

/**
 *
 * @author heril
 */
public class ControlAlias {
    
    private String alias;
    private boolean aceptadoPorServidor;
    
    public ControlAlias() {
        this.aceptadoPorServidor = false;
        this.alias = null;
    }

    public String getAlias() {
        return alias;
    }

    public boolean isAceptadoPorServidor() {
        return aceptadoPorServidor;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setAceptadoPorServidor(boolean aceptadoPorServidor) {
        this.aceptadoPorServidor = aceptadoPorServidor;
    }
    
    public void ejecutarControlAlias() {
        //si esta aceptado por el servidor, no hay que hacer nada
        if (aceptadoPorServidor) {
            return;
        }
        //si el alias es nulo o esta vacio, se debe ingresar uno
        if (alias == null ||alias.isEmpty()) {
            mostrarVentanaEleccionAlias();
            return;
        }
        //se realiza la peticion del alias cargado.
        int idConexion = ClienteManager.getInstance().getIdentificadorConexion();
        SolicitarAlias solicitarAlias = new SolicitarAlias(idConexion, alias);
        ClienteManager.getInstance().registrarSalida(solicitarAlias);
    }
    
    public void mostrarVentanaEleccionAlias() {
        EleccionAlias frm = new EleccionAlias(ClienteManager.getInstance().getSalaEspera(), true);
        frm.setVisible(true);
    }
    
    public boolean aliasValido() {
        return alias != null && !alias.isEmpty();
    }
}
