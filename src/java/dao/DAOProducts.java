package dao;

import JDBCConnect.DBConnect;
import entity.Products;
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
public class DAOProducts extends DBConnect{
   
    public int addProducts(Products pro){
        int n = 0;
        String sql = "INSERT INTO [dbo].[products]\n" +
"           ([product_id]\n"+
"           ,[product_name]\n" +
"           ,[product_type]\n" +
"           ,[product_quantity]\n" +                 
"           ,[description]\n" +
"           ,[price]\n" +
"           ,[status])\n" +               
"     VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, pro.getProductId());
            pre.setString(2, pro.getProductName());
            pre.setString(3, pro.getProductType());
            pre.setInt(4, pro.getProductQuantity());
            pre.setString(5, pro.getDescription());
            pre.setDouble(6, pro.getPrice());
            pre.setInt(7, pro.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int UpdateProducts(Products pro){
        int n = 0;
        String sql = "UPDATE [dbo].[products]\n" +
"   SET [product_name] = ?\n" +
"      ,[product_type] = ?\n" +
"      ,[product_quantity] = ?\n" +
"      ,[description] = ?\n" +
"      ,[price] = ?\n" +
"      ,[status] = ?\n" +                
" WHERE [product_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pro.getProductName());
            pre.setString(2, pro.getProductType());
            pre.setInt(3, pro.getProductQuantity());            
            pre.setString(4, pro.getDescription());
            pre.setDouble(5, pro.getPrice());
            pre.setInt(6, pro.getStatus()); 
            pre.setInt(7, pro.getProductId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public Vector<Products> getAllProduct(String sql){
        Vector<Products> vector = new Vector<Products>();
        ResultSet rs = this.getData(sql);
        try {
            while(rs.next()){
                int pid = rs.getInt("product_id");
                String pname = rs.getString("product_name");
                String ptype = rs.getString("product_type");
                int pquantity = rs.getInt("product_quantity");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                int status = rs.getInt("status");
                Products pro = new Products(pid, pname, ptype, pquantity, description, price, status);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public int removeProducts(int ProductId){
        int n = 0;
        String sql = "Delete from products where product_id = '" + ProductId +"'";
        Statement state;
        try {
            String sqlCheck1 = "Select * from reviews where product_id='"+ProductId+"'";
            String sqlCheck2 = "Select * from order_details where product_id='"+ProductId+"'";
            String sqlCheck3 = "Select * from carts where product_id='"+ProductId+"'";
            String sqlCheck4 = "Select * from categories where product_id='"+ProductId+"'";
            ResultSet rsCheck1 = this.getData(sqlCheck1);
            ResultSet rsCheck2 = this.getData(sqlCheck2);
            ResultSet rsCheck3 = this.getData(sqlCheck3);
            ResultSet rsCheck4 = this.getData(sqlCheck4);
            if (rsCheck1.next() || rsCheck2.next() || rsCheck3.next()|| rsCheck4.next()){
                n=-1;
            }else{
                state = conn.createStatement();
                n = state.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

}

