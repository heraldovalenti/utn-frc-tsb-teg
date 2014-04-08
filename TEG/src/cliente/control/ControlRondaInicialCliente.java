/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.control;

import Interfaces.FachadaInterfacePrincipal;
import cliente.ClienteManager;
import com.cliente.AccionableRefuerzoRondaInicial;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import juego.estructura.GestorJugadores;
import juego.estructura.Jugador;
import juego.estructura.Pais;

/**
 *
 * @author heril
 */
public class ControlRondaInicialCliente {

    private static ControlRondaInicialCliente instance = null;

    public static ControlRondaInicialCliente getInstance() {
        if (instance == null) {
            instance = new ControlRondaInicialCliente();
        }
        return instance;
    }

    private ControlRondaInicialCliente() {
    }
    private int cantidadEjercitos;
    private int cantidadEjercitosAgregados;
    private Map<Pais, Integer> ejercitosAgregados;

    /**
     * Ejecuta el control de la ronda inicial en el cliente. Si el jugador que
     * tiene el turno es el local, informa de la situación y comienza el control
     * de agregar ejercitos en paises, en caso contrario, informa que el turno
     * es de otro jugador.
     *
     * @param idJugador identificador del jugador que tiene el turno.
     * @param cantidadEjercitos la cantidad de ejercitos que se pueden agregar.
     */
    public void ejecutarRondaInicial(int idJugador, int cantidadEjercitos) {
        if (ClienteManager.getInstance().esJugadorLocal(idJugador)) {
            this.cantidadEjercitos = cantidadEjercitos;
            ejercitosAgregados = new HashMap<>();
            cantidadEjercitosAgregados = 0;
            Set<Pais> paisesJugador = GestorJugadores.getJugadorLocal().getConjuntoPaises();
            for (Pais p : paisesJugador) {
                ejercitosAgregados.put(p, 0);
            }
            FachadaInterfacePrincipal.informarRondaInicial();
        } else {
            Jugador jugadorDeTurno = GestorJugadores.obtenerPorNumero(idJugador);
            FachadaInterfacePrincipal.informarRondaInicial(jugadorDeTurno);
        }
    }

    /**
     * Agrega un ejercito en el pais indicado. Solo se agregara el ejercito si
     * el pais indicado esta en poder del jugador y ademas quedan ejercitos para
     * agregarse, en caso contrario no se realiza ninguna acción.
     *
     * @param pais el pais en el que se debe agregar el ejercito.
     */
    public void agregarEjercito(Pais pais) {
        if (ejercitosAgregados.containsKey(pais)
                && cantidadEjercitosAgregados < cantidadEjercitos) {
            int cantidadEjercitosActualEnPais = ejercitosAgregados.get(pais);
            cantidadEjercitosActualEnPais++;
            ejercitosAgregados.put(pais, cantidadEjercitosActualEnPais);
            cantidadEjercitosAgregados++;
        }
    }

    /**
     * Quita un ejercito del pais indicado. Solo se quitara el ejercito del pais
     * indicado si se agrego previamente en la ronda algun ejercito en dicho
     * pais, de lo contrario no se realiza ninguna acción.
     *
     * @param pais el pais de donde se debe quitar un ejercito.
     */
    public void quitarEjercito(Pais pais) {
        int cantidadEjercitosActualEnPais = ejercitosAgregados.get(pais);
        if (ejercitosAgregados.containsKey(pais)
                && cantidadEjercitosActualEnPais > 0
                && cantidadEjercitosAgregados > 0) {
            cantidadEjercitosActualEnPais--;
            ejercitosAgregados.put(pais, cantidadEjercitosActualEnPais);
            cantidadEjercitosAgregados--;
        }
    }

    /**
     * Metodo para verificar si quedan ejercitos para agregarse.
     *
     * @return true si quedan ejercitos para agregarse todavia, false en otro
     * caso.
     */
    public boolean quedanEjercitosPorAgregarse() {
        return cantidadEjercitos > cantidadEjercitosAgregados;
    }

    /**
     * Finaliza la ronda inicial de agregar ejercitos si es posible, es decir,
     * si se han agregado todos los ejercitos disponibles.
     */
    public void finalizarRondaInicial() {
        if (quedanEjercitosPorAgregarse()) {
            return;
        }
        List<Pais> paisesAfectados = new ArrayList<>();
        for (Entry<Pais,Integer> entradaPais : ejercitosAgregados.entrySet()) {
            int cantidadEjercitosAgregadosEnPais = entradaPais.getValue().intValue();
            Pais paisAfectado = entradaPais.getKey();
            if (cantidadEjercitosAgregadosEnPais > 0) {
                paisAfectado.añadirEjercitos(cantidadEjercitosAgregadosEnPais);
                paisesAfectados.add(paisAfectado);
            }
        }
        AccionableRefuerzoRondaInicial accionable = new AccionableRefuerzoRondaInicial(paisesAfectados);
        ClienteManager.getInstance().registrarSalida(accionable);
        FachadaInterfacePrincipal.informarFinRondaInicial();
    }

    public int getCantidadEjercitos() {
        return cantidadEjercitos;
    }
}
