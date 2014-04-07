/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import cliente.ClienteManager;
import cliente.control.ControlRefuerzo;
import com.cliente.AccionableChat;
import java.util.List;
import java.util.Set;
import juego.estructura.Continente;
import juego.estructura.GestorJugadores;
import juego.estructura.GestorObjetivosSecretos;
import juego.estructura.Jugador;
import juego.estructura.ObjetivoSecreto;
import juego.estructura.Pais;
import juego.mecanicas.ataque.ControlAtaque;
import juego.mecanicas.turno.GestorTurno;
import juego.mecanicas.turno.SecuenciaTurnos;
import servidor.ServerManager;

/**
 *
 * @author Emanuel
 */
public class FachadaInterface {

    public static List<ObjetivoSecreto> obtenerObjetivos() {
        return GestorObjetivosSecretos.getListaObjetivos();
    }
    public static void enviarChat(String chat) {
        ServerManager.getInstance().registrarSalida(new AccionableChat(getJugadorLocal().getNroJugador(),getJugadorLocal().getNombre(), chat));
    }

    public static boolean atacarPermitido() {
        return GestorTurno.accionPermitida(GestorTurno.ACCION_ATACAR);
    }
    public static boolean finTurnoPermitido() {
        return GestorTurno.accionPermitida(GestorTurno.ACCION_FINALIZAR_TURNO);
    }
    public static boolean ataquePermitido(Pais atacante, Pais defensa){
        return new ControlAtaque(atacante, defensa).ataqueValido();
    }
    public static boolean ataqueConMisilesPermitido(Pais atacante, Pais defensa){
        return new ControlAtaque(atacante, defensa).ataqueConMisilValido();
    }

    public static void atacar(Pais atacante, Pais defensa) {
        GestorTurno.atacar(atacante, defensa);
    }
    public static void atacarConMisil(Pais atacante, Pais defensa) {
        GestorTurno.lanzarMisil(atacante, defensa);
    }

    public static void reagrupar(Pais desde, Pais hasta, int cantidad) {
        GestorTurno.reagruparEjercitos(desde, hasta, cantidad, 0);
    }

    public static boolean canjearTarjetaPermitido() {
        return GestorTurno.accionPermitida(GestorTurno.ACCION_CANJEAR_TARJETA);
    }

    public static boolean solicitarTarjetaPermitido() {
        return GestorTurno.accionPermitida(GestorTurno.ACCION_SOLICITAR_TARJETA);
    }
    public static void solicitarTarjeta() {
        GestorTurno.solicitarTarjeta(getJugadorLocal());
    }

    public static boolean incorporarEjercitosPermitido() {
        return GestorTurno.accionPermitida(GestorTurno.ACCION_INCORPORAR_EJERCITOS);
    }

    public static boolean reagruparPermitido() {
        return GestorTurno.accionPermitida(GestorTurno.ACCION_REAGRUPAR);
    }

    public static boolean esMiTurno(Jugador jugador) {
        return GestorTurno.etapaActual != GestorTurno.FUERA_TURNO;
    }

    public static Jugador getJugadorLocal() {
        return ClienteManager.getInstance().getJugador();
    }
    
    public static Jugador getJugadorTurno() {
        return SecuenciaTurnos.getInstancia().getActual();
    }

    public static Pais obtenerPaisPorNombre(String nombre) {
        return new Pais(1, nombre, new Continente(1, "America del Norte"), true);
    }

    public static boolean esMiPais(Pais pais) {
        return ClienteManager.getInstance().getJugador().equals(pais.getJugador());
    }

    public static void finalizarTurno() {
        GestorTurno.finTurno();
    }

    public static void agregarRefuerzo(Pais pais) {
        GestorTurno.getRefuerzoActual().agregarEjercito(pais);
    }

    public static void agregarMisil(Pais pais) {
        GestorTurno.getRefuerzoActual().agregarMisil(pais);
    }

    public static void puedereforzar(Pais pais) {
        GestorTurno.getRefuerzoActual().puedeReforzar(pais);
    }

    public static void aplicarRefuerzo() {
        GestorTurno.getRefuerzoActual().aplicarRefuerzo();
    }

    public static void reiniciarRefuerzo() {
        GestorTurno.getRefuerzoActual().reiniciar();
    }

    public static void quitarRefuerzo(Pais pais) {
        //No se va a implementar
    }

    public static Set<Jugador> getJugadores() {
        return GestorJugadores.getJugadores();
    }

    public static ControlRefuerzo getRefuerzoActual() {
        return GestorTurno.getRefuerzoActual();
    }    
}
