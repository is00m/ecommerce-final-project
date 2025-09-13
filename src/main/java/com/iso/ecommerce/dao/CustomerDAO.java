package com.iso.ecommerce.dao;

import com.iso.ecommerce.model.Customer;

import java.sql.*;

public class CustomerDAO {
    String url = "jdbc:postgresql://localhost:5432/ecommerce";

    private final String saveScript = """
            INSERT INTO customer (first_name, last_name, email, passwrd)
            VALUES (?,?,?,?)
            """;

    private final String findByIdScript = """
            SELECT * FROM customer
            WHERE id = (?)
            """;

    public void save(Customer customer) {
        try {
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement ps = connection.prepareStatement(saveScript);
            ;
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
        }
    }

    public Customer findById(long id) {
        Customer customer = null;
        try {
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement ps = connection.prepareStatement(findByIdScript);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getLong("id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setEmail(rs.getString("email"));
//              customer.setCreatedDate(rs.getDate("created_date"));
//              customer.setUpdatedDate(rs.getDate("updated_date"));
            }
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
        }
        return customer;
    }
}

