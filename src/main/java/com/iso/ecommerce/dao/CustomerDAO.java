package com.iso.ecommerce.dao;

import com.iso.ecommerce.model.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDAO {

    private final Connection connection;

    public CustomerDAO(Connection connection) {
        this.connection = connection;
    }

    private final String saveScript = """
            
            """;

    public void save(Customer customer){
        String url = "jdbc:postgresql://localhost:5432/ecommerce";

        try {
            DriverManager.getConnection(url);
            PreparedStatement ps = connection.prepareStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

