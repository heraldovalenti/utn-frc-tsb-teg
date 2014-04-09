/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.ia;

import com.Accionable;
import com.servidor.AccionablePermitirRefuerzo;
import ia.MotorIA;
import logger.LogItem;
import servidor.ConexionCliente;
import servidor.ServerManager;

/**
 *
 * @author heril
 */
public class ClienteIA extends ConexionCliente {
        
    public ClienteIA() {
        super(-1);
    }

    @Override
    public void enviar(Accionable a) {
        /*
         * verificar si el accionable enviado debe ser provesado por la unidad
         * de IA e implementar cada funcion.
        */
        if (a instanceof AccionablePermitirRefuerzo) {
            AccionablePermitirRefuerzo permitirRefuerzo = (AccionablePermitirRefuerzo)a;
            if (permitirRefuerzo.getJugadorServidor().getNroJugador() == this.getId()) {
                ServerManager.getInstance().getLogger().addLogItem(new LogItem("Turno de IA: refuerzo de ronda inicial, realizando refuerzos..."));
                MotorIA.reforzarRondaInicial(permitirRefuerzo.getJugadorServidor(), permitirRefuerzo.getCantidadEjercitos());
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
            ConexionCliente otro = (ConexionCliente)obj;
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
