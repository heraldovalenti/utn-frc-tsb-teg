/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.estructura;

/**
 *
 * @author Daniel
 */
public class TarjetaContinente implements Canjeable {

    private int nroTarjeta;
    private Continente continente;
    private int[] simbolo;
    private boolean usada;

    public TarjetaContinente(int nroTarjeta, Continente continente, int[] simbolo) {
        this.nroTarjeta = nroTarjeta;
        this.continente = continente;
        this.simbolo = simbolo;
    }

    public int getNroTarjeta() {
        return nroTarjeta;
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

    public boolean isUsada() {
        return usada;
    }

    public void setUsada(boolean usada) {
        this.usada = usada;
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
