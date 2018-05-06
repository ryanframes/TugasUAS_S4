/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author ZSOFT_DEV
 */
public class clsConnection {
    
    public static String serverIP="localhost";
    public static String serverPort="3306";
    public static String serverUser="root";
    public static String serverPass="";
    public static String serverDB="db_perpustakaan";
    private static Connection con;
    
    public boolean getConnection() {
        if (con == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con =DriverManager.getConnection("jdbc:mysql://"+ serverIP +":"+ 
                    serverPort +"/" + serverDB, serverUser, serverPass);
            } catch (ClassNotFoundException | SQLException ex) {
                ex.getMessage();
            }
        }
        return (con!=null);
    }
    // Send data TO Database
    public void setData(String sql) throws SQLException {
        try {
            if (getConnection()) {
                con.createStatement().executeUpdate(sql);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(), "setData", 0);;
        }
        finally {
            disconnect();
        }
    }
    
    // Get Data From Database
    public ResultSet getData(String sql){
        ResultSet rs=null;
        try {
            if (getConnection()) {
                rs=con.createStatement().executeQuery(sql);   
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return rs;
    }
    
    public void disconnect() throws SQLException {
        if (con != null) {
            con.close();
            con=null;
        }
    }
    
}
