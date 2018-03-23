package com.gdc.apps.dao;

import com.gdc.apps.model.Product;

import javax.sql.DataSource;
import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao extends ProductGenericDao{

    private DataSource ds;
    private String tableName;

    public ProductDao(DataSource ds, String tableName) {
        this.ds = ds;
        this.tableName = tableName;
    }

    public List<Product> search(String name, int offset){
        List<Product> list = new ArrayList<>(1);
        try(Connection c = ds.getConnection()){
            PreparedStatement ps = c.prepareStatement("SELECT * FROM " + tableName + " WHERE name ILIKE ? LIMIT 10 OFFSET ?;");
            ps.setString(1, "%" + name + "%");
            ps.setInt(2, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                list.add(create(rs));
            }
        } catch (SQLException e) {
            throw new DaoException("Error searching products: ", e);
        }
        return list;
    }

    public int getSearchSize(String name){
        try (Connection c = ds.getConnection()){
            PreparedStatement ps = c.prepareStatement("SELECT COUNT(id) FROM " + tableName + " WHERE name ILIKE ?;");
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException("Error receiving search size: ", e);
        }
        return -1;
    }

    public int getFullCount(){
        try (Connection c = ds.getConnection()) {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(id) FROM " + tableName + ";");
            if (rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException("Error receiving rows count: ", e);
        }
        return -1;
    }

    public List<Product> getSortedList(String sortStatement){
        List<Product> list = new ArrayList<>();
        try (Connection c = ds.getConnection()) {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM " + tableName + " " + sortStatement);
            while (rs.next()){
                list.add(create(rs));
            }
            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            throw new DaoException("Error receiving products sorted by price: ", e);
        }
    }

    @Override
    Long insert(Product entity) {
        try (Connection c = ds.getConnection()) {
            PreparedStatement ps = c.prepareStatement("INSERT INTO " + tableName +
                    " (category, name, price) VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getCategory());
            ps.setString(2, entity.getName());
            ps.setInt(3, entity.getPrice());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new DaoException("Error inserting product in database: ", e);
        }
        return null;
    }

    @Override
    Long update(Product entity) {
        try (Connection c = ds.getConnection()) {
            PreparedStatement ps = c.prepareStatement("UPDATE " + tableName +
                    " SET category = ?, name = ?, price = ?, rating = ? WHERE id = ?;", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getCategory());
            ps.setString(2, entity.getName());
            ps.setInt(3, entity.getPrice());
            ps.setShort(4, entity.getRating());
            ps.setLong(5, entity.getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new DaoException("Error updating product in database: ", e);
        }
        return null;
    }

    @Override
    public Product get(Long id) {
        try (Connection c = ds.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?;");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? create(rs) : null;
        } catch (SQLException e) {
            throw new DaoException("Error receiving product from database: ", e);
        }
    }

    private Product create(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String category = rs.getString("category");
        String name = rs.getString("name");
        int price = rs.getInt("price");
        short rating = rs.getShort("rating");
        return new Product(id, category, name, price, rating);
    }

    @Override
    public List<Product> getTenOffset(int offset) {
        List<Product> list = new ArrayList<>();
        try (Connection c = ds.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM " + tableName + " LIMIT 10 OFFSET ?;");
            ps.setInt(1, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                list.add(create(rs));
            }
            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            throw new DaoException("Error receiving 10 products with offset = " + offset + " : ", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection c = ds.getConnection()) {
            PreparedStatement ps = c.prepareStatement("DELETE FROM " + tableName + " WHERE id=?;");
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error deleting product with id = " + id + " : ", e);
        }
    }
}
