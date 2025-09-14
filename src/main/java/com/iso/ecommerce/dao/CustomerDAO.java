package com.iso.ecommerce.dao;

import com.iso.ecommerce.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    String url = "jdbc:postgresql://localhost:5432/ecommerce";

    private final String saveScript = """
            INSERT INTO customer (first_name, last_name, email, passwrd)
            VALUES (?,?,?,?)
            """;

    private final String findByIdScript = """
            SELECT * FROM customer
            WHERE id = ?
            """;

    private final String existByEmailScript = """
            SELECT * FROM customer
            WHERE email = ?
            LIMIT 1
            """;

    private final String existByPasswordScript = """
            SELECT * FROM customer
            WHERE password = ?
            LIMIT 1
            """;

    public void save(Customer customer) {
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement ps = connection.prepareStatement(saveScript)) {

            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Customer findById(long id) {
        Customer customer = null;
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement ps = connection.prepareStatement(findByIdScript)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    customer = new Customer();
                    customer.setId(rs.getLong("id"));
                    customer.setFirstName(rs.getString("first_name"));
                    customer.setLastName(rs.getString("last_name"));
                    customer.setEmail(rs.getString("email"));
                    customer.setCreatedDate(new Timestamp(rs.getDate("created_date").getTime()).toLocalDateTime());
                    customer.setUpdatedDate(new Timestamp(rs.getDate("updated_date").getTime()).toLocalDateTime());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public boolean existByEmail(String email) {
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement ps = connection.prepareStatement(existByEmailScript)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Customer findByEmail(String email) {
        Customer customer = null;
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement ps = connection.prepareStatement(existByEmailScript)) {
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    customer = new Customer();
                    customer.setId(rs.getLong("id"));
                    customer.setFirstName(rs.getString("first_name"));
                    customer.setLastName(rs.getString("last_name"));
                    customer.setEmail(rs.getString("email"));
                    customer.setPassword(rs.getString("passwrd"));
                    customer.setCreatedDate(new Timestamp(rs.getDate("created_date").getTime()).toLocalDateTime());
                    customer.setUpdatedDate(new Timestamp(rs.getDate("updated_date").getTime()).toLocalDateTime());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
}

