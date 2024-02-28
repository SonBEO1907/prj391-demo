/*
 * 
 * 
 */
package dao;
import JDBCConnect.DBConnect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import entity.Customers;
/**
 *
 * @author os
 */
public class DAOLogin extends DBConnect{
        
        public Customers login(String user, String pass){
            String sql = "Select * from Customers where username = ? and password=?";
            PreparedStatement pre;
            try {
                pre = conn.prepareStatement(sql);
                pre.setString(1, user);
                pre.setString(2, pass);
                ResultSet rs = pre.executeQuery();
                if(rs.next()){
                    return new Customers(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        } 
        
    public String getCustomerByUserName(String user){
            String sql = "Select *from Customers where username='"+user+"'";
            PreparedStatement pre;
            try {
                pre= conn.prepareStatement(sql);
                ResultSet rs = pre.executeQuery();
                rs.next();
                String name = rs.getString(2) + " " +rs.getString(3);
                if (name != null){
                    return name;
                }
        }catch (Exception e) {
            e.printStackTrace();
        }
            return null;
    }  
    
    public boolean loginAdmin(String user, String pass){
            String sql = "Select * from admin where username = ? and password=?";
            PreparedStatement pre;
            try {
                pre = conn.prepareStatement(sql);
                pre.setString(1, user);
                pre.setString(2, pass);
                ResultSet rs = pre.executeQuery();
                if(rs.next()){
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    
    public String getAdminByUserName(String user){
            String sql = "Select *from admin where username='"+user+"'";
            PreparedStatement pre;
            try {
                pre= conn.prepareStatement(sql);
                ResultSet rs = pre.executeQuery();
                rs.next();
                String name = rs.getString(2);
                if (name != null){
                    return name;
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
            return null;
    }  
}
