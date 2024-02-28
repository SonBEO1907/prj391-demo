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

import dao.DAOCategories;
import entity.Categories;
import jakarta.servlet.annotation.WebServlet;
import java.util.Vector;
/**
 *
 * @author os
 */
public class CategoriesController extends HttpServlet {

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
            DAOCategories dao = new DAOCategories();
            String action = request.getParameter("go");
            if (action==null){
                action="listAll";
            }
            if (action.equals("listAll")){
                String sql = "Select * from categories";
                String sql2= "Select * from products";
                ResultSet rs = dao.getData(sql);
                ResultSet rs2 = dao.getData(sql2);
                request.setAttribute("data", rs);
                request.setAttribute("productData", rs2);
                dispatch(request, response, "/JSP/ViewCategories.jsp");
            }
            
            if (action.equals("insert")){
               String submit = request.getParameter("submit");
               if (submit==null){
                    String sql2= "Select * from products";
                    ResultSet rs2 = dao.getData(sql2);
                    request.setAttribute("productData", rs2);
                    dispatch(request, response, "/JSP/AddCategories.jsp");
               }else{
                   int cateId= Integer.parseInt(request.getParameter("cateId"));
                   String cname = request.getParameter("cname");
                   int productId = Integer.parseInt(request.getParameter("productId"));
                   
                   Categories cat = new Categories(cateId, cname, productId);
                   int n = dao.addCategories(cat);
                   response.sendRedirect("CategoriesController");
               }
               
            }
            
            if (action.equals("update")){
               String submit = request.getParameter("submit");
               if (submit==null){
                    String cateId = request.getParameter("cateId");
                    String sql = "Select * from categories where category_id='"+cateId+"'";
                    String sql2= "Select * from products";
                    ResultSet rs2 = dao.getData(sql2);
                    ResultSet rs = dao.getData(sql);
                    request.setAttribute("data", rs);
                    request.setAttribute("productData", rs2);
                    dispatch(request, response, "/JSP/UpdateCategories.jsp");
               }else{
                   int cateId= Integer.parseInt(request.getParameter("cateId"));
                   String cname = request.getParameter("cname");
                   int productId = Integer.parseInt(request.getParameter("productId"));
                   
                   Categories cat = new Categories(cateId, cname, productId);
                   int n = dao.UpdateCategories(cat);
                   response.sendRedirect("CategoriesController");
               }
               
            }
            
            if (action.equals("delete")){
                 int id = Integer.parseInt(request.getParameter("id"));
                 int n =dao.removeCate(id);
                 response.sendRedirect("CategoriesController");
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
