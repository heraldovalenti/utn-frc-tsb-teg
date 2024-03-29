package Interfaces;


import cliente.ClienteManager;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JTextField;
import juego.estructura.Jugador;
import juego.estructura.Pais;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Emanuel
 */
public class Jugadores extends IJugadores {
    private List<Jugador> jugadores ;
    private Jugador jugadorTurno = new Jugador();
    private Color colorTurno = Color.orange;
    private Color colorNoTurno = new Color(255,255,204);
    /**
     * Creates new form GUIJugadores
     */
    public Jugadores(List<Jugador> jugadores) {
        initComponents();
        this.setSize(250, 747);    
        this.jugadores = jugadores;
        cargarDatosJugadores();
    }
    private void cargarDatosJugadores(){
        int i = 0;
        for(Jugador jugador : jugadores){
            switch(i){
            case(0): txtColor1.setBackground(jugador.getColor());
                     txtJugador1.setText(jugador.getNombre());
                     cambiarColor(jugador, txtJugador1);
                     setFuenteJugadorLocal(jugador, txtJugador1);
                     break;
            case(1): txtColor2.setBackground(jugador.getColor());
                     txtJugador2.setText(jugador.getNombre());
                     cambiarColor(jugador, txtJugador2);
                     setFuenteJugadorLocal(jugador, txtJugador2);
                     break;
            case(2): txtColor3.setBackground(jugador.getColor());
                     txtJugador3.setText(jugador.getNombre());
                     cambiarColor(jugador, txtJugador3);
                     setFuenteJugadorLocal(jugador, txtJugador3);
                     break;
            case(3): txtColor4.setBackground(jugador.getColor());
                     txtJugador4.setText(jugador.getNombre());
                     cambiarColor(jugador, txtJugador4);
                     setFuenteJugadorLocal(jugador, txtJugador4);
                     break;
            case(4): txtColor5.setBackground(jugador.getColor());
                     txtJugador5.setText(jugador.getNombre());
                     cambiarColor(jugador, txtJugador5);
                     setFuenteJugadorLocal(jugador, txtJugador5);
                     break;
            case(5): txtColor6.setBackground(jugador.getColor());
                     txtJugador6.setText(jugador.getNombre());
                     cambiarColor(jugador, txtJugador6);
                     setFuenteJugadorLocal(jugador, txtJugador6);
                     break;    
        }
            i++;
        }
        habilitarTxts();
    }  
    private void cambiarColor(Jugador jugador, JTextField txt){
        if(jugador.equals(jugadorTurno)){
             txt.setBackground(colorTurno);             
         }
         else{
             txt.setBackground(colorNoTurno);
             
         }
    }
    private void setFuenteJugadorLocal(Jugador jugador, JTextField txt){
        if(FachadaInterface.getJugadorLocal().equals(jugador)){
             txt.setFont(new java.awt.Font("Tahoma",java.awt.Font.BOLD , 11));
        }
    }
    private void habilitarTxt(JTextField color, JTextField jugador, boolean habilitar){
        color.setVisible(habilitar);
        jugador.setVisible(habilitar);
    }
    private void habilitarTxts(){
        habilitarTxt(txtColor1,txtJugador1,!(txtJugador1.getText().compareTo("")==0));
        habilitarTxt(txtColor2,txtJugador2,!(txtJugador2.getText().compareTo("")==0));
        habilitarTxt(txtColor3,txtJugador3,!(txtJugador3.getText().compareTo("")==0));
        habilitarTxt(txtColor4,txtJugador4,!(txtJugador4.getText().compareTo("")==0));
        habilitarTxt(txtColor5,txtJugador5,!(txtJugador5.getText().compareTo("")==0));
        habilitarTxt(txtColor6,txtJugador6,!(txtJugador6.getText().compareTo("")==0));
    }
    private void actualizarJugadores(){
        int i = 0;
        for(Jugador jugador : jugadores){
            switch(i){
            case(0): txtColor1.setBackground(jugador.getColor());                    
                     cambiarColor(jugador, txtJugador1);
                     break;
            case(1): txtColor2.setBackground(jugador.getColor());                     
                     cambiarColor(jugador, txtJugador2);
                     break;
            case(2): txtColor3.setBackground(jugador.getColor());                     
                     cambiarColor(jugador, txtJugador3);
                     break;
            case(3): txtColor4.setBackground(jugador.getColor());                     
                     cambiarColor(jugador, txtJugador4);
                     break;
            case(4): txtColor5.setBackground(jugador.getColor());                     
                     cambiarColor(jugador, txtJugador5);
                     break;
            case(5): txtColor6.setBackground(jugador.getColor());                     
                     cambiarColor(jugador, txtJugador6);
                     break;    
        }
            i++;
        }
    }  
    
    
    private void mostrarDetalleJugador(String nombre){
        for(Jugador jugador:jugadores){
            if(jugador.getNombre().compareTo(nombre) == 0){
                txtCantidadPaises.setText(String.valueOf(jugador.getCantidadPaises()));
                int[] cantidad = obtenerCantidadTotalTropas(jugador);
                txtCantidadTropas.setText(String.valueOf(cantidad[0]));
                txtCantidadTropasDisponibles.setText(String.valueOf(cantidad[1]));
                jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle del Jugador "+jugador.getNombre()+":"));
            }
        }
    }
    public void actualizarJugadores(List<Jugador> jugadores, Jugador jugadorTurno){
        this.jugadores = jugadores;
        this.jugadorTurno = jugadorTurno;
        actualizarJugadores();
        if(jugadorTurno != null){
            mostrarDetalleJugador(jugadorTurno.getNombre());
        }
        
    }
    private int[] obtenerCantidadTotalTropas(Jugador jugador){
        int [] cantidad = {0,0};
        for(Pais pais : jugador.getConjuntoPaises()){
            cantidad[0]+=pais.getCantidadEjercitos();
            cantidad[1]+=pais.getCantidadMisiles();
        }
        return cantidad;
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
        jPanel2 = new javax.swing.JPanel();
        txtJugador1 = new javax.swing.JTextField();
        txtJugador2 = new javax.swing.JTextField();
        txtJugador3 = new javax.swing.JTextField();
        txtColor1 = new javax.swing.JTextField();
        txtColor2 = new javax.swing.JTextField();
        txtColor3 = new javax.swing.JTextField();
        txtColor4 = new javax.swing.JTextField();
        txtJugador4 = new javax.swing.JTextField();
        txtJugador5 = new javax.swing.JTextField();
        txtColor5 = new javax.swing.JTextField();
        txtColor6 = new javax.swing.JTextField();
        txtJugador6 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblPaises = new javax.swing.JLabel();
        lblTropas = new javax.swing.JLabel();
        txtCantidadPaises = new javax.swing.JTextField();
        txtCantidadTropas = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCantidadTropasDisponibles = new javax.swing.JTextField();

        setIconifiable(true);
        setResizable(true);
        setTitle("JUGADORES");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/male_users.png"))); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Ronda"));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel2MouseEntered(evt);
            }
        });

        txtJugador1.setEditable(false);
        txtJugador1.setBackground(new java.awt.Color(255, 255, 204));
        txtJugador1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtJugador1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtJugador4MouseExited(evt);
            }
        });

        txtJugador2.setEditable(false);
        txtJugador2.setBackground(new java.awt.Color(255, 255, 204));
        txtJugador2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtJugador2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtJugador4MouseExited(evt);
            }
        });

        txtJugador3.setEditable(false);
        txtJugador3.setBackground(new java.awt.Color(255, 255, 204));
        txtJugador3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtJugador3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtJugador4MouseExited(evt);
            }
        });

        txtColor1.setEditable(false);
        txtColor1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtColor1.setSelectionColor(new java.awt.Color(255, 255, 255));

        txtColor2.setEditable(false);
        txtColor2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtColor2.setSelectionColor(new java.awt.Color(255, 255, 255));

        txtColor3.setEditable(false);
        txtColor3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtColor3.setSelectionColor(new java.awt.Color(255, 255, 255));

        txtColor4.setEditable(false);
        txtColor4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtColor4.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtColor4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtColor4ActionPerformed(evt);
            }
        });

        txtJugador4.setEditable(false);
        txtJugador4.setBackground(new java.awt.Color(255, 255, 204));
        txtJugador4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtJugador4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtJugador4MouseExited(evt);
            }
        });

        txtJugador5.setEditable(false);
        txtJugador5.setBackground(new java.awt.Color(255, 255, 204));
        txtJugador5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtJugador5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtJugador4MouseExited(evt);
            }
        });

        txtColor5.setEditable(false);
        txtColor5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtColor5.setSelectionColor(new java.awt.Color(255, 255, 255));

        txtColor6.setEditable(false);
        txtColor6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtColor6.setSelectionColor(new java.awt.Color(255, 255, 255));

        txtJugador6.setEditable(false);
        txtJugador6.setBackground(new java.awt.Color(255, 255, 204));
        txtJugador6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtJugador6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtJugador4MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtColor1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtColor2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtJugador2)
                            .addComponent(txtJugador1)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtColor3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtJugador3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtColor4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtColor5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtJugador5)
                            .addComponent(txtJugador4)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtColor6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtJugador6)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtColor1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtColor2, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtColor3, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(txtJugador3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJugador4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtColor4, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJugador5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtColor5, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtColor6, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(txtJugador6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("T.E.G");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Jugador: "));

        lblPaises.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPaises.setText("Cantidad de Paises:");

        lblTropas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTropas.setText("Cantidad de Tropas:");

        txtCantidadPaises.setEditable(false);
        txtCantidadPaises.setBackground(new java.awt.Color(255, 255, 204));

        txtCantidadTropas.setEditable(false);
        txtCantidadTropas.setBackground(new java.awt.Color(255, 255, 204));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Cantidad de Misiles:");

        txtCantidadTropasDisponibles.setEditable(false);
        txtCantidadTropasDisponibles.setBackground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblPaises)
                    .addComponent(lblTropas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCantidadTropasDisponibles, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                    .addComponent(txtCantidadPaises, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCantidadTropas))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPaises)
                    .addComponent(txtCantidadPaises, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTropas)
                    .addComponent(txtCantidadTropas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCantidadTropasDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(136, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtColor4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtColor4ActionPerformed
       
    }//GEN-LAST:event_txtColor4ActionPerformed

    private void txtJugador1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtJugador1MouseEntered
          String nombre = txtJugador1.getText();
          if(nombre.compareTo("") == 0) return;
          mostrarDetalleJugador(nombre);
    }//GEN-LAST:event_txtJugador1MouseEntered

    private void jPanel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseEntered
          String nombre = txtJugador2.getText();
          if(nombre.compareTo("") == 0) return;
          mostrarDetalleJugador(nombre);
    }//GEN-LAST:event_jPanel2MouseEntered

    private void txtJugador3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtJugador3MouseEntered
          String nombre = txtJugador3.getText();
          if(nombre.compareTo("") == 0) return;
          mostrarDetalleJugador(nombre);
    }//GEN-LAST:event_txtJugador3MouseEntered

    private void txtJugador4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtJugador4MouseEntered
          String nombre = txtJugador4.getText();
          if(nombre.compareTo("") == 0) return;
          mostrarDetalleJugador(nombre);
    }//GEN-LAST:event_txtJugador4MouseEntered

    private void txtJugador5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtJugador5MouseEntered
          String nombre = txtJugador5.getText();
          if(nombre.compareTo("") == 0) return;
          mostrarDetalleJugador(nombre);
    }//GEN-LAST:event_txtJugador5MouseEntered

    private void txtJugador6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtJugador6MouseEntered
          String nombre = txtJugador6.getText();
          if(nombre.compareTo("") == 0) return;
          mostrarDetalleJugador(nombre);
    }//GEN-LAST:event_txtJugador6MouseEntered

    private void txtJugador4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtJugador4MouseExited
        mostrarDetalleJugador(jugadorTurno.getNombre());
    }//GEN-LAST:event_txtJugador4MouseExited

    private void txtJugador2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtJugador2MouseEntered
         String nombre = txtJugador2.getText();
         if(nombre.compareTo("") == 0) return;
          mostrarDetalleJugador(nombre);
    }//GEN-LAST:event_txtJugador2MouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblPaises;
    private javax.swing.JLabel lblTropas;
    private javax.swing.JTextField txtCantidadPaises;
    private javax.swing.JTextField txtCantidadTropas;
    private javax.swing.JTextField txtCantidadTropasDisponibles;
    private javax.swing.JTextField txtColor1;
    private javax.swing.JTextField txtColor2;
    private javax.swing.JTextField txtColor3;
    private javax.swing.JTextField txtColor4;
    private javax.swing.JTextField txtColor5;
    private javax.swing.JTextField txtColor6;
    private javax.swing.JTextField txtJugador1;
    private javax.swing.JTextField txtJugador2;
    private javax.swing.JTextField txtJugador3;
    private javax.swing.JTextField txtJugador4;
    private javax.swing.JTextField txtJugador5;
    private javax.swing.JTextField txtJugador6;
    // End of variables declaration//GEN-END:variables
}
