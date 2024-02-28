package dao;

import JDBCConnect.DBConnect;
import entity.Admin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOAdmin extends DBConnect{
   
    public int addAdmin(Admin admin){
        int n = 0;
        String sql = "INSERT INTO [dbo].[admin]\n" +
"           ([admin_id]\n"+
"           ,[username]\n" +
"           ,[password])\n" +
"     VALUES(?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, admin.getAdminId());
            pre.setString(2, admin.getUsername());
            pre.setString(3, admin.getPassword());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int UpdateAdmin(Admin admin){
        int n = 0;
        String sql = "UPDATE [dbo].[admin]\n" +
"   SET [password] = ?\n" +
" WHERE [username] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, admin.getPassword());
            pre.setString(2, admin.getUsername());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public Vector<Admin> getAllAdmin(String sql){
        Vector<Admin> vector = new Vector<Admin>();
        ResultSet rs = this.getData(sql);
        try {
            while(rs.next()){
                int adminId = rs.getInt("admin_id");
                String admin = rs.getString("username");
                String password = rs.getString("password");
                Admin Admin = new Admin(adminId, admin, password);
                vector.add(Admin);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public int removeAdmin(String username){
        int n = 0;
        String sql = "Delete from admin where username = '" + username+"'";
        Statement state;
        try {
            state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

}
