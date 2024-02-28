
package dao;

import JDBCConnect.DBConnect;
import entity.OrderDetails;
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
public class DAOOrderDetails extends DBConnect{
   
    public int addOrderDetails(OrderDetails ordde){
        int n = 0;
        String sql = "INSERT INTO [dbo].[order_details]\n" +
"           ([order_id]\n" +
"           ,[product_id]\n" +                
"           ,[quantity]\n" +
"           ,[total_price]\n" +
"           ,[purchase_date])\n" +                
"     VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, ordde.getOrderId());
            pre.setInt(2, ordde.getProductId());
            pre.setInt(3, ordde.getQuantity());
            pre.setDouble(4, ordde.getTotalPrice());
            pre.setString(5, ordde.getPurchaseDate());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int UpdateOrderDetails(OrderDetails ordde){
        int n = 0;
        String sql = "UPDATE [dbo].[order_details]\n" +
"   SET [order_id] = ?\n" +
"      ,[product_id] = ?\n" +
"      ,[quantity] = ?\n" +
"      ,[total_price] = ?\n" +
"      ,[purchase_date] = ?\n" +                
" WHERE [order_detail_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, ordde.getOrderId());
            pre.setInt(2, ordde.getProductId());
            pre.setInt(3, ordde.getQuantity());
            pre.setDouble(4, ordde.getTotalPrice());
            pre.setString(5, ordde.getPurchaseDate());
            pre.setInt(6, ordde.getOrderDetailId());     
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
   
    public Vector<OrderDetails> getAllOrderDetails(String sql){
        Vector<OrderDetails> vector = new Vector<OrderDetails>();
        ResultSet rs = this.getData(sql);
        try {
            int orderDetailId = rs.getInt("order_detail_id");
            int orderId = rs.getInt("order_id");
            int productId = rs.getInt("product_id");
            int quantity = rs.getInt("quantity");
            double totalPrice = rs.getDouble("total_price");
            String pd = rs.getString("purchase_date");
            OrderDetails od = new OrderDetails(orderDetailId, orderId, productId, quantity, totalPrice, pd);
            vector.add(od);
        } catch (Exception e) {
            e.printStackTrace();
        }
          return vector;
    }
    
    
    public int removeOrderDetails(int OrderDetailId){
        int n = 0;
        String sql = "DELETE FROM order_details WHERE order_detail_id = '" + OrderDetailId +"'";
        Statement state;
        try {
            state = conn.createStatement();
            n = state.executeUpdate(sql);                  
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int removeByOrderId(int oid){
        int n = 0;
        String sql = "DELETE FROM order_details WHERE order_id='"+oid+"'";
        Statement state;
        try {
            state = conn.createStatement();
            n = state.executeUpdate(sql);                  
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
