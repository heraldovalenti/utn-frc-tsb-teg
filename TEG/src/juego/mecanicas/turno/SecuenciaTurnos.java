/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.mecanicas.turno;

import com.servidor.AccionableInicioTurno;
import com.servidor.AccionablePermitirAtaque;
import com.servidor.AccionablePermitirRefuerzo;
import com.servidor.AccionableSituacion;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import juego.Juego;
import juego.estructura.Continente;
import juego.estructura.GestorContinentes;
import juego.estructura.GestorJugadores;
import juego.estructura.Jugador;
import juego.mecanicas.situacion.GestorSituacion;
import juego.mecanicas.situacion.Situacion;
import servidor.ServerManager;

/**
 *
 * @author heril
 */
public class SecuenciaTurnos {
    
    private static SecuenciaTurnos instancia = null;
    private List<Jugador> secuencia;
    private int actual;
    private int contadorRondas;
    private boolean rondaInicial1;
    private boolean rondaInicial2;
    private boolean situacionUtilizada;
    private boolean rondaDesdeAtaque;
    
    public static SecuenciaTurnos getInstancia() {
        if (instancia == null) {
            instancia = new SecuenciaTurnos();
        }
        return instancia;
    }
    
    private SecuenciaTurnos() {
        //  secuencia = new ArrayList<>();
        actual = 0;
        contadorRondas = 1;
        secuencia = new LinkedList<>(GestorJugadores.getJugadores());
        Collections.shuffle(secuencia);
        rondaInicial1 = true;
//        while (!listaJugadores.isEmpty()) {
//            double rnd = Math.random();
//            double dRes = rnd * listaJugadores.size();
//            int iRes = (int) dRes;
//            secuencia.add(listaJugadores.remove(iRes));
//        }
    }

    /**
     * Metodo para controlar si la ronda continua o ha llegado a su fin.
     *
     * @return si la ronda ha finalizado o no.
     */
    public boolean esFinRonda() {
        return actual >= (secuencia.size() - 1);
    }

    /**
     * Metodo que indica el comienzo de un nuevo turno.
     */
    public void siguienteTurno() {

//        if (esRondaInicial()) {
//            if (esFinRonda()) {
//                actual = 0;
//                if (rondaInicial1) {
//                    rondaInicial1 = false;
//                    rondaInicial2 = true;
//                } else {
//                    rondaInicial2 = false;
//                }
//            } else {
//                actual++;
//            }
//            if (esRondaInicial()) {
//                AccionablePermitirRefuerzo accionable = new AccionablePermitirRefuerzo(getActual(), calcularRefuerzosPermitidos(getActual()), new HashMap<Continente, Integer>(), false);
//                ServerManager.getInstance().registrarSalida(accionable);
//            } else {
//                if (actual == 0) {
//                    Situacion situacion = GestorSituacion.getInstance().getProximaSituacion();
//                    ServerManager.getInstance().registrarSalida(new AccionableSituacion(situacion));
//                }
//                AccionablePermitirAtaque accionable = new AccionablePermitirAtaque(getActual());
//                ServerManager.getInstance().registrarSalida(accionable);
//            }
//        } else {
//            if (esFinRonda()) {
//                nuevaRonda();
//            } else {
//                actual++;
//            }
//            if (contadorRondas > 1) {
//                if (actual == 0) {
//                    Situacion situacion = GestorSituacion.getInstance().getProximaSituacion();
//                    ServerManager.getInstance().registrarSalida(new AccionableSituacion(situacion));
//                }
//                AccionablePermitirRefuerzo accionable = new AccionablePermitirRefuerzo(getActual(), calcularRefuerzosPermitidos(getActual()), new HashMap<Continente, Integer>(), true);
//                ServerManager.getInstance().registrarSalida(accionable);
//            } else {
//                AccionablePermitirAtaque accionable = new AccionablePermitirAtaque(getActual());
//                ServerManager.getInstance().registrarSalida(accionable);
//            }
//        }
//        AccionableInicioTurno accionable = new AccionableInicioTurno(getActual());
//        ServerManager.getInstance().registrarSalida(accionable);
        if (esRondaSoloRefuerzos()) {
            if (esFinRonda()) {
                actual = 0;
                if (rondaInicial1) {
                    rondaInicial1 = false;
                    rondaInicial2 = true;
                } else if (rondaInicial2) {
                    rondaInicial2 = false;
                    rondaDesdeAtaque = true;
                } else {
                    situacionUtilizada = true;
                }
            } else {
                actual++;
            }
            if (!rondaDesdeAtaque) {
                AccionablePermitirRefuerzo accionable = new AccionablePermitirRefuerzo(getActual(), calcularRefuerzosPermitidos(getActual()), calcularEjercitosPorContinente(getActual()), false);
                ServerManager.getInstance().registrarSalida(accionable);
            } else {
                AccionablePermitirAtaque accionable = new AccionablePermitirAtaque(getActual());
                ServerManager.getInstance().registrarSalida(accionable);
            }
        } else if (rondaDesdeAtaque) {
            if (esFinRonda()) {
                nuevaRonda();
            } else {
                actual++;
            }
            if (rondaDesdeAtaque) {
                AccionablePermitirAtaque accionable = new AccionablePermitirAtaque(getActual());
                ServerManager.getInstance().registrarSalida(accionable);
            } else {
                AccionablePermitirRefuerzo accionable = new AccionablePermitirRefuerzo(getActual(), calcularRefuerzosPermitidos(getActual()), calcularEjercitosPorContinente(getActual()), true);
                ServerManager.getInstance().registrarSalida(accionable);
            }
        } else {
            if (esFinRonda()) {
                nuevaRonda();
            } else {
                actual++;
            }
            AccionablePermitirRefuerzo accionable = new AccionablePermitirRefuerzo(getActual(), calcularRefuerzosPermitidos(getActual()), calcularEjercitosPorContinente(getActual()), true);
            ServerManager.getInstance().registrarSalida(accionable);
        }
        AccionableInicioTurno accionable = new AccionableInicioTurno(getActual());
        ServerManager.getInstance().registrarSalida(accionable);
    }

    /**
     * Metodo que genera una nueva ronda, pasando el primero jugador de la ronda
     * a ultimo lugar ahora.
     */
    public void nuevaRonda() {
        contadorRondas++;
        actual = 0;
        Jugador aux = secuencia.remove(0);
        secuencia.add(aux);
        situacionUtilizada = false;
        rondaDesdeAtaque = false;
    }

    /**
     * Metodo para obtener el jugador que tiene el turno actual.
     *
     * @return el Jugador del turno.
     */
    public Jugador getActual() {
        return secuencia.get(actual);
    }

    /**
     * Devuelve una lista con la secuencia actual de turnos de los jugadores.
     *
     * @return secuencia de turnos.
     */
    public List<Jugador> getSecuencia() {
        return new ArrayList<>(secuencia);
    }

    /**
     * Establece la secuencia de turnos.
     *
     * @param secuencia la secuencia de turnos de jugadores.
     */
    public void setSecuencia(List<Jugador> secuencia) {
        this.secuencia = secuencia;
    }

    /**
     * Informa el jugador anterior al jugador indicado como parametro. Es
     * importante destacar que no tiene en cuenta la finalizacion de la ronda y
     * la generacion de un nuevo turno, que es en el momento en el que el orden
     * de los turnos es modificado.
     *
     * @param jugador el jugador de referencia
     * @return el jugador anterior al jugador de referencia
     */
    public Jugador getJugadorAnterior(Jugador jugador) {
        for (int i = 0; i < secuencia.size(); i++) {
            Jugador res = secuencia.get(i);
            if (jugador.equals(res)) {
                if (i == 0) {
                    return secuencia.get(secuencia.size() - 1);
                } else {
                    return secuencia.get(i - 1);
                }
            }
        }
        return null;
    }

    /**
     * Informa el jugador siguiente al jugador indicado como parametro. Es
     * importante destacar que no tiene en cuenta la finalizacion de la ronda y
     * la generacion de un nuevo turno, que es en el momento en el que el orden
     * de los turnos es modificado.
     *
     * @param jugador el jugador de referencia
     * @return el jugador siguiente al jugador de referencia
     */
    public Jugador getJugadorSiguiente(Jugador jugador) {
        for (int i = 0; i < secuencia.size(); i++) {
            Jugador res = secuencia.get(i);
            if (jugador.equals(res)) {
                if (i == secuencia.size() - 1) {
                    return secuencia.get(0);
                } else {
                    return secuencia.get(i + 1);
                }
            }
        }
        return null;
    }
    
    private int calcularRefuerzosPermitidos(Jugador jugador) {
        int refuerzos = 0;
        if (rondaInicial1) {
            if (GestorJugadores.getCantidadJugadores() <= 2) {
                refuerzos = 12;
            } else {
                refuerzos = 8;
            }
        } else if (rondaInicial2) {
            if (GestorJugadores.getCantidadJugadores() <= 2) {
                refuerzos = 6;
            } else {
                refuerzos = 4;
            }
        } else {
            refuerzos = (int) jugador.getCantidadPaises() / 2;
        }
        return refuerzos;
    }
    
    private Map<Continente, Integer> calcularEjercitosPorContinente(Jugador jugador) {
        Map<Continente, Integer> mapaContinentes = new HashMap<>();
        Set<Continente> conjuntoContinentes = jugador.obtenerContinentesOcupadosCompletos();
        for (Continente continente : conjuntoContinentes) {
            mapaContinentes.put(continente, GestorContinentes.obtenerRefuerzosPorContinente(continente));
        }
        return mapaContinentes;
    }
    
    public int getContadorRondas() {
        return contadorRondas;
    }
    
    public boolean esRondaInicial() {
        return rondaInicial1 || rondaInicial2;
    }
    
    private boolean esRondaSoloRefuerzos() {
        boolean rondaSoloRefuerzos = false;
        if (esRondaInicial()) {
            rondaSoloRefuerzos = true;
        }
        if (GestorSituacion.getInstance().getProximaSituacion().refuerzosExtra() && !situacionUtilizada) {
            rondaSoloRefuerzos = true;
        }
        return rondaSoloRefuerzos;
    }

    /**
     * Prueba la funcionalidad de la clase.
     */
    public static void test() {
        Juego j = Juego.getInstancia();
        Set<Jugador> jugadores = new HashSet<>();
        jugadores.add(new Jugador(1, "Heraldo", Color.ORANGE));
        jugadores.add(new Jugador(2, "Tero", Color.BLACK));
        jugadores.add(new Jugador(3, "Nariz", Color.BLUE));
        jugadores.add(new Jugador(4, "Bren", Color.GREEN));
        jugadores.add(new Jugador(5, "Lulu", Color.WHITE));
        jugadores.add(new Jugador(6, "Gato", Color.RED));
        GestorJugadores.setJugadores(jugadores);
        SecuenciaTurnos st = SecuenciaTurnos.getInstancia();
        
        System.out.println("secuencia: ");
        for (Jugador i : st.secuencia) {
            System.out.println("\t>>" + i.toString());
        }
        
        st.nuevaRonda();
        System.out.println("secuencia: ");
        for (Jugador i : st.secuencia) {
            System.out.println("\t>>" + i.toString());
        }
        
        st.nuevaRonda();
        System.out.println("secuencia: ");
        for (Jugador i : st.secuencia) {
            System.out.println("\t>>" + i.toString());
        }
        
        st.nuevaRonda();
        System.out.println("secuencia: ");
        for (Jugador i : st.secuencia) {
            System.out.println("\t>>" + i.toString());
        }
    }
    
    public static void testSecuencia() {
        Juego j = Juego.getInstancia();
        Set<Jugador> jugadores = new HashSet<>();
        jugadores.add(new Jugador(1, "Heraldo", Color.ORANGE));
//        jugadores.add(new Jugador(2, "Tero", Color.BLACK));
//        jugadores.add(new Jugador(3, "Nariz", Color.BLUE));
        jugadores.add(new Jugador(4, "Bren", Color.GREEN));
        jugadores.add(new Jugador(5, "Lulu", Color.WHITE));
//        jugadores.add(new Jugador(6, "Gato", Color.RED));
        GestorJugadores.setJugadores(jugadores);
        SecuenciaTurnos st = SecuenciaTurnos.getInstancia();
        
        for (int i = 0; i < 10; i++) {
            System.out.println("jugador actual: " + st.getActual().getNombre());
            System.out.println("secuencia: ");
            for (Jugador jug : st.secuencia) {
                System.out.println("\t>>" + jug.toString());
            }
            st.siguienteTurno();
        }
        
    }
    
    public static void testSecuenciaTurnos() {
        Juego j = Juego.getInstancia();
        Set<Jugador> jugadores = new HashSet<>();
        jugadores.add(new Jugador(1, "Heraldo", Color.ORANGE));
        jugadores.add(new Jugador(2, "Tero", Color.BLACK));
        jugadores.add(new Jugador(3, "Nariz", Color.BLUE));
        jugadores.add(new Jugador(4, "Bren", Color.GREEN));
        jugadores.add(new Jugador(5, "Lulu", Color.WHITE));
        jugadores.add(new Jugador(6, "Gato", Color.RED));
        GestorJugadores.setJugadores(jugadores);
        SecuenciaTurnos st = SecuenciaTurnos.getInstancia();
        
        Jugador aux = st.getActual();
        for (int i = 0; i < 20; i++) {
            System.out.println("jugador actual: " + aux.getNombre());
            aux = st.getJugadorSiguiente(aux);
        }
    }

//    public static void main(String args[]) {
//        testSecuenciaTurnos();
//    }
}
