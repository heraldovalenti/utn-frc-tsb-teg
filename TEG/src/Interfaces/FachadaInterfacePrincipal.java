/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import cliente.ClienteManager;
import java.util.List;
import juego.estructura.GestorObjetivosSecretos;
import juego.estructura.ObjetivoSecreto;
import juego.estructura.TarjetaPais;

/**
 *
 * @author heril
 */
public class FachadaInterfacePrincipal {
    
    public static void mostrarChat(String chat) {
        ClienteManager.getInstance().getInterfacePrincipal().cargarChat(chat);
    }
    public static List<ObjetivoSecreto> obtenerObjetivos(){     
        return GestorObjetivosSecretos.getListaObjetivos();
    }
    public static void mostrarTarjeta(TarjetaPais tarjeta){
        ClienteManager.getInstance().getInterfacePrincipal().mostrarTarjeta(tarjeta);
    }
    public static void actualizarMapa(){
        ClienteManager.getInstance().getInterfacePrincipal().actualizarFichas(ClienteManager.getInstance().getJuego().getGestorJugadores().getJugadores());
    }
}
    

