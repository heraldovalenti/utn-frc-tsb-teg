/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teg;

/**
 *
 * @author heril
 */
public class TEG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        cliente.ConexionServidor con = new cliente.ConexionServidor();
        con.start();
        
        cliente.SalaEspera sala = new cliente.SalaEspera();
        sala.setLocationRelativeTo(null);
        System.out.println("asdasd");
        sala.setVisible(true);
    }
}
