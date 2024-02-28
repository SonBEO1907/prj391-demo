<%-- 
    Document   : UpdateOrders
    Created on : Mar 16, 2023, 10:15:41 PM
    Author     : os
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Order</title>
    </head>
    <body>
        <%@page import="java.sql.ResultSet"%>
        <%  
            ResultSet rs = (ResultSet) request.getAttribute("data");
            ResultSet rsCus = (ResultSet) request.getAttribute("customersData");
            rs.next();
        %>
                 
        <form action="OrdersController" method="post">
            <input type="hidden" name="go" value="update">
            <table>
                <tr>
                    <td><label for="oId">Order ID</label></td>
                    <td><input type="text" name="oId" id="oId" value="<%=rs.getInt(1)%>" readonly></td> 
                </tr>               
                
                <tr>
                    <td><label for="cId">Customer Name</label></td>
                        <td>
                            <select name="cId">
                                <%while(rsCus.next()){%>
                                    <option value="<%=rsCus.getInt(1)%>">                            
                              <%=(rsCus.getInt(1)==rs.getInt(2))?"Selected: ":""%><%=rsCus.getString(2)%> <%=rsCus.getString(3)%>
                                   </option>
                                <%}%>
                            </select>
                        </td>
                </tr>
                
                <tr>
                    <td><label for="ordate">Order Date</label></td>
                    <td><input type="date" name="ordate" id="ordate" value="<%=rs.getString(3)%>"></td> 
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
