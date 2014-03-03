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
import juego.mecanicas.ataque.ControlAtaque;
import servidor.ActualizadorPaises;

/**
 *
 * @author Daniel
 */
public class AccionableAtaque implements Accionable {

    private final Pais origenCliente;
    private final Pais destinoCliente;

    public AccionableAtaque(Pais origenCliente, Pais destinoCliente) {
        this.origenCliente = origenCliente;
        this.destinoCliente = destinoCliente;
    }

    @Override
    public void accionar() {
        ControlAtaque control = new ControlAtaque(origenCliente, destinoCliente);
        Pais origenServidor = GestorPaises.getPais(origenCliente.getNroPais());
        Pais destinoServidor = GestorPaises.getPais(destinoCliente.getNroPais());
        if (control.ataqueValido()) {
            int ejercitosAtacantes = control.ataquePermitido();
            int ejercitosDefensores = control.defensaPermitida();
            control.atacar(ejercitosAtacantes, ejercitosDefensores);
            destinoServidor.restarEjercitos(control.perdidasDefensor());
            origenServidor.restarEjercitos(control.perdidasAtacante());
            if (destinoServidor.getCantidadEjercitos() < 1) {
                destinoServidor.ocuparPais(origenServidor.getJugador());
            }
            List<Pais> listaPaises = new ArrayList<>(2);
            listaPaises.add(origenServidor);
            listaPaises.add(destinoServidor);
            ActualizadorPaises actualizador = new ActualizadorPaises(listaPaises);
        }
    }

}
