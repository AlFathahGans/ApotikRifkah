/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu.kriteria;

import java.awt.Color;
import koneksi.koneksi;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Locale;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.util.Locale;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import apotikrifkah.exceptionData;

/**
 *
 * @author Acer
 */
public class kriteria extends javax.swing.JPanel {
    
    private Connection conne = new koneksi().getConnection();
    koneksi conn= new koneksi();
    
    DefaultTableModel tbk;

    /**
     * Creates new form kriteria
     */
    public kriteria() {
        initComponents();
        
        tabelmodelkriteria();
        setHeaderTable();
        
        Locale locale = new Locale("id","ID");
        Locale.setDefault(locale);
        
        setSize(1030, 630); // Ukuran panel sesuai kebutuhan
        // Set background panel menjadi transparan
        setOpaque(false); 
        
        btn_update.setEnabled(false);       
        btn_hapus.setEnabled(false);

    }
    
    public void tabelmodelkriteria(){
       tbk =new DefaultTableModel();
       tbk.addColumn("No.");
       tbk.addColumn("Kode");       
       tbk.addColumn("Nama");
       tbk.addColumn("Bobot");
       jTabelKriteria.setModel(tbk); 
       try {
        int no=1;
        ResultSet res = conn.ambilData("SELECT kode,nama,bobot FROM tbl_kriteria ");
        while (res.next()){
            tbk.addRow(new Object[]{
                no++,
                res.getString(1),
                res.getString(2),
                res.getString(3)

            });
        } 
      
        jTabelKriteria.setModel(tbk);
        setHeaderTable();
          } catch (SQLException ex) {
           JOptionPane.showMessageDialog(this, ex);
        }
       
        // Mengatur lebar kolom
        TableColumnModel columnModel = jTabelKriteria.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(40);  // Lebar kolom No.
        columnModel.getColumn(1).setPreferredWidth(40); // Lebar kolom Kode
        columnModel.getColumn(2).setPreferredWidth(180); // Lebar kolom Nama
        columnModel.getColumn(3).setPreferredWidth(40); // Lebar kolom Bobot       

    }
    
    private void setHeaderTable() {
        // Membuat renderer untuk header tabel
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        
        headerRenderer.setBackground(Color.decode("#1ec6bc")); // Mengatur warna latar belakang
        headerRenderer.setForeground(Color.WHITE); // Mengatur warna teks
        
        // Mengatur renderer untuk setiap kolom di header tabel
        for (int i = 0; i < jTabelKriteria.getColumnModel().getColumnCount(); i++) {
            jTabelKriteria.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        
         // Mengatur tinggi header tabel
        JTableHeader header = jTabelKriteria.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 25));
    }
    
    private void clear(){
        jTextField1.setText("");
        jTextField2.setText((""));
        jTextField3.setText("");
        
        btn_simpan.setEnabled(true);
        btn_update.setEnabled(false);       
        btn_hapus.setEnabled(false);
    }
    
    private void cekKosong() throws exceptionData
    {
       if(jTextField1.getText().isEmpty()==true || jTextField2.getText().isEmpty()==true
               || jTextField2.getText().isEmpty() == true || jTextField3.getText().isEmpty() == true)
            throw new exceptionData();
    }

    public int simpan(){
        try {
            PreparedStatement stmt = conne.prepareStatement("INSERT INTO tbl_kriteria(kode, nama, bobot) values(?,?,?)");
            stmt.setString(1, jTextField1.getText());
            stmt.setString(2, jTextField2.getText());
            stmt.setString(3, jTextField3.getText());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        clear();
        return 0;
    }
    
    public void edit() {
        try {
            
            String a = jTextField1.getText();
            String b = jTextField2.getText();
            String c = jTextField3.getText();

            //Query SQL
            String sql = "UPDATE tbl_kriteria SET nama='" + b + "', bobot='" + c + "' WHERE kode='" + a + "'";

            conn.st.execute(sql);
            JOptionPane.showMessageDialog(null, "Data Berhasil Terupdate");
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Terupdate");
            System.out.println(e.getMessage());
        }
        clear();
        tabelmodelkriteria();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        btn_simpan = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabelKriteria = new javax.swing.JTable();

        setBackground(new java.awt.Color(0, 204, 229));
        setLayout(null);

        jLabel2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Form Data Kriteria");
        add(jLabel2);
        jLabel2.setBounds(40, 30, 299, 43);

        jLabel3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama Kriteria");
        add(jLabel3);
        jLabel3.setBounds(40, 180, 100, 21);

        jLabel9.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Bobot");
        add(jLabel9);
        jLabel9.setBounds(40, 240, 90, 21);

        jLabel6.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 30)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tabel Data Kriteria");
        add(jLabel6);
        jLabel6.setBounds(500, 30, 287, 43);

        jLabel10.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Kode Kriteria");
        add(jLabel10);
        jLabel10.setBounds(40, 120, 100, 21);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        add(jTextField1);
        jTextField1.setBounds(150, 110, 290, 40);

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        add(jTextField2);
        jTextField2.setBounds(150, 170, 290, 40);

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        add(jTextField3);
        jTextField3.setBounds(150, 230, 290, 40);

        btn_simpan.setBackground(new java.awt.Color(30, 230, 110));
        btn_simpan.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 14)); // NOI18N
        btn_simpan.setForeground(new java.awt.Color(255, 255, 255));
        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Plus Math.png"))); // NOI18N
        btn_simpan.setText("Simpan");
        btn_simpan.setToolTipText("Klik untuk menyimpan data.");
        btn_simpan.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btn_simpan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        add(btn_simpan);
        btn_simpan.setBounds(60, 310, 129, 42);

        btn_update.setBackground(new java.awt.Color(30, 230, 110));
        btn_update.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 14)); // NOI18N
        btn_update.setForeground(new java.awt.Color(255, 255, 255));
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        btn_update.setText("Update");
        btn_update.setToolTipText("Klik untuk mengupdate data.");
        btn_update.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btn_update.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        add(btn_update);
        btn_update.setBounds(200, 310, 129, 42);

        btn_hapus.setBackground(new java.awt.Color(30, 230, 110));
        btn_hapus.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 14)); // NOI18N
        btn_hapus.setForeground(new java.awt.Color(255, 255, 255));
        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close.png"))); // NOI18N
        btn_hapus.setText("Hapus");
        btn_hapus.setToolTipText("Klik untuk menghapus data.");
        btn_hapus.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btn_hapus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        add(btn_hapus);
        btn_hapus.setBounds(340, 310, 129, 42);

        jScrollPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane1.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 18)); // NOI18N

        jTabelKriteria.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jTabelKriteria.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jTabelKriteria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No.", "Kode Kriteria", "Nama Kriteria", "Bobot"
            }
        ));
        jTabelKriteria.setFillsViewportHeight(true);
        jTabelKriteria.setMinimumSize(new java.awt.Dimension(95, 100));
        jTabelKriteria.setRowHeight(25);
        jTabelKriteria.setSelectionBackground(new java.awt.Color(30, 198, 188));
        jTabelKriteria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabelKriteriaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTabelKriteria);
        if (jTabelKriteria.getColumnModel().getColumnCount() > 0) {
            jTabelKriteria.getColumnModel().getColumn(0).setMinWidth(10);
            jTabelKriteria.getColumnModel().getColumn(0).setMaxWidth(20);
        }

        add(jScrollPane1);
        jScrollPane1.setBounds(510, 110, 750, 131);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        try {
            cekKosong();
        } catch(exceptionData ex) {
            JOptionPane.showMessageDialog(null, ex.showMessageError());
        }

        simpan();
        tabelmodelkriteria();
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        edit();
        //Field NonDisabled
        jTextField1.setEnabled(true);
        clear();
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Yakin Dihapus ?","Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        if(ok == 0){
            String sql = "DELETE FROM tbl_kriteria WHERE kode='"+jTextField1.getText()+"'"; //edit menggunakan kode
            System.out.println(sql);
            conn.simpanData(sql);
            //conn.simpanData(sql);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            tabelmodelkriteria();//tambahkan
            clear();
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void jTabelKriteriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelKriteriaMouseClicked
        int bar = jTabelKriteria.getSelectedRow();
        
        String a = jTabelKriteria.getValueAt(bar, 1).toString();
        String b = jTabelKriteria.getValueAt(bar, 2).toString();
        String c = jTabelKriteria.getValueAt(bar, 3).toString();
        //Field Disabled
        jTextField1.setEnabled(false);

        jTextField1.setText(a);
        jTextField2.setText(b);
        jTextField3.setText(c);
        

        btn_simpan.setEnabled(false);
        btn_update.setEnabled(true);       
        btn_hapus.setEnabled(true);
    }//GEN-LAST:event_jTabelKriteriaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTabelKriteria;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
