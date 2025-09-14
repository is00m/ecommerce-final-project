package com.iso.ecommerce.dao;

import com.iso.ecommerce.model.Category;
import com.iso.ecommerce.model.Customer;
import com.iso.ecommerce.model.Product;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    String url = "jdbc:postgresql://localhost:5432/ecommerce";

    private final String searchByNameScript = """
            SELECT * FROM product
            WHERE name LIKE ?
            """;

    public List<Product> searchByName(String name) {
        List<Product> products = new ArrayList<>();
        Product product = null;
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement ps = connection.prepareStatement(searchByNameScript)) {

            ps.setString(1, "%" + name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    product = new Product();
                    product.setId(rs.getLong("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getBigDecimal("price"));
                    product.setStock(rs.getInt("stock"));
//                    product.setCategory();
                    product.setCreatedDate(LocalDateTime.parse(rs.getString("created_date")));
                    product.setUpdatedDate(LocalDateTime.parse(rs.getString("updated_date")));
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
