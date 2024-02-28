<%-- 
    Document   : UpdateCategories
    Created on : Mar 15, 2023, 1:10:37 AM
    Author     : os
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Categories</title>
    </head>
    <body>
          <%@page import="java.sql.ResultSet"%>
        <%
            ResultSet rs = (ResultSet)request.getAttribute("data");
            rs.next();
            ResultSet rs2 = (ResultSet)request.getAttribute("productData");
        %>
        <form action="CategoriesController" method="post">
            <input type="hidden" name="go" value="update">
            <table>
                <tr>
                    <td><label for="cateId">Category ID</label></td>
                    <td><input type="text" name="cateId" id="cateId" value="<%=rs.getInt(1)%>" readonly></td> 
                </tr>
                <tr>
                    <td><label for="cname">Category Name</label></td>
                    <td><input type="text" name="cname" id="cname" value="<%=rs.getString(2)%>"></td> 
                </tr>
                <tr>
                    <tr>Product:<select name="productId">
                    <%while(rs2.next()){%>
                    <option value="<%=rs2.getInt(1)%>">
                        <%=rs2.getString(2)%>
                    </option>
                    <%}%>
                    <tr>
                </tr>
                 <tr>
                    <td><input type="submit" value="Update Category" name="submit" ></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
