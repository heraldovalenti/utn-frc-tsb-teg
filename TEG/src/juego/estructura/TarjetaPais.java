/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.estructura;

import java.io.Serializable;

/**
 *
 * @author Daniel
 */
public class TarjetaPais implements Canjeable, Serializable {

    private int nroTarjeta;
    private Pais pais;
    private int[] simbolo;
    private boolean usada;

    public TarjetaPais() {
    }

    public TarjetaPais(int nroTarjeta, Pais pais, int[] simbolo) {
        this.nroTarjeta = nroTarjeta;
        this.pais = pais;
        this.simbolo = simbolo;
    }

    public int getNroTarjeta() {
        return nroTarjeta;
    }

    public void setNroTarjeta(int nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
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
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.nroTarjeta;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TarjetaPais other = (TarjetaPais) obj;
        if (this.nroTarjeta != other.nroTarjeta) {
            return false;
        }
        return true;
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
