/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.ataque;

import juego.Juego;
import juego.Pais;
import juego.estructura.Paises;

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
        return Paises.sonLimitrofes(atacante, defensor)
                && Juego.getInstancia().getSituacion().ataquePermitido(atacante, defensor)
                && atacante.getOcupacion().getEjercitos() > 1;
    }

    public int ataquePermitido() {
        int res = 4;
        int porSituacion = Juego.getInstancia().getSituacion().maximoAtaque();
        int porOcupacion = atacante.getOcupacion().getEjercitos();
        int ejercitosDefensor = defensor.getOcupacion().getEjercitos();
        int porSuperPobacion = ((double) (porOcupacion / 2) >= ejercitosDefensor && porOcupacion >= 4) ? 4 : 3;
        res = Math.max(porSituacion, porSuperPobacion);
        res = Math.min(res, porOcupacion);
        return res;
    }

    public int defensaPermitida() {
        int res = 4;
        int porSituacion = Juego.getInstancia().getSituacion().maximoDefensa();
        int porOcupacion = defensor.getOcupacion().getEjercitos();
        res = Math.min(res, porSituacion);
        res = Math.min(res, porOcupacion);
        return res;
    }

    public void atacar(int cantidadAtaque, int cantidadDefensa) {
        this.ataque = new Ataque(cantidadAtaque, cantidadDefensa);
    }

    public int perdidasAtacante() {
        return ataque.getResolucion().getResultadoAtacante();
    }

    public int perdidasDefensor() {
        return ataque.getResolucion().getResultadoDefensor();
    }

//    /**
//     * Prueba la funcionalidad de la clase.
//     *
//     * @param args
//     */
//    public static void main(String args[]) {
//        Situacion situacion = new Nieve();
//        Pais defensor = new Pais();
//        Pais atacante = new Pais();
//        Ocupacion ocupacionDefensor = new Ocupacion();
//        Ocupacion ocupacionAtacante = new Ocupacion();
//        Juego juego = Juego.getInstancia();
//        juego.setSituacion(situacion);
//
//        ocupacionDefensor.setEjercitos(4);
//        ocupacionAtacante.setEjercitos(8);
//        defensor.setOcupacion(ocupacionDefensor);
//        atacante.setOcupacion(ocupacionAtacante);
//        ControlAtaque control = new ControlAtaque(atacante, defensor);
//
//
//        int perdidasDefensa=0;
//        int perdidasAtaque=0;
//        int vueltas = 100000;
//        for (int i = 0; i < vueltas; i++) {
//
//            control.atacar(control.ataquePermitido(), control.defensaPermitida());
//
//            perdidasAtaque+=control.perdidasAtacante();
//            perdidasDefensa+=control.perdidasDefensor();
////            System.out.println("Ataque permitido: " + control.ataquePermitido());
////            System.out.println("Defensa permitido: " + control.defensaPermitida());
////
////            for (int r : control.ataque.getTiradaAtacaque().getTiradas()) {
////                System.out.print("tiradaAtaque=" + r + ";");
////            }
////            System.out.println("");
////            for (int r : control.ataque.getTiradaDefensa().getTiradas()) {
////                System.out.print("tiradaDefens=" + r + ";");
////            }
////            System.out.println("");
////            System.out.println("perdidasAtacante=" + control.perdidasAtacante());
////            System.out.println("perdidasDefensor=" + control.perdidasDefensor());
//        }
//        System.out.println("perdidasDefensa="+perdidasDefensa+";tasa="+(double)perdidasDefensa/(perdidasDefensa+perdidasAtaque)*100);
//        System.out.println("perdidasAtaque="+perdidasAtaque+";tasa="+(double)perdidasAtaque/(perdidasDefensa+perdidasAtaque)*100);
//    }
}
