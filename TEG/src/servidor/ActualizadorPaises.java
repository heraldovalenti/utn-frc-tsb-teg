/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import com.Accionable;
import java.util.List;
import juego.estructura.GestorJugadores;
import juego.estructura.GestorPaises;
import juego.estructura.Jugador;
import juego.estructura.Pais;

/**
 *
 * @author Daniel
 */
public class ActualizadorPaises implements Accionable {

    private final List<Pais> listaPaises;

    public ActualizadorPaises(List<Pais> listaPaises) {
        this.listaPaises = listaPaises;
    }

    @Override
    public void accionar() {
        for (Pais paisServidor : listaPaises) {
            Pais paisCliente = GestorPaises.getPais(paisServidor.getNroPais());
            paisCliente.setCantidadEjercitos(paisServidor.getCantidadEjercitos());
            paisCliente.setCantidadMisiles(paisServidor.getCantidadMisiles());
            Jugador jugador = GestorJugadores.obtenerPorNumero(paisServidor.getJugador().getNroJugador());
            paisCliente.setJugador(jugador);
        }
    }

}
