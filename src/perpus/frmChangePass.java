/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ZSOFT_DEV
 */
public class frmChangePass extends javax.swing.JFrame {
    int mpX, mpY;
    clsFunctions cls = new clsFunctions();
    clsConnection oConn = new clsConnection();
    /**
     * Creates new form frmChangePass
     */
    public frmChangePass() {
        setUndecorated(true);
        initComponents();
        this.pack();
        this.setLocationRelativeTo(null);
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
        cmdCloseForm = new javax.swing.JButton();
        lblIcon = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtPwdLama = new javax.swing.JPasswordField();
        txtPwdBaru = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        txtPwdKonfirmasi = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        pnlBottom = new javax.swing.JPanel();
        cmdSave = new javax.swing.JButton();
        cmdClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlForm.setBackground(new java.awt.Color(255, 255, 255));
        pnlForm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlForm.setDoubleBuffered(false);

        pnlTitle.setBackground(new java.awt.Color(41, 57, 86));
        pnlTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitle.setText("Ganti Password");
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

        cmdCloseForm.setBackground(new java.awt.Color(231, 92, 92));
        cmdCloseForm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/Img/close-window-20.png"))); // NOI18N
        cmdCloseForm.setContentAreaFilled(false);
        cmdCloseForm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdCloseFormMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdCloseFormMouseExited(evt);
            }
        });
        cmdCloseForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCloseFormActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTitleLayout = new javax.swing.GroupLayout(pnlTitle);
        pnlTitle.setLayout(pnlTitleLayout);
        pnlTitleLayout.setHorizontalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdCloseForm, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlTitleLayout.setVerticalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
            .addComponent(cmdCloseForm)
        );

        lblIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIcon.setIcon(new javax.swing.ImageIcon("E:\\VBSource\\Icons_Images\\Image\\key.png")); // NOI18N
        lblIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Password Lama");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtPwdLama.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        txtPwdBaru.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Password Baru");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtPwdKonfirmasi.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Konfirmasi Password Baru");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        pnlBottom.setBackground(new java.awt.Color(41, 57, 86));
        pnlBottom.setPreferredSize(new java.awt.Dimension(100, 55));

        cmdSave.setBackground(new java.awt.Color(255, 255, 255));
        cmdSave.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cmdSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/Img/apply_gui_16.png"))); // NOI18N
        cmdSave.setText("Simpan");
        cmdSave.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        cmdSave.setContentAreaFilled(false);
        cmdSave.setMaximumSize(new java.awt.Dimension(87, 27));
        cmdSave.setMinimumSize(new java.awt.Dimension(87, 27));
        cmdSave.setOpaque(true);
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

        cmdClose.setBackground(new java.awt.Color(255, 255, 255));
        cmdClose.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cmdClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/Img/Door Opened_16px.png"))); // NOI18N
        cmdClose.setText("Keluar");
        cmdClose.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        cmdClose.setContentAreaFilled(false);
        cmdClose.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmdClose.setMaximumSize(new java.awt.Dimension(87, 27));
        cmdClose.setMinimumSize(new java.awt.Dimension(87, 27));
        cmdClose.setOpaque(true);
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
                .addComponent(cmdSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(cmdClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBottomLayout.setVerticalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBottomLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlFormLayout = new javax.swing.GroupLayout(pnlForm);
        pnlForm.setLayout(pnlFormLayout);
        pnlFormLayout.setHorizontalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPwdLama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                    .addComponent(txtPwdBaru)
                    .addComponent(txtPwdKonfirmasi))
                .addContainerGap())
            .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
        );
        pnlFormLayout.setVerticalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addComponent(pnlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlFormLayout.createSequentialGroup()
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPwdLama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPwdBaru, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPwdKonfirmasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
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
        //set JFframe Location when dragging mouse
        this.setLocation(
            getLocation().x + evt.getX() - mpX,
            getLocation().y + evt.getY() - mpY );
    }//GEN-LAST:event_lblTitleMouseDragged

    private void lblTitleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTitleMousePressed
        // store mouse pos x&y to variable
        mpX=evt.getX();
        mpY=evt.getY();
    }//GEN-LAST:event_lblTitleMousePressed

    private void cmdCloseFormMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdCloseFormMouseEntered
        // TODO add your handling code here:
        cmdCloseForm.setOpaque(true);
    }//GEN-LAST:event_cmdCloseFormMouseEntered

    private void cmdCloseFormMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdCloseFormMouseExited
        // TODO add your handling code here:
        cmdCloseForm.setOpaque(false);
    }//GEN-LAST:event_cmdCloseFormMouseExited

    private void cmdCloseFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseFormActionPerformed
        // dispose the form
        this.dispose();
    }//GEN-LAST:event_cmdCloseFormActionPerformed

    private void cmdSaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdSaveMouseEntered
        // TODO add your handling code here:
        cmdSave.setBackground(new Color(255,242,157));
    }//GEN-LAST:event_cmdSaveMouseEntered

    private void cmdSaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdSaveMouseExited
        // TODO add your handling code here:
        cmdSave.setBackground(Color.white);
    }//GEN-LAST:event_cmdSaveMouseExited

    private void cmdSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSaveActionPerformed
        try {
            String pwLama=String.valueOf(txtPwdLama.getPassword());
            String pwBaru=String.valueOf(txtPwdBaru.getPassword());
            String pwKonfirm=String.valueOf(txtPwdKonfirmasi.getPassword());
            
            if (!pwBaru.equals(pwKonfirm)) {
                cls.showMsg("Password Baru dan Konfirmasi Password tidak sesuai", "Ubah Password", 0);
                txtPwdBaru.grabFocus();
                return;
            }
            
            ResultSet rs= oConn.getData("select * from t_user where nm_user = '" + cls.getPubUserName() + "'");
            if (rs.next()) {
                if (!rs.getString("password").toString().equals(pwLama)) {
                    cls.showMsg("Password Lama salah", "Ubah Password", 0);
                    txtPwdLama.grabFocus();
                    return;
                }
                int btn = JOptionPane.YES_NO_OPTION;
                int res=JOptionPane.showConfirmDialog(null,"Apakah anda yakin ingin "
                    + "mengubah password anda ?","Ubah Password",3);
                if (res==JOptionPane.YES_OPTION){
                    //update password here
                    oConn.setData("update t_user set password = '" + String.valueOf(txtPwdBaru.getPassword()) + "' "
                            + "where nm_user = '" + cls.getPubUserName() + "'");
                    cls.showMsg("Password Berhasil di ubah", "Berhasil", 1);
                }
            }
        } catch (SQLException ex) {
            cls.showMsg("Ubah Password Gagal !/n" + ex.getMessage(), "Ubah Password", 0);
        }
    }//GEN-LAST:event_cmdSaveActionPerformed

    private void cmdCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdCloseMouseEntered
        // TODO add your handling code here:
        cmdClose.setBackground(new Color(255,242,157));
    }//GEN-LAST:event_cmdCloseMouseEntered

    private void cmdCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdCloseMouseExited
        // TODO add your handling code here:
        cmdClose.setBackground(Color.white);
    }//GEN-LAST:event_cmdCloseMouseExited

    private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cmdCloseActionPerformed

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
            java.util.logging.Logger.getLogger(frmChangePass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmChangePass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmChangePass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmChangePass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmChangePass().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdClose;
    private javax.swing.JButton cmdCloseForm;
    private javax.swing.JButton cmdSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JPasswordField txtPwdBaru;
    private javax.swing.JPasswordField txtPwdKonfirmasi;
    private javax.swing.JPasswordField txtPwdLama;
    // End of variables declaration//GEN-END:variables
}
