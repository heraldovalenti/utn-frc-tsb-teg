/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.DefaultCaret;
import logger.LogItem;
import logger.Loggeable;
import servidor.control.ControlEjecucionServidor;
import servidor.control.ControlInicioJuego;

/**
 *
 * @author heril
 */
public class AdministracionPartida extends javax.swing.JFrame implements Loggeable {

    @Override
    public void procesarLog(LogItem logItem) {
        txtChatArea.append(logItem.toString() + "\n");
    }

    /**
     * Creates new form SalaEspera
     */
    public AdministracionPartida() {
        initComponents();
        addListenerToFrame();
        addListenerToMenuIniciarServidor();
        addListenerToMenuDetenerServidor();
        addListenerToMenuReiniciarServidor();
        addListenerToMenuIniciarPartida();
        addListenerToMenuAgregarIA();
        addListenerToMenuQuitarIA();
        setCaretPolicyToChatArea();
    }

    /**
     * Agrega un listener de cierre al frame.
     */
    private void addListenerToFrame() {
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (!ControlEjecucionServidor.enEjecucion()) {
                    ControlEjecucionServidor.ocultarVentanaAdministracionPartida();
                }
            }
        });
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
    
    private void addListenerToMenuIniciarPartida() {
        this.menuItemComenzarPartida.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ControlInicioJuego.juegoIniciado()) {
                    return;
                }
                ControlInicioJuego.iniciarJuego();
            }
        });
    }

    /**
     * Agrega un listener de click al menu iniciar servidor.
     */
    private void addListenerToMenuIniciarServidor() {
        this.menuItemServidorIniciar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarServidor();
            }
        });
    }

    /**
     * Agrega un listener de click al menu detener servidor.
     */
    private void addListenerToMenuDetenerServidor() {
        this.menuItemServidorDetener.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detenerServidor();
                ControlInicioJuego.reiniciar();
            }
        });
    }

    /**
     * Agrega un listener de click al menu reiniciar servidor.
     */
    private void addListenerToMenuReiniciarServidor() {
        this.menuItemServidorReiniciar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarServidor();
            }
        });
    }
    
    private void addListenerToMenuAgregarIA() {
        this.menuItemAgregarJugadorIA.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarJugadorIA();
            }
        });
    }
    
    private void addListenerToMenuQuitarIA() {
        this.menuItemQuitarJugadorIA.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitarJugadorIA();
            }
        });
    }
            
    /**
     * Metodo para iniciar el servidor. Verifica que el servidor no este ya
     * iniciado.
     */
    private void iniciarServidor() {
        if (ControlEjecucionServidor.enEjecucion()) {
            return;
        }
        ControlEjecucionServidor.iniciarServidor();
    }

    /**
     * Metodo para detener el servidor. Verifica que el servidor no este ya
     * detenido.
     */
    private void detenerServidor() {
        if (!ControlEjecucionServidor.enEjecucion()) {
            return;
        }
        ControlEjecucionServidor.detenerServidor();
    }

    /**
     * Metodo para reiniciar el servidor. Se llaman a los metodos detener y
     * luego iniciar servidor. Se verifica tambien que el servidor este
     * iniciado.
     */
    private void reiniciarServidor() {
        boolean iniciado = ControlEjecucionServidor.enEjecucion();
        if (iniciado) {
            detenerServidor();
            iniciarServidor();
        }
        actualizarEstadoServidor();
    }

    /**
     * Metodo para actualizar la interfaz de acuerdo a si el servidor esta en
     * ejecución o no.
     */
    public void actualizarEstadoServidor() {
        StringBuilder txtEstadoServidor = new StringBuilder("Estado servidor: ");
        txtEstadoServidor.append(ControlEjecucionServidor.estadoServidor());
        StringBuilder txtNumeroDeJuego = new StringBuilder("Número de juego: ");
        Integer nroJuego = ControlEjecucionServidor.numeroDeJuego();
        if (nroJuego != null) {
            txtNumeroDeJuego.append(ControlEjecucionServidor.numeroDeJuego());
        } else {
            txtNumeroDeJuego.append("No definido");
        }
        lblEstadoServidor.setText(txtEstadoServidor.toString());
        lblNumeroJuego.setText(txtNumeroDeJuego.toString());
        actualizarEstadoJugadores(null);
    }

    /**
     *
     */
    private void enviarChat() {
        String chat = txtChatPersonal.getText();
        if (chat != null && chat.isEmpty()) {
            return;
        }
        txtChatArea.append(txtChatPersonal.getText() + "\n");
        DefaultCaret caret = (DefaultCaret) txtChatPersonal.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        txtChatPersonal.setText("");
    }

    public void actualizarEstadoJugadores(TableModel model) {
        try {
            if (model == null) {
                Object[] columnNames = {"Alias", "Tipo Jugador", "Color", "Listo"};
                Object[][] data = new Object[0][4];
                model = new DefaultTableModel(data, columnNames);
            }
            this.tblJugadores.setModel(model);
        } catch (Exception ex) {
            ServerManager.getInstance().getLogger().addLogItem(new LogItem("Error actualizando estado de jugadores", ex));
        }
    }
    
    public void informarJugadoresNoListos() {
        JOptionPane.showMessageDialog(this, 
                "Hay jugadores que todavía no están listos.\n"
                + "No se puede iniciar la partida.", 
                "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void informarJugadoresInsuficientes() {
        JOptionPane.showMessageDialog(this, 
                "La cantidad de jugadores en la sala es insuficiente.\n"
                + "Se necesitan al menos dos jugadores para iniciar la partida.",
                "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void agregarJugadorIA() {
        if (ControlInicioJuego.juegoIniciado()) {
            return;
        }
        ServerManager.getInstance().getGestorClientes().agregarClienteIA();
    }
    
    private void quitarJugadorIA() {
        if (ControlInicioJuego.juegoIniciado()) {
            return;
        }
        ServerManager.getInstance().getGestorClientes().quitarClienteIA();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNumeroJuego = new javax.swing.JLabel();
        sPanelJugadores = new javax.swing.JScrollPane();
        tblJugadores = new javax.swing.JTable();
        txtChatPersonal = new javax.swing.JTextField();
        sPanelChat = new javax.swing.JScrollPane();
        txtChatArea = new javax.swing.JTextArea();
        lblEstadoServidor = new javax.swing.JLabel();
        menuPrincipal = new javax.swing.JMenuBar();
        menuItemSala = new javax.swing.JMenu();
        menuItemEstablecerContraseña = new javax.swing.JMenuItem();
        menuItemCargarPartida = new javax.swing.JMenuItem();
        menuItemGuardarPartida = new javax.swing.JMenuItem();
        menuItemCerrarSala = new javax.swing.JMenuItem();
        menuItemJugadores = new javax.swing.JMenu();
        menuItemAgregarJugadorIA = new javax.swing.JMenuItem();
        menuItemQuitarJugadorIA = new javax.swing.JMenuItem();
        menuItemPartida = new javax.swing.JMenu();
        menuItemComenzarPartida = new javax.swing.JMenuItem();
        menuItemPausarPartida = new javax.swing.JMenuItem();
        menuItemServidor = new javax.swing.JMenu();
        menuItemServidorIniciar = new javax.swing.JMenuItem();
        menuItemServidorDetener = new javax.swing.JMenuItem();
        menuItemServidorReiniciar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TEG - Administracion de partida");
        setResizable(false);

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

        lblEstadoServidor.setText("Estado servidor: <ESTADO_SERVIDOR>");
        lblEstadoServidor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        menuItemSala.setText("Sala");

        menuItemEstablecerContraseña.setText("Establecer contraseña");
        menuItemSala.add(menuItemEstablecerContraseña);

        menuItemCargarPartida.setText("Cargar partida");
        menuItemSala.add(menuItemCargarPartida);

        menuItemGuardarPartida.setText("Guardar partida");
        menuItemSala.add(menuItemGuardarPartida);

        menuItemCerrarSala.setText("Cerrar sala");
        menuItemSala.add(menuItemCerrarSala);

        menuPrincipal.add(menuItemSala);

        menuItemJugadores.setText("Jugadores");

        menuItemAgregarJugadorIA.setText("Agregar jugador IA");
        menuItemJugadores.add(menuItemAgregarJugadorIA);

        menuItemQuitarJugadorIA.setText("Quitar jugador IA");
        menuItemJugadores.add(menuItemQuitarJugadorIA);

        menuPrincipal.add(menuItemJugadores);

        menuItemPartida.setText("Partida");

        menuItemComenzarPartida.setText("Comenzar partida");
        menuItemPartida.add(menuItemComenzarPartida);

        menuItemPausarPartida.setText("Pausar partida");
        menuItemPartida.add(menuItemPausarPartida);

        menuPrincipal.add(menuItemPartida);

        menuItemServidor.setText("Servidor");

        menuItemServidorIniciar.setText("Iniciar");
        menuItemServidor.add(menuItemServidorIniciar);

        menuItemServidorDetener.setText("Detener");
        menuItemServidor.add(menuItemServidorDetener);

        menuItemServidorReiniciar.setText("Reiniciar");
        menuItemServidor.add(menuItemServidorReiniciar);

        menuPrincipal.add(menuItemServidor);

        setJMenuBar(menuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sPanelChat)
                    .addComponent(sPanelJugadores)
                    .addComponent(txtChatPersonal, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblNumeroJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblEstadoServidor)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroJuego)
                    .addComponent(lblEstadoServidor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sPanelJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sPanelChat, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtChatPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblEstadoServidor;
    private javax.swing.JLabel lblNumeroJuego;
    private javax.swing.JMenuItem menuItemAgregarJugadorIA;
    private javax.swing.JMenuItem menuItemCargarPartida;
    private javax.swing.JMenuItem menuItemCerrarSala;
    private javax.swing.JMenuItem menuItemComenzarPartida;
    private javax.swing.JMenuItem menuItemEstablecerContraseña;
    private javax.swing.JMenuItem menuItemGuardarPartida;
    private javax.swing.JMenu menuItemJugadores;
    private javax.swing.JMenu menuItemPartida;
    private javax.swing.JMenuItem menuItemPausarPartida;
    private javax.swing.JMenuItem menuItemQuitarJugadorIA;
    private javax.swing.JMenu menuItemSala;
    private javax.swing.JMenu menuItemServidor;
    private javax.swing.JMenuItem menuItemServidorDetener;
    private javax.swing.JMenuItem menuItemServidorIniciar;
    private javax.swing.JMenuItem menuItemServidorReiniciar;
    private javax.swing.JMenuBar menuPrincipal;
    private javax.swing.JScrollPane sPanelChat;
    private javax.swing.JScrollPane sPanelJugadores;
    private javax.swing.JTable tblJugadores;
    private javax.swing.JTextArea txtChatArea;
    private javax.swing.JTextField txtChatPersonal;
    // End of variables declaration//GEN-END:variables
}
