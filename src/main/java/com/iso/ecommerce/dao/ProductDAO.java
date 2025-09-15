package com.iso.ecommerce.dao;
import com.iso.ecommerce.dao.constants.SqlScriptConstants;
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

    }

    @Override
    public Product findById(long id) {
        return null;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(long id) {

    }
}
