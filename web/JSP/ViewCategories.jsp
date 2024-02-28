<%-- 
    Document   : ViewCategories
    Created on : Mar 15, 2023, 12:31:03 AM
    Author     : os
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Categories</title>
    </head>
    <body>
        <%@page import="java.sql.ResultSet"%>
        <%
            ResultSet rs = (ResultSet)request.getAttribute("data");
            ResultSet rs2 = (ResultSet)request.getAttribute("productData");
        %>
        <a href="Admin">Main Page</a>
        <a href="CategoriesController">Category Controller</a>
        <a href="CategoriesController?go=insert">Add new Category</a>
        <table border="1">
                <caption>Categories</caption>
                <tr>
                    <th>Category ID</th>
                    <th>Category Name</th>
                    <th>Product</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
                <%while(rs.next()) {%>
                <tr>
                    <th><%=rs.getInt(1)%></th>
                    <th><%=rs.getString(2)%></th>
                    <%rs2.beforeFirst();%>
                    <th>
                    <%while(rs2.next()) {%>
                        <%if (rs.getInt(3)==rs2.getInt(1)){%>
                            <%=rs2.getString(2)%>
                        <%}%>                      
                    <%}%>
                    </th>
                    <td><a href="CategoriesController?go=update&cateId=<%=rs.getInt(1)%>">Update</a></td>
                    <td><a href="CategoriesController?go=delete&id=<%=rs.getInt(1)%>">Delete</a></td>
                </tr>
                <%}%>
        </table>
    </body>
</html>
