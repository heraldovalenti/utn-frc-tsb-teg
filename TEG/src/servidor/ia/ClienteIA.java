/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.ia;

import com.Accionable;
import servidor.ConexionCliente;

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
