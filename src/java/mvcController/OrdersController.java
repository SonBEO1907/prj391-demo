package mvcController;

/**
 *
 * @author os
 */

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
import jakarta.servlet.annotation.WebServlet;
import java.util.Vector;

import entity.Orders;
import dao.DAOOrders;

/**
 *
 * @author os
 */
public class OrdersController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            DAOOrders dao = new DAOOrders();
            String action = request.getParameter("go");
            if (action==null){
                action="listAll";
            }
            
            if (action.equals("listAll")){
                String sql = "SELECT order_id, cus.customer_id, order_date, status, cus.first_name, cus.last_name,phone,address FROM orders AS ord\n" +
                             "JOIN customers AS cus ON ord.customer_id = cus.customer_id";              
                ResultSet rs = dao.getData(sql);
                
                request.setAttribute("data", rs);
               
                dispatch(request, response, "/JSP/ViewOrders.jsp");
            }
            
            if (action.equals("insert")){
                String submit= request.getParameter("submit");
                if(submit==null){
                    String sqlOrd = "SELECT * FROM orders";
                    String sqlCus = "SELECT * FROM customers";
                    ResultSet rsOrd = dao.getData(sqlOrd); 
                    ResultSet rsCus = dao.getData(sqlCus);
                    request.setAttribute("ordersData", rsOrd);
                    request.setAttribute("customersData", rsCus);
                    dispatch(request, response, "/JSP/AddOrders.jsp");
                } else {
                    int cId = Integer.parseInt(request.getParameter("cId"));
                    String ordate = request.getParameter("ordate");
                    String sts = request.getParameter("sts");
                    Orders ord = new Orders(cId, ordate, sts);
                    int n = dao.addOrders(ord);
                    response.sendRedirect("OrdersController");
                }
            }
            
            if (action.equals("update")){
                String submit = request.getParameter("submit");
                if (submit == null){
                    String id = request.getParameter("id");
                    
                    String sql = "SELECT order_id, cus.customer_id, order_date, status, cus.first_name, cus.last_name, phone, address FROM orders AS ord\n" +
                                 "JOIN customers AS cus ON ord.customer_id = cus.customer_id WHERE order_id='" + id + "'";
                    String sqlCus = "SELECT * FROM customers";
                    
                    ResultSet rs = dao.getData(sql);
                    ResultSet rsCus = dao.getData(sqlCus);
                    request.setAttribute("data", rs);
                    request.setAttribute("customersData", rsCus);
                    dispatch(request, response, "/JSP/UpdateOrders.jsp");
                } else{
                    int oId = Integer.parseInt(request.getParameter("oId"));
                    int cId = Integer.parseInt(request.getParameter("cId"));
                    String ordate = request.getParameter("ordate");
                    String sts = request.getParameter("sts");

                    Orders ord = new Orders(oId, cId, ordate, sts);
                    int n = dao.UpdateOrders(ord);
                    response.sendRedirect("OrdersController");
                }
            }
            
            if (action.equals("delete")){
                 int oId= Integer.parseInt(request.getParameter("id"));
                 int n = dao.removeOrders(oId);
                 response.sendRedirect("OrdersController");
            }
            
            if(action.equals("search")){
                int id = Integer.parseInt(request.getParameter("search"));
                String sql = "SELECT order_id, cus.customer_id, order_date, status, cus.first_name, cus.last_name,phone,address FROM orders AS ord\n" +
                             "JOIN customers AS cus ON ord.customer_id = cus.customer_id where order_id like '%"+id+"%'"; 
                ResultSet rs = dao.getData(sql);
                
                request.setAttribute("data", rs);
               
                dispatch(request, response, "/JSP/ViewOrders.jsp");
            }
            
            if(action.equals("cateOrder")){
                String status = request.getParameter("status");
                String sql = "SELECT order_id, cus.customer_id, order_date, status, cus.first_name, cus.last_name,phone,address FROM orders AS ord\n" +
                             "JOIN customers AS cus ON ord.customer_id = cus.customer_id where status = '"+status+"'"; 
                ResultSet rs = dao.getData(sql);               
                request.setAttribute("data", rs);              
                dispatch(request, response, "/JSP/ViewOrders.jsp");
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
