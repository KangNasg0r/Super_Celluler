/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import form_report.report_pelanggan;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import koneksi.koneksi;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Ahmad Nur Latif P
 */
public class master_pelanggan extends javax.swing.JFrame {

    private Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;

    /**
     * Creates new form master_pelanggan
     */
    public master_pelanggan() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        kosong();
        aktif();
        datatable_pelanggan();
        almt_pelanggan.setLineWrap(true);
        almt_pelanggan.setWrapStyleWord(true);
        autonumber();
    }
    
    protected void autonumber() {
        try {
            String sql = "SELECT id_pelanggan from tb_pelanggan order by id_pelanggan asc";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            id_pelanggan.setText("P001");
            while (rs.next()) {
                String idpelanggan = rs.getString("id_pelanggan").substring(2);
                int AN = Integer.parseInt(idpelanggan) + 1;
                String Nol = "";
                if (AN < 10) {
                    Nol = "00";
                } else if (AN < 100) {
                    Nol = "0";
                }
                id_pelanggan.setText("P" + Nol + AN);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nomor Otomatis Gagal" + e);
        }
    }
    
    public javax.swing.JPanel getMainPanel() {
        return Panel_Pelanggan;
    }

    protected void aktif() {
        nama_pelanggan.requestFocus();
        id_pelanggan.setEditable(false);
    }

    protected void kosong() {
        id_pelanggan.setText("");
        nama_pelanggan.setText("");
        hp_pelanggan.setText("");
        almt_pelanggan.setText("");
        jk_pelanggan.setSelectedIndex(0);
        tanggal_pelanggan.setDate(null);
    }

    protected void datatable_pelanggan() {
        Object[] Baris = {"ID Pelanggan", "Nama Pelanggan", "Nomor Hp", "Jenis Kelamin", "Alamat", "Tanggal Daftar"};
        tabmode = new DefaultTableModel(null, Baris);
        String cariitem = cari_pelanggan.getText();
        Date tanggalCari = dateChooser.getDate();
        try {
            String sql;
            SimpleDateFormat sdfDatabase = new SimpleDateFormat("yyyy-MM-dd");

            if (tanggalCari != null) {
                // Jika tanggal dipilih, cari berdasarkan teks dan tanggal
                String tanggalDatabase = sdfDatabase.format(tanggalCari);
                sql = "SELECT * FROM tb_pelanggan WHERE (id_pelanggan LIKE '%" + cariitem + "%' "
                        + "OR nama_pelanggan LIKE '%" + cariitem + "%' "
                        + "OR hp_pelanggan LIKE '%" + cariitem + "%' "
                        + "OR jk_pelanggan LIKE '%" + cariitem + "%' "
                        + "OR almt_pelanggan LIKE '%" + cariitem + "%') "
                        + "AND tanggal_pelanggan = '" + tanggalDatabase + "' "
                        + "ORDER BY id_pelanggan ASC";
            } else {
                // Jika tanggal tidak dipilih, cari berdasarkan teks saja
                sql = "SELECT * FROM tb_pelanggan WHERE id_pelanggan LIKE '%" + cariitem + "%' "
                        + "OR nama_pelanggan LIKE '%" + cariitem + "%' "
                        + "OR hp_pelanggan LIKE '%" + cariitem + "%' "
                        + "OR jk_pelanggan LIKE '%" + cariitem + "%' "
                        + "OR almt_pelanggan LIKE '%" + cariitem + "%' "
                        + "OR tanggal_pelanggan LIKE '%" + cariitem + "%' "
                        + "ORDER BY id_pelanggan ASC";
            }
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tabmode.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getDate(6)
                });
            }
            tblpelanggan.setModel(tabmode);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal dipanggil" + e);
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

        Panel_Pelanggan = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        id_pelanggan = new javax.swing.JTextField();
        nama_pelanggan = new javax.swing.JTextField();
        hp_pelanggan = new javax.swing.JTextField();
        jk_pelanggan = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        almt_pelanggan = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tanggal_pelanggan = new org.jdesktop.swingx.JXDatePicker();
        jPanel1 = new javax.swing.JPanel();
        bbatal = new javax.swing.JButton();
        bhapus_pel = new javax.swing.JButton();
        bubah_pel = new javax.swing.JButton();
        bsimpan_pel = new javax.swing.JButton();
        jpanel_kiri = new javax.swing.JPanel();
        jpanel_kanan = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        cari_pelanggan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dateChooser = new org.jdesktop.swingx.JXDatePicker();
        bcari = new javax.swing.JButton();
        bprint_pel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblpelanggan = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Panel_Pelanggan.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DATA PELANGGAN");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        id_pelanggan.setBackground(java.awt.SystemColor.controlHighlight);
        id_pelanggan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        id_pelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_pelangganActionPerformed(evt);
            }
        });

        nama_pelanggan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        hp_pelanggan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jk_pelanggan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jk_pelanggan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Jenis Kelamin", "Laki-Laki", "Perempuan" }));
        jk_pelanggan.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jk_pelangganComponentShown(evt);
            }
        });

        almt_pelanggan.setColumns(20);
        almt_pelanggan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        almt_pelanggan.setRows(5);
        jScrollPane2.setViewportView(almt_pelanggan);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("ID Pelanggan :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Nama  :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Nomor Handphone :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Jenis Kelamin :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Alamat :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Tanggal Daftar :");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        bbatal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/cancel.png"))); // NOI18N
        bbatal.setText("BATAL");
        bbatal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbatalActionPerformed(evt);
            }
        });

        bhapus_pel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bhapus_pel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/delete.png"))); // NOI18N
        bhapus_pel.setText("HAPUS");
        bhapus_pel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bhapus_pel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapus_pelActionPerformed(evt);
            }
        });

        bubah_pel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bubah_pel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/edit.png"))); // NOI18N
        bubah_pel.setText("UBAH");
        bubah_pel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bubah_pel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bubah_pelActionPerformed(evt);
            }
        });

        bsimpan_pel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bsimpan_pel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/save.png"))); // NOI18N
        bsimpan_pel.setText("SIMPAN");
        bsimpan_pel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bsimpan_pel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpan_pelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bsimpan_pel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bubah_pel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bhapus_pel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bbatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bsimpan_pel)
                .addGap(18, 18, 18)
                .addComponent(bubah_pel)
                .addGap(18, 18, 18)
                .addComponent(bhapus_pel)
                .addGap(18, 18, 18)
                .addComponent(bbatal)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                    .addComponent(jk_pelanggan, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hp_pelanggan, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nama_pelanggan, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(id_pelanggan, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tanggal_pelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                        .addGap(87, 87, 87))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(id_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nama_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hp_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tanggal_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jk_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jpanel_kiri.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpanel_kiriLayout = new javax.swing.GroupLayout(jpanel_kiri);
        jpanel_kiri.setLayout(jpanel_kiriLayout);
        jpanel_kiriLayout.setHorizontalGroup(
            jpanel_kiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 65, Short.MAX_VALUE)
        );
        jpanel_kiriLayout.setVerticalGroup(
            jpanel_kiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jpanel_kanan.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpanel_kananLayout = new javax.swing.GroupLayout(jpanel_kanan);
        jpanel_kanan.setLayout(jpanel_kananLayout);
        jpanel_kananLayout.setHorizontalGroup(
            jpanel_kananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 64, Short.MAX_VALUE)
        );
        jpanel_kananLayout.setVerticalGroup(
            jpanel_kananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        cari_pelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cari_pelangganActionPerformed(evt);
            }
        });
        cari_pelanggan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cari_pelangganKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cari_pelangganKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Cari :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Cari berdasarkan tanggal :");

        bcari.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bcari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/search.png"))); // NOI18N
        bcari.setText("CARI");
        bcari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcariActionPerformed(evt);
            }
        });

        bprint_pel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bprint_pel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/printer.png"))); // NOI18N
        bprint_pel.setText("CETAK");
        bprint_pel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bprint_pel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bprint_pelActionPerformed(evt);
            }
        });

        tblpelanggan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblpelanggan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblpelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblpelanggan.setGridColor(new java.awt.Color(255, 255, 255));
        tblpelanggan.setRowHeight(25);
        tblpelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblpelangganMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblpelanggan);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cari_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bcari)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bprint_pel)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cari_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bcari)
                    .addComponent(bprint_pel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout Panel_PelangganLayout = new javax.swing.GroupLayout(Panel_Pelanggan);
        Panel_Pelanggan.setLayout(Panel_PelangganLayout);
        Panel_PelangganLayout.setHorizontalGroup(
            Panel_PelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Panel_PelangganLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_PelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(Panel_PelangganLayout.createSequentialGroup()
                        .addComponent(jpanel_kiri, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpanel_kanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        Panel_PelangganLayout.setVerticalGroup(
            Panel_PelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_PelangganLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_PelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpanel_kiri, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpanel_kanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Pelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Pelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblpelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblpelangganMouseClicked
        int bar = tblpelanggan.getSelectedRow();
        String a = tblpelanggan.getValueAt(bar, 0).toString();
        String b = tblpelanggan.getValueAt(bar, 1).toString();
        String c = tblpelanggan.getValueAt(bar, 2).toString();
        String d = tblpelanggan.getValueAt(bar, 3).toString();
        String e = tblpelanggan.getValueAt(bar, 4).toString();
        Date f = (Date) tblpelanggan.getValueAt(bar, 5);

        id_pelanggan.setText(a);
        nama_pelanggan.setText(b);
        hp_pelanggan.setText(c);
        jk_pelanggan.setSelectedItem(d);
        almt_pelanggan.setText(e);
        tanggal_pelanggan.setDate(f);
    }//GEN-LAST:event_tblpelangganMouseClicked

    private void cari_pelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cari_pelangganActionPerformed

    }//GEN-LAST:event_cari_pelangganActionPerformed

    private void cari_pelangganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cari_pelangganKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            datatable_pelanggan();
        }
    }//GEN-LAST:event_cari_pelangganKeyPressed

    private void bcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcariActionPerformed
        datatable_pelanggan();
    }//GEN-LAST:event_bcariActionPerformed

    private void bprint_pelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bprint_pelActionPerformed
        try {
            String loginId = UserID.getIdKasir();
            String loginKasir = "Tidak Diketahui";

            try (PreparedStatement teknama = conn.prepareStatement("SELECT nama FROM tb_kasir WHERE id_kasir = ?")) {
                teknama.setString(1, loginId);
                try (ResultSet rsNama = teknama.executeQuery()) {
                    if (rsNama.next()) {
                        loginKasir = rsNama.getString("nama");
                    }
                }
            }

            String reportPath = "./src/report/rep_pelanggan.jasper";
            HashMap parameter = new HashMap();
            parameter.put("KASIR", loginKasir);

            JasperPrint print = JasperFillManager.fillReport(reportPath,parameter,conn);
            
            form.menu_utama menuUtama = form.menu_utama.getInstance();
        if (menuUtama != null) {
            javax.swing.JPanel reportPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
            net.sf.jasperreports.swing.JRViewer viewer = new net.sf.jasperreports.swing.JRViewer(print);
            reportPanel.add(viewer, java.awt.BorderLayout.CENTER);
            // Load ke Pane1 di menu_utama
            menuUtama.loadPanel(reportPanel);
        } else {
            JasperViewer.viewReport(print, false);
        }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal mencetak report: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_bprint_pelActionPerformed

    private void id_pelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_pelangganActionPerformed

    }//GEN-LAST:event_id_pelangganActionPerformed

    private void jk_pelangganComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jk_pelangganComponentShown

    }//GEN-LAST:event_jk_pelangganComponentShown

    private boolean id_pelTerdaftar(String id_pelanggan) {
        String sql = "SELECT id_pelanggan FROM tb_pelanggan WHERE id_pelanggan = ?";
        try (PreparedStatement stat = conn.prepareStatement(sql)) {
            stat.setString(1, id_pelanggan);
            ResultSet hasil = stat.executeQuery();
            return hasil.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal memeriksa ID Pelanggan: " + e.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    private void bsimpan_pelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpan_pelActionPerformed
        String id_pelangganText = id_pelanggan.getText().trim(); // Trim untuk menghapus spasi di awal dan akhir
        String namaText = nama_pelanggan.getText().trim();
        String hpText = hp_pelanggan.getText().trim();
        String alamatText = almt_pelanggan.getText().trim();
        String jenisKelamin = jk_pelanggan.getSelectedItem().toString();
        if (id_pelangganText.isEmpty() || namaText.isEmpty() || hpText.isEmpty() || alamatText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua kolom harus diisi.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (id_pelangganText.length() > 15) {
            JOptionPane.showMessageDialog(this, "ID Pelanggan harus terdiri dari 15 digit.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (id_pelTerdaftar(id_pelangganText)) {
            JOptionPane.showMessageDialog(this, "ID Pelanggan sudah terdaftar.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (jenisKelamin.equals("Pilih Jenis Kelamin")) {
            JOptionPane.showMessageDialog(this, "Jenis Kelamin harus dipilih.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (tanggal_pelanggan.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Tanggal harus diisi.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return; // Hentikan penyimpanan jika tanggal kosong
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // TAMBAHAN: Format tanggal untuk database
        String tanggal_daftar = sdf.format(tanggal_pelanggan.getDate());
        String sql = "insert into tb_pelanggan values(?,?,?,?,?,?)";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, id_pelanggan.getText());
            stat.setString(2, nama_pelanggan.getText());
            stat.setString(3, hp_pelanggan.getText());
            stat.setString(4, jenisKelamin);
            stat.setString(5, almt_pelanggan.getText());
            stat.setString(6, tanggal_daftar);

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            kosong();
            aktif();
            autonumber();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal disimpan!" + e);
        }
        datatable_pelanggan();
    }//GEN-LAST:event_bsimpan_pelActionPerformed

    private void bubah_pelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bubah_pelActionPerformed
        String id_pelangganText = id_pelanggan.getText().trim(); // Trim untuk menghapus spasi di awal dan akhir
        String namaText = nama_pelanggan.getText().trim();
        String hpText = hp_pelanggan.getText().trim();
        String alamatText = almt_pelanggan.getText().trim();
        String jenisKelamin = jk_pelanggan.getSelectedItem().toString();
        if (id_pelangganText.isEmpty() || namaText.isEmpty() || hpText.isEmpty() || alamatText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua kolom harus diisi.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (id_pelangganText.length() > 15) {
            JOptionPane.showMessageDialog(this, "ID Pelanggan harus terdiri dari 15 digit.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (jenisKelamin.equals("Pilih Jenis Kelamin")) {
            JOptionPane.showMessageDialog(this, "Jenis Kelamin harus dipilih.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (tanggal_pelanggan.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Tanggal harus diisi.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return; // Hentikan penyimpanan jika tanggal kosong
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // TAMBAHAN: Format tanggal untuk database
        String tanggal_daftar = sdf.format(tanggal_pelanggan.getDate());
        String sql = "UPDATE tb_pelanggan SET nama_pelanggan =?, hp_pelanggan=?, jk_pelanggan=?, almt_pelanggan=?, tanggal_pelanggan=? WHERE id_pelanggan=?"; // Perhatikan penggunaan UPDATE
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, namaText);
            stat.setString(2, hpText);
            stat.setString(3, jenisKelamin);
            stat.setString(4, alamatText);
            stat.setString(5, tanggal_daftar);
            stat.setString(6, id_pelangganText); // WHERE clause
            int rowsUpdated = stat.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
                kosong();
                aktif();
                autonumber();
            } else {
                JOptionPane.showMessageDialog(null, "Data gagal diubah. ID Pelanggan tidak ditemukan.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal diubah!" + e, "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
        datatable_pelanggan();
    }//GEN-LAST:event_bubah_pelActionPerformed

    private void bhapus_pelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapus_pelActionPerformed
        String idPelanggan = id_pelanggan.getText().trim();
        if (idPelanggan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID Pelanggan belum diisi.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!id_pelTerdaftar(idPelanggan)) {
            JOptionPane.showMessageDialog(this, "ID Pelanggan tidak ditemukan.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int ok = JOptionPane.showConfirmDialog(null, "Apakah benar ingin dihapus?", "Peringatan!", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            String sql = "delete from tb_pelanggan where id_pelanggan='" + id_pelanggan.getText() + "'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                kosong();
                aktif();
                autonumber();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data gagal dihapus" + e);
            }
            datatable_pelanggan();
        }
    }//GEN-LAST:event_bhapus_pelActionPerformed

    private void bbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbatalActionPerformed
        kosong();
        datatable_pelanggan();
        autonumber();
    }//GEN-LAST:event_bbatalActionPerformed

    private void cari_pelangganKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cari_pelangganKeyTyped
        datatable_pelanggan();
    }//GEN-LAST:event_cari_pelangganKeyTyped

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
            java.util.logging.Logger.getLogger(master_pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(master_pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(master_pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(master_pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new master_pelanggan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_Pelanggan;
    private javax.swing.JTextArea almt_pelanggan;
    private javax.swing.JButton bbatal;
    private javax.swing.JButton bcari;
    private javax.swing.JButton bhapus_pel;
    private javax.swing.JButton bprint_pel;
    private javax.swing.JButton bsimpan_pel;
    private javax.swing.JButton bubah_pel;
    private javax.swing.JTextField cari_pelanggan;
    private org.jdesktop.swingx.JXDatePicker dateChooser;
    private javax.swing.JTextField hp_pelanggan;
    private javax.swing.JTextField id_pelanggan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jk_pelanggan;
    private javax.swing.JPanel jpanel_kanan;
    private javax.swing.JPanel jpanel_kiri;
    private javax.swing.JTextField nama_pelanggan;
    private org.jdesktop.swingx.JXDatePicker tanggal_pelanggan;
    private javax.swing.JTable tblpelanggan;
    // End of variables declaration//GEN-END:variables
}
