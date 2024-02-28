package dao;

import JDBCConnect.DBConnect;
import entity.Customers;
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
public class DAOCustomers extends DBConnect{
   
    public int addCustomers(Customers cus){
        int n = 0;
        String sql = "INSERT INTO [dbo].[customers]\n" +
"           ([first_name]\n" +
"           ,[last_name]\n" +                
"           ,[email]\n" +
"           ,[password]\n" +
"           ,[username])\n" +            
"     VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getFirstName());
            pre.setString(2, cus.getLastName());
            pre.setString(3, cus.getEmail());
            pre.setString(4, cus.getPassword());
            pre.setString(5, cus.getUsername());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int UpdateCustomers(Customers cus){
        int n = 0;
        String sql = "UPDATE [dbo].[customers]\n" +
"   SET [first_name] = ?\n" +
"      ,[last_name] = ?\n" +
"      ,[email] = ?\n" +
"      ,[password] = ?\n" +
"      ,[username] = ?\n" +              
" WHERE [customer_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getFirstName());
            pre.setString(2, cus.getLastName());
            pre.setString(3, cus.getEmail());
            pre.setString(4, cus.getPassword());
            pre.setString(5, cus.getUsername());
            pre.setInt(6, cus.getCustomerId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
   public Vector<Customers> getAllCustomers(String sql){
       Vector<Customers> vector = new Vector<Customers>();
       ResultSet rs = this.getData(sql);
       try {
           int cid = rs.getInt("customer_id");
           String fname = rs.getString("first_name");
           String lname = rs.getString("last_name");
           String email = rs.getString("email");
           String username = rs.getString("username");
           String password = rs.getString("password");
           Customers cus = new Customers(cid, fname, lname, email, password, username);
           vector.add(cus);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return vector;
   }
    
    public int removeCustomers(int CustomerId){
        int n = 0;
        String sql = "DELETE FROM customers WHERE customer_id = '" + CustomerId +"'";
        Statement state;
        try {
            ResultSet sqlCheck1 = this.getData("Select * from reviews where customer_id ='"+CustomerId+"'");
            ResultSet sqlCheck2 = this.getData("Select * from orders where customer_id ='"+CustomerId+"'");
            if (sqlCheck1.next() || sqlCheck2.next()){
                n=-1;
            }else{
                 state = conn.createStatement();
                n = state.executeUpdate(sql); 
            }                           
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

}
