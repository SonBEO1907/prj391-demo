package mvcController;

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

import entity.Customers;
import dao.DAOCustomers;

import jakarta.servlet.annotation.WebServlet;
import java.util.Vector;

/**
 *
 * @author os
 */

@WebServlet(name = "CustomersController", urlPatterns = {"/CustomersController"})
public class CustomersController extends HttpServlet {

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
        DAOCustomers dao = new DAOCustomers();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String action = request.getParameter("go");
            if (action==null){
                action="listAll";
            }
            if (action.equals("listAll")){
                String sql = "SELECT * FROM Customers";
                ResultSet rs = dao.getData(sql);
                request.setAttribute("data", rs);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/JSP/ViewCustomers.jsp");
                dispatcher.forward(request, response);
            }
            
            if (action.equals("insert")) {
                String firstName  = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String username = request.getParameter("username");
                Customers cus = new Customers(firstName, lastName, email, password, username);
                int n = dao.addCustomers(cus);
                response.sendRedirect("CustomersController");
            }
            
            if (action.equals("update")){
                String submit = request.getParameter("submit");
                if (submit==null){
                    String cid = request.getParameter("id");
                    String sql = "SELECT * FROM Customers WHERE customer_id='"+ cid +"'";
                    ResultSet rs = dao.getData(sql);
                    request.setAttribute("data", rs);
                    dispatch(request, response, "/JSP/UpdateCustomers.jsp");
                }else{
                    int customerId  = Integer.parseInt(request.getParameter("customerId"));
                    String firstName  = request.getParameter("firstName");
                    String lastName = request.getParameter("lastName");
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    String username = request.getParameter("username");  
                    log("get"+customerId+firstName+lastName+username);
                    Customers cus = new Customers(customerId, firstName, lastName, email, password, username);
                    
                    int n = dao.UpdateCustomers(cus);
                    response.sendRedirect("CustomersController");
                }
            }          
            
            if(action.equals("delete")){
                int id = Integer.parseInt(request.getParameter("id"));
                int n = dao.removeCustomers(id);
                response.sendRedirect("CustomersController");
            }

            if(action.equals("search")){
                String search = request.getParameter("search");
                String sql = "SELECT * FROM Customers WHERE first_name LIKE '%"+search+"%' OR last_name LIKE '%"+search+"%'";
                ResultSet rs = dao.getData(sql);
                request.setAttribute("data", rs);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/JSP/ViewCustomers.jsp");
                dispatcher.forward(request, response);
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
