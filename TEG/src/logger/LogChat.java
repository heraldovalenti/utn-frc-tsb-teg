/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author heril
 */
public class LogChat extends LogItem {
    
    private String jugador;
    
    public LogChat(String mensaje, String aliasJugador, Date timeStamp) {
        super.msg = mensaje;
        super.timeStamp = timeStamp;
        this.jugador = aliasJugador;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy'_'HH:mm:ss");
        res.append(format.format(super.timeStamp));
        res.append("# ");
        res.append(this.jugador);
        res.append(": ");
        res.append(super.msg);
        return res.toString();
    }
    
    
    
}
