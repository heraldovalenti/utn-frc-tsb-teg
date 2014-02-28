/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teg;

import cliente.ClienteManager;

/**
 *
 * @author heril
 */
public class TEG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClienteManager.getInstance().init();
    }
}
