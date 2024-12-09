/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu.obat;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import koneksi.koneksi;
import apotikrifkah.exceptionData;
import java.sql.Statement;
import javax.swing.JComboBox;

/**
 *
 * @author Acer
 */
public class obat extends javax.swing.JPanel {
    private Connection conne = new koneksi().getConnection();
    koneksi conn= new koneksi();
    

    
    DefaultTableModel tbdo;    
    DefaultTableModel tbno;

    /**
     * Creates new form obat
     */
    public obat() {
        initComponents();
        
        tabelmodeldataobat();        
        tabelmodelnilaiobat();
        
        jTabelNilaiObat.setEnabled(false);

        setHeaderTable();
        
        Locale locale = new Locale("id","ID");
        Locale.setDefault(locale);
        
        setSize(1030, 630); // Ukuran panel sesuai kebutuhan
        // Set background panel menjadi transparan
        setOpaque(false); 
        
        btn_update.setEnabled(false);       
        btn_hapus.setEnabled(false);
        
        dataListFromUsia();        
        dataListFromHarga(); 
        dataListFromTipeObat(); 
        dataListFromEfekSamping(); 
 

    }
    
    public void tabelmodeldataobat(){
       tbdo =new DefaultTableModel();
       tbdo.addColumn("ID"); // Kolom tambahan untuk ID
       tbdo.addColumn("No.");
       tbdo.addColumn("Nama");       
       tbdo.addColumn("Merek");       
       tbdo.addColumn("Usia");
       tbdo.addColumn("Harga");
       tbdo.addColumn("Tipe Obat");
       tbdo.addColumn("Efek Samping");


       jTabelDataObat.setModel(tbdo); 
       try {
        int no=1;
        ResultSet res = conn.ambilData(
            "SELECT \n" +
            "    a.id,\n" +            
            "    a.nama,\n" +
            "    a.merek,\n" +
            "    MAX(CASE WHEN k.kode = 'C1' THEN sk.sub_nama END) AS C1,\n" +
            "    MAX(CASE WHEN k.kode = 'C2' THEN sk.sub_nama END) AS C2,\n" +
            "    MAX(CASE WHEN k.kode = 'C3' THEN sk.sub_nama END) AS C3,\n" +
            "    MAX(CASE WHEN k.kode = 'C4' THEN sk.sub_nama END) AS C4\n" +
            "FROM\n" +
            "    tbl_alternatif a\n" +
            "JOIN\n" +
            "    tbl_nilai_alternatif na ON a.id = na.alternatif_id\n" +
            "JOIN\n" +
            "    tbl_kriteria k ON na.kriteria_id = k.id\n" +
            "JOIN\n" +
            "    tbl_sub_kriteria sk ON na.sub_kriteria_id = sk.id\n" +
            "GROUP BY\n" +
            "    a.id, a.nama\n" +
            "ORDER BY\n" +
            "    a.kode;"
        );
        while (res.next()){
            tbdo.addRow(new Object[]{
                res.getString(1),
                no++,
                res.getString(2),
                res.getString(3),               
                res.getString(4),                
                res.getString(5),
                res.getString(6),
                res.getString(7)

            });
        } 
      
        jTabelDataObat.setModel(tbdo);
        
        // Menyembunyikan kolom ID
        jTabelDataObat.getColumnModel().getColumn(0).setMaxWidth(0);
        jTabelDataObat.getColumnModel().getColumn(0).setMinWidth(0);
        jTabelDataObat.getColumnModel().getColumn(0).setPreferredWidth(0);
            
        setHeaderTable();
        
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(this, ex);
        }
       
        // Mengatur lebar kolom
        TableColumnModel columnModel = jTabelDataObat.getColumnModel();
        columnModel.getColumn(1).setPreferredWidth(40);  // Lebar kolom No.
        columnModel.getColumn(2).setPreferredWidth(200); // Lebar kolom Nama
        columnModel.getColumn(3).setPreferredWidth(100); // Lebar kolom Merek  
        columnModel.getColumn(4).setPreferredWidth(100); // Lebar kolom Usia
        columnModel.getColumn(5).setPreferredWidth(100); // Lebar kolom Harga
        columnModel.getColumn(6).setPreferredWidth(100); // Lebar kolom Tipe Obat
        columnModel.getColumn(7).setPreferredWidth(100); // Lebar kolom Efek Samping
    }
    
    public void tabelmodelnilaiobat() {
        tbno = new DefaultTableModel();
        tbno.addColumn("ID"); // Kolom tambahan untuk ID
        tbno.addColumn("No.");
        tbno.addColumn("Nama");       
        tbno.addColumn("Merek");
        tbno.addColumn("Usia");
        tbno.addColumn("Harga");
        tbno.addColumn("Tipe Obat");
        tbno.addColumn("Efek Samping");
        jTabelNilaiObat.setModel(tbno); 
        
        try {
            int no=1;
            ResultSet res = conn.ambilData(
                "SELECT \n" +
                "    a.id,\n" +            
                "    a.nama,\n" +
                "    a.merek,\n" +
                "    MAX(CASE WHEN k.kode = 'C1' THEN sk.nilai_rating END) AS C1,\n" +
                "    MAX(CASE WHEN k.kode = 'C2' THEN sk.nilai_rating END) AS C2,\n" +
                "    MAX(CASE WHEN k.kode = 'C3' THEN sk.nilai_rating END) AS C3,\n" +
                "    MAX(CASE WHEN k.kode = 'C4' THEN sk.nilai_rating END) AS C4\n" +
                "FROM\n" +
                "    tbl_alternatif a\n" +
                "JOIN\n" +
                "    tbl_nilai_alternatif na ON a.id = na.alternatif_id\n" +
                "JOIN\n" +
                "    tbl_kriteria k ON na.kriteria_id = k.id\n" +
                "JOIN\n" +
                "    tbl_sub_kriteria sk ON na.sub_kriteria_id = sk.id\n" +
                "GROUP BY\n" +
                "    a.id, a.nama\n" +
                "ORDER BY\n" +
                "    a.kode;"
            );

            while (res.next()) {
                tbno.addRow(new Object[]{
                    res.getString(1), // Menyimpan ID di kolom pertama
                    no++,
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),                    
                    res.getString(5),                    
                    res.getString(6),
                    res.getString(7),


                });
            }

            jTabelNilaiObat.setModel(tbno);
            
             // Menyembunyikan kolom ID
            jTabelNilaiObat.getColumnModel().getColumn(0).setMaxWidth(0);
            jTabelNilaiObat.getColumnModel().getColumn(0).setMinWidth(0);
            jTabelNilaiObat.getColumnModel().getColumn(0).setPreferredWidth(0);
            
            setHeaderTable();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
        
        // Mengatur lebar kolom
        TableColumnModel columnModel = jTabelNilaiObat.getColumnModel();
        columnModel.getColumn(1).setPreferredWidth(40);  // Lebar kolom No.
        columnModel.getColumn(2).setPreferredWidth(200); // Lebar kolom Nama
        columnModel.getColumn(3).setPreferredWidth(100); // Lebar kolom Merek  
        columnModel.getColumn(4).setPreferredWidth(100); // Lebar kolom Usia
        columnModel.getColumn(5).setPreferredWidth(100); // Lebar kolom Harga
        columnModel.getColumn(6).setPreferredWidth(100); // Lebar kolom Tipe Obat
        columnModel.getColumn(7).setPreferredWidth(100); // Lebar kolom Efek Samping
    }
    
    private void setHeaderTable() {
        // Membuat renderer untuk header tabel
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        
        headerRenderer.setBackground(Color.decode("#1ec6bc")); // Mengatur warna latar belakang
        headerRenderer.setForeground(Color.WHITE); // Mengatur warna teks
        
        // Mengatur renderer untuk setiap kolom di header tabel
        for (int i = 0; i < jTabelDataObat.getColumnModel().getColumnCount(); i++) {
            jTabelDataObat.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        
        // Mengatur tinggi header tabel
        JTableHeader headerData = jTabelDataObat.getTableHeader();
        headerData.setPreferredSize(new Dimension(headerData.getWidth(), 25));
        
        // Mengatur renderer untuk setiap kolom di header tabel
        for (int i = 0; i < jTabelNilaiObat.getColumnModel().getColumnCount(); i++) {
            jTabelNilaiObat.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        
        // Mengatur tinggi header tabel
        JTableHeader headerNilai = jTabelNilaiObat.getTableHeader();
        headerNilai.setPreferredSize(new Dimension(headerNilai.getWidth(), 25));
    }
    
    public void dataListFromUsia() {                                           
        Connection connect = conn.getConnection();
        try {
            String query = "SELECT * FROM tbl_sub_kriteria WHERE kriteria_id = 1";
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(query);

            jComboBox1.removeAllItems(); // Clear existing items
            jComboBox1.addItem("[Pilih Usia]");
            while (rs.next()) {
                System.out.println(rs.getString("id") + " - " + rs.getString("sub_nama"));
                jComboBox1.addItem(rs.getString("id") + " - " + rs.getString("sub_nama")); // Assuming ID and name format
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada.");
            System.out.println(e.getMessage());
        }
    }
    public void dataListFromHarga() {                                           
        Connection connect = conn.getConnection();
        try {
            String query = "SELECT * FROM tbl_sub_kriteria WHERE kriteria_id = 2";
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(query);

            jComboBox2.removeAllItems(); // Clear existing items
            jComboBox2.addItem("[Pilih Harga]");
            while (rs.next()) {
                System.out.println(rs.getString("id") + " - " + rs.getString("sub_nama"));
                jComboBox2.addItem(rs.getString("id") + " - " + rs.getString("sub_nama")); // Assuming ID and name format
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada.");
            System.out.println(e.getMessage());
        }
    }
    
    public void dataListFromTipeObat() {                                           
        Connection connect = conn.getConnection();
        try {
            String query = "SELECT * FROM tbl_sub_kriteria WHERE kriteria_id = 3";
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(query);

            jComboBox3.removeAllItems(); // Clear existing items
            jComboBox3.addItem("[Pilih Tipe Obat]");
            while (rs.next()) {
                System.out.println(rs.getString("id") + " - " + rs.getString("sub_nama"));
                jComboBox3.addItem(rs.getString("id") + " - " + rs.getString("sub_nama")); // Assuming ID and name format
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada.");
            System.out.println(e.getMessage());
        }
    }
    
    public void dataListFromEfekSamping() {                                           
        Connection connect = conn.getConnection();
        try {
            String query = "SELECT * FROM tbl_sub_kriteria WHERE kriteria_id = 4";
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(query);

            jComboBox4.removeAllItems(); // Clear existing items
            jComboBox4.addItem("[Pilih Efek Samping]");
            while (rs.next()) {
                System.out.println(rs.getString("id") + " - " + rs.getString("sub_nama"));
                jComboBox4.addItem(rs.getString("id") + " - " + rs.getString("sub_nama")); // Assuming ID and name format
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada.");
            System.out.println(e.getMessage());
        }
    }
    
    private void clear(){
        jTextField1.setText("");
        jTextField2.setText((""));
        jComboBox1.setSelectedIndex(0);        
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
        
        btn_simpan.setEnabled(true);
        btn_update.setEnabled(false);       
        btn_hapus.setEnabled(false);
    }
    
    private void cekKosong() throws exceptionData
    {
       if(jTextField1.getText().isEmpty()==true || 
        jTextField2.getText().isEmpty()==true ||
        jComboBox1.getSelectedIndex() == 0 || 
        jComboBox2.getSelectedIndex() == 0 || 
        jComboBox3.getSelectedIndex() == 0 || 
        jComboBox4.getSelectedIndex() == 0)
        throw new exceptionData();
    }

    public int simpan(){
        try {
            
            String idUsia = jComboBox1.getSelectedItem().toString().split(" - ")[0];        
            String idHarga = jComboBox2.getSelectedItem().toString().split(" - ")[0];
            String idTipeObat = jComboBox3.getSelectedItem().toString().split(" - ")[0];
            String idEfekSamping = jComboBox4.getSelectedItem().toString().split(" - ")[0];
            
            // Cek apakah semua combo box sudah dipilih
            if (jTextField1.getText().isEmpty()==true || jTextField2.getText().isEmpty()==true || idUsia.equals("[Pilih Usia]") || idHarga.equals("[Pilih Harga]") || idTipeObat.equals("[Pilih Tipe Obat]") || idEfekSamping.equals("[Pilih Efek Samping]")) {
                JOptionPane.showMessageDialog(this, "Silakan pilih semua opsi sebelum menyimpan data.");
                return 0;
            }

            System.out.println(idUsia+idHarga+idTipeObat +idEfekSamping);
        
             // Query untuk menghitung jumlah data yang ada di tabel tbl_alternatif
            String countQuery = "SELECT COUNT(id) AS total FROM tbl_alternatif";
            ResultSet res = conn.ambilData(countQuery);
            int total = 0;

            if (res.next()) {
                total = res.getInt("total");
            }

            // Tentukan nilai kode berikutnya
            String kode = "A" + (total + 1);
        
            PreparedStatement stmt = conne.prepareStatement("INSERT INTO tbl_alternatif(kode, nama, merek) values(?,?,?)", 
                Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, kode);
            stmt.setString(2, jTextField1.getText());
            stmt.setString(3, jTextField2.getText());           
            

            stmt.executeUpdate();
            
            // Ambil alternatif_id yang baru saja dimasukkan
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            int alternatifId = 0;
            if (generatedKeys.next()) {
                alternatifId = generatedKeys.getInt(1); // Ambil id alternatif yang baru dibuat
            } else {
                throw new SQLException("Gagal mendapatkan alternatif_id");
            }

            // Insert into tbl_nilai_kriteria
            String sqlNilaiKriteria = "INSERT INTO tbl_nilai_alternatif(alternatif_id, kriteria_id, sub_kriteria_id) VALUES (?, ?, ?)";
            PreparedStatement stmtNilaiKriteria = conne.prepareStatement(sqlNilaiKriteria);

            // idUsia
            stmtNilaiKriteria.setInt(1, alternatifId);
            stmtNilaiKriteria.setInt(2, 1);  // kriteria_id untuk Usia
            stmtNilaiKriteria.setInt(3, Integer.parseInt(idUsia));
            stmtNilaiKriteria.addBatch();

            // idHarga
            stmtNilaiKriteria.setInt(1, alternatifId);
            stmtNilaiKriteria.setInt(2, 2);  // kriteria_id untuk Harga
            stmtNilaiKriteria.setInt(3, Integer.parseInt(idHarga));
            stmtNilaiKriteria.addBatch();

            // idTipeObat
            stmtNilaiKriteria.setInt(1, alternatifId);
            stmtNilaiKriteria.setInt(2, 3);  // kriteria_id untuk Tipe Obat
            stmtNilaiKriteria.setInt(3, Integer.parseInt(idTipeObat));
            stmtNilaiKriteria.addBatch();

            // idEfekSamping
            stmtNilaiKriteria.setInt(1, alternatifId);
            stmtNilaiKriteria.setInt(2, 4);  // kriteria_id untuk Efek Samping
            stmtNilaiKriteria.setInt(3, Integer.parseInt(idEfekSamping));
            stmtNilaiKriteria.addBatch();

            // Execute batch insert
            stmtNilaiKriteria.executeBatch();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        clear();
        return 0;
    }
    
    public void edit() {
        try {
            // Ambil ID dari baris yang dipilih di tabel
            String alternatifId = jTabelDataObat.getValueAt(jTabelDataObat.getSelectedRow(), 0).toString(); 
            
            String a = jTextField1.getText();
            String b = jTextField2.getText();

            // Ambil nilai dari ComboBox
            String idUsia = jComboBox1.getSelectedItem().toString().split(" - ")[0];        
            String idHarga = jComboBox2.getSelectedItem().toString().split(" - ")[0];
            String idTipeObat = jComboBox3.getSelectedItem().toString().split(" - ")[0];
            String idEfekSamping = jComboBox4.getSelectedItem().toString().split(" - ")[0];

            System.out.println(idUsia + idHarga + idTipeObat + idEfekSamping);

            // Update tbl_alternatif
            String sqlUpdateAlternatif = "UPDATE tbl_alternatif SET nama = ?, merek = ? WHERE id = ?"; 
            PreparedStatement stmtUpdateAlternatif = conne.prepareStatement(sqlUpdateAlternatif);
            stmtUpdateAlternatif.setString(1, a);            
            stmtUpdateAlternatif.setString(2, b);
            stmtUpdateAlternatif.setString(3, alternatifId);
            stmtUpdateAlternatif.executeUpdate();

            // Update tbl_nilai_alternatif
            String sqlUpdateNilaiKriteria = "UPDATE tbl_nilai_alternatif SET sub_kriteria_id = ? WHERE alternatif_id = ? AND kriteria_id = ?";

            // idUsia
            PreparedStatement stmtUpdateKualitas = conne.prepareStatement(sqlUpdateNilaiKriteria);
            stmtUpdateKualitas.setInt(1, Integer.parseInt(idUsia));
            stmtUpdateKualitas.setString(2, alternatifId);
            stmtUpdateKualitas.setInt(3, 1);  // kriteria_id untuk Kualitas
            stmtUpdateKualitas.executeUpdate();

            // idHarga
            PreparedStatement stmtUpdateHarga = conne.prepareStatement(sqlUpdateNilaiKriteria);
            stmtUpdateHarga.setInt(1, Integer.parseInt(idHarga));
            stmtUpdateHarga.setString(2, alternatifId);
            stmtUpdateHarga.setInt(3, 2);  // kriteria_id untuk Harga
            stmtUpdateHarga.executeUpdate();

            // idTipeObat
            PreparedStatement stmtUpdateMerek = conne.prepareStatement(sqlUpdateNilaiKriteria);
            stmtUpdateMerek.setInt(1, Integer.parseInt(idTipeObat));
            stmtUpdateMerek.setString(2, alternatifId);
            stmtUpdateMerek.setInt(3, 3);  // kriteria_id untuk Merek
            stmtUpdateMerek.executeUpdate();

            // idEfekSamping
            PreparedStatement stmtUpdateWarnaMotif = conne.prepareStatement(sqlUpdateNilaiKriteria);
            stmtUpdateWarnaMotif.setInt(1, Integer.parseInt(idEfekSamping));
            stmtUpdateWarnaMotif.setString(2, alternatifId);
            stmtUpdateWarnaMotif.setInt(3, 4);  // kriteria_id untuk WarnaMotif
            stmtUpdateWarnaMotif.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data Berhasil Terupdate");
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Terupdate");
            System.out.println(e.getMessage());
        }
        
        clear();
        tabelmodeldataobat();                
        tabelmodelnilaiobat();
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
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        btn_simpan = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabelDataObat = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTabelNilaiObat = new javax.swing.JTable();

        setBackground(new java.awt.Color(0, 204, 229));
        setLayout(null);

        jLabel2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Form Data Obat");
        add(jLabel2);
        jLabel2.setBounds(40, 30, 380, 43);

        jLabel10.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Nama");
        add(jLabel10);
        jLabel10.setBounds(40, 110, 60, 21);

        jLabel8.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Merek");
        add(jLabel8);
        jLabel8.setBounds(40, 160, 60, 21);

        jLabel3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Usia");
        add(jLabel3);
        jLabel3.setBounds(40, 210, 60, 21);

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        add(jTextField2);
        jTextField2.setBounds(150, 150, 280, 40);

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
        btn_simpan.setBounds(40, 430, 129, 42);

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
        btn_update.setBounds(190, 430, 129, 42);

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
        btn_hapus.setBounds(340, 430, 129, 42);

        jLabel6.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 30)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tabel Data Obat");
        add(jLabel6);
        jLabel6.setBounds(510, 30, 287, 43);

        jScrollPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane1.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 18)); // NOI18N

        jTabelDataObat.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jTabelDataObat.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jTabelDataObat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "No.", "Nama", "Merek", "Usia", "Harga", "Tipe Obat", "Efek Samping"
            }
        ));
        jTabelDataObat.setFillsViewportHeight(true);
        jTabelDataObat.setMinimumSize(new java.awt.Dimension(95, 100));
        jTabelDataObat.setRowHeight(25);
        jTabelDataObat.setSelectionBackground(new java.awt.Color(30, 198, 188));
        jTabelDataObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabelDataObatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTabelDataObat);

        add(jScrollPane1);
        jScrollPane1.setBounds(510, 100, 780, 156);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        add(jTextField1);
        jTextField1.setBounds(150, 100, 280, 40);

        jLabel11.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 15)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Tipe Obat");
        add(jLabel11);
        jLabel11.setBounds(40, 310, 90, 21);

        jLabel12.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 15)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Efek Samping");
        add(jLabel12);
        jLabel12.setBounds(40, 360, 110, 21);

        jLabel16.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 15)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Harga");
        add(jLabel16);
        jLabel16.setBounds(40, 260, 60, 21);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Pilih Usia]" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        add(jComboBox1);
        jComboBox1.setBounds(150, 200, 280, 40);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Pilih Harga]" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        add(jComboBox2);
        jComboBox2.setBounds(150, 250, 280, 40);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Pilih Tipe Obat]" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        add(jComboBox3);
        jComboBox3.setBounds(150, 300, 280, 40);

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Pilih Efek Samping]" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        add(jComboBox4);
        jComboBox4.setBounds(150, 350, 280, 40);

        jLabel7.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 30)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tabel Data Nilai Obat");
        add(jLabel7);
        jLabel7.setBounds(510, 290, 287, 43);

        jScrollPane2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane2.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 18)); // NOI18N

        jTabelNilaiObat.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jTabelNilaiObat.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jTabelNilaiObat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "No.", "Nama", "Merek", "Usia", "Harga", "Tipe Obat", "Efek Samping"
            }
        ));
        jTabelNilaiObat.setFillsViewportHeight(true);
        jTabelNilaiObat.setMinimumSize(new java.awt.Dimension(95, 100));
        jTabelNilaiObat.setRowHeight(25);
        jTabelNilaiObat.setSelectionBackground(new java.awt.Color(30, 198, 188));
        jTabelNilaiObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabelNilaiObatMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTabelNilaiObat);

        add(jScrollPane2);
        jScrollPane2.setBounds(510, 360, 780, 156);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        try {
            cekKosong();
        } catch(exceptionData ex) {
            JOptionPane.showMessageDialog(null, ex.showMessageError());
        }

        simpan();
        tabelmodeldataobat();
        tabelmodelnilaiobat();
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        edit();
        clear();
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        int selectedRow = jTabelDataObat.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Pilih data yang ingin dihapus.");
            return;
        }
        // Ambil ID dari baris yang dipilih di tabel
        String alternatifId = jTabelDataObat.getValueAt(jTabelDataObat.getSelectedRow(), 0).toString();
        
        int ok = JOptionPane.showConfirmDialog(null, "Yakin Dihapus ?","Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        
        if (ok == 0) {
            try {

                // Hapus dari tbl_nilai_alternatif terlebih dahulu
                String sqlDeleteNilai = "DELETE FROM tbl_nilai_alternatif WHERE alternatif_id = ?";
                PreparedStatement pstDeleteNilai = conne.prepareStatement(sqlDeleteNilai);
                pstDeleteNilai.setString(1, alternatifId);
                pstDeleteNilai.executeUpdate();

                // Hapus dari tbl_alternatif
                String sqlDeleteAlternatif = "DELETE FROM tbl_alternatif WHERE id = ?";
                PreparedStatement pstDeleteAlternatif = conne.prepareStatement(sqlDeleteAlternatif);
                pstDeleteAlternatif.setString(1, alternatifId);
                pstDeleteAlternatif.executeUpdate();

                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                tabelmodeldataobat();                
                tabelmodelnilaiobat(); // Perbarui tabel
                clear();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data gagal dihapus");
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void jTabelDataObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelDataObatMouseClicked
        try {
            
 
            String id = jTabelDataObat.getValueAt(jTabelDataObat.getSelectedRow(), 0).toString();
            
            // Set value untuk JTextField
            String nama = jTabelDataObat.getValueAt(jTabelDataObat.getSelectedRow(), 2).toString();            
            String merek = jTabelDataObat.getValueAt(jTabelDataObat.getSelectedRow(), 3).toString();

            jTextField1.setText(nama);            
            jTextField2.setText(merek);


            // Ambil nilai dari kolom yang sesuai di tabel
            String usia = jTabelDataObat.getValueAt(jTabelDataObat.getSelectedRow(), 4).toString();
            String harga = jTabelDataObat.getValueAt(jTabelDataObat.getSelectedRow(), 5).toString();
            String tipeobat = jTabelDataObat.getValueAt(jTabelDataObat.getSelectedRow(), 6).toString();
            String efeksamping = jTabelDataObat.getValueAt(jTabelDataObat.getSelectedRow(), 7).toString();

            // Pilih item yang sesuai di masing-masing JComboBox
            selectComboBoxItem(jComboBox1, usia);
            selectComboBoxItem(jComboBox2, harga);
            selectComboBoxItem(jComboBox3, tipeobat);
            selectComboBoxItem(jComboBox4, efeksamping);
            
            // Set status tombol
            btn_simpan.setEnabled(false);
            btn_update.setEnabled(true);
            btn_hapus.setEnabled(true);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while processing table data.");
        }
    }//GEN-LAST:event_jTabelDataObatMouseClicked
    
    private void selectComboBoxItem(JComboBox<String> comboBox, String value) {
        comboBox.setEnabled(true);
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            String item = comboBox.getItemAt(i).toString();
            String[] parts = item.split(" - ");
            if (parts.length > 1) {
                String itemName = parts[1]; // Mengambil nama kriteria
                if (itemName.equals(value)) {
                    comboBox.setSelectedIndex(i);
                    break;
                }
            }
        }
    }
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jTabelNilaiObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelNilaiObatMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabelNilaiObatMouseClicked
    
//Cara Get Nama Penduduk

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTabelDataObat;
    private javax.swing.JTable jTabelNilaiObat;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
