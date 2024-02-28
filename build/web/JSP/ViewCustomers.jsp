<%-- 
    Document   : ViewCustomers
    Created on : Mar 14, 2023, 9:42:34 AM
    Author     : os
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Customer</title>
    </head>
    <body>
        <%@page import="dao.DAOCustomers"%>
        <%@page import="entity.Customers"%>
        <%@page import="java.sql.ResultSet"%>
        <%
            ResultSet rs = (ResultSet)request.getAttribute("data");
        %>
        <a href="Admin">Main Page</a>
        <a href="CustomersController">Customer Controller</a>
        <form action="CustomersController" method="POST">
            <input type="hidden" name="go" value="search">
            <input type="text" name="search" placeholder="Search Customer...">
            <input type="submit" value="Search" name="submit" />
        </form>
        
        <table border="1">
                         <caption>Customer List</caption>
                        <tr>
                        <th>Customer ID</th>
                        <th>Customer First Name</th>
                        <th>Customer Last Name</th>
                        <th>Email</th>
                        <th>Password</th>
                        <th>Username</th>
                        <th>Update</th>
                        <th>Delete</th>
                        </tr>
        <%while(rs.next()) {%>
    <tr>
        <td><%=rs.getInt(1)%></td>
        <td><%=rs.getString(2)%></td>
        <td><%=rs.getString(3)%></td>
        <td><%=rs.getString(4)%></td>
        <td><%=rs.getString(5)%></td>
        <td><%=rs.getString(6)%></td>   
        <td><a href="CustomersController?go=update&id=<%=rs.getInt(1)%>">Update</a></td>
        <td><a href="CustomersController?go=delete&id=<%=rs.getInt(1)%>">Delete</a></td>
    </tr>    
    <%}%>
    
    </table>
    <br>
    
    </body>
</html>

