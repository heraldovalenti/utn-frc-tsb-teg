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
public class AccionableRefuerzo implements Accionable {

    private final Pais paisCliente;
    private final int cantidadEjercitos;
    private final int cantidadMisiles;

    public AccionableRefuerzo(Pais paisCliente, int cantidadEjercitos, int cantidadMisiles) {
        this.paisCliente = paisCliente;
        this.cantidadEjercitos = cantidadEjercitos;
        this.cantidadMisiles = cantidadMisiles;
    }

    @Override
    public void accionar() {
        Pais paisServidor = GestorPaises.getPais(paisCliente.getNroPais());
        paisServidor.añadirEjercitos(cantidadEjercitos);
        paisServidor.añadirMisiles(cantidadMisiles);
        List<Pais> listaPaises = new ArrayList<>(1);
        listaPaises.add(paisServidor);
        ActualizadorPaises actualizador = new ActualizadorPaises(listaPaises);
    }

}
