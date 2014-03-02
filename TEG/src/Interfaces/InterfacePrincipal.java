package Interfaces;


import cliente.ClienteManager;
import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import juego.estructura.Continente;
import juego.estructura.Jugador;
import juego.estructura.ObjetivoSecreto;
import juego.estructura.Pais;
import juego.mecanicas.turno.SecuenciaTurnos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Emanuel
 */
public class InterfacePrincipal extends javax.swing.JFrame {
    private InformacionDelPais informacion;
    private Jugadores jugadores;
    private InterfaceMapa mapa;
    private Chat chat;
    private Seleccion seleccion;
    private Dados dados;
    
    /**
     * Creates new form GUI
     */
    public InterfacePrincipal() {
        initComponents();
        this.setSize(1330,990);        
        agregarGuis();
        actualizarJugadores(simularJugadores());
    }    
    
   
    public void cargarChat(String msg){
        chat.cargarChat(msg);
    }
    public void actualizarJugadores(ArrayList<Jugador> jug){
        jugadores.actualizarJugadores(jug, obtenerJugadorActual());
    }
    public void enviarChat(String envioChat){
        //aca va a la clase de heraldo
        //String quienDice = ClienteManager.getInstance().getJuego().getJugadores().get(1).g
        String quienDice = "Emanuel: ";
        cargarChat(quienDice+envioChat);
    }
    public void actualizarFichas(ArrayList<Pais> paises){
        mapa.actualizarFichas(paises);
    }
      public void cargarInformacionPais(String pais){
        if(informacion != null) informacion.setDatos(pais, "Emanuel", 2);
    }
    public void cargarDados(int[]ataque, int [] defensa){
        try {          
            dados.setMaximum(true);
            dados.setSize(new Dimension(195,295));
            ubicarGuis(dados,mapa.getWidth()-dados.getWidth(),mapa.getHeight()-dados.getHeight());
        } catch (PropertyVetoException ex) {            
            return;
        } 
        HiloDados hilo = new HiloDados(ataque, defensa,dados);
        hilo.start();       
        
    }
    private Jugador obtenerJugadorActual(){
        //return SecuenciaTurnos.getInstancia().getActual();
        return simularJugadores().get(0);
    }
    private List<ObjetivoSecreto> obtenerObjetivos(){
        return FachadaInterface.obtenerObjetivos();
    }
  
    private ObjetivoSecreto obtenerObjetivo(){
        //return ClienteManager.getInstance().getJugador().getObjetivoSecreto();
        ObjetivoSecreto obj =new ObjetivoSecreto();
        obj.setDescripcion("Conquistar America del Sur.\nConquistar 3 paises de Europa limitrofes entre si.\nConquistar 5 paises de Asia");
        return obj; 
    }
    private ArrayList<Jugador> simularJugadores(){
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>(); 
        Jugador jugador = new Jugador();
        jugador.setColor(Color.red);
        jugador.setNombre("ebroggi");
        jugador.setNroJugador(0);
        
        jugador.getConjuntoPaises().add(new Pais(1,"Argentina",new Continente(1,"America"),true));
        jugador.getConjuntoPaises().add(new Pais(2,"Chile",new Continente(1,"America"),true));
        jugador.getConjuntoPaises().add(new Pais(3,"Venezuela",new Continente(1,"America"),true));
        jugador.setNroJugador(1);
        jugadores.add(jugador);
        
        jugador = new Jugador();
        jugador.setColor(Color.blue);
        jugador.setNombre("dnievas");
        jugador.getConjuntoPaises().add(new Pais(1,"Argentina",new Continente(1,"America"),true));
        jugador.setNroJugador(2);
        jugadores.add(jugador);
        
        jugador = new Jugador();
        jugador.setColor(Color.black);
        jugador.setNombre("hvalenti");
        jugador.getConjuntoPaises().add(new Pais(2,"Chile",new Continente(1,"America"),true));
        jugador.getConjuntoPaises().add(new Pais(3,"Venezuela",new Continente(1,"America"),true));
        jugadores.add(jugador);
        
        jugador = new Jugador();
        jugador.setColor(Color.green);
        jugador.setNombre("valerio");
        jugador.getConjuntoPaises().add(new Pais(2,"Chile",new Continente(1,"America"),true));
        jugador.getConjuntoPaises().add(new Pais(3,"Venezuela",new Continente(1,"America"),true));
        jugador.setNroJugador(3);
        jugadores.add(jugador);
        return jugadores;
        
    }
     private void agregarGuis(){
       mapa =  new InterfaceMapa(this);
       mapa.setVisible(true);
       desktop.add(mapa);
       jugadores =  new Jugadores();
       jugadores.setVisible(true);
       desktop.add(jugadores);
       chat =  new Chat(this);
       chat.setVisible(true);
       desktop.add(chat);
       seleccion =  new Seleccion();
       mapa.setVisible(true);
       desktop.add(seleccion);
       dados = new Dados();
       dados.setVisible(true);
       desktop.add(dados);    
       ubicarGuis(mapa, 0,0);
       ubicarGuis(jugadores,mapa.getSize().width,0);
       ubicarGuis(chat,0,mapa.getSize().height);
       ubicarGuis(seleccion,chat.getSize().width,jugadores.getSize().height); 
       ubicarGuis(dados,mapa.getWidth()-dados.getWidth(),mapa.getHeight()-dados.getHeight());
        try {
            dados.setIcon(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(InterfacePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void ubicarGuis(JInternalFrame frame, int ubicacionH, int ubicacionV){        
        frame.setLocation(ubicacionH, ubicacionV);
        frame.setVisible(true);
       
        try {
            frame.setSelected(true);
        } catch (PropertyVetoException ex) {
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

        desktop = new javax.swing.JDesktopPane();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuObjetivo = new javax.swing.JMenuItem();
        menuObjetivos = new javax.swing.JMenuItem();
        menuInformacion = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("T.E.G  Plan Táctico y Estratégico de la Guerra");

        desktop.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.setBounds(830, 10, 73, 23);
        desktop.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jMenu1.setText("Partida");

        jMenuItem2.setText("Conectar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ver");

        menuObjetivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/objetivo2.jpg"))); // NOI18N
        menuObjetivo.setText("Objetivo");
        menuObjetivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuObjetivoActionPerformed(evt);
            }
        });
        jMenu2.add(menuObjetivo);

        menuObjetivos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/objetivo.jpg"))); // NOI18N
        menuObjetivos.setText("Listar Objetivos");
        menuObjetivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuObjetivosActionPerformed(evt);
            }
        });
        jMenu2.add(menuObjetivos);

        menuInformacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Información.png"))); // NOI18N
        menuInformacion.setText("Información");
        menuInformacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuInformacionActionPerformed(evt);
            }
        });
        jMenu2.add(menuInformacion);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 907, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuObjetivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuObjetivoActionPerformed
       InterfaceObjetivo objetivo =  new InterfaceObjetivo(obtenerObjetivo().getDescripcion(), menuObjetivo);
       menuObjetivo.setEnabled(false);
       objetivo.setVisible(true);
       desktop.add(objetivo);
       ubicarGuis(objetivo,0,425);
       objetivo.requestFocus();
    }//GEN-LAST:event_menuObjetivoActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void menuInformacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuInformacionActionPerformed
        menuInformacion.setEnabled(false);
        informacion = new InformacionDelPais(menuInformacion);
        informacion.setVisible(true);
        desktop.add(informacion);
        ubicarGuis(informacion, 10,10);           
    }//GEN-LAST:event_menuInformacionActionPerformed

    private void menuObjetivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuObjetivosActionPerformed
        InterfaceMostrarTodosObjetivos objetivo =  new InterfaceMostrarTodosObjetivos(FachadaInterface.obtenerObjetivos(),menuObjetivos);
        objetivo.setVisible(true);
        menuObjetivos.setEnabled(false);
        desktop.add(objetivo);
        ubicarGuis(objetivo,0,0);
        objetivo.requestFocus();
        
    }//GEN-LAST:event_menuObjetivosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ArrayList<Pais> paises= new ArrayList<Pais>();
        Jugador jug = new Jugador();
        jug.setColor(Color.black);        
        Pais pais = new Pais(1,"Chile",new Continente(1,"America"),true);
        pais.setJugador(jug);
        pais.setCantidadEjercitos(1);
        paises.add(pais);
        pais = new Pais(2,"Brasil",new Continente(1,"America"),true);
        pais.setJugador(jug);
        pais.setCantidadEjercitos(2);
        paises.add(pais);
        pais = new Pais(3,"Venezuela",new Continente(1,"America"),true);
        pais.setCantidadEjercitos(3);
        pais.setJugador(jug);
        paises.add(pais);
        actualizarFichas(paises);
        cargarDados(simularDados((int)Math.floor(Math.random()*4+1)),simularDados((int)Math.floor(Math.random()*4+1)));
    }//GEN-LAST:event_jButton1ActionPerformed
    private int[] simularDados(int cant){
        
        int[] dados = new int[cant];
        for(int i = 0; i< cant; i++){          
            dados[i] = (int)Math.floor(Math.random()*6+1); 
        }
        return dados;
       
    }
    /**
     * @param args the command line arguments
     */
//   public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(InterfacePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(InterfacePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(InterfacePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(InterfacePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new InterfacePrincipal().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem menuInformacion;
    private javax.swing.JMenuItem menuObjetivo;
    private javax.swing.JMenuItem menuObjetivos;
    // End of variables declaration//GEN-END:variables
}

