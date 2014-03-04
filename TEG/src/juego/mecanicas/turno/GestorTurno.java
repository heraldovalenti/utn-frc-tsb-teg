/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.mecanicas.turno;

import cliente.AccionableAtaque;
import cliente.AccionableCanjePorEjercitos;
import cliente.AccionableCanjePorMisil;
import cliente.AccionableCanjeTarjetas;
import cliente.AccionableLanzarMisil;
import cliente.AccionableMovimiento;
import cliente.AccionableRefuerzo;
import java.util.List;
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

    public static int etapaActual;

    private static boolean[][] permisos;

    public static void crearPermisos() {
        permisos = new boolean[5][8];

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
            }
        }
    }

    public static void colocarEjercitos(Pais pais, int cantidadEjercitos, int cantidadMisiles) {
        if (accionPermitida(ACCION_INCORPORAR_EJERCITOS)) {
            AccionableRefuerzo refuerzo = new AccionableRefuerzo(pais, cantidadEjercitos, cantidadMisiles);
        }
    }

    public static void canjearEjercitosPorMisil(Pais pais, int cantidadMisiles) {
        if (accionPermitida(ACCION_CANJEAR_EJERCITO_POR_MISIL)) {
            if (pais.getCantidadEjercitos() > 6 * cantidadMisiles) {
                AccionableCanjePorMisil canje = new AccionableCanjePorMisil(pais, cantidadMisiles);
            }
        }
    }

    public static void canjearMisilPorEjercito(Pais pais, int cantidadMisiles) {
        if (accionPermitida(ACCION_CANJEAR_MISIL_POR_EJERCITO)) {
            if (pais.getCantidadMisiles() >= cantidadMisiles) {
                AccionableCanjePorEjercitos canje = new AccionableCanjePorEjercitos(pais, cantidadMisiles);
            }
        }

    }

    public static void reagruparEjercitos(Pais origen, Pais destino, int cantidadEjercitos, int cantidadMisiles) {
        if (accionPermitida(ACCION_REAGRUPAR)) {
            ControlMovimiento control = new ControlMovimiento(origen, destino, cantidadEjercitos, cantidadMisiles);
            if (control.movimientoValido()) {
                AccionableMovimiento movimiento = new AccionableMovimiento(origen, destino, cantidadEjercitos, cantidadMisiles);
            }
        }
    }

    public static void lanzarMisil(Pais origen, Pais destino) {
        if (accionPermitida(ACCION_LANZAR_MISIL)) {
            if (origen.getCantidadMisiles() > destino.getCantidadMisiles() && GestorPaises.calcularDistancia(origen, destino) <= 3) {
                AccionableLanzarMisil lanzamiento = new AccionableLanzarMisil(origen, destino);
            }
        }
    }

    public static void canjearTarjetas(Jugador jugador, List<Canjeable> listaTarjetas) {
        if (accionPermitida(ACCION_CANJEAR_TARJETA)) {
            if (GestorTarjetas.canjeValido(listaTarjetas)) {
                AccionableCanjeTarjetas canje = new AccionableCanjeTarjetas(jugador, listaTarjetas);
            }
        }
    }

    public static boolean accionPermitida(int accion) {
        return permisos[etapaActual][accion];
    }

}
