package com.iso.ecommerce.dao;

import com.iso.ecommerce.dao.constants.SqlScriptConstants;
import com.iso.ecommerce.model.Customer;
import com.iso.ecommerce.util.DBUtil;

import java.sql.*;
public class CustomerDAO implements BaseDAO<Customer> {

    public void save(Customer customer) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CUSTOMER_SAVE)) {

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
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CUSTOMER_FIND_BY_ID)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    customer = new Customer();
                    customer.setId(rs.getLong("id"));
                    customer.setFirstName(rs.getString("first_name"));
                    customer.setLastName(rs.getString("last_name"));
                    customer.setEmail(rs.getString("email"));
                    customer.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
                    customer.setUpdatedDate(rs.getTimestamp("updated_date").toLocalDateTime());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void delete(long id) {

    }

    public boolean existByEmail(String email) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CUSTOMER_EXIST_BY_EMAIL)) {
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
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CUSTOMER_EXIST_BY_EMAIL)) {
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    customer = new Customer();
                    customer.setId(rs.getLong("id"));
                    customer.setFirstName(rs.getString("first_name"));
                    customer.setLastName(rs.getString("last_name"));
                    customer.setEmail(rs.getString("email"));
                    customer.setPassword(rs.getString("passwrd"));
                    customer.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
                    customer.setUpdatedDate(rs.getTimestamp("updated_date").toLocalDateTime());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
}

