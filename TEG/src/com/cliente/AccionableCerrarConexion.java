/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import cliente.control.ControlConexion;
import com.Accionable;
import com.servidor.AccionableEstadoJugadores;
import servidor.ConexionCliente;
import servidor.GestorClientes;
import servidor.ServerManager;

/**
 *
 * @author heril
 */
public class AccionableCerrarConexion implements Accionable {

    private String razon;
    private boolean solicitudProcesada;
    private int idConexion;
    public static String SERVIDOR_INTERRUMPIDO = "El servidor ha sido detenido.";
    public static String DESCONEXION_SERVIDOR = "Has sido desconectado por el servidor.";
    public static String DESCONEXION_MANUAL = "Has cerrado la conexión con el servidor.";

    public AccionableCerrarConexion() {
        this.razon = SERVIDOR_INTERRUMPIDO;
        this.idConexion = -1;
        this.solicitudProcesada = true;
    }
    
    public AccionableCerrarConexion(int idConexion, String razon) {
        this.idConexion = idConexion;
        this.razon = razon;
    }

    @Override
    public void accionar() {
        if (solicitudProcesada) {
            procesarRespuesta();
        } else {
            procesarSolicitud();
        }
    }

    private void procesarSolicitud() {
        GestorClientes gestorClientes = ServerManager.getInstance().getGestorClientes();
        ConexionCliente cc = gestorClientes.quitarCliente(idConexion);
        solicitudProcesada = true;
        cc.enviar(this);
        AccionableEstadoJugadores.notificarActualizacionJugadores();
    }
    
    private void procesarRespuesta() {
        ControlConexion.cerrarConexion(idConexion, razon);
    }
    
    public void setProcesado(boolean procesado) {
        this.solicitudProcesada = procesado;
    }
}
