<%-- 
    Document   : CustomerLogin
    Created on : Mar 17, 2023, 10:58:00 PM
    Author     : os
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    
    <body>
        <%String isLoggedIn =(String)request.getAttribute("isLoggedIn");%>
        <h1>Login</h1>
        <form action="LoginController" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username"><br><br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password"><br><br>
            <input type="submit" name="submit" value="Login">
            <%if (isLoggedIn.equals("false")){%>
            <font color="red">Either the username or password is wrong</font>
            <%}%>
        </form>
        Not yet have an account? <a href="./LoginForm/RegisterForm.jsp">Register Now</a>.
    </body>
    
</html>




