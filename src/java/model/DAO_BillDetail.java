/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.BillDetail;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class DAO_BillDetail {

    Connection conn = null;
    DBConnect dbc = new DBConnect();//Initialize an object of class DBConnect

    public DAO_BillDetail(DBConnect connect) {
        conn = connect.connection;
        this.dbc = connect;
    }

    public int addBillDetail(BillDetail obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[BillDetail]\n"
                + "           ([pid]\n"
                + "           ,[oID]\n"
                + "           ,[quantity]\n"
                + "           ,[price]\n"
                + "           ,[total])\n"
                + "     VALUES ('" + obj.getProductId() + "','" + obj.getOrderId() + "','" + obj.getQuantityPurchased() + "','" + obj.getPriceOfProduct()
                + "','" + obj.getTotal() + "')";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAO_BillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
