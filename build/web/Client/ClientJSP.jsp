<%-- 
    Document   : ClientJSP
    Created on : Mar 19, 2023, 10:10:47 PM
    Author     : os
--%>
<style>
    .content{
        display: flex;
        flex-direction: row;
    }
</style>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Random Online Shop</title>
    </head>
    <body>
        <%@page import="jakarta.servlet.http.HttpSession"%>
        <%@page import="entity.Customers"%>
        <%
            String isAdmin = (String)session.getAttribute("isAdmin");
            String name = (String)session.getAttribute("Name");
            String adminName=(String)session.getAttribute("Admin");
            Customers cus = (Customers)session.getAttribute("CustomerInfo");
        %>
        <a href="ClientController">Main Menu</a><br>
        <%if (isAdmin!=null){%>
        <a href="Admin">Return to Manager</a><br>
        <%}%>
        <%if (isAdmin==null && name==null){%>
        <a href="LoginController">Login</a>
        <a href="./LoginForm/RegisterForm.jsp">Register</a>
        <%}%>      
        <%if(name!=null && isAdmin==null){%>
        <a href="ClientController?go=logout">Logout</a>
        <%}%>
        <form action="ClientController" method="POST">
            <input type="hidden" name="go" value="search">
            <input type="text" name="search" placeholder="Search Product...">
            <input type="submit" value="Search" name="submit" />
        </form>
        <br>       
        <%if (name==null && isAdmin==null){%>
            Hello, guest.
        <%}else if (adminName!=null){%>
            Hello, admin <%=adminName%>.
        <%}else if (adminName==null && isAdmin!=null){%>
            Hello, admin.
        <%}else{%>
            Hello, <%=name%>.
            <br>
            ID: <%=cus.getCustomerId()%>.
            <br>
            <a href="CartDetailController">View Cart</a>
        <%}%>
        <div class="content">
        <jsp:include page="CateMenu.jsp"></jsp:include>
        <jsp:include page="ViewProducts.jsp"></jsp:include>
        </div>
        Phone: 0123456789</br>
        Mail: RandomShop@gmail.com
    </body>
</html>
