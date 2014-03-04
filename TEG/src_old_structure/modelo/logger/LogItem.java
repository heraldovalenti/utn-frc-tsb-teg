/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author heril
 */
public class LogItem {
    
    private String msg;
    private Date timeStamp;
    
    public LogItem(String msg) {
        if (!msg.endsWith(".")) {
            msg += ".";
        }
        this.msg = msg;
        this.timeStamp = new Date();
    }
    
    public String getMsg() {
        return this.msg;
    }
    
    public Date getTimeStamp() {
        return this.timeStamp;
    }
    
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy'_'HH:mm:ss");
        res.append(format.format(timeStamp));
        res.append("# ");
        res.append(this.msg);
        return res.toString();
    }
    
}
