/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.control;

import com.servidor.AccionableInicioJuego;
import com.servidor.AccionableNotificacionInicioJuego;
import com.servidor.AccionableSituacion;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import juego.Juego;
import juego.estructura.GestorJugadores;
import juego.estructura.GestorObjetivosSecretos;
import juego.estructura.GestorPaises;
import juego.estructura.GestorTarjetas;
import juego.estructura.Jugador;
import juego.estructura.ObjetivoSecreto;
import juego.estructura.Pais;
import juego.mecanicas.situacion.GestorSituacion;
import juego.mecanicas.situacion.Situacion;
import juego.mecanicas.turno.SecuenciaTurnos;
import logger.LogItem;
import servidor.ServerManager;

/**
 *
 * @author heril
 */
public class ControlInicioJuego {

    private static boolean juegoIniciado = false;

    public static boolean juegoIniciado() {
        return juegoIniciado;
    }

    public static void reiniciar() {
        juegoIniciado = false;
    }

    public static boolean jugadoresListos() {
        Set<Integer> idClientes = ServerManager.getInstance().getGestorClientes().getIdConexionesEstablecidas();
        for (Integer id : idClientes) {
            Boolean aux = ControlEstadoJugadores.getEstadoJugador(id);
            if (aux == null || !aux) {
                return false;
            }
        }
        return true;
    }

    public static boolean jugadoresSuficientes() {
        return ServerManager.getInstance().getGestorClientes().cantidadConexionesEstablecidas() >= 2;
    }

    public static void iniciarJuego() {
        if (juegoIniciado) {
            return;
        }
        if (!jugadoresSuficientes()) {
            ServerManager.getInstance().getAdministracionPartida().informarJugadoresInsuficientes();
            return;
        }
        if (!jugadoresListos()) {
            ServerManager.getInstance().getAdministracionPartida().informarJugadoresNoListos();
            return;
        }
        juegoIniciado = true;
        inicializarParametrosJuego();
        enviarOrdenComienzoJuego();
        enviarNotificacionInicioJuego();
        iniciarRondaInicialDeIncorporacion();
    }

    private static void enviarNotificacionInicioJuego() {
        ServerManager.getInstance().registrarSalida(new AccionableNotificacionInicioJuego());
        ServerManager.getInstance().getLogger().addLogItem(new LogItem("Notificación de inicio de juego enviada a jugadores."));
    }

    private static void inicializarParametrosJuego() {
        inicializarJugadores();
        inicializarJuego();
        inicializarPaises();
        inicializarTurnos();
        inicializarObjetivosSecretos();
        inicializarTarjetas();
    }

    private static void inicializarObjetivosSecretos() {
        Set<Jugador> jugadores = GestorJugadores.getJugadores();
        LinkedList<ObjetivoSecreto> objetivos = new LinkedList<>(GestorObjetivosSecretos.getListaObjetivos(GestorJugadores.getCantidadJugadores()));
        Collections.shuffle(objetivos);
        for (Jugador j : jugadores) {
            ObjetivoSecreto objetivo = objetivos.removeFirst();
            j.setObjetivoSecreto(objetivo);
        }
        ServerManager.getInstance().getLogger().addLogItem(new LogItem("Objetivos secretos generados y asignados."));
    }

    private static void inicializarTurnos() {
        SecuenciaTurnos.getInstancia();
        ServerManager.getInstance().getLogger().addLogItem(new LogItem("Secuencia de turnos generada."));
    }

    private static void inicializarPaises() {
        List<Pais> paises = new ArrayList<>(GestorPaises.getListaPaises());
        LinkedList<Jugador> jugadores = new LinkedList(GestorJugadores.getJugadores());
        Collections.shuffle(paises);
        Collections.shuffle(jugadores);
        for (Pais p : paises) {
            Jugador j = jugadores.removeFirst();
            jugadores.add(j);
            j.añadirPais(p);
            p.setCantidadMisiles(0);
            p.setCantidadEjercitos(1);
        }
        ServerManager.getInstance().getLogger().addLogItem(new LogItem("Paises inicializados y repartidos."));
    }

    private static void inicializarJuego() {
        Juego.getInstancia().setIdJuego(ServerManager.getInstance().getServidor().getNumeroDeJuego());
        ServerManager.getInstance().getLogger().addLogItem(new LogItem("Juego inicializado."));
    }

    private static void inicializarJugadores() {
        Set<Integer> idsJugadores = ServerManager.getInstance().getGestorClientes().getIdConexionesEstablecidas();
        Set<Jugador> jugadores = new HashSet<>(8);
        for (Integer id : idsJugadores) {
            Jugador aux = new Jugador(id);
            aux.setColor(ControlColores.getColorJugador(id));
            aux.setNombre(ControladorAlias.getAliasJugador(id));
            jugadores.add(aux);
        }
        GestorJugadores.setJugadores(jugadores);
        ServerManager.getInstance().getLogger().addLogItem(new LogItem("Jugadores inicializados."));
    }

    private static void inicializarTarjetas() {
        GestorTarjetas.inicializarGestor();
        ServerManager.getInstance().getLogger().addLogItem(new LogItem("Tarjetas inicializadas."));
    }

    private static void enviarOrdenComienzoJuego() {
        AccionableInicioJuego inicioJuego = new AccionableInicioJuego(Juego.getInstancia(), GestorJugadores.getJugadores(), SecuenciaTurnos.getInstancia().getSecuencia(), GestorPaises.getListaPaises());
        ServerManager.getInstance().registrarSalida(inicioJuego);
        ServerManager.getInstance().getLogger().addLogItem(new LogItem("Orden de inicialización de juego enviada a jugadores."));
    }

    private static void iniciarRondaInicialDeIncorporacion() {
        servidor.control.ControlRondaInicial.getInstance().comenzar();
    }
}
