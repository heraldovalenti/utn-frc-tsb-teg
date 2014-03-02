/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package juego.mecanicas.turno;

/**
 *
 * @author Daniel
 */
public class GestorTurno {
    private static final int FUERA_TURNO = 0;
    private static final int ETAPA_INCORPORAR_EJERCITOS = 1;
    private static final int ETAPA_ATACAR = 2;
    private static final int ETAPA_REAGRUPAR = 3;
    private static final int ETAPA_SOLICITAR_TARJETA = 4;
    
    private static final int ACCION_ATACAR = 1;
    private static final int ACCION_SOLICITAR_TARJETA = 1;
    private static final int ACCION_INCORPORAR_EJERCITOS = 1;
    private static final int ACCION_REAGRUPAR = 1;
    private static final int ACCION_CANJEAR_TARJETA = 1;
    
    private static final boolean[][] permisos = new boolean[4][5];
    
    
}
