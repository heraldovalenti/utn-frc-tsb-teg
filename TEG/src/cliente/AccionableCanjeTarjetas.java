/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import com.Accionable;
import java.util.ArrayList;
import java.util.List;
import juego.estructura.Canjeable;
import juego.estructura.GestorJugadores;
import juego.estructura.GestorTarjetas;
import juego.estructura.Jugador;
import servidor.ActualizadorJugadores;

/**
 *
 * @author Daniel
 */
public class AccionableCanjeTarjetas implements Accionable {

    private final Jugador jugadorCliente;
    private final List<Canjeable> listaTarjetas;

    public AccionableCanjeTarjetas(Jugador jugadorCliente, List<Canjeable> listaTarjetas) {
        this.jugadorCliente = jugadorCliente;
        this.listaTarjetas = listaTarjetas;
    }

    @Override
    public void accionar() {
        if (GestorTarjetas.canjeValido(listaTarjetas)) {
            Jugador jugadorServidor = GestorJugadores.obtenerPorNumero(jugadorCliente.getNroJugador());
            jugadorServidor.canjearTarjetas(listaTarjetas);
            List<Jugador> listaJugadores = new ArrayList<>(1);
            listaJugadores.add(jugadorServidor);
            ActualizadorJugadores actualizador = new ActualizadorJugadores(listaJugadores);
        }
    }

}
