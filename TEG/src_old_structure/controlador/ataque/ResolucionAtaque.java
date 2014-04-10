/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.ataque;

/**
 *
 * @author heril
 */
public class ResolucionAtaque {

    private int resultadoAtacante;
    private int resultadoDefensor;

    public ResolucionAtaque(TiradaAtaque ataque, TiradaAtaque defensa) {
        this.resultadoAtacante = 0;
        this.resultadoDefensor = 0;
        for (int i = 0; i < Math.min(defensa.getTiradas().length, ataque.getTiradas().length); i++) {
            if (defensa.getTiradas()[i] >= ataque.getTiradas()[i]) {
                this.resultadoAtacante++;
            } else {
                this.resultadoDefensor++;
            }
        }
    }

    public int getResultadoAtacante() {
        return resultadoAtacante;
    }

    public int getResultadoDefensor() {
        return resultadoDefensor;
    }
}
