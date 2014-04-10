/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import java.io.Serializable;
import juego.mecanicas.situacion.CombateNormal;
import juego.mecanicas.situacion.Situacion;

/**
 *
 * @author heril
 */
public class Juego implements Serializable {

    private static Juego instancia = null;
    private int idJuego;
    private Situacion situacion = new CombateNormal();

    public static Juego getInstancia() {
        if (instancia == null) {
            instancia = new Juego();
        }
        return instancia;
    }

    private Juego() {
    }

    public Situacion getSituacion() {
        return situacion;
    }

    public void setSituacion(Situacion situacion) {
        this.situacion = situacion;
    }

    public int getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }
}
