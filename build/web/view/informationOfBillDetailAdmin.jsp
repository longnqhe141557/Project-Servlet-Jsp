<%-- 
    Document   : informationOfBillDetailAdmin
    Created on : Aug 16, 2021, 9:45:44 PM
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
            ResultSet rs = (ResultSet) request.getAttribute("rs");
            String title = request.getAttribute("title").toString();
            ResultSet rs2 = (ResultSet) request.getAttribute("rs2");
        %>
        <div style="text-align: center">
            <p style="font-size: 50px;  color: purple"><%=title%></p> 
        </div>
        <%while (rs2.next()) {%>
        <p style="font-weight: bolder; font-size: 20px">ID Đơn hàng:<i style="font-size: 25px; color: blueviolet"> <%=rs2.getString(1)%> </i></p>
        <p style="font-weight: bolder; font-size: 20px">Tên khách hàng:<i style="font-size: 25px; color: blueviolet"> <%=rs2.getString(3)%> </i></p>
        <p style="font-weight: bolder; font-size: 20px">Số điện thoại:<i style="font-size: 25px; color: blueviolet"> <%=rs2.getString(4)%> </i></p>
        <p style="font-weight: bolder; font-size: 20px">Địa chỉ:<i style="font-size: 25px; color: blueviolet"> <%=rs2.getString(5)%> </i></p>
        <p style="font-weight: bolder; font-size: 20px">Ngày mua:<i style="font-size: 25px; color: blueviolet"> <%=rs2.getString(2)%> </i></p>


        <%}%>
        <table style="width: 100%" border="1">
            <thead>
                <tr>                   
                    <th>Mã sản phẩm</th>
                    <th>Số lượng</th>
                    <th>Giá sản phẩm</th>
                    <th>Tổng tiền</th>                   
                    <th>Hình ảnh</th>
                    <th>Mô tả</th>
                    <th>Loại sản phẩm</th>
                </tr>
            </thead>
            <tbody>
                <%while (rs.next()) {%>
                <tr>                   
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getInt(3)%></td>
                    <td><%=rs.getDouble(4)%></td>
                    <td><%=rs.getDouble(5)%></td>                                      
                    <td><img style="width: 200px; height: 140px;" src="./<%=rs.getString(11)%>"></td>
                    <td><%=rs.getString(12)%></td>
                    <td><%=rs.getString(13)%></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
