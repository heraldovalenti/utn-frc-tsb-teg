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
    
    private static Logger instance = null;
    private Logger() {
        
    }
    
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
    
    public void addLogItem(LogItem logItem) {
        System.out.println(logItem);
    }
    
}
