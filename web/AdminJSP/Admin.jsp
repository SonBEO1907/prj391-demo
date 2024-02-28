<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Manager</title>
    </head>
    <body>
        <%@page import="jakarta.servlet.http.HttpSession"%>
        <%
            String adminName=(String)session.getAttribute("Admin");
        %>
        <%if(adminName==null){%>
        <a href="AdminLoginController?go=login">Login as Admin</a>
        <%}else{%>
        <a href="AdminLoginController?go=logout">LogOut as Admin</a><br>
            Hello, <%=adminName%>
            <br>
            <a href="ClientController">Client Page Demo</a><br>
            <a href="ProductsController">Product Manager</a><br>
            <a href="CustomersController">Customer Manager</a><br>
            <a href="CategoriesController">Categories Manager</a><br>
            <a href="OrdersController">Order Controller</a><br>
        <%}%>
        <br>
       
    </body>
</html>
