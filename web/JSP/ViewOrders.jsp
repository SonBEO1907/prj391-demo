<%-- 
    Document   : ViewOrders
    Created on : Mar 16, 2023, 8:59:55 AM
    Author     : os
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Orders</title>
    </head>
    <body>    
        <%@page import="java.sql.ResultSet"%>
        <%
            ResultSet rs = (ResultSet)request.getAttribute("data");
        %>
        <a href="Admin">Main Page</a>
        <td><a href="OrdersController">Orders Manager</a></td>
        <form action="OrdersController" method="POST">
            <input type="hidden" name="go" value="search">
            <input type="text" name="search" placeholder="Search Order Id...">
            <input type="submit" value="Search" name="submit" />
        </form>
        <table border="1">
                <tr>
                    <td><a href="OrdersController?go=cateOrder&status=wait">Wait</a>
                        <a href="OrdersController?go=cateOrder&status=pro">Processing</a>
                        <a href="OrdersController?go=cateOrder&status=done">Done</a>
                    </td>
                </tr>
        </table>
        <table border="1">
            <caption>Orders</caption>
            <tr>
                <td>Order ID</td>
                <td>Customer ID</td>
                <td>Customer Name</td>
                <td>Order Date</td>
                <td>Status</td>
                <td>View Order Detail</td>
                <td>Update</td>
                <td>Delete</td>
            </tr>
            
            <%while(rs.next()){%>
            <tr>
                <td><%=rs.getInt(1)%></td>
                <td><%=rs.getInt(2)%></td>
                <td><%=rs.getString(5)%> <%=rs.getString(6)%></td>
                <td><%=rs.getString(3)%></td>
                <td>
                    <%if(rs.getString(4).equals("wait"))%>Wait
                    <%if(rs.getString(4).equals("pro"))%>Processing
                    <%if(rs.getString(4).equals("done"))%>Done
                </td>
                <td><a href="OrderDetailsController?go=getdetailsfrom&id=<%=rs.getInt(1)%>&cid=<%=rs.getInt(2)%>&phone=<%=rs.getString(7)%>&address=<%=rs.getString(8)%>">View Order Detail</td>       
                <td><a href="OrdersController?go=update&id=<%=rs.getInt(1)%>">Update</a></td>
                <td>
                    <%if(rs.getString(4).equals("wait")){%>
                        <a href="OrdersController?go=delete&id=<%=rs.getInt(1)%>">Delete</a>
                    <%}else{%>
                        .....
                    <%}%>
                </td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
