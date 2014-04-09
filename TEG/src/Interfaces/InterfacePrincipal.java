package Interfaces;

import cliente.ClienteManager;

import java.awt.Color;
import java.awt.Desktop;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import juego.estructura.Canjeable;
import juego.estructura.Continente;
import juego.estructura.GestorTarjetas;
import juego.estructura.Jugador;
import juego.estructura.ObjetivoSecreto;
import juego.estructura.Pais;
import logger.LogItem;
import logger.Loggeable;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Emanuel
 */
public class InterfacePrincipal extends javax.swing.JFrame implements Loggeable {

    private InformacionDelPais informacion;
    private Jugadores jugadores;
    private InterfaceMapa mapa;
    private Chat chat;
    private Seleccion seleccion;
    private Dados dados;
    private boolean mostrarFichas = true;
    private boolean mostrarMisiles = true;
    private Refuerzo refuerzo;
    private Pais paisDesde;
    private Pais paisHasta;
    private boolean reagrupar = false;
    private Reagrupar ventanaReagrupar;
    private HiloSonido hiloSonido;

    /**
     * Creates new form GUI
     */
    public InterfacePrincipal() {
        initComponents();
        this.setSize(1330, 990);
        agregarGuis();
        habilitarBotones();
        ClienteManager.getInstance().setInterfacePrincipal(this);
        GestorTarjetas.inicializarGestor();
    }

    public void procesarLog(LogItem logItem) {
        //if (logItem instanceof LogChat) {
        chat.cargarChat(logItem.toString() + "\n");
        //}
    }

    public void pasarRefuerzosPaisGanado(Pais paisDesde, Pais paisHasta, int cantidad) {
        RefuerzoPaisGanado ventana = new RefuerzoPaisGanado(this, true, paisDesde, paisHasta, cantidad);
        ventana.setVisible(true);
    }

    public void cerraVentanaReagrupar() {
        ventanaReagrupar = null;
    }

    private void actualizarJugadores(Jugador actual) {
        jugadores.actualizarJugadores(FachadaInterface.getJugadores(), obtenerJugadorActual());
    }

    public void cargarChat(String msg) {
        chat.cargarChat(msg);
    }

    public void enviarChat(String envioChat) {
        String quienDice = FachadaInterface.getJugadorLocal().getNombre() + ": ";
        FachadaInterface.enviarChat(quienDice + envioChat);
    }

    private void actualizarFicha(Set<Jugador> jugadores) {
        ArrayList<Pais> paises = new ArrayList<Pais>();
        for (Jugador jugador : jugadores) {
            for (Pais pais : jugador.getConjuntoPaises()) {
                paises.add(pais);
            }
        }
        mapa.actualizarFichas(paises, mostrarFichas, mostrarMisiles);
    }

    public void mostrarMensajeGlobal(String msj) {
        mapa.mostraMensajeGlobal(msj);
    }

    public void actualizarFichas() {
        /*ArrayList<Pais> paises= new ArrayList<Pais>();
         Jugador jug = new Jugador();
         jug.setColor(Color.black);        
         Pais pais = new Pais(1,"Chile",new Continente(1,"America"),true);
         pais.setJugador(jug);
         pais.setCantidadEjercitos(1);
         pais.setCantidadMisiles(9);
         paises.add(pais);
         jug.añadirPais(pais);
         pais = new Pais(2,"Brasil",new Continente(1,"America"),true);
         pais.setJugador(jug);
         pais.setCantidadEjercitos(2);
         pais.setCantidadMisiles(5);
         paises.add(pais);
         jug.añadirPais(pais);
         pais = new Pais(3,"Venezuela",new Continente(1,"America"),true);
         pais.setCantidadEjercitos(3);
         pais.setJugador(jug);
         pais.setCantidadMisiles(4);
         paises.add(pais);
         jug.añadirPais(pais);
         Set <Jugador> jugadores = new HashSet<Jugador>();
         jugadores.add(jug);
         actualizarFicha(jgadores); */
        actualizarFicha(FachadaInterface.getJugadores());
    }

    public void cargarInformacionPais(String pais) {
        if (informacion != null) {
            Pais p = obtenerPaisPorNombre(pais);
            if (p == null) {
                return;
            }
            informacion.setDatos(pais, p.getJugador().getNombre(), p.getCantidadEjercitos());
        }

    }

    private Pais obtenerPaisPorNombre(String nombre) {
        Set<Jugador> jugadores = FachadaInterface.getJugadores();
        for (Jugador jugador : jugadores) {
            Set<Pais> paises = jugador.getConjuntoPaises();
            for (Pais pais : paises) {
                if (pais.getNombre().equalsIgnoreCase(nombre)) {
                    return pais;
                }
            }
        }
        return null;
    }

    public void mostrarTarjeta(Canjeable tarj) {
        Tarjeta tarjeta = new Tarjeta(tarj.getNombre());
        tarjeta.setVisible(true);
        desktop.add(tarjeta);
        ubicarGuis(tarjeta, mapa.getWidth() / 2, mapa.getHeight() / 2);
    }

    public void cargarDados(String nombreAtacante, String nombreDefensor, int[] ataque, int[] defensa) {
        try {
            dados.setIcon(false);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
            return;
        }
        dados.actualizarNombres(nombreAtacante, nombreDefensor);
        HiloDados hilo = new HiloDados(this, ataque, defensa, dados);
        hilo.start();

    }

    private Jugador obtenerJugadorActual() {
        return FachadaInterface.getJugadorTurno();

    }

    private List<ObjetivoSecreto> obtenerObjetivos() {
        return FachadaInterface.obtenerObjetivos();
    }

    private ObjetivoSecreto obtenerObjetivo() {
        return ClienteManager.getInstance().getJugador().getObjetivoSecreto();
    }

    private ArrayList<Jugador> simularJugadores() {
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        Jugador jugador = new Jugador();
        jugador.setColor(Color.red);
        jugador.setNombre("ebroggi");
        jugador.setNroJugador(0);

        jugador.getConjuntoPaises().add(new Pais(1, "Argentina", new Continente(1, "America"), true));
        jugador.getConjuntoPaises().add(new Pais(2, "Chile", new Continente(1, "America"), true));
        jugador.getConjuntoPaises().add(new Pais(3, "Venezuela", new Continente(1, "America"), true));
        jugador.setNroJugador(1);
        jugadores.add(jugador);

        jugador = new Jugador();
        jugador.setColor(Color.blue);
        jugador.setNombre("dnievas");
        jugador.getConjuntoPaises().add(new Pais(1, "Argentina", new Continente(1, "America"), true));
        jugador.setNroJugador(2);
        jugadores.add(jugador);

        jugador = new Jugador();
        jugador.setColor(Color.black);
        jugador.setNombre("hvalenti");
        jugador.getConjuntoPaises().add(new Pais(2, "Chile", new Continente(1, "America"), true));
        jugador.getConjuntoPaises().add(new Pais(3, "Venezuela", new Continente(1, "America"), true));
        jugadores.add(jugador);

        jugador = new Jugador();
        jugador.setColor(Color.green);
        jugador.setNombre("valerio");
        jugador.getConjuntoPaises().add(new Pais(2, "Chile", new Continente(1, "America"), true));
        jugador.getConjuntoPaises().add(new Pais(3, "Venezuela", new Continente(1, "America"), true));
        jugador.setNroJugador(3);
        jugadores.add(jugador);
        return jugadores;

    }

    private void agregarGuis() {
        mapa = new InterfaceMapa(this);
        mapa.setVisible(true);
        desktop.add(mapa);
        jugadores = new Jugadores();
        jugadores.setVisible(true);
        desktop.add(jugadores);
        chat = new Chat(this);
        chat.setVisible(true);
        desktop.add(chat);
        seleccion = new Seleccion();
        mapa.setVisible(true);
        desktop.add(seleccion);
        dados = new Dados();
        dados.setVisible(true);
        desktop.add(dados);
        ubicarGuis(mapa, 0, 0);
        ubicarGuis(jugadores, mapa.getSize().width, 0);
        ubicarGuis(chat, 0, mapa.getSize().height);
        ubicarGuis(seleccion, chat.getSize().width, jugadores.getSize().height);
        ubicarGuis(dados, mapa.getWidth() - dados.getWidth(), mapa.getHeight() - dados.getHeight());
        try {
            dados.setIcon(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(InterfacePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ubicarGuis(JInternalFrame frame, int ubicacionH, int ubicacionV) {
        frame.setLocation(ubicacionH, ubicacionV);
        frame.setVisible(true);

        try {
            frame.setSelected(true);
        } catch (PropertyVetoException ex) {
        }
    }

    public void setHiloSonido(HiloSonido hiloSonido) {
        this.hiloSonido = hiloSonido;
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
        desktop = new javax.swing.JDesktopPane();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnAtacar = new javax.swing.JButton();
        btnReagrupar = new javax.swing.JButton();
        btnTarjeta = new javax.swing.JButton();
        btnFinTurno = new javax.swing.JButton();
        btnMision = new javax.swing.JButton();
        btnVerTarjetas = new javax.swing.JButton();
        btnAtacarMisil = new javax.swing.JButton();
        btnSonido = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        btnSituacion = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuObjetivo = new javax.swing.JMenuItem();
        menuObjetivos = new javax.swing.JMenuItem();
        menuInformacion = new javax.swing.JMenuItem();
        menu4 = new javax.swing.JMenuItem();
        menu5 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("T.E.G  Plan Táctico y Estratégico de la Guerra");

        desktop.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        desktop.add(jButton1);
        jButton1.setBounds(830, 13, 73, 20);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 1281, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 507, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE))
        );

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAtacar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/botones/atacar3.png"))); // NOI18N
        btnAtacar.setText("Atacar");
        btnAtacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtacarActionPerformed(evt);
            }
        });

        btnReagrupar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/botones/reagrupar2.png"))); // NOI18N
        btnReagrupar.setText("Reagrupar");
        btnReagrupar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReagruparActionPerformed(evt);
            }
        });

        btnTarjeta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/botones/alaska.png"))); // NOI18N
        btnTarjeta.setText("Recoger");
        btnTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTarjetaActionPerformed(evt);
            }
        });

        btnFinTurno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/botones/chek.png"))); // NOI18N
        btnFinTurno.setText("Fin Turno");
        btnFinTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinTurnoActionPerformed(evt);
            }
        });

        btnMision.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/botones/objetivo.png"))); // NOI18N
        btnMision.setText("Objetivo");
        btnMision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMisionActionPerformed(evt);
            }
        });

        btnVerTarjetas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/botones/alaska.png"))); // NOI18N
        btnVerTarjetas.setText("Tarjetas");
        btnVerTarjetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerTarjetasActionPerformed(evt);
            }
        });

        btnAtacarMisil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/botones/misilChico.png"))); // NOI18N
        btnAtacarMisil.setText("Misil");
        btnAtacarMisil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtacarMisilActionPerformed(evt);
            }
        });

        btnSonido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/botones/ConSonido.png"))); // NOI18N
        btnSonido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSonidoActionPerformed(evt);
            }
        });

        btnSiguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/botones/siguiente4.png"))); // NOI18N
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnSituacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/botones/tarjetaIcono.png"))); // NOI18N
        btnSituacion.setText("Situacion");

        jMenu1.setText("Ver");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reglamento.jpg"))); // NOI18N
        jMenuItem2.setText("Reglamento");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Juego");

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

        menu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/chek.jpg"))); // NOI18N
        menu4.setText("Mosrar Fichas");
        menu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu4ActionPerformed(evt);
            }
        });
        jMenu2.add(menu4);

        menu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/chek.jpg"))); // NOI18N
        menu5.setText("Mostrar Misiles");
        menu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu5ActionPerformed(evt);
            }
        });
        jMenu2.add(menu5);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/botones/alaska.png"))); // NOI18N
        jMenuItem1.setText("Tarjetas Paises");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnMision)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVerTarjetas)
                .addGap(7, 7, 7)
                .addComponent(btnSituacion)
                .addGap(18, 18, 18)
                .addComponent(btnAtacar)
                .addGap(1, 1, 1)
                .addComponent(btnAtacarMisil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReagrupar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTarjeta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFinTurno)
                .addGap(127, 127, 127)
                .addComponent(btnSonido, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAtacar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTarjeta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFinTurno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnVerTarjetas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSituacion))
                        .addComponent(btnAtacarMisil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReagrupar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSonido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnSiguiente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuObjetivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuObjetivoActionPerformed
        InterfaceObjetivo objetivo = new InterfaceObjetivo(obtenerObjetivo().getDescripcion(), menuObjetivo, btnMision);
        menuObjetivo.setEnabled(false);
        btnMision.setEnabled(false);
        objetivo.setVisible(true);
        desktop.add(objetivo);
        ubicarGuis(objetivo, 0, 425);
        objetivo.requestFocus();
    }//GEN-LAST:event_menuObjetivoActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        try {
            File path = new File ("src/reglamento.pdf");
            Desktop.getDesktop().open(path);
       }catch (IOException ex) {
            ex.printStackTrace();
       }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void menuInformacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuInformacionActionPerformed
        menuInformacion.setEnabled(false);
        informacion = new InformacionDelPais(menuInformacion);
        informacion.setVisible(true);
        desktop.add(informacion);
        ubicarGuis(informacion, 10, 10);
    }//GEN-LAST:event_menuInformacionActionPerformed

    private void menuObjetivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuObjetivosActionPerformed
        InterfaceMostrarTodosObjetivos objetivo = new InterfaceMostrarTodosObjetivos(FachadaInterface.obtenerObjetivos(), menuObjetivos);
        objetivo.setVisible(true);
        menuObjetivos.setEnabled(false);
        desktop.add(objetivo);
        ubicarGuis(objetivo, 0, 0);
        objetivo.requestFocus();

    }//GEN-LAST:event_menuObjetivosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        cargarDados("Emanuel", "Graciela", simularDados((int) Math.floor(Math.random() * 4 + 1)), simularDados((int) Math.floor(Math.random() * 4 + 1)));
        Tarjeta tarj = new Tarjeta("Asia");
        tarj.setVisible(true);
        desktop.add(tarj);
        Tarjeta tarj2 = new Tarjeta("canad a");
        tarj2.setVisible(true);
        desktop.add(tarj2);
        ubicarGuis(tarj, mapa.getWidth() / 2, mapa.getHeight() / 2);
        ubicarGuis(tarj2, mapa.getWidth() / 2, mapa.getHeight() / 2);
        HiloSonido sonido = new HiloSonido("src/Sonidos/tuTurno.mp3");
        sonido.start();
        // refuerzo(null);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void menu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu4ActionPerformed
        mostrarFichas = !mostrarFichas;
        String rutaImagen = "";
        if (mostrarFichas) {
            rutaImagen = "/imagenes/chek.png";
        } else {
            rutaImagen = "/imagenes/noChek.png";
        }
        mapa.ocultar(mostrarFichas, mostrarMisiles);
        menu4.setIcon(new javax.swing.ImageIcon(getClass().getResource(rutaImagen)));
    }//GEN-LAST:event_menu4ActionPerformed

    private void menu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu5ActionPerformed
        mostrarMisiles = !mostrarMisiles;
        String rutaImagen = "";
        if (mostrarMisiles) {
            rutaImagen = "/imagenes/chek.png";
        } else {
            rutaImagen = "/imagenes/noChek.png";
        }
        mapa.ocultar(mostrarFichas, mostrarMisiles);
        menu5.setIcon(new javax.swing.ImageIcon(getClass().getResource(rutaImagen)));
    }//GEN-LAST:event_menu5ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Tarjetas tarjetas = new Tarjetas(null, jMenuItem1, btnVerTarjetas);
        jMenuItem1.setEnabled(false);
        btnVerTarjetas.setEnabled(false);
        tarjetas.setVisible(true);
        desktop.add(tarjetas);
        ubicarGuis(tarjetas, mapa.getWidth() / 2, mapa.getHeight() / 2);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnMisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMisionActionPerformed
        if (obtenerObjetivo() == null) {
            JOptionPane.showMessageDialog(this, "Objetivo no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        InterfaceObjetivo objetivo = new InterfaceObjetivo(obtenerObjetivo().getDescripcion(), menuObjetivo, btnMision);
        menuObjetivo.setEnabled(false);
        btnMision.setEnabled(false);
        objetivo.setVisible(true);
        desktop.add(objetivo);
        ubicarGuis(objetivo, 0, 425);
        objetivo.requestFocus();
    }//GEN-LAST:event_btnMisionActionPerformed

    private void btnVerTarjetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerTarjetasActionPerformed
        Tarjetas tarjetas = new Tarjetas(FachadaInterface.obtenerTarjetas(), jMenuItem1, btnVerTarjetas);
        jMenuItem1.setEnabled(false);
        btnVerTarjetas.setEnabled(false);
        tarjetas.setVisible(true);
        desktop.add(tarjetas);
        ubicarGuis(tarjetas, mapa.getWidth() / 2, mapa.getHeight() / 2);
    }//GEN-LAST:event_btnVerTarjetasActionPerformed

    private void btnAtacarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtacarActionPerformed
        FachadaInterface.atacar(paisDesde, paisHasta);
        btnAtacar.setEnabled(false);
    }//GEN-LAST:event_btnAtacarActionPerformed

    private void btnAtacarMisilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtacarMisilActionPerformed
        FachadaInterface.atacarConMisil(paisDesde, paisHasta);
        btnAtacarMisil.setEnabled(false);
    }//GEN-LAST:event_btnAtacarMisilActionPerformed

    private void btnFinTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinTurnoActionPerformed
        if (refuerzo != null) {
            refuerzo.setVisible(false);
        }
        refuerzo = null;
        paisDesde = null;
        paisHasta = null;
        reagrupar = false;
        FachadaInterface.finalizarTurno();
        habilitarBotones();
    }//GEN-LAST:event_btnFinTurnoActionPerformed

    private void btnReagruparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReagruparActionPerformed
        reagrupar = true;
        habilitarBotones();
        FachadaInterface.comenzarReagrupacion();
        ventanaReagrupar = new Reagrupar(this, false, this);
        ventanaReagrupar.setVisible(true);
        btnReagrupar.setEnabled(false);
    }//GEN-LAST:event_btnReagruparActionPerformed

    private void btnSonidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSonidoActionPerformed
        if (hiloSonido == null) {
            hiloSonido = new HiloSonido();
            hiloSonido.start();
            btnSonido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/botones/conSonido.png")));
            btnSiguiente.setEnabled(true);
        } else {
            hiloSonido.stop();
            hiloSonido = null;
            btnSonido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/botones/sinSonido4.png")));
            btnSiguiente.setEnabled(false);
        }
    }//GEN-LAST:event_btnSonidoActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        if (hiloSonido != null) {
            hiloSonido.stop();
            hiloSonido = null;
            hiloSonido = new HiloSonido();
            hiloSonido.start();
        }

    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTarjetaActionPerformed
        FachadaInterface.pedirTarjeta();
    }//GEN-LAST:event_btnTarjetaActionPerformed
    private int[] simularDados(int cant) {

        int[] dados = new int[cant];
        for (int i = 0; i < cant; i++) {
            dados[i] = (int) Math.floor(Math.random() * 6 + 1);
        }
        return dados;

    }

    public void habilitarBotones() {
        if (esMiTurno()) {
            if (FachadaInterface.atacarPermitido()) {
                habilitarBotonesAtaque();
            } else {
                btnAtacar.setEnabled(false);
                btnAtacarMisil.setEnabled(false);
            }
            btnReagrupar.setEnabled(FachadaInterface.reagruparPermitido());
            btnTarjeta.setEnabled(FachadaInterface.solicitarTarjetaPermitido());
            btnFinTurno.setEnabled(FachadaInterface.finTurnoPermitido());
        } else {
            btnAtacar.setEnabled(false);
            btnAtacarMisil.setEnabled(false);
            btnReagrupar.setEnabled(false);
            btnTarjeta.setEnabled(false);
            btnFinTurno.setEnabled(false);
        }
    }

    public void inciarRefuerzo() {
        if (refuerzo == null) {
            refuerzo = new Refuerzo(FachadaInterface.getRefuerzoActual());
            refuerzo.setVisible(true);
            desktop.add(refuerzo);
            ubicarGuis(refuerzo, mapa.getWidth() - refuerzo.getWidth(), 0);
        } else {

        }
    }

    private void habilitarBotonesAtaque() {
        boolean atacar = false;
        boolean misil = false;
        if (FachadaInterface.atacarPermitido()) {
            if (paisDesde != null && paisHasta != null) {
                //aca hay que poner la validacion si se puede atacr de un pais a otro
                if (FachadaInterface.ataquePermitido(paisDesde, paisHasta)) {
                    atacar = true;
                }
                if (FachadaInterface.ataqueConMisilesPermitido(paisDesde, paisHasta)) {
                    misil = true;
                }
            }
        }
        btnAtacar.setEnabled(atacar);
        btnAtacarMisil.setEnabled(misil);

    }

    private void reagrupar(Pais pais) {
        if (ventanaReagrupar == null) {
            ventanaReagrupar = new Reagrupar(this, false, this);
            ventanaReagrupar.setVisible(true);
        }
        ventanaReagrupar.cargarPais(pais);
    }

    public void inicioTurno(Jugador jugador) {
        if (jugador.getNroJugador() == FachadaInterface.getJugadorLocal().getNroJugador()) {
            HiloSonido sonido = new HiloSonido("src/Sonidos/tuTurno.mp3");
            sonido.start();
        }
        actualizarFichas();
        actualizarJugadores(jugador);
        habilitarBotones();
    }

    public void seleccionPais(String p) {

        if (!esMiTurno()) {
            return;
        }
        Pais pais = obtenerPaisPorNombre(p);
        if (pais == null) {
            return;
        }
        if (reagrupar) {
            if (FachadaInterface.esMiPais(pais)) {
                reagrupar(pais);
            }
        } else {
            if (FachadaInterface.incorporarEjercitosPermitido() && refuerzo != null) {
                if (FachadaInterface.esMiPais(pais)) {
                    if (refuerzo != null) {
                        refuerzo.agregarRefuerzo(pais, FachadaInterface.getRefuerzoActual());
                        actualizarFichas();
                    }
                }
            } else {
                if (FachadaInterface.esMiPais(pais)) {
                    paisDesde = pais;
                    seleccion.cargarDesde(pais.getNombre());
                } else {
                    paisHasta = pais;
                    seleccion.cargarHasta(pais.getNombre());
                }
                habilitarBotonesAtaque();
            }
        }

    }

    private void permitirAtaque() {
        if (paisDesde != null && paisHasta != null) {

        }
    }

    private boolean esMiTurno() {
        return FachadaInterface.esMiTurno(FachadaInterface.getJugadorLocal());
    }

    public void terminarRefuerzo() {
        refuerzo.dispose();
        refuerzo = null;
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
    private javax.swing.JButton btnAtacar;
    private javax.swing.JButton btnAtacarMisil;
    private javax.swing.JButton btnFinTurno;
    private javax.swing.JButton btnMision;
    private javax.swing.JButton btnReagrupar;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton btnSituacion;
    private javax.swing.JButton btnSonido;
    private javax.swing.JButton btnTarjeta;
    private javax.swing.JButton btnVerTarjetas;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JMenuItem menu4;
    private javax.swing.JMenuItem menu5;
    private javax.swing.JMenuItem menuInformacion;
    private javax.swing.JMenuItem menuObjetivo;
    private javax.swing.JMenuItem menuObjetivos;
    // End of variables declaration//GEN-END:variables
}
