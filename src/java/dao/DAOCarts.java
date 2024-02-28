package dao;

import JDBCConnect.DBConnect;
import entity.Carts;
import entity.Orders;
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
public class DAOCarts extends DBConnect{
   
   public int updateQuantity(int quantity,int odId){
       int n = 0;
        String sql = "UPDATE [dbo].[order_details]\n" +
"      SET [quantity] = ?\n" +              
" WHERE [order_detail_id] = ?";
       try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, quantity);
            pre.setInt(2, odId);
            n = pre.executeUpdate();
       } catch (Exception e) {
           e.printStackTrace();
       }
       return n;
   }

   public void updatePrice(int odId){
       int n = 0;
       String sql = "UPDATE order_details \n" +
"SET total_price = od.quantity * p.price\n" +
"FROM order_details AS od JOIN products AS p ON od.product_id = p.product_id\n" +
"WHERE order_detail_id = ?";
       try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, odId);
            n = pre.executeUpdate();
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   
   public void updateTotalQuantity(int odId){
       int n =0;
       String sql ="UPDATE products\n" +
"SET product_quantity = product_quantity - od.quantity\n" +
"FROM products AS p JOIN order_details AS od ON od.product_id = p.product_id\n" +
"WHERE order_detail_id = ?  ";
       try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, odId);
            n = pre.executeUpdate();
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   
   public void checkOutDone(int oId){
       int n =0;
       String sql = "UPDATE orders\n" +
"SET status = 'pro'\n" +
"FROM orders AS od\n" +
"WHERE order_id = ?";
       try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, oId);
            n = pre.executeUpdate();
       } catch (Exception e) {
            e.printStackTrace();
       }
   }
   
   public void getCusInfo(int oid, String phone, String address){
       int n =0;
       String sql ="UPDATE [dbo].[orders]\n" +
"   SET \n" +
"       [phone] = ?\n" +
"      ,[address] = ?\n" +
" WHERE [order_id] = ?";
       try {
           PreparedStatement pre = conn.prepareStatement(sql);
           pre.setString(1, phone);
           pre.setString(2, address);
           pre.setInt(3, oid);
           n = pre.executeUpdate();
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
}
