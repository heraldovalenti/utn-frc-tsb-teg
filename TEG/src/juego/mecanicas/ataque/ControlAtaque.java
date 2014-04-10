/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.mecanicas.ataque;

import juego.Juego;
import juego.estructura.Pais;
import juego.estructura.GestorPaises;
import juego.mecanicas.situacion.CombateNormal;
import juego.mecanicas.situacion.FronterasAbiertas;
import juego.mecanicas.situacion.FronterasCerradas;
import juego.mecanicas.situacion.Nieve;
import juego.mecanicas.situacion.Situacion;
import juego.mecanicas.situacion.VientoFavor;

/**
 *
 * @author heril
 */
public class ControlAtaque {

    public String ATAQUE_INVALIDO_NO_LIMITROFES = "Los paises no son limitrofes";
    public String ATAQUE_INVALIDO_SITUACION = "La situacion no permite el ataque";
    public String ATAQUE_INVALIDO_INSUFICIENCIA = "No se cuentan con suficientes ejercitos";
    public String ATAQUE_VALIDO = "El ataque puede llevarse a cabo";
    private Ataque ataque;
    private Pais atacante;
    private Pais defensor;

    public ControlAtaque(Pais atacante, Pais defensor) {
        this.atacante = atacante;
        this.defensor = defensor;
    }

    public boolean ataqueValido() {
        return GestorPaises.sonLimitrofes(atacante, defensor)
                && Juego.getInstancia().getSituacion().ataquePermitido(atacante, defensor)
                && atacante.getCantidadEjercitos() > 1;
    }

    public boolean ataqueConMisilValido() {
        return atacante.getCantidadMisiles() > defensor.getCantidadMisiles() && defensor.getCantidadEjercitos() > 1;
    }

    public int ataquePermitido() {
        Situacion situacionJuego = Juego.getInstancia().getSituacion();
        int ejercitosAtacante = atacante.getCantidadEjercitos();
        int ejercitosDefensor = defensor.getCantidadEjercitos();
        int res = ejercitosAtacante - 1;
        double aux = ((double)ejercitosAtacante / 2);
        if ((aux >= ejercitosDefensor && ejercitosAtacante >= 4) 
                || situacionJuego instanceof VientoFavor) {
            res ++;
        } 
        if (res > 4) {
            res = 4;
        }
        return res;
    }

    public int defensaPermitida() {
        Situacion situacionJuego = Juego.getInstancia().getSituacion();
        int res = defensor.getCantidadEjercitos();
        if (res > 3) {
            res = 3;
        }
        if (situacionJuego instanceof Nieve) {
            res++;
        }
        return res;
    }

    public void atacar(int cantidadAtaque, int cantidadDefensa) {
        this.ataque = new Ataque(cantidadAtaque, cantidadDefensa);
    }

    public int perdidasAtacante() {
        int cantidadAtacante = atacante.getCantidadEjercitos() - 1;
        int cantidadResolucion = ataque.getResolucion().getResultadoAtacante();
        if (cantidadResolucion > cantidadAtacante) {
            return cantidadAtacante;
        } else {
            return cantidadResolucion;
        }
    }

    public int perdidasDefensor() {
        return ataque.getResolucion().getResultadoDefensor();
    }

    public int[] dadosAtacante() {
        return ataque.getTiradaAtaque().getTiradas();
    }

    public int[] dadosDefensor() {
        return ataque.getTiradaDefensa().getTiradas();
    }

    public boolean ataqueRealizado() {
        return this.ataque != null;
    }

    public boolean paisConquistado() {
        if (!ataqueRealizado()) {
            return false;
        }
        if (perdidasDefensor() == defensor.getCantidadEjercitos()) {
            return true;
        }
        return false;
    }

//    /**
//     * Prueba la funcionalidad de la clase.
//     *
//     * @param args
//     */
//    public static void main(String args[]) {
//        Situacion nieve = new VientoFavor();
//        Pais defensor = GestorPaises.getPais(GestorPaises.COLOMBIA);
//        Pais atacante = GestorPaises.getPais(GestorPaises.NICARAGUA);
//        defensor.setCantidadEjercitos(4);
//        atacante.setCantidadEjercitos(2);
//        Juego juego = Juego.getInstancia();
//
//        juego.setSituacion(nieve);
//        for (int i = 0; i < 100; i++) {
//        ControlAtaque control = new ControlAtaque(atacante, defensor);
//
//        System.out.println(control.ataqueValido());
////        System.out.println("defensa:" + control.defensaPermitida());
////        System.out.println("ataque:" + control.ataquePermitido());
//        control.atacar(control.ataquePermitido(), control.defensaPermitida());
//        System.out.println("perdidasAtaque:" + control.perdidasAtacante());
////            System.out.println("//////////////////////////////////");
//        }
//        int perdidasDefensa=0;
//        int perdidasAtaque=0;
//        int vueltas = 100;
//        for (int i = 0; i < vueltas; i++) {
//
//            control.atacar(control.ataquePermitido(), control.defensaPermitida());
//
//            perdidasAtaque+=control.perdidasAtacante();
//            perdidasDefensa+=control.perdidasDefensor();
//            System.out.println("Ataque permitido: " + control.ataquePermitido());
//            System.out.println("Defensa permitido: " + control.defensaPermitida());
//
//            for (int r : control.ataque.getTiradaAtaque().getTiradas()) {
//                System.out.print("tiradaAtaque=" + r + ";");
//            }
//            System.out.println("");
//            for (int r : control.ataque.getTiradaDefensa().getTiradas()) {
//                System.out.print("tiradaDefens=" + r + ";");
//            }
//            System.out.println("");
//            System.out.println("perdidasAtacante=" + control.perdidasAtacante());
//            System.out.println("perdidasDefensor=" + control.perdidasDefensor());
//        }
//        System.out.println("perdidasDefensa="+perdidasDefensa+";tasa="+(double)perdidasDefensa/(perdidasDefensa+perdidasAtaque)*100);
//        System.out.println("perdidasAtaque="+perdidasAtaque+";tasa="+(double)perdidasAtaque/(perdidasDefensa+perdidasAtaque)*100);
//    }
}
