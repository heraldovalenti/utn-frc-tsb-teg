/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.control;

import cliente.ClienteManager;
import cliente.SalaEspera;
import com.servidor.SolicitarAlias;
import javax.swing.JOptionPane;

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
        int idConexion = ClienteManager.getInstance().getIdentificadorConexion();
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
