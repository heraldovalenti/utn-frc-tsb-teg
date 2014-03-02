/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import juego.estructura.Pais;

/**
 *
 * @author Emanuel
 */
public class Dados extends javax.swing.JInternalFrame {  
    /**
     * Creates new form Dados
     */
    public Dados() {
        initComponents();
        this.setSize(195,300);    
        deshabilitar();     
    }
    public void deshabilitar(){
        dadoDefensa1.setVisible(false);
        dadoDefensa2.setVisible(false);
        dadoDefensa3.setVisible(false);
        dadoDefensa4.setVisible(false);
        dadoAtaque1.setVisible(false);
        dadoAtaque2.setVisible(false);
        dadoAtaque3.setVisible(false);
        dadoAtaque4.setVisible(false); 
    }
    public void cargarDados(int [] ataque, int[] defensa){
           
        if(ataque == null || defensa == null)return;
        QuickSort.quicksort(ataque, 0, ataque.length-1);
        QuickSort.quicksort(defensa, 0, defensa.length-1);
        mostrarDados(ataque,true);
        mostrarDados(defensa, false);
       
    }
    private void mostrarDados(int [] dados, boolean esAtaque){
        Component[] componentes = jPanel1.getComponents();        
        int num = 0;
        for(int i = dados.length-1; i>=0; i--){
            String nombre = "";
            num++;
            if(esAtaque){
                        nombre = "dadoAtaque"+num;
                    }
                    else{
                        nombre = "dadoDefensa"+num;
            }           
            for(int j=0; j<componentes.length;j++){                
                if(componentes[j] instanceof JLabel) 
                { 
                    JLabel label = ((JLabel) componentes[j]);
                   
                    
                    if(label.getName()!=null && nombre.equalsIgnoreCase(label.getName())){
                          
                          String rutaImagen= "/imagenes/Dados/dado"+dados[i]+".png";
                          
                          label.setIcon(new javax.swing.ImageIcon(getClass().getResource(rutaImagen)));
                          label.setVisible(true);
                    }
                } 
               
            }
        }

    }
    public void simularTirada(int tamAtaque, int tamDefensa){   
        int [] ataque = simularDados(tamAtaque);
        int [] defensa = simularDados(tamDefensa);
        int i = 0;
        while(i<9){
            mostrarDados(ataque,true);
            mostrarDados(defensa,false);          
            i++;
        }
        
    }
     private int[] simularDados(int cant){
        int[] dados = new int[cant];
        for(int i = 0; i< cant; i++){          
            dados[i] = (int)Math.floor(Math.random()*6+1); 
        }
        return dados;
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        dadoAtaque2 = new javax.swing.JLabel();
        dadoAtaque1 = new javax.swing.JLabel();
        dadoAtaque4 = new javax.swing.JLabel();
        dadoAtaque3 = new javax.swing.JLabel();
        dadoDefensa3 = new javax.swing.JLabel();
        dadoDefensa4 = new javax.swing.JLabel();
        dadoDefensa2 = new javax.swing.JLabel();
        dadoDefensa1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setIconifiable(true);
        setTitle("DADOS");

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dadoAtaque2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Dados/dado2.png"))); // NOI18N
        dadoAtaque2.setName("dadoAtaque2"); // NOI18N
        jPanel1.add(dadoAtaque2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 101, -1, -1));

        dadoAtaque1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Dados/dado5.png"))); // NOI18N
        dadoAtaque1.setName("dadoAtaque1"); // NOI18N
        jPanel1.add(dadoAtaque1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 52, -1, -1));

        dadoAtaque4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Dados/dado3.png"))); // NOI18N
        dadoAtaque4.setName("dadoAtaque4"); // NOI18N
        jPanel1.add(dadoAtaque4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 197, -1, -1));

        dadoAtaque3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Dados/dado6.png"))); // NOI18N
        dadoAtaque3.setName("dadoAtaque3"); // NOI18N
        jPanel1.add(dadoAtaque3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        dadoDefensa3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Dados/dado2.png"))); // NOI18N
        dadoDefensa3.setName("dadoDefensa3"); // NOI18N
        jPanel1.add(dadoDefensa3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, -1, -1));

        dadoDefensa4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Dados/dado4.png"))); // NOI18N
        dadoDefensa4.setName("dadoDefensa4"); // NOI18N
        jPanel1.add(dadoDefensa4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 199, -1, -1));

        dadoDefensa2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Dados/dado3.png"))); // NOI18N
        dadoDefensa2.setName("dadoDefensa2"); // NOI18N
        jPanel1.add(dadoDefensa2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 101, -1, -1));

        dadoDefensa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Dados/dado1.png"))); // NOI18N
        dadoDefensa1.setName("dadoDefensa1"); // NOI18N
        jPanel1.add(dadoDefensa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 52, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Ataque");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 11, 84, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Defensa");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 11, 86, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dadoAtaque1;
    private javax.swing.JLabel dadoAtaque2;
    private javax.swing.JLabel dadoAtaque3;
    private javax.swing.JLabel dadoAtaque4;
    private javax.swing.JLabel dadoDefensa1;
    private javax.swing.JLabel dadoDefensa2;
    private javax.swing.JLabel dadoDefensa3;
    private javax.swing.JLabel dadoDefensa4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
