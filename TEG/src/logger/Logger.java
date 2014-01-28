/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logger;

/**
 *
 * @author heril
 */
public class Logger {
    
    public Logger() {
        
    }
    
    private void notificar(String cadenaIdentificacionEvento) {
        System.out.println(cadenaIdentificacionEvento);
    }
    
    public void addLogItem(LogItem logItem) {
        System.out.println(logItem);
    }
    
}
