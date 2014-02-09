/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.util;

import java.awt.Color;
import java.util.Objects;

/**
 *
 * @author Daniel
 */
public class Colores {

    private int numero;
    private String nombre;
    private java.awt.Color color;

    public Colores() {
    }

    public Colores(int numero, String nombre, Color color) {
        this.numero = numero;
        this.nombre = nombre;
        this.color = color;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.numero;
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
        final Colores other = (Colores) obj;
        if (this.numero != other.numero) {
            return false;
        }
        return true;
    }

}
