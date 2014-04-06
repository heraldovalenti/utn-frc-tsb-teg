/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.mecanicas.turno;

import com.cliente.AccionableAtaque;
import com.cliente.AccionableCanjePorEjercitos;
import com.cliente.AccionableCanjePorMisil;
import com.cliente.AccionableCanjeTarjetas;
import com.cliente.AccionableLanzarMisil;
import com.cliente.AccionableMovimiento;
import cliente.ClienteManager;
import cliente.control.ControlRefuerzo;
import com.cliente.AccionableSolicitarTarjeta;
import java.util.List;
import juego.Juego;
import juego.estructura.Canjeable;
import juego.estructura.GestorPaises;
import juego.estructura.GestorTarjetas;
import juego.estructura.Jugador;
import juego.estructura.Pais;
import juego.mecanicas.ataque.ControlAtaque;
import juego.mecanicas.movimiento.ControlMovimiento;

/**
 *
 * @author Daniel
 */
public class GestorTurno {

    public static final int FUERA_TURNO = 0;
    public static final int ETAPA_INCORPORAR_EJERCITOS = 1;
    public static final int ETAPA_ATACAR = 2;
    public static final int ETAPA_REAGRUPAR = 3;
    public static final int ETAPA_SOLICITAR_TARJETA = 4;

    public static final int ACCION_ATACAR = 1;
    public static final int ACCION_LANZAR_MISIL = 2;
    public static final int ACCION_SOLICITAR_TARJETA = 3;
    public static final int ACCION_INCORPORAR_EJERCITOS = 4;
    public static final int ACCION_REAGRUPAR = 5;
    public static final int ACCION_CANJEAR_TARJETA = 6;
    public static final int ACCION_CANJEAR_MISIL_POR_EJERCITO = 7;
    public static final int ACCION_CANJEAR_EJERCITO_POR_MISIL = 8;

    public static int etapaActual = 0;
    private static int paisesConquistados = 0;
    private static boolean canjeRealizado = false;
    private static boolean tarjetaSolicitada = false;

    private static boolean[][] permisos;

    private static ControlRefuerzo refuerzoActual;

    public static void crearPermisos() {
        permisos = new boolean[5][9];

        permisos[ETAPA_INCORPORAR_EJERCITOS][ACCION_INCORPORAR_EJERCITOS] = true;
        permisos[ETAPA_INCORPORAR_EJERCITOS][ACCION_CANJEAR_EJERCITO_POR_MISIL] = true;
        permisos[ETAPA_INCORPORAR_EJERCITOS][ACCION_CANJEAR_MISIL_POR_EJERCITO] = true;
        permisos[ETAPA_INCORPORAR_EJERCITOS][ACCION_CANJEAR_TARJETA] = true;

        permisos[ETAPA_ATACAR][ACCION_ATACAR] = true;
        permisos[ETAPA_ATACAR][ACCION_LANZAR_MISIL] = true;

        permisos[ETAPA_REAGRUPAR][ACCION_REAGRUPAR] = true;
    }

    public static void atacar(Pais origen, Pais destino) {
        if (accionPermitida(ACCION_ATACAR)) {
            ControlAtaque control = new ControlAtaque(origen, destino);
            if (control.ataqueValido()) {
                AccionableAtaque ataque = new AccionableAtaque(origen, destino);
                ClienteManager.getInstance().registrarSalida(ataque);
                etapaActual = ETAPA_ATACAR;
            }
        }
    }

//    public static void colocarEjercitos(Pais pais, int cantidadEjercitos, int cantidadMisiles) {
//        if (accionPermitida(ACCION_INCORPORAR_EJERCITOS)) {
//            AccionableRefuerzo refuerzo = new AccionableRefuerzo(pais, cantidadEjercitos, cantidadMisiles);
//            ClienteManager.getInstance().registrarSalida(refuerzo);
//        }
//    }
    public static void canjearEjercitosPorMisil(Pais pais, int cantidadMisiles) {
        if (accionPermitida(ACCION_CANJEAR_EJERCITO_POR_MISIL)) {
            if (pais.getCantidadEjercitos() > 6 * cantidadMisiles) {
                AccionableCanjePorMisil canje = new AccionableCanjePorMisil(pais, cantidadMisiles);
                ClienteManager.getInstance().registrarSalida(canje);
            }
        }
    }

    public static void canjearMisilPorEjercito(Pais pais, int cantidadMisiles) {
        if (accionPermitida(ACCION_CANJEAR_MISIL_POR_EJERCITO)) {
            if (pais.getCantidadMisiles() >= cantidadMisiles) {
                AccionableCanjePorEjercitos canje = new AccionableCanjePorEjercitos(pais, cantidadMisiles);
                ClienteManager.getInstance().registrarSalida(canje);
            }
        }
    }

    public static void reagruparEjercitos(Pais origen, Pais destino, int cantidadEjercitos, int cantidadMisiles) {
        if (accionPermitida(ACCION_REAGRUPAR)) {
            ControlMovimiento control = new ControlMovimiento(origen, destino, cantidadEjercitos, cantidadMisiles);
            if (control.movimientoValido()) {
                AccionableMovimiento movimiento = new AccionableMovimiento(origen, destino, cantidadEjercitos, cantidadMisiles);
                ClienteManager.getInstance().registrarSalida(movimiento);
                etapaActual = ETAPA_REAGRUPAR;
            }
        }
    }

    public static void lanzarMisil(Pais origen, Pais destino) {
        if (accionPermitida(ACCION_LANZAR_MISIL)) {
            if (origen.getCantidadMisiles() > destino.getCantidadMisiles() && GestorPaises.calcularDistancia(origen, destino) <= 3) {
                AccionableLanzarMisil lanzamiento = new AccionableLanzarMisil(origen, destino);
                ClienteManager.getInstance().registrarSalida(lanzamiento);
                etapaActual = ETAPA_ATACAR;
            }
        }
    }

    public static void canjearTarjetas(Jugador jugador, List<Canjeable> listaTarjetas) {
        if (accionPermitida(ACCION_CANJEAR_TARJETA) && !canjeRealizado) {
            if (GestorTarjetas.canjeValido(jugador, listaTarjetas)) {
                AccionableCanjeTarjetas canje = new AccionableCanjeTarjetas(jugador, listaTarjetas);
                ClienteManager.getInstance().registrarSalida(canje);
                canjeRealizado = true;
            }
        }
    }

    public static void solicitarTarjeta(Jugador jugador) {
        if (accionPermitida(ACCION_SOLICITAR_TARJETA) && !tarjetaSolicitada && jugador.getCantidadTarjetasPais() < 6 && Juego.getInstancia().getSituacion().puedeObtenerTarjetaPais(jugador)) {
            int canjesRealizados = jugador.getCantidadCanjes();
            boolean res = false;
            if (canjesRealizados < 3 && paisesConquistados > 0) {
                res = true;
            }
            if (canjesRealizados > 3 && paisesConquistados > 1) {
                res = true;
            }
            if (res) {
                AccionableSolicitarTarjeta solicitar = new AccionableSolicitarTarjeta(jugador);
                ClienteManager.getInstance().registrarSalida(solicitar);
                tarjetaSolicitada = true;
            }
        }
    }

    public static boolean accionPermitida(int accion) {
        crearPermisos();
        return permisos[etapaActual][accion];
    }

    public static void finTurno() {
        etapaActual = FUERA_TURNO;
        paisesConquistados = 0;
        canjeRealizado = false;
        tarjetaSolicitada = false;
    }

    public static void aumentarContadorPaisesConquistados() {
        paisesConquistados++;
    }

    public static ControlRefuerzo getRefuerzoActual() {
        return refuerzoActual;
    }
}
