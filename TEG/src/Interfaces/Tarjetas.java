/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import juego.estructura.Canjeable;
import juego.estructura.TarjetaPais;


/**
 *
 * @author Emanuel
 */
public class Tarjetas extends javax.swing.JInternalFrame {
    private List<Canjeable> tarjetas;  
    private JMenuItem menu;
    private JButton boton;
    private List<Canjeable> tarjetasSeleccionadas = new ArrayList<Canjeable>();    
    private boolean canjePermitido;
    private InterfacePrincipal padre;
    /**
     * Creates new form Tarjetas
     */
    public Tarjetas(List<Canjeable> tarjetas, JMenuItem menu, JButton boton, boolean canjePermitido, InterfacePrincipal padre) {
        initComponents();
        this.tarjetas = (ArrayList)tarjetas;
        limpiarLabels();
        this.boton = boton;
        this.menu = menu;
        deshabilitarChecks();
        btnCanjear.setEnabled(false);
        this.canjePermitido = canjePermitido;
        this.padre = padre;
        cargarTarjetas();   
  
    }
    public void actualizarTarjetas(List<Canjeable> tarjetas){
        this.tarjetas = (ArrayList)tarjetas;
        cargarTarjetas();
    }
    private void cargarTarjetas(){
        if(tarjetas == null) return;        
        for(int i = 0; i<tarjetas.size(); i++){
            switch(i){
                case(0): cargarTarjeta(lblTarjeta1,tarjetas.get(0).getNombre());
                         chkTarjeta1.setEnabled(canjePermitido);
                         break;
                case(1): cargarTarjeta(lblTarjeta2,tarjetas.get(1).getNombre());
                         chkTarjeta2.setEnabled(canjePermitido);
                         break;
                case(2): cargarTarjeta(lblTarjeta3,tarjetas.get(2).getNombre());
                         chkTarjeta3.setEnabled(canjePermitido);
                         break;
                case(3): cargarTarjeta(lblTarjeta4,tarjetas.get(3).getNombre());
                         chkTarjeta4.setEnabled(canjePermitido);
                         break;
                case(4): cargarTarjeta(lblTarjeta5,tarjetas.get(4).getNombre());    
                         chkTarjeta5.setEnabled(canjePermitido);
                         break;
                case(5): cargarTarjeta(lblTarjeta6,tarjetas.get(5).getNombre());    
                         chkTarjeta6.setEnabled(canjePermitido);
                         break;
                case(6): cargarTarjeta(lblTarjeta7,tarjetas.get(6).getNombre());    
                         chkTarjeta7.setEnabled(canjePermitido);
                         break;
                case(7): cargarTarjeta(lblTarjeta8,tarjetas.get(7).getNombre());    
                         chkTarjeta8.setEnabled(canjePermitido);
                         break;
                case(8): cargarTarjeta(lblTarjeta9,tarjetas.get(8).getNombre());    
                         chkTarjeta9.setEnabled(canjePermitido);
                         break;
                case(9): cargarTarjeta(lblTarjeta10,tarjetas.get(9).getNombre());    
                         chkTarjeta10.setEnabled(canjePermitido);
                         break;
            }
        }
    }
    private void deshabilitarChecks(){
        chkTarjeta1.setEnabled(false);
        chkTarjeta2.setEnabled(false);
        chkTarjeta3.setEnabled(false);
        chkTarjeta4.setEnabled(false);
        chkTarjeta5.setEnabled(false);
        chkTarjeta6.setEnabled(false);
        chkTarjeta7.setEnabled(false);
        chkTarjeta8.setEnabled(false);
        chkTarjeta9.setEnabled(false);
        chkTarjeta10.setEnabled(false);
    }
    private void cargarTarjeta(JLabel label, String pais){
        try{
            label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Tarjetas/"+pais.replaceAll(" ", "")+".png")));
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, "Tarjeta no encontrada: " + pais.replaceAll(" ", "")+".png", "Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    private void limpiarLabels(){      
        lblTarjeta1.setIcon(null);
        lblTarjeta2.setIcon(null);
        lblTarjeta3.setIcon(null);
        lblTarjeta4.setIcon(null);
        lblTarjeta5.setIcon(null);
        lblTarjeta6.setIcon(null);
        lblTarjeta7.setIcon(null);
        lblTarjeta8.setIcon(null);
        lblTarjeta9.setIcon(null);
        lblTarjeta10.setIcon(null);
    }
    private boolean puedoCanjear(){
        return FachadaInterface.canjeValido(FachadaInterface.getJugadorLocal(),tarjetasSeleccionadas);
    }
    private boolean cargarTarjetaSeleccionada(Canjeable tarjeta){
        if(tarjetasSeleccionadas.size() <=3){           
            if(puedoCanjear()){
                JOptionPane.showMessageDialog(this,"Ya Posee las tarjetas suficientes para realizar un canje", "Canje", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            tarjetasSeleccionadas.add(tarjeta);
            if(puedoCanjear()) btnCanjear.setEnabled(true);
            else btnCanjear.setEnabled(false);
            return true;
        }
        else{
            JOptionPane.showMessageDialog(this, "A llegado al maximo de tarjetas para canjear", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
     private void quitarTarjetasSelccionada(Canjeable tarjeta){
        tarjetasSeleccionadas.remove(tarjeta);  
        if(puedoCanjear()){
            btnCanjear.setEnabled(true);
        }
        else{
            btnCanjear.setEnabled(false);
        }
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
        jLabel1 = new javax.swing.JLabel();
        btnCanjear = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblTarjeta1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblTarjeta2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblTarjeta3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblTarjeta4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblTarjeta5 = new javax.swing.JLabel();
        chkTarjeta5 = new javax.swing.JCheckBox();
        chkTarjeta1 = new javax.swing.JCheckBox();
        chkTarjeta2 = new javax.swing.JCheckBox();
        chkTarjeta3 = new javax.swing.JCheckBox();
        chkTarjeta4 = new javax.swing.JCheckBox();
        chkTarjeta9 = new javax.swing.JCheckBox();
        chkTarjeta8 = new javax.swing.JCheckBox();
        chkTarjeta7 = new javax.swing.JCheckBox();
        chkTarjeta6 = new javax.swing.JCheckBox();
        chkTarjeta10 = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        lblTarjeta10 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lblTarjeta9 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        lblTarjeta8 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lblTarjeta7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblTarjeta6 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Canje De Tarjetas");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/canje.png"))); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 153, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TARJETAS DISPONIBLES");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 14, 590, -1));

        btnCanjear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/canje.png"))); // NOI18N
        btnCanjear.setText("Canjear");
        btnCanjear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCanjearActionPerformed(evt);
            }
        });
        jPanel1.add(btnCanjear, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 410, -1, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTarjeta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Tarjetas/alaska.png"))); // NOI18N
        jPanel2.add(lblTarjeta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 98, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 100, 147));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTarjeta2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Tarjetas/alaska.png"))); // NOI18N
        jPanel4.add(lblTarjeta2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 98, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 100, 147));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTarjeta3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Tarjetas/alaska.png"))); // NOI18N
        jPanel5.add(lblTarjeta3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 98, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 100, 147));

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTarjeta4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Tarjetas/alaska.png"))); // NOI18N
        jPanel6.add(lblTarjeta4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 98, -1));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 100, 147));

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTarjeta5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Tarjetas/alaska.png"))); // NOI18N
        jPanel7.add(lblTarjeta5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 98, -1));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 100, 147));

        chkTarjeta5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTarjeta5ActionPerformed(evt);
            }
        });
        jPanel1.add(chkTarjeta5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 200, -1, -1));

        chkTarjeta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTarjeta1ActionPerformed(evt);
            }
        });
        jPanel1.add(chkTarjeta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, -1, -1));

        chkTarjeta2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTarjeta2ActionPerformed(evt);
            }
        });
        jPanel1.add(chkTarjeta2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, -1, -1));

        chkTarjeta3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTarjeta3ActionPerformed(evt);
            }
        });
        jPanel1.add(chkTarjeta3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, -1, -1));

        chkTarjeta4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTarjeta4ActionPerformed(evt);
            }
        });
        jPanel1.add(chkTarjeta4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 200, -1, -1));

        chkTarjeta9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTarjeta9ActionPerformed(evt);
            }
        });
        jPanel1.add(chkTarjeta9, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 380, -1, -1));

        chkTarjeta8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTarjeta8ActionPerformed(evt);
            }
        });
        jPanel1.add(chkTarjeta8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 380, -1, -1));

        chkTarjeta7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTarjeta7ActionPerformed(evt);
            }
        });
        jPanel1.add(chkTarjeta7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, -1, -1));

        chkTarjeta6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTarjeta6ActionPerformed(evt);
            }
        });
        jPanel1.add(chkTarjeta6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 380, -1, -1));

        chkTarjeta10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTarjeta10ActionPerformed(evt);
            }
        });
        jPanel1.add(chkTarjeta10, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 380, -1, -1));

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTarjeta10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Tarjetas/alaska.png"))); // NOI18N
        jPanel8.add(lblTarjeta10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 98, -1));

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 230, 100, 147));

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTarjeta9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Tarjetas/alaska.png"))); // NOI18N
        jPanel9.add(lblTarjeta9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 98, -1));

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, 100, 147));

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTarjeta8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Tarjetas/alaska.png"))); // NOI18N
        jPanel10.add(lblTarjeta8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 98, -1));

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 230, 100, 147));

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTarjeta7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Tarjetas/alaska.png"))); // NOI18N
        jPanel11.add(lblTarjeta7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 98, -1));

        jPanel1.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 100, 147));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTarjeta6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Tarjetas/alaska.png"))); // NOI18N
        jPanel3.add(lblTarjeta6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 98, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 100, 147));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCanjearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCanjearActionPerformed
        FachadaInterface.canjearTarjeta(FachadaInterface.getJugadorLocal(), tarjetasSeleccionadas);
        boton.setEnabled(true);
        this.dispose();
        padre.cerraVentanaReagrupar();
    }//GEN-LAST:event_btnCanjearActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
       menu.setEnabled(true);
       boton.setEnabled(true);
       padre.cerrarVentanaTarjetas();
    }//GEN-LAST:event_formInternalFrameClosing

    private void chkTarjeta5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTarjeta5ActionPerformed
        if(chkTarjeta5.isSelected()){
             if(!cargarTarjetaSeleccionada(tarjetas.get(4))){
                chkTarjeta5.setSelected(false);                
            }
        }
        else{
            quitarTarjetasSelccionada(tarjetas.get(4));
        }
    }//GEN-LAST:event_chkTarjeta5ActionPerformed

    private void chkTarjeta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTarjeta1ActionPerformed
        if(chkTarjeta1.isSelected()){
             if(!cargarTarjetaSeleccionada(tarjetas.get(0))){
                chkTarjeta1.setSelected(false);                
            }
        }
        else{
            quitarTarjetasSelccionada(tarjetas.get(0));
        }
    }//GEN-LAST:event_chkTarjeta1ActionPerformed

    private void chkTarjeta2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTarjeta2ActionPerformed
        if(chkTarjeta2.isSelected()){
            if(!cargarTarjetaSeleccionada(tarjetas.get(1))){
                chkTarjeta2.setSelected(false);                
            }
        }
        else{
            quitarTarjetasSelccionada(tarjetas.get(1));
        }
    }//GEN-LAST:event_chkTarjeta2ActionPerformed

    private void chkTarjeta3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTarjeta3ActionPerformed
        if(chkTarjeta3.isSelected()){
            if(!cargarTarjetaSeleccionada(tarjetas.get(2))){
                chkTarjeta3.setSelected(false);                
            }
        }
        else{
            quitarTarjetasSelccionada(tarjetas.get(2));
        }
    }//GEN-LAST:event_chkTarjeta3ActionPerformed

    private void chkTarjeta4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTarjeta4ActionPerformed
        if(chkTarjeta4.isSelected()){
            if(!cargarTarjetaSeleccionada(tarjetas.get(3))){
                chkTarjeta4.setSelected(false);                
            }
        }
        else{
            quitarTarjetasSelccionada(tarjetas.get(3));
        }
    }//GEN-LAST:event_chkTarjeta4ActionPerformed

    private void chkTarjeta9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTarjeta9ActionPerformed
        if(chkTarjeta9.isSelected()){
            if(!cargarTarjetaSeleccionada(tarjetas.get(8))){
                chkTarjeta9.setSelected(false);                
            }
        }
        else{
            quitarTarjetasSelccionada(tarjetas.get(8));
        }
    }//GEN-LAST:event_chkTarjeta9ActionPerformed

    private void chkTarjeta8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTarjeta8ActionPerformed
        if(chkTarjeta8.isSelected()){
            if(!cargarTarjetaSeleccionada(tarjetas.get(7))){
                chkTarjeta8.setSelected(false);                
            }
        }
        else{
            quitarTarjetasSelccionada(tarjetas.get(7));
        }
    }//GEN-LAST:event_chkTarjeta8ActionPerformed

    private void chkTarjeta7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTarjeta7ActionPerformed
        if(chkTarjeta7.isSelected()){
            if(!cargarTarjetaSeleccionada(tarjetas.get(6))){
                chkTarjeta7.setSelected(false);                
            }
        }
        else{
            quitarTarjetasSelccionada(tarjetas.get(6));
        }
    }//GEN-LAST:event_chkTarjeta7ActionPerformed

    private void chkTarjeta6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTarjeta6ActionPerformed
        if(chkTarjeta6.isSelected()){
            if(!cargarTarjetaSeleccionada(tarjetas.get(5))){
                chkTarjeta6.setSelected(false);                
            }
        }
        else{
            quitarTarjetasSelccionada(tarjetas.get(5));
        }
    }//GEN-LAST:event_chkTarjeta6ActionPerformed

    private void chkTarjeta10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTarjeta10ActionPerformed
        if(chkTarjeta10.isSelected()){
            if(!cargarTarjetaSeleccionada(tarjetas.get(9))){
                chkTarjeta10.setSelected(false);                
            }
        }
        else{
            quitarTarjetasSelccionada(tarjetas.get(9));
        }
    }//GEN-LAST:event_chkTarjeta10ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCanjear;
    private javax.swing.JCheckBox chkTarjeta1;
    private javax.swing.JCheckBox chkTarjeta10;
    private javax.swing.JCheckBox chkTarjeta2;
    private javax.swing.JCheckBox chkTarjeta3;
    private javax.swing.JCheckBox chkTarjeta4;
    private javax.swing.JCheckBox chkTarjeta5;
    private javax.swing.JCheckBox chkTarjeta6;
    private javax.swing.JCheckBox chkTarjeta7;
    private javax.swing.JCheckBox chkTarjeta8;
    private javax.swing.JCheckBox chkTarjeta9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblTarjeta1;
    private javax.swing.JLabel lblTarjeta10;
    private javax.swing.JLabel lblTarjeta2;
    private javax.swing.JLabel lblTarjeta3;
    private javax.swing.JLabel lblTarjeta4;
    private javax.swing.JLabel lblTarjeta5;
    private javax.swing.JLabel lblTarjeta6;
    private javax.swing.JLabel lblTarjeta7;
    private javax.swing.JLabel lblTarjeta8;
    private javax.swing.JLabel lblTarjeta9;
    // End of variables declaration//GEN-END:variables
}
