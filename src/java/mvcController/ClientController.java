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
import jakarta.servlet.http.HttpSession;


/**
 *
 * @author os
 */
public class ClientController extends HttpServlet {

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
           String action = request.getParameter("go");
           if(action==null){
               action="listAll";
           }
           if (action.equals("listAll")){
                String sql = "Select * from Products";
                String sqlCate = "Select distinct category_id, category_name from categories";
                ResultSet rs = dao.getData(sql);
                ResultSet cateData = dao.getData(sqlCate);
                request.setAttribute("data", rs);
                request.setAttribute("cateData", cateData);
                dispatch(request, response, "/Client/ClientJSP.jsp");
            }
           if(action.equals("logout")){
               HttpSession session = request.getSession();
               session.invalidate();
               response.sendRedirect("ClientController");
           }
           if(action.equals("search")){
               String search = request.getParameter("search");
               String sql = "Select * from Products where product_name like'%"+search+"%'";
               String sqlCate = "Select distinct category_id, category_name from categories";
               ResultSet rs = dao.getData(sql);
               ResultSet cateData = dao.getData(sqlCate);
               request.setAttribute("data", rs);
               request.setAttribute("cateData", cateData);
               dispatch(request, response, "/Client/ClientJSP.jsp");
           }
           if(action.equals("CateItems")){
               int id = Integer.parseInt(request.getParameter("id"));
               String sql="Select p.product_id,p.product_name,p.product_type,p.product_quantity,p.description,p.price,p.status,c.category_id\n" +
"from categories as c join products as p on c.product_id=p.product_id Where c.category_id = "+id;
               String sqlCate = "Select distinct category_id, category_name from categories";
               ResultSet rs = dao.getData(sql);
               ResultSet cateData = dao.getData(sqlCate);
               request.setAttribute("data", rs);
               request.setAttribute("cateData", cateData);
               dispatch(request, response, "/Client/ClientJSP.jsp");
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
