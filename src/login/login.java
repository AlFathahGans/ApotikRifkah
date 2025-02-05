/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;
import koneksi.koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import apotikrifkah.UserSession;
/**
 *
 * @author Acer
 */
public class login extends javax.swing.JFrame {
    private Connection conne = new koneksi().getConnection();

    /**
     * Creates new form login
     */
    public login() {
        initComponents();
        
         txt_username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Hapus teks ketika JTextField diklik
                txt_username.setText("");
            }
        });
         
        txt_password.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Hapus teks ketika jLabel diklik
                jLabel2.setText("");
            }
        });

        txt_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                // Hapus teks ketika JPasswordField txtpass diberikan fokus
                txt_password.setText("");
                 jLabel2.setText("");
            }
        });
        
        setSize(506,525);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // Pusatkan jendela ditengah
        setLocationRelativeTo(null); 
        //Nonaktifkan size dijendela
        setResizable(false);
    }
    
    // Tambahkan variabel global untuk menyimpan id_user
    private int userId;

    void proses_login(){
        String username = txt_username.getText();
        String password = txt_password.getText();

        // Mencoba melakukan login dengan data dari database
        try {
            // Mencari pengguna berdasarkan username
            String query = "SELECT * FROM tbl_user WHERE username=?";
            PreparedStatement pstmt = conne.prepareStatement(query);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Jika pengguna ditemukan, bandingkan password
                String dbPasswordHashed = rs.getString("password");
                
                int userId = rs.getInt("id");
                String userName = rs.getString("username");
                // Simpan ID dan nama ke variabel global atau kelas penyimpanan
                UserSession.getInstance().setUserId(userId);
                UserSession.getInstance().setUserName(userName); // Ambil id_user dari hasil query
                
                // Lakukan validasi password (Anda bisa menggunakan library hashing seperti BCrypt)
                if (dbPasswordHashed.equals(password)) {
                    // Jika login berhasil, tampilkan pesan selamat datang
                    JOptionPane.showMessageDialog(null, "Selamat datang, " + toSentenceCase(username) + "!");
                    
                    new menu.menu().setVisible(true);
                    this.dispose();
                } else {
                    // Jika password salah, tampilkan pesan kesalahan
                    JOptionPane.showMessageDialog(null, "Password salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Jika pengguna tidak ditemukan, tampilkan pesan kesalahan
                JOptionPane.showMessageDialog(null, "Username tidak ditemukan!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
            }

            conne.close();
        } catch (Exception e) {
            // Tangani error jika ada masalah dengan koneksi atau query
            e.printStackTrace();
        }
    }
    
    //Fungsi Setence Case 
    public static String toSentenceCase(String input){
        if(input == null || input.isEmpty()){
            return input;
        }
        return input.substring(0,1).toUpperCase() + input.substring(1).toLowerCase();
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_username = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();
        btn_login = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_username.setText("Username");
        txt_username.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txt_username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_usernameMouseClicked(evt);
            }
        });
        txt_username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_usernameKeyPressed(evt);
            }
        });
        getContentPane().add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, 270, 30));

        jLabel2.setText("Password");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 312, -1, 20));

        txt_password.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txt_password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_passwordFocusGained(evt);
            }
        });
        txt_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_passwordKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_passwordKeyTyped(evt);
            }
        });
        getContentPane().add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 309, 250, 29));

        btn_login.setBackground(new java.awt.Color(26, 172, 183));
        btn_login.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 14)); // NOI18N
        btn_login.setForeground(new java.awt.Color(255, 255, 255));
        btn_login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Import.png"))); // NOI18N
        btn_login.setText("Login");
        btn_login.setToolTipText("Klik Untuk Login");
        btn_login.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });
        getContentPane().add(btn_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 360, 290, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/layout-login.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_usernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_usernameMouseClicked
        
    }//GEN-LAST:event_txt_usernameMouseClicked

    private void txt_usernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_usernameKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) proses_login();
    }//GEN-LAST:event_txt_usernameKeyPressed

    private void txt_passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passwordKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) proses_login();
    }//GEN-LAST:event_txt_passwordKeyPressed

    private void txt_passwordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passwordKeyTyped
      
    }//GEN-LAST:event_txt_passwordKeyTyped

    private void txt_passwordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_passwordFocusGained
        jLabel2.setText("");
    }//GEN-LAST:event_txt_passwordFocusGained

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        proses_login();
    }//GEN-LAST:event_btn_loginActionPerformed

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
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_login;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
