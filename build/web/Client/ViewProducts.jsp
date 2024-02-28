<%-- 
    Document   : ViewProducts
    Created on : Mar 14, 2023, 10:14:20 PM
    Author     : os
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Product</title>
    </head>
    <body>
        <%@page import="dao.DAOProducts"%>
        <%@page import="entity.Products"%>
        <%@page import="java.sql.ResultSet"%>
        <%@page import="jakarta.servlet.http.HttpSession"%>
        <%
            String isAdmin = (String)session.getAttribute("isAdmin");
            ResultSet rs = (ResultSet)request.getAttribute("data");
        %>
        <table border="1">
            <caption>Product List</caption>
            <tr>
                <td>ProductID</td>
                <td>Product Name</td>
                <td>Product Type</td>
                <td>Product Quantity</td>
                <td>Description</td>
                <td>Price</td>
                <td>Status</td>
                <%if(isAdmin==null){%>
                <td>Add to Cart</td>
                <%}%>
            </tr>
            <%while(rs.next()){%>
            <tr>
            <td><%=rs.getInt(1)%></td>
            <td><%=rs.getString(2)%></td>
            <td><%=rs.getString(3)%></td>
            <td><%=rs.getInt(4)%></td>
            <td><%=rs.getString(5)%></td>
            <td><%=rs.getDouble(6)%></td>
            <td><%=rs.getInt(4)==0 ?"Sold Out":"Available"%></td>
            <%if(isAdmin==null){%>
                <td><a href="AddToCartController?go=addtocart&id=<%=rs.getInt(1)%>">Add to Cart</a></td>
            <%}%>
            </tr>
            <%}%>
        </table>
    </body>
</html>
