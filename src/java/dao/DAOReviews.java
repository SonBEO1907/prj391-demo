package dao;

import JDBCConnect.DBConnect;
import entity.Reviews;
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
public class DAOReviews extends DBConnect{
   
    public int addReviews(Reviews re){
        int n = 0;
        String sql = "INSERT INTO [dbo].[reviews]\n" +
"           ([review_id]\n"+
"           ,[customer_id]\n" +
"           ,[product_id]\n" +                
"           ,[rating])\n" +
"           ,[review_text]\n" +       
"     VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, re.getReviewId());
            pre.setInt(2, re.getCustomerId());
            pre.setInt(3, re.getProductId());
            pre.setInt(4, re.getRating());
            pre.setString(5, re.getReviewText());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int UpdateReviews(Reviews re){
        int n = 0;
        String sql = "UPDATE [dbo].[reviews]\n" +
"   SET [customer_id] = ?\n" +
"      ,[product_id] = ?\n" +
"      ,[rating] = ?\n" +
"      ,[review_text] = ?\n" +            
" WHERE [review_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, re.getCustomerId());
            pre.setInt(2, re.getProductId());
            pre.setInt(3, re.getRating());
            pre.setString(4, re.getReviewText());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    /*
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
    */
    
    public int removeReviews(int ReviewId){
        int n = 0;
        String sql = "DELETE FROM reviews WHERE review_id = '" + ReviewId +"'";
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
