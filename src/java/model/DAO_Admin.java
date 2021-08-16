package model;

import entity.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAO_Admin {

    Connection conn = null;
    DBConnect dbc = new DBConnect();//Initialize an object of class DBConnect

    public DAO_Admin(DBConnect connect) {
        conn = connect.connection;
        this.dbc = connect;
    }

    public Admin checkLogin(String username, String password) {
        try {
            String sql = "select * from admin where username = ? and password = ? ";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            pre.setString(2, password);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Admin admin = new Admin(rs.getString(2), rs.getString(3));
                return admin;
            }
        } catch (Exception e) {
        }
        return null;
    }
}
