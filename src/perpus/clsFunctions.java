/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXDatePicker;


/**
 *
 * @author ZSOFT_DEV
 */
public class clsFunctions {
    public void showMsg(String msg, String msgTitle, int msgType) {
        /*0=Error, 1=Info msg, 2 = exclamation, 3=question*/
        JOptionPane.showMessageDialog(null, msg, msgTitle, msgType);
    }
    
    public void showCombo(JComboBox cmb,String sql) {
        //fill combo box with col index = 1
        clsConnection oConn = new clsConnection();
        try {
            cmb.removeAllItems();
            cmb.addItem("-Pilih-");
            ResultSet rs=oConn.getData(sql);
            while (rs.next()) {
                cmb.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(clsFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void showTblGrid(JTable obj, String sql) throws SQLException {
        clsConnection oConn = new clsConnection();
        DefaultTableModel modTbl = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row,int col) {
                return false;      
            }
        };
        
        ResultSet rs=oConn.getData(sql);
        ResultSetMetaData metaData=rs.getMetaData();
        
        // names of columns
        Vector<String> colNames = new Vector<String>();
        int colCount = metaData.getColumnCount();
        for (int i = 1; i <= colCount; i++) {
            colNames.add(metaData.getColumnLabel(i));
        }
        modTbl.setColumnIdentifiers(colNames);
        
        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> rowData = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= colCount; columnIndex++) {
                rowData.add(rs.getObject(columnIndex));
            }
            modTbl.addRow(rowData);
        }
        obj.setModel(modTbl);
    }
    
    public String getDateVal(JXDatePicker dtp) {
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        formater.format(dtp.getDate());
        String ret=formater.format(cal.getTime());
        return ret;
    }
    
    public void setDateVal(JXDatePicker dtp,Date vDate) {
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        dtp.setFormats(formater);
        dtp.setDate(vDate);
    }
}
