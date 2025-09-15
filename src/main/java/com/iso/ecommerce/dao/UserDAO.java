package com.iso.ecommerce.dao;

import com.iso.ecommerce.dao.constants.SqlScriptConstants;
import com.iso.ecommerce.exception.ExceptionMessages;
import com.iso.ecommerce.exception.ISOStoreException;
import com.iso.ecommerce.model.Customer;
import com.iso.ecommerce.model.User;
import com.iso.ecommerce.model.enums.Role;
import com.iso.ecommerce.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements BaseDAO<User>{

    @Override
    public void save(User user) {

        try(Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.USER_SAVE)) {

            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPasswrd());
            ps.setString(3, user.getRole().name());
            ps.setBoolean(4,user.getActive());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(long id) {

    }

    public User findByUsername(String username) {
        User user = null;

        try(Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.USER_FIND_BY_USERNAME) ) {
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    user = new User();
                    user.setId(rs.getLong("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPasswrd(rs.getString("passwrd"));
                    user.setRole(Role.valueOf(rs.getString("role")));
                    user.setActive(rs.getBoolean("active"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
