package model;

import entity.Customer;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO_Customer {

    Connection conn = null;
    DBConnect dbc = new DBConnect();//Initialize an object of class DBConnect

    public DAO_Customer(DBConnect connect) {
        conn = connect.connection;
        this.dbc = connect;
    }

    //This function return a array contains list of customers
    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        String sql = " select * from Customer";
        ResultSet rs = dbc.getData(sql);
        try {
            while (rs.next()) {
                Customer customer = new Customer(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getInt(7));
                list.add(customer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //This function is responsible for adding customers
    public void addCustomer(Customer customer) {
        String sql = "insert into Customer(cname, cphone,"
                + " cAddress, username, password, status)"
                + " values(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getCustomerPhone());
            ps.setString(3, customer.getCustomerAddress());
            ps.setString(4, customer.getUsername());
            ps.setString(5, customer.getPassword());
            ps.setInt(6, customer.getStatus());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //This function is responsible for updating customers
    public void updateCustomer(Customer customer) {
        String sql = "update customer set cname=?,cAddress=?,"
                + "cphone=?,username=?,password=?,status=? where cid=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getCustomerAddress());
            ps.setString(3, customer.getCustomerPhone());
            ps.setString(4, customer.getUsername());
            ps.setString(5, customer.getPassword());
            ps.setInt(6, customer.getStatus());
            ps.setInt(7, customer.getCustomerId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //This function is responsible for deleting customers
    public void deleteCustomer(String customerId) {
        String sql = "delete from customer where cid = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, customerId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        DBConnect bConnect = new DBConnect();
        DAO_Customer dao = new DAO_Customer(bConnect);
        /*  List<Customer> list = dao.getAllCustomers();
        for (Customer customers : list) {
            System.out.println(customers);
        }
        Customer customer = new Customer("nguyen van a", "0975310760", "Soc Son", "longvit2000", "longdeptrai123", 0);
        dao.addCustomer(customer); 
        List<Customer> list = dao.getAllCustomers();
        Customer customer = list.get(5);
        String customerId = Integer.toString(customer.getCustomerId());
        dao.deleteCustomer(customerId);
        
        customer.setCustomerName("nguyen quang long");
        dao.updateCustomer(customer);
         */
    }
}
