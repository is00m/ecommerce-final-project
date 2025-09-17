package com.iso.ecommerce.dao;

import com.iso.ecommerce.dao.constants.SqlScriptConstants;
import com.iso.ecommerce.model.Category;
import com.iso.ecommerce.model.Product;
import com.iso.ecommerce.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements BaseDAO<Product> {

    public List<Product> searchByName(String name) {
        List<Product> products = new ArrayList<>();
        Product product;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.PRODUCT_SEARCH_BY_NAME)) {

            ps.setString(1, "%" + name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    product = new Product();
                    product.setId(rs.getLong("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getBigDecimal("price"));
                    product.setStock(rs.getInt("stock"));
//                    product.setCategory();
                    product.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
                    product.setUpdatedDate(rs.getTimestamp("updated_date").toLocalDateTime());
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void save(Product product) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.PRODUCT_SAVE)) {

            ps.setString(1, product.getName());
            ps.setBigDecimal(2, product.getPrice());
            ps.setInt(3, product.getStock());
            ps.setLong(4, product.getCategory().getId());
            ps.setLong(5, product.getCreatedUser().getId());
            ps.setLong(6, product.getUpdatedUser().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product findById(long id) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DBUtil.getConnection();
             Statement s = connection.createStatement()) {

            try (ResultSet rs = s.executeQuery(SqlScriptConstants.PRODUCT_FIND_ALL)) {
                while (rs.next()) {
                    products.add(new Product(rs.getLong("id"),
                            rs.getString("name"),
                            rs.getBigDecimal("price"),
                            rs.getInt("stock"),
                            new Category(rs.getLong("category_id"), rs.getString("category_name"))));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(long id) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.PRODUCT_DELETE)) {

            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
