<%-- 
    Document   : AddCustomers
    Created on : Mar 14, 2023, 8:59:47 PM
    Author     : os
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Customer</title>
    </head>
    <body>
        <form action="../CustomersController" method="post">
            <input type="hidden" name="go" value="insert">
            <table>
                <tr>
                    <td><label for="firstName">First Name</label></td>
                    <td><input type="text" name="firstName" id="firstName"></td>
                </tr>

                <tr>
                    <td><label for="lastName">Last Name</label></td>
                    <td><input type="text" name="lastName" id="lastName"></td>
                </tr>
                
                <tr>
                    <td><label for="email">Email</label></td>
                    <td><input type="text" name="email" id="email"></td>
                </tr>
                
                <tr>
                    <td><label for="username">Username</label></td>
                    <td><input type="text" name="username" id="username"></td>
                </tr>
                
                <tr>
                    <td><label for="password">Password</label></td>
                    <td><input type="password" name="password" id="password"></td>
                </tr>

                <tr>
                    <td><input type="submit" value="Insert Customer" name="submit" ></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
        </form>
    </body>
</html>