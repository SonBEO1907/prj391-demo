<%-- 
    Document   : LoginAdmin
    Created on : Mar 20, 2023, 11:15:14 AM
    Author     : os
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Login</title>
    </head>
    <body>
         <%String isLoggedIn =(String)request.getAttribute("isLoggedIn");%>
        <h1>Admin Login Form</h1>
        <form action="AdminLoginController" method="post">
            <input type="hidden" name="go" value="login">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username"><br><br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password"><br><br>
            <input type="submit" name="submit" value="Login">
            <%if (isLoggedIn.equals("false")){%>
            <font color="red">Either the username or password is wrong</form>
            <%}%>
        </form>
    </body>
</html>
