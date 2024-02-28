<%-- 
    Document   : ViewDetailsById
    Created on : Mar 20, 2023, 11:59:24 PM
    Author     : os
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@page import="java.sql.ResultSet"%>
        <%
            ResultSet rs = (ResultSet)request.getAttribute("data");
            ResultSet rsPro = (ResultSet)request.getAttribute("productData");
        %>
         <a href="Admin">Main Page</a>
        <td><a href="OrderDetailsController?go=insert">Add New Order Details</a></td>
        <table border="1">
            <caption>Order Details</caption>
            <tr>
                <td>Order Detail ID</td>
                <td>Order ID</td>
                <td>Product</td>
                <td>Quantity</td>
                <td>Total Price</td>
                <td>Purchase Date</td>
                <td>Update</td>
                <td>Delete</td>
            </tr>
            <% double Total=0;%>
            <%while(rs.next()){%>
            <tr>
                <td><%=rs.getInt(1)%></td>
                <td><%=rs.getInt(2)%></td>                     
                <td><%=rs.getString(7)%></td>
                <td><%=rs.getInt(4)%></td>
                <td><%= rs.getInt(4) * rs.getDouble(8)%></td>
                <%Total +=  rs.getInt(4) * rs.getDouble(8); %>
                <td><%=rs.getString(6)%></td>
                <td><a href="OrderDetailsController?go=update&id=<%=rs.getInt(1)%>">Update</a></td>
                <td><a href="OrderDetailsController?go=delete&id=<%=rs.getInt(1)%>">Delete</a></td>
            </tr>
            <%}%>
        </table>
        <div>Total Price: <%=Total%></div>
    </body>
</html>
