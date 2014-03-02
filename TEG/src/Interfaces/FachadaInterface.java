/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.List;
import juego.estructura.GestorObjetivosSecretos;
import juego.estructura.ObjetivoSecreto;

/**
 *
 * @author Emanuel
 */
public class FachadaInterface {
    public static List<ObjetivoSecreto> obtenerObjetivos(){
        
        return GestorObjetivosSecretos.getListaObjetivos();
    }
}
