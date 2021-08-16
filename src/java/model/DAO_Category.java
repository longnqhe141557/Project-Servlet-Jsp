package model;

import entity.Category;
import entity.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO_Category {

    Connection conn = null;
    DBConnect dbc = new DBConnect();//Initialize an object of class DBConnect

    public DAO_Category(DBConnect connect) {
        conn = connect.connection;
        this.dbc = connect;
    }

    //This function return a array contains list of categories
    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        String sql = "select * from Category";
        ResultSet rs = dbc.getData(sql);
        try {
            while (rs.next()) {
                Category category = new Category(rs.getInt(1),
                        rs.getString(2), rs.getInt(3));
                list.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //This function is responsible for adding categories
    public void addCategory(Category category) {
        String sql = "insert into Category(cateName, status)"
                + " values(?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, category.getCategoryName());
            ps.setInt(2, category.getStatus());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Category.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //This function is responsible for updating categories
    public void updateCategory(Category category) {
        String sql = "update category set cateName = ?,"
                + "status = ? where cateID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, category.getCategoryName());
            ps.setInt(2, category.getStatus());
            ps.setInt(3, category.getCategoryId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Category.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //This function is responsible for deleting categories
    public void deleteCategory(String categoryId) {
        String sql = "delete from category where cateID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, categoryId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        DBConnect bConnect = new DBConnect();
        DAO_Category dao = new DAO_Category(bConnect);
        /* List<Category> list = dao.getAllCategories();
        for (Category category : list) {
            System.out.println(category);
        } 
        Category category = new Category("Natapongsanwha", 0);
        dao.addCategory(category);
        
        List<Category> list = dao.getAllCategories();
        Category category = list.get(4);
        category.setCategoryName("Oidoioi");
        category.setStatus(1);
        dao.updateCategory(category); 
        List<Category> list = dao.getAllCategories();
        Category category = list.get(list.size()-1);
        String categoryId = Integer.toString(category.getCategoryId());
        dao.deleteCategory(categoryId);*/
    }
}
