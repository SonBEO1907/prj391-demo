<%-- 
    Document   : AddCategories
    Created on : Mar 15, 2023, 12:51:15 AM
    Author     : os
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Categories</title>
    </head>
    <body>
         <%@page import="java.sql.ResultSet"%>
        <%
            ResultSet rs2 = (ResultSet)request.getAttribute("productData");
        %>
        <form action="CategoriesController" method="post">
            <input type="hidden" name="go" value="insert">
            <table>
                <tr>
                    <td><label for="cateId">Category ID</label></td>
                   <td><input type="text" name="cateId" id="cateId"></td> 
                </tr>
                <tr>
                    <td><label for="cname">Category Name</label></td>
                   <td><input type="text" name="cname" id="cname"></td> 
                </tr>
                <tr>
                    Product:<select name="productId">
                    <%while(rs2.next()){%>
                    <option value="<%=rs2.getInt(1)%>">
                    <%=rs2.getString(2)%>
                    </option>
                    <%}%>
                </tr>
                 <tr>
                    <td><input type="submit" value="Insert Category" name="submit" ></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
