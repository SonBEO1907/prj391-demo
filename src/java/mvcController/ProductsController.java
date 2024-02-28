/*
 * 
 * 
 */
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

import dao.DAOProducts;
import entity.Products;
import jakarta.servlet.annotation.WebServlet;
import java.util.Vector;

/**
 *
 * @author os
 */
public class ProductsController extends HttpServlet {

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
            DAOProducts dao = new DAOProducts();
            String action= request.getParameter("go");
            if (action==null){
                action="listAll";
            }
            if (action.equals("listAll")){
                String sql = "Select * from Products";
                ResultSet rs = dao.getData(sql);
                request.setAttribute("data", rs);
                dispatch(request, response, "/JSP/ViewProducts.jsp");
            }
            
            if (action.equals("insert")){
                int productId = Integer.parseInt(request.getParameter("productId"));
                String pname = request.getParameter("pname");
                String ptype = request.getParameter("ptype");
                int quantity =  Integer.parseInt(request.getParameter("quantity"));
                String desc = request.getParameter("description");
                double price = Double.parseDouble(request.getParameter("price"));
                int status = Integer.parseInt(request.getParameter("status"));
                
                Products pro = new Products(productId, pname, ptype, quantity, desc, price, status);
                int n = dao.addProducts(pro);
                response.sendRedirect("ProductsController");
            }
            
            if (action.equals("update")){
                String submit = request.getParameter("submit");
                if (submit==null){
                    String pid = request.getParameter("id");
                    String sql = "Select * from Products where product_id='"+pid+"'";
                    ResultSet rs = dao.getData(sql);
                    request.setAttribute("data", rs);
                    dispatch(request, response, "/JSP/UpdateProducts.jsp");
                }else{
                    int productId = Integer.parseInt(request.getParameter("productId"));
                    String pname = request.getParameter("pname");
                    String ptype = request.getParameter("ptype");
                    int quantity =  Integer.parseInt(request.getParameter("quantity"));
                    String desc = request.getParameter("description");
                    double price = Double.parseDouble(request.getParameter("price"));
                    int status = Integer.parseInt(request.getParameter("status"));
                
                Products pro = new Products(productId, pname, ptype, quantity, desc, price, status);
                int n = dao.UpdateProducts(pro);
                response.sendRedirect("ProductsController");
                }             
            }
            
            if (action.equals("delete")){
                int id = Integer.parseInt(request.getParameter("id"));
                int n = dao.removeProducts(id);
                response.sendRedirect("ProductsController");
            }
            if(action.equals("search")){
                String search = request.getParameter("search");
                String sql = "Select * from Products Where product_name like '%"+search+"%'";
                ResultSet rs = dao.getData(sql);
                request.setAttribute("data", rs);
                dispatch(request, response, "/JSP/ViewProducts.jsp");
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
