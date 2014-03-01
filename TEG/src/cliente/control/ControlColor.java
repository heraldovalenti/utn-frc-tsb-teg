/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.control;

import cliente.ClienteManager;
import logger.LogItem;

/**
 *
 * @author heril
 */
public class ControlColor {
    
    public void solicitarColor() {
        ClienteManager.getInstance().getLogger().addLogItem(new LogItem("Solicitando color..."));
    }
    
}
