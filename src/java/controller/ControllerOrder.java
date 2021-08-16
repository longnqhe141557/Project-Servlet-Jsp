/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Bill;
import entity.BillDetail;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAO_Bill;
import model.DAO_BillDetail;
import model.DBConnect;

/**
 *
 * @author User
 */
@WebServlet(name = "ControllerOrder", urlPatterns = {"/order"})
public class ControllerOrder extends HttpServlet {

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
        DBConnect dbc = new DBConnect();
        DAO_Bill dao = new DAO_Bill(dbc);
        DAO_BillDetail dao2 = new DAO_BillDetail(dbc);
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service.equals("addToCart")) {
                String productID = request.getParameter("pid");
                String sql = "select * from Product where pid = '" + productID + "'";
                ResultSet rs = dbc.getData(sql);
                if (rs.next()) {
                    //Đây là cái sản phẩm của mình tìm thấy
                    Product product = new Product(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
                    HttpSession session = request.getSession();
                    //Đang lấy lại cái sản phẩm. qua session
                    Product p = (Product) session.getAttribute(productID);
                    if (p == null) {
                        product.setQuantity(1);
                        //Đẩy cái product p lên trên session
                        session.setAttribute(productID, product);
                    } else {
                        //Đang lấy lại cái sản phẩm. qua session
                        Product pro = (Product) session.getAttribute(productID);
                        int count = pro.getQuantity();
                        product.setQuantity(count + 1);
                        session.setAttribute(productID, product);
                    }
                    request.setAttribute("title", "Bạn đã thêm thành công 1 đôi " + product.getProductName() + " với mã sản phẩm là:" + product.getProductId() + " tới phần Giỏ hàng.");
                    request.getRequestDispatcher("/view/add2Cart.jsp").forward(request, response);
                }
            }
            if (service.equals("showCart")) {
                request.getRequestDispatcher("/view/showCart.jsp").forward(request, response);
            }
            if (service.equals("remove")) {
                String id = request.getParameter("id1");
                HttpSession session = request.getSession();
                session.removeAttribute(id);
                request.getRequestDispatcher("/view/showCart.jsp").forward(request, response);
            }
            if (service.equals("removeAll")) {
                HttpSession session = request.getSession();
                session.invalidate();
                request.getRequestDispatcher("/view/showCart.jsp").forward(request, response);
            }
            if (service.equals("checkout")) {
                request.getRequestDispatcher("/view/checkout.jsp").forward(request, response);
            }
            if (service.equals("checkouted")) {
                //  String orderID = request.getParameter("oid");
                String orderID = dao.randomCapchar();
                String date = java.time.LocalDateTime.now().toString();
                String cname = request.getParameter("name");
                String cphone = request.getParameter("phone");
                String cAddress = request.getParameter("address");
                Double total = Double.parseDouble(request.getParameter("total"));
                Bill bill = new Bill(orderID, date, cname, cphone, cAddress, total);
                dao.addBill(bill);
                HttpSession session = request.getSession();
                java.util.Enumeration em = session.getAttributeNames();
                while (em.hasMoreElements()) {
                    String id = em.nextElement().toString();
                    Product pro = (Product) session.getAttribute(id);
                    int quantity = pro.getQuantity();
                    String pid = pro.getProductId();
                    double price = (double) pro.getPriceOfProduct();
                    double total1 = quantity * price;
                    BillDetail billDetail = new BillDetail(pid, orderID, quantity, price, total1);
                    dao2.addBillDetail(billDetail);
                }
                session.invalidate();
                //request.getRequestDispatcher("/order?service=viewBill").forward(request, response);
                response.sendRedirect("http://localhost:8080/TheSteal/order?service=viewBill");
            }
            if (service.equals("viewBill")) {
                String sql = "select top 1 * from Bill order by dateCreate desc";
                ResultSet rs = dbc.getData(sql);
                String title = "Hóa đơn mua hàng của bạn";
                request.setAttribute("title", title);
                request.setAttribute("rs", rs);
                request.getRequestDispatcher("/view/informationOfBill.jsp").forward(request, response);
            }
            if (service.equals("viewBillDetail")) {
                String orderID = request.getParameter("bid");
                String sql = "select * from   (select BillDetail.oID, BillDetail.pid, BillDetail.quantity, BillDetail.price, BillDetail.total, Bill.dateCreate, Bill.cname, Bill.cphone, Bill.cAddress, Product.pname, Product.image, Product.description, Category.cateName\n"
                        + "		  from BillDetail\n"
                        + "		  inner join Bill on BillDetail.oID = Bill.oID\n"
                        + "		  inner join Product on Product.pid = BillDetail.pid\n"
                        + "		  inner join Category on Category.cateID = Product.cateID) as T\n"
                        + "		  where T.oID = '" + orderID + "'";
                ResultSet rs = dbc.getData(sql);
                String titleTable = "Chi tiết hóa đơn của bạn";
                request.setAttribute("ketQua", rs);
                request.setAttribute("tieuDe", titleTable);
                request.getRequestDispatcher("/view/informationOfBillDetail.jsp").forward(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ControllerOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
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
