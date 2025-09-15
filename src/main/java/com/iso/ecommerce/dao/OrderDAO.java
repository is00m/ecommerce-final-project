package com.iso.ecommerce.dao;

import com.iso.ecommerce.dao.constants.SqlScriptConstants;
import com.iso.ecommerce.model.Order;
import com.iso.ecommerce.util.DBUtil;

import java.sql.*;

public class OrderDAO implements BaseDAO<Order>{

    public void save(Order order) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.ORDER_SAVE)) {

            ps.setLong(1, order.getCustomer().getId());
            ps.setBigDecimal(2, order.getTotalAmount());
            ps.setTimestamp(3, Timestamp.valueOf(order.getOrderDate()));

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order findById(long id) {
        return null;
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(long id) {

    }
}
