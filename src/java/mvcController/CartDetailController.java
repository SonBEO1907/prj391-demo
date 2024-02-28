/*
 * 
 * 
 */
package mvcController;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import jakarta.servlet.annotation.WebServlet;
import java.util.Vector;
import dao.DAOProducts;
import dao.DAOCarts;
import entity.Orders;
import entity.Products;
import dao.DAOOrderDetails;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author os
 */
public class CartDetailController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            Orders ord = (Orders) session.getAttribute("Cart");
            String action = request.getParameter("go");
            DAOProducts dao = new DAOProducts();
            DAOCarts cart = new DAOCarts();
            DAOOrderDetails DaoOd = new DAOOrderDetails();
            if (action == null) {
                action = "listAll";
            }
            
            if (action.equals("listAll")) {
                if (ord == null) {
                    out.print("You don't have any orders yet.");
                    out.print("<br><a href=\"ClientController\">Return to Menu</a>");
                } else {
                    String sql = "SELECT od.order_detail_id, od.order_id, od.product_id, od.quantity, od.total_price, od.purchase_date, p.product_name, p.product_quantity, p.price\n"
                            + "FROM order_details AS od\n"
                            + "JOIN products AS p ON od.product_id = p.product_id where order_id='" + ord.getOrderId() + "'";
                    log("Requested oid: " + ord.getOrderId());
                    ResultSet rs = dao.getData(sql);
                    request.setAttribute("CartData", rs);
                    dispatch(request, response, "/Client/ViewCart.jsp");
                }
            }
            
            if (action.equals("confirm")) {
                String cf = request.getParameter("cf");
                String checkout = request.getParameter("checkout");
                if (cf == null) {                    
                } else {
                    String phone = request.getParameter("phone");
                    String address = request.getParameter("address");
                    session.setAttribute("displayPhone", phone);
                    session.setAttribute("displayAddress", address);
                    cart.getCusInfo(ord.getOrderId(),phone,address);
                    String orderID_set[] = request.getParameterValues("odId");
                    String quantity_set[] = request.getParameterValues("quantity");
                    for (int i = 0; i < orderID_set.length; i++) {
                        int odId = Integer.parseInt(orderID_set[i]);
                        int quantity = Integer.parseInt(quantity_set[i]);
                        int n = cart.updateQuantity(quantity, odId);
                        cart.updatePrice(odId);
                    }             
                    response.sendRedirect("CartDetailController");
                }
                
                
                if(checkout==null){
                } else{
                    boolean isValid = true;
                    String orderID_set[] = request.getParameterValues("odId");
                    String pquantitySet[] =request.getParameterValues("pquantity");
                    String quantitySet[]= request.getParameterValues("quantity");
                    for (int i = 0; i < orderID_set.length; i++) {
                        int odId = Integer.parseInt(orderID_set[i]);
                        int pquantity = Integer.parseInt(pquantitySet[i]);
                        int quantity = Integer.parseInt(quantitySet[i]);
                        if (pquantity<quantity){
                            isValid=false;
                        }
                    }
                    if (isValid==true){
                        for (int i = 0; i < orderID_set.length; i++) {
                            int odId = Integer.parseInt(orderID_set[i]);
                            cart.updateTotalQuantity(odId);
                        }
                        cart.checkOutDone(ord.getOrderId());
                        response.sendRedirect("CheckOutController");
                    }else{
                        dispatch(request, response, "/Client/ErrorJSP.jsp");
                    }    
                }
            }
           
            
            if (action.equals("delete")){               
                 int odId= Integer.parseInt(request.getParameter("id"));
                 int n = DaoOd.removeOrderDetails(odId);
                 response.sendRedirect("CartDetailController");
            }
            
        
        }
    }

    protected void dispatch(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
        RequestDispatcher disp = request.getRequestDispatcher(url);
        disp.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
