<%-- 
    Document   : viewBill
    Created on : Jun 17, 2021, 7:10:06 AM
    Author     : User
--%>

<%@page import="java.util.List"%>
<%@page import="entity.Bill"%>
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
            List<Bill> list = (List<Bill>) request.getAttribute("bill");
            String title = request.getAttribute("title").toString();
        %>
        <div style="text-align: center">
            <p style="font-size: 50px;  color: purple"><%=title%></p> 
        </div>
        <table style="width: 100%" border="1">
            <thead>
                <tr>
                    <th>ID Đơn hàng</th>
                    <th>Ngày tạo</th>
                    <th>Tên khách hàng</th>
                    <th>số điện thoại</th>
                    <th>Địa chỉ</th>
                    <th>Tổng tiền</th>
                    <th>Trạng thái</th>
                    <th>Cập nhật đơn hàng</th>
                    <th>Chi tiết đơn hàng</th>
                </tr>
            </thead>
            <tbody>
                <%for (Bill bill : list) {%>
                <tr>
                    <td><%=bill.getOrderId()%></td>
                    <td><%=bill.getDateCreate()%></td>
                    <td><%=bill.getCustomerName()%></td>
                    <td><%=bill.getCustomerPhone()%></td>
                    <td><%=bill.getCustomerAddress()%></td>
                    <td>$<%=bill.getTotal()%></td>
                    <td><%=(bill.getStatus() == 1 ? "Đã giao" : "Chưa giao")%></td>
                    <td><a style="text-decoration: none" href="manage?name=bills&service=update&bid=<%=bill.getOrderId()%>">Cập nhật</a></td>                   
                    <td><a style="text-decoration: none" href="manage?name=bills&service=viewBillDetail&bid=<%=bill.getOrderId()%>">Chi tiết đơn hàng</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>

    </body>
</html>
