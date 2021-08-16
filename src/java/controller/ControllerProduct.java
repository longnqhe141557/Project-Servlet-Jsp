package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DBConnect;

@WebServlet(name = "ControllerProduct", urlPatterns = {"/product"})
public class ControllerProduct extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            
            String type = request.getParameter("type");
            if (type == null) {
                String word = request.getParameter("word");
                String sql = "select Product.pname, Product.price, Product.image, Product.pid\n"
                        + "from Product  where pname like '%" + word + "%'";
                ResultSet rs = dbc.getData(sql);
                String sql1 = "select * from Category";
                ResultSet rs1 = dbc.getData(sql1);
                request.setAttribute("product", rs);
                request.setAttribute("category", rs1);
                request.getRequestDispatcher("/view/home.jsp").forward(request, response);
            }
            if (type.equals("Skateboard")) {
                String sql = "select * from\n"
                        + "(select Product.pname, Product.price, Product.image, Product.pid, "
                        + "Category.cateName\n" + "from Product\n"
                        + "inner join Category on Product.cateID = Category.cateID)  as T\n"
                        + "where T.cateName = 'Skateboard'";
                ResultSet rs = dbc.getData(sql);
                String sql1 = "select * from Category";
                ResultSet rs1 = dbc.getData(sql1);
                request.setAttribute("product", rs);
                request.setAttribute("category", rs1);
                request.getRequestDispatcher("/view/home.jsp").forward(request, response);
            }
            if (type.equals("Lifestyle")) {
                String sql = "select * from\n"
                        + "(select Product.pname, Product.price, Product.image, Product.pid, "
                        + "Category.cateName\n" + "from Product\n"
                        + "inner join Category on Product.cateID = Category.cateID)  as T\n"
                        + "where T.cateName = 'Lifestyle'";
                ResultSet rs = dbc.getData(sql);
                String sql1 = "select * from Category";
                ResultSet rs1 = dbc.getData(sql1);
                request.setAttribute("product", rs);
                request.setAttribute("category", rs1);
                request.getRequestDispatcher("/view/home.jsp").forward(request, response);
            }
            if (type.equals("Running")) {
                String sql = "select * from\n"
                        + "(select Product.pname, Product.price, Product.image, Product.pid, "
                        + "Category.cateName\n" + "from Product\n"
                        + "inner join Category on Product.cateID = Category.cateID)  as T\n"
                        + "where T.cateName = 'Running'";
                ResultSet rs = dbc.getData(sql);
                String sql1 = "select * from Category";
                ResultSet rs1 = dbc.getData(sql1);
                request.setAttribute("product", rs);
                request.setAttribute("category", rs1);
                request.getRequestDispatcher("/view/home.jsp").forward(request, response);
            }
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
