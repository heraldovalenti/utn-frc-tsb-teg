/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import com.Accionable;
import java.util.ArrayList;
import java.util.List;
import juego.estructura.GestorPaises;
import juego.estructura.Pais;
import servidor.ActualizadorPaises;

/**
 *
 * @author Daniel
 */
public class AccionableCanjePorEjercitos implements Accionable {

    private final Pais paisCliente;
    private final int cantidadMisiles;

    public AccionableCanjePorEjercitos(Pais paisCliente, int cantidadMisiles) {
        this.paisCliente = paisCliente;
        this.cantidadMisiles = cantidadMisiles;
    }

    @Override
    public void accionar() {
        Pais paisServidor = GestorPaises.getPais(paisCliente.getNroPais());
        paisServidor.añadirEjercitos(cantidadMisiles * 6);
        paisServidor.restarMisiles(cantidadMisiles);
        List listaPaises = new ArrayList(1);
        listaPaises.add(paisServidor);
        ActualizadorPaises actualizador = new ActualizadorPaises(listaPaises);
    }
}
