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
        <%
            ResultSet rs = (ResultSet)request.getAttribute("data");
        %>
        <a href="Admin">Main Page</a>
        <a href="ProductsController">Product Controller</a>
        <a href="./JSP/AddProducts.jsp">Add new Product</a>
        <form action="ProductsController" method="POST">
            <input type="hidden" name="go" value="search">
            <input type="text" name="search" placeholder="Search Products...">
            <input type="submit" value="Search" name="submit" />
        </form>
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
                <th>Update</th>
                <th>Delete</th>
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
            <td><a href="ProductsController?go=update&id=<%=rs.getInt(1)%>">Update</a></td>
            <td><a href="ProductsController?go=delete&id=<%=rs.getInt(1)%>">Delete</a></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
