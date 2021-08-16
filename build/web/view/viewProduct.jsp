<%-- 
    Document   : viewProduct
    Created on : Aug 14, 2021, 5:40:28 PM
    Author     : User
--%>

<%@page import="java.util.List"%>
<%@page import="entity.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Product</title>
    </head>
    <body>
        <%
            List<Product> list = (List<Product>) request.getAttribute("product");
            String title = request.getAttribute("title").toString();
        %>
        <div style="text-align: center; margin-bottom: 20px">
            <p style="font-size: 50px;  color: purple"><%=title%></p> 
            <a style="font-size: 40px; text-decoration: none " href="manage?name=products&service=add" target="_blank">Thêm sản phẩm tại đây!</a>
        </div>
            <table style="width: 100%" border="1">                   
            <thead>
                <tr>
                    <th>Mã sản phẩm</th>
                    <th>Tên sản phẩm</th>
                    <th>Số lượng</th>
                    <th>Giá</th>
                    <th>Hình ảnh</th>
                    <th>Mô tả</th>
                    <th>Trạng thái</th>
                    <th>Xóa</th>
                    <th>Sửa</th>
                </tr>
            </thead>
            <tbody>
                <%for(Product product : list) {%>
                <tr>
                    <td><%=product.getProductId()%></td>
                    <td><%=product.getProductName()%></td>
                    <td><%=product.getQuantity()%></td>
                    <td><%=product.getPriceOfProduct()%></td>
                    <td><img style="width: 200px; height: 140px;" src="./<%=product.getProductImage()%>"></td>
                    <td style="width: 200px"><%=product.getProductDescription()%></td>
                    <td><%=(product.getStatus() == 1 ? "Còn hàng" : "Hết hàng")%></td>
                    <td style="font-size: 30px"><a style="text-decoration: none" href="manage?name=products&service=delete&pid=<%=product.getProductId()%>">xóa</a></td>
                    <td style="font-size: 30px"><a style="text-decoration: none" href="manage?name=products&service=update&pid=<%=product.getProductId()%>">sửa</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
