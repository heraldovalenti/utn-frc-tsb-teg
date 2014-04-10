package Interfaces;


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import juego.estructura.Pais;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Emanuel
 */
public class Mapa extends javax.swing.JPanel {
    private ImageIcon imagen;
    private InterfacePrincipal principal;
    private boolean mostrarFichas = true;
    private boolean mostrarMisiles = true;
    /**
     * Creates new form Mapa
     */
    public Mapa(InterfacePrincipal principal) {
        initComponents();  
        panelCentroAmerica.setSize(200, 115);
        this.principal = principal;
        txtMensajeGlobal.setVisible(false);
        jScrollPane1.setVisible(false);
       
      
   }
   public void mostrarMensajeGloblal(String msj){
       HiloMensajeGlobal hiloMensaje = new HiloMensajeGlobal(txtMensajeGlobal,jScrollPane1, msj);
       hiloMensaje.start();
   }
   private void cargarFichaEnPais(JLabel label, Color col, int cantidad,boolean mostrar){
        String color = obtenerStringColor(col);       
        String rutaImagen;
        label.setForeground(Color.BLACK);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.CENTER);
        if(cantidad == 0){
            rutaImagen = "/imagenes/fichas/fichaGenericaTransparente.png";
           
        }
        else{
            if(col.equals(Color.black)){
                label.setForeground(Color.WHITE);
            }            
           rutaImagen= "/imagenes/fichas/ficha"+color+".png";
           label.setText(String.valueOf(cantidad));
        }
        try{
            
            label.setIcon(new javax.swing.ImageIcon(getClass().getResource(rutaImagen)));
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        label.setVisible(mostrar);
    }
    private String obtenerStringColor(Color col){
        String color ="";  
        if(col.equals(Color.RED)){
            color = "Roja";
            }
        else{
                if(col.equals(Color.BLUE)){
                    color = "Azul";
                }
                else{
                    if(col.equals(Color.black)){
                        color = "Negra";                      
                    }
                    else{
                        if(col.equals(Color.WHITE)){
                            color = "Blanca";                    
                        }
                        else{
                            if(col.equals(Color.green)){
                                color = "Verde";
                            }
                            else{
                                color = "Amarilla";
                            }
                        }
                    }
                }
         }
        return color;
    }
     private void cargarMisilesEnPais(JLabel label, Color col, int cantidad, boolean mostrar){
        String color = obtenerStringColor(col);       
        String rutaImagen;
        Font fuente =new Font("Arial", Font.BOLD, 15);
        label.setFont(fuente);
        label.setForeground(Color.white);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.CENTER);
        if(cantidad == 0){
            label.setIcon(null);
            label.setText("");
            label.setVisible(false);
            return;
        }
        else{
            label.setVisible(mostrar);
            if(col.equals(Color.white)){
                label.setForeground(Color.black);
            }            
           rutaImagen= "/imagenes/misil/misil"+color+".png";
           label.setText(String.valueOf(cantidad));
        }
        try{
            
            label.setIcon(new javax.swing.ImageIcon(getClass().getResource(rutaImagen)));
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }   
  
    public void actualizarFichas(ArrayList<Pais> paises, boolean mostrarFichas, boolean mostrarMisiles){
        Component[] componentes = this.getComponents();        
        for(Pais pais : paises){
            for(int i=0; i<componentes.length;i++){ 
              
                Component[] hijos = new Component[0];
                if(componentes[i] instanceof JLabel) 
                { 
                    JLabel label = ((JLabel) componentes[i]);
                    if(label.getName()!=null){
                           if(pais.getNombre().replaceAll(" ", "").equalsIgnoreCase(label.getName().replaceAll(" ", ""))){
                               cargarFichaEnPais(label, pais.getJugador().getColor(), pais.getCantidadEjercitos(), mostrarFichas);
                           }
                           else{
                               
                               String nombre = "Misil"+pais.getNombre().replaceAll(" ", "");; 
                                                        
                               if(nombre.equalsIgnoreCase(label.getName().replaceAll(" ", ""))){
                                    cargarMisilesEnPais(label, pais.getJugador().getColor(), pais.getCantidadMisiles(), mostrarMisiles);
                               }
                           }

                    }
                } 
                else{
                    if(componentes[i] instanceof JPanel){
                        JPanel panel = ((JPanel) componentes[i]);
                        hijos = panel.getComponents();
                    } 
                }
                for(int j = 0 ; j<hijos.length; j++){      
                     
                    if(hijos[j] instanceof JLabel) {                      
                        JLabel label2= ((JLabel) hijos[j]);
                        if(label2.getName()!=null){
                           if(pais.getNombre().replaceAll(" ", "").equalsIgnoreCase(label2.getName().replaceAll(" ", ""))){
                               cargarFichaEnPais(label2, pais.getJugador().getColor(), pais.getCantidadEjercitos(), mostrarFichas);
                           }
                           else{
                               String nombre = "Misil"+pais.getNombre().replaceAll(" ", ""); 
                                                        
                               if(nombre.equalsIgnoreCase(label2.getName().replaceAll(" ", ""))){
                                    cargarMisilesEnPais(label2, pais.getJugador().getColor(), pais.getCantidadMisiles(),  mostrarMisiles);
                               }
                           }

                    }
                    }
                    
                }
               
            }
        }

    }
   
    private Pais obtenerPais(ArrayList<Pais> paises,String nombre){
        for(Pais pais : paises){
            if(pais.getNombre().equalsIgnoreCase(nombre))return pais;
        }
        return null;
    }
    //Vuelve a mostrar los labels que se ocultan a medida que pasamos con el mouse
    //por encima de un pais
    public void mostrarFichas(boolean mostrarFicha, boolean mostrarMisile){
        this.mostrarFichas = mostrarFicha;
        this.mostrarMisiles = mostrarMisile;
        Component[] componentes = this.getComponents();        
         for(int i=0; i<componentes.length;i++){ 
                Component[] hijos = new Component[0];
                if(componentes[i] instanceof JLabel) 
                { 
                    JLabel label = ((JLabel) componentes[i]);
                     if(label.getName()!=null){
                           if(label.getName().replaceAll(" ", "").contains("Misil")){
                               label.setVisible(mostrarMisiles);
                           }
                           else{
                               label.setVisible(mostrarFichas);
                           }

                    }
                } 
                else{
                    if(componentes[i] instanceof JPanel){
                        JPanel panel = ((JPanel) componentes[i]);
                        hijos = panel.getComponents();
                    } 
                }
                for(int j = 0 ; j<hijos.length; j++){      
                     
                    if(hijos[j] instanceof JLabel) {                      
                         JLabel label2= ((JLabel) hijos[j]);
                         if(label2.getName()!=null){
                            if(label2.getName()!=null){
                                if(label2.getName().replaceAll(" ", "").contains("Misil")){
                                    label2.setVisible(mostrarMisiles);
                                }
                                else{
                                     label2.setVisible(mostrarFichas);
                                }

                            }

                        }
                    }
                    
                }
               
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

        panelAmericaDelNorrte = new javax.swing.JPanel();
        lblLabrador = new javax.swing.JLabel();
        lblAlaska = new javax.swing.JLabel();
        lblTerranova = new javax.swing.JLabel();
        lblNewYork = new javax.swing.JLabel();
        lblGroenlandia = new javax.swing.JLabel();
        lblCanada = new javax.swing.JLabel();
        lblIslaVictoria = new javax.swing.JLabel();
        lblLasVegas = new javax.swing.JLabel();
        lblOregon = new javax.swing.JLabel();
        lblChicago = new javax.swing.JLabel();
        lblCalifornia = new javax.swing.JLabel();
        lblFlorida = new javax.swing.JLabel();
        lblFichaAlaska = new javax.swing.JLabel();
        lblFichaCanda = new javax.swing.JLabel();
        lblFichaOregon = new javax.swing.JLabel();
        lblFichaTerranova = new javax.swing.JLabel();
        lblFichaIslaVictoria = new javax.swing.JLabel();
        lblFichaLabrador = new javax.swing.JLabel();
        lblFichaGroenlandia = new javax.swing.JLabel();
        lblFichaNuevaYork = new javax.swing.JLabel();
        lblFichaChicago = new javax.swing.JLabel();
        lblFichaLasVegas = new javax.swing.JLabel();
        lblFichaFlorida = new javax.swing.JLabel();
        lblFichaCalifornia = new javax.swing.JLabel();
        lblMisilOregon = new javax.swing.JLabel();
        lblMisilCanada = new javax.swing.JLabel();
        lblMisilAlask = new javax.swing.JLabel();
        lblMisilIslaVictoria = new javax.swing.JLabel();
        lblMisilGroenlandia = new javax.swing.JLabel();
        lblMisilTerranova = new javax.swing.JLabel();
        lblMisilChicago = new javax.swing.JLabel();
        lblMisilNuevaYork = new javax.swing.JLabel();
        lblMisilLasVegas = new javax.swing.JLabel();
        lblMisilCalifornia = new javax.swing.JLabel();
        lblMisilLabrador = new javax.swing.JLabel();
        lblMisilFlorida = new javax.swing.JLabel();
        lblFichaIslandia = new javax.swing.JLabel();
        lblFichaNoruega = new javax.swing.JLabel();
        lblFichaBielorrusia = new javax.swing.JLabel();
        lblFichaIrlanda = new javax.swing.JLabel();
        lblFichaGranBretaña = new javax.swing.JLabel();
        lblFichaFrancia = new javax.swing.JLabel();
        lblFichaEspaña = new javax.swing.JLabel();
        lblFichaPortugal = new javax.swing.JLabel();
        lblFichaUcrania = new javax.swing.JLabel();
        lblFichaCroacia = new javax.swing.JLabel();
        lblFichaItalia = new javax.swing.JLabel();
        lblFichaPolonia = new javax.swing.JLabel();
        lblFichaAlemania = new javax.swing.JLabel();
        lblFichaSerbia = new javax.swing.JLabel();
        lblFichaAlbania = new javax.swing.JLabel();
        lblFichaFinlandia = new javax.swing.JLabel();
        lblIslandia = new javax.swing.JLabel();
        lblMisilBielorrusia = new javax.swing.JLabel();
        lblMisilFinlandia = new javax.swing.JLabel();
        lblMisilNoruega = new javax.swing.JLabel();
        lblMisilIslandia = new javax.swing.JLabel();
        lblMisilIrlanda = new javax.swing.JLabel();
        lblMisilPortugal = new javax.swing.JLabel();
        lblMisilEspaña = new javax.swing.JLabel();
        lblMisilFrancia = new javax.swing.JLabel();
        lblMisilPolonia = new javax.swing.JLabel();
        lblMisilAlbania = new javax.swing.JLabel();
        lblMisilSerbia = new javax.swing.JLabel();
        lblMisilAlemania = new javax.swing.JLabel();
        lblMisilItalia = new javax.swing.JLabel();
        lblMisilGranBretaña = new javax.swing.JLabel();
        lblMisilCroacia = new javax.swing.JLabel();
        lblMisilUcrania = new javax.swing.JLabel();
        panelCentroAmerica = new javax.swing.JPanel();
        lblMexico = new javax.swing.JLabel();
        lblHonduras = new javax.swing.JLabel();
        lblElSalvador = new javax.swing.JLabel();
        lblNicaragua = new javax.swing.JLabel();
        lblCuba = new javax.swing.JLabel();
        lblJamaica = new javax.swing.JLabel();
        lblFichaMexico = new javax.swing.JLabel();
        lblFichaHonduras = new javax.swing.JLabel();
        lblFichaElSalvador = new javax.swing.JLabel();
        lblFichaNicaragua = new javax.swing.JLabel();
        lblFichaJamaica = new javax.swing.JLabel();
        lblFichaCuba = new javax.swing.JLabel();
        lblMisilMexico = new javax.swing.JLabel();
        lblMisilCuba = new javax.swing.JLabel();
        lblMisilHonduras = new javax.swing.JLabel();
        lblMisilElSalvador = new javax.swing.JLabel();
        lblMisilNicaragua = new javax.swing.JLabel();
        lblMisilJamaica = new javax.swing.JLabel();
        panelAmericaDelSur = new javax.swing.JPanel();
        lblColombia = new javax.swing.JLabel();
        lblArgentina = new javax.swing.JLabel();
        lblVenezuela = new javax.swing.JLabel();
        lblBrasil = new javax.swing.JLabel();
        lblBolivia = new javax.swing.JLabel();
        lblParaguay = new javax.swing.JLabel();
        lblChile = new javax.swing.JLabel();
        lblUruguay = new javax.swing.JLabel();
        lblFichaColombia = new javax.swing.JLabel();
        lblFichaVenezuela = new javax.swing.JLabel();
        lblFichaBrasil = new javax.swing.JLabel();
        lblFichaParaguay = new javax.swing.JLabel();
        lblFichaUruguay = new javax.swing.JLabel();
        lblFichaArgentina = new javax.swing.JLabel();
        lblFichaBolivia = new javax.swing.JLabel();
        lblFichaChile = new javax.swing.JLabel();
        lblMisilColombia = new javax.swing.JLabel();
        lblMisilBrasil = new javax.swing.JLabel();
        lblMisilVenezuela = new javax.swing.JLabel();
        lblMisilChile = new javax.swing.JLabel();
        lblMisilBolivia = new javax.swing.JLabel();
        lblMisilParaguay = new javax.swing.JLabel();
        lblMisilArgentina = new javax.swing.JLabel();
        lblMisilUruguay = new javax.swing.JLabel();
        panelAfrica = new javax.swing.JPanel();
        lblSahara = new javax.swing.JLabel();
        lblEgipto = new javax.swing.JLabel();
        lblEtiopia = new javax.swing.JLabel();
        lblNigeria = new javax.swing.JLabel();
        lblAngola = new javax.swing.JLabel();
        lblMauritania = new javax.swing.JLabel();
        lblSudafrica = new javax.swing.JLabel();
        lblMadagascar = new javax.swing.JLabel();
        lblFichaSahara = new javax.swing.JLabel();
        lblFichaEgipto = new javax.swing.JLabel();
        lblFichaEtiopia = new javax.swing.JLabel();
        lblFichaNigeria = new javax.swing.JLabel();
        lblFichaAngola = new javax.swing.JLabel();
        lblFichaMauritania = new javax.swing.JLabel();
        lblFichaSudafrica = new javax.swing.JLabel();
        lblFichaMadagascar = new javax.swing.JLabel();
        lblMisilSahara = new javax.swing.JLabel();
        lblMisilEgipto = new javax.swing.JLabel();
        lblMisilEtiopia = new javax.swing.JLabel();
        lblMisilNigeria = new javax.swing.JLabel();
        lblMisilAngola = new javax.swing.JLabel();
        lblMisilMauritania = new javax.swing.JLabel();
        lblMisilSudafrica = new javax.swing.JLabel();
        lblMisilMadagascar = new javax.swing.JLabel();
        panelAsia = new javax.swing.JPanel();
        lblIndia = new javax.swing.JLabel();
        lblVietnam = new javax.swing.JLabel();
        lblMalasia = new javax.swing.JLabel();
        lblCorea = new javax.swing.JLabel();
        lblJapon = new javax.swing.JLabel();
        lblKamtchatka = new javax.swing.JLabel();
        lblChukchi = new javax.swing.JLabel();
        lblChina = new javax.swing.JLabel();
        lblTurquia = new javax.swing.JLabel();
        lblArabia = new javax.swing.JLabel();
        lblIsrael = new javax.swing.JLabel();
        lblIran = new javax.swing.JLabel();
        lblRusia = new javax.swing.JLabel();
        lblChechenia = new javax.swing.JLabel();
        lblSiberia = new javax.swing.JLabel();
        lblFichaChukChi = new javax.swing.JLabel();
        lblFichaKamtchatcka = new javax.swing.JLabel();
        lblFichaSiberia = new javax.swing.JLabel();
        lblFichaChina = new javax.swing.JLabel();
        lblFichaChechenia = new javax.swing.JLabel();
        lblFichaCorea = new javax.swing.JLabel();
        lblFichaMalasia = new javax.swing.JLabel();
        lblFichaRusia = new javax.swing.JLabel();
        lblFichaIran = new javax.swing.JLabel();
        lblFichaTurquia = new javax.swing.JLabel();
        lblFichaVietnam = new javax.swing.JLabel();
        lblFichaIndia = new javax.swing.JLabel();
        lblFichaIsrael = new javax.swing.JLabel();
        lblFichaArabia = new javax.swing.JLabel();
        lblFichaJapon = new javax.swing.JLabel();
        lblMisilCorea = new javax.swing.JLabel();
        lblMisilKamtchatka = new javax.swing.JLabel();
        lblMisilChukchi = new javax.swing.JLabel();
        lblMisilSiberia = new javax.swing.JLabel();
        lblMisilChechenia = new javax.swing.JLabel();
        lblMisilRusia = new javax.swing.JLabel();
        lblMisilIran = new javax.swing.JLabel();
        lblMisilMalasia = new javax.swing.JLabel();
        lblMisilVietnam = new javax.swing.JLabel();
        lblMisilIndia = new javax.swing.JLabel();
        lblMisilTurquia = new javax.swing.JLabel();
        lblMisilIsrael = new javax.swing.JLabel();
        lblMisilArabia = new javax.swing.JLabel();
        lblMisilJapon = new javax.swing.JLabel();
        lblMisilChina = new javax.swing.JLabel();
        panelOceania = new javax.swing.JPanel();
        lblSumatra = new javax.swing.JLabel();
        lblFilipinas = new javax.swing.JLabel();
        lblTonga = new javax.swing.JLabel();
        lblAustralia = new javax.swing.JLabel();
        lblTasmania = new javax.swing.JLabel();
        lblNuevaZelandia = new javax.swing.JLabel();
        lblFichaSumatra = new javax.swing.JLabel();
        lblFichaFilipinas = new javax.swing.JLabel();
        lblFichaTonga = new javax.swing.JLabel();
        lblFichaAustralia = new javax.swing.JLabel();
        lblFichaTasmania = new javax.swing.JLabel();
        lblFichaNuevaZelandia = new javax.swing.JLabel();
        lblMisilAustralia = new javax.swing.JLabel();
        lblMisilFilipinas = new javax.swing.JLabel();
        lblMisilSumatra = new javax.swing.JLabel();
        lblMisilTasmania = new javax.swing.JLabel();
        lblMisilNuevaZelandia = new javax.swing.JLabel();
        lblMisilTonga = new javax.swing.JLabel();
        panelAsia2 = new javax.swing.JPanel();
        lblIrak = new javax.swing.JLabel();
        lblFichaIrak = new javax.swing.JLabel();
        lblMisilIrak = new javax.swing.JLabel();
        lblUcrania = new javax.swing.JLabel();
        lblPolonia = new javax.swing.JLabel();
        lblSerbia = new javax.swing.JLabel();
        lblFinlandia = new javax.swing.JLabel();
        lblNoruega = new javax.swing.JLabel();
        lblGranBretaña = new javax.swing.JLabel();
        lblIrlanda = new javax.swing.JLabel();
        lblAlemania = new javax.swing.JLabel();
        lblFrancia = new javax.swing.JLabel();
        lblEspaña = new javax.swing.JLabel();
        lblPortugal = new javax.swing.JLabel();
        lblItalia = new javax.swing.JLabel();
        lblCroacia = new javax.swing.JLabel();
        lblAlbania = new javax.swing.JLabel();
        lblBielorrusia = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMensajeGlobal = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelAmericaDelNorrte.setEnabled(false);
        panelAmericaDelNorrte.setOpaque(false);
        panelAmericaDelNorrte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAmericaDelNorrte.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLabrador.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblLabrador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLabradorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblLabradorMouseEntered(evt);
            }
        });
        panelAmericaDelNorrte.add(lblLabrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 70, 20));

        lblAlaska.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblAlaska.setToolTipText("");
        lblAlaska.setName(""); // NOI18N
        lblAlaska.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAlaskaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblAlaskaMouseEntered(evt);
            }
        });
        panelAmericaDelNorrte.add(lblAlaska, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 40));

        lblTerranova.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTerranova.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTerranovaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTerranovaMouseEntered(evt);
            }
        });
        panelAmericaDelNorrte.add(lblTerranova, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 90, 80, 76));

        lblNewYork.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblNewYork.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNewYorkMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblNewYorkMouseEntered(evt);
            }
        });
        panelAmericaDelNorrte.add(lblNewYork, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 94, 60));

        lblGroenlandia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblGroenlandia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGroenlandiaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblGroenlandiaMouseEntered(evt);
            }
        });
        panelAmericaDelNorrte.add(lblGroenlandia, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 106, 50));

        lblCanada.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCanada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCanadaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCanadaMouseEntered(evt);
            }
        });
        panelAmericaDelNorrte.add(lblCanada, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 60, 80, 59));

        lblIslaVictoria.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblIslaVictoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIslaVictoriaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIslaVictoriaMouseEntered(evt);
            }
        });
        panelAmericaDelNorrte.add(lblIslaVictoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 83, 37));

        lblLasVegas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblLasVegas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLasVegasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblLasVegasMouseEntered(evt);
            }
        });
        panelAmericaDelNorrte.add(lblLasVegas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 77, 30));

        lblOregon.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblOregon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOregonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblOregonMouseEntered(evt);
            }
        });
        panelAmericaDelNorrte.add(lblOregon, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 88, 56));

        lblChicago.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblChicago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblChicagoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblChicagoMouseEntered(evt);
            }
        });
        panelAmericaDelNorrte.add(lblChicago, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 48, 34));

        lblCalifornia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCalifornia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCaliforniaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCaliforniaMouseEntered(evt);
            }
        });
        panelAmericaDelNorrte.add(lblCalifornia, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 100, 20));

        lblFlorida.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFlorida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFloridaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblFloridaMouseEntered(evt);
            }
        });
        panelAmericaDelNorrte.add(lblFlorida, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 61, 19));

        lblFichaAlaska.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaAlaska.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaRoja6.png"))); // NOI18N
        lblFichaAlaska.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaAlaska.setName("Alaska"); // NOI18N
        panelAmericaDelNorrte.add(lblFichaAlaska, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 15, 30, 30));

        lblFichaCanda.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaCanda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaRoja5.png"))); // NOI18N
        lblFichaCanda.setToolTipText("");
        lblFichaCanda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaCanda.setName("Canada"); // NOI18N
        panelAmericaDelNorrte.add(lblFichaCanda, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        lblFichaOregon.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaOregon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaRoja4.png"))); // NOI18N
        lblFichaOregon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaOregon.setName("Oregon"); // NOI18N
        panelAmericaDelNorrte.add(lblFichaOregon, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 145, -1, 40));

        lblFichaTerranova.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaTerranova.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaRoja3.png"))); // NOI18N
        lblFichaTerranova.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaTerranova.setName("Terranova"); // NOI18N
        panelAmericaDelNorrte.add(lblFichaTerranova, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 115, 40, 40));

        lblFichaIslaVictoria.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaIslaVictoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaRoja2.png"))); // NOI18N
        lblFichaIslaVictoria.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaIslaVictoria.setName("IslaVictoria"); // NOI18N
        panelAmericaDelNorrte.add(lblFichaIslaVictoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 5, 40, 40));

        lblFichaLabrador.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaLabrador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaRoja1.png"))); // NOI18N
        lblFichaLabrador.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaLabrador.setName("Labrador"); // NOI18N
        panelAmericaDelNorrte.add(lblFichaLabrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 45, 30, 30));

        lblFichaGroenlandia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaGroenlandia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaRoja10.png"))); // NOI18N
        lblFichaGroenlandia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaGroenlandia.setName("Groenlandia"); // NOI18N
        panelAmericaDelNorrte.add(lblFichaGroenlandia, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, -1, -1));

        lblFichaNuevaYork.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaNuevaYork.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaRoja9.png"))); // NOI18N
        lblFichaNuevaYork.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaNuevaYork.setName("NuevaYork"); // NOI18N
        panelAmericaDelNorrte.add(lblFichaNuevaYork, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, -1, -1));

        lblFichaChicago.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaChicago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaRoja6.png"))); // NOI18N
        lblFichaChicago.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaChicago.setName("Chicago"); // NOI18N
        panelAmericaDelNorrte.add(lblFichaChicago, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 165, 30, 40));

        lblFichaLasVegas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaLasVegas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaRoja3.png"))); // NOI18N
        lblFichaLasVegas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaLasVegas.setName("LasVegas"); // NOI18N
        panelAmericaDelNorrte.add(lblFichaLasVegas, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 195, 30, 40));

        lblFichaFlorida.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaFlorida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaRoja2.png"))); // NOI18N
        lblFichaFlorida.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaFlorida.setName("Florida"); // NOI18N
        panelAmericaDelNorrte.add(lblFichaFlorida, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 215, -1, 30));

        lblFichaCalifornia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaCalifornia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaRoja1.png"))); // NOI18N
        lblFichaCalifornia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaCalifornia.setName("California"); // NOI18N
        panelAmericaDelNorrte.add(lblFichaCalifornia, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 235, 30, 40));

        lblMisilOregon.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblMisilOregon.setForeground(new java.awt.Color(255, 255, 255));
        lblMisilOregon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilRoja.png"))); // NOI18N
        lblMisilOregon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblMisilOregon.setName("MisilOregon"); // NOI18N
        panelAmericaDelNorrte.add(lblMisilOregon, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 20, 50));

        lblMisilCanada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilRoja.png"))); // NOI18N
        lblMisilCanada.setName("MisilCanada"); // NOI18N
        panelAmericaDelNorrte.add(lblMisilCanada, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 20, -1));

        lblMisilAlask.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilRoja.png"))); // NOI18N
        lblMisilAlask.setName("MisilAlaska"); // NOI18N
        panelAmericaDelNorrte.add(lblMisilAlask, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 20, -1));

        lblMisilIslaVictoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilRoja.png"))); // NOI18N
        lblMisilIslaVictoria.setName("MisilIslaVictoria"); // NOI18N
        panelAmericaDelNorrte.add(lblMisilIslaVictoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, -1, 50));

        lblMisilGroenlandia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilRoja.png"))); // NOI18N
        lblMisilGroenlandia.setName("MisilGroenlandia"); // NOI18N
        panelAmericaDelNorrte.add(lblMisilGroenlandia, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, -1, -1));

        lblMisilTerranova.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilRoja.png"))); // NOI18N
        lblMisilTerranova.setName("MisilTerranova"); // NOI18N
        panelAmericaDelNorrte.add(lblMisilTerranova, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, -1, -1));

        lblMisilChicago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilRoja.png"))); // NOI18N
        lblMisilChicago.setName("MisilChicago"); // NOI18N
        panelAmericaDelNorrte.add(lblMisilChicago, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 20, 40));

        lblMisilNuevaYork.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilRoja.png"))); // NOI18N
        lblMisilNuevaYork.setName("MisilNuevaYork"); // NOI18N
        panelAmericaDelNorrte.add(lblMisilNuevaYork, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, -1, 40));

        lblMisilLasVegas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilRoja.png"))); // NOI18N
        lblMisilLasVegas.setName("MisilLasVegas"); // NOI18N
        panelAmericaDelNorrte.add(lblMisilLasVegas, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 20, -1));

        lblMisilCalifornia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilRoja.png"))); // NOI18N
        lblMisilCalifornia.setName("MisilCalifornia"); // NOI18N
        panelAmericaDelNorrte.add(lblMisilCalifornia, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 20, 30));

        lblMisilLabrador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilRoja.png"))); // NOI18N
        lblMisilLabrador.setName("MisilLabrador"); // NOI18N
        panelAmericaDelNorrte.add(lblMisilLabrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 20, 30));

        lblMisilFlorida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilRoja.png"))); // NOI18N
        lblMisilFlorida.setName("MisilFlorida"); // NOI18N
        panelAmericaDelNorrte.add(lblMisilFlorida, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 210, 20, 40));

        add(panelAmericaDelNorrte, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 21, 360, 270));

        lblFichaIslandia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaIslandia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaGenericaTransparente.png"))); // NOI18N
        lblFichaIslandia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaIslandia.setName("Islandia"); // NOI18N
        add(lblFichaIslandia, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, 40, 40));

        lblFichaNoruega.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaNoruega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaVerde4.png"))); // NOI18N
        lblFichaNoruega.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaNoruega.setName("Noruega"); // NOI18N
        add(lblFichaNoruega, new org.netbeans.lib.awtextra.AbsoluteConstraints(544, 105, 70, 60));

        lblFichaBielorrusia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaBielorrusia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaVerde3.png"))); // NOI18N
        lblFichaBielorrusia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaBielorrusia.setName("Bielorrusia"); // NOI18N
        add(lblFichaBielorrusia, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, -1, -1));

        lblFichaIrlanda.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaIrlanda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaVerde6.png"))); // NOI18N
        lblFichaIrlanda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaIrlanda.setName("Irlanda"); // NOI18N
        add(lblFichaIrlanda, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 205, 30, 40));

        lblFichaGranBretaña.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaGranBretaña.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaVerde7.png"))); // NOI18N
        lblFichaGranBretaña.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaGranBretaña.setName("GranBretaña"); // NOI18N
        add(lblFichaGranBretaña, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, -1, -1));

        lblFichaFrancia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaFrancia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaVerde1.png"))); // NOI18N
        lblFichaFrancia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaFrancia.setName("Francia"); // NOI18N
        add(lblFichaFrancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 295, 30, 40));

        lblFichaEspaña.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaEspaña.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaVerde2.png"))); // NOI18N
        lblFichaEspaña.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaEspaña.setName("España"); // NOI18N
        add(lblFichaEspaña, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 355, 50, 30));

        lblFichaPortugal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaPortugal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaVerde5.png"))); // NOI18N
        lblFichaPortugal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaPortugal.setName("Portugal"); // NOI18N
        add(lblFichaPortugal, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 350, 30, -1));

        lblFichaUcrania.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaUcrania.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaVerde7.png"))); // NOI18N
        lblFichaUcrania.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaUcrania.setName("Ucrania"); // NOI18N
        add(lblFichaUcrania, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 185, -1, 50));

        lblFichaCroacia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaCroacia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaVerde8.png"))); // NOI18N
        lblFichaCroacia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaCroacia.setName("Croacia"); // NOI18N
        add(lblFichaCroacia, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 305, 30, 50));

        lblFichaItalia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaItalia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaVerde9.png"))); // NOI18N
        lblFichaItalia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaItalia.setName("Italia"); // NOI18N
        add(lblFichaItalia, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 345, -1, 40));

        lblFichaPolonia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaPolonia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaVerde3.png"))); // NOI18N
        lblFichaPolonia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaPolonia.setName("Polonia"); // NOI18N
        add(lblFichaPolonia, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 215, 30, 40));

        lblFichaAlemania.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaAlemania.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaVerde10.png"))); // NOI18N
        lblFichaAlemania.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaAlemania.setName("Alemania"); // NOI18N
        add(lblFichaAlemania, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 235, 30, 50));

        lblFichaSerbia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaSerbia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaVerde7.png"))); // NOI18N
        lblFichaSerbia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaSerbia.setName("Serbia"); // NOI18N
        add(lblFichaSerbia, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 255, 30, 40));

        lblFichaAlbania.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaAlbania.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaVerde4.png"))); // NOI18N
        lblFichaAlbania.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaAlbania.setName("Albania"); // NOI18N
        add(lblFichaAlbania, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 275, 30, 40));

        lblFichaFinlandia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaFinlandia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaVerde7.png"))); // NOI18N
        lblFichaFinlandia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaFinlandia.setName("Finlandia"); // NOI18N
        add(lblFichaFinlandia, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 130, 30, 40));

        lblIslandia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblIslandia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIslandiaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIslandiaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        add(lblIslandia, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, 70, 64));

        lblMisilBielorrusia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilVerde.png"))); // NOI18N
        lblMisilBielorrusia.setName("MisilBielorrusia"); // NOI18N
        add(lblMisilBielorrusia, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 90, -1, 50));

        lblMisilFinlandia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilVerde.png"))); // NOI18N
        lblMisilFinlandia.setName("MisilFinlandia"); // NOI18N
        add(lblMisilFinlandia, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, 20, 40));

        lblMisilNoruega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilVerde.png"))); // NOI18N
        lblMisilNoruega.setName("MisilNoruega"); // NOI18N
        add(lblMisilNoruega, new org.netbeans.lib.awtextra.AbsoluteConstraints(545, 90, 20, 40));

        lblMisilIslandia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilVerde.png"))); // NOI18N
        lblMisilIslandia.setName("MisilIslandia"); // NOI18N
        add(lblMisilIslandia, new org.netbeans.lib.awtextra.AbsoluteConstraints(455, 90, 20, 50));

        lblMisilIrlanda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilVerde.png"))); // NOI18N
        lblMisilIrlanda.setToolTipText("");
        lblMisilIrlanda.setName("MisilIrlanda"); // NOI18N
        add(lblMisilIrlanda, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 220, 20, 40));

        lblMisilPortugal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilVerde.png"))); // NOI18N
        lblMisilPortugal.setName("MisilPortugal"); // NOI18N
        add(lblMisilPortugal, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 380, 20, 40));

        lblMisilEspaña.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilVerde.png"))); // NOI18N
        lblMisilEspaña.setName("MisilEspaña"); // NOI18N
        add(lblMisilEspaña, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 350, 20, 40));

        lblMisilFrancia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilVerde.png"))); // NOI18N
        lblMisilFrancia.setName("MisilFrancia"); // NOI18N
        add(lblMisilFrancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, 20, 30));

        lblMisilPolonia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilVerde.png"))); // NOI18N
        lblMisilPolonia.setName("MisilPolonia"); // NOI18N
        add(lblMisilPolonia, new org.netbeans.lib.awtextra.AbsoluteConstraints(645, 240, -1, 50));

        lblMisilAlbania.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilVerde.png"))); // NOI18N
        lblMisilAlbania.setName("MisilAlbania"); // NOI18N
        add(lblMisilAlbania, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 310, 20, 40));

        lblMisilSerbia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilVerde.png"))); // NOI18N
        lblMisilSerbia.setName("MisilSerbia"); // NOI18N
        add(lblMisilSerbia, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 260, 20, 40));

        lblMisilAlemania.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilVerde.png"))); // NOI18N
        lblMisilAlemania.setToolTipText("");
        lblMisilAlemania.setName("MisilAlemania"); // NOI18N
        add(lblMisilAlemania, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 270, 20, 40));

        lblMisilItalia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilVerde.png"))); // NOI18N
        lblMisilItalia.setName("MisilItalia"); // NOI18N
        add(lblMisilItalia, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 320, 20, 30));

        lblMisilGranBretaña.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilVerde.png"))); // NOI18N
        lblMisilGranBretaña.setName("MisilGranBretaña"); // NOI18N
        add(lblMisilGranBretaña, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 200, 20, 30));

        lblMisilCroacia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilVerde.png"))); // NOI18N
        lblMisilCroacia.setName("MisilCroacia"); // NOI18N
        add(lblMisilCroacia, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 340, 20, 40));

        lblMisilUcrania.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilVerde.png"))); // NOI18N
        lblMisilUcrania.setName("MisilUcrania"); // NOI18N
        add(lblMisilUcrania, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 150, -1, 50));

        panelCentroAmerica.setOpaque(false);
        panelCentroAmerica.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMexico.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblMexico.setName(""); // NOI18N
        lblMexico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMexicoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblMexicoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelCentroAmerica.add(lblMexico, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 50));

        lblHonduras.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblHonduras.setName(""); // NOI18N
        lblHonduras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHondurasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblHondurasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelCentroAmerica.add(lblHonduras, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 30, 30));

        lblElSalvador.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblElSalvador.setName(""); // NOI18N
        lblElSalvador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblElSalvadorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblElSalvadorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelCentroAmerica.add(lblElSalvador, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 40, 20));

        lblNicaragua.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblNicaragua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNicaraguaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblNicaraguaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelCentroAmerica.add(lblNicaragua, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 60, 40));

        lblCuba.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCuba.setName(""); // NOI18N
        lblCuba.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCubaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCubaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelCentroAmerica.add(lblCuba, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 70, 20));

        lblJamaica.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblJamaica.setName(""); // NOI18N
        lblJamaica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblJamaicaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblJamaicaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelCentroAmerica.add(lblJamaica, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 50, 40));

        lblFichaMexico.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaMexico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaAzul7.png"))); // NOI18N
        lblFichaMexico.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaMexico.setName("Mexico"); // NOI18N
        panelCentroAmerica.add(lblFichaMexico, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, -1, 30));

        lblFichaHonduras.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaHonduras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaAzul6.png"))); // NOI18N
        lblFichaHonduras.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaHonduras.setName("Honduras"); // NOI18N
        panelCentroAmerica.add(lblFichaHonduras, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 50, 30, 30));

        lblFichaElSalvador.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaElSalvador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaAzul4.png"))); // NOI18N
        lblFichaElSalvador.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaElSalvador.setName("ElSalvador"); // NOI18N
        panelCentroAmerica.add(lblFichaElSalvador, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 30, -1));

        lblFichaNicaragua.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaNicaragua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaAzul7.png"))); // NOI18N
        lblFichaNicaragua.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaNicaragua.setName("Nicaragua"); // NOI18N
        panelCentroAmerica.add(lblFichaNicaragua, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 95, 40, 40));

        lblFichaJamaica.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaJamaica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaAzul4.png"))); // NOI18N
        lblFichaJamaica.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaJamaica.setName("Jamaica"); // NOI18N
        panelCentroAmerica.add(lblFichaJamaica, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 65, 30, 40));

        lblFichaCuba.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaCuba.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaAzul8.png"))); // NOI18N
        lblFichaCuba.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaCuba.setName("Cuba"); // NOI18N
        panelCentroAmerica.add(lblFichaCuba, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, -5, -1, 40));

        lblMisilMexico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilAzul.png"))); // NOI18N
        lblMisilMexico.setName("MisilMexico"); // NOI18N
        panelCentroAmerica.add(lblMisilMexico, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        lblMisilCuba.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilAzul.png"))); // NOI18N
        lblMisilCuba.setName("MisilCuba"); // NOI18N
        panelCentroAmerica.add(lblMisilCuba, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, 50));

        lblMisilHonduras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilAzul.png"))); // NOI18N
        lblMisilHonduras.setName("MisilHonduras"); // NOI18N
        panelCentroAmerica.add(lblMisilHonduras, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 30, -1, 40));

        lblMisilElSalvador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilAzul.png"))); // NOI18N
        lblMisilElSalvador.setName("MisilElSalvador"); // NOI18N
        panelCentroAmerica.add(lblMisilElSalvador, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 20, 50));

        lblMisilNicaragua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilAzul.png"))); // NOI18N
        lblMisilNicaragua.setName("MisilNicaragua"); // NOI18N
        panelCentroAmerica.add(lblMisilNicaragua, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, 40));

        lblMisilJamaica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilAzul.png"))); // NOI18N
        lblMisilJamaica.setToolTipText("");
        lblMisilJamaica.setName("MisilJamaica"); // NOI18N
        panelCentroAmerica.add(lblMisilJamaica, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 70, 20, 40));

        add(panelCentroAmerica, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 217, 140));

        panelAmericaDelSur.setOpaque(false);
        panelAmericaDelSur.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblColombia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblColombia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblColombiaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblColombiaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblColombiaMouseExited(evt);
            }
        });
        panelAmericaDelSur.add(lblColombia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 14, 100, 50));

        lblArgentina.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblArgentina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblArgentinaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblArgentinaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAmericaDelSur.add(lblArgentina, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 30, 80));

        lblVenezuela.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblVenezuela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVenezuelaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblVenezuelaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblColombiaMouseExited(evt);
            }
        });
        panelAmericaDelSur.add(lblVenezuela, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 60, 40));

        lblBrasil.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblBrasil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBrasilMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblBrasilMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblColombiaMouseExited(evt);
            }
        });
        panelAmericaDelSur.add(lblBrasil, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 90, 50));

        lblBolivia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblBolivia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBoliviaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblBoliviaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAmericaDelSur.add(lblBolivia, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 40, 40));

        lblParaguay.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblParaguay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblParaguayMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblParaguayMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAmericaDelSur.add(lblParaguay, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 40, 40));

        lblChile.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblChile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblChileMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblChileMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAmericaDelSur.add(lblChile, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 30, 90));

        lblUruguay.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblUruguay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUruguayMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblUruguayMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAmericaDelSur.add(lblUruguay, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 50, 30));

        lblFichaColombia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaColombia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaAmarilla1.png"))); // NOI18N
        lblFichaColombia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaColombia.setName("Colombia"); // NOI18N
        panelAmericaDelSur.add(lblFichaColombia, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 15, 30, 50));

        lblFichaVenezuela.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaVenezuela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaAmarilla10.png"))); // NOI18N
        lblFichaVenezuela.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaVenezuela.setName("Venezuela"); // NOI18N
        panelAmericaDelSur.add(lblFichaVenezuela, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 40, 30));

        lblFichaBrasil.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaBrasil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaAmarilla3.png"))); // NOI18N
        lblFichaBrasil.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaBrasil.setName("Brasil"); // NOI18N
        panelAmericaDelSur.add(lblFichaBrasil, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 85, 40, 40));

        lblFichaParaguay.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaParaguay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaAmarilla6.png"))); // NOI18N
        lblFichaParaguay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaParaguay.setName("Paraguay"); // NOI18N
        panelAmericaDelSur.add(lblFichaParaguay, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 125, 30, 40));

        lblFichaUruguay.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaUruguay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaAmarilla5.png"))); // NOI18N
        lblFichaUruguay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaUruguay.setName("Uruguay"); // NOI18N
        panelAmericaDelSur.add(lblFichaUruguay, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 165, 30, 40));

        lblFichaArgentina.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaArgentina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaAmarilla2.png"))); // NOI18N
        lblFichaArgentina.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaArgentina.setName("Argentina"); // NOI18N
        panelAmericaDelSur.add(lblFichaArgentina, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 185, -1, 40));

        lblFichaBolivia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaBolivia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaAmarilla4.png"))); // NOI18N
        lblFichaBolivia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaBolivia.setName("Bolivia"); // NOI18N
        panelAmericaDelSur.add(lblFichaBolivia, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 30, -1));

        lblFichaChile.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaChile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaAmarilla9.png"))); // NOI18N
        lblFichaChile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaChile.setName("Chile"); // NOI18N
        panelAmericaDelSur.add(lblFichaChile, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 135, -1, 40));

        lblMisilColombia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilAmarilla.png"))); // NOI18N
        lblMisilColombia.setName("MisilColombia"); // NOI18N
        panelAmericaDelSur.add(lblMisilColombia, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 20, 40));

        lblMisilBrasil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilAmarilla.png"))); // NOI18N
        lblMisilBrasil.setName("MisilBrasil"); // NOI18N
        panelAmericaDelSur.add(lblMisilBrasil, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 20, -1));

        lblMisilVenezuela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilAmarilla.png"))); // NOI18N
        lblMisilVenezuela.setName("MisilVenezuela"); // NOI18N
        panelAmericaDelSur.add(lblMisilVenezuela, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, 60));

        lblMisilChile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilAmarilla.png"))); // NOI18N
        lblMisilChile.setName("MisilChile"); // NOI18N
        panelAmericaDelSur.add(lblMisilChile, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 20, -1));

        lblMisilBolivia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilAmarilla.png"))); // NOI18N
        lblMisilBolivia.setName("MisilBolivia"); // NOI18N
        panelAmericaDelSur.add(lblMisilBolivia, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 20, -1));

        lblMisilParaguay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilAmarilla.png"))); // NOI18N
        lblMisilParaguay.setName("MisilParaguay"); // NOI18N
        panelAmericaDelSur.add(lblMisilParaguay, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 120, 20, 50));

        lblMisilArgentina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilAmarilla.png"))); // NOI18N
        lblMisilArgentina.setName("MisilArgentina"); // NOI18N
        panelAmericaDelSur.add(lblMisilArgentina, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 160, 20, 40));

        lblMisilUruguay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilAmarilla.png"))); // NOI18N
        lblMisilUruguay.setName("MisilUruguay"); // NOI18N
        panelAmericaDelSur.add(lblMisilUruguay, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 20, -1));

        add(panelAmericaDelSur, new org.netbeans.lib.awtextra.AbsoluteConstraints(107, 430, 240, 280));

        panelAfrica.setOpaque(false);
        panelAfrica.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSahara.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblSahara.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSaharaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblSaharaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAfrica.add(lblSahara, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 14, 70, 80));

        lblEgipto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblEgipto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEgiptoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblEgiptoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAfrica.add(lblEgipto, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 16, 100, 40));

        lblEtiopia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblEtiopia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEtiopiaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblEtiopiaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAfrica.add(lblEtiopia, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 64, 100, 30));

        lblNigeria.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblNigeria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNigeriaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblNigeriaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAfrica.add(lblNigeria, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 80, 40));

        lblAngola.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblAngola.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAngolaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblAngolaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAfrica.add(lblAngola, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 70, 30));

        lblMauritania.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblMauritania.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMauritaniaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblMauritaniaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAfrica.add(lblMauritania, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 70, 30));

        lblSudafrica.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblSudafrica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSudafricaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblSudafricaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAfrica.add(lblSudafrica, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 50, 50));

        lblMadagascar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblMadagascar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMadagascarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblMadagascarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAfrica.add(lblMadagascar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 60, 50));

        lblFichaSahara.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaSahara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaNegra10.png"))); // NOI18N
        lblFichaSahara.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaSahara.setName("Sahara"); // NOI18N
        panelAfrica.add(lblFichaSahara, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 45, 30, 40));

        lblFichaEgipto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaEgipto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaNegra8.png"))); // NOI18N
        lblFichaEgipto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaEgipto.setName("Egipto"); // NOI18N
        panelAfrica.add(lblFichaEgipto, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, -1, -1));

        lblFichaEtiopia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaEtiopia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaNegra7.png"))); // NOI18N
        lblFichaEtiopia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaEtiopia.setName("Etiopia"); // NOI18N
        panelAfrica.add(lblFichaEtiopia, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, -1, -1));

        lblFichaNigeria.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaNigeria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaNegra6.png"))); // NOI18N
        lblFichaNigeria.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaNigeria.setName("Nigeria"); // NOI18N
        panelAfrica.add(lblFichaNigeria, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 105, -1, 40));

        lblFichaAngola.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaAngola.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaNegra8.png"))); // NOI18N
        lblFichaAngola.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaAngola.setName("Angola"); // NOI18N
        panelAfrica.add(lblFichaAngola, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 85, -1, 40));

        lblFichaMauritania.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaMauritania.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaNegra10.png"))); // NOI18N
        lblFichaMauritania.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaMauritania.setName("Mauritania"); // NOI18N
        panelAfrica.add(lblFichaMauritania, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 30, -1));

        lblFichaSudafrica.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaSudafrica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaNegra7.png"))); // NOI18N
        lblFichaSudafrica.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaSudafrica.setName("Sudafrica"); // NOI18N
        panelAfrica.add(lblFichaSudafrica, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 180, 30, 30));

        lblFichaMadagascar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaMadagascar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaNegra9.png"))); // NOI18N
        lblFichaMadagascar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaMadagascar.setName("Madagascar"); // NOI18N
        panelAfrica.add(lblFichaMadagascar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 165, 30, 30));

        lblMisilSahara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilNegra.png"))); // NOI18N
        lblMisilSahara.setName("MisilSahara"); // NOI18N
        panelAfrica.add(lblMisilSahara, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 20, 50));

        lblMisilEgipto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilNegra.png"))); // NOI18N
        lblMisilEgipto.setName("MisilEgipto"); // NOI18N
        panelAfrica.add(lblMisilEgipto, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, -1, 30));

        lblMisilEtiopia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilNegra.png"))); // NOI18N
        lblMisilEtiopia.setName("MisilEtiopia"); // NOI18N
        panelAfrica.add(lblMisilEtiopia, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, -1, 30));

        lblMisilNigeria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilNegra.png"))); // NOI18N
        lblMisilNigeria.setToolTipText("");
        lblMisilNigeria.setName("MisilNigeria"); // NOI18N
        panelAfrica.add(lblMisilNigeria, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, -1, 40));

        lblMisilAngola.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilNegra.png"))); // NOI18N
        lblMisilAngola.setName("MisilAngola"); // NOI18N
        panelAfrica.add(lblMisilAngola, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, -1, 40));

        lblMisilMauritania.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilNegra.png"))); // NOI18N
        lblMisilMauritania.setName("MisilMauritania"); // NOI18N
        panelAfrica.add(lblMisilMauritania, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 20, 50));

        lblMisilSudafrica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilNegra.png"))); // NOI18N
        lblMisilSudafrica.setName("MisilSudafrica"); // NOI18N
        panelAfrica.add(lblMisilSudafrica, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 150, -1, 50));

        lblMisilMadagascar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilNegra.png"))); // NOI18N
        lblMisilMadagascar.setName("MisilMadagascar"); // NOI18N
        panelAfrica.add(lblMisilMadagascar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, -1, 50));

        add(panelAfrica, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 420, 280, 240));

        panelAsia.setOpaque(false);
        panelAsia.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIndia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblIndia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIndiaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIndiaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAsia.add(lblIndia, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 330, 36, 58));

        lblVietnam.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblVietnam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVietnamMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblVietnamMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAsia.add(lblVietnam, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, 70, 70));

        lblMalasia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblMalasia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMalasiaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblMalasiaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAsia.add(lblMalasia, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 264, 50, 50));

        lblCorea.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCorea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCoreaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCoreaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAsia.add(lblCorea, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 50, 80));

        lblJapon.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblJapon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblJaponMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblJaponMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAsia.add(lblJapon, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 20, 90));

        lblKamtchatka.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblKamtchatka.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblKamtchatkaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblKamtchatkaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAsia.add(lblKamtchatka, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 50, 100));

        lblChukchi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblChukchi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblChukchiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblChukchiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAsia.add(lblChukchi, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 60, 40));

        lblChina.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblChina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblChinaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblChinaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAsia.add(lblChina, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 60, 130));

        lblTurquia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTurquia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTurquiaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTurquiaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAsia.add(lblTurquia, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 40, 50));

        lblArabia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblArabia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblArabiaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblArabiaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAsia.add(lblArabia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 50, 50));

        lblIsrael.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblIsrael.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIsraelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIsraelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAsia.add(lblIsrael, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 40, 50));

        lblIran.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblIran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIranMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIranMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAsia.add(lblIran, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 30, 40));

        lblRusia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblRusia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRusiaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblRusiaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAsia.add(lblRusia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 50, 70));

        lblChechenia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblChechenia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblChecheniaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblChecheniaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAsia.add(lblChechenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 60, 50));

        lblSiberia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblSiberia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSiberiaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblSiberiaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelAsia.add(lblSiberia, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 50, 40));

        lblFichaChukChi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaChukChi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaAzul5.png"))); // NOI18N
        lblFichaChukChi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaChukChi.setName("Chukchi"); // NOI18N
        panelAsia.add(lblFichaChukChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 40, 30));

        lblFichaKamtchatcka.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaKamtchatcka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaAzul6.png"))); // NOI18N
        lblFichaKamtchatcka.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaKamtchatcka.setName("Kamtchatka"); // NOI18N
        panelAsia.add(lblFichaKamtchatcka, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, -1, -1));

        lblFichaSiberia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaSiberia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaAzul7.png"))); // NOI18N
        lblFichaSiberia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaSiberia.setName("Siberia"); // NOI18N
        panelAsia.add(lblFichaSiberia, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 5, 30, 40));

        lblFichaChina.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaChina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaAmarilla4.png"))); // NOI18N
        lblFichaChina.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaChina.setName("China"); // NOI18N
        panelAsia.add(lblFichaChina, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 155, -1, 40));

        lblFichaChechenia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaChechenia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaAmarilla9.png"))); // NOI18N
        lblFichaChechenia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaChechenia.setName("Chechenia"); // NOI18N
        panelAsia.add(lblFichaChechenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 30, -1));

        lblFichaCorea.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaCorea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaAmarilla7.png"))); // NOI18N
        lblFichaCorea.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaCorea.setName("Corea"); // NOI18N
        panelAsia.add(lblFichaCorea, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 30, -1));

        lblFichaMalasia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaMalasia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaBlanca2.png"))); // NOI18N
        lblFichaMalasia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaMalasia.setName("Malasia"); // NOI18N
        panelAsia.add(lblFichaMalasia, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 275, 30, 40));

        lblFichaRusia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaRusia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaNegra4.png"))); // NOI18N
        lblFichaRusia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaRusia.setName("Rusia"); // NOI18N
        panelAsia.add(lblFichaRusia, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 135, -1, 30));

        lblFichaIran.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaIran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaRoja5.png"))); // NOI18N
        lblFichaIran.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaIran.setName("Iran"); // NOI18N
        panelAsia.add(lblFichaIran, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 215, 30, 30));

        lblFichaTurquia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaTurquia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaRoja5.png"))); // NOI18N
        lblFichaTurquia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaTurquia.setName("Turquia"); // NOI18N
        panelAsia.add(lblFichaTurquia, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 265, 30, 70));

        lblFichaVietnam.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaVietnam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaRoja7.png"))); // NOI18N
        lblFichaVietnam.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaVietnam.setName("Vietnam"); // NOI18N
        panelAsia.add(lblFichaVietnam, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 315, 30, 40));

        lblFichaIndia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaIndia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaRoja9.png"))); // NOI18N
        lblFichaIndia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaIndia.setName("India"); // NOI18N
        panelAsia.add(lblFichaIndia, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 335, -1, 40));

        lblFichaIsrael.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaIsrael.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaBlanca4.png"))); // NOI18N
        lblFichaIsrael.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaIsrael.setName("Israel"); // NOI18N
        panelAsia.add(lblFichaIsrael, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 325, -1, 40));

        lblFichaArabia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaArabia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaBlanca9.png"))); // NOI18N
        lblFichaArabia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaArabia.setName("Arabia"); // NOI18N
        panelAsia.add(lblFichaArabia, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 375, -1, 30));

        lblFichaJapon.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaJapon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaAmarilla3.png"))); // NOI18N
        lblFichaJapon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaJapon.setName("Japon"); // NOI18N
        panelAsia.add(lblFichaJapon, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 175, 30, 30));

        lblMisilCorea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilAmarilla.png"))); // NOI18N
        lblMisilCorea.setName("MisilCorea"); // NOI18N
        panelAsia.add(lblMisilCorea, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, -1, 40));

        lblMisilKamtchatka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilAzul.png"))); // NOI18N
        lblMisilKamtchatka.setName("MisilKamtchatka"); // NOI18N
        panelAsia.add(lblMisilKamtchatka, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 20, 40));

        lblMisilChukchi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilAzul.png"))); // NOI18N
        lblMisilChukchi.setName("MisilChukchi"); // NOI18N
        panelAsia.add(lblMisilChukchi, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 20, 40));

        lblMisilSiberia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilAzul.png"))); // NOI18N
        lblMisilSiberia.setName("MisilSiberia"); // NOI18N
        panelAsia.add(lblMisilSiberia, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 20, 50));

        lblMisilChechenia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilAmarilla.png"))); // NOI18N
        lblMisilChechenia.setName("MisilChechenia"); // NOI18N
        panelAsia.add(lblMisilChechenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 20, 50));

        lblMisilRusia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilNegra.png"))); // NOI18N
        lblMisilRusia.setName("MisilRusia"); // NOI18N
        panelAsia.add(lblMisilRusia, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, 50));

        lblMisilIran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilRoja.png"))); // NOI18N
        lblMisilIran.setName("MisilIran"); // NOI18N
        panelAsia.add(lblMisilIran, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, -1, 60));

        lblMisilMalasia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilBlanca.png"))); // NOI18N
        lblMisilMalasia.setToolTipText("");
        lblMisilMalasia.setName("MisilMalasia"); // NOI18N
        panelAsia.add(lblMisilMalasia, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, -1, 40));

        lblMisilVietnam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilRoja.png"))); // NOI18N
        lblMisilVietnam.setName("MisilVietnam"); // NOI18N
        panelAsia.add(lblMisilVietnam, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, -1, 60));

        lblMisilIndia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilRoja.png"))); // NOI18N
        lblMisilIndia.setName("MisilIndia"); // NOI18N
        panelAsia.add(lblMisilIndia, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 370, -1, -1));

        lblMisilTurquia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilRoja.png"))); // NOI18N
        lblMisilTurquia.setName("MisilTurquia"); // NOI18N
        panelAsia.add(lblMisilTurquia, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 20, 50));

        lblMisilIsrael.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilBlanca.png"))); // NOI18N
        lblMisilIsrael.setName("MisilIsrael"); // NOI18N
        panelAsia.add(lblMisilIsrael, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 290, -1, 50));

        lblMisilArabia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilBlanca.png"))); // NOI18N
        lblMisilArabia.setName("MisilArabia"); // NOI18N
        panelAsia.add(lblMisilArabia, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, -1, 40));

        lblMisilJapon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilAmarilla.png"))); // NOI18N
        lblMisilJapon.setName("MisilJapon"); // NOI18N
        panelAsia.add(lblMisilJapon, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 130, 20, 60));

        lblMisilChina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilAmarilla.png"))); // NOI18N
        lblMisilChina.setName("MisilChina"); // NOI18N
        panelAsia.add(lblMisilChina, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 190, 20, 40));

        add(panelAsia, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 30, 280, 430));

        panelOceania.setOpaque(false);
        panelOceania.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSumatra.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblSumatra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSumatraMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblSumatraMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelOceania.add(lblSumatra, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 49, 36));

        lblFilipinas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFilipinas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFilipinasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblFilipinasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelOceania.add(lblFilipinas, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 1, 50, 40));

        lblTonga.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTonga.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTongaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTongaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelOceania.add(lblTonga, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 60, 45));

        lblAustralia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblAustralia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAustraliaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblAustraliaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelOceania.add(lblAustralia, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 131, 71));

        lblTasmania.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTasmania.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTasmaniaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTasmaniaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelOceania.add(lblTasmania, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 60, 23));

        lblNuevaZelandia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblNuevaZelandia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNuevaZelandiaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblNuevaZelandiaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        panelOceania.add(lblNuevaZelandia, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 205, 100, 35));

        lblFichaSumatra.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaSumatra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaBlanca8.png"))); // NOI18N
        lblFichaSumatra.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaSumatra.setName("Sumatra"); // NOI18N
        panelOceania.add(lblFichaSumatra, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 45, 30, 30));

        lblFichaFilipinas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaFilipinas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaBlanca8.png"))); // NOI18N
        lblFichaFilipinas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaFilipinas.setName("Filipinas"); // NOI18N
        panelOceania.add(lblFichaFilipinas, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 5, -1, 40));

        lblFichaTonga.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaTonga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaBlanca9.png"))); // NOI18N
        lblFichaTonga.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaTonga.setName("Tonga"); // NOI18N
        panelOceania.add(lblFichaTonga, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 35, -1, 40));

        lblFichaAustralia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaAustralia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaBlanca5.png"))); // NOI18N
        lblFichaAustralia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaAustralia.setName("Australia"); // NOI18N
        panelOceania.add(lblFichaAustralia, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 30, -1));

        lblFichaTasmania.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaTasmania.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaBlanca7.png"))); // NOI18N
        lblFichaTasmania.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaTasmania.setName("Tasmania"); // NOI18N
        panelOceania.add(lblFichaTasmania, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 165, 30, 50));

        lblFichaNuevaZelandia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaNuevaZelandia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaBlanca5.png"))); // NOI18N
        lblFichaNuevaZelandia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaNuevaZelandia.setName("NuevaZelandia"); // NOI18N
        panelOceania.add(lblFichaNuevaZelandia, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 205, 40, 30));

        lblMisilAustralia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilBlanca.png"))); // NOI18N
        lblMisilAustralia.setName("MisilAustralia"); // NOI18N
        panelOceania.add(lblMisilAustralia, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 20, -1));

        lblMisilFilipinas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilBlanca.png"))); // NOI18N
        lblMisilFilipinas.setName("MisilFIlipinas"); // NOI18N
        panelOceania.add(lblMisilFilipinas, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

        lblMisilSumatra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilBlanca.png"))); // NOI18N
        lblMisilSumatra.setName("MisilSUmatra"); // NOI18N
        panelOceania.add(lblMisilSumatra, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 20, 50));

        lblMisilTasmania.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilBlanca.png"))); // NOI18N
        lblMisilTasmania.setName("MisilTasmania"); // NOI18N
        panelOceania.add(lblMisilTasmania, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 20, 40));

        lblMisilNuevaZelandia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilBlanca.png"))); // NOI18N
        lblMisilNuevaZelandia.setName("MisilNuevaZelandia"); // NOI18N
        panelOceania.add(lblMisilNuevaZelandia, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 20, 50));

        lblMisilTonga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilBlanca.png"))); // NOI18N
        lblMisilTonga.setName("MisilTonga"); // NOI18N
        panelOceania.add(lblMisilTonga, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 20, -1));

        add(panelOceania, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 460, 230, 240));

        panelAsia2.setOpaque(false);
        panelAsia2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIrak.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblIrak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIrakMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIrakMouseEntered(evt);
            }
        });
        panelAsia2.add(lblIrak, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 29, 50));

        lblFichaIrak.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFichaIrak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichas/fichaRoja8.png"))); // NOI18N
        lblFichaIrak.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFichaIrak.setName("Irak"); // NOI18N
        panelAsia2.add(lblFichaIrak, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        lblMisilIrak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/misil/misilRoja.png"))); // NOI18N
        lblMisilIrak.setName("MisilIrak"); // NOI18N
        panelAsia2.add(lblMisilIrak, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        add(panelAsia2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 340, 40, 80));

        lblUcrania.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblUcrania.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUcraniaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblUcraniaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        add(lblUcrania, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 180, 40, 60));

        lblPolonia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblPolonia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPoloniaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblPoloniaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        add(lblPolonia, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 210, 50, 40));

        lblSerbia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblSerbia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSerbiaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblSerbiaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        add(lblSerbia, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 260, 50, 30));

        lblFinlandia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFinlandia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFinlandiaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblFinlandiaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        add(lblFinlandia, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 120, 50, 60));

        lblNoruega.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblNoruega.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNoruegaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblNoruegaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        add(lblNoruega, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, 50, 80));

        lblGranBretaña.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblGranBretaña.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGranBretañaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblGranBretañaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        add(lblGranBretaña, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 200, 40, 70));

        lblIrlanda.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblIrlanda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIrlandaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIrlandaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        add(lblIrlanda, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 210, 40, 40));

        lblAlemania.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblAlemania.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAlemaniaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblAlemaniaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        add(lblAlemania, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 230, 40, 70));

        lblFrancia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFrancia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFranciaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblFranciaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        add(lblFrancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, 40, 60));

        lblEspaña.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblEspaña.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEspañaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblEspañaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        add(lblEspaña, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 340, 60, 60));

        lblPortugal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblPortugal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPortugalMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblPortugalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        add(lblPortugal, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 330, 20, 70));

        lblItalia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblItalia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblItaliaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblItaliaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        add(lblItalia, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 330, 30, 70));

        lblCroacia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCroacia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCroaciaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCroaciaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        add(lblCroacia, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 310, 30, 50));

        lblAlbania.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblAlbania.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAlbaniaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblAlbaniaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        add(lblAlbania, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 270, 40, 70));

        lblBielorrusia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblBielorrusia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBielorrusiaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblBielorrusiaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJaponMouseExited(evt);
            }
        });
        add(lblBielorrusia, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 100, 30, 80));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 204));
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtMensajeGlobal.setBackground(new java.awt.Color(255, 255, 204));
        txtMensajeGlobal.setColumns(20);
        txtMensajeGlobal.setLineWrap(true);
        txtMensajeGlobal.setRows(5);
        jScrollPane1.setViewportView(txtMensajeGlobal);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 220, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/teg3.jpg"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 710));
    }// </editor-fold>//GEN-END:initComponents

    private void lblAlaskaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAlaskaMouseClicked
         principal.seleccionPais("Alaska");
    }//GEN-LAST:event_lblAlaskaMouseClicked

    private void lblCanadaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCanadaMouseClicked
         principal.seleccionPais("Canada");
    }//GEN-LAST:event_lblCanadaMouseClicked

    private void lblIslaVictoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIslaVictoriaMouseClicked
         principal.seleccionPais("Isla Victoria");
    }//GEN-LAST:event_lblIslaVictoriaMouseClicked

    private void lblGroenlandiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGroenlandiaMouseClicked
        principal.seleccionPais("Groenlandia");
    }//GEN-LAST:event_lblGroenlandiaMouseClicked

    private void lblIslandiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIslandiaMouseClicked
         principal.seleccionPais("Islandia");
    }//GEN-LAST:event_lblIslandiaMouseClicked

    private void lblLabradorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLabradorMouseClicked
         principal.seleccionPais("Labrador");
    }//GEN-LAST:event_lblLabradorMouseClicked

    private void lblNewYorkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNewYorkMouseClicked
         principal.seleccionPais("Nueva York");
    }//GEN-LAST:event_lblNewYorkMouseClicked

    private void lblTerranovaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTerranovaMouseClicked
         principal.seleccionPais("Terranova");
    }//GEN-LAST:event_lblTerranovaMouseClicked

    private void lblOregonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOregonMouseClicked
         principal.seleccionPais("Oregon");
    }//GEN-LAST:event_lblOregonMouseClicked

    private void lblLasVegasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLasVegasMouseClicked
         principal.seleccionPais("Las Vegas");
    }//GEN-LAST:event_lblLasVegasMouseClicked

    private void lblChicagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChicagoMouseClicked
         principal.seleccionPais("Chicago");
    }//GEN-LAST:event_lblChicagoMouseClicked

    private void lblCaliforniaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCaliforniaMouseClicked
         principal.seleccionPais("California");
    }//GEN-LAST:event_lblCaliforniaMouseClicked

    private void lblFloridaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFloridaMouseClicked
         principal.seleccionPais("Florida");
    }//GEN-LAST:event_lblFloridaMouseClicked

    private void lblMexicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMexicoMouseClicked
         principal.seleccionPais("Mexico");
    }//GEN-LAST:event_lblMexicoMouseClicked

    private void lblHondurasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHondurasMouseClicked
         principal.seleccionPais("Honduras");
    }//GEN-LAST:event_lblHondurasMouseClicked

    private void lblElSalvadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblElSalvadorMouseClicked
          principal.seleccionPais("El Salvador");
    }//GEN-LAST:event_lblElSalvadorMouseClicked

    private void lblNicaraguaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNicaraguaMouseClicked
          principal.seleccionPais("Nicaragua");
    }//GEN-LAST:event_lblNicaraguaMouseClicked

    private void lblCubaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCubaMouseClicked
          principal.seleccionPais("Cuba");
    }//GEN-LAST:event_lblCubaMouseClicked

    private void lblJamaicaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblJamaicaMouseClicked
         principal.seleccionPais("Jamaica");
    }//GEN-LAST:event_lblJamaicaMouseClicked

    private void lblColombiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblColombiaMouseClicked
         principal.seleccionPais("Colombia");
    }//GEN-LAST:event_lblColombiaMouseClicked

    private void lblVenezuelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVenezuelaMouseClicked
         principal.seleccionPais("Venezuela");
    }//GEN-LAST:event_lblVenezuelaMouseClicked

    private void lblBrasilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBrasilMouseClicked
         principal.seleccionPais("Brasil");
    }//GEN-LAST:event_lblBrasilMouseClicked

    private void lblBoliviaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBoliviaMouseClicked
        principal.seleccionPais("Bolivia");
    }//GEN-LAST:event_lblBoliviaMouseClicked

    private void lblParaguayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblParaguayMouseClicked
         principal.seleccionPais("Paraguay");
    }//GEN-LAST:event_lblParaguayMouseClicked

    private void lblChileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChileMouseClicked
         principal.seleccionPais("Chile");
    }//GEN-LAST:event_lblChileMouseClicked

    private void lblArgentinaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblArgentinaMouseClicked
         principal.seleccionPais("Argentina");
    }//GEN-LAST:event_lblArgentinaMouseClicked

    private void lblUruguayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUruguayMouseClicked
         principal.seleccionPais("Uruguay");
    }//GEN-LAST:event_lblUruguayMouseClicked

    private void lblSaharaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSaharaMouseClicked
         principal.seleccionPais("Sahara");
    }//GEN-LAST:event_lblSaharaMouseClicked

    private void lblEgiptoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEgiptoMouseClicked
         principal.seleccionPais("Egipto");
    }//GEN-LAST:event_lblEgiptoMouseClicked

    private void lblEtiopiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEtiopiaMouseClicked
         principal.seleccionPais("Etiopia");
    }//GEN-LAST:event_lblEtiopiaMouseClicked

    private void lblNigeriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNigeriaMouseClicked
         principal.seleccionPais("Nigeria");
    }//GEN-LAST:event_lblNigeriaMouseClicked

    private void lblAngolaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAngolaMouseClicked
        principal.seleccionPais("Angola");
    }//GEN-LAST:event_lblAngolaMouseClicked

    private void lblMauritaniaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMauritaniaMouseClicked
         principal.seleccionPais("Mauritania");
    }//GEN-LAST:event_lblMauritaniaMouseClicked

    private void lblSudafricaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSudafricaMouseClicked
         principal.seleccionPais("Sudafrica");
    }//GEN-LAST:event_lblSudafricaMouseClicked

    private void lblMadagascarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMadagascarMouseClicked
         principal.seleccionPais("Madagascar");
    }//GEN-LAST:event_lblMadagascarMouseClicked

    private void lblSumatraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSumatraMouseClicked
         principal.seleccionPais("Sumatra");
    }//GEN-LAST:event_lblSumatraMouseClicked

    private void lblFilipinasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFilipinasMouseClicked
         principal.seleccionPais("Filipinas"); 
    }//GEN-LAST:event_lblFilipinasMouseClicked

    private void lblTongaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTongaMouseClicked
         principal.seleccionPais("Tonga"); 
    }//GEN-LAST:event_lblTongaMouseClicked

    private void lblAustraliaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAustraliaMouseClicked
          principal.seleccionPais("Australia"); 
    }//GEN-LAST:event_lblAustraliaMouseClicked

    private void lblTasmaniaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTasmaniaMouseClicked
          principal.seleccionPais("Tasmania"); 
    }//GEN-LAST:event_lblTasmaniaMouseClicked

    private void lblNuevaZelandiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNuevaZelandiaMouseClicked
         principal.seleccionPais("Nueva Zelandia"); 
    }//GEN-LAST:event_lblNuevaZelandiaMouseClicked

    private void lblIndiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIndiaMouseClicked
         principal.seleccionPais("India"); 
    }//GEN-LAST:event_lblIndiaMouseClicked

    private void lblVietnamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVietnamMouseClicked
         principal.seleccionPais("Vietnam"); 
    }//GEN-LAST:event_lblVietnamMouseClicked

    private void lblMalasiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMalasiaMouseClicked
         principal.seleccionPais("Malasia"); 
    }//GEN-LAST:event_lblMalasiaMouseClicked

    private void lblCoreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCoreaMouseClicked
         principal.seleccionPais("Corea"); 
    }//GEN-LAST:event_lblCoreaMouseClicked

    private void lblJaponMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblJaponMouseClicked
         principal.seleccionPais("Japon"); 
    }//GEN-LAST:event_lblJaponMouseClicked

    private void lblKamtchatkaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKamtchatkaMouseClicked
         principal.seleccionPais("Kamtchatka"); 
    }//GEN-LAST:event_lblKamtchatkaMouseClicked

    private void lblChukchiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChukchiMouseClicked
         principal.seleccionPais("Chukchi"); 
    }//GEN-LAST:event_lblChukchiMouseClicked

    private void lblChinaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChinaMouseClicked
         principal.seleccionPais("China"); 
    }//GEN-LAST:event_lblChinaMouseClicked

    private void lblTurquiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTurquiaMouseClicked
         principal.seleccionPais("Turquia");
    }//GEN-LAST:event_lblTurquiaMouseClicked

    private void lblArabiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblArabiaMouseClicked
         principal.seleccionPais("Arabia");
    }//GEN-LAST:event_lblArabiaMouseClicked

    private void lblIsraelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIsraelMouseClicked
          principal.seleccionPais("Israel");
    }//GEN-LAST:event_lblIsraelMouseClicked

    private void lblIrakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIrakMouseClicked
          principal.seleccionPais("Irak");
    }//GEN-LAST:event_lblIrakMouseClicked

    private void lblIranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIranMouseClicked
        principal.seleccionPais("Iran");
    }//GEN-LAST:event_lblIranMouseClicked

    private void lblRusiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRusiaMouseClicked
         principal.seleccionPais("Rusia");
    }//GEN-LAST:event_lblRusiaMouseClicked

    private void lblChecheniaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChecheniaMouseClicked
        principal.seleccionPais("Chechenia");
    }//GEN-LAST:event_lblChecheniaMouseClicked

    private void lblSiberiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSiberiaMouseClicked
         principal.seleccionPais("Siberia");
    }//GEN-LAST:event_lblSiberiaMouseClicked

    private void lblUcraniaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUcraniaMouseClicked
         principal.seleccionPais("Ucrania");
    }//GEN-LAST:event_lblUcraniaMouseClicked

    private void lblPoloniaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPoloniaMouseClicked
         principal.seleccionPais("Polonia");
    }//GEN-LAST:event_lblPoloniaMouseClicked

    private void lblSerbiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSerbiaMouseClicked
         principal.seleccionPais("Serbia");
    }//GEN-LAST:event_lblSerbiaMouseClicked

    private void lblFinlandiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFinlandiaMouseClicked
         principal.seleccionPais("Finlandia");
    }//GEN-LAST:event_lblFinlandiaMouseClicked

    private void lblNoruegaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNoruegaMouseClicked
         principal.seleccionPais("Noruega");
    }//GEN-LAST:event_lblNoruegaMouseClicked

    private void lblGranBretañaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGranBretañaMouseClicked
         principal.seleccionPais("Gran Bretaña");
    }//GEN-LAST:event_lblGranBretañaMouseClicked

    private void lblIrlandaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIrlandaMouseClicked
         principal.seleccionPais("Irlanda");
    }//GEN-LAST:event_lblIrlandaMouseClicked

    private void lblAlemaniaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAlemaniaMouseClicked
         principal.seleccionPais("Alemania");
    }//GEN-LAST:event_lblAlemaniaMouseClicked

    private void lblFranciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFranciaMouseClicked
         principal.seleccionPais("Francia");
    }//GEN-LAST:event_lblFranciaMouseClicked

    private void lblEspañaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEspañaMouseClicked
         principal.seleccionPais("España");
    }//GEN-LAST:event_lblEspañaMouseClicked

    private void lblPortugalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPortugalMouseClicked
         principal.seleccionPais("Portugal");
    }//GEN-LAST:event_lblPortugalMouseClicked

    private void lblItaliaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblItaliaMouseClicked
         principal.seleccionPais("Italia");
    }//GEN-LAST:event_lblItaliaMouseClicked

    private void lblCroaciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCroaciaMouseClicked
         principal.seleccionPais("Croacia");
    }//GEN-LAST:event_lblCroaciaMouseClicked

    private void lblAlbaniaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAlbaniaMouseClicked
         principal.seleccionPais("Albania");
    }//GEN-LAST:event_lblAlbaniaMouseClicked

    private void lblBielorrusiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBielorrusiaMouseClicked
         principal.seleccionPais("BieloRrusia");
    }//GEN-LAST:event_lblBielorrusiaMouseClicked

    private void lblAlaskaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAlaskaMouseEntered
        principal.cargarInformacionPais("ALASKA");
        lblFichaAlaska.setVisible(false);
        lblMisilAlask.setVisible(false);
    }//GEN-LAST:event_lblAlaskaMouseEntered

    private void lblCanadaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCanadaMouseEntered
        principal.cargarInformacionPais("CANADA");
        lblFichaCanda.setVisible(false);
        lblMisilCanada.setVisible(false);        
    }//GEN-LAST:event_lblCanadaMouseEntered

    private void lblTerranovaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTerranovaMouseEntered
        principal.cargarInformacionPais("TERRANOVA");
        lblFichaTerranova.setVisible(false);
        lblMisilTerranova.setVisible(false);
    }//GEN-LAST:event_lblTerranovaMouseEntered

    private void lblOregonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOregonMouseEntered
        principal.cargarInformacionPais("OREGON");
        lblMisilOregon.setVisible(false);
        lblFichaOregon.setVisible(false);
    }//GEN-LAST:event_lblOregonMouseEntered

    private void lblFloridaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFloridaMouseEntered
        principal.cargarInformacionPais("FLORIDA");
        lblFichaFlorida.setVisible(false);
        lblMisilFlorida.setVisible(false);
    }//GEN-LAST:event_lblFloridaMouseEntered

    private void lblGroenlandiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGroenlandiaMouseEntered
        principal.cargarInformacionPais("GROENLANDIA");
        lblFichaGroenlandia.setVisible(false);
        lblMisilGroenlandia.setVisible(false);
    }//GEN-LAST:event_lblGroenlandiaMouseEntered

    private void lblNewYorkMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNewYorkMouseEntered
        principal.cargarInformacionPais("NUEVA YORK");
        lblFichaNuevaYork.setVisible(false);
        lblMisilNuevaYork.setVisible(false);
    }//GEN-LAST:event_lblNewYorkMouseEntered

    private void lblLasVegasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLasVegasMouseEntered
       principal.cargarInformacionPais("LAS VEGAS");
       lblFichaLasVegas.setVisible(false);
       lblMisilLasVegas.setVisible(false);
    }//GEN-LAST:event_lblLasVegasMouseEntered

    private void lblIslaVictoriaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIslaVictoriaMouseEntered
        principal.cargarInformacionPais("ISLA VICTORIA");
        lblFichaIslaVictoria.setVisible(false);
        lblMisilIslaVictoria.setVisible(false);
    }//GEN-LAST:event_lblIslaVictoriaMouseEntered

    private void lblChicagoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChicagoMouseEntered
        principal.cargarInformacionPais("CHICAGO");
        lblFichaChicago.setVisible(false);
        lblMisilChicago.setVisible(false);
    }//GEN-LAST:event_lblChicagoMouseEntered

    private void lblCaliforniaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCaliforniaMouseEntered
       principal.cargarInformacionPais("CALIFORNIA");
       lblFichaCalifornia.setVisible(false);
       lblMisilCalifornia.setVisible(false);
    }//GEN-LAST:event_lblCaliforniaMouseEntered

    private void lblLabradorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLabradorMouseEntered
        principal.cargarInformacionPais("LABRADOR");
        lblFichaLabrador.setVisible(false);
        lblMisilLabrador.setVisible(false);
    }//GEN-LAST:event_lblLabradorMouseEntered

    private void lblMexicoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMexicoMouseEntered
        principal.cargarInformacionPais("MEXICO");
        lblFichaMexico.setVisible(false);
        lblMisilMexico.setVisible(false);
    }//GEN-LAST:event_lblMexicoMouseEntered

    private void lblHondurasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHondurasMouseEntered
        principal.cargarInformacionPais("HONDURAS");
        lblFichaHonduras.setVisible(false);
        lblMisilHonduras.setVisible(false);
    }//GEN-LAST:event_lblHondurasMouseEntered

    private void lblElSalvadorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblElSalvadorMouseEntered
        principal.cargarInformacionPais("EL SALVADOR");
        lblFichaElSalvador.setVisible(false);
        lblMisilElSalvador.setVisible(false);
    }//GEN-LAST:event_lblElSalvadorMouseEntered

    private void lblNicaraguaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNicaraguaMouseEntered
        principal.cargarInformacionPais("NICARAGUA");
        lblFichaNicaragua.setVisible(false);
        lblMisilNicaragua.setVisible(false);
    }//GEN-LAST:event_lblNicaraguaMouseEntered

    private void lblCubaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCubaMouseEntered
       principal.cargarInformacionPais("CUBA");
       lblFichaCuba.setVisible(false);
       lblMisilCuba.setVisible(false);
    }//GEN-LAST:event_lblCubaMouseEntered

    private void lblJamaicaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblJamaicaMouseEntered
        principal.cargarInformacionPais("JAMAICA");
        lblFichaJamaica.setVisible(false);
        lblMisilJamaica.setVisible(false);
    }//GEN-LAST:event_lblJamaicaMouseEntered

    private void lblVenezuelaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVenezuelaMouseEntered
         principal.cargarInformacionPais("Venezuela");
         lblFichaVenezuela.setVisible(false);
         lblMisilVenezuela.setVisible(false);
    }//GEN-LAST:event_lblVenezuelaMouseEntered

    private void lblSaharaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSaharaMouseEntered
        principal.cargarInformacionPais("Sahara");
        lblFichaSahara.setVisible(false);
        lblMisilSahara.setVisible(false);
    }//GEN-LAST:event_lblSaharaMouseEntered

    private void lblSumatraMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSumatraMouseEntered
        principal.cargarInformacionPais("Sumatra");
        lblFichaSumatra.setVisible(false);
        lblMisilSumatra.setVisible(false);
    }//GEN-LAST:event_lblSumatraMouseEntered

    private void lblPortugalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPortugalMouseEntered
       principal.cargarInformacionPais("Portugal");
       lblFichaPortugal.setVisible(false);
       lblMisilPortugal.setVisible(false);
    }//GEN-LAST:event_lblPortugalMouseEntered

    private void lblIrakMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIrakMouseEntered
         principal.cargarInformacionPais("Irak");
         lblFichaIrak.setVisible(false);
         lblMisilIrak.setVisible(false);
    }//GEN-LAST:event_lblIrakMouseEntered

    private void lblIslandiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIslandiaMouseEntered
        principal.cargarInformacionPais("Islandia");
        lblFichaIslandia.setVisible(false);
        lblMisilIslandia.setVisible(false);
    }//GEN-LAST:event_lblIslandiaMouseEntered

    private void lblNoruegaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNoruegaMouseEntered
       principal.cargarInformacionPais("Noruega");
       lblFichaNoruega.setVisible(false);
       lblMisilNoruega.setVisible(false);
    }//GEN-LAST:event_lblNoruegaMouseEntered

    private void lblIrlandaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIrlandaMouseEntered
        principal.cargarInformacionPais("Irlanda");
        lblFichaIrlanda.setVisible(false);
        lblMisilIrlanda.setVisible(false);
    }//GEN-LAST:event_lblIrlandaMouseEntered

    private void lblGranBretañaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGranBretañaMouseEntered
        principal.cargarInformacionPais("Gran Bretaña");
        lblFichaGranBretaña.setVisible(false);
        lblMisilGranBretaña.setVisible(false);
    }//GEN-LAST:event_lblGranBretañaMouseEntered

    private void lblEspañaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEspañaMouseEntered
        principal.cargarInformacionPais("España");
        lblFichaEspaña.setVisible(false);
        lblMisilEspaña.setVisible(false);
    }//GEN-LAST:event_lblEspañaMouseEntered

    private void lblFranciaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFranciaMouseEntered
        principal.cargarInformacionPais("Francia");
        lblFichaFrancia.setVisible(false);
        lblMisilFrancia.setVisible(false);
    }//GEN-LAST:event_lblFranciaMouseEntered

    private void lblAlemaniaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAlemaniaMouseEntered
        principal.cargarInformacionPais("Alemania");
        lblFichaAlemania.setVisible(false);
        lblMisilAlemania.setVisible(false);
    }//GEN-LAST:event_lblAlemaniaMouseEntered

    private void lblSerbiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSerbiaMouseEntered
        principal.cargarInformacionPais("Serbia");
        lblFichaSerbia.setVisible(false);
        lblMisilSerbia.setVisible(false);
    }//GEN-LAST:event_lblSerbiaMouseEntered

    private void lblCroaciaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCroaciaMouseEntered
        principal.cargarInformacionPais("Croacia");
        lblFichaCroacia.setVisible(false);
        lblMisilCroacia.setVisible(false);
    }//GEN-LAST:event_lblCroaciaMouseEntered

    private void lblItaliaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblItaliaMouseEntered
        principal.cargarInformacionPais("Italia");
        lblFichaItalia.setVisible(false);
        lblMisilItalia.setVisible(false);
    }//GEN-LAST:event_lblItaliaMouseEntered

    private void lblPoloniaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPoloniaMouseEntered
        principal.cargarInformacionPais("Polonia");
        lblFichaPolonia.setVisible(false);
        lblMisilPolonia.setVisible(false);
    }//GEN-LAST:event_lblPoloniaMouseEntered

    private void lblUcraniaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUcraniaMouseEntered
        principal.cargarInformacionPais("Ucrania");
        lblFichaUcrania.setVisible(false);
        lblMisilUcrania.setVisible(false);
    }//GEN-LAST:event_lblUcraniaMouseEntered

    private void lblBielorrusiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBielorrusiaMouseEntered
        principal.cargarInformacionPais("Bielorrusia");
        lblFichaBielorrusia.setVisible(false);
        lblMisilBielorrusia.setVisible(false);
    }//GEN-LAST:event_lblBielorrusiaMouseEntered

    private void lblFinlandiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFinlandiaMouseEntered
        principal.cargarInformacionPais("Finlandia");
        lblFichaFinlandia.setVisible(false);
        lblMisilFinlandia.setVisible(false);
    }//GEN-LAST:event_lblFinlandiaMouseEntered

    private void lblAlbaniaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAlbaniaMouseEntered
        principal.cargarInformacionPais("Albania");
        lblFichaAlbania.setVisible(false);
        lblMisilAlbania.setVisible(false);
    }//GEN-LAST:event_lblAlbaniaMouseEntered

    private void lblIsraelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIsraelMouseEntered
        principal.cargarInformacionPais("Israel");
        lblFichaIsrael.setVisible(false);
        lblMisilIsrael.setVisible(false);
    }//GEN-LAST:event_lblIsraelMouseEntered

    private void lblArabiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblArabiaMouseEntered
        principal.cargarInformacionPais("Arabia");
        lblFichaArabia.setVisible(false);
        lblMisilArabia.setVisible(false);
    }//GEN-LAST:event_lblArabiaMouseEntered

    private void lblTurquiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTurquiaMouseEntered
        principal.cargarInformacionPais("Turquia");
        lblFichaTurquia.setVisible(false);
        lblMisilTurquia.setVisible(false);
    }//GEN-LAST:event_lblTurquiaMouseEntered

    private void lblMalasiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMalasiaMouseEntered
        principal.cargarInformacionPais("Malasia");
        lblFichaMalasia.setVisible(false);
        lblMisilMalasia.setVisible(false);
    }//GEN-LAST:event_lblMalasiaMouseEntered

    private void lblIndiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIndiaMouseEntered
        principal.cargarInformacionPais("India");
        lblFichaIndia.setVisible(false);
        lblMisilIndia.setVisible(false);
    }//GEN-LAST:event_lblIndiaMouseEntered

    private void lblVietnamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVietnamMouseEntered
        principal.cargarInformacionPais("Vietnam");
        lblFichaVietnam.setVisible(false);
        lblMisilVietnam.setVisible(false);
    }//GEN-LAST:event_lblVietnamMouseEntered

    private void lblCoreaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCoreaMouseEntered
        principal.cargarInformacionPais("Corea");
        lblFichaCorea.setVisible(false);
        lblMisilCorea.setVisible(false);
    }//GEN-LAST:event_lblCoreaMouseEntered

    private void lblJaponMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblJaponMouseEntered
        principal.cargarInformacionPais("Japon");
        lblFichaJapon.setVisible(false);
        lblMisilJapon.setVisible(false);
    }//GEN-LAST:event_lblJaponMouseEntered

    private void lblKamtchatkaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKamtchatkaMouseEntered
        principal.cargarInformacionPais("Kamtchatka");
        lblFichaKamtchatcka.setVisible(false);
        lblMisilKamtchatka.setVisible(false);
    }//GEN-LAST:event_lblKamtchatkaMouseEntered

    private void lblChukchiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChukchiMouseEntered
        principal.cargarInformacionPais("Chukchi");
        lblFichaChukChi.setVisible(false);
        lblMisilChukchi.setVisible(false);
    }//GEN-LAST:event_lblChukchiMouseEntered

    private void lblChecheniaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChecheniaMouseEntered
        principal.cargarInformacionPais("Chechenia");
        lblFichaChechenia.setVisible(false);
        lblMisilChechenia.setVisible(false);
    }//GEN-LAST:event_lblChecheniaMouseEntered

    private void lblSiberiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSiberiaMouseEntered
        principal.cargarInformacionPais("Siberia");
        lblFichaSiberia.setVisible(false);
        lblMisilSiberia.setVisible(false);
    }//GEN-LAST:event_lblSiberiaMouseEntered

    private void lblRusiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRusiaMouseEntered
        principal.cargarInformacionPais("Rusia");
        lblFichaRusia.setVisible(false);
        lblMisilRusia.setVisible(false);
    }//GEN-LAST:event_lblRusiaMouseEntered

    private void lblIranMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIranMouseEntered
        principal.cargarInformacionPais("Iran");
        lblFichaIran.setVisible(false);
        lblMisilIran.setVisible(false);
    }//GEN-LAST:event_lblIranMouseEntered

    private void lblChinaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChinaMouseEntered
        principal.cargarInformacionPais("China");
        lblFichaChina.setVisible(false);
        lblMisilChina.setVisible(false);
    }//GEN-LAST:event_lblChinaMouseEntered

    private void lblFilipinasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFilipinasMouseEntered
        principal.cargarInformacionPais("Filipinas");
        lblFichaFilipinas.setVisible(false);
        lblMisilFilipinas.setVisible(false);
    }//GEN-LAST:event_lblFilipinasMouseEntered

    private void lblTongaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTongaMouseEntered
        principal.cargarInformacionPais("Tonga");
        lblFichaTonga.setVisible(false);
        lblMisilTonga.setVisible(false);
    }//GEN-LAST:event_lblTongaMouseEntered

    private void lblAustraliaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAustraliaMouseEntered
        principal.cargarInformacionPais("Australia");
        lblFichaAustralia.setVisible(false);
        lblMisilAustralia.setVisible(false);
    }//GEN-LAST:event_lblAustraliaMouseEntered

    private void lblTasmaniaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTasmaniaMouseEntered
        principal.cargarInformacionPais("Tasmania");
        lblFichaTasmania.setVisible(false);
        lblMisilTasmania.setVisible(false);
    }//GEN-LAST:event_lblTasmaniaMouseEntered

    private void lblNuevaZelandiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNuevaZelandiaMouseEntered
        principal.cargarInformacionPais("Nueva Zelanda");
        lblFichaNuevaZelandia.setVisible(false);
        lblMisilNuevaZelandia.setVisible(false);
    }//GEN-LAST:event_lblNuevaZelandiaMouseEntered

    private void lblMadagascarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMadagascarMouseEntered
        principal.cargarInformacionPais("Madagascar");
        lblFichaMadagascar.setVisible(false);
        lblMisilMadagascar.setVisible(false);
    }//GEN-LAST:event_lblMadagascarMouseEntered

    private void lblSudafricaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSudafricaMouseEntered
        principal.cargarInformacionPais("Sudafrica");
        lblFichaSudafrica.setVisible(false);
        lblMisilSudafrica.setVisible(false);
    }//GEN-LAST:event_lblSudafricaMouseEntered

    private void lblMauritaniaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMauritaniaMouseEntered
        principal.cargarInformacionPais("Mauritania");
        lblFichaMauritania.setVisible(false);
        lblMisilMauritania.setVisible(false);
    }//GEN-LAST:event_lblMauritaniaMouseEntered

    private void lblAngolaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAngolaMouseEntered
        principal.cargarInformacionPais("Angola");
        lblFichaAngola.setVisible(false);
        lblMisilAngola.setVisible(false);
    }//GEN-LAST:event_lblAngolaMouseEntered

    private void lblEtiopiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEtiopiaMouseEntered
        principal.cargarInformacionPais("Etipia");
        lblFichaEtiopia.setVisible(false);
        lblMisilEtiopia.setVisible(false);
    }//GEN-LAST:event_lblEtiopiaMouseEntered

    private void lblEgiptoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEgiptoMouseEntered
        principal.cargarInformacionPais("Egipto");
        lblFichaEgipto.setVisible(false);
        lblMisilEgipto.setVisible(false);
    }//GEN-LAST:event_lblEgiptoMouseEntered

    private void lblNigeriaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNigeriaMouseEntered
        principal.cargarInformacionPais("Nigeria");
        lblFichaNigeria.setVisible(false);
        lblMisilNigeria.setVisible(false);
    }//GEN-LAST:event_lblNigeriaMouseEntered

    private void lblBrasilMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBrasilMouseEntered
        principal.cargarInformacionPais("Brasil");
        lblFichaBrasil.setVisible(false);
        lblMisilBrasil.setVisible(false);
    }//GEN-LAST:event_lblBrasilMouseEntered

    private void lblColombiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblColombiaMouseEntered
        principal.cargarInformacionPais("Colombia");
        lblFichaColombia.setVisible(false);
        lblMisilColombia.setVisible(false);
    }//GEN-LAST:event_lblColombiaMouseEntered

    private void lblBoliviaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBoliviaMouseEntered
        principal.cargarInformacionPais("Bolivia");
        lblFichaBolivia.setVisible(false);
        lblMisilBolivia.setVisible(false);
    }//GEN-LAST:event_lblBoliviaMouseEntered

    private void lblChileMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChileMouseEntered
        principal.cargarInformacionPais("Chile");
        lblFichaChile.setVisible(false);
        lblMisilChile.setVisible(false);
    }//GEN-LAST:event_lblChileMouseEntered

    private void lblParaguayMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblParaguayMouseEntered
        principal.cargarInformacionPais("Paraguay");
        lblFichaParaguay.setVisible(false);
        lblMisilParaguay.setVisible(false);
    }//GEN-LAST:event_lblParaguayMouseEntered

    private void lblArgentinaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblArgentinaMouseEntered
        principal.cargarInformacionPais("Argentina");
        lblFichaArgentina.setVisible(false);
        lblMisilArgentina.setVisible(false);
    }//GEN-LAST:event_lblArgentinaMouseEntered

    private void lblUruguayMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUruguayMouseEntered
        principal.cargarInformacionPais("Uruguay");
        lblFichaUruguay.setVisible(false);
        lblMisilUruguay.setVisible(false);
    }//GEN-LAST:event_lblUruguayMouseEntered

    private void lblColombiaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblColombiaMouseExited
        lblFichaColombia.setVisible(mostrarFichas);
        lblMisilColombia.setVisible(mostrarMisiles);
        lblFichaVenezuela.setVisible(mostrarFichas);
        lblMisilVenezuela.setVisible(mostrarMisiles);
        lblFichaBrasil.setVisible(mostrarFichas);
        lblMisilBrasil.setVisible(mostrarMisiles);
    }//GEN-LAST:event_lblColombiaMouseExited

    private void lblJaponMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblJaponMouseExited
        mostrarFichas(mostrarFichas, mostrarMisiles);
    }//GEN-LAST:event_lblJaponMouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAlaska;
    private javax.swing.JLabel lblAlbania;
    private javax.swing.JLabel lblAlemania;
    private javax.swing.JLabel lblAngola;
    private javax.swing.JLabel lblArabia;
    private javax.swing.JLabel lblArgentina;
    private javax.swing.JLabel lblAustralia;
    private javax.swing.JLabel lblBielorrusia;
    private javax.swing.JLabel lblBolivia;
    private javax.swing.JLabel lblBrasil;
    private javax.swing.JLabel lblCalifornia;
    private javax.swing.JLabel lblCanada;
    private javax.swing.JLabel lblChechenia;
    private javax.swing.JLabel lblChicago;
    private javax.swing.JLabel lblChile;
    private javax.swing.JLabel lblChina;
    private javax.swing.JLabel lblChukchi;
    private javax.swing.JLabel lblColombia;
    private javax.swing.JLabel lblCorea;
    private javax.swing.JLabel lblCroacia;
    private javax.swing.JLabel lblCuba;
    private javax.swing.JLabel lblEgipto;
    private javax.swing.JLabel lblElSalvador;
    private javax.swing.JLabel lblEspaña;
    private javax.swing.JLabel lblEtiopia;
    private javax.swing.JLabel lblFichaAlaska;
    private javax.swing.JLabel lblFichaAlbania;
    private javax.swing.JLabel lblFichaAlemania;
    private javax.swing.JLabel lblFichaAngola;
    private javax.swing.JLabel lblFichaArabia;
    private javax.swing.JLabel lblFichaArgentina;
    private javax.swing.JLabel lblFichaAustralia;
    private javax.swing.JLabel lblFichaBielorrusia;
    private javax.swing.JLabel lblFichaBolivia;
    private javax.swing.JLabel lblFichaBrasil;
    private javax.swing.JLabel lblFichaCalifornia;
    private javax.swing.JLabel lblFichaCanda;
    private javax.swing.JLabel lblFichaChechenia;
    private javax.swing.JLabel lblFichaChicago;
    private javax.swing.JLabel lblFichaChile;
    private javax.swing.JLabel lblFichaChina;
    private javax.swing.JLabel lblFichaChukChi;
    private javax.swing.JLabel lblFichaColombia;
    private javax.swing.JLabel lblFichaCorea;
    private javax.swing.JLabel lblFichaCroacia;
    private javax.swing.JLabel lblFichaCuba;
    private javax.swing.JLabel lblFichaEgipto;
    private javax.swing.JLabel lblFichaElSalvador;
    private javax.swing.JLabel lblFichaEspaña;
    private javax.swing.JLabel lblFichaEtiopia;
    private javax.swing.JLabel lblFichaFilipinas;
    private javax.swing.JLabel lblFichaFinlandia;
    private javax.swing.JLabel lblFichaFlorida;
    private javax.swing.JLabel lblFichaFrancia;
    private javax.swing.JLabel lblFichaGranBretaña;
    private javax.swing.JLabel lblFichaGroenlandia;
    private javax.swing.JLabel lblFichaHonduras;
    private javax.swing.JLabel lblFichaIndia;
    private javax.swing.JLabel lblFichaIrak;
    private javax.swing.JLabel lblFichaIran;
    private javax.swing.JLabel lblFichaIrlanda;
    private javax.swing.JLabel lblFichaIslaVictoria;
    private javax.swing.JLabel lblFichaIslandia;
    private javax.swing.JLabel lblFichaIsrael;
    private javax.swing.JLabel lblFichaItalia;
    private javax.swing.JLabel lblFichaJamaica;
    private javax.swing.JLabel lblFichaJapon;
    private javax.swing.JLabel lblFichaKamtchatcka;
    private javax.swing.JLabel lblFichaLabrador;
    private javax.swing.JLabel lblFichaLasVegas;
    private javax.swing.JLabel lblFichaMadagascar;
    private javax.swing.JLabel lblFichaMalasia;
    private javax.swing.JLabel lblFichaMauritania;
    private javax.swing.JLabel lblFichaMexico;
    private javax.swing.JLabel lblFichaNicaragua;
    private javax.swing.JLabel lblFichaNigeria;
    private javax.swing.JLabel lblFichaNoruega;
    private javax.swing.JLabel lblFichaNuevaYork;
    private javax.swing.JLabel lblFichaNuevaZelandia;
    private javax.swing.JLabel lblFichaOregon;
    private javax.swing.JLabel lblFichaParaguay;
    private javax.swing.JLabel lblFichaPolonia;
    private javax.swing.JLabel lblFichaPortugal;
    private javax.swing.JLabel lblFichaRusia;
    private javax.swing.JLabel lblFichaSahara;
    private javax.swing.JLabel lblFichaSerbia;
    private javax.swing.JLabel lblFichaSiberia;
    private javax.swing.JLabel lblFichaSudafrica;
    private javax.swing.JLabel lblFichaSumatra;
    private javax.swing.JLabel lblFichaTasmania;
    private javax.swing.JLabel lblFichaTerranova;
    private javax.swing.JLabel lblFichaTonga;
    private javax.swing.JLabel lblFichaTurquia;
    private javax.swing.JLabel lblFichaUcrania;
    private javax.swing.JLabel lblFichaUruguay;
    private javax.swing.JLabel lblFichaVenezuela;
    private javax.swing.JLabel lblFichaVietnam;
    private javax.swing.JLabel lblFilipinas;
    private javax.swing.JLabel lblFinlandia;
    private javax.swing.JLabel lblFlorida;
    private javax.swing.JLabel lblFrancia;
    private javax.swing.JLabel lblGranBretaña;
    private javax.swing.JLabel lblGroenlandia;
    private javax.swing.JLabel lblHonduras;
    private javax.swing.JLabel lblIndia;
    private javax.swing.JLabel lblIrak;
    private javax.swing.JLabel lblIran;
    private javax.swing.JLabel lblIrlanda;
    private javax.swing.JLabel lblIslaVictoria;
    private javax.swing.JLabel lblIslandia;
    private javax.swing.JLabel lblIsrael;
    private javax.swing.JLabel lblItalia;
    private javax.swing.JLabel lblJamaica;
    private javax.swing.JLabel lblJapon;
    private javax.swing.JLabel lblKamtchatka;
    private javax.swing.JLabel lblLabrador;
    private javax.swing.JLabel lblLasVegas;
    private javax.swing.JLabel lblMadagascar;
    private javax.swing.JLabel lblMalasia;
    private javax.swing.JLabel lblMauritania;
    private javax.swing.JLabel lblMexico;
    private javax.swing.JLabel lblMisilAlask;
    private javax.swing.JLabel lblMisilAlbania;
    private javax.swing.JLabel lblMisilAlemania;
    private javax.swing.JLabel lblMisilAngola;
    private javax.swing.JLabel lblMisilArabia;
    private javax.swing.JLabel lblMisilArgentina;
    private javax.swing.JLabel lblMisilAustralia;
    private javax.swing.JLabel lblMisilBielorrusia;
    private javax.swing.JLabel lblMisilBolivia;
    private javax.swing.JLabel lblMisilBrasil;
    private javax.swing.JLabel lblMisilCalifornia;
    private javax.swing.JLabel lblMisilCanada;
    private javax.swing.JLabel lblMisilChechenia;
    private javax.swing.JLabel lblMisilChicago;
    private javax.swing.JLabel lblMisilChile;
    private javax.swing.JLabel lblMisilChina;
    private javax.swing.JLabel lblMisilChukchi;
    private javax.swing.JLabel lblMisilColombia;
    private javax.swing.JLabel lblMisilCorea;
    private javax.swing.JLabel lblMisilCroacia;
    private javax.swing.JLabel lblMisilCuba;
    private javax.swing.JLabel lblMisilEgipto;
    private javax.swing.JLabel lblMisilElSalvador;
    private javax.swing.JLabel lblMisilEspaña;
    private javax.swing.JLabel lblMisilEtiopia;
    private javax.swing.JLabel lblMisilFilipinas;
    private javax.swing.JLabel lblMisilFinlandia;
    private javax.swing.JLabel lblMisilFlorida;
    private javax.swing.JLabel lblMisilFrancia;
    private javax.swing.JLabel lblMisilGranBretaña;
    private javax.swing.JLabel lblMisilGroenlandia;
    private javax.swing.JLabel lblMisilHonduras;
    private javax.swing.JLabel lblMisilIndia;
    private javax.swing.JLabel lblMisilIrak;
    private javax.swing.JLabel lblMisilIran;
    private javax.swing.JLabel lblMisilIrlanda;
    private javax.swing.JLabel lblMisilIslaVictoria;
    private javax.swing.JLabel lblMisilIslandia;
    private javax.swing.JLabel lblMisilIsrael;
    private javax.swing.JLabel lblMisilItalia;
    private javax.swing.JLabel lblMisilJamaica;
    private javax.swing.JLabel lblMisilJapon;
    private javax.swing.JLabel lblMisilKamtchatka;
    private javax.swing.JLabel lblMisilLabrador;
    private javax.swing.JLabel lblMisilLasVegas;
    private javax.swing.JLabel lblMisilMadagascar;
    private javax.swing.JLabel lblMisilMalasia;
    private javax.swing.JLabel lblMisilMauritania;
    private javax.swing.JLabel lblMisilMexico;
    private javax.swing.JLabel lblMisilNicaragua;
    private javax.swing.JLabel lblMisilNigeria;
    private javax.swing.JLabel lblMisilNoruega;
    private javax.swing.JLabel lblMisilNuevaYork;
    private javax.swing.JLabel lblMisilNuevaZelandia;
    private javax.swing.JLabel lblMisilOregon;
    private javax.swing.JLabel lblMisilParaguay;
    private javax.swing.JLabel lblMisilPolonia;
    private javax.swing.JLabel lblMisilPortugal;
    private javax.swing.JLabel lblMisilRusia;
    private javax.swing.JLabel lblMisilSahara;
    private javax.swing.JLabel lblMisilSerbia;
    private javax.swing.JLabel lblMisilSiberia;
    private javax.swing.JLabel lblMisilSudafrica;
    private javax.swing.JLabel lblMisilSumatra;
    private javax.swing.JLabel lblMisilTasmania;
    private javax.swing.JLabel lblMisilTerranova;
    private javax.swing.JLabel lblMisilTonga;
    private javax.swing.JLabel lblMisilTurquia;
    private javax.swing.JLabel lblMisilUcrania;
    private javax.swing.JLabel lblMisilUruguay;
    private javax.swing.JLabel lblMisilVenezuela;
    private javax.swing.JLabel lblMisilVietnam;
    private javax.swing.JLabel lblNewYork;
    private javax.swing.JLabel lblNicaragua;
    private javax.swing.JLabel lblNigeria;
    private javax.swing.JLabel lblNoruega;
    private javax.swing.JLabel lblNuevaZelandia;
    private javax.swing.JLabel lblOregon;
    private javax.swing.JLabel lblParaguay;
    private javax.swing.JLabel lblPolonia;
    private javax.swing.JLabel lblPortugal;
    private javax.swing.JLabel lblRusia;
    private javax.swing.JLabel lblSahara;
    private javax.swing.JLabel lblSerbia;
    private javax.swing.JLabel lblSiberia;
    private javax.swing.JLabel lblSudafrica;
    private javax.swing.JLabel lblSumatra;
    private javax.swing.JLabel lblTasmania;
    private javax.swing.JLabel lblTerranova;
    private javax.swing.JLabel lblTonga;
    private javax.swing.JLabel lblTurquia;
    private javax.swing.JLabel lblUcrania;
    private javax.swing.JLabel lblUruguay;
    private javax.swing.JLabel lblVenezuela;
    private javax.swing.JLabel lblVietnam;
    private javax.swing.JPanel panelAfrica;
    private javax.swing.JPanel panelAmericaDelNorrte;
    private javax.swing.JPanel panelAmericaDelSur;
    private javax.swing.JPanel panelAsia;
    private javax.swing.JPanel panelAsia2;
    private javax.swing.JPanel panelCentroAmerica;
    private javax.swing.JPanel panelOceania;
    private javax.swing.JTextArea txtMensajeGlobal;
    // End of variables declaration//GEN-END:variables
}
