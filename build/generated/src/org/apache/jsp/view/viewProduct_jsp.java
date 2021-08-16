package org.apache.jsp.view;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import entity.Product;

public final class viewProduct_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Manage Product</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            List<Product> list = (List<Product>) request.getAttribute("product");
            String title = request.getAttribute("title").toString();
        
      out.write("\n");
      out.write("        <div style=\"text-align: center\">\n");
      out.write("            <p style=\"font-size: 50px;  color: purple\">");
      out.print(title);
      out.write("</p> \n");
      out.write("            <a style=\"font-size: 40px; text-decoration: none \" href=\"#\" target=\"_blank\">Add Product</a>\n");
      out.write("        </div>\n");
      out.write("            <table style=\"width: 100%\" border=\"1\">                   \n");
      out.write("            <thead>\n");
      out.write("                <tr>\n");
      out.write("                    <th>Mã sản phẩm</th>\n");
      out.write("                    <th>Tên sản phẩm</th>\n");
      out.write("                    <th>Số lượng</th>\n");
      out.write("                    <th>Giá</th>\n");
      out.write("                    <th>Hình ảnh</th>\n");
      out.write("                    <th>Mô tả</th>\n");
      out.write("                    <th>Trạng thái</th>\n");
      out.write("                    <th>Xóa</th>\n");
      out.write("                    <th>Sửa</th>\n");
      out.write("                </tr>\n");
      out.write("            </thead>\n");
      out.write("            <tbody>\n");
      out.write("                ");
for(Product product : list) {
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                    <td>");
      out.print(product.getProductId());
      out.write("</td>\n");
      out.write("                    <td>");
      out.print(product.getProductName());
      out.write("</td>\n");
      out.write("                    <td>");
      out.print(product.getQuantity());
      out.write("</td>\n");
      out.write("                    <td>");
      out.print(product.getPriceOfProduct());
      out.write("</td>\n");
      out.write("                    <td><img style=\"width: 200px; height: 140px;\" src=\"./");
      out.print(product.getProductImage());
      out.write("\"></td>\n");
      out.write("                    <td style=\"width: 200px\">");
      out.print(product.getProductDescription());
      out.write("</td>\n");
      out.write("                    <td>");
      out.print((product.getStatus() == 1 ? "Còn hàng" : "Hết hàng"));
      out.write("</td>\n");
      out.write("                    <td style=\"font-size: 30px\"><a style=\"text-decoration: none\" href=\"#\">xóa</a></td>\n");
      out.write("                    <td style=\"font-size: 30px\"><a style=\"text-decoration: none\" href=\"#\">sửa</a></td>\n");
      out.write("                </tr>\n");
      out.write("                ");
}
      out.write("\n");
      out.write("            </tbody>\n");
      out.write("        </table>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
