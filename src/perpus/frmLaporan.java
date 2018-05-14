/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.TitledBorder;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author MOZART-PC
 */
public class frmLaporan extends javax.swing.JInternalFrame {
    clsFunctions cls = new clsFunctions();
    clsConnection oConn = new clsConnection();
    /**
     * Creates new form frmLaporan
     */
    public frmLaporan() {
        initComponents();
        LocalDate today=LocalDate.now(); //first date of month
        cls.setDateVal(dtpAwal, java.sql.Date.valueOf(today.withDayOfMonth(1)));
        cls.setDateVal(dtpAkhir, new Date());
        
        //make datepicker editable = false
        dtpAkhir.getEditor().setEditable(false);
        dtpAwal.getEditor().setEditable(false);
        
        cmdDisplay.doClick();
    }

    private void displayTrans() {
        try {
            cls.showTblGrid(tblHeader, "select no_dok as 'No. Dokumen', tgl_pinjam as 'Tgl Pinjam', "
                    + "p.id_member as 'ID Member', m.nama as 'Nama Member', tgl_kembali as 'Tgl Kembali', "
                    + "jml_buku as 'Jml. Buku' from t_pinjam p join t_member m on p.id_member = m.id_member "
                    + "where tgl_pinjam between '" + cls.getDateVal(dtpAwal) + " 00:00:00' and "
                    + "'" + cls.getDateVal(dtpAkhir) + " 23:59:59'");
            tblHeader.setEditingColumn(0);
            tblHeader.setEditingRow(0);
            displayDet();
        } catch (SQLException ex) {
            cls.showMsg("Error saat menampilkan detail buku.","Tampil Data Buku", 2);
        }
    }
            
    private void displayDet() {
        String noDok = "";
        if (tblHeader.getSelectedRow()>=0) {
            noDok=(String) tblHeader.getValueAt(tblHeader.getSelectedRow(), 0);
        }
        try {
            Font fn=new Font(jLabel4.getFont().getFontName(), jLabel4.getFont().getStyle(), jLabel4.getFont().getSize());
            TitledBorder border = new TitledBorder("Detail Dokumen : " + noDok);
            border.setTitleColor(Color.white);
            border.setTitleFont(fn);
            pnlDetail.setBorder(border);
            cls.showTblGrid(tblDetail, "select d.kd_buku as 'Kode Buku', nm_buku as 'Nama Buku' "
                    + "from t_pinjam_det d join t_buku b on b.kd_buku = d.kd_buku "
                    + "where no_dok ='" + noDok + "'");
        } catch (SQLException ex) {
            cls.showMsg("Error saat menampilkan detail buku.","Tampil Data Buku", 2);
        }
    }
    
    private void printReport() {
        try {
            String periode="Periode : " + cls.getDateVal(dtpAwal,"dd MMM yyyy") + 
                " s/d " + cls.getDateVal(dtpAkhir, "dd MMM yyyy");
            String tgl_awal = cls.getDateVal(dtpAwal);
            String tgl_akhir = cls.getDateVal(dtpAkhir);
            String fName = "src/perpus/rptLaporan.jasper";
            HashMap param = new HashMap();
            param.put("headerPeriode", periode);
            param.put("tgl_awal", tgl_awal);
            param.put("tgl_akhir", tgl_akhir);
            JasperPrint print = JasperFillManager.fillReport(fName, param, oConn.getDbCon());
            JasperViewer.viewReport(print,false);
        } catch (JRException ex) {
            Logger.getLogger(frmLaporan.class.getName()).log(Level.SEVERE, null, ex);
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

        pnlForm = new javax.swing.JPanel();
        pnlTitle = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHeader = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        cmdDisplay = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        dtpAwal = new org.jdesktop.swingx.JXDatePicker();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dtpAkhir = new org.jdesktop.swingx.JXDatePicker();
        pnlDetail = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDetail = new javax.swing.JTable();
        pnlBottom = new javax.swing.JPanel();
        cmdPrint = new javax.swing.JButton();
        cmdClose = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        pnlForm.setBackground(new java.awt.Color(30, 144, 255));

        pnlTitle.setBackground(new java.awt.Color(255, 255, 255));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitle.setText("Laporan Pinjam Buku");
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

        tblHeader.setModel(new javax.swing.table.DefaultTableModel(
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
        tblHeader.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHeaderMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHeader);

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
                        .addComponent(dtpAwal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(dtpAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(dtpAwal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(dtpAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlDetail.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detail Dokumen Pinjam Buku", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlDetail.setForeground(new java.awt.Color(255, 255, 255));
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

        javax.swing.GroupLayout pnlDetailLayout = new javax.swing.GroupLayout(pnlDetail);
        pnlDetail.setLayout(pnlDetailLayout);
        pnlDetailLayout.setHorizontalGroup(
            pnlDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        pnlDetailLayout.setVerticalGroup(
            pnlDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
        );

        pnlBottom.setBackground(new java.awt.Color(255, 255, 255));
        pnlBottom.setPreferredSize(new java.awt.Dimension(100, 55));

        cmdPrint.setBackground(new java.awt.Color(255, 242, 157));
        cmdPrint.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cmdPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/Img/printer_16.png"))); // NOI18N
        cmdPrint.setText("Cetak");
        cmdPrint.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        cmdPrint.setContentAreaFilled(false);
        cmdPrint.setMaximumSize(new java.awt.Dimension(87, 27));
        cmdPrint.setMinimumSize(new java.awt.Dimension(87, 27));
        cmdPrint.setPreferredSize(new java.awt.Dimension(87, 27));
        cmdPrint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdPrintMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdPrintMouseExited(evt);
            }
        });
        cmdPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPrintActionPerformed(evt);
            }
        });

        cmdClose.setBackground(new java.awt.Color(255, 242, 157));
        cmdClose.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cmdClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/Img/Door Opened_16px.png"))); // NOI18N
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

        javax.swing.GroupLayout pnlBottomLayout = new javax.swing.GroupLayout(pnlBottom);
        pnlBottom.setLayout(pnlBottomLayout);
        pnlBottomLayout.setHorizontalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBottomLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(cmdClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBottomLayout.setVerticalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBottomLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlFormLayout = new javax.swing.GroupLayout(pnlForm);
        pnlForm.setLayout(pnlFormLayout);
        pnlFormLayout.setHorizontalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(pnlDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
        );
        pnlFormLayout.setVerticalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addComponent(pnlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(pnlDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
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

    private void tblHeaderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHeaderMouseClicked
        // TODO add your handling code here:
        displayDet();
    }//GEN-LAST:event_tblHeaderMouseClicked

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

    private void cmdPrintMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdPrintMouseEntered
        // TODO add your handling code here:
        cmdPrint.setOpaque(true);
    }//GEN-LAST:event_cmdPrintMouseEntered

    private void cmdPrintMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdPrintMouseExited
        // TODO add your handling code here:
        cmdPrint.setOpaque(false);
    }//GEN-LAST:event_cmdPrintMouseExited

    private void cmdPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPrintActionPerformed
        printReport();
    }//GEN-LAST:event_cmdPrintActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdClose;
    private javax.swing.JButton cmdDisplay;
    private javax.swing.JButton cmdPrint;
    private org.jdesktop.swingx.JXDatePicker dtpAkhir;
    private org.jdesktop.swingx.JXDatePicker dtpAwal;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlDetail;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JTable tblDetail;
    private javax.swing.JTable tblHeader;
    // End of variables declaration//GEN-END:variables

}
