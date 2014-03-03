/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import cliente.control.ControlColor;
import cliente.control.ControlConexion;
import cliente.control.ControlJuego;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultCaret;
import servidor.control.ControlEjecucionServidor;

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
        addListenerToFrame();
        addListenerToAdministracionPartidaMenu();
        addListenerToAliasMenu();
        addListenerToSalirSalaMenu();
        addListenerToAdministracionSeleccionColorMenu();
        addListenerToChatPersonal();
        setCaretPolicyToChatArea();
    }

    /**
     * Carga el alias en el label correspondiente.
     */
    public void cargarAlias() {
        String aliasActual = ClienteManager.getInstance().getControlAlias().getAlias();
        lblAlias.setText("Alias: " + aliasActual);
    }

    /**
     * Establece que el text area de chat auto-scrollee hacia abajo cada vez que
     * se agrega una linea y es necesario ajustar el scroll para visualizar la
     * nueva linea.
     */
    private void setCaretPolicyToChatArea() {
        DefaultCaret caret = (DefaultCaret) txtChatArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    /**
     * Agrega un listener de tecla enter al campo de texto del chat personal.
     */
    private void addListenerToChatPersonal() {
        this.txtChatPersonal.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    enviarChat();
                }
            }
        });
    }

    /**
     * Agrega un listener de cierre al frame.
     */
    private void addListenerToFrame() {
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                finalizar();
            }
        });
    }

    /**
     * Agrega un listener de click al menu de salir de la sala.
     */
    private void addListenerToSalirSalaMenu() {
        this.menuItemSalida.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salirSala();
            }
        });
    }

    /**
     * Agrega un listener de click al menu de elegir alias.
     */
    private void addListenerToAliasMenu() {
        this.menuItemAlias.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solicitarAlias();
            }
        });
    }

    /**
     * Agrega un listener de click al menu de administracion de partida.
     */
    private void addListenerToAdministracionPartidaMenu() {
        this.menuItemAdministracionPartida.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaAdministracionPartida();
            }
        });
    }

    /**
     * Agrega un listener de click al menu de seleccion de color.
     */
    private void addListenerToAdministracionSeleccionColorMenu() {
        this.menuItemSeleccionColor.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarColor();
            }
        });
    }

    /**
     * Metodo para abrir la ventana de administracion de partida.
     */
    private void abrirVentanaAdministracionPartida() {
        ControlEjecucionServidor.mostrarVentanaAdministracionPartida();
    }

    private void finalizar() {
        int res = JOptionPane.showConfirmDialog(this, "Se terminará cualquier conexión existente.\n"
                + "¿Está seguro que desea cerrar el sistema?",
                "Confirmación requerida", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (res == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
        //se deberia notificar al servidor de la desconexion.
    }

    /**
     * Solicita al jugador el ingreso de un alias.
     */
    private void solicitarAlias() {
        ClienteManager.getInstance().getControlAlias().reingresarAlias();
    }

    public void mostrarChat(String chat) {
        if (chat != null && chat.isEmpty()) {
            return;
        }
        txtChatArea.append(chat + "\n");
    }

    private void enviarChat() {
        String chat = txtChatPersonal.getText();
        if (chat != null && chat.isEmpty()) {
            return;
        }
        ClienteManager.getInstance().enviarChat(chat);
        txtChatPersonal.setText("");
    }

    /**
     * Da la orden al control de conexión de iniciar la conexión con el
     * servidor.
     */
    private void iniciarConexion() {
        String direccion = txtDireccionServidor.getText();
        ControlConexion.conectarServidor(direccion);
    }

    /**
     * Da la orden al control de conexión de finalizar la conexión con el
     * servidor.
     */
    private void salirSala() {
        if (ControlConexion.conectado()) {
            ControlConexion.desconectarServidor();
        }
    }

    /**
     * Actualiza la interfaz de acuerdo al estado de la conexión con el
     * servidor.
     */
    public void actualizarEstadoConexion() {
        lblEstadoConexionInfo.setText(ControlConexion.stringEstadoConexion());
        lblIdConexion.setText("ID Conexión: " + ControlConexion.stringIdentificadorConexion());
        lblNumeroJuego.setText("Número de juego: " + ControlJuego.stringIdentificadorJuego());
        lblColorAsignado.setText("Color: ");
        if (ControlConexion.conectado()) {
            txtDireccionServidor.setEditable(false);
            btnConexion.setEnabled(false);
        } else {
            txtDireccionServidor.setEditable(true);
            btnConexion.setEnabled(true);
        }
    }

    /**
     * Metodo para actualizar la asignacion de color del jugador local.
     */
    public void actualizarAsignacionColor() {
        ControlColor cc = new ControlColor();
        lblColorAsignado.setText("Color: " + cc.getColorAsignado());
    }

    /**
     * Metodo para informar que la asignacion de color ha fallado.
     */
    public void informarAsignacionFallida() {
        JOptionPane.showMessageDialog(this, "El color solicitado ya ha sido "
                + "asignado a otro jugador.\nSe asignara un color automaticamente.",
                "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Metodo para ingresar la seleccion de color del jugador.
     */
    private void seleccionarColor() {
        ControlColor cc = new ControlColor();
        Object[] coloresDisponibles = cc.getColoresDisponibles().toArray();
        Object[] options = cc.getColoresDisponibles().toArray();
        for (int i = 0; i < options.length; i++) {
            options[i] = cc.getNombreColor((Color)options[i]);
        }
        if (options.length > 0) {
            Object initialOption = options[0];
            int selected = JOptionPane.showOptionDialog(this, "Seleccione un color",
                    "Selección de color", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, options, initialOption);
            if (selected != JOptionPane.CLOSED_OPTION) {
                cc.solicitarColor((Color)coloresDisponibles[selected]);
            }
        } else {
            //No hay colores disponibles para seleccionar.
            JOptionPane.showMessageDialog(this, "No hay colores disponibles para seleccionar.",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
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

        panelConexion = new javax.swing.JPanel();
        lblDireccionServidor = new javax.swing.JLabel();
        txtDireccionServidor = new javax.swing.JTextField();
        lblEstadoConexion = new javax.swing.JLabel();
        btnConexion = new javax.swing.JButton();
        lblEstadoConexionInfo = new javax.swing.JLabel();
        panelSalaEspera = new javax.swing.JPanel();
        lblNumeroJuego = new javax.swing.JLabel();
        sPanelJugadores = new javax.swing.JScrollPane();
        tblJugadores = new javax.swing.JTable();
        sPanelChat = new javax.swing.JScrollPane();
        txtChatArea = new javax.swing.JTextArea();
        txtChatPersonal = new javax.swing.JTextField();
        cbxEstadoJugador = new javax.swing.JCheckBox();
        lblAlias = new javax.swing.JLabel();
        lblIdConexion = new javax.swing.JLabel();
        lblColorAsignado = new javax.swing.JLabel();
        menuPrincipal = new javax.swing.JMenuBar();
        menuItemOpciones = new javax.swing.JMenu();
        menuItemAlias = new javax.swing.JMenuItem();
        menuItemSeleccionColor = new javax.swing.JMenuItem();
        menuItemSalida = new javax.swing.JMenuItem();
        menuItemAdministracionPartida = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("TEG - Sala de espera");

        panelConexion.setBorder(javax.swing.BorderFactory.createTitledBorder("Conexión a partida"));

        lblDireccionServidor.setText("IP servidor:");

        txtDireccionServidor.setText("localhost");

        lblEstadoConexion.setText("Estado:");

        btnConexion.setText("Conectar");
        btnConexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConexionActionPerformed(evt);
            }
        });

        lblEstadoConexionInfo.setText("<ESTADO_CONEXION>");

        javax.swing.GroupLayout panelConexionLayout = new javax.swing.GroupLayout(panelConexion);
        panelConexion.setLayout(panelConexionLayout);
        panelConexionLayout.setHorizontalGroup(
            panelConexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConexionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelConexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblEstadoConexion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDireccionServidor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelConexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelConexionLayout.createSequentialGroup()
                        .addComponent(txtDireccionServidor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConexion, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblEstadoConexionInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelConexionLayout.setVerticalGroup(
            panelConexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConexionLayout.createSequentialGroup()
                .addGroup(panelConexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDireccionServidor)
                    .addComponent(txtDireccionServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConexion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstadoConexion)
                    .addComponent(lblEstadoConexionInfo)))
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

        lblAlias.setText("Alias: <ALIAS>");

        lblIdConexion.setText("ID Conexion: <ID_CONEXION>");

        lblColorAsignado.setText("Color: <COLOR_ASIGNADO>");

        javax.swing.GroupLayout panelSalaEsperaLayout = new javax.swing.GroupLayout(panelSalaEspera);
        panelSalaEspera.setLayout(panelSalaEsperaLayout);
        panelSalaEsperaLayout.setHorizontalGroup(
            panelSalaEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSalaEsperaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSalaEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sPanelJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(sPanelChat)
                    .addGroup(panelSalaEsperaLayout.createSequentialGroup()
                        .addComponent(txtChatPersonal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxEstadoJugador))
                    .addGroup(panelSalaEsperaLayout.createSequentialGroup()
                        .addGroup(panelSalaEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblAlias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNumeroJuego, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelSalaEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIdConexion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblColorAsignado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelSalaEsperaLayout.setVerticalGroup(
            panelSalaEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSalaEsperaLayout.createSequentialGroup()
                .addGroup(panelSalaEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroJuego)
                    .addComponent(lblIdConexion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSalaEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAlias)
                    .addComponent(lblColorAsignado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sPanelJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sPanelChat, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

    private void btnConexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConexionActionPerformed
        iniciarConexion();
    }//GEN-LAST:event_btnConexionActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConexion;
    private javax.swing.JCheckBox cbxEstadoJugador;
    private javax.swing.JLabel lblAlias;
    private javax.swing.JLabel lblColorAsignado;
    private javax.swing.JLabel lblDireccionServidor;
    private javax.swing.JLabel lblEstadoConexion;
    private javax.swing.JLabel lblEstadoConexionInfo;
    private javax.swing.JLabel lblIdConexion;
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
