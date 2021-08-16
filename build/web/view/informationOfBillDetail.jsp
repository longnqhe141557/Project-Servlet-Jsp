<%-- 
    Document   : viewBillDetail1
    Created on : Jul 22, 2021, 3:47:34 AM
    Author     : User
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ResultSet rs = (ResultSet) request.getAttribute("ketQua");
            String title = request.getAttribute("tieuDe").toString();
        %>
        <div style="text-align: center">
            <p style="font-size: 50px;  color: purple"><%=title%></p> 
            <!--<a style="font-size: 20px; " href="./ControllerBill?service=addBill" target="_blank">ADD BILL</a>-->
        </div>
            <table style="width: 100%" border="1">
            <thead>
                <tr>
                    <th>ID Đơn hàng</th>
                    <th>Mã sản phẩm</th>
                    <th>Số lượng</th>
                    <th>Giá sản phẩm</th>
                    <th>Tổng tiền</th>
                    <th>Ngày tạo</th>
                    <th>Tên khách hàng</th>
                    <th>Số điện thoại</th>
                    <th>Địa chỉ</th>
                    <th>Hình ảnh</th>
                    <th>Mô tả</th>
                    <th>Loại sản phẩm</th>
                </tr>
            </thead>
            <tbody>
                <%while (rs.next()) {%>
                <tr>
                    <td><%=rs.getString(1)%></td>
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getInt(3)%></td>
                    <td><%=rs.getDouble(4)%></td>
                    <td><%=rs.getDouble(5)%></td>
                    <td><%=rs.getString(6)%></td>
                    <td><%=rs.getString(7)%></td>
                    <td><%=rs.getString(8)%></td>
                    <td><%=rs.getString(9)%></td>
                    <td><img style="width: 200px; height: 140px;" src="./<%=rs.getString(11)%>"></td>
                    <td><%=rs.getString(12)%></td>
                    <td><%=rs.getString(13)%></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
