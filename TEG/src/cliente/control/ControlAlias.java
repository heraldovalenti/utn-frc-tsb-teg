/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.control;

import cliente.ClienteManager;
import com.cliente.AccionableSolicitudAlias;
import logger.LogItem;

/**
 *
 * @author heril
 */
public class ControlAlias {

    private static String alias = null;

    public ControlAlias() {
    }
    
    public void invalidar() {
        alias = null;
    }

    public String getAlias() {
        if (alias == null) {
            return "No definido";
        } else {
            return alias;
        }
    }

    /**
     * Comprueba que una cadena no sea nula ni este vacia.
     *
     * @param cadena la cadena a verificarse.
     * @return false si la cadena es nula o esta vacia, true en otro caso.
     */
    public boolean aliasValido(String cadena) {
        cadena = eliminarEspaciosIniciales(cadena);
        return (cadena != null && !cadena.isEmpty());
    }

    private String eliminarEspaciosIniciales(String cadena) {
        //si hay espacios en blanco iniciales:
        if (cadena != null && cadena.length() > 0 && cadena.startsWith(" ")) {
            char[] ca_de_na = cadena.toCharArray();
            int i = 0;
            while (i < ca_de_na.length) {
                char cAux = ca_de_na[i];
                if (cAux == ' ') {
                    i++;
                } else {
                    break;
                }
            }
            StringBuilder sb = new StringBuilder("");
            for (int j = i; j < ca_de_na.length; j++) {
                sb.append(ca_de_na[j]);
            }
            cadena = sb.toString();
        }
        return cadena;
    }

    /**
     * Solicita al servidor el alias ingresado por el usuario.
     *
     * @param aliasSolicitado el alias ingresado por el usuario.
     */
    public void solicitarAlias(String aliasSolicitado) {
        aliasSolicitado = eliminarEspaciosIniciales(aliasSolicitado);
        if (aliasSolicitado != null && aliasSolicitado.equals(alias)) {
            return;
        }
        int idConexion = ClienteManager.getInstance().getConexionServidor().getConexionId();
        AccionableSolicitudAlias solicitarAlias = new AccionableSolicitudAlias(idConexion, aliasSolicitado);
        ClienteManager.getInstance().registrarSalida(solicitarAlias);
        ClienteManager.getInstance().getLogger().addLogItem(new LogItem("Solicitando alias..."));
    }
    
    public void asignarAlias(int idJugador, String aliasAsignado, String aliasSolicitado) {
        int idCliente = ClienteManager.getInstance().getIdCliente();
        if (idJugador == idCliente) {
            alias = aliasAsignado;
            if (aliasSolicitado != null && !aliasAsignado.equals(aliasSolicitado)) {
                ClienteManager.getInstance().getSalaEspera().informarSolicitudAliasRechazada();
            }
            ClienteManager.getInstance().getSalaEspera().actualizarAlias();
            ClienteManager.getInstance().getLogger().addLogItem(new LogItem("Alias aceptado: " + alias));
        } else {
            ClienteManager.getInstance().getLogger().addLogItem(new LogItem("Alias rechazado (otra conexion)."));
        }
    }
}
