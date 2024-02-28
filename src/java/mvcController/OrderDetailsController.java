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
import jakarta.servlet.annotation.WebServlet;
import java.util.Vector;
import entity.OrderDetails;
import dao.DAOOrderDetails;
import entity.Products;
import entity.Products;
import dao.DAOProducts;

/**
 *
 * @author os
 */
public class OrderDetailsController extends HttpServlet {

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
            DAOOrderDetails dao = new DAOOrderDetails();
            DAOProducts daoPro = new DAOProducts();
            String action = request.getParameter("go");
            if (action==null){
                action="listAll";
            }
            
            if (action.equals("listAll")){
                String sql = "SELECT odd.order_detail_id, odd.order_id, odd.product_id, odd.quantity, odd.total_price, odd.purchase_date, pro.product_name, pro.price\n" +
"FROM order_details AS odd\n" +
"JOIN products AS pro\n" +
"ON odd.product_id = pro.product_id";
                
                ResultSet rs = dao.getData(sql);               
                request.setAttribute("data", rs);             
                dispatch(request, response, "/JSP/ViewOrderDetails.jsp");
            }
            
            if (action.equals("insert")){
                String submit= request.getParameter("submit");
                if(submit==null){
                    String sqlOrd = "SELECT * FROM orders join customers on orders.customer_id = customers.customer_id";
                    String sqlPro = "Select * from Products";
                    ResultSet rsOrd = dao.getData(sqlOrd); 
                    ResultSet rsPro = dao.getData(sqlPro);
                    request.setAttribute("cateData", rsOrd);
                    request.setAttribute("productData", rsPro);
                    dispatch(request, response, "/JSP/AddOrderDetails.jsp");
                } else{
                    int oId= Integer.parseInt(request.getParameter("oId"));
                    int pId= Integer.parseInt(request.getParameter("pId"));
                    Vector<Products> vec = daoPro.getAllProduct("Select * from products where product_id='"+pId+"'");
                    Products pro = vec.get(0);
                    int quantity= Integer.parseInt(request.getParameter("quantity"));
                    double totalPrice= quantity * pro.getPrice();
                    String pd = request.getParameter("pd");
                    OrderDetails od = new OrderDetails(oId, pId, quantity, totalPrice, pd);
                    int n = dao.addOrderDetails(od);
                    response.sendRedirect("OrderDetailsController");
                }
            }
            
            if (action.equals("update")){
                String submit = request.getParameter("submit");
                if (submit==null){
                    String odId = request.getParameter("id");
                    String sql = "SELECT odd.order_detail_id, odd.order_id, odd.product_id, odd.quantity, odd.total_price,odd.purchase_date, pro.product_name, pro.price\n" +
                                 "FROM order_details AS odd\n" +
                                 "JOIN products AS pro\n" +
                                 "ON odd.product_id = pro.product_id where odd.order_detail_id='"+odId+"'";
                    
                    ResultSet rs = dao.getData(sql);                 
                    String sqlOrd = "SELECT * FROM orders join customers on orders.customer_id = customers.customer_id";
                    String sqlPro = "Select * from Products";
                    ResultSet rsOrd = dao.getData(sqlOrd); 
                    ResultSet rsPro = dao.getData(sqlPro);
                    request.setAttribute("orderData", rsOrd);
                    request.setAttribute("productData", rsPro);
                    request.setAttribute("data", rs);
                    dispatch(request, response, "/JSP/UpdateOrderDetails.jsp");
                }else{
                    int odId= Integer.parseInt(request.getParameter("odId"));
                    int oId= Integer.parseInt(request.getParameter("oId"));
                    int pId= Integer.parseInt(request.getParameter("pId"));                   
                    Vector<Products> vec = daoPro.getAllProduct("Select * from products where product_id='"+pId+"'");
                    Products pro = vec.get(0);
                    int quantity= Integer.parseInt(request.getParameter("quantity"));
                    double totalPrice= quantity * pro.getPrice();
                    String pd = request.getParameter("pd");
                    OrderDetails od = new OrderDetails(odId, oId, pId, quantity, totalPrice, pd);
                    int n = dao.UpdateOrderDetails(od);
                    response.sendRedirect("OrderDetailsController");
                }
            }
            
            if (action.equals("delete")){
                 int odId= Integer.parseInt(request.getParameter("id"));
                 int n = dao.removeOrderDetails(odId);
                 response.sendRedirect("OrderDetailsController");
            }
            
            if(action.equals("getdetailsfrom")){
                String id = request.getParameter("id");          
                int cid = Integer.parseInt(request.getParameter("cid"));
                String phone = request.getParameter("phone");
                String address = request.getParameter("address");
                String sql = "SELECT od.order_id, p.product_name,p.price ,od.quantity, od.total_price, od.purchase_date\n" +
"FROM products AS p JOIN order_details AS od ON p.product_id = od.product_id\n" +
"WHERE od.order_id = '"+id+"'";
                String sqlCus = "Select * from customers";
                ResultSet rsCus = dao.getData(sqlCus);
                ResultSet rs = dao.getData(sql);               
                request.setAttribute("data", rs);
                request.setAttribute("customerData", rsCus);
                request.setAttribute("requestedCid", cid);
                request.setAttribute("requestedPhone", phone);
                request.setAttribute("requestedAddress", address);
                dispatch(request, response, "/JSP/ViewDetailsById.jsp");
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
