<%-- 
    Document   : AddProducts
    Created on : Mar 14, 2023, 11:14:07 PM
    Author     : os
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Products</title>
    </head>
    <body>
         <form action="../ProductsController" method="post">
             <input type="hidden" name="go" value="insert">
             <table>
                 <tr>
                   <td><label for="productId">Product ID</label></td>
                   <td><input type="text" name="productId" id="productId"></td> 
                </tr>
                <tr>
                   <td><label for="pname">Product Name</label></td>
                   <td><input type="text" name="pname" id="pname"></td> 
                </tr>
                 <tr>
                   <td><label for="ptype">Product Type</label></td>
                   <td><input type="text" name="ptype" id="ptype"></td> 
                </tr>
                 <tr>
                   <td><label for="quantity">Quantity</label></td>
                   <td><input type="text" name="quantity" id="quantity"></td> 
                </tr>
                 <tr>
                   <td><label for="description">Description</label></td>
                   <td><input type="text" name="description" id="description"></td> 
                </tr>
                 <tr>
                   <td><label for="price">Price</label></td>
                   <td><input type="text" name="price" id="price"></td> 
                </tr>
                 <tr>
                   <td><label for="status"></label></td>
                   <td><input type="hidden" name="status" id="status" value ="1"></td> 
                </tr>
                <tr>
                    <td><input type="submit" value="Insert Product" name="submit" ></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
             </table>
         </form>
    </body>
</html>
