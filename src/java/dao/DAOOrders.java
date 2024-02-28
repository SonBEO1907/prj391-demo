package dao;

import JDBCConnect.DBConnect;
import entity.Orders;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import dao.DAOOrderDetails;
/**
 *
 * @author os
 */
public class DAOOrders extends DBConnect{
   
    public int addOrders(Orders ord){
        int n = 0;
        String sql = "INSERT INTO [dbo].[orders]\n" +
"           ([customer_id]\n" +
"           ,[order_date]\n" +
"           ,[status])\n" +
"     VALUES(?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, ord.getCustomerId());
            pre.setString(2, ord.getOrderDate());
            pre.setString(3, ord.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int UpdateOrders(Orders ord){
        int n = 0;
        String sql = "UPDATE [dbo].[orders]\n" +
"   SET [customer_id] = ?\n" +
"      ,[order_date] = ?\n" +
"      ,[status] = ?\n" +                
" WHERE [order_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, ord.getCustomerId());
            pre.setString(2, ord.getOrderDate());
            pre.setString(3, ord.getStatus());
            pre.setInt(4,ord.getOrderId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
  
    public int getOIDbyCID(int cid){
        int oid=0;
        String sql = "SELECT TOP 1 order_id\n" +
"FROM orders\n" +
"WHERE customer_id = ?\n" +
"ORDER BY order_id DESC";
        PreparedStatement pre;
        try {
            pre = conn.prepareStatement(sql);
            pre.setInt(1, cid);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                oid = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oid;
    }
  
    public Vector<Orders> getAllOrders(String sql){
        Vector<Orders> vector = new Vector<Orders>();
        ResultSet rs = this.getData(sql);
        try {
            while(rs.next()){
                int orderId = rs.getInt("order_id");
                int customer_id = rs.getInt("customer_id");
                String orderDate = rs.getString("order_date");
                String status = rs.getString("status");
                Orders ord = new Orders(orderId, customer_id, orderDate, status);
                vector.add(ord);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }
    
    public int removeOrders(int OrderId){
        int n = 0;
        String sql = "Delete from orders where order_id = '" + OrderId +"'";
        Statement state;
        try {
            DAOOrderDetails dao = new DAOOrderDetails();
            int m = dao.removeByOrderId(OrderId);
            state = conn.createStatement();
            n = state.executeUpdate(sql);                
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

}
