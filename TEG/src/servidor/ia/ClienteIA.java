/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.ia;

import com.Accionable;
import com.servidor.AccionablePermitirAtaque;
import com.servidor.AccionablePermitirRefuerzo;
import ia.MotorIA;
import juego.estructura.GestorJugadores;
import servidor.ConexionCliente;

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
         * verificar si el accionable enviado debe ser procesado por la unidad
         * de IA e implementar cada funcion.
         */
        if (a instanceof AccionablePermitirRefuerzo) {
            AccionablePermitirRefuerzo permitirRefuerzo = (AccionablePermitirRefuerzo) a;
            if (permitirRefuerzo.getJugadorServidor().getNroJugador() == this.getId()) {
                MotorIA.turnoIA(permitirRefuerzo.getJugadorServidor(), permitirRefuerzo.getCantidadEjercitos(), permitirRefuerzo.getEjercitosPorContinente());
            }
        }
        if (a instanceof AccionablePermitirAtaque) {
            AccionablePermitirAtaque permitirAtaque = (AccionablePermitirAtaque) a;
            if (permitirAtaque.getNroJugador() == this.getId()) {
                MotorIA.turnoIA(GestorJugadores.obtenerPorNumero(permitirAtaque.getNroJugador()), 0, null);
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
