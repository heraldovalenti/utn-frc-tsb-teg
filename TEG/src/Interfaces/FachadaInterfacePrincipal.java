/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import cliente.ClienteManager;
import java.util.List;
import juego.estructura.GestorObjetivosSecretos;
import juego.estructura.Jugador;
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
    public static void mostrarTarjeta(TarjetaPais tarjeta) {
        ClienteManager.getInstance().getInterfacePrincipal().mostrarTarjeta(tarjeta);
    }
    
    public static void actualizarMapa() {
        ClienteManager.getInstance().getInterfacePrincipal().actualizarFichas();
    }

    public static void mostrarResultadoAtaque(String nombreAtacante, String nombreDefensor, int[] dadosAtacante, int dadosDefensor[]) {
        ClienteManager.getInstance().getInterfacePrincipal().cargarDados(nombreAtacante, nombreDefensor, dadosAtacante, dadosDefensor);
    }
    
    public static void mostrarMensaje(String mensaje){
        //Ema esto es para que pongas un label o algo asi que muestre el mensaje para todos los jugadores
    }
    
    public static void informarRondaInicial() {
        //METODO PARA INFORMAR QUE ES EL TURNO DEL JUGADOR PARA AGREGAR
        //EJERCITOS EN SUS PAISES, CORRESPONDIENTES A LA RONDA INICIAL.
    }
    
    public static void informarRondaInicial(Jugador jugador) {
        //METODO PARA INFORMAR QUE ES EL TURNO DEL JUGADOR INDICADO EN LA RONDA
        //INICIAL.
    }
    
    public static void informarFinRondaInicial() {
        //METODO PARA INFORMAR QUE ES FINALIZO EL TURNO DE RONDA INICIAL
    }
    public static void iniciarAgregadoRefuerzo(){
        ClienteManager.getInstance().getInterfacePrincipal().inciarRefuerzo();
    }
}
