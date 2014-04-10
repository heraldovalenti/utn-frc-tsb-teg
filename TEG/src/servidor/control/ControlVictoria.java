/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.control;

import com.servidor.AccionableVictoria;
import juego.estructura.GestorJugadores;
import juego.estructura.Jugador;
import logger.LogItem;
import servidor.ServerManager;

/**
 *
 * @author Daniel
 */
public class ControlVictoria {

    private static Jugador ganador = null;
    private static String mensaje = null;

    public static boolean comprobarVictoria() {
        for (Jugador jugador : GestorJugadores.getJugadores()) {
            if (comprobarObjetivoGeneral(jugador) || comprobarObjetivoSecreto(jugador)) {
                informarVictoria();
                return true;
            }
        }
        return false;
    }

    private static boolean comprobarObjetivoGeneral(Jugador jugador) {
        if (jugador.getCantidadPaises() >= 45) {
            ganador = jugador;
            mensaje = "El jugador " + jugador.getNombre() + " ha ganado al cumplir el objetivo general de conquistar 45 países";
            return true;
        }
        return false;
    }

    private static boolean comprobarObjetivoSecreto(Jugador jugador) {
        if (jugador.getObjetivoSecreto().comprobarVictoria(jugador)) {
            ganador = jugador;
            mensaje = "El jugador " + jugador.getNombre() + " ha ganado al cumplir su objetivo secreto:\n" + jugador.getObjetivoSecreto().getDescripcion();
            return true;
        }
        return false;
    }

    private static void informarVictoria() {
        AccionableVictoria victoria = new AccionableVictoria(ganador, mensaje);
        ServerManager.getInstance().registrarSalida(victoria);
        ServerManager.getInstance().getLogger().addLogItem(
                new LogItem("Victoria del jugador: " + ganador.getNombre()));
    }
    
    public static boolean juegoTerminado() {
        return ganador != null;
    }
}
