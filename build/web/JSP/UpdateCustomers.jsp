<%-- 
    Document   : UpdateCustomers
    Created on : Mar 14, 2023, 9:23:43 PM
    Author     : os
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Customers</title>
    </head>
    <body>
        <%@page import="java.sql.ResultSet"%>
        <%
            ResultSet rs = (ResultSet)request.getAttribute("data");
            rs.next();
        %>
          <form action="CustomersController" method="post">
            <input type="hidden" name="go" value="update">
            <table>
                <tr>
                   <td><label for="customerId">Customer ID</label></td>
                   <td><input type="text" name="customerId" id="customerId" value="<%=rs.getInt(1)%>" readonly></td> 
                </tr>
                
                <tr>
                    <td><label for="firstName">First Name</label></td>
                    <td><input type="text" name="firstName" id="firstName" value=""></td>
                </tr>

                <tr>
                    <td><label for="lastName">Last Name</label></td>
                    <td><input type="text" name="lastName" id="lastName" value=""></td>
                </tr>
                
                <tr>
                    <td><label for="email">Email</label></td>
                    <td><input type="text" name="email" id="email" value=""></td>
                </tr>
                
                <tr>
                    <td><label for="username">Username</label></td>
                    <td><input type="text" name="username" id="username" value=""></td>
                </tr>
                
                <tr>
                    <td><label for="password">Password</label></td>
                    <td><input type="password" name="password" id="password" value=""></td>
                </tr>

                <tr>
                    <td><input type="submit" value="Update Customer" name="submit" ></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
        </form>
    </body>
</html>