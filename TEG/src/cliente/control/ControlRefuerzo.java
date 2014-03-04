/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.control;

import cliente.ClienteManager;
import com.cliente.AccionableRefuerzo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import juego.estructura.Continente;
import juego.estructura.Pais;

/**
 *
 * @author Daniel
 */
public class ControlRefuerzo {

    private final int cantidadEjercitos;
    private final Map<Continente, Integer> ejercitosPorContinente;
    private Map<Pais, Integer> ejercitosColocados;
    private Map<Pais, Integer> misilesColocados;

    public ControlRefuerzo(int cantidadEjercitos, Map<Continente, Integer> ejercitosPorContinente) {
        this.cantidadEjercitos = cantidadEjercitos;
        this.ejercitosPorContinente = ejercitosPorContinente;
    }

    public boolean agregarEjercito(Pais pais) {
        if (totalUtilizado() < maximoPermitido()) {
            int cantidadAnterior = 0;
            if (ejercitosColocados.containsKey(pais)) {
                cantidadAnterior = ejercitosColocados.get(pais);
            }
            ejercitosColocados.put(pais, cantidadAnterior + 1);
            pais.añadirEjercitos(1);
            return true;
        }
        return false;
    }

    public boolean agregarMisil(Pais pais) {
        if (totalUtilizado() < maximoPermitido() - 5) {
            int cantidadAnterior = 0;
            if (misilesColocados.containsKey(pais)) {
                cantidadAnterior = misilesColocados.get(pais);
            }
            misilesColocados.put(pais, cantidadAnterior + 1);
            pais.añadirMisiles(1);
            return true;
        }
        return false;
    }

    private int totalUtilizado() {
        int acu = 0;
        for (Integer cantidad : ejercitosColocados.values()) {
            acu += cantidad;
        }
        for (Integer cantidad : misilesColocados.values()) {
            acu += (cantidad * 6);
        }
        return acu;
    }

    private int maximoPermitido() {
        int acu = cantidadEjercitos;
        for (Integer cantidad : ejercitosPorContinente.values()) {
            acu += cantidad;
        }
        return acu;
    }

    public boolean quedanRefuerzos() {
        return totalUtilizado() < maximoPermitido();
    }

    public void reiniciar() {
        for (Pais pais : ejercitosColocados.keySet()) {
            pais.restarEjercitos(ejercitosColocados.get(pais));
            ejercitosColocados.put(pais, 0);
        }
        for (Pais pais : misilesColocados.keySet()) {
            pais.restarMisiles(misilesColocados.get(pais));
            misilesColocados.put(pais, 0);
        }
    }

    private boolean esValido() {
        if (totalUtilizado() != maximoPermitido()) {
            return false;
        }
        Map<Continente, Integer> utilizadosPorContinente = new HashMap<>();
        for (Pais pais : ejercitosColocados.keySet()) {
            Continente continente = pais.getContinente();
            int cantidadAnterior = 0;
            if (utilizadosPorContinente.containsKey(continente)) {
                cantidadAnterior = utilizadosPorContinente.get(continente);
            }
            utilizadosPorContinente.put(continente, cantidadAnterior + ejercitosColocados.get(pais));
        }
        for (Pais pais : misilesColocados.keySet()) {
            Continente continente = pais.getContinente();
            int cantidadAnterior = 0;
            if (utilizadosPorContinente.containsKey(continente)) {
                cantidadAnterior = utilizadosPorContinente.get(continente);
            }
            utilizadosPorContinente.put(continente, cantidadAnterior + misilesColocados.get(pais) * 6);
        }
        for (Continente continente : ejercitosPorContinente.keySet()) {
            if (!utilizadosPorContinente.containsKey(continente)) {
                return false;
            }
            if (ejercitosPorContinente.get(continente) < utilizadosPorContinente.get(continente)) {
                return false;
            }
        }
        return true;
    }

    public boolean aplicarRefuerzo() {
        if (esValido()) {
            List<Pais> listaPaises = new ArrayList<>(ejercitosColocados.keySet());
            for (Pais pais : misilesColocados.keySet()) {
                if (!listaPaises.contains(pais)) {
                    listaPaises.add(pais);
                }
            }
            AccionableRefuerzo refuerzo = new AccionableRefuerzo(listaPaises);
            ClienteManager.getInstance().registrarSalida(refuerzo);
            return true;
        }
        return false;
    }
}
