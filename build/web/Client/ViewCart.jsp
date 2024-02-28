<%-- 
    Document   : ViewCart.jsp
    Created on : Mar 20, 2023, 6:26:18 PM
    Author     : os
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <%@page import="java.sql.ResultSet"%>
        <%@page import="jakarta.servlet.http.HttpSession" %>
        <%
            ResultSet rs = (ResultSet)request.getAttribute("CartData");
            String phone = (String)session.getAttribute("displayPhone");
            String address = (String)session.getAttribute("displayAddress");
        %>
        <a href="ClientController">Return to Menu</a>
        <form action="CartDetailController" method="POST">
            <input type="hidden" name="go" value="confirm">
            <table border="1">
                <thead>
                    <tr>
                        <th>Product Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Purchase Date</th>
                        <th>Price</th>
                        <th>Note</th>
                        <th>Remove from Cart</th>
                    </tr>
                </thead>
                <% double Total=0;%>
                <tbody>
                    <%while(rs.next()){%>
                    <tr>
                        <input type="hidden" name="odId" value="<%=rs.getInt(1)%>">
                        <input type="hidden" name="pquantity" value="<%=rs.getInt(8)%>">
                        <td><%=rs.getString(7)%></td>
                        <td><input type="text" name="quantity" value="<%=rs.getInt(4)%>" /></td>
                        <td><%=rs.getDouble(9)%></td>
                        <td><%=rs.getString(6)%></td>
                        <td><%=rs.getDouble(9)*rs.getInt(4)%></td>
                        <%Total +=  rs.getInt(4) * rs.getDouble(9); %>
                        <td>
                            <%if(rs.getInt(4)>rs.getInt(8)){%>
                            <font color ="red">Order quantity exceeds total in stock.</font>
                            <%}else{%>
                            Valid Value
                            <%}%>
                        </td>
                        <td><a href="CartDetailController?go=delete&id=<%=rs.getInt(1)%>">Remove from Cart</a></td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
                <table border="1" width="100">
                    <thead>
                        <tr>
                            <td><input type="text" name="phone" value="<%=phone==null?"":phone%>" placeholder="Enter your phone"/></td>
                            <td><input type="text" name="address" value="<%=address==null?"":address%>" placeholder="Enter your address"/></td>  
                        </tr>
                    </thead>
                </table>
                <table border="1" width="100">
                    <thead>
                        <tr>
                            <td>Total Price: <%=Total%></td>
                        </tr>
                    </thead>
                </table>
            <input type="submit" value="Confirm Orders" name="cf" />
            <input type="submit" value="Check Out" name="checkout" />
        </form>
    </body>
</html>
