/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente;

import cliente.ClienteManager;
import cliente.ConexionServidor;
import cliente.SalaEspera;
import cliente.control.ControlAlias;
import cliente.control.ControlColor;
import com.Accionable;
import juego.Juego;
import logger.LogItem;

/**
 * 
 * @author heril
 */
public class InicioConexion implements Accionable {

    private int idConexionCliente;
    private int idPartida;

    public InicioConexion(int idConexionCliente, int idPartida) {
        this.idConexionCliente = idConexionCliente;
        this.idPartida = idPartida;
    }

    /**
     * Asigna a la conexion con el servidor el numero de identificacion asignado
     * y el numero de juego indicado por el servidor.
     */
    @Override
    public void accionar() {
        SalaEspera salaEspera = ClienteManager.getInstance().getSalaEspera();
        ConexionServidor conexionServidor = ClienteManager.getInstance().getConexionServidor();
        Juego juego = ClienteManager.getInstance().getJuego();
        
        conexionServidor.setConexionId(idConexionCliente);
        juego.setIdJuego(idPartida);
        salaEspera.actualizarEstadoConexion();
        ClienteManager.getInstance().getLogger().addLogItem(new LogItem("Conexión establecida con éxito."));
        new ControlAlias().solicitarAlias(null);
        new ControlColor().solicitarColor();
    }
}
