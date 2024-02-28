<%-- 
    Document   : AddOrders
    Created on : Mar 16, 2023, 9:40:23 AM
    Author     : os
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Orders</title>
    </head>
    <body>
        <%@page import="java.sql.ResultSet"%>
        <%  
            ResultSet rsOrd = (ResultSet) request.getAttribute("ordersData");
            ResultSet rsCus = (ResultSet) request.getAttribute("customersData");
        %>
                 
        <form action="OrdersController" method="post">
            <input type="hidden" name="go" value="insert">
            <table>              
                <tr>
                    <td><label for="cId">Customer Name</label></td>
                        <td>
                            <select name="cId">
                                <%while(rsCus.next()){%>
                                    <option value="<%=rsCus.getInt(1)%>">                            
                                                   <%=rsCus.getString(2)%> <%=rsCus.getString(3)%>
                                   </option>
                                <%}%>
                            </select>
                        </td>
                </tr>
                
                <tr>
                    <td><label for="ordate">Order Date</label></td>
                    <td><input type="date" name="ordate" id="ordate"></td> 
                </tr>
                
                <tr>
                    <td><label for="sts">Status</label></td>
                    <td>
                        <input type="radio" name="sts" id="sts" value="wait" checked>Wait
                        <input type="radio" name="sts" id="sts" value="process">Process
                        <input type="radio" name="sts" id="sts" value="done">Done
                    </td>
                </tr>
                   
                <tr>
                    <td><input type="submit" value="Insert Order Details" name="submit" ></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
                
            </table>
        </form>
                        
    </body>
</html>