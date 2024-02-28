<%-- 
    Document   : ViewOrderDetails
    Created on : Mar 15, 2023, 8:40:15 PM
    Author     : os
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Order Details</title>
    </head>
    <body>    
        <%@page import="java.sql.ResultSet"%>
        <%
            ResultSet rs = (ResultSet)request.getAttribute("data");
            ResultSet rsCus = (ResultSet)request.getAttribute("customerData");
            int cid = (int)request.getAttribute("requestedCid");
            String phone = (String)request.getAttribute("requestedPhone");
            String address = (String)request.getAttribute("requestedAddress");
        %>
        <a href="Admin">Main Page</a>
        <table border="1">
            <caption>Order Details</caption>
            <tr><%while(rsCus.next()){%>
                <%if (rsCus.getInt(1)==cid){%>
                <td>Customer Name: <%=rsCus.getString(2)%> <%=rsCus.getString(3)%></td>
                <%}%>
            <%}%>
            </tr>
            <tr>
                <td>Phone : <%=phone%></td>
            </tr>
            <tr>
                <td>Address : <%=address%></td>
            </tr>
            <tr>
                <td>Product Name</td>
                <td>Quantity</td>
                <td>Price</td>
                <td>Total Price</td>
                <td>Purchase Date</td>
            </tr>
            <% double Total=0;%>
            <%while(rs.next()){%>
            <tr>
                <td><%=rs.getString(2)%></td>
                <td><%=rs.getInt(4)%></td>                     
                <td><%=rs.getDouble(3)%></td>
                <td><%= rs.getInt(4) * rs.getDouble(3)%></td>
                <%Total +=  rs.getInt(4) * rs.getDouble(3); %>
                <td><%=rs.getString(6)%></td>
            </tr>
            <%}%>
        </table>
        <table border="1">
            <thead>
                <tr>
                    <td>Total Price: <%=Total%></td>
                </tr>
            </thead>
        </table>

    </body>
</html>
