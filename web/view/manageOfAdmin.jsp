<%-- 
    Document   : manageOfAdmin
    Created on : Aug 15, 2021, 3:00:34 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            //String username = session.getAttribute("username").toString();
            String username = request.getAttribute("username").toString();
        %>
        <div style="text-align: center; font-size: 40px">
            <h1>Hello <%=username%></h1>
            <a style="text-decoration: none; font-size: 30px" href="manage?name=products" target="_blank">Quản lý sản phẩm</a><br>
            <a style="text-decoration: none; font-size: 30px" href="">Quản lý chủng loại sản phẩm </a><br>
            <a style="text-decoration: none; font-size: 30px" href="">Quản lý khách hàng</a><br>
            <a style="text-decoration: none; font-size: 30px" href="manage?name=bills" target="_blank">Quản lý hóa đơn</a>
        </div>
    </body>
</html>
