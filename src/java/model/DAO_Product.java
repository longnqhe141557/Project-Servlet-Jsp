package model;

import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO_Product {

    Connection conn = null;
    DBConnect dbc = new DBConnect();//Initialize an object of class DBConnect

    public DAO_Product(DBConnect connect) {
        conn = connect.connection;
        this.dbc = connect;
    }

    //This function return a array contains list of products
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Product order by cateID";
        ResultSet rs = dbc.getData(sql);
        try {
            while (rs.next()) {
                Product product = new Product(rs.getString(1), rs.getString(2),
                        rs.getInt(3), rs.getDouble(4), rs.getString(5),
                        rs.getString(6), rs.getInt(7), rs.getInt(8));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //This function is responsible for adding products
    public void addProduct(Product product) {
        String sql = "insert into product(pid, pname, quantity,"
                + " price, image, description, status, cateID)"
                + " values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, product.getProductId());
            ps.setString(2, product.getProductName());
            ps.setInt(3, product.getQuantity());
            ps.setDouble(4, product.getPriceOfProduct());
            ps.setString(5, product.getProductImage());
            ps.setString(6, product.getProductDescription());
            ps.setInt(7, product.getStatus());
            ps.setInt(8, product.getCategoryId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Product.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //This function is responsible for updating products
    public void updateProduct(Product product) {
        String sql = "update product set pname=?, quantity=?,"
                + " price=?, image=?, description=?,"
                + " status=?, cateID=? where pid=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, product.getProductName());
            ps.setInt(2, product.getQuantity());
            ps.setDouble(3, product.getPriceOfProduct());
            ps.setString(4, product.getProductImage());
            ps.setString(5, product.getProductDescription());
            ps.setInt(6, product.getStatus());
            ps.setInt(7, product.getCategoryId());
            ps.setString(8, product.getProductId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Product.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //This function is responsible for deleting products
    public void deleteProduct(String productId) {
        String sql = "delete from product where pid = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, productId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        DBConnect bConnect = new DBConnect();
        DAO_Product dao = new DAO_Product(bConnect);
        /*  1.View list of products.
        List<Product> list = dao.getAllProduct();
        Product product = list.get(0);
        System.out.println(product);
        -status: thành công  
      2.Add a product.
        Product product = new Product("1111", "sadsaad", 1, 10, "dasd", "dasd", 0, 1);
        dao.addProduct(product);
        -status: thành công 
      3.Update a product.   
        List<Product> list = dao.getAllProduct();
        Product product1 = list.get(4);
        product1.setProductName("cccccc");
        product1.setQuantity(3);
        product1.setPriceOfProduct(10);
        product1.setProductImage("kkkkkk");
        product1.setProductDescription("mmmmm");
        product1.setStatus(1);
        product1.setCategoryId(2);
        dao.updateProduct(product1);
        System.out.println(product1);
        -status: thành công 
       4.Delete a product.  
         List<Product> list = dao.getAllProduct();
         Product product = list.get(0);
         String productId = product.getProductId();
         dao.deleteProduct(productId);
         -status: thành công
         */
    }
}
