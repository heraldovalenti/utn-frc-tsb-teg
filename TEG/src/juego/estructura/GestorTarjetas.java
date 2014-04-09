/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.estructura;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class GestorTarjetas {

    public static final int[] ARMA = {0, 1};
    public static final int[] AVION = {1, 1};
    public static final int[] SOLDADO = {2, 1};
    public static final int[] ANCLA = {3, 1};
    public static final int[] AVION_SOLDADO_ANCLA = {1 + 2 + 3, 3};
    public static final int[] SOLDADO_ANCLA = {2 + 3, 1};
    public static final int[] AVION_ANCLA = {1 + 3, 1};

    private static List<TarjetaPais> listaTarjetasPais = new ArrayList(72);
    private static List<TarjetaContinente> listaTarjetasContinentes = new ArrayList(7);
    private static List<TarjetaPais> listaTarjetasDevultas = new ArrayList<>(72);

    public static void inicializarGestor() {
        crearTarjetas();
        Collections.shuffle(listaTarjetasPais);
    }

    public static void crearTarjetas() {
        listaTarjetasPais.add(new TarjetaPais(1, GestorPaises.getPais(GestorPaises.ALASKA), ARMA));
        listaTarjetasPais.add(new TarjetaPais(2, GestorPaises.getPais(GestorPaises.ALBANIA), ARMA));
        listaTarjetasPais.add(new TarjetaPais(3, GestorPaises.getPais(GestorPaises.ALEMANIA), ARMA));
        listaTarjetasPais.add(new TarjetaPais(4, GestorPaises.getPais(GestorPaises.ANGOLA), ARMA));
        listaTarjetasPais.add(new TarjetaPais(5, GestorPaises.getPais(GestorPaises.ARABIA), AVION));
        listaTarjetasPais.add(new TarjetaPais(6, GestorPaises.getPais(GestorPaises.ARGENTINA), AVION_SOLDADO_ANCLA));
        listaTarjetasPais.add(new TarjetaPais(7, GestorPaises.getPais(GestorPaises.AUSTRALIA), AVION));
        listaTarjetasPais.add(new TarjetaPais(8, GestorPaises.getPais(GestorPaises.BIELORRUSIA), AVION));
        listaTarjetasPais.add(new TarjetaPais(9, GestorPaises.getPais(GestorPaises.BOLIVIA), AVION));
        listaTarjetasPais.add(new TarjetaPais(10, GestorPaises.getPais(GestorPaises.BRASIL), AVION));
        listaTarjetasPais.add(new TarjetaPais(11, GestorPaises.getPais(GestorPaises.CALIFORNIA), AVION));
        listaTarjetasPais.add(new TarjetaPais(12, GestorPaises.getPais(GestorPaises.CANADA), AVION));
        listaTarjetasPais.add(new TarjetaPais(13, GestorPaises.getPais(GestorPaises.COLOMBIA), AVION));
        listaTarjetasPais.add(new TarjetaPais(14, GestorPaises.getPais(GestorPaises.COREA), AVION));
        listaTarjetasPais.add(new TarjetaPais(15, GestorPaises.getPais(GestorPaises.CROACIA), AVION));
        listaTarjetasPais.add(new TarjetaPais(16, GestorPaises.getPais(GestorPaises.CUBA), AVION));
        listaTarjetasPais.add(new TarjetaPais(17, GestorPaises.getPais(GestorPaises.CHECHENIA), AVION_SOLDADO_ANCLA));
        listaTarjetasPais.add(new TarjetaPais(18, GestorPaises.getPais(GestorPaises.CHICAGO), AVION));
        listaTarjetasPais.add(new TarjetaPais(19, GestorPaises.getPais(GestorPaises.CHILE), AVION));
        listaTarjetasPais.add(new TarjetaPais(20, GestorPaises.getPais(GestorPaises.CHINA), AVION));
        listaTarjetasPais.add(new TarjetaPais(21, GestorPaises.getPais(GestorPaises.CHUKCHI), AVION));
        listaTarjetasPais.add(new TarjetaPais(22, GestorPaises.getPais(GestorPaises.IRLANDA), AVION));
        listaTarjetasPais.add(new TarjetaPais(23, GestorPaises.getPais(GestorPaises.ETIOPIA), AVION));
        listaTarjetasPais.add(new TarjetaPais(24, GestorPaises.getPais(GestorPaises.EGIPTO), AVION));
        listaTarjetasPais.add(new TarjetaPais(25, GestorPaises.getPais(GestorPaises.EL_SALVADOR), AVION));
        listaTarjetasPais.add(new TarjetaPais(26, GestorPaises.getPais(GestorPaises.ESPANA), AVION));
        listaTarjetasPais.add(new TarjetaPais(27, GestorPaises.getPais(GestorPaises.FILIPINAS), AVION));
        listaTarjetasPais.add(new TarjetaPais(28, GestorPaises.getPais(GestorPaises.FINLANDIA), ANCLA));
        listaTarjetasPais.add(new TarjetaPais(29, GestorPaises.getPais(GestorPaises.FRANCIA), ANCLA));
        listaTarjetasPais.add(new TarjetaPais(30, GestorPaises.getPais(GestorPaises.GRAN_BRETANA), ANCLA));
        listaTarjetasPais.add(new TarjetaPais(31, GestorPaises.getPais(GestorPaises.GROENLANDIA), ANCLA));
        listaTarjetasPais.add(new TarjetaPais(32, GestorPaises.getPais(GestorPaises.HONDURAS), ANCLA));
        listaTarjetasPais.add(new TarjetaPais(33, GestorPaises.getPais(GestorPaises.INDIA), ANCLA));
        listaTarjetasPais.add(new TarjetaPais(34, GestorPaises.getPais(GestorPaises.IRAK), ANCLA));
        listaTarjetasPais.add(new TarjetaPais(35, GestorPaises.getPais(GestorPaises.IRAN), ANCLA));
        listaTarjetasPais.add(new TarjetaPais(36, GestorPaises.getPais(GestorPaises.ISLANDIA), ANCLA));
        listaTarjetasPais.add(new TarjetaPais(37, GestorPaises.getPais(GestorPaises.ISLA_VICTORIA), ANCLA));
        listaTarjetasPais.add(new TarjetaPais(38, GestorPaises.getPais(GestorPaises.ISRAEL), ANCLA));
        listaTarjetasPais.add(new TarjetaPais(39, GestorPaises.getPais(GestorPaises.ITALIA), ANCLA));
        listaTarjetasPais.add(new TarjetaPais(40, GestorPaises.getPais(GestorPaises.JAMAICA), ANCLA));
        listaTarjetasPais.add(new TarjetaPais(41, GestorPaises.getPais(GestorPaises.JAPON), ANCLA));
        listaTarjetasPais.add(new TarjetaPais(42, GestorPaises.getPais(GestorPaises.KAMTCHATKA), ANCLA));
        listaTarjetasPais.add(new TarjetaPais(43, GestorPaises.getPais(GestorPaises.FLORIDA), ANCLA));
        listaTarjetasPais.add(new TarjetaPais(44, GestorPaises.getPais(GestorPaises.LABRADOR), ANCLA));
        listaTarjetasPais.add(new TarjetaPais(45, GestorPaises.getPais(GestorPaises.LAS_VEGAS), AVION_SOLDADO_ANCLA));
        listaTarjetasPais.add(new TarjetaPais(46, GestorPaises.getPais(GestorPaises.MADAGASCAR), ANCLA));
        listaTarjetasPais.add(new TarjetaPais(47, GestorPaises.getPais(GestorPaises.MALASIA), ANCLA));
        listaTarjetasPais.add(new TarjetaPais(48, GestorPaises.getPais(GestorPaises.MAURITANIA), AVION_SOLDADO_ANCLA));
        listaTarjetasPais.add(new TarjetaPais(49, GestorPaises.getPais(GestorPaises.MEXICO), ANCLA));
        listaTarjetasPais.add(new TarjetaPais(50, GestorPaises.getPais(GestorPaises.NICARAGUA), ANCLA));
        listaTarjetasPais.add(new TarjetaPais(51, GestorPaises.getPais(GestorPaises.NIGERIA), SOLDADO));
        listaTarjetasPais.add(new TarjetaPais(52, GestorPaises.getPais(GestorPaises.NORUEGA), SOLDADO));
        listaTarjetasPais.add(new TarjetaPais(53, GestorPaises.getPais(GestorPaises.NUEVA_YORK), SOLDADO));
        listaTarjetasPais.add(new TarjetaPais(54, GestorPaises.getPais(GestorPaises.NUEVA_ZELANDIA), SOLDADO));
        listaTarjetasPais.add(new TarjetaPais(55, GestorPaises.getPais(GestorPaises.OREGON), SOLDADO));
        listaTarjetasPais.add(new TarjetaPais(56, GestorPaises.getPais(GestorPaises.PARAGUAY), SOLDADO));
        listaTarjetasPais.add(new TarjetaPais(57, GestorPaises.getPais(GestorPaises.UCRANIA), AVION_SOLDADO_ANCLA));
        listaTarjetasPais.add(new TarjetaPais(58, GestorPaises.getPais(GestorPaises.PORTUGAL), SOLDADO));
        listaTarjetasPais.add(new TarjetaPais(59, GestorPaises.getPais(GestorPaises.RUSIA), SOLDADO));
        listaTarjetasPais.add(new TarjetaPais(60, GestorPaises.getPais(GestorPaises.SAHARA), SOLDADO));
        listaTarjetasPais.add(new TarjetaPais(61, GestorPaises.getPais(GestorPaises.SERBIA), SOLDADO));
        listaTarjetasPais.add(new TarjetaPais(62, GestorPaises.getPais(GestorPaises.SIBERIA), SOLDADO));
        listaTarjetasPais.add(new TarjetaPais(63, GestorPaises.getPais(GestorPaises.SUDAFRICA), SOLDADO));
        listaTarjetasPais.add(new TarjetaPais(64, GestorPaises.getPais(GestorPaises.SUMATRA), SOLDADO));
        listaTarjetasPais.add(new TarjetaPais(65, GestorPaises.getPais(GestorPaises.TASMANIA), SOLDADO));
        listaTarjetasPais.add(new TarjetaPais(66, GestorPaises.getPais(GestorPaises.TERRANOVA), SOLDADO));
        listaTarjetasPais.add(new TarjetaPais(67, GestorPaises.getPais(GestorPaises.TONGA), SOLDADO));
        listaTarjetasPais.add(new TarjetaPais(68, GestorPaises.getPais(GestorPaises.TURQUIA), SOLDADO));
        listaTarjetasPais.add(new TarjetaPais(69, GestorPaises.getPais(GestorPaises.POLONIA), SOLDADO));
        listaTarjetasPais.add(new TarjetaPais(70, GestorPaises.getPais(GestorPaises.URUGUAY), SOLDADO));
        listaTarjetasPais.add(new TarjetaPais(71, GestorPaises.getPais(GestorPaises.VENEZUELA), SOLDADO));
        listaTarjetasPais.add(new TarjetaPais(72, GestorPaises.getPais(GestorPaises.VIETNAM), SOLDADO));
        listaTarjetasContinentes.add(new TarjetaContinente(1, GestorContinentes.getContinente(GestorContinentes.AFRICA), SOLDADO_ANCLA));
        listaTarjetasContinentes.add(new TarjetaContinente(2, GestorContinentes.getContinente(GestorContinentes.AMERICA_CENTRAL), AVION));
        listaTarjetasContinentes.add(new TarjetaContinente(3, GestorContinentes.getContinente(GestorContinentes.AMERICA_NORTE), AVION_SOLDADO_ANCLA));
        listaTarjetasContinentes.add(new TarjetaContinente(4, GestorContinentes.getContinente(GestorContinentes.AMERICA_SUR), AVION_ANCLA));
        listaTarjetasContinentes.add(new TarjetaContinente(5, GestorContinentes.getContinente(GestorContinentes.ASIA), AVION_SOLDADO_ANCLA));
        listaTarjetasContinentes.add(new TarjetaContinente(6, GestorContinentes.getContinente(GestorContinentes.EUROPA), AVION_SOLDADO_ANCLA));
        listaTarjetasContinentes.add(new TarjetaContinente(7, GestorContinentes.getContinente(GestorContinentes.OCEANIA), SOLDADO));
    }

    public static List getListaTarjetasPais() {
        return listaTarjetasPais;
    }

    public static List getListaTarjetasContinentes() {
        return listaTarjetasContinentes;
    }

    public static int calcularEjercitosAdicionales(int numeroCanje) {
        if (numeroCanje == 1) {
            return 6;
        } else {
            return numeroCanje * 5;
        }
    }

    public static boolean canjeValido(Jugador jugador, List<Canjeable> listaTarjetas) {
        int valor = 0;
        int peso = 0;
        boolean hayComodines = false;
        for (Canjeable tarjeta : listaTarjetas) {
            if (tarjeta instanceof TarjetaContinente) {
                TarjetaContinente tarjetaContinente = (TarjetaContinente) tarjeta;
                if (tarjetaContinente.fueUsada(jugador)) {
                    return false;
                }
            }
            valor += tarjeta.getValor();
            peso += tarjeta.getPeso();
            if (tarjeta.esComodin()) {
                hayComodines = true;
            }
        }
        if (hayComodines) {
            return (peso >= 3);
        } else {
            return ((valor == 3 || valor == 6 || valor == 9) && peso == 3);
        }
    }

    public static TarjetaPais solicitarTarjetaPais() {
        if (listaTarjetasPais.size() < 0) {
            renovarMazo();
        }
        return listaTarjetasPais.remove(0);
    }

    public static void devolverTarjeta(TarjetaPais tarjeta) {
        listaTarjetasDevultas.add(tarjeta);
    }

    public static void renovarMazo() {
        listaTarjetasPais.addAll(listaTarjetasDevultas);
        listaTarjetasDevultas.clear();
        Collections.shuffle(listaTarjetasPais);
    }

    public static boolean yaUsada(Jugador jugador, TarjetaContinente tarjeta) {
        return tarjeta.fueUsada(jugador);
    }

    public static TarjetaPais getTarjetaPais(int nroTarjeta) {
        for (TarjetaPais tarjeta : listaTarjetasPais) {
            if (tarjeta.getNroTarjeta() == nroTarjeta) {
                return tarjeta;
            }
        }
        for (TarjetaPais tarjeta : listaTarjetasDevultas) {
            if (tarjeta.getNroTarjeta() == nroTarjeta) {
                return tarjeta;
            }
        }
        return null;
    }

    public static TarjetaContinente getTarjetaContinente(int nrotarjeta) {
        return listaTarjetasContinentes.get(nrotarjeta - 1);
    }

    public static TarjetaContinente obtenerPorContinente(Continente continente) {
        for (TarjetaContinente tarjeta : listaTarjetasContinentes) {
            if (tarjeta.getContinente().equals(continente)) {
                return tarjeta;
            }
        }
        return null;
    }
}
