/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servidor;

import cliente.ClienteManager;
import cliente.control.ControlColor;
import com.Accionable;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import logger.LogItem;
import servidor.ServerManager;
import servidor.control.ControlColores;
import servidor.control.ControlEstadoJugadores;
import servidor.control.ControladorAlias;

/**
 *
 * @author heril
 */
public class AccionableEstadoJugadores implements Accionable {

    private Color[] coloresAsignados;
    private String[] aliasesAsignados;
    private Boolean[] estadoJugadores;
    private Boolean[] jugadoresIA;
    private int cantidad;
    private boolean cliente;

    public AccionableEstadoJugadores(boolean cliente) {
        this.cliente = cliente;
        Object[] idsJugadores = ServerManager.getInstance().getGestorClientes().getIdConexionesEstablecidas().toArray();
        cantidad = idsJugadores.length;
        coloresAsignados = new Color[cantidad];
        aliasesAsignados = new String[cantidad];
        estadoJugadores = new Boolean[cantidad];
        jugadoresIA = new Boolean[cantidad];

        for (int i = 0; i < cantidad; i++) {
            Integer id = (Integer) idsJugadores[i];
            coloresAsignados[i] = ControlColores.getColorJugador(id);
            aliasesAsignados[i] = ControladorAlias.getAliasJugador(id);
            estadoJugadores[i] = ControlEstadoJugadores.getEstadoJugador(id);
            jugadoresIA[i] = false;
        }
    }

    @Override
    public void accionar() {
        Object[] columnNames = {"Alias", "Tipo Jugador", "Color", "Listo"};
        Object[][] data = new Object[cantidad][4];
        try {
            for (int i = 0; i < cantidad; i++) {
                data[i][0] = aliasesAsignados[i];
                data[i][1] = (jugadoresIA[i]) ? "Sistema" : "Persona";
                data[i][2] = new ControlColor().getNombreColor(coloresAsignados[i]);
                data[i][3] = (estadoJugadores[i]) ? "Si" : "No";
            }
        } catch (Exception ex) {
            data = new Object[0][4];
        }
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        if (cliente) {
            ClienteManager.getInstance().getSalaEspera().actualizarEstadoJugadores(model);
        } else {
            ServerManager.getInstance().getAdministracionPartida().actualizarEstadoJugadores(model);
        }
    }

    public static void notificarActualizacionJugadores() {
        ServerManager.getInstance().registrarSalida(new AccionableEstadoJugadores(true));
        ServerManager.getInstance().registrarEntrada(new AccionableEstadoJugadores(false));
    }
}