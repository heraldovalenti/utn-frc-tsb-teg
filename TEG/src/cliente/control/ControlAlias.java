/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.control;

import cliente.ClienteManager;
import cliente.SalaEspera;
import com.servidor.SolicitarAlias;
import javax.swing.JOptionPane;
import logger.LogItem;

/**
 *
 * @author heril
 */
public class ControlAlias {

    private String alias;
    private boolean solicitudRealizada;
    private boolean aceptadoPorServidor;

    public ControlAlias() {
        aceptadoPorServidor = false;
        solicitudRealizada = false;
        alias = null;
        cargarAlias();
    }

    /**
     * Carga un alias almacenado localmente.
     */
    private void cargarAlias() {
        //se deberia buscar en algun archivo local si hay un alias ya cargado...
    }

    public String getAlias() {
        if (!aliasValido()) {
            return "NO_DISPONIBLE";
        }
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

    /**
     * Verifica que el alias sea valido.
     *
     * @return true en caso de que la cadena sea valida, falso en otro caso.
     */
    public boolean aliasValido() {
        return verificarValidez(alias);
    }

    /**
     * Comprueba que una cadena no sea nula ni este vacia.
     *
     * @param cadena la cadena a verificarse.
     * @return false si la cadena es nula o esta vacia, true en otro caso.
     */
    private boolean verificarValidez(String cadena) {
        return cadena != null && !cadena.isEmpty();
    }

    /**
     * Informa al servidor del alias ingresado por el usuario.
     */
    public void informarAliasAServidor() {
        int idConexion = ClienteManager.getInstance().getConexionServidor().getConexionId();
        SolicitarAlias solicitarAlias = new SolicitarAlias(idConexion, alias);
        ClienteManager.getInstance().registrarSalida(solicitarAlias);
        ClienteManager.getInstance().getLogger().addLogItem(new LogItem("Alias informado a servidor. Esperando respuesta."));
        solicitudRealizada = true;
    }
    
    /**
     * Recibe la respuesta del servidor de la solicitud del alias realizada.
     * Si el alias fue aceptado por el servidor, procede a solicitar un color.
     * Si el alias no fue aceptado, procede a solicitar un alias nuevo al
     * usuario y a solicitar el alias al servidor nuevamente.
     * @param aceptado true si el alias fue aceptado por el servidor, false en 
     * otro caso.
     */
    public void respuestaServidor(boolean aceptado) {
        aceptadoPorServidor = aceptado;
        if (aceptadoPorServidor) {
            ClienteManager.getInstance().getLogger().addLogItem(new LogItem("Alias aceptado por servidor."));
            new ControlColor().solicitarColor();
        } else {
            ClienteManager.getInstance().getLogger().addLogItem(new LogItem("Alias rechazadp por servidor. Solicitando un nuevo alias."));
            solicitarAlias();
            informarAliasAServidor();
        }
    }
    
    public void ejecutarControlAlias() {
        //si esta aceptado por el servidor, no hay que hacer nada
        if (aceptadoPorServidor) {
            return;
        }
        //si el alias es nulo o esta vacio, se debe ingresar uno
        if (alias == null || alias.isEmpty()) {
            mostrarVentanaEleccionAlias();
            return;
        }
        //se realiza la peticion del alias cargado.
        int idConexion = ClienteManager.getInstance().getConexionServidor().getConexionId();
        SolicitarAlias solicitarAlias = new SolicitarAlias(idConexion, alias);
        ClienteManager.getInstance().registrarSalida(solicitarAlias);
    }

    public void mostrarVentanaEleccionAlias() {
        //EleccionAlias frm = new EleccionAlias(ClienteManager.getInstance().getSalaEspera(), true);
        //frm.setVisible(true);
    }

    /**
     * Solicita el ingreso de un alias al jugador. El metodo se ejecuta en dos
     * casos: una, cuando no hay un alias cargado al inicio de la aplicacion; la
     * otra, cuando el servidor ha rechazado el alias en el intento de conexion,
     * por lo que debe ingresarse uno distinto.
     */
    public void solicitarAlias() {
        if (solicitudRealizada) {
            //rama de control si ya se intento realizar una conexion al servidor
            //y el alias fue rechazado:
            String oldAlias = alias;
            do {
                SalaEspera salaEspera = ClienteManager.getInstance().getSalaEspera();
                JOptionPane.showMessageDialog(salaEspera, "El alias <" + oldAlias + "> ha sido rechazado por el servidor."
                        + "Para continuar deberá elegir un nuevo alias distinto.", 
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                String inputAlias = JOptionPane.showInputDialog(salaEspera, "Ingrese un nuevo alias");
                if (verificarValidez(inputAlias) && !inputAlias.equals(oldAlias)) {
                    alias = inputAlias;
                }
                if (!aliasValido()) {
                    JOptionPane.showMessageDialog(salaEspera, "Debe ingresar un alias para poder continuar", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } while (!aliasValido());
        } else {
            //rama de control si todavia no se ha realizado un intento de
            //conexion al servidor.
            do {
                SalaEspera salaEspera = ClienteManager.getInstance().getSalaEspera();
                String inputAlias = JOptionPane.showInputDialog(salaEspera, "Ingrese un alias");
                if (verificarValidez(inputAlias)) {
                    alias = inputAlias;
                }
                if (!aliasValido()) {
                    JOptionPane.showMessageDialog(salaEspera, "Debe ingresar un alias para poder continuar", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } while (!aliasValido());
        }
        ClienteManager.getInstance().getSalaEspera().cargarAlias();
    }
}
