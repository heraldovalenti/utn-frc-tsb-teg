/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.control;

import com.servidor.AccionableRondaInicial;
import juego.estructura.GestorJugadores;
import juego.estructura.Jugador;
import juego.mecanicas.turno.SecuenciaTurnos;
import servidor.ServerManager;

/**
 *
 * @author heril
 */
public class ControlRondaInicial {

    private static ControlRondaInicial instance = null;

    public static ControlRondaInicial getInstance() {
        if (instance == null) {
            instance = new ControlRondaInicial();
        }
        return instance;
    }

    private ControlRondaInicial() {
        cantidadJugadores = GestorJugadores.getCantidadJugadores();
        if (cantidadJugadores == 2) {
            cantidadEjercitosPrimeraRonda = 12;
            cantidadEjercitosSegundaRonda = 6;
        } else {
            cantidadEjercitosPrimeraRonda = 8;
            cantidadEjercitosSegundaRonda = 4;
        }
        primeraRonda = true;
        contadorTurnos = 1;
        jugadorDeTurno = SecuenciaTurnos.getInstancia().getActual();
    }
    private Jugador jugadorDeTurno;
    private int cantidadJugadores;
    private int contadorTurnos;
    private int cantidadEjercitosPrimeraRonda;
    private int cantidadEjercitosSegundaRonda;
    private boolean primeraRonda;

    /**
     * Envia la orden de comenzar la ronda inicial de incorporacion de
     * ejercitos.
     */
    public void comenzar() {
        AccionableRondaInicial accionable = new AccionableRondaInicial(jugadorDeTurno.getNroJugador(), cantidadEjercitosPrimeraRonda);
        ServerManager.getInstance().registrarSalida(accionable);
    }

    /**
     * Metodo para indicar que un jugador ha finalizado de incorporar ejercitos.
     */
    public void finTurnoJugador() {
        jugadorDeTurno = SecuenciaTurnos.getInstancia().getJugadorSiguiente(jugadorDeTurno);
        contadorTurnos++;
        ejecutarControl();
    }

    /**
     * Envia la orden de continuar con la ronda inicial de incorporacion de
     * ejercitos. El metodo debe llamarse continuamente cada vez que un jugador
     * ha finalizado de incorporar ejercitos y cuando se terminen las dos rondas
     * de incorporacion se dara la orden de iniciar los ataques.
     */
    private void ejecutarControl() {
        if (primeraRonda && contadorTurnos > cantidadJugadores) {
            primeraRonda = false;
            contadorTurnos = 1;
        }
        if (!finRondaInicial()) {
            AccionableRondaInicial accionable = (primeraRonda)
                    ? new AccionableRondaInicial(jugadorDeTurno.getNroJugador(), cantidadEjercitosPrimeraRonda)
                    : new AccionableRondaInicial(jugadorDeTurno.getNroJugador(), cantidadEjercitosSegundaRonda);
            ServerManager.getInstance().registrarSalida(accionable);
        } else {
            //llamar al controlador de inicio de ataques...
        }
    }

    private boolean finRondaInicial() {
        if (!primeraRonda && contadorTurnos > cantidadJugadores) {
            return true;
        }
        return false;
    }
}
