/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import cliente.control.ControlRefuerzo;
import java.awt.Component;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import juego.estructura.Continente;
import juego.estructura.Pais;

/**
 *
 * @author Emanuel
 */
public class Refuerzo extends javax.swing.JInternalFrame {

    private ControlRefuerzo controlRefuerzo;
    private InterfacePrincipal padre;
    /**
     * Creates new form Refuerzo
     */
    public Refuerzo(ControlRefuerzo controlRefuerzo, InterfacePrincipal padre) {
        initComponents();
        rbTropa.setSelected(true);
        this.controlRefuerzo = controlRefuerzo;
        this.padre = padre;
        actualizarTropas(controlRefuerzo.calcularEjercitosPorContinenteDisponibles(), controlRefuerzo.calcularEjercitosLibresDisponibles());
        btnFinalizar.setEnabled(false);
    }

    public void actualizarTropas(Map<Continente, Integer> refuerzosPorContinente, int refuerzosDisponibles) {
        reiniciarTextField();
        txtLibres.setText(String.valueOf(refuerzosDisponibles));
        Component[] componentes = jPanel1.getComponents();
        int num = 0;
        String nombre = "";
        Iterator it = refuerzosPorContinente.entrySet().iterator();
//        while (it.hasNext()) {
//                Map.Entry e = (Map.Entry)it.next();
//                Continente cont = (Continente)e.getValue();
//                nombre = cont.getNombre();
//                for(int j=0; j<componentes.length;j++){                
//                    if(componentes[j] instanceof JLabel) 
//                    { 
//                        JLabel label = ((JLabel) componentes[j]);
//                        if(label.getName()!=null && nombre.equalsIgnoreCase(label.getName())){
//                              label.setText(String.valueOf((Integer)e.getKey()));
//                        }
//                    } 
//               
//                }
//        }
        for (Continente cont : refuerzosPorContinente.keySet()) {
            nombre = cont.getNombre();
            for (Component componente : componentes) {
                if (componente instanceof JTextField) {
                    JTextField text = (JTextField) componente;
                    if (text.getName() != null && nombre.equalsIgnoreCase(text.getName())) {
                        text.setText(String.valueOf((refuerzosPorContinente.get(cont))));
                    }
                }
            }
        }
    }

    private void reiniciarTextField() {
        txtLibres.setText(String.valueOf(0));
        txtAfrica.setText(String.valueOf(0));
        txtAmericaNorte.setText(String.valueOf(0));
        txtAmericaSur.setText(String.valueOf(0));
        txtAsia.setText(String.valueOf(0));
        txtEuropa.setText(String.valueOf(0));
        txtOceania.setText(String.valueOf(0));
    }

    public void agregarRefuerzo(Pais pais, ControlRefuerzo control) {
        controlRefuerzo = control;
        if (esTropa()) {
            if (controlRefuerzo.puedeReforzar(pais)) {
                controlRefuerzo.agregarEjercito(pais);
            } else {
                JOptionPane.showMessageDialog(this, "No puede Agregar mas refuerzos", "Refuerzos", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            if (controlRefuerzo.puedeReforzarConMisil(pais)) {
                controlRefuerzo.agregarMisil(pais);
            } else {
                JOptionPane.showMessageDialog(this, "No puede Canjer por un Misil", "Refuerzos con Misil", JOptionPane.WARNING_MESSAGE);
            }
        }
        actualizarTropas(controlRefuerzo.calcularEjercitosPorContinenteDisponibles(), controlRefuerzo.calcularEjercitosLibresDisponibles());
        btnFinalizar.setEnabled(sinRefuerzos());
    }

    private boolean sinRefuerzos() {
        if (controlRefuerzo.calcularEjercitosLibresDisponibles() != 0) {
            return false;
        }
//        Map refPorContinente = controlRefuerzo.calcularEjercitosPorContinenteDisponibles();
//        Iterator it = refPorContinente.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry e = (Map.Entry) it.next();
//            if ((Integer) e.getKey() != 0) {
//                return false;
//            }
//        }
        Map<Continente, Integer> mapaContinentes = controlRefuerzo.calcularEjercitosPorContinenteDisponibles();
        for (Continente continente : mapaContinentes.keySet()) {
            if (mapaContinentes.get(continente) > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean esTropa() {
        return rbTropa.isSelected();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtLibres = new javax.swing.JTextField();
        txtAfrica = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAmericaNorte = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtAmericaSur = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtAsia = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEuropa = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtOceania = new javax.swing.JTextField();
        rbTropa = new javax.swing.JRadioButton();
        rbMisil = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();

        setTitle("Refuerzos");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/refuerzos.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tropas Disponibles"));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Libres:");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Africa:");

        txtLibres.setEditable(false);

        txtAfrica.setEditable(false);
        txtAfrica.setName("Africa"); // NOI18N

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("A. del Norte:");

        txtAmericaNorte.setEditable(false);
        txtAmericaNorte.setName("America del Norte"); // NOI18N
        txtAmericaNorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAmericaNorteActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("A del Sur:");
        jLabel4.setToolTipText("");

        txtAmericaSur.setEditable(false);
        txtAmericaSur.setName("America Del Sur"); // NOI18N

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Asia:");

        txtAsia.setEditable(false);
        txtAsia.setName("Asia"); // NOI18N

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Europa:");

        txtEuropa.setEditable(false);
        txtEuropa.setName("Europa"); // NOI18N

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Oceania:");

        txtOceania.setEditable(false);
        txtOceania.setName("Oceanía"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtOceania))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtLibres, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtEuropa, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtAmericaSur, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtAsia, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtAmericaNorte, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                                        .addComponent(txtAfrica)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtLibres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtAfrica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtAmericaNorte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtAmericaSur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtAsia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtEuropa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtOceania, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        buttonGroup1.add(rbTropa);
        rbTropa.setForeground(new java.awt.Color(51, 153, 0));
        rbTropa.setText("Tropa");
        rbTropa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTropaActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbMisil);
        rbMisil.setForeground(new java.awt.Color(255, 0, 0));
        rbMisil.setText("Misil");

        jButton1.setText("Reiniciar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnFinalizar.setText("Finalizar");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFinalizar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rbTropa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbMisil)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbTropa)
                    .addComponent(rbMisil))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnFinalizar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAmericaNorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAmericaNorteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAmericaNorteActionPerformed

    private void rbTropaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTropaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbTropaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        controlRefuerzo.reiniciar();
        actualizarTropas(controlRefuerzo.calcularEjercitosPorContinenteDisponibles(), controlRefuerzo.calcularEjercitosLibresDisponibles());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        controlRefuerzo.aplicarRefuerzo();
        padre.habilitarBotones();
        this.dispose();
    }//GEN-LAST:event_btnFinalizarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFinalizar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbMisil;
    private javax.swing.JRadioButton rbTropa;
    private javax.swing.JTextField txtAfrica;
    private javax.swing.JTextField txtAmericaNorte;
    private javax.swing.JTextField txtAmericaSur;
    private javax.swing.JTextField txtAsia;
    private javax.swing.JTextField txtEuropa;
    private javax.swing.JTextField txtLibres;
    private javax.swing.JTextField txtOceania;
    // End of variables declaration//GEN-END:variables
}
