/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import cliente.ClienteManager;
import cliente.control.ControlRondaInicialCliente;
import juego.estructura.Canjeable;
import juego.estructura.Jugador;
import juego.estructura.Pais;
import logger.LogItem;

/**
 *
 * @author heril
 */
public class FachadaInterfacePrincipal {

    public static void mostrarChat(String chat) {
        ClienteManager.getInstance().getInterfacePrincipal().cargarChat(chat);
    }

    public static void mostrarTarjeta(Canjeable tarjeta) {
        ClienteManager.getInstance().getInterfacePrincipal().mostrarTarjeta(tarjeta);
    }

    public static void actualizarMapa() {
        ClienteManager.getInstance().getInterfacePrincipal().actualizarFichas();
    }

    public static void mostrarResultadoAtaque(String nombreAtacante, String nombreDefensor, int[] dadosAtacante, int dadosDefensor[]) {
        ClienteManager.getInstance().getInterfacePrincipal().cargarDados(nombreAtacante, nombreDefensor, dadosAtacante, dadosDefensor);
    }

    public static void mostrarMensaje(String mensaje) {
        ClienteManager.getInstance().getInterfacePrincipal().mostrarMensajeGlobal(mensaje);
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

    public static void informarInicioTurno(Jugador jugadorTurno) {
        ClienteManager.getInstance().getInterfacePrincipal().inicioTurno(jugadorTurno);
    }

    public static void terminarRefuerzo() {
        ClienteManager.getInstance().getInterfacePrincipal().terminarRefuerzo();
    }

    public static void refuerzoPaisGanado(Pais paisDesde, Pais paisHasta, int cantidad) {
        ClienteManager.getInstance().getInterfacePrincipal().pasarRefuerzosPaisGanado(paisDesde, paisHasta, cantidad);
    }

    public static void actualizarEstadoBotones() {
        ClienteManager.getInstance().getInterfacePrincipal().habilitarBotones();
    }

    public static void mostrarTarjetaSituacion() {
        ClienteManager.getInstance().getInterfacePrincipal().mostrarTarjetaSituacion();
    }

    public static void victoria(String mensaje) {
        ClienteManager.getInstance().getInterfacePrincipal().victoria(mensaje);
    }

    public static void derrota(String mensaje) {
        ClienteManager.getInstance().getInterfacePrincipal().derrota(mensaje);
    }
}
