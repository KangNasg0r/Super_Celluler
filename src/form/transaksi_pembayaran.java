package form;

import form_popup.poppup_sparepart;
import form_popup.poppup_aksesoris;
import form_popup.poppup_service;
import form_popup.poppup_pelanggan;
import form_popup.poppup_teknisi;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.event.FocusAdapter; 
import java.awt.event.FocusEvent;   
import java.awt.Color;               
import javax.swing.JTextField;   

/**
 *
 * @author Ahmad Nur Latif P
 */
public class transaksi_pembayaran extends javax.swing.JFrame {

    public String id, nama, telp, jenis, almt, tgl;
    public String idTek, namaTek, telpTek, jenisTek, almtTek;
    public String id_ser, jenis_ser, biaya_ser;
    public String kd_bar, nama_bar, harga_bel_bar, harga_ju_bar, jenis_bar, merk_bar;
    public String id_aks, nama_aks, harga_bel_aks, harga_ju_aks;
    private Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;
    
    private static final String PLACEHOLDER_QTY = "Masukkan jumlah";

    /**
     * Creates new form transaksi_pembayaran
     */
    public transaksi_pembayaran() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        aktif();
        kosong();
        tampilkanIdKasir();
        bcari_sparepart.setEnabled(false);
        bcari_sparepart.setToolTipText("Pilih jenis service terlebih dahulu");
        String kasir = UserID.getNamaKasir();
        label_namaKasir.setText(kasir);
        almt_pelanggan.setLineWrap(true);
        almt_pelanggan.setWrapStyleWord(true);
        almt_teknisi.setLineWrap(true);
        almt_teknisi.setWrapStyleWord(true);
        autonumber();
        
        addPlaceholder(qtySparepart, PLACEHOLDER_QTY);
        addPlaceholder(qtyAksesoris, PLACEHOLDER_QTY);
    }
    
    public javax.swing.JPanel getMainPanel() {
        return nota;
    }
    
    private void addPlaceholder(JTextField textField, String placeholderText) {
        textField.setText(placeholderText);
        textField.setForeground(Color.BLACK);

        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholderText)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholderText);
                    textField.setForeground(Color.BLACK);
                }
            }
        });
    }

    protected void aktif() {
        //jtgl.requestFocus();
        jtgl.setFormats(new SimpleDateFormat("yyyy/MM/dd"));
        jtgl.setDate(new Date());
        jtgl.setEditable(false);
        Object[] Baris = {"ID Barang", "Nama Barang", "Harga Beli", "Harga Jual", "Kuantitas", "Total"};
        tabmode = new DefaultTableModel(null, Baris);
        tbl_transaksi.setModel(tabmode);
        
        id_nota.setEditable(false);
        id_pelanggan.setEditable(false);
        nama_pelanggan.setEditable(false);
        hp_pelanggan.setEditable(false);
        almt_pelanggan.setEditable(false);
        id_teknisi.setEditable(false);
        nama_teknisi.setEditable(false);
        hp_teknisi.setEditable(false);
        almt_teknisi.setEditable(false);
        id_service.setEditable(false);
        jenis_service.setEditable(false);
        biaya_service.setEditable(false);
        kd_barang.setEditable(false);
        nama_barang.setEditable(false);
        harga_beli.setEditable(false);
        harga_jual.setEditable(false);
        merk_barang.setEditable(false);
        subTotalSparepart.setEditable(false);
        id_aksesoris.setEditable(false);
        nama_aksesoris.setEditable(false);
        harga_beliAk.setEditable(false);
        harga_jualAk.setEditable(false);
        subTotalAksesoris.setEditable(false);
        total_biaya.setEditable(false);
        bcari_service.setEnabled(true);
        bcari_sparepart.setEnabled(false);
        bcari_sparepart.setToolTipText("Pilih jenis service terlebih dahulu");
    }
    
    protected void kosong() {
        id_pelanggan.setText("");
        nama_pelanggan.setText("");
        hp_pelanggan.setText("");
        almt_pelanggan.setText("");
        id_teknisi.setText("");
        nama_teknisi.setText("");
        hp_teknisi.setText("");
        almt_teknisi.setText("");
        id_service.setText("");
        jenis_service.setText("");
        biaya_service.setText("");
        kd_barang.setText("");
        nama_barang.setText("");
        harga_beli.setText("");
        harga_jual.setText("");
        merk_barang.setText("");
        
        qtySparepart.setText(PLACEHOLDER_QTY);
        
        subTotalSparepart.setText("");
        id_aksesoris.setText("");
        nama_aksesoris.setText("");
        harga_beliAk.setText("");
        harga_jualAk.setText("");
        
        qtyAksesoris.setText(PLACEHOLDER_QTY);
        
        subTotalAksesoris.setText("");
        total_biaya.setText("");

    }
    
    private void tampilkanIdKasir() {
        String idKasir = UserID.getIdKasir();
        label_idKasir.setText(idKasir);
    }


    public void itemTerpilihPlgn() {
        poppup_pelanggan pp = new poppup_pelanggan();
        pp.plgn = this;
        id_pelanggan.setText(id);
        nama_pelanggan.setText(nama);
        hp_pelanggan.setText(telp);
        almt_pelanggan.setText(almt);
    }
    
    public void itemTerpilihTek() {
        poppup_teknisi pt = new poppup_teknisi();
        pt.tek = this;
        id_teknisi.setText(idTek);
        nama_teknisi.setText(namaTek);
        hp_teknisi.setText(telpTek);
        almt_teknisi.setText(almtTek);
    }

    public void itemTerpilihSrvc() {
        poppup_service ps = new poppup_service();
        ps.srvc = this;
    
        id_service.setText(id_ser);
        jenis_service.setText(jenis_ser);
        biaya_service.setText(biaya_ser);
    
        // TAMBAHKAN INI - Enable button sparepart
        bcari_sparepart.setEnabled(true);
        bcari_sparepart.setToolTipText("Cari sparepart untuk " + jenis_ser);
        
    }

    public void itemTerpilihSppt() {
        poppup_sparepart pss = new poppup_sparepart();
        pss.sppt = this;
        kd_barang.setText(kd_bar);
        nama_barang.setText(nama_bar);
        harga_beli.setText(harga_bel_bar);
        harga_jual.setText(harga_ju_bar);
        merk_barang.setText(merk_bar);
        
        qtySparepart.setText(PLACEHOLDER_QTY);
        subTotalSparepart.setText("");
        
        bcari_service.setEnabled(false);
        bcari_service.setToolTipText("Klik batal jika ingin mengganti service!");
    }

    public void itemTerpilihAkse() {
        poppup_aksesoris pa = new poppup_aksesoris();
        pa.akse = this;
        id_aksesoris.setText(id_aks);
        nama_aksesoris.setText(nama_aks);
        harga_beliAk.setText(harga_bel_aks);
        harga_jualAk.setText(harga_ju_aks);
        
        qtyAksesoris.setText(PLACEHOLDER_QTY);
        subTotalAksesoris.setText("");
    }

    protected void autonumber() {
        try {
            String sql = "SELECT id_nota from tb_nota order by id_nota asc";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            id_nota.setText("IN0001");
            while (rs.next()) {
                String idnota = rs.getString("id_nota").substring(2);
                int AN = Integer.parseInt(idnota) + 1;
                String Nol = "";
                if (AN < 10) {
                    Nol = "000";
                } else if (AN < 100) {
                    Nol = "00";
                } else if (AN < 1000) {
                    Nol = "0";
                } else if (AN < 10000) {
                    Nol = "";
                }
                id_nota.setText("IN" + Nol + AN);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nomor Otomatis Gagal" + e);
        }
    }
    
    public void hitung() {
        int total = 0;
        for (int i = 0; i < tbl_transaksi.getRowCount(); i++) {
            int amount = Integer.valueOf(tbl_transaksi.getValueAt(i, 5).toString());
            total += amount;
        }
        total_biaya.setText(Integer.toString(total));
    }
    
    public void cetak() {
        try{
            String loginId = UserID.getIdKasir();
            String loginKasir = "Tidak Diketahui";
            
            try (PreparedStatement kasnama = conn.prepareStatement("SELECT nama FROM tb_kasir WHERE id_kasir = ?")) {
                kasnama.setString(1, loginId);
                try (ResultSet rsNama = kasnama.executeQuery()) {
                    if (rsNama.next()) {
                        loginKasir = rsNama.getString("nama");
                    }
                }
            }
            
            String path="./src/report/nota1.jasper";
            HashMap parameter = new HashMap();
            parameter.put("id_nota",id_nota.getText());
            parameter.put("KASIR", loginKasir);
            
            JasperPrint print = JasperFillManager.fillReport(path,parameter,conn);
            
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
        
        }catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada" +ex);   
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

        nota = new javax.swing.JPanel();
        nota_pembayaran = new javax.swing.JLabel();
        panel_teknisi = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        label_idKasir = new javax.swing.JLabel();
        label_namaKasir = new javax.swing.JLabel();
        panel_nota = new javax.swing.JPanel();
        labelnot = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        id_nota = new javax.swing.JTextField();
        jtgl = new org.jdesktop.swingx.JXDatePicker();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_transaksi = new javax.swing.JTable();
        bhapus_transaksi = new javax.swing.JButton();
        bsimpan = new javax.swing.JButton();
        bbatal = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        total_biaya = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        panel_pelanggan = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        id_pelanggan = new javax.swing.JTextField();
        nama_pelanggan = new javax.swing.JTextField();
        hp_pelanggan = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        almt_pelanggan = new javax.swing.JTextArea();
        bcari_pelanggan = new javax.swing.JButton();
        detail_service = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        id_service = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        bcari_service = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jenis_service = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        biaya_service = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        kd_barang = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        bcari_sparepart = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        nama_barang = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        harga_beli = new javax.swing.JTextField();
        harga_jual = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        merk_barang = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        qtySparepart = new javax.swing.JTextField();
        btambah_sparepart = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        subTotalSparepart = new javax.swing.JTextField();
        bbatal_ss = new javax.swing.JButton();
        panel_pelanggan1 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        id_teknisi = new javax.swing.JTextField();
        nama_teknisi = new javax.swing.JTextField();
        hp_teknisi = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        almt_teknisi = new javax.swing.JTextArea();
        bcari_teknisi = new javax.swing.JButton();
        detail_aksesoris = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        id_aksesoris = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        bcari_aksesoris = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        nama_aksesoris = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        harga_beliAk = new javax.swing.JTextField();
        btambah_aksesoris = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        qtyAksesoris = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        subTotalAksesoris = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        harga_jualAk = new javax.swing.JTextField();
        bbatal_akse = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nota.setBackground(new java.awt.Color(0, 0, 204));

        nota_pembayaran.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        nota_pembayaran.setForeground(new java.awt.Color(255, 255, 255));
        nota_pembayaran.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nota_pembayaran.setText("NOTA PEMBAYARAN");
        nota_pembayaran.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        panel_teknisi.setBackground(new java.awt.Color(0, 0, 204));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID Kasir");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama Kasir");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText(":");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText(":");

        label_idKasir.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        label_idKasir.setForeground(new java.awt.Color(255, 255, 255));
        label_idKasir.setText("MUNCUL ID KASIR");

        label_namaKasir.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        label_namaKasir.setForeground(new java.awt.Color(255, 255, 255));
        label_namaKasir.setText("MUNCUL NAMA KASIR");

        javax.swing.GroupLayout panel_teknisiLayout = new javax.swing.GroupLayout(panel_teknisi);
        panel_teknisi.setLayout(panel_teknisiLayout);
        panel_teknisiLayout.setHorizontalGroup(
            panel_teknisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_teknisiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_teknisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_teknisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_teknisiLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_idKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_teknisiLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_namaKasir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_teknisiLayout.setVerticalGroup(
            panel_teknisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_teknisiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_teknisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_idKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_teknisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_teknisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_namaKasir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_teknisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        panel_nota.setBackground(new java.awt.Color(0, 0, 204));

        labelnot.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        labelnot.setForeground(new java.awt.Color(255, 255, 255));
        labelnot.setText("ID Nota");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tanggal");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText(":");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText(":");

        id_nota.setBackground(java.awt.SystemColor.controlHighlight);
        id_nota.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout panel_notaLayout = new javax.swing.GroupLayout(panel_nota);
        panel_nota.setLayout(panel_notaLayout);
        panel_notaLayout.setHorizontalGroup(
            panel_notaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_notaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_notaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelnot, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_notaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_notaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(id_nota, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtgl, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_notaLayout.setVerticalGroup(
            panel_notaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_notaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_notaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_notaLayout.createSequentialGroup()
                        .addGroup(panel_notaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelnot, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addGroup(panel_notaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(15, Short.MAX_VALUE))
                    .addGroup(panel_notaLayout.createSequentialGroup()
                        .addComponent(id_nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "Transaksi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        tbl_transaksi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tbl_transaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id_item", "nama_item", "harga_beli", "harga_jual", "kuantitas", "subtotal"
            }
        ));
        jScrollPane2.setViewportView(tbl_transaksi);

        bhapus_transaksi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bhapus_transaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/delete.png"))); // NOI18N
        bhapus_transaksi.setText("HAPUS");
        bhapus_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapus_transaksiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bhapus_transaksi))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bhapus_transaksi)
                .addContainerGap())
        );

        bsimpan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/save.png"))); // NOI18N
        bsimpan.setText("SIMPAN");
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
            }
        });

        bbatal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/cancel.png"))); // NOI18N
        bbatal.setText("BATAL");
        bbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbatalActionPerformed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Total");

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText(":");

        jPanel4.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(0, 0, 204));
        jPanel5.setAutoscrolls(true);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(0, 0, 204));

        panel_pelanggan.setBackground(new java.awt.Color(0, 0, 204));
        panel_pelanggan.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "PELANGGAN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("ID Pelanggan");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Nama");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Alamat");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Nomor Hp");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText(":");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText(":");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText(":");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText(":");

        id_pelanggan.setBackground(java.awt.SystemColor.controlHighlight);
        id_pelanggan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        nama_pelanggan.setBackground(java.awt.SystemColor.controlHighlight);
        nama_pelanggan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        hp_pelanggan.setBackground(java.awt.SystemColor.controlHighlight);
        hp_pelanggan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        almt_pelanggan.setBackground(java.awt.SystemColor.controlHighlight);
        almt_pelanggan.setColumns(20);
        almt_pelanggan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        almt_pelanggan.setRows(5);
        jScrollPane1.setViewportView(almt_pelanggan);

        bcari_pelanggan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bcari_pelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/search.png"))); // NOI18N
        bcari_pelanggan.setText("CARI");
        bcari_pelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcari_pelangganActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_pelangganLayout = new javax.swing.GroupLayout(panel_pelanggan);
        panel_pelanggan.setLayout(panel_pelangganLayout);
        panel_pelangganLayout.setHorizontalGroup(
            panel_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_pelangganLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_pelangganLayout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(panel_pelangganLayout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(id_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bcari_pelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel_pelangganLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nama_pelanggan))
                    .addGroup(panel_pelangganLayout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hp_pelanggan)))
                .addContainerGap())
        );
        panel_pelangganLayout.setVerticalGroup(
            panel_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_pelangganLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel14)
                    .addComponent(id_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bcari_pelanggan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel15)
                    .addComponent(nama_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel16)
                    .addComponent(hp_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_pelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(jLabel17))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        detail_service.setBackground(new java.awt.Color(0, 0, 204));
        detail_service.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "SERVIS & SPAREPART", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        detail_service.setForeground(new java.awt.Color(255, 255, 255));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("ID Service");

        id_service.setBackground(java.awt.SystemColor.controlHighlight);
        id_service.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText(":");

        bcari_service.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bcari_service.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/search.png"))); // NOI18N
        bcari_service.setText("CARI");
        bcari_service.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcari_serviceActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Jenis Service");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText(":");

        jenis_service.setBackground(java.awt.SystemColor.controlHighlight);
        jenis_service.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Harga");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText(":");

        biaya_service.setBackground(java.awt.SystemColor.controlHighlight);
        biaya_service.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Kode Barang");

        kd_barang.setBackground(java.awt.SystemColor.controlHighlight);
        kd_barang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText(":");

        bcari_sparepart.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bcari_sparepart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/search.png"))); // NOI18N
        bcari_sparepart.setText("CARI");
        bcari_sparepart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcari_sparepartActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Nama Barang");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText(":");

        nama_barang.setBackground(java.awt.SystemColor.controlHighlight);
        nama_barang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Harga Beli");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText(":");

        harga_beli.setBackground(java.awt.SystemColor.controlHighlight);
        harga_beli.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        harga_jual.setBackground(java.awt.SystemColor.controlHighlight);
        harga_jual.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText(":");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Harga Jual");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Merk Barang");

        merk_barang.setBackground(java.awt.SystemColor.controlHighlight);
        merk_barang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText(":");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Kuantitas");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText(":");

        qtySparepart.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        qtySparepart.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                qtySparepartKeyReleased(evt);
            }
        });

        btambah_sparepart.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btambah_sparepart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/create.png"))); // NOI18N
        btambah_sparepart.setText("TAMBAH");
        btambah_sparepart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btambah_sparepartActionPerformed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Sparepart Yang Digunakan");
        jLabel46.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, new java.awt.Color(255, 255, 255)));

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("Sub Total");

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText(":");

        subTotalSparepart.setBackground(java.awt.SystemColor.controlHighlight);
        subTotalSparepart.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        bbatal_ss.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bbatal_ss.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/cancel.png"))); // NOI18N
        bbatal_ss.setText("BATAL");
        bbatal_ss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbatal_ssActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout detail_serviceLayout = new javax.swing.GroupLayout(detail_service);
        detail_service.setLayout(detail_serviceLayout);
        detail_serviceLayout.setHorizontalGroup(
            detail_serviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detail_serviceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(detail_serviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detail_serviceLayout.createSequentialGroup()
                        .addGroup(detail_serviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(detail_serviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(detail_serviceLayout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(id_service)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bcari_service, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(detail_serviceLayout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(biaya_service))
                            .addGroup(detail_serviceLayout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jenis_service))))
                    .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detail_serviceLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(detail_serviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(detail_serviceLayout.createSequentialGroup()
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nama_barang))
                            .addGroup(detail_serviceLayout.createSequentialGroup()
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(kd_barang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bcari_sparepart, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(detail_serviceLayout.createSequentialGroup()
                                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(detail_serviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(subTotalSparepart)
                                    .addGroup(detail_serviceLayout.createSequentialGroup()
                                        .addComponent(btambah_sparepart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bbatal_ss, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(detail_serviceLayout.createSequentialGroup()
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(harga_beli))
                            .addGroup(detail_serviceLayout.createSequentialGroup()
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(harga_jual))
                            .addGroup(detail_serviceLayout.createSequentialGroup()
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(merk_barang))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detail_serviceLayout.createSequentialGroup()
                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(qtySparepart)))))
                .addContainerGap())
        );
        detail_serviceLayout.setVerticalGroup(
            detail_serviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detail_serviceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(detail_serviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(id_service, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(bcari_service))
                .addGap(18, 18, 18)
                .addGroup(detail_serviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jenis_service, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(detail_serviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(biaya_service, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(detail_serviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33)
                    .addComponent(kd_barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bcari_sparepart))
                .addGap(18, 18, 18)
                .addGroup(detail_serviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel35)
                    .addComponent(nama_barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(detail_serviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37)
                    .addComponent(harga_beli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(detail_serviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jLabel39)
                    .addComponent(harga_jual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(detail_serviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41)
                    .addComponent(merk_barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(detail_serviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jLabel43)
                    .addComponent(qtySparepart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(detail_serviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(jLabel52)
                    .addComponent(subTotalSparepart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(detail_serviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btambah_sparepart)
                    .addComponent(bbatal_ss))
                .addContainerGap())
        );

        panel_pelanggan1.setBackground(new java.awt.Color(0, 0, 204));
        panel_pelanggan1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "TEKNISI", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("ID Teknisi");

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("Nama");

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("Alamat");

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("Nomor Hp");

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText(":");

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText(":");

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setText(":");

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText(":");

        id_teknisi.setBackground(java.awt.SystemColor.controlHighlight);
        id_teknisi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        nama_teknisi.setBackground(java.awt.SystemColor.controlHighlight);
        nama_teknisi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        hp_teknisi.setBackground(java.awt.SystemColor.controlHighlight);
        hp_teknisi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        almt_teknisi.setBackground(java.awt.SystemColor.controlHighlight);
        almt_teknisi.setColumns(20);
        almt_teknisi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        almt_teknisi.setRows(5);
        jScrollPane3.setViewportView(almt_teknisi);

        bcari_teknisi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bcari_teknisi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/search.png"))); // NOI18N
        bcari_teknisi.setText("CARI");
        bcari_teknisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcari_teknisiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_pelanggan1Layout = new javax.swing.GroupLayout(panel_pelanggan1);
        panel_pelanggan1.setLayout(panel_pelanggan1Layout);
        panel_pelanggan1Layout.setHorizontalGroup(
            panel_pelanggan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_pelanggan1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_pelanggan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_pelanggan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_pelanggan1Layout.createSequentialGroup()
                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3))
                    .addGroup(panel_pelanggan1Layout.createSequentialGroup()
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(id_teknisi, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bcari_teknisi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel_pelanggan1Layout.createSequentialGroup()
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nama_teknisi))
                    .addGroup(panel_pelanggan1Layout.createSequentialGroup()
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hp_teknisi)))
                .addContainerGap())
        );
        panel_pelanggan1Layout.setVerticalGroup(
            panel_pelanggan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_pelanggan1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_pelanggan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(jLabel57)
                    .addComponent(id_teknisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bcari_teknisi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_pelanggan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(jLabel58)
                    .addComponent(nama_teknisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_pelanggan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(jLabel59)
                    .addComponent(hp_teknisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_pelanggan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_pelanggan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel55)
                        .addComponent(jLabel60))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        detail_aksesoris.setBackground(new java.awt.Color(0, 0, 204));
        detail_aksesoris.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "AKSESORIS (Opsional)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("ID Aksesoris");

        id_aksesoris.setBackground(java.awt.SystemColor.controlHighlight);
        id_aksesoris.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText(":");

        bcari_aksesoris.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bcari_aksesoris.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/search.png"))); // NOI18N
        bcari_aksesoris.setText("CARI");
        bcari_aksesoris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcari_aksesorisActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Nama");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText(":");

        nama_aksesoris.setBackground(java.awt.SystemColor.controlHighlight);
        nama_aksesoris.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Harga Beli");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText(":");

        harga_beliAk.setBackground(java.awt.SystemColor.controlHighlight);
        harga_beliAk.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        btambah_aksesoris.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btambah_aksesoris.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/create.png"))); // NOI18N
        btambah_aksesoris.setText("TAMBAH");
        btambah_aksesoris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btambah_aksesorisActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Kuantitas");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText(":");

        qtyAksesoris.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        qtyAksesoris.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                qtyAksesorisKeyReleased(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Sub Total");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText(":");

        subTotalAksesoris.setBackground(java.awt.SystemColor.controlHighlight);
        subTotalAksesoris.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Harga Jual");

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setText(":");

        harga_jualAk.setBackground(java.awt.SystemColor.controlHighlight);
        harga_jualAk.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        bbatal_akse.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bbatal_akse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/cancel.png"))); // NOI18N
        bbatal_akse.setText("BATAL");
        bbatal_akse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbatal_akseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout detail_aksesorisLayout = new javax.swing.GroupLayout(detail_aksesoris);
        detail_aksesoris.setLayout(detail_aksesorisLayout);
        detail_aksesorisLayout.setHorizontalGroup(
            detail_aksesorisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detail_aksesorisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(detail_aksesorisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(detail_aksesorisLayout.createSequentialGroup()
                        .addGroup(detail_aksesorisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(detail_aksesorisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(detail_aksesorisLayout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nama_aksesoris))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detail_aksesorisLayout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(harga_beliAk))
                            .addGroup(detail_aksesorisLayout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(id_aksesoris, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bcari_aksesoris)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(detail_aksesorisLayout.createSequentialGroup()
                                .addGroup(detail_aksesorisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(detail_aksesorisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(subTotalAksesoris)
                                    .addComponent(qtyAksesoris)
                                    .addGroup(detail_aksesorisLayout.createSequentialGroup()
                                        .addComponent(btambah_aksesoris)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bbatal_akse, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(detail_aksesorisLayout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(harga_jualAk)))
                .addGap(7, 7, 7))
        );
        detail_aksesorisLayout.setVerticalGroup(
            detail_aksesorisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detail_aksesorisLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(detail_aksesorisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(id_aksesoris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(bcari_aksesoris))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(detail_aksesorisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(nama_aksesoris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(detail_aksesorisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(harga_beliAk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(detail_aksesorisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(jLabel50)
                    .addComponent(harga_jualAk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(detail_aksesorisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31)
                    .addComponent(qtyAksesoris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(detail_aksesorisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(jLabel48)
                    .addComponent(subTotalAksesoris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(detail_aksesorisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btambah_aksesoris)
                    .addComponent(bbatal_akse))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_pelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_pelanggan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detail_service, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detail_aksesoris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(detail_service, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(panel_pelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel_pelanggan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(detail_aksesoris, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(217, 217, 217)))
                .addContainerGap())
        );

        javax.swing.GroupLayout notaLayout = new javax.swing.GroupLayout(nota);
        nota.setLayout(notaLayout);
        notaLayout.setHorizontalGroup(
            notaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notaLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(notaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nota_pembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(notaLayout.createSequentialGroup()
                        .addComponent(panel_teknisi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel_nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(notaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(notaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(notaLayout.createSequentialGroup()
                        .addComponent(bsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(bbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(total_biaya, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        notaLayout.setVerticalGroup(
            notaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notaLayout.createSequentialGroup()
                .addGroup(notaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(notaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nota_pembayaran)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(notaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel_nota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panel_teknisi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(notaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bsimpan)
                    .addComponent(bbatal)
                    .addComponent(jLabel44)
                    .addComponent(jLabel45)
                    .addComponent(total_biaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bcari_pelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcari_pelangganActionPerformed
        form_popup.poppup_pelanggan pp = new form_popup.poppup_pelanggan();
        pp.plgn = this;
        pp.setVisible(true);
        pp.setResizable(false);
        pp.setLocationRelativeTo(null);
    }//GEN-LAST:event_bcari_pelangganActionPerformed

    private void bcari_serviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcari_serviceActionPerformed
        form_popup.poppup_service ps = new form_popup.poppup_service();
        ps.srvc = this;
        ps.setVisible(true);
        ps.setResizable(false);
        ps.setLocationRelativeTo(null);
    }//GEN-LAST:event_bcari_serviceActionPerformed

    private void bcari_sparepartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcari_sparepartActionPerformed
        String jenisService = jenis_service.getText().trim();
    
    if (jenisService.isEmpty()) {
        JOptionPane.showMessageDialog(this, 
            "Silakan pilih jenis service terlebih dahulu!", 
            "Peringatan", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Buka popup dengan filter
    form_popup.poppup_sparepart pop = new form_popup.poppup_sparepart(this, jenisService);
    pop.sppt = this; // Set reference
    pop.setVisible(true);
    pop.setLocationRelativeTo(null);    
    }//GEN-LAST:event_bcari_sparepartActionPerformed

    private void bcari_aksesorisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcari_aksesorisActionPerformed
        form_popup.poppup_aksesoris pa = new form_popup.poppup_aksesoris();
        pa.akse = this;
        pa.setVisible(true);
        pa.setResizable(false);
        pa.setLocationRelativeTo(null);
    }//GEN-LAST:event_bcari_aksesorisActionPerformed

    private void btambah_sparepartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btambah_sparepartActionPerformed
        try {
            String idservice = id_service.getText();
            String jenisservice = jenis_service.getText();
            int biayaservice = Integer.parseInt(biaya_service.getText());
            
            
            String kodeBarang = kd_barang.getText();
            String namaBarang = nama_barang.getText();
            int hargaBeli = Integer.parseInt(harga_beli.getText());
            int hargaJual = Integer.parseInt(harga_jual.getText());
            int kuantitas = Integer.parseInt(qtySparepart.getText());
            int subTotalService = biayaservice * kuantitas;
            int subTotal = Integer.parseInt(subTotalSparepart.getText());
            
            tabmode.addRow(new Object[]{idservice, jenisservice, 0, biayaservice, kuantitas, subTotalService});
            tabmode.addRow(new Object[]{kodeBarang, namaBarang, hargaBeli, hargaJual, kuantitas, subTotal});
            
            tbl_transaksi.setModel(tabmode);
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        id_service.setText("");
        jenis_service.setText("");
        biaya_service.setText("");
        kd_barang.setText("");
        nama_barang.setText("");
        harga_beli.setText("");
        harga_jual.setText("");
        merk_barang.setText("");
        qtySparepart.setText(PLACEHOLDER_QTY);
        subTotalSparepart.setText("");
        hitung();
        bcari_service.setEnabled(true);
        bcari_sparepart.setEnabled(false);
        bcari_sparepart.setToolTipText("Pilih jenis service terlebih dahulu");
    }//GEN-LAST:event_btambah_sparepartActionPerformed

    private void qtySparepartKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtySparepartKeyReleased
        try{
        int xhj = Integer.parseInt(harga_jual.getText());
        int xqty = Integer.parseInt(qtySparepart.getText());
        int xjml = xhj * xqty;
        subTotalSparepart.setText(String.valueOf(xjml));
       } catch  (NumberFormatException e) {
       subTotalSparepart.setText("0");
       }
    }//GEN-LAST:event_qtySparepartKeyReleased

    private void btambah_aksesorisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btambah_aksesorisActionPerformed
        try {
            String idAksesoris = id_aksesoris.getText();
            String namaAksesoris = nama_aksesoris.getText();
            int hargaBeliAk = Integer.parseInt(harga_beliAk.getText());
            int hargaJualAk = Integer.parseInt(harga_jualAk.getText());
            int kuantitasAk = Integer.parseInt(qtyAksesoris.getText());
            int subTotalAk = Integer.parseInt(subTotalAksesoris.getText());
            tabmode.addRow(new Object[]{idAksesoris, namaAksesoris, hargaBeliAk, hargaJualAk, kuantitasAk, subTotalAk});
            tbl_transaksi.setModel(tabmode);
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        id_aksesoris.setText("");
        nama_aksesoris.setText("");
        harga_beliAk.setText("");
        harga_jualAk.setText("");
        qtyAksesoris.setText(PLACEHOLDER_QTY);
        subTotalAksesoris.setText("");
        hitung();
    }//GEN-LAST:event_btambah_aksesorisActionPerformed

    private void qtyAksesorisKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyAksesorisKeyReleased
        try{
        int xhja = Integer.parseInt(harga_jualAk.getText());
        int xqtya = Integer.parseInt(qtyAksesoris.getText());
        int xjmla = xhja * xqtya;
        subTotalAksesoris.setText(String.valueOf(xjmla));
       } catch  (NumberFormatException e) {
       subTotalAksesoris.setText("0");
       }
    }//GEN-LAST:event_qtyAksesorisKeyReleased

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        // Validasi pelanggan dan teknisi
    String idPelangganText = id_pelanggan.getText().trim();
    String idTeknisiText = id_teknisi.getText().trim();
    
    if (idPelangganText.isEmpty()) {
        JOptionPane.showMessageDialog(this, 
            "Pelanggan belum dipilih!\nSilakan pilih pelanggan terlebih dahulu.", 
            "Validasi Data", 
            JOptionPane.WARNING_MESSAGE);
        bcari_pelanggan.requestFocus();
        return;
    }
    
    if (idTeknisiText.isEmpty()) {
        JOptionPane.showMessageDialog(this, 
            "Teknisi belum dipilih!\nSilakan pilih teknisi terlebih dahulu.", 
            "Validasi Data", 
            JOptionPane.WARNING_MESSAGE);
        bcari_teknisi.requestFocus();
        return;
    }
    
    // validasi minimal ada 1 service dan sparepart
    int rowCount = tbl_transaksi.getRowCount();
    
    if (rowCount < 2) {
        JOptionPane.showMessageDialog(this, 
            "Transaksi harus memiliki minimal:\n" +
            "• 1 Service\n" +
            "• 1 Sparepart\n\n", 
            "Validasi Transaksi", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Validasi apakah ada service dan sparepart
    boolean hasService = false;
    boolean hasSparepart = false;
 
    for (int i = 0; i < rowCount; i++) {
        String idItem = tbl_transaksi.getValueAt(i, 0).toString();
        
        String hargaBeli = tbl_transaksi.getValueAt(i, 2).toString();
        
        if (hargaBeli.equals("0")) {
            hasService = true;
        } else {
            hasSparepart = true;
        }
    }
    
    if (!hasService) {
        JOptionPane.showMessageDialog(this, 
            "Transaksi harus memiliki minimal 1 Service!\n" +
            "Silakan tambahkan service terlebih dahulu.", 
            "Validasi Transaksi", 
            JOptionPane.WARNING_MESSAGE);
        bcari_service.requestFocus();
        return;
    }
    
    if (!hasSparepart) {
        JOptionPane.showMessageDialog(this, 
            "Transaksi harus memiliki minimal 1 Sparepart!\n" +
            "Silakan tambahkan sparepart terlebih dahulu.", 
            "Validasi Transaksi", 
            JOptionPane.WARNING_MESSAGE);
        bcari_sparepart.requestFocus();
        return;
    }
    
    // validasi total tidak boleh Rp 0
    String totalText = total_biaya.getText().trim();
    if (totalText.isEmpty() || totalText.equals("0")) {
        JOptionPane.showMessageDialog(this, 
            "Total biaya tidak valid!\n" +
            "Pastikan semua item sudah ditambahkan dengan benar.", 
            "Validasi Transaksi", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Memproses Simpan
    Date selectedDate = jtgl.getDate();
    if (selectedDate == null) {
        JOptionPane.showMessageDialog(this, 
            "Tanggal belum dipilih!", 
            "Validasi Data", 
            JOptionPane.WARNING_MESSAGE);
        jtgl.requestFocus();
        return;
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String fd = sdf.format(selectedDate);
    
    String sql = "INSERT INTO tb_nota VALUES (?,?,?,?,?)";
    String zsql = "INSERT INTO tb_nota_detail VALUES (?,?,?,?,?,?)";
    try {
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1, id_nota.getText());
        stat.setString(2, fd);
        stat.setString(3, label_idKasir.getText());
        stat.setString(4, idPelangganText);
        stat.setString(5, idTeknisiText);
        stat.executeUpdate();
        for (int i = 0; i < rowCount; i++) {
            String xkd = tbl_transaksi.getValueAt(i, 0).toString();
            String nama = tbl_transaksi.getValueAt(i, 1).toString();
            String xhb = tbl_transaksi.getValueAt(i, 2).toString();
            String xhj = tbl_transaksi.getValueAt(i, 3).toString();
            String xqty = tbl_transaksi.getValueAt(i, 4).toString();
            
            PreparedStatement stat2 = conn.prepareStatement(zsql);
            stat2.setString(1, id_nota.getText());
            stat2.setString(2, xkd);
            stat2.setString(3, nama);
            stat2.setString(4, xhb);
            stat2.setString(5, xhj);
            stat2.setString(6, xqty);
            stat2.executeUpdate();
        }
        JOptionPane.showMessageDialog(null, 
            "Data berhasil disimpan!\n" +
            "ID Nota: " + id_nota.getText() + "\n" +
            "Total: Rp " + totalText,
            "Sukses",
            JOptionPane.INFORMATION_MESSAGE);
        cetak();
        aktif();
        kosong();
        autonumber();
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, 
            "Data gagal disimpan!\n" + e.getMessage(),
            "Error Database",
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }//GEN-LAST:event_bsimpanActionPerformed

    private void bbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbatalActionPerformed
        kosong();
        aktif();
        autonumber();
    }//GEN-LAST:event_bbatalActionPerformed

    private void bhapus_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapus_transaksiActionPerformed
        int index = tbl_transaksi.getSelectedRow();
        tabmode.removeRow(index);
        tbl_transaksi.setModel(tabmode);
        hitung();
    }//GEN-LAST:event_bhapus_transaksiActionPerformed

    private void bcari_teknisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcari_teknisiActionPerformed
        form_popup.poppup_teknisi pt = new form_popup.poppup_teknisi();
        pt.tek = this;
        pt.setVisible(true);
        pt.setResizable(false);
        pt.setLocationRelativeTo(null);
    }//GEN-LAST:event_bcari_teknisiActionPerformed

    private void bbatal_ssActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbatal_ssActionPerformed
        id_service.setText("");
        jenis_service.setText("");
        biaya_service.setText("");
        kd_barang.setText("");
        nama_barang.setText("");
        harga_beli.setText("");
        harga_jual.setText("");
        merk_barang.setText("");
        qtySparepart.setText(PLACEHOLDER_QTY);
        subTotalSparepart.setText("");
        bcari_service.setEnabled(true);
        bcari_sparepart.setEnabled(false);
        bcari_sparepart.setToolTipText("Pilih jenis service terlebih dahulu");  
    }//GEN-LAST:event_bbatal_ssActionPerformed

    private void bbatal_akseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbatal_akseActionPerformed
        id_aksesoris.setText("");
        nama_aksesoris.setText("");
        harga_beliAk.setText("");
        harga_jualAk.setText("");
        
        qtyAksesoris.setText(PLACEHOLDER_QTY);
        
        subTotalAksesoris.setText("");
    }//GEN-LAST:event_bbatal_akseActionPerformed

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
            java.util.logging.Logger.getLogger(transaksi_pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaksi_pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaksi_pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaksi_pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transaksi_pembayaran().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea almt_pelanggan;
    public javax.swing.JTextArea almt_teknisi;
    private javax.swing.JButton bbatal;
    private javax.swing.JButton bbatal_akse;
    private javax.swing.JButton bbatal_ss;
    private javax.swing.JButton bcari_aksesoris;
    private javax.swing.JButton bcari_pelanggan;
    private javax.swing.JButton bcari_service;
    private javax.swing.JButton bcari_sparepart;
    private javax.swing.JButton bcari_teknisi;
    private javax.swing.JButton bhapus_transaksi;
    private javax.swing.JTextField biaya_service;
    private javax.swing.JButton bsimpan;
    private javax.swing.JButton btambah_aksesoris;
    private javax.swing.JButton btambah_sparepart;
    private javax.swing.JPanel detail_aksesoris;
    private javax.swing.JPanel detail_service;
    private javax.swing.JTextField harga_beli;
    private javax.swing.JTextField harga_beliAk;
    private javax.swing.JTextField harga_jual;
    private javax.swing.JTextField harga_jualAk;
    public javax.swing.JTextField hp_pelanggan;
    public javax.swing.JTextField hp_teknisi;
    private javax.swing.JTextField id_aksesoris;
    private javax.swing.JTextField id_nota;
    public javax.swing.JTextField id_pelanggan;
    private javax.swing.JTextField id_service;
    public javax.swing.JTextField id_teknisi;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jenis_service;
    private org.jdesktop.swingx.JXDatePicker jtgl;
    private javax.swing.JTextField kd_barang;
    private javax.swing.JLabel label_idKasir;
    private javax.swing.JLabel label_namaKasir;
    private javax.swing.JLabel labelnot;
    private javax.swing.JTextField merk_barang;
    private javax.swing.JTextField nama_aksesoris;
    private javax.swing.JTextField nama_barang;
    public javax.swing.JTextField nama_pelanggan;
    public javax.swing.JTextField nama_teknisi;
    private javax.swing.JPanel nota;
    private javax.swing.JLabel nota_pembayaran;
    private javax.swing.JPanel panel_nota;
    private javax.swing.JPanel panel_pelanggan;
    private javax.swing.JPanel panel_pelanggan1;
    private javax.swing.JPanel panel_teknisi;
    private javax.swing.JTextField qtyAksesoris;
    private javax.swing.JTextField qtySparepart;
    private javax.swing.JTextField subTotalAksesoris;
    private javax.swing.JTextField subTotalSparepart;
    private javax.swing.JTable tbl_transaksi;
    private javax.swing.JTextField total_biaya;
    // End of variables declaration//GEN-END:variables
}
