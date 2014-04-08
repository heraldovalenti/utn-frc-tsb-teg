/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import javax.swing.JOptionPane;
import juego.estructura.Pais;

/**
 *
 * @author Emanuel
 */
public class Reagrupar extends javax.swing.JDialog {
    private InterfacePrincipal padre;
    private Pais paisDesde;
    private Pais paisHasta;
    /**
     * Creates new form Reagrupar2
     */
    public Reagrupar(java.awt.Frame parent, boolean modal, InterfacePrincipal padre) {
        super(parent, modal);
        initComponents();
        rbDesde.setSelected(true);
        txtDesde.setEditable(false);
        txtHasta.setEditable(false);
        this.padre = padre;
    }
    public void cargarPais(Pais pais){
        if(rbDesde.isSelected()){
            if(pais.getCantidadEjercitos() <=1 ){
                JOptionPane.showMessageDialog(this, "No posee cantidad suficientes de tropas", "Tropas Insuficientes",JOptionPane.WARNING_MESSAGE);
                paisDesde = null;
            }
            else{
                paisDesde = pais;
                txtDesde.setText(paisDesde.getNombre());
            }
        }
        else{
            paisHasta = pais;
            txtHasta.setText(paisHasta.getNombre());
        }
        validarReagrupacion();
    }   
    private void validarReagrupacion(){        
        
        if(paisDesde == null || paisHasta == null){
            btnReagrupar.setEnabled(false);
            txtCantidad.setValue(0);
            txtCantidad.setEnabled(false);
        }
        else{
            btnReagrupar.setEnabled(true);
            txtCantidad.setValue(1);
            txtCantidad.setEnabled(true);
            txtCantidad.setMaximum(paisDesde.getCantidadEjercitos()-1);
            txtCantidad.setMinimum(1);
        }
    }
    private void inicializar(){
        rbDesde.setSelected(true);
        paisDesde = null;
        rbHasta.setSelected(true);
        paisHasta = null;
        cargarPais(paisDesde);
        cargarPais(paisHasta);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        rbHasta = new javax.swing.JRadioButton();
        rbDesde = new javax.swing.JRadioButton();
        txtDesde = new javax.swing.JTextField();
        txtHasta = new javax.swing.JTextField();
        btnReagrupar = new javax.swing.JButton();
        txtCantidad = new com.toedter.components.JSpinField();
        btnFin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Reagrupar Tropas");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        buttonGroup1.add(rbHasta);
        rbHasta.setText("Hasta:");

        buttonGroup1.add(rbDesde);
        rbDesde.setText("Desde:");

        txtHasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHastaActionPerformed(evt);
            }
        });

        btnReagrupar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/botones/reagrupar2.png"))); // NOI18N
        btnReagrupar.setText("Reagrupar");
        btnReagrupar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReagruparActionPerformed(evt);
            }
        });

        btnFin.setText("Fin");
        btnFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addComponent(btnReagrupar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFin)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(rbDesde)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbHasta)
                        .addGap(8, 8, 8)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtHasta, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                    .addComponent(txtDesde))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbDesde)
                            .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rbHasta)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtHasta)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReagrupar)
                    .addComponent(btnFin))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtHastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHastaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHastaActionPerformed

    private void btnReagruparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReagruparActionPerformed
        FachadaInterface.reagrupar(paisDesde,paisHasta, txtCantidad.getValue());
        inicializar();        
    }//GEN-LAST:event_btnReagruparActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
         padre.cerraVentanaReagrupar();
    }//GEN-LAST:event_formWindowClosing

    private void btnFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinActionPerformed
        padre.cerraVentanaReagrupar();
        this.dispose();
    }//GEN-LAST:event_btnFinActionPerformed

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFin;
    private javax.swing.JButton btnReagrupar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbDesde;
    private javax.swing.JRadioButton rbHasta;
    private com.toedter.components.JSpinField txtCantidad;
    private javax.swing.JTextField txtDesde;
    private javax.swing.JTextField txtHasta;
    // End of variables declaration//GEN-END:variables
}
