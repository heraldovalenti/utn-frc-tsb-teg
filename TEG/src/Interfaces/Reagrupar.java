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
        txtCantidadTropas.setMinimum(0);
        txtCantidadMisil.setMinimum(0);
        txtDesde.setEditable(false);
        txtHasta.setEditable(false);
        inicializar();
        this.padre = padre;
    }

    public void cargarPais(Pais pais) {
        if (rbDesde.isSelected()) {
            if (pais.getCantidadEjercitos() <= 1 && pais.getCantidadMisiles() == 0) {
                JOptionPane.showMessageDialog(this, "No posee cantidad suficiente de tropas ni de misiles", "Tropas y misiles Insuficientes", JOptionPane.WARNING_MESSAGE);
                paisDesde = null;
            } else {
                paisDesde = pais;
                txtDesde.setText(paisDesde.getNombre());
            }
        } else {
            paisHasta = pais;
            txtHasta.setText(paisHasta.getNombre());
        }
        validarReagrupacion();
    }    

    private void validarReagrupacion() {        
        
        if (paisDesde == null || paisHasta == null) {
            btnReagrupar.setEnabled(false);
            setCantidades(false);
        } else {
            setCantidades(true);
            txtCantidadTropas.setMaximum(paisDesde.getCantidadEjercitos() - 1);
            txtCantidadMisil.setMaximum(paisDesde.getCantidadMisiles());
            btnReagrupar.setEnabled(true);
        }
    }

    private void setCantidades(boolean enabled) {
        txtCantidadTropas.setValue(0);
        txtCantidadTropas.setEnabled(enabled);
        txtCantidadMisil.setValue(0);
        txtCantidadMisil.setEnabled(enabled);
    }

    private void inicializar() {
        rbHasta.setSelected(true);
        paisHasta = null;
        //cargarPais(paisHasta);
        rbDesde.setSelected(true);
        paisDesde = null;
        //cargarPais(paisDesde);       
        setCantidades(false);
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
        groupTipo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        rbHasta = new javax.swing.JRadioButton();
        rbDesde = new javax.swing.JRadioButton();
        txtDesde = new javax.swing.JTextField();
        txtHasta = new javax.swing.JTextField();
        btnReagrupar = new javax.swing.JButton();
        txtCantidadTropas = new com.toedter.components.JSpinField();
        btnFin = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCantidadMisil = new com.toedter.components.JSpinField();

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

        jLabel1.setText("Tropas:");

        jLabel2.setText("Misiles:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(rbDesde)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rbHasta)
                                        .addGap(8, 8, 8))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCantidadTropas, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCantidadMisil, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtHasta)
                            .addComponent(txtDesde)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReagrupar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFin)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addComponent(txtHasta)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCantidadTropas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtCantidadMisil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReagrupar)
                    .addComponent(btnFin))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtHastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHastaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHastaActionPerformed

    private void btnReagruparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReagruparActionPerformed
        if (txtCantidadTropas.getValue() == 0 && txtCantidadMisil.getValue() == 0) {
            JOptionPane.showMessageDialog(this, "Debe ragrupar al menos una tropa o al menos un misil", "Error al reagrupar", JOptionPane.WARNING_MESSAGE);
            
        } else {
            FachadaInterface.reagrupar(paisDesde, paisHasta, txtCantidadTropas.getValue(), txtCantidadMisil.getValue());
            inicializar();
        }        
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
    private javax.swing.ButtonGroup groupTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbDesde;
    private javax.swing.JRadioButton rbHasta;
    private com.toedter.components.JSpinField txtCantidadMisil;
    private com.toedter.components.JSpinField txtCantidadTropas;
    private javax.swing.JTextField txtDesde;
    private javax.swing.JTextField txtHasta;
    // End of variables declaration//GEN-END:variables
}
