/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Bill;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO_Bill;
import model.DAO_Product;
import model.DBConnect;

/**
 *
 * @author User
 */
@WebServlet(name = "ControllerAdminManage", urlPatterns = {"/manage"})
public class ControllerAdminManage extends HttpServlet {

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
        DAO_Product dAO_Product = new DAO_Product(dbc);
        DAO_Bill dAO_Bill = new DAO_Bill(dbc);
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            String name = request.getParameter("name");
            //This block code is responsible for manage Products(view, add, update, delete).
            if (name.equals("products")) {
                if (service == null) {
                    service = "displayAll";
                }
                if (service.equals("displayAll")) {
                    List<Product> list = dAO_Product.getAllProducts();
                    String title = "Danh sách sản phẩm";
                    request.setAttribute("product", list);
                    request.setAttribute("title", title);
                    request.getRequestDispatcher("/view/viewProduct.jsp").forward(request, response);
                }
                if (service.equals("add")) {
                    String sql = "select * from Category";
                    ResultSet rs = dbc.getData(sql);
                    request.setAttribute("ketQua", rs);
                    request.getRequestDispatcher("/view/addProduct.jsp").forward(request, response);
                }
                if (service.equals("added")) {
                    String productId = request.getParameter("ProductID");
                    String productName = request.getParameter("Name");
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    double priceOfProduct = Double.parseDouble(request.getParameter("Price"));
                    String productImage = request.getParameter("image");
                    String productDescription = request.getParameter("description");
                    int status = Integer.parseInt(request.getParameter("st"));
                    int categoryId = Integer.parseInt(request.getParameter("Cate"));
                    Product product = new Product(productId, productName,
                            quantity, priceOfProduct, productImage,
                            productDescription, status, categoryId);
                    dAO_Product.addProduct(product);
                    request.getRequestDispatcher("/manage?name=products&service=displayAll").forward(request, response);
                }
                if (service.equals("update")) {
                    String ProductID = request.getParameter("pid");
                    String sql = "select * from Product where pid = '" + ProductID + "'";
                    ResultSet rs = dbc.getData(sql);
                    String Sql1 = "select * from Category";
                    ResultSet rs1 = dbc.getData(Sql1);
                    if (rs.next()) {
                        Product product = new Product(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
                        request.setAttribute("product", product);
                        request.setAttribute("ketQua1", rs1);
                        RequestDispatcher dis = request.getRequestDispatcher("/view/updateProduct.jsp");
                        dis.forward(request, response);
                    }
                }
                if (service.equals("updated")) {
                    String proID = request.getParameter("productID");
                    String proName = request.getParameter("productName");
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    double price = Double.parseDouble(request.getParameter("price"));
                    String image = request.getParameter("image");
                    String description = request.getParameter("description");
                    int status = Integer.parseInt(request.getParameter("status"));
                    int cateID = Integer.parseInt(request.getParameter("cate"));
                    Product pro = new Product(proID, proName, quantity, price, image, description, status, cateID);
                    dAO_Product.updateProduct(pro);
                    request.getRequestDispatcher("/manage?name=products&service=displayAll").forward(request, response);
                }
                if (service.equals("delete")) {
                    String productID = request.getParameter("pid");
                    dAO_Product.deleteProduct(productID);
                    request.getRequestDispatcher("/manage?name=products&service=displayAll").forward(request, response);
                }
            }
            //This block code is responsible for manage Bill(view, update, delete).
            if (name.equals("bills")) {
                if (service == null) {
                    service = "displayAll";
                }
                if (service.equals("displayAll")) {
                    List<Bill> list = dAO_Bill.getAllBills();
                    String title = "Danh sách hóa đơn";
                    request.setAttribute("bill", list);
                    request.setAttribute("title", title);
                    request.getRequestDispatcher("/view/viewBill.jsp").forward(request, response);
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
                    String sql2 = "select * from bill where oID = '"+orderID+"'";
                    ResultSet rs2 = dbc.getData(sql2);
                    String titleTable = "Chi tiết hóa đơn";
                    request.setAttribute("rs", rs);
                    request.setAttribute("rs2", rs2);
                    request.setAttribute("title", titleTable);
                    request.getRequestDispatcher("/view/informationOfBillDetailAdmin.jsp").forward(request, response);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ControllerAdminManage.class.getName()).log(Level.SEVERE, null, ex);
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
