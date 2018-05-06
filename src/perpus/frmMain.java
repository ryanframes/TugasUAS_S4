/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus;

import java.awt.Component;
import java.awt.Dimension;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import sun.java2d.SunGraphicsEnvironment;

/**
 *
 * @author ZSOFT_DEV
 */
public class frmMain extends javax.swing.JFrame {
    /**
     * Creates new form frmMain
     */
    clsFunctions cls = new clsFunctions();
    int mpX, mpY;
    Dimension defDim;
    frmBuku buku = new frmBuku();
    frmHome home = new frmHome();
    frmMaster master = new frmMaster(this);
    frmUser user = new frmUser();
    frmMember member = new frmMember();
    frmTransaksi transaksi = new frmTransaksi();

    public frmMain() {
        this.setUndecorated(true); //set jframe without titlebar
        initComponents();
        
        //set position to center
        this.pack(); 
        defDim=this.getSize();
        setLocationRelativeTo(null); 
        
        //function to make this form resizable
        ComponentResizer cr=new ComponentResizer();
        cr.registerComponent(this);
        cr.setMaximumSize(this.getMaximumSize());
        cr.setMinimumSize(this.getMinimumSize());
        
        //get maximum bound/screen size dari device yg digunakan
        GraphicsConfiguration config = this.getGraphicsConfiguration();
        Rectangle usableBounds = SunGraphicsEnvironment.getUsableBounds(config.getDevice()); 
        
        //make this form maximize berdasarkan maximal bound screen yg sudah di dapat
        setMaximizedBounds(usableBounds);
        cmdMaximizeForm.doClick(); //panggil fungsi maximize form

        //show home form
        cmdHome.doClick();
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
        cmdMinimizeForm = new javax.swing.JButton();
        cmdMaximizeForm = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlMenu = new javax.swing.JPanel();
        btnLogo = new javax.swing.JButton();
        cmdHome = new javax.swing.JButton();
        cmdMaster = new javax.swing.JButton();
        cmdTrans = new javax.swing.JButton();
        cmdReport = new javax.swing.JButton();
        cmdExit = new javax.swing.JButton();
        pnlFooter = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlChildForm = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlForm.setBackground(new java.awt.Color(255, 255, 255));
        pnlForm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pnlTitle.setBackground(new java.awt.Color(41, 57, 86));
        pnlTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitle.setText("SIPustaka - Sistem Informasi Perpustakaan");
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

        cmdMinimizeForm.setBackground(new java.awt.Color(204, 204, 204));
        cmdMinimizeForm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/Img/minimize-window-20.png"))); // NOI18N
        cmdMinimizeForm.setContentAreaFilled(false);
        cmdMinimizeForm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdMinimizeFormMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdMinimizeFormMouseExited(evt);
            }
        });
        cmdMinimizeForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdMinimizeFormActionPerformed(evt);
            }
        });

        cmdMaximizeForm.setBackground(new java.awt.Color(204, 204, 204));
        cmdMaximizeForm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/Img/maximize-window-20.png"))); // NOI18N
        cmdMaximizeForm.setContentAreaFilled(false);
        cmdMaximizeForm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdMaximizeFormMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdMaximizeFormMouseExited(evt);
            }
        });
        cmdMaximizeForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdMaximizeFormActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTitleLayout = new javax.swing.GroupLayout(pnlTitle);
        pnlTitle.setLayout(pnlTitleLayout);
        pnlTitleLayout.setHorizontalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addContainerGap(156, Short.MAX_VALUE)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdMinimizeForm, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cmdMaximizeForm, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cmdCloseForm, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlTitleLayout.setVerticalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
            .addGroup(pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(cmdCloseForm, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdMaximizeForm, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdMinimizeForm, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        pnlMenu.setBackground(new java.awt.Color(255, 255, 255));

        btnLogo.setBackground(new java.awt.Color(255, 255, 255));
        btnLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/Img/RnD-Logo_160.png"))); // NOI18N
        btnLogo.setContentAreaFilled(false);
        btnLogo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLogo.setOpaque(true);

        cmdHome.setBackground(new java.awt.Color(255, 242, 157));
        cmdHome.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cmdHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/Img/home_window-20.png"))); // NOI18N
        cmdHome.setText("Halaman Utama");
        cmdHome.setContentAreaFilled(false);
        cmdHome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdHome.setIconTextGap(14);
        cmdHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdHomeMouseExited(evt);
            }
        });
        cmdHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdHomeActionPerformed(evt);
            }
        });

        cmdMaster.setBackground(new java.awt.Color(255, 242, 157));
        cmdMaster.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cmdMaster.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/Img/documents-20.png"))); // NOI18N
        cmdMaster.setText("Master Data");
        cmdMaster.setContentAreaFilled(false);
        cmdMaster.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdMaster.setIconTextGap(14);
        cmdMaster.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdMasterMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdMasterMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cmdMasterMousePressed(evt);
            }
        });
        cmdMaster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdMasterActionPerformed(evt);
            }
        });

        cmdTrans.setBackground(new java.awt.Color(255, 242, 157));
        cmdTrans.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cmdTrans.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/Img/user-manual-20.png"))); // NOI18N
        cmdTrans.setText("Transaksi");
        cmdTrans.setContentAreaFilled(false);
        cmdTrans.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdTrans.setIconTextGap(14);
        cmdTrans.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdTransMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdTransMouseExited(evt);
            }
        });
        cmdTrans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdTransActionPerformed(evt);
            }
        });

        cmdReport.setBackground(new java.awt.Color(255, 242, 157));
        cmdReport.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cmdReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/Img/Transaction_20.png"))); // NOI18N
        cmdReport.setText("Laporan");
        cmdReport.setContentAreaFilled(false);
        cmdReport.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdReport.setIconTextGap(14);
        cmdReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdReportMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdReportMouseExited(evt);
            }
        });

        cmdExit.setBackground(new java.awt.Color(255, 242, 157));
        cmdExit.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cmdExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpus/Img/shutdown-20 (2).png"))); // NOI18N
        cmdExit.setText("Keluar");
        cmdExit.setContentAreaFilled(false);
        cmdExit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdExit.setIconTextGap(14);
        cmdExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmdExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmdExitMouseExited(evt);
            }
        });
        cmdExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cmdHome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLogo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                    .addComponent(cmdMaster, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdTrans, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdReport, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdExit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addComponent(btnLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdHome, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdMaster, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdTrans, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdReport, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(cmdExit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jScrollPane1.setViewportView(pnlMenu);

        pnlFooter.setBackground(new java.awt.Color(41, 57, 86));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Copyright © 2018 Kelompok 3 PVII AMIK BSI. All Rights Reserved");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlFooterLayout = new javax.swing.GroupLayout(pnlFooter);
        pnlFooter.setLayout(pnlFooterLayout);
        pnlFooterLayout.setHorizontalGroup(
            pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlFooterLayout.setVerticalGroup(
            pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlChildFormLayout = new javax.swing.GroupLayout(pnlChildForm);
        pnlChildForm.setLayout(pnlChildFormLayout);
        pnlChildFormLayout.setHorizontalGroup(
            pnlChildFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlChildFormLayout.setVerticalGroup(
            pnlChildFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlFormLayout = new javax.swing.GroupLayout(pnlForm);
        pnlForm.setLayout(pnlFormLayout);
        pnlFormLayout.setHorizontalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(pnlTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(pnlChildForm))
            .addComponent(pnlFooter, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlFormLayout.setVerticalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addComponent(pnlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                    .addComponent(pnlChildForm))
                .addComponent(pnlFooter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    public void showMasterMember() {
        showChildForm(member);
    }
    
    public void showMasterUser() {
        //frmUser frm = new frmUser();
        showChildForm(user);
    }
    
    public void showMasterBuku() {
        //frmBuku frm = new frmBuku();
        showChildForm(buku);
    }
    private void showChildForm(JInternalFrame frm) {
         /*Cek if JInternalFrame sudah di tambahkan ke pnlChildForm atau belum
        Ref : https://stackoverflow.com/questions/859691/a-fast-way-to-determine-whether-a-componet-is-found-in-jpanel
        Logika : 
        1. cek apakah komponen sudah di tambahkan ke pnlChildForm
        2. Jika sudah ada di pnlChildform lgsg di set selected = true dan visible = true
        3. Jika belum ada di pnlChildForm add component baru ke pnlChildForm
         */
         boolean loaded=false;
         try {
            Component[] components = pnlChildForm.getComponents();
            for (Component component : components) {
                if (frm==component) {
                    component.setVisible(true);
                    frm.setSelected(true);
                    loaded=true;
                }
            }
            if (!loaded) {
                ((BasicInternalFrameUI)frm.getUI()).setNorthPane(null);
                pnlChildForm.add(frm);
                frm.setMaximumSize(pnlChildForm.getSize());
                frm.setVisible(true);
                try {
                 frm.setMaximum(true);
                 pnlChildForm.setComponentZOrder(frm, 0);
                    } catch (Exception e) {
                }
            }
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
    }

    private void lblTitleMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTitleMouseDragged
        //set JFframe Location when dragging mouse
        if (this.getExtendedState()!=MAXIMIZED_BOTH)
        this.setLocation(
              getLocation().x + evt.getX() - mpX,
              getLocation().y + evt.getY() - mpY );
    }//GEN-LAST:event_lblTitleMouseDragged

    private void lblTitleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTitleMousePressed
        // store mouse pos x&y to variable
        mpX=evt.getX();
        mpY=evt.getY();
    }//GEN-LAST:event_lblTitleMousePressed

    private void cmdMinimizeFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdMinimizeFormActionPerformed
        // set this frame to iconified (minimized):
        this.setState(ICONIFIED);
    }//GEN-LAST:event_cmdMinimizeFormActionPerformed

    private void cmdCloseFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseFormActionPerformed
        // dispose the form
        this.dispose();
    }//GEN-LAST:event_cmdCloseFormActionPerformed

    private void cmdMinimizeFormMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdMinimizeFormMouseEntered
        // TODO add your handling code here:
        cmdMinimizeForm.setOpaque(true);
    }//GEN-LAST:event_cmdMinimizeFormMouseEntered

    private void cmdMinimizeFormMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdMinimizeFormMouseExited
        // TODO add your handling code here:
        cmdMinimizeForm.setOpaque(false);
    }//GEN-LAST:event_cmdMinimizeFormMouseExited

    private void cmdCloseFormMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdCloseFormMouseEntered
        // TODO add your handling code here:
        cmdCloseForm.setOpaque(true);
    }//GEN-LAST:event_cmdCloseFormMouseEntered

    private void cmdCloseFormMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdCloseFormMouseExited
        // TODO add your handling code here:
        cmdCloseForm.setOpaque(false);
    }//GEN-LAST:event_cmdCloseFormMouseExited

    private void cmdMaximizeFormMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdMaximizeFormMouseEntered
        // TODO add your handling code here:
        cmdMaximizeForm.setOpaque(true);
    }//GEN-LAST:event_cmdMaximizeFormMouseEntered

    private void cmdMaximizeFormMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdMaximizeFormMouseExited
        // TODO add your handling code here:
        cmdMaximizeForm.setOpaque(false);
    }//GEN-LAST:event_cmdMaximizeFormMouseExited

    private void cmdMaximizeFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdMaximizeFormActionPerformed
        // TODO add your handling code here:
        if (getSize().width==defDim.width) {
            this.setExtendedState(MAXIMIZED_BOTH);
            this.setSize(getSize().width, getSize().height);
        }
        else
        {
            this.setExtendedState(NORMAL);
            this.setSize(defDim);
            setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_cmdMaximizeFormActionPerformed

    private void cmdHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdHomeMouseEntered
        // TODO add your handling code here:
        cmdHome.setOpaque(true);
    }//GEN-LAST:event_cmdHomeMouseEntered

    private void cmdExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExitActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cmdExitActionPerformed

    private void cmdHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdHomeActionPerformed
        //frmHome home = new frmHome();
        showChildForm(home);
    }//GEN-LAST:event_cmdHomeActionPerformed

    private void cmdHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdHomeMouseExited
        // TODO add your handling code here:
        cmdHome.setOpaque(false);
    }//GEN-LAST:event_cmdHomeMouseExited

    private void cmdMasterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdMasterMouseEntered
        // TODO add your handling code here:
        cmdMaster.setOpaque(true);
    }//GEN-LAST:event_cmdMasterMouseEntered

    private void cmdMasterMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdMasterMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdMasterMousePressed

    private void cmdMasterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdMasterMouseExited
        // TODO add your handling code here:
        cmdMaster.setOpaque(false);
    }//GEN-LAST:event_cmdMasterMouseExited

    private void cmdTransMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdTransMouseEntered
        // TODO add your handling code here:
        cmdTrans.setOpaque(true);
    }//GEN-LAST:event_cmdTransMouseEntered

    private void cmdTransMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdTransMouseExited
        // TODO add your handling code here:
        cmdTrans.setOpaque(false);
    }//GEN-LAST:event_cmdTransMouseExited

    private void cmdReportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdReportMouseEntered
        // TODO add your handling code here:
        cmdReport.setOpaque(true);
    }//GEN-LAST:event_cmdReportMouseEntered

    private void cmdReportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdReportMouseExited
        // TODO add your handling code here:
        cmdReport.setOpaque(false);
    }//GEN-LAST:event_cmdReportMouseExited

    private void cmdExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdExitMouseEntered
        // TODO add your handling code here:
        cmdExit.setOpaque(true);
    }//GEN-LAST:event_cmdExitMouseEntered

    private void cmdExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdExitMouseExited
        // TODO add your handling code here:
        cmdExit.setOpaque(false);
    }//GEN-LAST:event_cmdExitMouseExited

    private void cmdMasterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdMasterActionPerformed
        // TODO add your handling code here:
        //frmMaster master = new frmMaster(this);
        showChildForm(master);
    }//GEN-LAST:event_cmdMasterActionPerformed

    private void cmdTransActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdTransActionPerformed
        // TODO add your handling code here:
        showChildForm(transaksi);
    }//GEN-LAST:event_cmdTransActionPerformed
    
    /**
     * @param args the command line arguments
     */
    
    public void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogo;
    private javax.swing.JButton cmdCloseForm;
    private javax.swing.JButton cmdExit;
    private javax.swing.JButton cmdHome;
    private javax.swing.JButton cmdMaster;
    private javax.swing.JButton cmdMaximizeForm;
    private javax.swing.JButton cmdMinimizeForm;
    private javax.swing.JButton cmdReport;
    private javax.swing.JButton cmdTrans;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JDesktopPane pnlChildForm;
    private javax.swing.JPanel pnlFooter;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlTitle;
    // End of variables declaration//GEN-END:variables
}
