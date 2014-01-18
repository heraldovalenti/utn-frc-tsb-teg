/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.cliente;

import javax.swing.text.DefaultCaret;

/**
 *
 * @author heril
 */
public class SalaEspera extends javax.swing.JFrame {

    /**
     * Creates new form SalaEspera
     */
    public SalaEspera() {
        initComponents();
    }
    
    private void enviarChat() {
        String chat = txtChatPersonal.getText();
        if (chat != null && chat.isEmpty()) {
            return;
        }
        txtChatArea.append(txtChatPersonal.getText() + "\n");
        DefaultCaret caret = (DefaultCaret)txtChatPersonal.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        txtChatPersonal.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelConexion = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDireccionServidor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnConexion = new javax.swing.JButton();
        lblEstadoConexion = new javax.swing.JLabel();
        panelSalaEspera = new javax.swing.JPanel();
        lblNumeroJuego = new javax.swing.JLabel();
        sPanelJugadores = new javax.swing.JScrollPane();
        tblJugadores = new javax.swing.JTable();
        sPanelChat = new javax.swing.JScrollPane();
        txtChatArea = new javax.swing.JTextArea();
        txtChatPersonal = new javax.swing.JTextField();
        cbxEstadoJugador = new javax.swing.JCheckBox();
        menuPrincipal = new javax.swing.JMenuBar();
        menuItemOpciones = new javax.swing.JMenu();
        menuItemAlias = new javax.swing.JMenuItem();
        menuItemSeleccionColor = new javax.swing.JMenuItem();
        menuItemSalida = new javax.swing.JMenuItem();
        menuItemAdministracionPartida = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TEG - Sala de espera");

        panelConexion.setBorder(javax.swing.BorderFactory.createTitledBorder("Conexión a partida"));

        jLabel1.setText("IP servidor:");

        txtDireccionServidor.setText("localhost");

        jLabel2.setText("Estado:");

        btnConexion.setText("Conectar");

        lblEstadoConexion.setText("<ESTADO_CONEXION>");

        javax.swing.GroupLayout panelConexionLayout = new javax.swing.GroupLayout(panelConexion);
        panelConexion.setLayout(panelConexionLayout);
        panelConexionLayout.setHorizontalGroup(
            panelConexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConexionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelConexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelConexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelConexionLayout.createSequentialGroup()
                        .addComponent(txtDireccionServidor, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConexion, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblEstadoConexion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelConexionLayout.setVerticalGroup(
            panelConexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConexionLayout.createSequentialGroup()
                .addGroup(panelConexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDireccionServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConexion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblEstadoConexion)))
        );

        panelSalaEspera.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala de espera"));

        lblNumeroJuego.setText("Numero de juego: <VALOR_ALEATORIO>");

        tblJugadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Alias", "Tipo", "Color", "Listo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sPanelJugadores.setViewportView(tblJugadores);

        txtChatArea.setEditable(false);
        txtChatArea.setColumns(20);
        txtChatArea.setLineWrap(true);
        txtChatArea.setRows(1);
        sPanelChat.setViewportView(txtChatArea);

        cbxEstadoJugador.setText("Estoy listo");

        javax.swing.GroupLayout panelSalaEsperaLayout = new javax.swing.GroupLayout(panelSalaEspera);
        panelSalaEspera.setLayout(panelSalaEsperaLayout);
        panelSalaEsperaLayout.setHorizontalGroup(
            panelSalaEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSalaEsperaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSalaEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNumeroJuego, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sPanelJugadores, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                    .addComponent(sPanelChat)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSalaEsperaLayout.createSequentialGroup()
                        .addComponent(txtChatPersonal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxEstadoJugador)))
                .addContainerGap())
        );
        panelSalaEsperaLayout.setVerticalGroup(
            panelSalaEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSalaEsperaLayout.createSequentialGroup()
                .addComponent(lblNumeroJuego)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sPanelJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sPanelChat, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSalaEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtChatPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxEstadoJugador))
                .addContainerGap())
        );

        menuItemOpciones.setText("Opciones");

        menuItemAlias.setText("Elegir alias");
        menuItemOpciones.add(menuItemAlias);

        menuItemSeleccionColor.setText("Seleccionar color");
        menuItemOpciones.add(menuItemSeleccionColor);

        menuItemSalida.setText("Salir de la sala");
        menuItemOpciones.add(menuItemSalida);

        menuItemAdministracionPartida.setText("Administracion de partida");
        menuItemOpciones.add(menuItemAdministracionPartida);

        menuPrincipal.add(menuItemOpciones);

        setJMenuBar(menuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelSalaEspera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelConexion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelConexion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelSalaEspera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConexion;
    private javax.swing.JCheckBox cbxEstadoJugador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblEstadoConexion;
    private javax.swing.JLabel lblNumeroJuego;
    private javax.swing.JMenuItem menuItemAdministracionPartida;
    private javax.swing.JMenuItem menuItemAlias;
    private javax.swing.JMenu menuItemOpciones;
    private javax.swing.JMenuItem menuItemSalida;
    private javax.swing.JMenuItem menuItemSeleccionColor;
    private javax.swing.JMenuBar menuPrincipal;
    private javax.swing.JPanel panelConexion;
    private javax.swing.JPanel panelSalaEspera;
    private javax.swing.JScrollPane sPanelChat;
    private javax.swing.JScrollPane sPanelJugadores;
    private javax.swing.JTable tblJugadores;
    private javax.swing.JTextArea txtChatArea;
    private javax.swing.JTextField txtChatPersonal;
    private javax.swing.JTextField txtDireccionServidor;
    // End of variables declaration//GEN-END:variables
}