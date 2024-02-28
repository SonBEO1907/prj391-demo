<%-- 
    Document   : CateMenu
    Created on : Mar 21, 2023, 1:30:34 AM
    Author     : os
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cate Menu</title>
    </head>
    <body>
        <%@page import="java.sql.ResultSet"%>
        <%
            ResultSet rsCate = (ResultSet)request.getAttribute("cateData");
        %>
        <form action="ClientController" method="get">
            <table border="1">
                    <%while(rsCate.next()){%>
                    <tr>
                        <td><a href="ClientController?go=CateItems&id=<%=rsCate.getInt(1)%>"><%=rsCate.getString(2)%></a></td>  
                    </tr>
                    <%}%>   
            </table>

        </form>
    </body>
</html>
