<%-- 
    Document   : UpdateOrderDetails
    Created on : Mar 16, 2023, 8:01:03 AM
    Author     : os
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Order Details</title>
    </head>
    <body>
        <%@page import="java.sql.ResultSet"%>
        <%
            ResultSet rsOrd = (ResultSet) request.getAttribute("orderData");
            ResultSet rsPro = (ResultSet) request.getAttribute("productData");
            ResultSet rs=(ResultSet)request.getAttribute("data");
            rs.next();
        %>
        
        <form action="OrderDetailsController" method="post">
            <input type="hidden" name="go" value="update">
            <table>
                                <tr>
                    <td><label for="odId">Order Detail ID</label></td>
                    <td><input type="text" name="odId" id="odId" value="<%=rs.getInt(1)%>" readonly></td> 
                </tr>
                
                <tr>                   
                    <td><label for="oId">Order ID</label></td>
                    <td><select name="oId">
                            <%while(rsOrd.next()){%>
                                <option value="<%=rsOrd.getInt(1)%>">                            
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
                   <td><input type="date" name="pd" id="pd" value="<%=rs.getString(8)%>"></td> 
                </tr>
                
                <tr>
                    <td><input type="submit" value="Update Order Details" name="submit" ></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
                
            </table>
        </form>
    </body>
</html>