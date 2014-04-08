/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import cliente.ClienteManager;
import cliente.control.ControlRefuerzo;
import com.cliente.AccionableChat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import juego.estructura.Canjeable;
import juego.estructura.Continente;
import juego.estructura.GestorJugadores;
import juego.estructura.GestorObjetivosSecretos;
import juego.estructura.GestorTarjetas;
import juego.estructura.Jugador;
import juego.estructura.ObjetivoSecreto;
import juego.estructura.Pais;
import juego.estructura.TarjetaContinente;
import juego.estructura.TarjetaPais;
import juego.mecanicas.ataque.ControlAtaque;
import juego.mecanicas.turno.GestorTurno;
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
        ServerManager.getInstance().registrarSalida(new AccionableChat(getJugadorLocal().getNroJugador(), getJugadorLocal().getNombre(), chat));
    }

    public static boolean atacarPermitido() {
        return GestorTurno.getInstance().accionPermitida(GestorTurno.ACCION_ATACAR);
    }

    public static boolean finTurnoPermitido() {
        return GestorTurno.getInstance().accionPermitida(GestorTurno.ACCION_FINALIZAR_TURNO);
    }

    public static boolean ataquePermitido(Pais atacante, Pais defensa) {
        return new ControlAtaque(atacante, defensa).ataqueValido();
    }

    public static boolean ataqueConMisilesPermitido(Pais atacante, Pais defensa) {
        return new ControlAtaque(atacante, defensa).ataqueConMisilValido();
    }

    public static void atacar(Pais atacante, Pais defensa) {
        GestorTurno.getInstance().atacar(atacante, defensa);
    }

    public static void atacarConMisil(Pais atacante, Pais defensa) {
        GestorTurno.getInstance().lanzarMisil(atacante, defensa);
    }

    public static void reagrupar(Pais desde, Pais hasta, int cantidadTropas, int cantidadMisiles) {
        GestorTurno.getInstance().reagruparEjercitos(desde, hasta, cantidadTropas, cantidadMisiles);
    }

    public static boolean canjearTarjetaPermitido() {
        return GestorTurno.getInstance().accionPermitida(GestorTurno.ACCION_CANJEAR_TARJETA);
    }

    public static boolean solicitarTarjetaPermitido() {
        return GestorTurno.getInstance().accionPermitida(GestorTurno.ACCION_SOLICITAR_TARJETA);
    }

    public static void solicitarTarjeta() {
        GestorTurno.getInstance().solicitarTarjeta(getJugadorLocal());
    }

    public static boolean incorporarEjercitosPermitido() {
        return GestorTurno.getInstance().accionPermitida(GestorTurno.ACCION_INCORPORAR_EJERCITOS);
    }

    public static boolean reagruparPermitido() {
        return GestorTurno.getInstance().accionPermitida(GestorTurno.ACCION_REAGRUPAR);
    }

    public static boolean esMiTurno(Jugador jugador) {
        return GestorTurno.getInstance().getEtapaActual() != GestorTurno.FUERA_TURNO;
    }

    public static Jugador getJugadorLocal() {
        return ClienteManager.getInstance().getJugador();
    }

    public static Jugador getJugadorTurno() {
        return GestorTurno.getInstance().getJugadorActual();
    }

    public static Pais obtenerPaisPorNombre(String nombre) {
        return new Pais(1, nombre, new Continente(1, "America del Norte"), true);
    }

    public static boolean esMiPais(Pais pais) {
        return ClienteManager.getInstance().getJugador().equals(pais.getJugador());
    }

    public static void finalizarTurno() {
        GestorTurno.getInstance().finTurno();
    }

    public static void agregarRefuerzo(Pais pais) {
        GestorTurno.getInstance().getRefuerzoActual().agregarEjercito(pais);
    }

    public static void agregarMisil(Pais pais) {
        GestorTurno.getInstance().getRefuerzoActual().agregarMisil(pais);
    }

    public static void puedereforzar(Pais pais) {
        GestorTurno.getInstance().getRefuerzoActual().puedeReforzar(pais);
    }

    public static void aplicarRefuerzo() {
        GestorTurno.getInstance().getRefuerzoActual().aplicarRefuerzo();
    }

    public static void reiniciarRefuerzo() {
        GestorTurno.getInstance().getRefuerzoActual().reiniciar();
    }

    public static void quitarRefuerzo(Pais pais) {
        //No se va a implementar
    }

    public static Set<Jugador> getJugadores() {
        return GestorJugadores.getJugadores();
    }

    public static ControlRefuerzo getRefuerzoActual() {
        return GestorTurno.getInstance().getRefuerzoActual();
    }

    public static void comenzarReagrupacion() {
        GestorTurno.getInstance().comenzarReagrupacion();
    }

    public static void pasarRefuerzoPaisGanado(Pais paisDesde, Pais paisHasta, int cantidad) {
        //Daniel aca implementas vos
    }

    public static void pedirTarjeta() {

    }

    public static void canjearTarjeta(Jugador jugador, List<Canjeable> tarjetas) {
        GestorTurno.getInstance().canjearTarjetas(jugador, tarjetas);
    }

    public static List<Canjeable> obtenerTarjetas() {
        ClienteManager.getInstance().getJugador().getListaTarjetaContinentes();
        List<Canjeable> tarjetas = new ArrayList<>();
        for (TarjetaPais tar : ClienteManager.getInstance().getJugador().getListaTarjetasPais()) {
            tarjetas.add((Canjeable) tar);
        }
        for (TarjetaContinente tar : ClienteManager.getInstance().getJugador().getListaTarjetaContinentes()) {
            tarjetas.add((Canjeable) tar);
        }
        return tarjetas;
    }

    public static boolean canjeValido(Jugador jugador, List<Canjeable> tarjetas) {
        return GestorTarjetas.canjeValido(jugador, tarjetas);
    }

}
