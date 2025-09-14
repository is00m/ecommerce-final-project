package com.iso.ecommerce.dao;

import com.iso.ecommerce.model.Order;

import java.sql.*;

public class OrderDAO {
    String url = "jdbc:postgresql://localhost:5432/ecommerce";

    private final String saveScript = """
            INSERT INTO "/order/" (customer_id, total_amount, order_date, created_date, updated_date)
            VALUES (?,?,?,?,?)
            """;

    public void save(Order order) {
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement ps = connection.prepareStatement(saveScript)) {

            ps.setLong(1, order.getCustomer().getId());
            ps.setBigDecimal(2, order.getTotalAmount());
            ps.setTimestamp(3, java.sql.Timestamp.valueOf(order.getOrderDate()));

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
