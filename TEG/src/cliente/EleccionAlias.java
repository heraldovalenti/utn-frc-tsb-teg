/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import javax.swing.JOptionPane;

/**
 *
 * @author heril
 */
public class EleccionAlias extends javax.swing.JDialog {

    private boolean mostrarAdvertenciaAliasNoAceptado;
    private ControlAlias controlAlias;
    public EleccionAlias(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        mostrarAdvertenciaAliasNoAceptado = false;
        controlAlias = ClienteManager.getInstance().getControlAlias();
        cargarAlias();
    }
    
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        mostrarAdvertenciaAliasNoAceptado();
    }
    
    private void cargarAlias() {
        String aliasActual = controlAlias.getAlias();
        StringBuilder res = new StringBuilder("Alias actual:");
        if (aliasActual == null || aliasActual.isEmpty()) {
            res.append( " <Sin asignar>");
        } else {
            res.append( " <");
            res.append(aliasActual);
            res.append(">");
        }
        lblAliasActual.setText(res.toString());
    }
    
    private void guardarNuevoAlias() {
        String nuevoAlias = txtNuevoAlias.getText();
        controlAlias.setAlias(nuevoAlias);
        if (!controlAlias.aliasValido()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un alias válido para continuar.",
                    "Información", JOptionPane.WARNING_MESSAGE);
            return;
        }
        this.dispose();
        controlAlias.ejecutarControlAlias();
    }

    private void cancelar() {
        if (!controlAlias.aliasValido()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un alias válido para continuar.",
                    "Información", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!controlAlias.isAceptadoPorServidor()) {
            this.dispose();
            controlAlias.ejecutarControlAlias();
            return;
        }
        this.dispose();
    }
    
    private void mostrarAdvertenciaAliasNoAceptado() {
        if (mostrarAdvertenciaAliasNoAceptado) {
            JOptionPane.showMessageDialog(this, "El alias ingresado no ha sido aceptado por el servidor."
                    + "\nQuizás sea porque el alias ya está en uso."
                    + "\nIngrese un alias distinto para continuar.",
                    "Información", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void activarMostrarAdvertenciaAliasNoAceptado() {
        mostrarAdvertenciaAliasNoAceptado = true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAliasActual = new javax.swing.JLabel();
        lblNuevoAlias = new javax.swing.JLabel();
        txtNuevoAlias = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Eleccion de Alias");
        setResizable(false);

        lblAliasActual.setText("Alias actual: <ALIAS_ACTUAL>");

        lblNuevoAlias.setText("Nuevo alias:");

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAliasActual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNuevoAlias)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNuevoAlias))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 77, Short.MAX_VALUE)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAceptar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAliasActual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNuevoAlias)
                    .addComponent(txtNuevoAlias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        cancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        guardarNuevoAlias();
    }//GEN-LAST:event_btnAceptarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel lblAliasActual;
    private javax.swing.JLabel lblNuevoAlias;
    private javax.swing.JTextField txtNuevoAlias;
    // End of variables declaration//GEN-END:variables
}