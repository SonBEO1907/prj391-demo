/*
 * 
 * 
 */
package mvcController;

import jakarta.servlet.annotation.WebServlet;
import java.util.Vector;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import dao.DAOCustomers;
import entity.Customers;
import entity.Orders;
import dao.DAOOrders;
import dao.DAOOrderDetails;
import entity.OrderDetails;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;

/**
 *
 * @author os
 */
public class AddToCartController extends HttpServlet {

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
           String action = request.getParameter("go");
           int pid = Integer.parseInt(request.getParameter("id"));
           HttpSession session = request.getSession();
           Customers cus = (Customers)session.getAttribute("CustomerInfo");
           DAOOrderDetails cart = new DAOOrderDetails();
           Orders o = (Orders)session.getAttribute("Cart");
           DAOOrders dao = new DAOOrders();
           if (action==null){
               action="addtocart";
           }
           if (action.equals("addtocart")){
                if (cus==null){
                response.sendRedirect("LoginController");
                }else if (o==null){
                    
                    int cid = cus.getCustomerId();
                    String orderdate = LocalDate.now().toString();
                    String status ="wait";
                    
                    o = new Orders(cid, orderdate, status);
                    
                    int m = dao.addOrders(o);                  
                    int orderID = dao.getOIDbyCID(cid);
                    int productId = pid;
                    int quantity = 0;
                    double totalPrice = 0;
                    String purchaseDate = o.getOrderDate();
                    
                    o = new Orders(orderID, cid, orderdate, status);
                    session.setAttribute("Cart", o);
                    
                    log("oId: "+orderID+", product id: "+productId+", purchase date: "+purchaseDate);
                    
                    OrderDetails od = new OrderDetails(orderID, productId, quantity, totalPrice, purchaseDate);
                    int n = cart.addOrderDetails(od);
                    response.sendRedirect("CartDetailController");
                }else{
                    int orderID = dao.getOIDbyCID(o.getCustomerId());
                    int productId = pid;
                    int quantity = 0;
                    double totalPrice = 0;
                    String purchaseDate = o.getOrderDate();
                    OrderDetails od = new OrderDetails(orderID, productId, quantity, totalPrice, purchaseDate);
                    int n = cart.addOrderDetails(od);
                    response.sendRedirect("CartDetailController");
                }
           }
        }
    }
protected void dispatch(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException{
        RequestDispatcher disp=request.getRequestDispatcher(url);
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
