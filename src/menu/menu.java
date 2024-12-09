/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JFrame;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.Timer;

//INPUT MENU
import menu.kriteria.kriteria;
import menu.subkriteria.subkriteria;
import menu.obat.obat;
import menu.hasil_seleksi.hasil_seleksi;
import menu.laporan.laporan;

/**
 *
 * @author Acer
 */
public class menu extends javax.swing.JFrame {
    private SimpleDateFormat dateFormat;
    private JLabel activeLabel = null; 
    
    kriteria kriteria = new kriteria();
    subkriteria subkriteria = new subkriteria();   
    obat penduduk = new obat();    
    hasil_seleksi hasil_seleksi = new hasil_seleksi();    
    laporan laporan = new laporan();

    /**
     * Creates new form menu
     */
    public menu() {
        setTitle("SPK PEMILIHAN OBAT BATUK TERBAIK - APOTIK RIFKAH ");
        initComponents();
        setResizable(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height); // Mengatur ukuran jendela sesuai dengan ukuran layar
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH); // Menyetel jendela menjadi fullscreen
         addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeComponents();
            }
        });
        // Set layout manager untuk panel utama
        jPanelMain.setLayout(new java.awt.CardLayout());

        // Menggunakan layout manager yang sesuai untuk panel
        kriteria.setLayout(new java.awt.BorderLayout());
        subkriteria.setLayout(new java.awt.BorderLayout());    
        penduduk.setLayout(new java.awt.BorderLayout());        
        hasil_seleksi.setLayout(new java.awt.BorderLayout());        
        laporan.setLayout(new java.awt.BorderLayout());


        // Menambahkan laporan ke jPanelMain dan mengatur panel yang terlihat
        jPanelMain.add(kriteria, "kriteria");
        jPanelMain.add(subkriteria, "subkriteria");  
        jPanelMain.add(penduduk, "penduduk");        
        jPanelMain.add(hasil_seleksi, "hasil_seleksi");        
        jPanelMain.add(laporan, "laporan");



         // Tambahkan MouseListener untuk efek hover
        addHoverEffect(jlabel_logout);
        addHoverEffect(jlabel_input_data_penduduk);
        addHoverEffect(jlabel_data_kriteria);        
        addHoverEffect(jlabel_data_sub_kriteria);        
        addHoverEffect(jlabel_hasil_seleksi);        
        addHoverEffect(jlabel_laporan);

        
        //Set Dashboard as default active label and visible panel
        setActiveLabel(jlabel_data_kriteria);
        ((java.awt.CardLayout) jPanelMain.getLayout()).show(jPanelMain, "kriteria"); 
        
        jPanelMain.setOpaque(false);
        
        // Format tanggal dan waktu untuk Waktu Indonesia Barat dengan Locale Indonesia
        dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy : HH:mm:ss", new Locale("id", "ID"));
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));

        // Update waktu setiap detik
        Timer timer = new Timer(1000, e -> updateDateTime());
        timer.start();
        
        // Set teks awal
        updateDateTime();
    }
    
    private void updateDateTime() {
        // Mendapatkan tanggal dan waktu saat ini
        String currentDateTime = dateFormat.format(new Date());
        // Set teks JLabel
        jlabel_time.setText(currentDateTime);
    }
    
    
    private void resizeComponents() {
        Dimension size = getSize();
        int width = size.width;
        int height = size.height;
    }
    
    private void addHoverEffect(final JLabel label) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!label.equals(activeLabel)) {
                    if (label.equals(jlabel_logout)) {
                        label.setForeground(Color.decode("#1ec6bc"));
                    } else {
                        label.setForeground(Color.GREEN);
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!label.equals(activeLabel)) {
                    if (label.equals(jlabel_logout)) {
                        label.setForeground(Color.WHITE);
                    } else {
                        label.setForeground(Color.decode("#1ec6bc"));
                    }
                }
            }
        });
    }

    
    private void setActiveLabel(JLabel newActiveLabel) {
        System.out.println("Setting active label: " + newActiveLabel.getText());

        if (activeLabel != null && !activeLabel.equals(newActiveLabel)) {
            System.out.println("Deactivating label: " + activeLabel.getText());
            activeLabel.setForeground(Color.decode("#1ec6bc")); // Reset color for the previously active label
        } else if (activeLabel == null) {
            System.out.println("No active label to deactivate.");
        }

        newActiveLabel.setForeground(Color.GREEN); // Set color for the newly active label
        activeLabel = newActiveLabel; // Update the active label reference

        // Force a repaint and revalidate
        revalidate();
        repaint();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlabel_logo_time = new javax.swing.JLabel();
        jlabel_time = new javax.swing.JLabel();
        jlabel_logout = new javax.swing.JLabel();
        jlabel_input_data_penduduk = new javax.swing.JLabel();
        jlabel_data_kriteria = new javax.swing.JLabel();
        jlabel_hasil_seleksi = new javax.swing.JLabel();
        jlabel_data_sub_kriteria = new javax.swing.JLabel();
        jPanelMain = new javax.swing.JPanel();
        jlabel_laporan = new javax.swing.JLabel();
        jlabel_layout = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jlabel_logo_time.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jlabel_logo_time.setForeground(new java.awt.Color(255, 255, 255));
        jlabel_logo_time.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Time.png"))); // NOI18N
        jlabel_logo_time.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlabel_logo_timeMouseClicked(evt);
            }
        });
        getContentPane().add(jlabel_logo_time);
        jlabel_logo_time.setBounds(260, 20, 30, 30);

        jlabel_time.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 17)); // NOI18N
        jlabel_time.setForeground(new java.awt.Color(30, 198, 188));
        jlabel_time.setText("Time");
        jlabel_time.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlabel_timeMouseClicked(evt);
            }
        });
        getContentPane().add(jlabel_time);
        jlabel_time.setBounds(300, 20, 290, 30);

        jlabel_logout.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 16)); // NOI18N
        jlabel_logout.setForeground(new java.awt.Color(255, 255, 255));
        jlabel_logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        jlabel_logout.setText("Logout");
        jlabel_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlabel_logoutMouseClicked(evt);
            }
        });
        getContentPane().add(jlabel_logout);
        jlabel_logout.setBounds(1240, 10, 110, 40);

        jlabel_input_data_penduduk.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 16)); // NOI18N
        jlabel_input_data_penduduk.setForeground(new java.awt.Color(30, 198, 188));
        jlabel_input_data_penduduk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Pill.png"))); // NOI18N
        jlabel_input_data_penduduk.setText("Input Data Obat");
        jlabel_input_data_penduduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlabel_input_data_pendudukMouseClicked(evt);
            }
        });
        getContentPane().add(jlabel_input_data_penduduk);
        jlabel_input_data_penduduk.setBounds(570, 80, 180, 30);

        jlabel_data_kriteria.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 16)); // NOI18N
        jlabel_data_kriteria.setForeground(new java.awt.Color(30, 198, 188));
        jlabel_data_kriteria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Audit.png"))); // NOI18N
        jlabel_data_kriteria.setText("Data Kriteria");
        jlabel_data_kriteria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlabel_data_kriteriaMouseClicked(evt);
            }
        });
        getContentPane().add(jlabel_data_kriteria);
        jlabel_data_kriteria.setBounds(210, 80, 150, 30);

        jlabel_hasil_seleksi.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 16)); // NOI18N
        jlabel_hasil_seleksi.setForeground(new java.awt.Color(30, 198, 188));
        jlabel_hasil_seleksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Services.png"))); // NOI18N
        jlabel_hasil_seleksi.setText("Perhitungan Metode SAW");
        jlabel_hasil_seleksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlabel_hasil_seleksiMouseClicked(evt);
            }
        });
        getContentPane().add(jlabel_hasil_seleksi);
        jlabel_hasil_seleksi.setBounds(760, 80, 250, 30);

        jlabel_data_sub_kriteria.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 16)); // NOI18N
        jlabel_data_sub_kriteria.setForeground(new java.awt.Color(30, 198, 188));
        jlabel_data_sub_kriteria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clipboard.png"))); // NOI18N
        jlabel_data_sub_kriteria.setText("Data Sub Kriteria");
        jlabel_data_sub_kriteria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlabel_data_sub_kriteriaMouseClicked(evt);
            }
        });
        getContentPane().add(jlabel_data_sub_kriteria);
        jlabel_data_sub_kriteria.setBounds(370, 80, 180, 30);

        jPanelMain.setBackground(new java.awt.Color(30, 198, 188));
        jPanelMain.setLayout(new java.awt.CardLayout());
        getContentPane().add(jPanelMain);
        jPanelMain.setBounds(30, 140, 1310, 580);

        jlabel_laporan.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 16)); // NOI18N
        jlabel_laporan.setForeground(new java.awt.Color(30, 198, 188));
        jlabel_laporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Magazine.png"))); // NOI18N
        jlabel_laporan.setText("Laporan");
        jlabel_laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlabel_laporanMouseClicked(evt);
            }
        });
        getContentPane().add(jlabel_laporan);
        jlabel_laporan.setBounds(1030, 80, 140, 30);

        jlabel_layout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/layout-apotik-rifkah.png"))); // NOI18N
        jlabel_layout.setText("jLabel1");
        getContentPane().add(jlabel_layout);
        jlabel_layout.setBounds(0, 0, 1362, 768);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jlabel_logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlabel_logoutMouseClicked
        dispose();
    }//GEN-LAST:event_jlabel_logoutMouseClicked

    private void jlabel_data_kriteriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlabel_data_kriteriaMouseClicked
         setActiveLabel(jlabel_data_kriteria);
        ((java.awt.CardLayout) jPanelMain.getLayout()).show(jPanelMain, "kriteria");
    }//GEN-LAST:event_jlabel_data_kriteriaMouseClicked

    private void jlabel_data_sub_kriteriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlabel_data_sub_kriteriaMouseClicked
        setActiveLabel(jlabel_data_sub_kriteria);
        ((java.awt.CardLayout) jPanelMain.getLayout()).show(jPanelMain, "subkriteria");
    }//GEN-LAST:event_jlabel_data_sub_kriteriaMouseClicked

    private void jlabel_logo_timeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlabel_logo_timeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jlabel_logo_timeMouseClicked

    private void jlabel_timeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlabel_timeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jlabel_timeMouseClicked

    private void jlabel_input_data_pendudukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlabel_input_data_pendudukMouseClicked
        setActiveLabel(jlabel_input_data_penduduk);
        ((java.awt.CardLayout) jPanelMain.getLayout()).show(jPanelMain, "penduduk");
    }//GEN-LAST:event_jlabel_input_data_pendudukMouseClicked

    private void jlabel_hasil_seleksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlabel_hasil_seleksiMouseClicked
        setActiveLabel(jlabel_hasil_seleksi);
        ((java.awt.CardLayout) jPanelMain.getLayout()).show(jPanelMain, "hasil_seleksi");
    }//GEN-LAST:event_jlabel_hasil_seleksiMouseClicked

    private void jlabel_laporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlabel_laporanMouseClicked
         setActiveLabel(jlabel_laporan);
        ((java.awt.CardLayout) jPanelMain.getLayout()).show(jPanelMain, "laporan");
    }//GEN-LAST:event_jlabel_laporanMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JLabel jlabel_data_kriteria;
    private javax.swing.JLabel jlabel_data_sub_kriteria;
    private javax.swing.JLabel jlabel_hasil_seleksi;
    private javax.swing.JLabel jlabel_input_data_penduduk;
    private javax.swing.JLabel jlabel_laporan;
    private javax.swing.JLabel jlabel_layout;
    private javax.swing.JLabel jlabel_logo_time;
    private javax.swing.JLabel jlabel_logout;
    private javax.swing.JLabel jlabel_time;
    // End of variables declaration//GEN-END:variables

}
