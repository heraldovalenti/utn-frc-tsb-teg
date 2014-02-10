/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import javax.swing.text.DefaultCaret;
import logger.LogItem;

/**
 *
 * @author heril
 */
public class AdministracionPartida extends javax.swing.JFrame {

    private static AdministracionPartida instance = null;

    /**
     * Creates new form SalaEspera
     */
    private AdministracionPartida() {
        initComponents();
        addListenerToFrame();
        addListenerToMenuIniciarServidor();
    }

    public static AdministracionPartida getInstance() {
        if (instance == null) {
            instance = new AdministracionPartida();
        }
        return instance;
    }

    private void addListenerToFrame() {
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ocultarVentana();
            }
        });
    }

    private void addListenerToMenuIniciarServidor() {
        this.menuItemServidorIniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                iniciarServidor();
            }
        });
    }

    /**
     * Metodo para iniciar el servidor.
     */
    private void iniciarServidor() {
        ServerManager.getInstance().iniciarServidor();
    }

    /**
     * Metodo para ocultar este frame.
     */
    public void ocultarVentana() {
        this.setVisible(false);
        ServerManager.getInstance().getLogger().addLogItem(new LogItem("Ventana servidor ha sido oculta."));
    }

    /**
     * Metodo para mostrar este frame.
     */
    public void mostrarVentana() {
        ServerManager.getInstance().getLogger().addLogItem(new LogItem("Ventana servidor ha sido abierta."));
        this.setVisible(true);
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
        menuItemDificultadIA = new javax.swing.JMenuItem();
        menuItemPartida = new javax.swing.JMenu();
        menuItemComenzarPartida = new javax.swing.JMenuItem();
        menuItemPausarPartida = new javax.swing.JMenuItem();
        menuItemServidor = new javax.swing.JMenu();
        menuItemServidorIniciar = new javax.swing.JMenuItem();
        menuItemServidorDetener = new javax.swing.JMenuItem();
        menuItemServidorReiniciar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
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

        menuItemDificultadIA.setText("Establecer dificultad IA");
        menuItemJugadores.add(menuItemDificultadIA);

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
                .addComponent(sPanelJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sPanelChat, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
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
    private javax.swing.JMenuItem menuItemDificultadIA;
    private javax.swing.JMenuItem menuItemEstablecerContraseña;
    private javax.swing.JMenuItem menuItemGuardarPartida;
    private javax.swing.JMenu menuItemJugadores;
    private javax.swing.JMenu menuItemPartida;
    private javax.swing.JMenuItem menuItemPausarPartida;
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