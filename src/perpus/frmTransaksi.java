/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.MultiSplitLayout;

/**
 *
 * @author MOZART-PC
 */
public class frmTransaksi extends javax.swing.JInternalFrame {
    clsConnection oConn=new clsConnection();
    clsFunctions cls = new clsFunctions();
    boolean isNew = true;
    /**
     * Creates new form frmTransaksi
     */
    public frmTransaksi() {
        initComponents();
        cls.setDateVal(dtpTglAwal, new Date());
        cls.setDateVal(dtpTglAkhir, new Date());
        cls.setDateVal(dtpTglPinjam, new Date());
        displayTrans();
        
        //make datepicker editable = false
        dtpTglAkhir.getEditor().setEditable(false);
        dtpTglAwal.getEditor().setEditable(false);
        dtpTglPinjam.getEditor().setEditable(false);
        dtpTglKembali.getEditor().setEditable(false);
    }
    
    private void cariBuku() {
        try {
            // TODO add your handling code here:
            String wCondition = "";
            if (!txtCariKodeBarang.getText().equals("")) {
                wCondition = "where barang_kode like '%" + txtCariKodeBarang.getText() + "%'";
            }
            if (!txtCariNamaBarang.getText().equals("")) {
                if (wCondition.equals("")) {
                    wCondition="where barang_nama like '%" + txtCariNamaBarang.getText() + "%'";
                }
                else {
                    wCondition= wCondition + " and barang_nama like '%" + txtCariNamaBarang.getText() + "%'";
                }
            }
            cls.showTblGrid(tblCariBuku, "select kd_buku as 'Kode Buku',"
                    + "nm_buku as 'Nama Barang' from t_buku " + wCondition + " order by kd_buku");
            tblCariBuku.setEditingColumn(0);
            tblCariBuku.setEditingRow(0);
        } catch (SQLException ex) {
            Logger.getLogger(frmTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void genDocNo() {
        try {
            String year=cls.getDateVal(dtpTglAwal).substring(0, 4);
            ResultSet rs=oConn.getData("select ifnull(max(cast(left(no_dok,4) as signed)),0) + 1 as no_dok "
                + " from t_pinjam where year(tgl_pinjam) = " + year + "");
            if (rs.next()) {
                String dok=new DecimalFormat("0000").format(rs.getInt("no_dok")) + "/PB/" + year.substring(2, 4);
                txtDokumen.setText(dok);
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean allowSave() {
        if (txtIDMember.getText().equals("")) {
            cls.showMsg("ID Member harus di isi", "Simpan Gagal", 0);
            txtIDMember.grabFocus();
            return false;
        }
        if (txtNamaMember.getText().equals("")) {
            cls.showMsg("Nama Member harus di isi", "Simpan Gagal", 0);
            txtNamaMember.grabFocus();
            return false;
        }
        if (txtQty.getText().equals("0")) {
            cls.showMsg("Total Buku yang di pinjam harus lebih besar dari nol (0)", "Simpan Gagal", 0);
            txtQty.grabFocus();
            return false;
        }
        return true;
    }
    
    private void displayTrans() {
        try {
            cls.showTblGrid(tblTransaksi, "select no_dok as 'No. Dokumen', tgl_pinjam as 'Tgl Pinjam', "
                    + "id_member as 'ID Member', tgl_kembali as 'Tgl Kembali', jml_buku as 'Jml. Buku' "
                    + "from t_pinjam where tgl_pinjam between '" + cls.getDateVal(dtpTglAwal) + " 00:00:00' and "
                    + "'" + cls.getDateVal(dtpTglAkhir) + " 23:59:59'");
            tblTransaksi.setEditingColumn(0);
            tblTransaksi.setEditingRow(0);
            showDokumen();
        } catch (SQLException ex) {
            cls.showMsg("Error saat menampilkan detail buku.","Tampil Data Buku", 2);
        }
    }
    
    private void showDokumen() {
        textMode(false);
        if (tblTransaksi.getRowCount()!=0 || tblTransaksi.getSelectedRow()>0) { 
            txtDokumen.setText((String) tblTransaksi.getModel().getValueAt(tblTransaksi.getSelectedRow(), 0)); 
            dtpTglPinjam.setDate((Date) tblTransaksi.getModel().getValueAt(tblTransaksi.getSelectedRow(), 1)); 
            txtIDMember.setText((String) tblTransaksi.getModel().getValueAt(tblTransaksi.getSelectedRow(), 2)); 
            dtpTglAwal.setDate((Date) tblTransaksi.getModel().getValueAt(tblTransaksi.getSelectedRow(), 3)); 
            txtQty.setText((String) tblTransaksi.getModel().getValueAt(tblTransaksi.getSelectedRow(), 4)); 
            displayTransDet();
        }
        else
        {
            clrForm();
        }
    }
    private void displayTransDet() {
        try {
            cls.showTblGrid(tblDetail, "select d.kd_buku as 'Kode Buku', nm_buku as 'Nama Buku' "
                    + "from t_pinjam_det d join t_buku b on b.kd_buku = d.kd_buku "
                    + "where no_dok ='" + txtDokumen.getText() + "'");
        } catch (SQLException ex) {
            cls.showMsg("Error saat menampilkan detail buku.","Tampil Data Buku", 2);
        }
    }
    
    private void clrForm() {
        txtDokumen.setText("");
        txtIDMember.setText("");
        txtNamaMember.setText("");
        chkBukuKembali.setSelected(false);
        txtQty.setText("0");
        displayTransDet();
        txtIDMember.grabFocus();
        cmdAdd.setOpaque(false);
        cmdCancel.setOpaque(false);
        cmdSave.setOpaque(false);
    }
        
    private void textMode(boolean bol) {
        txtIDMember.setEnabled(bol);
        txtNamaMember.setEnabled(bol);
        dtpTglPinjam.setEnabled(bol);
        dtpTglKembali.setEnabled(bol);
        txtQty.setEnabled(bol);
        cmdSave.setEnabled(bol);
        cmdCancel.setEnabled(bol);
        cmdAdd.setEnabled(!bol);
        cmdEdit.setEnabled(!bol);
        cmdDelete.setEnabled(!bol);
        cmdAddDet.setEnabled(bol);
        cmdEditDet.setEnabled(bol);
        cmdDelDet.setEnabled(bol);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cari_Buku = new javax.swing.JDialog();
        bgCariBarang = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblCariBuku = new javax.swing.JTable();
        pnlSearchDialog = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtCariKodeBarang = new javax.swing.JTextField();
        txtCariNamaBarang = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        cmdCariBarang = new javax.swing.JButton();
        cmdPilihBarang = new javax.swing.JButton();
        pnlForm = new javax.swing.JPanel();
        pnlTitle = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtDokumen = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        cmdSave = new javax.swing.JButton();
        cmdClose = new javax.swing.JButton();
        cmdDelete = new javax.swing.JButton();
        cmdAdd = new javax.swing.JButton();
        cmdEdit = new javax.swing.JButton();
        cmdCancel = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        cmdDisplay = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        dtpTglAwal = new org.jdesktop.swingx.JXDatePicker();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dtpTglAkhir = new org.jdesktop.swingx.JXDatePicker();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtIDMember = new javax.swing.JTextField();
        txtNamaMember = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        dtpTglPinjam = new org.jdesktop.swingx.JXDatePicker();
        dtpTglKembali = new org.jdesktop.swingx.JXDatePicker();
        chkBukuKembali = new javax.swing.JCheckBox();
        cmdSearch = new javax.swing.JButton();
        pnlDetail = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDetail = new javax.swing.JTable();
        cmdAddDet = new javax.swing.JButton();
        cmdEditDet = new javax.swing.JButton();
        cmdDelDet = new javax.swing.JButton();

        bgCariBarang.setBackground(new java.awt.Color(27, 161, 226));

        tblCariBuku.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tblCariBuku.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tblCariBuku);

        pnlSearchDialog.setBackground(new java.awt.Color(255, 255, 255));
        pnlSearchDialog.setBorder(javax.swing.BorderFactory.createTitledBorder("Cari Buku"));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel16.setText("Kode Buku");

        txtCariKodeBarang.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        txtCariKodeBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCariKodeBarangKeyPressed(evt);
            }
        });

        txtCariNamaBarang.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel17.setText("Nama Buku");

        cmdCariBarang.setBackground(new java.awt.Color(255, 242, 157));
        cmdCariBarang.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cmdCariBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/Img/search.png"))); // NOI18N
        cmdCariBarang.setText("Cari");
        cmdCariBarang.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        cmdCariBarang.setContentAreaFilled(false);
        cmdCariBarang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmdCariBarang.setMaximumSize(new java.awt.Dimension(87, 21));
        cmdCariBarang.setMinimumSize(new java.awt.Dimension(87, 21));
        cmdCariBarang.setPreferredSize(new java.awt.Dimension(87, 21));
        cmdCariBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdCariBarangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdCariBarangMouseExited(evt);
            }
        });
        cmdCariBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCariBarangActionPerformed(evt);
            }
        });

        cmdPilihBarang.setBackground(new java.awt.Color(255, 242, 157));
        cmdPilihBarang.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cmdPilihBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/Img/apply_gui_16.png"))); // NOI18N
        cmdPilihBarang.setText("Pilih");
        cmdPilihBarang.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        cmdPilihBarang.setContentAreaFilled(false);
        cmdPilihBarang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmdPilihBarang.setMaximumSize(new java.awt.Dimension(87, 21));
        cmdPilihBarang.setMinimumSize(new java.awt.Dimension(87, 21));
        cmdPilihBarang.setPreferredSize(new java.awt.Dimension(87, 21));
        cmdPilihBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdPilihBarangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdPilihBarangMouseExited(evt);
            }
        });
        cmdPilihBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPilihBarangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSearchDialogLayout = new javax.swing.GroupLayout(pnlSearchDialog);
        pnlSearchDialog.setLayout(pnlSearchDialogLayout);
        pnlSearchDialogLayout.setHorizontalGroup(
            pnlSearchDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSearchDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16))
                .addGap(44, 44, 44)
                .addGroup(pnlSearchDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSearchDialogLayout.createSequentialGroup()
                        .addComponent(txtCariKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdCariBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdPilihBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 16, Short.MAX_VALUE))
                    .addComponent(txtCariNamaBarang))
                .addContainerGap())
        );
        pnlSearchDialogLayout.setVerticalGroup(
            pnlSearchDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchDialogLayout.createSequentialGroup()
                .addGroup(pnlSearchDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSearchDialogLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel16))
                    .addGroup(pnlSearchDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCariKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmdCariBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmdPilihBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSearchDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSearchDialogLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel17))
                    .addComponent(txtCariNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout bgCariBarangLayout = new javax.swing.GroupLayout(bgCariBarang);
        bgCariBarang.setLayout(bgCariBarangLayout);
        bgCariBarangLayout.setHorizontalGroup(
            bgCariBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgCariBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgCariBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlSearchDialog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bgCariBarangLayout.setVerticalGroup(
            bgCariBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgCariBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlSearchDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlSearchDialog.getAccessibleContext().setAccessibleName("Cari Buku");

        javax.swing.GroupLayout cari_BukuLayout = new javax.swing.GroupLayout(cari_Buku.getContentPane());
        cari_Buku.getContentPane().setLayout(cari_BukuLayout);
        cari_BukuLayout.setHorizontalGroup(
            cari_BukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bgCariBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        cari_BukuLayout.setVerticalGroup(
            cari_BukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bgCariBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });

        pnlForm.setBackground(new java.awt.Color(30, 144, 255));

        pnlTitle.setBackground(new java.awt.Color(255, 255, 255));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitle.setText("Transaksi Pinjam Buku");
        lblTitle.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                lblTitleMouseDragged(evt);
            }
        });
        lblTitle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblTitleMousePressed(evt);
            }
        });

        javax.swing.GroupLayout pnlTitleLayout = new javax.swing.GroupLayout(pnlTitle);
        pnlTitle.setLayout(pnlTitleLayout);
        pnlTitleLayout.setHorizontalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTitleLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlTitleLayout.setVerticalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        tblTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTransaksi);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("No. Dokumen");

        txtDokumen.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        txtDokumen.setEnabled(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(639, 55));
        jPanel2.setLayout(null);

        cmdSave.setBackground(new java.awt.Color(255, 242, 157));
        cmdSave.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cmdSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/Img/apply_gui_20.png"))); // NOI18N
        cmdSave.setText("Simpan");
        cmdSave.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        cmdSave.setContentAreaFilled(false);
        cmdSave.setEnabled(false);
        cmdSave.setMaximumSize(new java.awt.Dimension(87, 27));
        cmdSave.setMinimumSize(new java.awt.Dimension(87, 27));
        cmdSave.setPreferredSize(new java.awt.Dimension(87, 27));
        cmdSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdSaveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdSaveMouseExited(evt);
            }
        });
        cmdSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSaveActionPerformed(evt);
            }
        });
        jPanel2.add(cmdSave);
        cmdSave.setBounds(280, 10, 87, 27);

        cmdClose.setBackground(new java.awt.Color(255, 242, 157));
        cmdClose.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cmdClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/Img/Door Opened_20px.png"))); // NOI18N
        cmdClose.setText("Keluar");
        cmdClose.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        cmdClose.setContentAreaFilled(false);
        cmdClose.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmdClose.setMaximumSize(new java.awt.Dimension(87, 27));
        cmdClose.setMinimumSize(new java.awt.Dimension(87, 27));
        cmdClose.setPreferredSize(new java.awt.Dimension(87, 27));
        cmdClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdCloseMouseExited(evt);
            }
        });
        cmdClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCloseActionPerformed(evt);
            }
        });
        jPanel2.add(cmdClose);
        cmdClose.setBounds(460, 10, 87, 27);

        cmdDelete.setBackground(new java.awt.Color(255, 242, 157));
        cmdDelete.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cmdDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/Img/Delete File_20px.png"))); // NOI18N
        cmdDelete.setText("Hapus");
        cmdDelete.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        cmdDelete.setContentAreaFilled(false);
        cmdDelete.setMaximumSize(new java.awt.Dimension(87, 27));
        cmdDelete.setMinimumSize(new java.awt.Dimension(87, 27));
        cmdDelete.setPreferredSize(new java.awt.Dimension(87, 27));
        cmdDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdDeleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdDeleteMouseExited(evt);
            }
        });
        cmdDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteActionPerformed(evt);
            }
        });
        jPanel2.add(cmdDelete);
        cmdDelete.setBounds(190, 10, 87, 27);

        cmdAdd.setBackground(new java.awt.Color(255, 242, 157));
        cmdAdd.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cmdAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/Img/Add List_20px.png"))); // NOI18N
        cmdAdd.setText("Tambah");
        cmdAdd.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        cmdAdd.setContentAreaFilled(false);
        cmdAdd.setMaximumSize(new java.awt.Dimension(87, 27));
        cmdAdd.setMinimumSize(new java.awt.Dimension(87, 27));
        cmdAdd.setPreferredSize(new java.awt.Dimension(87, 27));
        cmdAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdAddMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdAddMouseExited(evt);
            }
        });
        cmdAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });
        jPanel2.add(cmdAdd);
        cmdAdd.setBounds(10, 10, 87, 27);

        cmdEdit.setBackground(new java.awt.Color(255, 242, 157));
        cmdEdit.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cmdEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/Img/Edit Property_20px.png"))); // NOI18N
        cmdEdit.setText("Ubah");
        cmdEdit.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        cmdEdit.setContentAreaFilled(false);
        cmdEdit.setMaximumSize(new java.awt.Dimension(87, 27));
        cmdEdit.setMinimumSize(new java.awt.Dimension(87, 27));
        cmdEdit.setPreferredSize(new java.awt.Dimension(87, 27));
        cmdEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdEditMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdEditMouseExited(evt);
            }
        });
        cmdEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditActionPerformed(evt);
            }
        });
        jPanel2.add(cmdEdit);
        cmdEdit.setBounds(100, 10, 87, 27);

        cmdCancel.setBackground(new java.awt.Color(255, 242, 157));
        cmdCancel.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cmdCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/Img/remove-icon_16.png"))); // NOI18N
        cmdCancel.setText("Batal");
        cmdCancel.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        cmdCancel.setContentAreaFilled(false);
        cmdCancel.setEnabled(false);
        cmdCancel.setMaximumSize(new java.awt.Dimension(87, 27));
        cmdCancel.setMinimumSize(new java.awt.Dimension(87, 27));
        cmdCancel.setPreferredSize(new java.awt.Dimension(87, 27));
        cmdCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdCancelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdCancelMouseExited(evt);
            }
        });
        cmdCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelActionPerformed(evt);
            }
        });
        jPanel2.add(cmdCancel);
        cmdCancel.setBounds(370, 10, 87, 27);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(639, 55));

        cmdDisplay.setBackground(new java.awt.Color(255, 242, 157));
        cmdDisplay.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cmdDisplay.setText("Tampilkan Data");
        cmdDisplay.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        cmdDisplay.setContentAreaFilled(false);
        cmdDisplay.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmdDisplay.setMaximumSize(new java.awt.Dimension(87, 21));
        cmdDisplay.setMinimumSize(new java.awt.Dimension(87, 21));
        cmdDisplay.setPreferredSize(new java.awt.Dimension(87, 21));
        cmdDisplay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdDisplayMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdDisplayMouseExited(evt);
            }
        });
        cmdDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDisplayActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel6.setText("Pencarian Dokumen");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel4.setText("Tgl Awal");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel5.setText("Tgl Akhir");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(dtpTglAwal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(dtpTglAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmdDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(dtpTglAwal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(dtpTglAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tgl Pinjam");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ID Member");

        txtIDMember.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        txtIDMember.setEnabled(false);

        txtNamaMember.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        txtNamaMember.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNamaMember.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Nama Member");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Tgl Kembali");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Jumlah Buku");

        txtQty.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        txtQty.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtQty.setText("0");
        txtQty.setEnabled(false);

        dtpTglPinjam.setEnabled(false);

        dtpTglKembali.setEnabled(false);

        chkBukuKembali.setForeground(new java.awt.Color(255, 255, 255));
        chkBukuKembali.setText("Buku Dikembalikan");
        chkBukuKembali.setOpaque(false);

        cmdSearch.setBackground(new java.awt.Color(255, 255, 255));
        cmdSearch.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cmdSearch.setText("Cari");
        cmdSearch.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        cmdSearch.setContentAreaFilled(false);
        cmdSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmdSearch.setMaximumSize(new java.awt.Dimension(87, 21));
        cmdSearch.setMinimumSize(new java.awt.Dimension(87, 21));
        cmdSearch.setOpaque(true);
        cmdSearch.setPreferredSize(new java.awt.Dimension(87, 21));
        cmdSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdSearchMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdSearchMouseExited(evt);
            }
        });
        cmdSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSearchActionPerformed(evt);
            }
        });

        pnlDetail.setOpaque(false);

        tblDetail.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblDetail);

        cmdAddDet.setBackground(new java.awt.Color(255, 255, 255));
        cmdAddDet.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cmdAddDet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/Img/Add List_20px.png"))); // NOI18N
        cmdAddDet.setToolTipText("Klik untuk tambah item buku yang dipinjam");
        cmdAddDet.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        cmdAddDet.setContentAreaFilled(false);
        cmdAddDet.setEnabled(false);
        cmdAddDet.setMaximumSize(new java.awt.Dimension(87, 27));
        cmdAddDet.setMinimumSize(new java.awt.Dimension(87, 27));
        cmdAddDet.setOpaque(true);
        cmdAddDet.setPreferredSize(new java.awt.Dimension(87, 27));
        cmdAddDet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdAddDetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdAddDetMouseExited(evt);
            }
        });
        cmdAddDet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddDetActionPerformed(evt);
            }
        });

        cmdEditDet.setBackground(new java.awt.Color(255, 255, 255));
        cmdEditDet.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cmdEditDet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/Img/Edit Property_20px.png"))); // NOI18N
        cmdEditDet.setToolTipText("Klik untuk ubah item buku yang dipinjam");
        cmdEditDet.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        cmdEditDet.setContentAreaFilled(false);
        cmdEditDet.setEnabled(false);
        cmdEditDet.setMaximumSize(new java.awt.Dimension(87, 27));
        cmdEditDet.setMinimumSize(new java.awt.Dimension(87, 27));
        cmdEditDet.setOpaque(true);
        cmdEditDet.setPreferredSize(new java.awt.Dimension(87, 27));
        cmdEditDet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdEditDetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdEditDetMouseExited(evt);
            }
        });
        cmdEditDet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditDetActionPerformed(evt);
            }
        });

        cmdDelDet.setBackground(new java.awt.Color(255, 255, 255));
        cmdDelDet.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cmdDelDet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/Img/Delete File_20px.png"))); // NOI18N
        cmdDelDet.setToolTipText("Klik untuk hapus item buku yang dipinjam");
        cmdDelDet.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        cmdDelDet.setContentAreaFilled(false);
        cmdDelDet.setEnabled(false);
        cmdDelDet.setMaximumSize(new java.awt.Dimension(87, 27));
        cmdDelDet.setMinimumSize(new java.awt.Dimension(87, 27));
        cmdDelDet.setOpaque(true);
        cmdDelDet.setPreferredSize(new java.awt.Dimension(87, 27));
        cmdDelDet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdDelDetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdDelDetMouseExited(evt);
            }
        });
        cmdDelDet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDelDetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDetailLayout = new javax.swing.GroupLayout(pnlDetail);
        pnlDetail.setLayout(pnlDetailLayout);
        pnlDetailLayout.setHorizontalGroup(
            pnlDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
            .addGroup(pnlDetailLayout.createSequentialGroup()
                .addComponent(cmdAddDet, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdEditDet, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdDelDet, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        pnlDetailLayout.setVerticalGroup(
            pnlDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetailLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdAddDet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdEditDet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDelDet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlFormLayout = new javax.swing.GroupLayout(pnlForm);
        pnlForm.setLayout(pnlFormLayout);
        pnlFormLayout.setHorizontalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(pnlFormLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlFormLayout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlFormLayout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(dtpTglKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chkBukuKembali))
                            .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFormLayout.createSequentialGroup()
                                    .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(pnlFormLayout.createSequentialGroup()
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(15, 15, 15)
                                            .addComponent(txtDokumen, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlFormLayout.createSequentialGroup()
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(15, 15, 15)
                                            .addComponent(txtIDMember))
                                        .addGroup(pnlFormLayout.createSequentialGroup()
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(15, 15, 15)
                                            .addComponent(dtpTglPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmdSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFormLayout.createSequentialGroup()
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(15, 15, 15)
                                    .addComponent(txtNamaMember, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(126, 126, 126)
                        .addComponent(pnlDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
        );
        pnlFormLayout.setVerticalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addComponent(pnlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormLayout.createSequentialGroup()
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtDokumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(dtpTglPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtIDMember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtNamaMember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(dtpTglKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkBukuKembali))
                        .addGap(4, 4, 4)
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(pnlDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblTitleMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTitleMouseDragged

    }//GEN-LAST:event_lblTitleMouseDragged

    private void lblTitleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTitleMousePressed

    }//GEN-LAST:event_lblTitleMousePressed

    private void tblTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTransaksiMouseClicked
        // TODO add your handling code here:
        showDokumen();
    }//GEN-LAST:event_tblTransaksiMouseClicked

    private void cmdSaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdSaveMouseEntered
        // TODO add your handling code here:
        cmdSave.setOpaque(true);
    }//GEN-LAST:event_cmdSaveMouseEntered

    private void cmdSaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdSaveMouseExited
        // TODO add your handling code here:
        cmdSave.setOpaque(false);
    }//GEN-LAST:event_cmdSaveMouseExited

    private void cmdSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSaveActionPerformed
        // TODO add your handling code here:
        if (!allowSave()){ return; }
        try {
            if (txtDokumen.isEnabled()) {
                oConn.setData("insert into t_buku (kd_buku, nm_buku, "
                    + "th_penerbit, nm_penerbit, nm_penulis, deskripsi, stok_buku) "
                    + "values ('" + txtDokumen.getText() + "','" + txtNamaMember.getText() + "', "
                    + "'" + txtNamaMember.getText() + "', '" + txtIDMember.getText() + "', "
                    + "'" + txtQty.getText() + "')");
            }
            else {
                oConn.setData("update t_buku set nm_buku = '" + txtNamaMember.getText() + "', "
                    + "th_penerbit = " + txtNamaMember.getText() + ", nm_penerbit = '" + txtIDMember.getText() + "', "
                    + "stok_buku = '" + txtQty.getText() + "' where kd_buku = '" + txtDokumen.getText() + "'");
            }
            textMode(false);
            clrForm();
            cls.showMsg("Simpan data berhasil !", "Simpan", 1);
        } catch (SQLException ex) {
            cls.showMsg(ex.getSQLState(), "Gagal Simpan", 0);
        }
        cmdDisplay.doClick();
    }//GEN-LAST:event_cmdSaveActionPerformed

    private void cmdCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdCloseMouseEntered
        // TODO add your handling code here:
        cmdClose.setOpaque(true);
    }//GEN-LAST:event_cmdCloseMouseEntered

    private void cmdCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdCloseMouseExited
        // TODO add your handling code here:
        cmdClose.setOpaque(false);
    }//GEN-LAST:event_cmdCloseMouseExited

    private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cmdCloseActionPerformed

    private void cmdDeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdDeleteMouseEntered
        // TODO add your handling code here:
        cmdDelete.setOpaque(true);
    }//GEN-LAST:event_cmdDeleteMouseEntered

    private void cmdDeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdDeleteMouseExited
        // TODO add your handling code here:
        cmdDelete.setOpaque(false);
    }//GEN-LAST:event_cmdDeleteMouseExited

    private void cmdDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteActionPerformed
        // TODO add your handling code here:
        if (txtDokumen.getText().equals("")) {
            cls.showMsg("Harap pilih buku yang akan dihapus !", "Hapus Gagal", 2);
            txtDokumen.grabFocus();
            return;
        }
        int btn = JOptionPane.YES_NO_OPTION;
        int res=JOptionPane.showConfirmDialog(null,"Apakah anda yakin ingin "
            + "menghapus data buku [" + txtDokumen.getText() + " - " +
            txtNamaMember.getText()  + "] ?","Hapus Data",btn);
        if (res==JOptionPane.YES_OPTION){
            try {
                oConn.setData("delete from t_buku where kd_buku = '"
                    + txtDokumen.getText() + "'");
                cmdDisplay.doClick();
                cls.showMsg("Kode Buku ["+ txtDokumen.getText() +" - " +
                    txtNamaMember.getText() + "] berhasil di hapus.", "Hapus Berhasil", 0);
                textMode(true);
                clrForm();
            } catch (SQLException ex) {
                cls.showMsg(ex.getMessage(), "Error", 0);
            }
        }
    }//GEN-LAST:event_cmdDeleteActionPerformed

    private void cmdAddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdAddMouseEntered
        // TODO add your handling code here:
        cmdAdd.setOpaque(true);
    }//GEN-LAST:event_cmdAddMouseEntered

    private void cmdAddMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdAddMouseExited
        // TODO add your handling code here:
        cmdAdd.setOpaque(false);
    }//GEN-LAST:event_cmdAddMouseExited

    private void cmdAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddActionPerformed
        // TODO add your handling code here:
        textMode(true);
        clrForm();
        genDocNo();
    }//GEN-LAST:event_cmdAddActionPerformed

    private void cmdEditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdEditMouseEntered
        // TODO add your handling code here:
        cmdEdit.setOpaque(true);
    }//GEN-LAST:event_cmdEditMouseEntered

    private void cmdEditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdEditMouseExited
        // TODO add your handling code here:
        cmdEdit.setOpaque(false);
    }//GEN-LAST:event_cmdEditMouseExited

    private void cmdEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditActionPerformed
        // TODO add your handling code here:
        if (txtDokumen.getText().equals("")) {
            cls.showMsg("Harap pilih user yang akan di ubah",
                "Ubah Gagal", 2);
            return;
        }
        textMode(true);
        txtDokumen.setEnabled(false);
    }//GEN-LAST:event_cmdEditActionPerformed

    private void cmdCancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdCancelMouseEntered
        // TODO add your handling code here:
        cmdCancel.setOpaque(true);
    }//GEN-LAST:event_cmdCancelMouseEntered

    private void cmdCancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdCancelMouseExited
        // TODO add your handling code here:
        cmdCancel.setOpaque(false);
    }//GEN-LAST:event_cmdCancelMouseExited

    private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
        // TODO add your handling code here:
        textMode(false);
        clrForm();
    }//GEN-LAST:event_cmdCancelActionPerformed

    private void cmdDisplayMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdDisplayMouseEntered
        // TODO add your handling code here:
        cmdDisplay.setOpaque(true);
    }//GEN-LAST:event_cmdDisplayMouseEntered

    private void cmdDisplayMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdDisplayMouseExited
        // TODO add your handling code here:
        cmdDisplay.setOpaque(false);
    }//GEN-LAST:event_cmdDisplayMouseExited

    private void cmdDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDisplayActionPerformed
        displayTrans();
    }//GEN-LAST:event_cmdDisplayActionPerformed

    private void cmdSearchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdSearchMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdSearchMouseEntered

    private void cmdSearchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdSearchMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdSearchMouseExited

    private void cmdSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdSearchActionPerformed

    private void cmdAddDetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdAddDetMouseEntered
        // TODO add your handling code here:
        cmdAddDet.setBackground(new Color(255,242,157));
    }//GEN-LAST:event_cmdAddDetMouseEntered

    private void cmdAddDetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdAddDetMouseExited
        // TODO add your handling code here:
        cmdAddDet.setBackground(new Color(255,255,255)); //white
    }//GEN-LAST:event_cmdAddDetMouseExited

    private void cmdAddDetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddDetActionPerformed
        // TODO add your handling code here:
        isNew=true;
        cariBuku();
        cari_Buku.pack();
        cari_Buku.setModal(true);
        cari_Buku.setLocationRelativeTo(this);
        cari_Buku.setVisible(true);
    }//GEN-LAST:event_cmdAddDetActionPerformed

    private void cmdEditDetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdEditDetMouseEntered
        // TODO add your handling code here:
        cmdEdit.setBackground(new Color(255,242,157));
    }//GEN-LAST:event_cmdEditDetMouseEntered

    private void cmdEditDetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdEditDetMouseExited
        // TODO add your handling code here:
        cmdEdit.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_cmdEditDetMouseExited

    private void cmdEditDetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditDetActionPerformed
        // TODO add your handling code here:
        if (tblDetail.getSelectedRow()==0) {
            cls.showMsg("Harap pilih buku yang akan diubah !", "Hapus Gagal", 2);
            tblCariBuku.grabFocus();
            return;
        }        // TODO add your handling code here:
        isNew=false;
        cariBuku();
        cari_Buku.pack();
        cari_Buku.setModal(true);
        cari_Buku.setLocationRelativeTo(this);
        cari_Buku.setVisible(true);
    }//GEN-LAST:event_cmdEditDetActionPerformed

    private void cmdDelDetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdDelDetMouseEntered
        // TODO add your handling code here:
        cmdDelDet.setBackground(new Color(255,242,157));
    }//GEN-LAST:event_cmdDelDetMouseEntered

    private void cmdDelDetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdDelDetMouseExited
        // TODO add your handling code here:
        cmdDelDet.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_cmdDelDetMouseExited

    private void cmdDelDetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDelDetActionPerformed
        // TODO add your handling code here:
        if (tblDetail.getSelectedRow()==0) {
            cls.showMsg("Harap pilih buku yang akan dihapus !", "Hapus Gagal", 2);
            tblDetail.grabFocus();
            return;
        }
        String kd_bk=(String) tblDetail.getModel().getValueAt(tblDetail.getSelectedRow(), 0);
        String nm_buku=(String) tblDetail.getModel().getValueAt(tblDetail.getSelectedRow(), 1);
        int btn = JOptionPane.YES_NO_OPTION;
        int res=JOptionPane.showConfirmDialog(null,"Apakah anda yakin ingin "
            + "menghapus buku [" + kd_bk + " - " +
            nm_buku  + "] dari transaksi ini ?","Hapus",btn);
        if (res==JOptionPane.YES_OPTION){
            ((DefaultTableModel)tblDetail.getModel()).removeRow(tblDetail.getSelectedRow());
        }        
    }//GEN-LAST:event_cmdDelDetActionPerformed

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_formFocusLost

    private void txtCariKodeBarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKodeBarangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariKodeBarangKeyPressed

    private void cmdCariBarangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdCariBarangMouseEntered
        // TODO add your handling code here:
        cmdCariBarang.setOpaque(true);
    }//GEN-LAST:event_cmdCariBarangMouseEntered

    private void cmdCariBarangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdCariBarangMouseExited
        // TODO add your handling code here:
        cmdCariBarang.setOpaque(false);
    }//GEN-LAST:event_cmdCariBarangMouseExited

    private void cmdCariBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCariBarangActionPerformed
        cariBuku();
    }//GEN-LAST:event_cmdCariBarangActionPerformed

    private void cmdPilihBarangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdPilihBarangMouseEntered
        // TODO add your handling code here:
        cmdPilihBarang.setOpaque(true);
    }//GEN-LAST:event_cmdPilihBarangMouseEntered

    private void cmdPilihBarangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdPilihBarangMouseExited
        // TODO add your handling code here:
        cmdPilihBarang.setOpaque(false);
    }//GEN-LAST:event_cmdPilihBarangMouseExited

    private void cmdPilihBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPilihBarangActionPerformed
        // TODO add your handling code here:
        if (tblCariBuku.getRowCount()==0) {
            cls.showMsg("Harap pilih buku yang akan di tambahkan !", "Cari Data", 0);
            txtCariKodeBarang.grabFocus();
            return;
        }
        String kd_bk=(String) tblCariBuku.getModel().getValueAt(tblCariBuku.getSelectedRow(), 0);
        String nm_buku=(String) tblCariBuku.getModel().getValueAt(tblCariBuku.getSelectedRow(), 1);
        DefaultTableModel model=(DefaultTableModel) tblDetail.getModel();
        if (isNew) {
            Vector<Object> rowData = new Vector<Object>();
            rowData.add(kd_bk);
            rowData.add(nm_buku);
            rowData.add(txtQty.getText());
            model.addRow(rowData);
        }
        else
        {
            model.setValueAt((String) tblCariBuku.getModel().getValueAt(tblCariBuku.getSelectedRow(), 0), tblDetail.getSelectedRow(),  0);
        }
        cari_Buku.setVisible(false);
    }//GEN-LAST:event_cmdPilihBarangActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bgCariBarang;
    private javax.swing.JDialog cari_Buku;
    private javax.swing.JCheckBox chkBukuKembali;
    private javax.swing.JButton cmdAdd;
    private javax.swing.JButton cmdAddDet;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdCariBarang;
    private javax.swing.JButton cmdClose;
    private javax.swing.JButton cmdDelDet;
    private javax.swing.JButton cmdDelete;
    private javax.swing.JButton cmdDisplay;
    private javax.swing.JButton cmdEdit;
    private javax.swing.JButton cmdEditDet;
    private javax.swing.JButton cmdPilihBarang;
    private javax.swing.JButton cmdSave;
    private javax.swing.JButton cmdSearch;
    private org.jdesktop.swingx.JXDatePicker dtpTglAkhir;
    private org.jdesktop.swingx.JXDatePicker dtpTglAwal;
    private org.jdesktop.swingx.JXDatePicker dtpTglKembali;
    private org.jdesktop.swingx.JXDatePicker dtpTglPinjam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlDetail;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JPanel pnlSearchDialog;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JTable tblCariBuku;
    private javax.swing.JTable tblDetail;
    private javax.swing.JTable tblTransaksi;
    private javax.swing.JTextField txtCariKodeBarang;
    private javax.swing.JTextField txtCariNamaBarang;
    private javax.swing.JTextField txtDokumen;
    private javax.swing.JTextField txtIDMember;
    private javax.swing.JTextField txtNamaMember;
    private javax.swing.JTextField txtQty;
    // End of variables declaration//GEN-END:variables
}
