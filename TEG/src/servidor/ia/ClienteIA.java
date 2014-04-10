/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.ia;

import com.Accionable;
import com.cliente.AccionableFinTurno;
import com.servidor.AccionablePermitirAtaque;
import com.servidor.AccionablePermitirRefuerzo;
import ia.MotorIA;
import juego.estructura.GestorJugadores;
import logger.LogItem;
import servidor.ConexionCliente;
import servidor.ServerManager;

/**
 *
 * @author heril
 */
public class ClienteIA extends ConexionCliente {

    private boolean primeraRondaFinalizada;

    public ClienteIA() {
        super(-1);
        primeraRondaFinalizada = false;
    }

    @Override
    public void enviar(Accionable a) {
        /*
         * verificar si el accionable enviado debe ser provesado por la unidad
         * de IA e implementar cada funcion.
         */
        if (primeraRondaFinalizada) {
            if (a instanceof AccionablePermitirRefuerzo) {
                AccionablePermitirRefuerzo permitirRefuerzo = (AccionablePermitirRefuerzo) a;
                if (permitirRefuerzo.getJugadorServidor().getNroJugador() == this.getId()) {
                    MotorIA.turnoIA(permitirRefuerzo.getJugadorServidor(), permitirRefuerzo.getCantidadEjercitos(), permitirRefuerzo.getEjercitosPorContinente());
                }
            }
        } else {
            if (a instanceof AccionablePermitirRefuerzo) {
                AccionablePermitirRefuerzo permitirRefuerzo = (AccionablePermitirRefuerzo) a;
                if (permitirRefuerzo.getJugadorServidor().getNroJugador() == this.getId()) {
                    MotorIA.reforzarRondaInicial(permitirRefuerzo.getJugadorServidor(), permitirRefuerzo.getCantidadEjercitos());
                }
            }
            if (a instanceof AccionablePermitirAtaque) {
                AccionablePermitirAtaque permitirAtaque = (AccionablePermitirAtaque) a;
                if (permitirAtaque.getNroJugador() == this.getId()) {
                    MotorIA.atacar(GestorJugadores.obtenerPorNumero(permitirAtaque.getNroJugador()));
                    MotorIA.reagrupar(GestorJugadores.obtenerPorNumero(permitirAtaque.getNroJugador()));
                    AccionableFinTurno accionable = new AccionableFinTurno();
                    ServerManager.getInstance().registrarEntrada(accionable);
                    ServerManager.getInstance().getLogger().addLogItem(new LogItem("Turno de " + GestorJugadores.obtenerPorNumero(permitirAtaque.getNroJugador()).getNombre() + ": Fin de turno de IA."));
                    primeraRondaFinalizada = true;
                }
            }
        }
    }

    @Override
    public int disponible() {
        return 0;
    }

    @Override
    public Accionable recibir() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ClienteIA) {
            ClienteIA otro = (ClienteIA) obj;
            return (getId() == otro.getId());
        }
        if (obj instanceof ConexionCliente) {
            ConexionCliente otro = (ConexionCliente) obj;
            return (getId() == otro.getId());
        }
        return false;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }
}
