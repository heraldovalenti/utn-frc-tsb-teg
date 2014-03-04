/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logger;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author heril
 */
public class Logger {
    
    private Set<Loggeable> loggeables;
    
    public Logger() {
        loggeables = new HashSet<>();
    }
    
    public void agregarLoggeable(Loggeable l) {
        loggeables.add(l);
    }
    
    public void addLogItem(LogItem logItem) {
        for (Loggeable l : loggeables) {
            l.procesarLog(logItem);
        }
        System.out.println(logItem);
    }
    
}
