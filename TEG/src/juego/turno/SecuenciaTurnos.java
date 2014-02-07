/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.turno;

import java.util.LinkedList;
import java.util.List;
import juego.Juego;
import juego.Jugador;


/**
 *
 * @author heril
 */
public class SecuenciaTurnos {

    private static SecuenciaTurnos instancia = null;

    public static SecuenciaTurnos getInstancia() {
        if (instancia == null) {
            instancia = new SecuenciaTurnos();
        }
        return instancia;
    }
    private LinkedList<Jugador> secuencia;
    private int actual;
    private int contadorRondas;
    
    private SecuenciaTurnos() {
        secuencia = new LinkedList();
        actual = 0;
        contadorRondas = 1;
        List<Jugador> listaJugadores = new LinkedList(Juego.getInstancia().getJugadores());
        while (!listaJugadores.isEmpty()) {
            double rnd = Math.random();
            double dRes = rnd * listaJugadores.size();
            int iRes = (int) dRes;
            secuencia.add(listaJugadores.remove(iRes));
        }
    }
    
    /**
     * Metodo para controlar si la ronda continua o ha llegado a su fin.
     * @return si la ronda ha finalizado o no.
     */
    public boolean esFinRonda() {
        return actual >= (secuencia.size() - 1);
    }
    
    /**
     * Metodo que indica el comienzo de un nuevo turno.
     */
    public void siguienteTurno() {
        actual++;
    }
    
    /**
     * Metodo que genera una nueva ronda, pasando el primero jugador de la ronda
     * a ultimo lugar ahora.
     */
    public void nuevaRonda() {
        contadorRondas++;
        actual = 0;
        Jugador aux = secuencia.pollFirst();
        secuencia.addLast(aux);
    }
    
    /**
     * Metodo para obtener el jugador que tiene el turno actual.
     * @return el Jugador del turno.
     */
    public Jugador getActual() {
        return secuencia.get(actual);
    }
    
    /**
     * Prueba la funcionalidad de la clase.
     */
    public static void test() {        
        Juego j = Juego.getInstancia();
        LinkedList<Jugador> jugadores = new LinkedList();
        jugadores.add(new Jugador(1,"Heraldo","Naranja"));        
        jugadores.add(new Jugador(2,"Tero","Negro"));
        jugadores.add(new Jugador(3,"Nariz","Azul"));
        jugadores.add(new Jugador(4,"Bren","Verde"));
        jugadores.add(new Jugador(5,"Lulu","Blanco"));
        jugadores.add(new Jugador(6,"Gato","Rojo"));
        j.setJugadores(jugadores);
        SecuenciaTurnos st = SecuenciaTurnos.getInstancia();
        
        System.out.println("secuencia: ");
        for (Jugador i : st.secuencia) {
            System.out.println("\t>>"+i.toString());
        }
        
        st.nuevaRonda();
        System.out.println("secuencia: ");
        for (Jugador i : st.secuencia) {
            System.out.println("\t>>"+i.toString());
        }
        
        st.nuevaRonda();
        System.out.println("secuencia: ");
        for (Jugador i : st.secuencia) {
            System.out.println("\t>>"+i.toString());
        }
        
        st.nuevaRonda();
        System.out.println("secuencia: ");
        for (Jugador i : st.secuencia) {
            System.out.println("\t>>"+i.toString());
        }        
    }
    
//    public static void main (String args[]) {
//        test();
//    }
}
