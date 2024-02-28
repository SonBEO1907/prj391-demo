<%-- 
    Document   : AddOrderDetails
    Created on : Mar 16, 2023, 1:24:14 AM
    Author     : os
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Products</title>
    </head>
    <body>
        <%@page import="java.sql.ResultSet"%>
        <%  ResultSet rsCat = (ResultSet) request.getAttribute("cateData");
            ResultSet rsPro = (ResultSet) request.getAttribute("productData");
        %>
                 
        <form action="OrderDetailsController" method="post">
            <input type="hidden" name="go" value="insert">
            <table>              
                <tr>                   
                    <td><label for="oId">Order ID</label></td>
                    <td>
                        <select name="oId">
                            <%while(rsCat.next()){%>
                                <option value="<%=rsCat.getInt(1)%>">                            
                                               <%=rsCat.getInt(1)%> / <%=rsCat.getString(6)%> <%=rsCat.getString(7)%>
                                </option>
                            <%}%>
                       </select>
                    </td>   
                </tr>
                
                <tr>
                    <td><label for="pId">Product Name</label></td>
                    <td>
                        <select name="pId">
                            <%while(rsPro.next()){%>
                                <option value="<%=rsPro.getInt(1)%>">                            
                                              <%=rsPro.getInt(1)%> / <%=rsPro.getString(2)%>
                                </option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                
                <tr>
                    <td><label for="quantity">Quantity</label></td>
                    <td><input type="text" name="quantity" id="quantity"></td> 
                </tr>
                
                <tr>
                    <td><label for="totalPrice"></label></td>
                    <td><input type="hidden" name="totalPrice" id="totalPrice" value="1"></td> 
                </tr>
                
                 <tr>
                   <td><label for="pd">Purchase Date</label></td>
                   <td><input type="date" name="pd" id="pd"></td> 
                </tr>
                
                <tr>
                    <td><input type="submit" value="Insert Order Details" name="submit" ></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
                
            </table>
        </form>
                        
    </body>
</html>