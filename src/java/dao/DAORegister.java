/*
 * 
 * 
 */
package dao;
import JDBCConnect.DBConnect;
import dao.DAOCustomers;
import entity.Customers;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author os
 */
public class DAORegister extends JDBCConnect.DBConnect{

    public int registerNewCustomer(Customers cus){
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
 
}
