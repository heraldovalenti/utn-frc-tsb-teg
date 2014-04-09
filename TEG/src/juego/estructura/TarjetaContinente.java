/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.estructura;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Daniel
 */
public class TarjetaContinente implements Canjeable, Serializable {

    private int nroTarjeta;
    private Continente continente;
    private int[] simbolo;
    private final Set<Jugador> usadaPor = new HashSet<>(8);

    public TarjetaContinente(int nroTarjeta, Continente continente, int[] simbolo) {
        this.nroTarjeta = nroTarjeta;
        this.continente = continente;
        this.simbolo = simbolo;
    }

    public int getNroTarjeta() {
        return nroTarjeta;
    }
    public String getNombrePais(){
        return continente.getNombre();
    }

    public void setNroTarjeta(int nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    public Continente getContinente() {
        return continente;
    }

    public void setContinente(Continente continente) {
        this.continente = continente;
    }

    public int[] getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(int[] simbolo) {
        this.simbolo = simbolo;
    }

    public void registrarUso(Jugador jugador) {
        usadaPor.add(jugador);
    }

    public boolean fueUsada(Jugador jugador) {
        return usadaPor.contains(jugador);
    }

    @Override
    public int getValor() {
        return simbolo[0];
    }

    @Override
    public int getPeso() {
        return simbolo[1];
    }

    @Override
    public boolean esComodin() {
        return (simbolo == GestorTarjetas.ARMA);
    }

}
