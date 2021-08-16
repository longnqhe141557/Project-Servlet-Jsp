/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Bill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class DAO_Bill {

    Connection conn = null;
    DBConnect dbc = new DBConnect();//Initialize an object of class DBConnect

    public DAO_Bill(DBConnect connect) {
        conn = connect.connection;
        this.dbc = connect;
    }

    public List<Bill> getAllBills() {
        List<Bill> list = new ArrayList<>();
        String sql = "select * from bill";
        ResultSet rs = dbc.getData(sql);
        try {
            while (rs.next()) {
                /* Bill bill = new Bill(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getDouble(6)); */
                Bill bill = new Bill(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getDouble(6), rs.getInt(7), rs.getInt(8));
                list.add(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Bill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void addBill(Bill bill) {
        String sql = "insert into  Bill(oID,"
                + " dateCreate, cname, cphone,"
                + " cAddress, total) values(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, bill.getOrderId());
            ps.setString(2, bill.getDateCreate());
            ps.setString(3, bill.getCustomerName());
            ps.setString(4, bill.getCustomerPhone());
            ps.setString(5, bill.getCustomerAddress());
            ps.setDouble(6, bill.getTotal());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Bill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String randomCapchar() {
        String root = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        String capcha = "";
        int length = root.length();// = 62
        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            int index = r.nextInt(length);//tra ve index bat ki nam trong khoang 0 - 61
            capcha = capcha + root.charAt(index);
        }
        return capcha;
    }
}
