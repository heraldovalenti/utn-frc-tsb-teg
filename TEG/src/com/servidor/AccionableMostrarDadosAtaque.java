/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servidor;

import Interfaces.FachadaInterfacePrincipal;
import com.Accionable;

/**
 *
 * @author Daniel
 */
public class AccionableMostrarDadosAtaque implements Accionable {

    private String nombreAtacante;
    private String nombreDefensor;
    private int[] dadosAtacante;
    private int[] dadosDefensor;

    public AccionableMostrarDadosAtaque(String nombreAtacante, String nombreDefensor, int[] dadosAtacante, int[] dadosDefensor) {
        this.nombreAtacante = nombreAtacante;
        this.nombreDefensor = nombreDefensor;
        this.dadosAtacante = dadosAtacante;
        this.dadosDefensor = dadosDefensor;
    }

    @Override
    public void accionar() {
         FachadaInterfacePrincipal.mostrarResultadoAtaque(nombreAtacante, nombreDefensor, dadosAtacante, dadosDefensor);
    }

}
