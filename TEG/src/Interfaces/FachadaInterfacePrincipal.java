/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import cliente.ClienteManager;
import cliente.control.ControlRondaInicialCliente;
import juego.estructura.Jugador;
import juego.estructura.TarjetaPais;
import logger.LogItem;

/**
 *
 * @author heril
 */
public class FachadaInterfacePrincipal {

    public static void mostrarChat(String chat) {
        ClienteManager.getInstance().getInterfacePrincipal().cargarChat(chat);
    }

    public static void mostrarTarjeta(TarjetaPais tarjeta) {
        ClienteManager.getInstance().getInterfacePrincipal().mostrarTarjeta(tarjeta);
    }

    public static void actualizarMapa() {
        ClienteManager.getInstance().getInterfacePrincipal().actualizarFichas();
    }

    public static void mostrarResultadoAtaque(String nombreAtacante, String nombreDefensor, int[] dadosAtacante, int dadosDefensor[]) {
        ClienteManager.getInstance().getInterfacePrincipal().cargarDados(nombreAtacante, nombreDefensor, dadosAtacante, dadosDefensor);
    }

    public static void mostrarMensaje(String mensaje) {
        //Ema esto es para que pongas un label o algo asi que muestre el mensaje para todos los jugadores
    }

    public static void informarRondaInicial() {
        int cantidadEjercitos_a_Incorporarse = ControlRondaInicialCliente.getInstance().getCantidadEjercitos();
        ClienteManager.getInstance().getLogger().addLogItem(
                new LogItem("Es tu turno para incorporar ejércitos ("
                        + cantidadEjercitos_a_Incorporarse + ")."));
        ClienteManager.getInstance().getInterfacePrincipal().inciarRefuerzo();
    }

    public static void informarRondaInicial(Jugador jugador) {
        ClienteManager.getInstance().getLogger().addLogItem(
                new LogItem("Es el turno de <"
                        + jugador.getNombre()
                        + "> para incorporar ejércitos."));
    }

    public static void informarFinRondaInicial() {
        ClienteManager.getInstance().getLogger().addLogItem(
                new LogItem("Fin de turno de incorporación."));
    }

    public static void iniciarAgregadoRefuerzo() {
        ClienteManager.getInstance().getInterfacePrincipal().inciarRefuerzo();
    }

    public static void informarInicioTurno() {
        ClienteManager.getInstance().getInterfacePrincipal().habilitarBotones();
    }
}
