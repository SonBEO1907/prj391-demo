package dao;

import JDBCConnect.DBConnect;
import entity.Categories;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author os
 */
public class DAOCategories extends DBConnect{
   
    public int addCategories(Categories cate){
        int n = 0;
        String sql = "INSERT INTO [dbo].[categories]\n" +
"           ([category_id]\n"+
"           ,[category_name]\n" +
"           ,[product_id])\n" +
"     VALUES(?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, cate.getCategoryId());
            pre.setString(2, cate.getCategoryName());
            pre.setInt(3, cate.getProductId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int UpdateCategories(Categories cate){
        int n = 0;
        String sql = "UPDATE [dbo].[categories]\n" +
"   SET [category_name] = ?\n" +
"      ,[product_id] = ?\n" +                
" WHERE [category_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cate.getCategoryName());
            pre.setInt(2, cate.getProductId());
            pre.setInt(3, cate.getCategoryId()); 
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    /*
    public Vector<Admin> getAllCate(String sql){
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
    */
    
    public int removeCate(int CategoryId){
        int n = 0;
        String sql = "Delete from categories where category_id = '" + CategoryId +"'";
        Statement state;
        try {
            state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

}

