package com.iso.ecommerce.dao;

import com.iso.ecommerce.dao.constants.SqlScriptConstants;
import com.iso.ecommerce.model.Order;
import com.iso.ecommerce.model.Payment;
import com.iso.ecommerce.util.DBUtil;

import java.sql.*;

public class PaymentDao implements BaseDAO<Payment> {

    public void save(Payment payment) {
        Order order;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.PAYMENT_SAVE)) {
            ps.setLong(1, payment.getOrder().getId());
            ps.setString(2, payment.getPaymentMethod().name());
            ps.setBigDecimal(3, payment.getAmount());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Payment findById(long id) {
        return null;
    }

    @Override
    public void update(Payment payment) {

    }

    @Override
    public void delete(long id) {

    }
}
