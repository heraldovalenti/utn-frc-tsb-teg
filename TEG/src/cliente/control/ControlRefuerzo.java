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
import logger.LogItem;

/**
 *
 * @author Daniel
 */
public class ControlRefuerzo {

    private final int cantidadEjercitos;
    private final Map<Continente, Integer> ejercitosPorContinente;
    private Map<Pais, Integer> ejercitosColocados = new HashMap<>();
    private Map<Pais, Integer> misilesColocados = new HashMap<>();
    private final Map<Continente, Integer> refuerzosUtilizadosPorContinente = new HashMap<>(5);
    private final boolean permitirMisiles;

    public ControlRefuerzo(int cantidadEjercitos, Map<Continente, Integer> ejercitosPorContinente, boolean permitirMisiles) {
        this.cantidadEjercitos = cantidadEjercitos;
        this.ejercitosPorContinente = ejercitosPorContinente;
        this.permitirMisiles = permitirMisiles;
    }

    public int getCantidadEjercitos() {
        return cantidadEjercitos;
    }

    public Map<Continente, Integer> getEjercitosPorContinente() {
        return ejercitosPorContinente;
    }

    public Map<Continente, Integer> getRefuerzosUtilizadosPorContinente() {
        return refuerzosUtilizadosPorContinente;
    }

    public boolean agregarEjercito(Pais pais) {
        if (totalUtilizado() < maximoPermitido()) {
            int cantidadAnterior = 0;
            if (ejercitosColocados.containsKey(pais)) {
                cantidadAnterior = ejercitosColocados.get(pais);
            }
            ejercitosColocados.put(pais, cantidadAnterior + 1);
            registrarUsoPorContinente(pais, 1);
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
            registrarUsoPorContinente(pais, 6);
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
        refuerzosUtilizadosPorContinente.clear();
    }

//    private boolean esValido() {
//        if (totalUtilizado() != maximoPermitido()) {
//            return false;
//        }
//        Map<Continente, Integer> utilizadosPorContinente = new HashMap<>();
//        for (Pais pais : ejercitosColocados.keySet()) {
//            Continente continente = pais.getContinente();
//            int cantidadAnterior = 0;
//            if (utilizadosPorContinente.containsKey(continente)) {
//                cantidadAnterior = utilizadosPorContinente.get(continente);
//            }
//            utilizadosPorContinente.put(continente, cantidadAnterior + ejercitosColocados.get(pais));
//        }
//        for (Pais pais : misilesColocados.keySet()) {
//            Continente continente = pais.getContinente();
//            int cantidadAnterior = 0;
//            if (utilizadosPorContinente.containsKey(continente)) {
//                cantidadAnterior = utilizadosPorContinente.get(continente);
//            }
//            utilizadosPorContinente.put(continente, cantidadAnterior + misilesColocados.get(pais) * 6);
//        }
//        for (Continente continente : ejercitosPorContinenteDisponibles.keySet()) {
//            if (!utilizadosPorContinente.containsKey(continente)) {
//                return false;
//            }
//            if (ejercitosPorContinenteDisponibles.get(continente) < utilizadosPorContinente.get(continente)) {
//                return false;
//            }
//        }
//        return true;
//    }
    private boolean esValido() {
        if (totalUtilizado() != maximoPermitido()) {
            return false;
        }
        for (Continente continente : ejercitosPorContinente.keySet()) {
            if (!refuerzosUtilizadosPorContinente.containsKey(continente)) {
                return false;
            }
            if (ejercitosPorContinente.get(continente) < refuerzosUtilizadosPorContinente.get(continente)) {
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
            for (Pais pais : listaPaises) {
                AccionableRefuerzo refuerzo = new AccionableRefuerzo(pais);
                ClienteManager.getInstance().registrarSalida(refuerzo);
                ClienteManager.getInstance().getLogger().addLogItem(
                        new LogItem("Enviado actualizador refuerzo con " + listaPaises.toString()));
            }
            return true;
        }
        return false;
    }

    public boolean puedeReforzar(Pais pais) {
        int cantidadDisponible = 0;
        Continente continente = pais.getContinente();
        if (ejercitosPorContinente.containsKey(continente)) {
            cantidadDisponible = ejercitosPorContinente.get(continente);
            if (refuerzosUtilizadosPorContinente.containsKey(continente)) {
                cantidadDisponible -= refuerzosUtilizadosPorContinente.get(continente);
                if (cantidadDisponible < 0) {
                    cantidadDisponible = 0;
                }
            }
        }
//        if (refuerzosUtilizadosPorContinente.containsKey(continente)) {
//            cantidadDisponible -= refuerzosUtilizadosPorContinente.get(continente);
//            //TODO: puede ser necesario hacer que sea cero si queda negativo
//        }
        cantidadDisponible += calcularEjercitosLibresDisponibles();
        return cantidadDisponible > 0;

    }

    public boolean puedeReforzarConMisil(Pais pais) {
        if (!permitirMisiles) {
            return false;
        }
        int cantidadDisponible = 0;
        Continente continente = pais.getContinente();
        if (ejercitosPorContinente.containsKey(continente)) {
            cantidadDisponible = ejercitosPorContinente.get(continente);
        }
        if (refuerzosUtilizadosPorContinente.containsKey(continente)) {
            cantidadDisponible -= refuerzosUtilizadosPorContinente.get(continente);
            //TODO: puede ser necesario hacer que sea cero si queda negativo
        }
        cantidadDisponible += calcularEjercitosLibresDisponibles();
        return cantidadDisponible > 5;
    }

    private void registrarUsoPorContinente(Pais pais, int cantidad) {
        int cantidadAnterior = 0;
        Continente continente = pais.getContinente();
        if (refuerzosUtilizadosPorContinente.containsKey(continente)) {
            cantidadAnterior = refuerzosUtilizadosPorContinente.get(continente);
        }
        refuerzosUtilizadosPorContinente.put(continente, cantidad + cantidadAnterior);
    }

    public int calcularEjercitosLibresDisponibles() {
//        int libresUtilizados = 0;
//        for (Continente continente : ejercitosPorContinente.keySet()) {
//            if (refuerzosUtilizadosPorContinente.containsKey(continente)) {
//                int cantidad = refuerzosUtilizadosPorContinente.get(continente) - ejercitosPorContinente.get(continente);
//                if (cantidad > 0) {
//                    libresUtilizados += cantidad;
//                }
//            }
//        }
//        return cantidadEjercitos - libresUtilizados;
        int libresUtilizados = 0;
        for (Continente continente : refuerzosUtilizadosPorContinente.keySet()) {
            if (ejercitosPorContinente.containsKey(continente)) {
                int resta = refuerzosUtilizadosPorContinente.get(continente) - ejercitosPorContinente.get(continente);
                if (resta > 0) {
                    libresUtilizados += resta;
                }
            } else {
                libresUtilizados += refuerzosUtilizadosPorContinente.get(continente);
            }
        }
        return cantidadEjercitos - libresUtilizados;
    }

    public Map<Continente, Integer> calcularEjercitosPorContinenteDisponibles() {
        Map<Continente, Integer> ejercitosPorContinenteDisponibles = new HashMap<>(ejercitosPorContinente);
        for (Continente continente : refuerzosUtilizadosPorContinente.keySet()) {
            if (ejercitosPorContinente.containsKey(continente)) {
                ejercitosPorContinenteDisponibles.put(continente, ejercitosPorContinente.get(continente) - refuerzosUtilizadosPorContinente.get(continente));
            }
        }
        return ejercitosPorContinenteDisponibles;

    }
}
